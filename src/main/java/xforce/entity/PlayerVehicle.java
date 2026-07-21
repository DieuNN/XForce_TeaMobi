package xforce.entity;

import xforce.data.MissionState;
import xforce.game.GameLevel;
import xforce.game.XMIDlet;
import xforce.map.MapRenderer;
import xforce.resource.ResourceManager;
import xforce.resource.SpriteSheet;
import xforce.screen.GameScreen;

import javax.microedition.lcdui.Graphics;

public final class PlayerVehicle extends Sprite {

    private static final byte VEHICLE_TANK  = -1;
    private static final byte VEHICLE_HELI  = -2;
    private static final byte VEHICLE_JEEP  = -3;

    private static final int DIR_RIGHT  = 0;
    private static final int DIR_DOWN   = 2;
    private static final int DIR_LEFT   = 4;
    private static final int DIR_UP     = 6;

    private static final int TURRET_TRANSITION = 7;

    private static final int PICKUP_XP       = 0;
    private static final int PICKUP_CASH     = 1;
    private static final int PICKUP_REPAIR   = 2;
    private static final int PICKUP_SPEED    = 3;
    private static final int PICKUP_DYNAMITE = 4;
    private static final int PICKUP_MINE     = 5;
    private static final int PICKUP_FLAG     = 6;
    private static final int PICKUP_COMPLETE = 7;

    private static final int BULLET_TANK   = 0;
    private static final int BULLET_HELI   = 1;
    private static final int BULLET_CANNON = 2;
    private static final int BULLET_ROCKET = 3;
    private static final int BULLET_PIERCE = 4;
    private static final byte BULLET_HOMING = 10;
    private static final byte BULLET_DYNAMITE = 16;

    private static final byte EFFECT_SMOKE    = 2;
    private static final byte LAYER_AIR       = 2;
    private static final byte EFFECT_BOOM     = 5;
    private static final byte EFFECT_TRACK_H  = 8;
    private static final byte EFFECT_TRACK_V  = 9;

    private static final int HELI_DRAG      = 128;
    private static final int HELI_ACCEL     = 512;
    private static final int SPEED_BOOST    = 1024;
    private static final int BOOST_DURATION = 1000;

    private static final int HP_BASE_TANK = 50;
    private static final int HP_BASE_HELI = 80;
    private static final int HP_BASE_JEEP = 20;
    private static final int HP_PER_ARMOR = 10;
    private static final int REPAIR_PERCENT = 5;

    private static final int AUTO_TURRET_RANGE = 8;
    private static final int AUTO_FIRE_SCAN_MS = 20;
    private static final int AUTO_FIRE_COOLDOWN = 70;
    private static final int AUTO_SCAN_RADIUS   = 100;

    private static final int GUN_RECOIL      = 2;
    private static final int COOLDOWN_MINE_DYNAMITE = 50;
    private static final int MAX_MINES_DYNAMITE = 5;

    private static final int CAMERA_LEAD = 32;
    private static final int WALL_HUG_THRESHOLD = 5;

    private static final int UPGRADE_SPEED = 0;
    private static final int UPGRADE_WEAPON = 1;
    private static final int UPGRADE_ARMOR = 2;
    private static final int UPGRADE_REPAIR = 3;
    private static final int UPGRADE_ITEMS = 4;

    private static final int DEFAULT_SPEED = 3072;
    private static final int SPEED_SCALE = 512;
    private static final int ARMOR_SCALE = 128;
    private int bodyDirection;
    private int turretDirection;
    private int gunState;
    private int fireRate;
    private int autoFireCooldown;
    private int gunRecoil;
    private int speedBoostTimer;
    private int gunTreadAnim;
    public int fireCooldown;
    public int mineCooldown;
    public int dynamiteCooldown;
    private int currentSpeed;
    private byte bulletType;
    private boolean destroyed;
    private boolean hasAutoTurret;
    private int rotorAnim;
    private int rotorFrame;
    private int heliDrag;
    private int heliAccel;
    private int autoDetectRange;

    public PlayerVehicle(byte vehicleType) {
        super(0, 0, vehicleType);
        this.speedBoostTimer = 0;
        this.heliDrag = HELI_DRAG;
        this.heliAccel = HELI_ACCEL;
        this.faction = (byte) 1;
        setSize(24, 24);
        this.turretDirection = 0;
        if (vehicleType == VEHICLE_JEEP) {
            this.z = 20;
            this.layer = (byte) LAYER_AIR;
        }
        if (this.type == VEHICLE_HELI) {
            ResourceManager.tankBodySprite = new SpriteSheet(ResourceManager.loadImage("/tg0.png"), 32, 32);
            ResourceManager.playerSprite = new SpriteSheet(ResourceManager.loadImage("/mt0.png"), 24, 24);
            this.speed = ((DEFAULT_SPEED + (GameLevel.vehicleUpgrades[1][0] * SPEED_SCALE)) - (GameLevel.vehicleUpgrades[1][2] * ARMOR_SCALE)) - (GameLevel.vehicleUpgrades[1][1] * ARMOR_SCALE);
            this.hasAutoTurret = false;
            switch (GameLevel.vehicleUpgrades[1][1]) {
                case 0:
                    this.bulletType = (byte) BULLET_CANNON;
                    this.fireRate = 10;
                    break;
                case 1:
                    this.bulletType = (byte) BULLET_CANNON;
                    this.fireRate = 7;
                    break;
                case 2:
                    this.bulletType = (byte) BULLET_ROCKET;
                    this.fireRate = 10;
                    break;
                case 3:
                    this.bulletType = (byte) BULLET_ROCKET;
                    this.fireRate = 7;
                    this.hasAutoTurret = true;
                    break;
                case 4:
                    this.bulletType = (byte) BULLET_PIERCE;
                    this.fireRate = 10;
                    this.hasAutoTurret = true;
                    break;
            }
            this.maxHp = (short) (HP_BASE_HELI + (GameLevel.vehicleUpgrades[1][2] * HP_PER_ARMOR));
            this.currentHp = (short) ((GameLevel.vehicleUpgrades[1][3] * this.maxHp) / 100);
            GameLevel.dynamiteCount = GameLevel.vehicleUpgrades[1][4] % 10;
            GameLevel.mineCount = GameLevel.vehicleUpgrades[1][4] / 10;
        }
        if (this.type == VEHICLE_TANK) {
            ResourceManager.tankBodySprite = new SpriteSheet(ResourceManager.loadImage("/sgun.png"), 15, 15);
            ResourceManager.playerSprite = new SpriteSheet(ResourceManager.loadImage("/humer.png"), 24, 24);
            this.speed = ((DEFAULT_SPEED + (GameLevel.vehicleUpgrades[0][0] * SPEED_SCALE)) - (GameLevel.vehicleUpgrades[0][2] * ARMOR_SCALE)) - (GameLevel.vehicleUpgrades[0][1] * ARMOR_SCALE);
            switch (GameLevel.vehicleUpgrades[0][1]) {
                case 0:
                    this.bulletType = (byte) BULLET_TANK;
                    this.fireRate = 10;
                    break;
                case 1:
                    this.bulletType = (byte) BULLET_TANK;
                    this.fireRate = 7;
                    break;
                case 2:
                    this.bulletType = (byte) BULLET_HELI;
                    this.fireRate = 7;
                    break;
                case 3:
                    this.bulletType = (byte) BULLET_HELI;
                    this.fireRate = 5;
                    break;
                case 4:
                    this.bulletType = (byte) BULLET_CANNON;
                    this.fireRate = 5;
                    break;
            }
            this.maxHp = (short) (HP_BASE_TANK + (GameLevel.vehicleUpgrades[0][2] * HP_PER_ARMOR));
            this.currentHp = (short) ((GameLevel.vehicleUpgrades[0][3] * this.maxHp) / 100);
            GameLevel.dynamiteCount = GameLevel.vehicleUpgrades[0][4] % 10;
            GameLevel.mineCount = GameLevel.vehicleUpgrades[0][4] / 10;
        }
        if (this.type == VEHICLE_JEEP) {
            ResourceManager.playerSprite = new SpriteSheet(ResourceManager.loadImage("/heli.png"), 24, 24);
            ResourceManager.heliShadowSprite = new SpriteSheet(ResourceManager.loadImage("/sheli.png"), 24, 24);
            ResourceManager.propellerSprite2 = new SpriteSheet(ResourceManager.loadImage("/probeller2.png"), 24, 24);
            this.speed = ((DEFAULT_SPEED + (GameLevel.vehicleUpgrades[2][0] * SPEED_SCALE)) - (GameLevel.vehicleUpgrades[2][2] * ARMOR_SCALE)) - (GameLevel.vehicleUpgrades[2][1] * ARMOR_SCALE);
            switch (GameLevel.vehicleUpgrades[2][1]) {
                case 0:
                    this.bulletType = (byte) BULLET_TANK;
                    this.fireRate = 10;
                    break;
                case 1:
                    this.bulletType = (byte) BULLET_TANK;
                    this.fireRate = 7;
                    break;
                case 2:
                    this.bulletType = (byte) BULLET_HELI;
                    this.fireRate = 7;
                    break;
                case 3:
                    this.bulletType = (byte) BULLET_HELI;
                    this.fireRate = 5;
                    break;
                case 4:
                    this.bulletType = (byte) BULLET_CANNON;
                    this.fireRate = 5;
                    break;
            }
            this.maxHp = (short) (HP_BASE_JEEP + (GameLevel.vehicleUpgrades[2][2] * HP_PER_ARMOR));
            this.currentHp = (short) ((GameLevel.vehicleUpgrades[2][3] * this.maxHp) / 100);
            GameLevel.dynamiteCount = GameLevel.vehicleUpgrades[2][4] % 10;
            GameLevel.mineCount = GameLevel.vehicleUpgrades[2][4] / 10;
        }
        if (this.currentHp <= 0) {
            this.currentHp = (short) (this.maxHp / 2);
        }
        this.autoDetectRange = AUTO_TURRET_RANGE;
    }

    @Override
    public final void draw(Graphics graphics) {
        if (this.destroyed) {
            ResourceManager.playerSprite.drawFrame(8, this.x, this.y, 0, graphics);
            return;
        }
        if (this.type == VEHICLE_JEEP) {
            ResourceManager.heliShadowSprite.drawFrame(this.rotorFrame, this.x + this.z, this.y + this.z, 0, graphics);
            ResourceManager.playerSprite.drawFrame(this.rotorFrame, this.x, this.y, 0, graphics);
            ResourceManager.propellerSprite2.drawFrame(this.rotorAnim, this.x, this.y, 0, graphics);
            this.rotorAnim++;
            if (this.rotorAnim >= 4) {
                this.rotorAnim = 0;
            }
            if (this.bodyDirection == 6 && this.rotorFrame == 0) {
                this.rotorFrame = 7;
                return;
            }
            if (this.bodyDirection == 0 && this.rotorFrame == 6) {
                this.rotorFrame = 7;
                return;
            }
            if (this.bodyDirection == 0 && this.rotorFrame == 7) {
                this.rotorFrame = 0;
                return;
            }
            if (this.rotorFrame < this.bodyDirection) {
                this.rotorFrame++;
            }
            if (this.rotorFrame > this.bodyDirection) {
                this.rotorFrame--;
                return;
            }
            return;
        }
        ResourceManager.playerSprite.drawFrame((this.bodyDirection << 1) + this.gunTreadAnim, this.x, this.y, 0, graphics);
        if (this.type == VEHICLE_HELI) {
            if (this.gunRecoil > 0) {
                switch (this.gunState) {
                    case 0:
                        ResourceManager.tankBodySprite.drawFrame(this.gunState, (this.x - this.gunRecoil) - 4, this.y - 4, 0, graphics);
                        break;
                    case 2:
                        ResourceManager.tankBodySprite.drawFrame(this.gunState, this.x - 4, (this.y - this.gunRecoil) - 4, 0, graphics);
                        break;
                    case 4:
                        ResourceManager.tankBodySprite.drawFrame(this.gunState, (this.x + this.gunRecoil) - 4, this.y - 4, 0, graphics);
                        break;
                    case 6:
                        ResourceManager.tankBodySprite.drawFrame(this.gunState, this.x - 4, (this.y + this.gunRecoil) - 4, 0, graphics);
                        break;
                }
                this.gunRecoil--;
            } else {
                ResourceManager.tankBodySprite.drawFrame(this.gunState, this.x - 4, this.y - 4, 0, graphics);
            }
        } else if (this.gunRecoil > 0) {
            switch (this.gunState) {
                case 0:
                    ResourceManager.tankBodySprite.drawFrame(this.gunState, (this.x - this.gunRecoil) + 4, this.y + 4, 0, graphics);
                    break;
                case 2:
                    ResourceManager.tankBodySprite.drawFrame(this.gunState, this.x + 4, (this.y - this.gunRecoil) + 4, 0, graphics);
                    break;
                case 4:
                    ResourceManager.tankBodySprite.drawFrame(this.gunState, this.x + this.gunRecoil + 4, this.y + 4, 0, graphics);
                    break;
                case 6:
                    ResourceManager.tankBodySprite.drawFrame(this.gunState, this.x + 4, this.y + this.gunRecoil + 4, 0, graphics);
                    break;
            }
            this.gunRecoil--;
        } else {
            ResourceManager.tankBodySprite.drawFrame(this.gunState, this.x + 4, this.y + 4, 0, graphics);
        }
        if (this.turretDirection == 6 && this.gunState == 0) {
            this.gunState = 7;
            return;
        }
        if (this.turretDirection == 0 && this.gunState == 6) {
            this.gunState = 7;
            return;
        }
        if (this.turretDirection == 0 && this.gunState == 7) {
            this.gunState = 0;
            return;
        }
        if (this.gunState < this.turretDirection) {
            this.gunState++;
        }
        if (this.gunState > this.turretDirection) {
            this.gunState--;
        }
    }

    @Override
    public final void update() {
        if (this.type == VEHICLE_JEEP) {
            if (this.destroyed) {
                return;
            }
            if (this.speedBoostTimer > 0) {
                if (this.speedBoostTimer == 1) {
                    this.speed -= SPEED_BOOST;
                    GameLevel.spawnFloatingText("SPEED--", this.x, this.y);
                }
                this.speedBoostTimer--;
            }
            if (this.velocityX > this.heliDrag) {
                this.velocityX -= this.heliDrag;
            } else if (this.velocityX < (-this.heliDrag)) {
                this.velocityX += this.heliDrag;
            } else {
                this.velocityX = 0;
            }
            if (this.velocityY > this.heliDrag) {
                this.velocityY -= this.heliDrag;
            } else if (this.velocityY < (-this.heliDrag)) {
                this.velocityY += this.heliDrag;
            } else {
                this.velocityY = 0;
            }
            if (GameLevel.player == this) {
                if (GameScreen.inputState[0]) {
                    this.velocityY = this.velocityY > (-this.speed) ? this.velocityY - this.heliAccel : -this.speed;
                } else if (GameScreen.inputState[1]) {
                    this.velocityY = this.velocityY < this.speed ? this.velocityY + this.heliAccel : this.speed;
                } else if (GameScreen.inputState[2]) {
                    this.velocityX = this.velocityX > (-this.speed) ? this.velocityX - this.heliAccel : -this.speed;
                } else if (GameScreen.inputState[3]) {
                    this.velocityX = this.velocityX < this.speed ? this.velocityX + this.heliAccel : this.speed;
                }
                if (MissionState.missionType == 12) {
                    this.bodyDirection = 6;
                } else if (!GameScreen.inputState[4]) {
                    if (GameScreen.inputState[0]) {
                        this.bodyDirection = 6;
                        this.turretDirection = 6;
                    } else if (GameScreen.inputState[1]) {
                        this.bodyDirection = 2;
                        this.turretDirection = 2;
                    } else if (GameScreen.inputState[2]) {
                        this.bodyDirection = 4;
                        this.turretDirection = 4;
                    } else if (GameScreen.inputState[3]) {
                        this.bodyDirection = 0;
                        this.turretDirection = 0;
                    }
                }
                applyVelocity();
                if (this.x < 0) {
                    this.x = 0;
                }
                if (this.x + this.width > MapRenderer.mapPixelWidth) {
                    this.x = MapRenderer.mapPixelWidth - this.width;
                }
                if (this.y < 0) {
                    this.y = 0;
                }
                if (this.y + this.height > MapRenderer.mapPixelHeight) {
                    this.y = MapRenderer.mapPixelHeight - this.height;
                }
                if (this.fireCooldown > 0) {
                    this.fireCooldown--;
                }
                if (this.fireCooldown == 0) {
                    boolean zM96g = GameLevel.autoShoot ? MissionState.missionType == 12 ? true : autoFireTarget() : false;
                    if (GameScreen.inputState[4] || zM96g) {
                        switch (this.bodyDirection) {
                            case 0:
                                GameLevel.spawnBullet(this.bulletType, this.x + (this.width >> 1) + 15, this.y + 6, 0, null);
                                GameLevel.spawnBullet(this.bulletType, this.x + (this.width >> 1) + 15, this.y + 18, 0, null);
                                break;
                            case 2:
                                GameLevel.spawnBullet(this.bulletType, this.x + 6, this.y + (this.height >> 1) + 15, 90, null);
                                GameLevel.spawnBullet(this.bulletType, this.x + 18, this.y + (this.height >> 1) + 15, 90, null);
                                break;
                            case 4:
                                GameLevel.spawnBullet(this.bulletType, (this.x + (this.width >> 1)) - 15, this.y + 6, 180, null);
                                GameLevel.spawnBullet(this.bulletType, (this.x + (this.width >> 1)) - 15, this.y + 18, 180, null);
                                break;
                            case 6:
                                GameLevel.spawnBullet(this.bulletType, this.x + 6, (this.y + (this.height >> 1)) - 15, 270, null);
                                GameLevel.spawnBullet(this.bulletType, this.x + 18, (this.y + (this.height >> 1)) - 15, 270, null);
                                break;
                        }
                        this.fireCooldown = this.fireRate;
                    }
                }
                if (this.autoFireCooldown <= 0) {
                    this.autoFireCooldown = AUTO_FIRE_SCAN_MS;
                    for (int i = 0; i < GameLevel.entityCount; i++) {
                        if (GameLevel.entities[i].layer == 2 && GameLevel.entities[i].faction == -1 && GameLevel.entities[i].x > this.x - AUTO_SCAN_RADIUS && GameLevel.entities[i].x < this.x + AUTO_SCAN_RADIUS && GameLevel.entities[i].y > this.y - AUTO_SCAN_RADIUS && GameLevel.entities[i].y < this.y + AUTO_SCAN_RADIUS) {
                            GameLevel.spawnBullet(BULLET_HOMING, this.x, this.y, ResourceManager.angleBetween(GameLevel.entities[i].x - this.x, GameLevel.entities[i].y - this.y), GameLevel.entities[i]);
                            this.autoFireCooldown = AUTO_FIRE_COOLDOWN;
                            System.out.println("shoot socket");
                        }
                    }
                } else {
                    this.autoFireCooldown--;
                }
                if (this.mineCooldown > 0) {
                    this.mineCooldown--;
                }
                if (this.dynamiteCooldown > 0) {
                    this.dynamiteCooldown--;
                    return;
                }
                return;
            }
            return;
        }
        if (this.destroyed) {
            return;
        }
        if (this.speedBoostTimer > 0) {
            if (this.speedBoostTimer == 1) {
                this.speed -= SPEED_BOOST;
                GameLevel.spawnFloatingText("SPEED--", this.x, this.y);
            }
            this.speedBoostTimer--;
        }
        if (GameLevel.player != this) {
            return;
        }
        int tileRemX = this.x % MapRenderer.tileSize;
        int tileRemY = this.y % MapRenderer.tileSize;
        int tileRow = this.y / MapRenderer.tileSize;
        if (this.y < 0) {
            tileRow--;
        }
        int tileCol = this.x / MapRenderer.tileSize;
        if (this.x < 0) {
            tileCol--;
        }
        GameLevel.clearTileOccupant(tileRow, tileCol, this);
        if (tileRemX != 0) {
            GameLevel.clearTileOccupant(tileRow, tileCol + 1, this);
        }
        if (tileRemY != 0) {
            GameLevel.clearTileOccupant(tileRow + 1, tileCol, this);
        }
        if (tileRemX != 0 && tileRemY != 0) {
            GameLevel.clearTileOccupant(tileRow + 1, tileCol + 1, this);
        }
        this.velocityY = 0;
        this.velocityX = 0;
        this.currentSpeed = this.speed;
        if (GameScreen.inputState[0]) {
            if (MapRenderer.isBlocked(tileRow, tileCol)) {
                if (!MapRenderer.isBlocked(tileRow, tileCol + 1)) {
                    if (MapRenderer.tileSize - tileRemX <= WALL_HUG_THRESHOLD) {
                        this.x += MapRenderer.tileSize - tileRemX;
                    } else if (!MapRenderer.isBlocked(tileRow + 1, tileCol + 1)) {
                        this.bodyDirection = 0;
                        this.velocityX = this.currentSpeed;
                    }
                }
            } else if (tileRemX == 0 || !MapRenderer.isBlocked(tileRow, tileCol + 1)) {
                this.bodyDirection = 3;
                this.velocityY = -this.currentSpeed;
            } else if (tileRemX <= WALL_HUG_THRESHOLD) {
                this.x -= tileRemX;
            } else if (!MapRenderer.isBlocked(tileRow + 1, tileCol)) {
                this.bodyDirection = 2;
                this.velocityX = -this.currentSpeed;
            }
        } else if (GameScreen.inputState[1]) {
            if (MapRenderer.isBlocked(tileRow + 1, tileCol)) {
                if (!MapRenderer.isBlocked(tileRow + 1, tileCol + 1)) {
                    if (MapRenderer.tileSize - tileRemX <= WALL_HUG_THRESHOLD) {
                        this.x += MapRenderer.tileSize - tileRemX;
                    } else if (!MapRenderer.isBlocked(tileRow, tileCol + 1)) {
                        this.bodyDirection = 0;
                        this.velocityX = this.currentSpeed;
                    }
                }
            } else if (tileRemX == 0 || !MapRenderer.isBlocked(tileRow + 1, tileCol + 1)) {
                this.bodyDirection = 1;
                this.velocityY = this.currentSpeed;
            } else if (tileRemX <= WALL_HUG_THRESHOLD) {
                this.x -= tileRemX;
            } else if (!MapRenderer.isBlocked(tileRow, tileCol)) {
                this.bodyDirection = 2;
                this.velocityX = -this.currentSpeed;
            }
        } else if (GameScreen.inputState[2]) {
            if (MapRenderer.isBlocked(tileRow, tileCol)) {
                if (!MapRenderer.isBlocked(tileRow + 1, tileCol)) {
                    if (MapRenderer.tileSize - tileRemY <= WALL_HUG_THRESHOLD) {
                        this.y += MapRenderer.tileSize - tileRemY;
                    } else if (!MapRenderer.isBlocked(tileRow + 1, tileCol + 1)) {
                        this.bodyDirection = 1;
                        this.velocityY = this.currentSpeed;
                    }
                }
            } else if (tileRemY == 0 || !MapRenderer.isBlocked(tileRow + 1, tileCol)) {
                this.bodyDirection = 2;
                this.velocityX = -this.currentSpeed;
            } else if (tileRemY <= WALL_HUG_THRESHOLD) {
                this.y -= tileRemY;
            } else if (!MapRenderer.isBlocked(tileRow, tileCol + 1)) {
                this.bodyDirection = 3;
                this.velocityY = -this.currentSpeed;
            }
        } else if (!GameScreen.inputState[3]) {
            this.currentSpeed = 0;
        } else if (MapRenderer.isBlocked(tileRow, tileCol + 1)) {
            if (!MapRenderer.isBlocked(tileRow + 1, tileCol + 1) && MapRenderer.tileSize - tileRemY <= WALL_HUG_THRESHOLD) {
                this.y += MapRenderer.tileSize - tileRemY;
            }
        } else if (tileRemY == 0 || !MapRenderer.isBlocked(tileRow + 1, tileCol + 1)) {
            this.bodyDirection = 0;
            this.velocityX = this.currentSpeed;
        } else if (tileRemY <= WALL_HUG_THRESHOLD) {
            this.y -= tileRemY;
        } else if (!MapRenderer.isBlocked(tileRow, tileCol)) {
            this.bodyDirection = 3;
            this.velocityY = -this.currentSpeed;
        }
        applyVelocity();
        if (this.type == VEHICLE_HELI) {
            if (tileRow != this.y / MapRenderer.tileSize) {
                GameLevel.spawnEffect(EFFECT_TRACK_H, this.x, this.y, 0, 0, 0);
            } else if (tileCol != this.x / MapRenderer.tileSize) {
                GameLevel.spawnEffect(EFFECT_TRACK_V, this.x, this.y, 0, 0, 0);
            }
        }
        int tileRow2 = this.y / MapRenderer.tileSize;
        if (this.y < 0) {
            tileRow2--;
        }
        int tileCol2 = this.x / MapRenderer.tileSize;
        if (this.x < 0) {
            tileCol2--;
        }
        int tileRemX2 = this.x % MapRenderer.tileSize;
        int tileRemY2 = this.y % MapRenderer.tileSize;
        GameLevel.setTileOccupant(tileRow2, tileCol2, this);
        if (tileRemX2 != 0) {
            GameLevel.setTileOccupant(tileRow2, tileCol2 + 1, this);
        }
        if (tileRemY2 != 0) {
            GameLevel.setTileOccupant(tileRow2 + 1, tileCol2, this);
        }
        if (tileRemX2 != 0 && tileRemY2 != 0) {
            GameLevel.setTileOccupant(tileRow2 + 1, tileCol2 + 1, this);
        }
        if (this.currentSpeed != 0) {
            this.gunTreadAnim = 1 - this.gunTreadAnim;
        }
        if (!GameScreen.inputState[4]) {
            if (GameScreen.inputState[0]) {
                this.turretDirection = 6;
            } else if (GameScreen.inputState[1]) {
                this.turretDirection = 2;
            } else if (GameScreen.inputState[2]) {
                this.turretDirection = 4;
            } else if (GameScreen.inputState[3]) {
                this.turretDirection = 0;
            }
        }
        if (this.fireCooldown > 0) {
            this.fireCooldown--;
        }
        if (this.fireCooldown == 0) {
            boolean z = false;
            if (GameLevel.autoShoot && autoFireTarget()) {
                z = true;
            }
            if (GameScreen.inputState[4] || z) {
                switch (this.turretDirection) {
                    case 0:
                        GameLevel.spawnBullet(this.bulletType, this.x + (this.width >> 1) + 15, this.y + (this.height >> 1), 0, null);
                        GameLevel.spawnEffect(EFFECT_SMOKE, this.x + (this.width >> 1) + 15, this.y + (this.height >> 1), 1024, 0, 0);
                        break;
                    case 2:
                        GameLevel.spawnBullet(this.bulletType, this.x + (this.width >> 1), this.y + (this.height >> 1) + 15, 90, null);
                        GameLevel.spawnEffect(EFFECT_SMOKE, this.x + (this.width >> 1), this.y + (this.height >> 1) + 15, 0, 1024, 0);
                        break;
                    case 4:
                        GameLevel.spawnBullet(this.bulletType, (this.x + (this.width >> 1)) - 15, this.y + (this.height >> 1), 180, null);
                        GameLevel.spawnEffect(EFFECT_SMOKE, (this.x + (this.width >> 1)) - 15, this.y + (this.height >> 1), -1024, 0, 0);
                        break;
                    case 6:
                        GameLevel.spawnBullet(this.bulletType, this.x + (this.width >> 1), (this.y + (this.height >> 1)) - 15, 270, null);
                        GameLevel.spawnEffect(EFFECT_SMOKE, this.x + (this.width >> 1), (this.y + (this.height >> 1)) - 15, 0, -1024, 0);
                        break;
                }
                this.gunRecoil = GUN_RECOIL;
                this.fireCooldown = this.fireRate;
            }
        }
        if (this.mineCooldown > 0) {
            this.mineCooldown--;
        }
        if (this.dynamiteCooldown > 0) {
            this.dynamiteCooldown--;
        }
        if (this.hasAutoTurret) {
            if (this.autoFireCooldown > 0) {
                this.autoFireCooldown--;
                return;
            }
            this.autoFireCooldown = AUTO_FIRE_SCAN_MS;
            for (int entityIndex = 0; entityIndex < GameLevel.entityCount; entityIndex++) {
                if (GameLevel.entities[entityIndex].layer == 2 && GameLevel.entities[entityIndex].faction == -1 && GameLevel.entities[entityIndex].x > this.x - AUTO_SCAN_RADIUS && GameLevel.entities[entityIndex].x < this.x + AUTO_SCAN_RADIUS && GameLevel.entities[entityIndex].y > this.y - AUTO_SCAN_RADIUS && GameLevel.entities[entityIndex].y < this.y + AUTO_SCAN_RADIUS) {
                    GameLevel.spawnBullet(BULLET_HOMING, this.x, this.y, ResourceManager.angleBetween(GameLevel.entities[entityIndex].x - this.x, GameLevel.entities[entityIndex].y - this.y), GameLevel.entities[entityIndex]);
                    this.autoFireCooldown = AUTO_FIRE_COOLDOWN;
                    System.out.println("shoot socket");
                    return;
                }
            }
        }
    }

    @Override
    public final boolean takeDamageFrom(Bullet bullet) {
        if (!super.takeDamageFrom(bullet)) {
            return false;
        }
        XMIDlet.m1a(100);
        return true;
    }

    @Override
    public final void takeDamage(int i) {
        super.takeDamage(i);
        XMIDlet.m1a(100);
    }

    @Override
    public final void onDeath() {
        if (this.destroyed) {
            return;
        }
        this.destroyed = true;
        GameLevel.spawnPlayerDeath(this.x + (this.width / 2), this.y + (this.height / 2));
        GameLevel.spawnEffect(EFFECT_BOOM, this.x + (this.width / 2), this.y + (this.height / 2), 0, 0, 0);
        GameLevel.triggerGameOver(false);
    }
    public final boolean handlePickup(PickupItem pickup) {
        switch (pickup.itemType) {
            case PICKUP_XP:
                GameLevel.addXP(100);
                GameLevel.spawnFloatingText("+100XP", this.x, this.y);
                return true;
            case PICKUP_CASH:
                GameLevel.setupMapData(100);
                GameLevel.spawnFloatingText("+100$", this.x, this.y);
                return true;
            case PICKUP_REPAIR:
                this.currentHp = (short) (this.currentHp + ((this.maxHp * REPAIR_PERCENT) / 100));
                if (this.currentHp > this.maxHp) {
                    this.currentHp = this.maxHp;
                }
                GameLevel.spawnFloatingText("+5%HP", this.x, this.y);
                return true;
            case PICKUP_SPEED:
                if (this.speedBoostTimer == 0) {
                    this.speed += SPEED_BOOST;
                }
                this.speedBoostTimer = BOOST_DURATION;
                GameLevel.spawnFloatingText("SPEED++", this.x, this.y);
                return true;
            case PICKUP_DYNAMITE:
                if (GameLevel.dynamiteCount >= MAX_MINES_DYNAMITE) {
                    return false;
                }
                GameLevel.spawnFloatingText("DYNAMIC", this.x, this.y);
                GameLevel.dynamiteCount++;
                return true;
            case PICKUP_MINE:
                if (GameLevel.mineCount >= MAX_MINES_DYNAMITE) {
                    return false;
                }
                GameLevel.spawnFloatingText("MINE", this.x, this.y);
                GameLevel.mineCount++;
                return true;
            case PICKUP_FLAG:
                MissionState.flag1 = true;
                if (MissionState.missionType != 10) {
                    return true;
                }
                GameLevel.setWaypoint(132, 132);
                return true;
            case PICKUP_COMPLETE:
                GameLevel.triggerGameOver(true);
                return true;
            default:
                return false;
        }
    }

    @Override
    public final void followCamera() {
        switch (this.turretDirection) {
            case 0:
                GameLevel.setCameraTarget(this.x + (this.width / 2) + CAMERA_LEAD, this.y + (this.width / 2));
                break;
            case 2:
                GameLevel.setCameraTarget(this.x + (this.width / 2), this.y + (this.width / 2) + CAMERA_LEAD);
                break;
            case 4:
                GameLevel.setCameraTarget((this.x + (this.width / 2)) - CAMERA_LEAD, this.y + (this.width / 2));
                break;
            case 6:
                GameLevel.setCameraTarget(this.x + (this.width / 2), (this.y + (this.width / 2)) - CAMERA_LEAD);
                break;
        }
    }
    private boolean autoFireTarget() {
        int tileY = (this.y + 12) / MapRenderer.tileSize;
        int tileX = (this.x + 12) / MapRenderer.tileSize;
        switch (this.turretDirection) {
            case 0:
                for (int scanDist = 1; scanDist < this.autoDetectRange; scanDist++) {
                    if (GameLevel.hasEnemyAt(tileY, tileX + scanDist)) {
                        return true;
                    }
                }
                return false;
            case 1:
            case 3:
            case 5:
            default:
                return false;
            case 2:
                for (int scanDist = 1; scanDist < this.autoDetectRange; scanDist++) {
                    if (GameLevel.hasEnemyAt(tileY + scanDist, tileX)) {
                        return true;
                    }
                }
                return false;
            case 4:
                for (int scanDist = 1; scanDist < this.autoDetectRange; scanDist++) {
                    if (GameLevel.hasEnemyAt(tileY, tileX - scanDist)) {
                        return true;
                    }
                }
                return false;
            case 6:
                for (int scanDist = 1; scanDist < this.autoDetectRange; scanDist++) {
                    if (GameLevel.hasEnemyAt(tileY - scanDist, tileX)) {
                        return true;
                    }
                }
                return false;
        }
    }
    public final void deployDynamite() {
        if (this.dynamiteCooldown != 0 || GameLevel.dynamiteCount <= 0) {
            return;
        }
        GameLevel.spawnBullet(BULLET_DYNAMITE, (((this.x + 12) / MapRenderer.tileSize) * MapRenderer.tileSize) + 12, (((this.y + 12) / MapRenderer.tileSize) * MapRenderer.tileSize) + 12, 0, null);
        this.dynamiteCooldown = COOLDOWN_MINE_DYNAMITE;
        GameLevel.dynamiteCount--;
    }
    public final void deployMine() {
        if (this.mineCooldown != 0 || GameLevel.mineCount <= 0) {
            return;
        }
        GameLevel.spawnLandmine(this.x + (this.width / 2), this.y + (this.height / 2));
        this.mineCooldown = COOLDOWN_MINE_DYNAMITE;
        GameLevel.mineCount--;
    }
}
