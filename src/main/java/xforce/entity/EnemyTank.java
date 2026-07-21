package xforce.entity;

import xforce.game.GameLevel;
import xforce.map.MapRenderer;
import xforce.resource.ResourceManager;
import xforce.resource.SpriteSheet;

import javax.microedition.lcdui.Graphics;

public final class EnemyTank extends Sprite {

    private static final byte FACTION_HOSTILE = -1;

    private static final byte TYPE_SIMPLE_1  = 1;
    private static final byte TYPE_SIMPLE_2  = 2;
    private static final byte TYPE_TURRET_1  = 3;
    private static final byte TYPE_TURRET_2  = 4;
    private static final byte TYPE_BURST_1   = 5;
    private static final byte TYPE_BURST_2   = 6;
    private static final byte TYPE_BURST_3   = 7;
    private static final byte TYPE_TURRET_3  = 8;
    private static final byte TYPE_TURRET_4  = 9;
    private static final byte TYPE_BURST_4   = 10;
    private static final byte TYPE_BOSS      = 111;

    private static final int DIR_EAST  = 0;
    private static final int DIR_SOUTH = 1;
    private static final int DIR_WEST  = 2;
    private static final int DIR_NORTH = 3;
    private static final int DIR_COUNT = 4;
    private static final int ANGLES_PER_DIR = 90;

    private static final int SPRITE_FRAME_ANGLE_OFFSET = 22;
    private static final int ANGLES_PER_FRAME          = 45;

    private static final int TILE_SIZE    = 24;
    private static final int TILE_HALF    = 12;
    private static final int TRIG_SCALE   = 10;

    private static final int RADAR_CONE_HALF    = 15;
    private static final int AIM_ANGLE_TOLERANCE = 15;

    private static final int DEFAULT_TURRET_SPEED = 5;

    private static final int BOSS_HP_ENRAGE     = 20;
    private static final int BOSS_HP_SPEED_UP   = 50;
    private static final int BOSS_SPEED_NORMAL  = 1024;
    private static final int BOSS_SPEED_FAST    = 2048;
    private static final int BOSS_SPEED_FASTEST = 3072;

    private static final int FIRE_COOLDOWN = 50;
    private static final int FIRE_COOLDOWN_BOSS = 60;

    private static final int PATHFIND_RANGE_LONG  = 10;
    private static final int PATHFIND_RANGE_SHORT = 4;
    private static final int STUCK_BASE           = 15;

    private static final int ROAD_TILE_MIN = 12;
    private static final int ROAD_TILE_MAX = 24;

    private static final byte EFFECT_DEBRIS     = 0;
    private static final byte EFFECT_EXPLOSION  = 6;
    private static final byte EFFECT_SMOKE      = 2;
    private static final byte EFFECT_BIG_BOOM   = 5;
    private static final byte EFFECT_TRACK_H    = 8;
    private static final byte EFFECT_TRACK_V    = 9;

    private static final byte BULLET_ENEMY_WEAK  = 20;
    private static final byte BULLET_ENEMY_STRONG = 22;

    private static final int COLOR_RED     = 16711680;
    private static final int COLOR_TARGET  = 52224;

    private static final int DEFAULT_LIVES = 3;
    private static final int RESPAWN_DELAY = 20;
    private static final int PICKUP_VARIETY = 6;
    private boolean[] passableDirs;
    private byte[] dirVectorsX;
    private byte[] dirVectorsY;
    private byte[] dirPriority;
    private int fireRange;
    private int turretSpeed;
    private int moveSpeed;
    private int detectionRange;
    private int stuckTimer;
    private boolean smartPathfinding;
    private boolean turretMode;
    private int spawnX;
    private int spawnY;
    private int lives;
    private int invincibilityTimer;

    public EnemyTank(int i, int i2, byte b) {
        super(i, i2, b);
        this.passableDirs = new boolean[4];
        this.dirVectorsX = new byte[]{1, 0, -1, 0};
        this.dirVectorsY = new byte[]{0, 1, 0, -1};
        this.dirPriority = new byte[]{0, 1, 2, 3};
        this.lives = DEFAULT_LIVES;
        setBounds(i, i2, TILE_SIZE, TILE_SIZE);
        this.spawnX = i;
        this.spawnY = i2;
        this.faction = (byte) FACTION_HOSTILE;
        switch (b) {
            case 1:
                this.moveSpeed = BOSS_SPEED_FAST;
                this.maxHp = (short) 2;
                this.currentHp = (short) 2;
                this.xpReward = (short) 50;
                if (ResourceManager.tankSprites[0] == null) {
                    ResourceManager.tankSprites[0] = new SpriteSheet(ResourceManager.loadImage("/tank1.png"), TILE_SIZE, TILE_SIZE);
                }
                break;
            case 2:
                this.moveSpeed = BOSS_SPEED_FASTEST;
                this.maxHp = (short) 5;
                this.currentHp = (short) 5;
                this.xpReward = (short) 100;
                if (ResourceManager.tankSprites[1] == null) {
                    ResourceManager.tankSprites[1] = new SpriteSheet(ResourceManager.loadImage("/tank2.png"), TILE_SIZE, TILE_SIZE);
                }
                break;
            case 3:
                this.moveSpeed = BOSS_SPEED_NORMAL;
                this.detectionRange = 100;
                this.maxHp = (short) 7;
                this.currentHp = (short) 7;
                this.xpReward = (short) 150;
                if (ResourceManager.tankSprites[2] == null) {
                    ResourceManager.tankSprites[2] = new SpriteSheet(ResourceManager.loadImage("/tank3.png"), TILE_SIZE, TILE_SIZE);
                }
                break;
            case 4:
                this.moveSpeed = BOSS_SPEED_FAST;
                this.detectionRange = 120;
                this.maxHp = (short) 10;
                this.currentHp = (short) 10;
                this.xpReward = (short) 200;
                if (ResourceManager.tankSprites[3] == null) {
                    ResourceManager.tankSprites[3] = new SpriteSheet(ResourceManager.loadImage("/tank4.png"), TILE_SIZE, TILE_SIZE);
                }
                break;
            case 5:
                this.moveSpeed = BOSS_SPEED_FAST;
                this.maxHp = (short) 12;
                this.currentHp = (short) 12;
                this.xpReward = (short) 50;
                if (ResourceManager.tankSprites[4] == null) {
                    ResourceManager.tankSprites[4] = new SpriteSheet(ResourceManager.loadImage("/tank5.png"), TILE_SIZE, TILE_SIZE);
                }
                break;
            case 6:
                this.moveSpeed = BOSS_SPEED_FASTEST;
                this.maxHp = (short) 15;
                this.currentHp = (short) 15;
                this.xpReward = (short) 100;
                if (ResourceManager.tankSprites[5] == null) {
                    ResourceManager.tankSprites[5] = new SpriteSheet(ResourceManager.loadImage("/tank6.png"), TILE_SIZE, TILE_SIZE);
                }
                break;
            case 7:
                this.moveSpeed = BOSS_SPEED_NORMAL;
                this.maxHp = (short) 17;
                this.currentHp = (short) 17;
                this.xpReward = (short) 150;
                this.smartPathfinding = true;
                if (ResourceManager.tankSprites[6] == null) {
                    ResourceManager.tankSprites[6] = new SpriteSheet(ResourceManager.loadImage("/tank7.png"), TILE_SIZE, TILE_SIZE);
                }
                break;
            case 8:
                this.moveSpeed = BOSS_SPEED_FAST;
                this.detectionRange = 120;
                this.maxHp = (short) 20;
                this.currentHp = (short) 20;
                this.xpReward = (short) 200;
                this.smartPathfinding = true;
                if (ResourceManager.tankSprites[7] == null) {
                    ResourceManager.tankSprites[7] = new SpriteSheet(ResourceManager.loadImage("/tank8.png"), TILE_SIZE, TILE_SIZE);
                }
                break;
            case 9:
                this.moveSpeed = BOSS_SPEED_NORMAL;
                this.detectionRange = 100;
                this.maxHp = (short) 27;
                this.currentHp = (short) 27;
                this.xpReward = (short) 150;
                this.smartPathfinding = true;
                if (ResourceManager.tankSprites[8] == null) {
                    ResourceManager.tankSprites[8] = new SpriteSheet(ResourceManager.loadImage("/tank9.png"), TILE_SIZE, TILE_SIZE);
                }
                break;
            case 10:
                this.moveSpeed = BOSS_SPEED_FAST;
                this.maxHp = (short) 30;
                this.currentHp = (short) 30;
                this.xpReward = (short) 200;
                this.smartPathfinding = true;
                if (ResourceManager.tankSprites[9] == null) {
                    ResourceManager.tankSprites[9] = new SpriteSheet(ResourceManager.loadImage("/tank10.png"), TILE_SIZE, TILE_SIZE);
                }
                break;
            case 111:
                this.moveSpeed = BOSS_SPEED_NORMAL;
                this.maxHp = (short) 80;
                this.currentHp = (short) 80;
                this.xpReward = (short) 2000;
                this.turretMode = true;
                this.smartPathfinding = true;
                if (ResourceManager.tankBossSprite == null) {
                    ResourceManager.tankBossSprite = new SpriteSheet(ResourceManager.loadImage("/tankboss.png"), TILE_SIZE, TILE_SIZE);
                    ResourceManager.tankTurretSprite = new SpriteSheet(ResourceManager.loadImage("/tg3.png"), 32, 32);
                }
                break;
        }
        this.direction = ResourceManager.randomPositive(4);
        this.velocityY = 0;
        this.velocityX = 0;
        this.turretSpeed = DEFAULT_TURRET_SPEED;
        this.fireRange = DEFAULT_TURRET_SPEED;
        this.target = GameLevel.player;
        System.out.println("create tank " + (int) b);
    }

    @Override // p000.Sprite
    public final void draw(Graphics graphics) {
        if (this.invincibilityTimer > 0) {
            return;
        }
        if (!this.damageFlash) {
            if (this.type == 4 || this.type == 3 || this.type == 8 || this.type == 9) {
                graphics.setColor(this.aimingAtPlayer ? COLOR_RED : COLOR_TARGET);
                int iM81b = this.x + TILE_HALF + ((ResourceManager.cos(ResourceManager.normalizeAngle(this.angle - RADAR_CONE_HALF)) * this.detectionRange) >> TRIG_SCALE);
                int iM80a = this.y + TILE_HALF + ((ResourceManager.sin(ResourceManager.normalizeAngle(this.angle - RADAR_CONE_HALF)) * this.detectionRange) >> TRIG_SCALE);
                int iM81b2 = this.x + TILE_HALF + ((ResourceManager.cos(ResourceManager.normalizeAngle(this.angle + RADAR_CONE_HALF)) * this.detectionRange) >> TRIG_SCALE);
                int iM80a2 = this.y + TILE_HALF + ((ResourceManager.sin(ResourceManager.normalizeAngle(this.angle + RADAR_CONE_HALF)) * this.detectionRange) >> TRIG_SCALE);
                graphics.drawLine(this.x + (this.width >> 1), this.y + (this.height >> 1), iM81b, iM80a);
                graphics.drawLine(this.x + (this.width >> 1), this.y + (this.height >> 1), iM81b2, iM80a2);
                graphics.drawLine(iM81b, iM80a, iM81b2, iM80a2);
            }
            switch (this.type) {
                case 1:
                    ResourceManager.tankSprites[0].drawFrame(this.direction, this.x, this.y, 0, graphics);
                    break;
                case 2:
                    ResourceManager.tankSprites[1].drawFrame(this.direction, this.x, this.y, 0, graphics);
                    break;
                case 3:
                    ResourceManager.tankSprites[2].drawFrame(this.direction, this.x, this.y, 0, graphics);
                    break;
                case 4:
                    ResourceManager.tankSprites[3].drawFrame(this.direction, this.x, this.y, 0, graphics);
                    break;
                case 5:
                    ResourceManager.tankSprites[4].drawFrame(this.direction, this.x, this.y, 0, graphics);
                    break;
                case 6:
                    ResourceManager.tankSprites[5].drawFrame(this.direction, this.x, this.y, 0, graphics);
                    break;
                case 7:
                    ResourceManager.tankSprites[6].drawFrame(this.direction, this.x, this.y, 0, graphics);
                    break;
                case 8:
                    ResourceManager.tankSprites[7].drawFrame(this.direction, this.x, this.y, 0, graphics);
                    break;
                case 9:
                    ResourceManager.tankSprites[8].drawFrame(this.direction, this.x, this.y, 0, graphics);
                    break;
                case 10:
                    ResourceManager.tankSprites[9].drawFrame(this.direction, this.x, this.y, 0, graphics);
                    break;
                case 111:
                    if (this.currentHp <= 0) {
                        ResourceManager.tankBossSprite.drawFrame(4, this.x, this.y, 0, graphics);
                    } else {
                        ResourceManager.tankBossSprite.drawFrame(this.direction, this.x, this.y, 0, graphics);
                    }
                    if (this.turretMode) {
                        ResourceManager.tankTurretSprite.drawFrame(((this.angle + SPRITE_FRAME_ANGLE_OFFSET) % 360) / ANGLES_PER_FRAME, this.x - 4, this.y - 4, 0, graphics);
                    }
                    break;
            }
        } else {
            this.damageFlash = false;
        }
        drawHpBar(graphics);
    }

    @Override // p000.GameEntity
    public final void update() {
        if (this.invincibilityTimer > 0) {
            if (this.invincibilityTimer > 0) {
                this.invincibilityTimer--;
            }
            if (this.invincibilityTimer == 0) {
                if (GameLevel.isInViewport((GameEntity) this)) {
                    this.invincibilityTimer = 1;
                    return;
                } else {
                    this.hidden = false;
                    return;
                }
            }
            return;
        }
        if (this.currentHp <= 0) {
            return;
        }
        switch (this.type) {
            case 3:
            case 4:
            case 8:
            case 9:
                this.animTimer++;
                if (this.animTimer >= 2) {
                    this.animTimer = 0;
                }
                if (this.attackCooldown > 0) {
                    this.attackCooldown = (byte) (this.attackCooldown - 1);
                }
                if (!this.aimingAtPlayer) {
                    switch (this.direction) {
                        case DIR_EAST:
                            if (this.angle < 315 && this.angle > 180) {
                                this.fireRange = this.turretSpeed;
                            }
                            if (this.angle > 45 && this.angle < 180) {
                                this.fireRange = -this.turretSpeed;
                            }
                            break;
                        case DIR_SOUTH:
                            if (this.angle < 45) {
                                this.fireRange = this.turretSpeed;
                            }
                            if (this.angle > 135) {
                                this.fireRange = -this.turretSpeed;
                            }
                            break;
                        case DIR_WEST:
                            if (this.angle < 135) {
                                this.fireRange = this.turretSpeed;
                            }
                            if (this.angle > 225) {
                                this.fireRange = -this.turretSpeed;
                            }
                            break;
                        case DIR_NORTH:
                            if (this.angle < 225) {
                                this.fireRange = this.turretSpeed;
                            }
                            if (this.angle > 315) {
                                this.fireRange = -this.turretSpeed;
                            }
                            break;
                    }
                    this.angle = ResourceManager.normalizeAngle(this.angle + this.fireRange);
                }
                if (this.target != null && !this.target.dead && this.animTimer == 0) {
                    int i6 = (this.target.x + (this.target.width >> 1)) - (this.x + (this.width >> 1));
                    int i7 = (this.target.y + (this.target.height >> 1)) - (this.y + (this.height >> 1));
                    this.aimingAtPlayer = false;
                    if ((i6 * i6) + (i7 * i7) < this.detectionRange * this.detectionRange) {
                        int iM82a = ResourceManager.angleBetween(i6, i7);
                        if (Math.abs(ResourceManager.angleDelta(iM82a, this.angle)) <= AIM_ANGLE_TOLERANCE) {
                            this.angle = iM82a;
                            this.aimingAtPlayer = true;
                            if (this.attackCooldown == 0) {
                                this.attackCooldown = (byte) FIRE_COOLDOWN;
                            }
                            if (this.attackCooldown > 40) {
                                GameLevel.spawnBullet(BULLET_ENEMY_WEAK, this.x + (this.width >> 1), this.y + (this.height >> 1), iM82a, null);
                            }
                        }
                    }
                }
                break;
            case 5:
            case 6:
            case 7:
                if (this.attackCooldown > 0) {
                    this.attackCooldown = (byte) (this.attackCooldown - 1);
                } else {
                    this.attackCooldown = (byte) FIRE_COOLDOWN;
                }
                if (this.attackCooldown == FIRE_COOLDOWN || this.attackCooldown == 40) {
                    GameLevel.spawnBullet(BULLET_ENEMY_STRONG, this.x + TILE_HALF, this.y + TILE_HALF, this.direction * ANGLES_PER_DIR, null);
                }
                break;
            case 10:
                if (this.attackCooldown > 0) {
                    this.attackCooldown = (byte) (this.attackCooldown - 1);
                } else {
                    this.attackCooldown = (byte) FIRE_COOLDOWN;
                }
                if (this.attackCooldown == FIRE_COOLDOWN || this.attackCooldown == 45 || this.attackCooldown == 40) {
                    GameLevel.spawnBullet(BULLET_ENEMY_STRONG, this.x + TILE_HALF, this.y + TILE_HALF, this.direction * ANGLES_PER_DIR, null);
                }
                break;
            case 111:
                if (this.turretMode) {
                    if (this.currentHp < BOSS_HP_ENRAGE) {
                        this.turretMode = false;
                        GameLevel.spawnEffect(EFFECT_DEBRIS, this.x + TILE_HALF, this.y + TILE_HALF, 0, 0, 0);
                    } else {
                        this.angle = ResourceManager.angleBetween(this.target.x - this.x, this.target.y - this.y);
                        if (this.attackCooldown > 0) {
                            this.attackCooldown = (byte) (this.attackCooldown - 1);
                        } else {
                            this.attackCooldown = (byte) FIRE_COOLDOWN_BOSS;
                        }
                        if (this.attackCooldown > 40 && this.attackCooldown % 4 == 0) {
                            GameLevel.spawnBullet(BULLET_ENEMY_STRONG, this.x + TILE_HALF + ((ResourceManager.cos(this.angle) << 4) >> TRIG_SCALE), this.y + TILE_HALF + ((ResourceManager.sin(this.angle) << 4) >> TRIG_SCALE), this.angle, null);
                        }
                    }
                } else if (ResourceManager.randomInt(3) == 0) {
                    GameLevel.spawnEffect(EFFECT_SMOKE, this.x + TILE_HALF, this.y + TILE_HALF, ResourceManager.randomInt(BOSS_SPEED_NORMAL), ResourceManager.randomInt(BOSS_SPEED_NORMAL), 0);
                }
                break;
            default:
                if (this.attackCooldown > 0) {
                    this.attackCooldown = (byte) (this.attackCooldown - 1);
                } else {
                    this.attackCooldown = (byte) FIRE_COOLDOWN;
                    GameLevel.spawnBullet(BULLET_ENEMY_WEAK, this.x + TILE_HALF, this.y + TILE_HALF, this.direction * ANGLES_PER_DIR, null);
                }
                break;
        }
        if (this.aimingAtPlayer) {
            return;
        }
        if (this.x % MapRenderer.tileSize == 0 && this.y % MapRenderer.tileSize == 0) {
            if (this.type == 111) {
                if (this.direction == DIR_EAST || this.direction == DIR_WEST) {
                    GameLevel.spawnEffect(EFFECT_TRACK_V, this.x, this.y, 0, 0, 0);
                } else {
                    GameLevel.spawnEffect(EFFECT_TRACK_H, this.x, this.y, 0, 0, 0);
                }
                if (this.currentHp < BOSS_HP_SPEED_UP && this.smartPathfinding) {
                    this.moveSpeed = BOSS_SPEED_FAST;
                    this.smartPathfinding = false;
                }
                if (this.currentHp < BOSS_HP_ENRAGE) {
                    this.moveSpeed = BOSS_SPEED_FASTEST;
                }
            }
            boolean foundDirection = false;
            int row = this.y / MapRenderer.tileSize;
            int col = this.x / MapRenderer.tileSize;
            boolean skipDecisions = false;
            if (this.stuckTimer > 1) {
                this.stuckTimer--;
                skipDecisions = !this.smartPathfinding;
            }
            if (!skipDecisions) {
                GameLevel.clearTileOccupant(row - this.dirVectorsY[this.direction], col - this.dirVectorsX[this.direction], this);
                this.passableDirs[DIR_EAST] = !MapRenderer.isBlocked(row, col + 1);
                this.passableDirs[DIR_SOUTH] = !MapRenderer.isBlocked(row + 1, col);
                this.passableDirs[DIR_WEST] = !MapRenderer.isBlocked(row, col - 1);
                this.passableDirs[DIR_NORTH] = !MapRenderer.isBlocked(row - 1, col);
                if (this.smartPathfinding) {
                    int targetDx = ((this.target.x + TILE_HALF) / 24) - ((this.x + TILE_HALF) / 24);
                    int targetDy = ((this.target.y + TILE_HALF) / 24) - ((this.y + TILE_HALF) / 24);
                    if (Math.abs(targetDx) < PATHFIND_RANGE_LONG && Math.abs(targetDy) < PATHFIND_RANGE_LONG) {
                        if ((targetDx == 0 || targetDy == 0) && Math.abs(targetDx) < PATHFIND_RANGE_SHORT && Math.abs(targetDy) < PATHFIND_RANGE_SHORT) {
                            if (targetDx > 0) {
                                this.direction = DIR_EAST;
                            }
                            if (targetDx < 0) {
                                this.direction = DIR_WEST;
                            }
                            if (targetDy > 0) {
                                this.direction = DIR_SOUTH;
                            }
                            if (targetDy < 0) {
                                this.direction = DIR_NORTH;
                            }
                            this.velocityX = 0;
                            this.velocityY = 0;
                            foundDirection = true;
                        } else {
                            foundDirection = steerTowardPlayer(targetDx, targetDy);
                        }
                    }
                }
                if (!foundDirection) {
                    if (this.stuckTimer == 1) {
                        pickRandomDirection(row, col);
                    } else if (!this.passableDirs[this.direction] || ResourceManager.randomInt(10) == 0) {
                        this.stuckTimer = STUCK_BASE + ResourceManager.randomInt(10);
                        this.velocityX = 0;
                        this.velocityY = 0;
                    }
                }
                if (this.velocityX != 0 || this.velocityY != 0) {
                    GameLevel.setTileOccupant(row + this.dirVectorsY[this.direction], col + this.dirVectorsX[this.direction], this);
                }
            }
        }
        applyVelocity();
    }

    private boolean steerTowardPlayer(int targetDx, int targetDy) {
        for (int i = 0; i < DIR_COUNT; i++) {
            int dir = this.dirPriority[i];
            if (!this.passableDirs[dir]) {
                continue;
            }
            boolean towardPlayer;
            switch (dir) {
                case DIR_EAST:
                    towardPlayer = targetDx > 0;
                    break;
                case DIR_SOUTH:
                    towardPlayer = targetDy > 0;
                    break;
                case DIR_WEST:
                    towardPlayer = targetDx < 0;
                    break;
                default:
                    towardPlayer = targetDy < 0;
                    break;
            }
            if (towardPlayer) {
                setDirectionAndVelocity(dir);
                return true;
            }
        }
        return false;
    }

    private void pickRandomDirection(int row, int col) {
        for (int i = 0; i < DIR_COUNT; i++) {
            byte swapWith = this.dirPriority[Math.abs(ResourceManager.randomInt(DIR_COUNT))];
            byte tmp = this.dirPriority[i];
            this.dirPriority[i] = this.dirPriority[swapWith];
            this.dirPriority[swapWith] = tmp;
        }
        this.velocityX = 0;
        this.velocityY = 0;
        for (int i = 0; i < DIR_COUNT; i++) {
            int dir = this.dirPriority[i];
            if (this.passableDirs[dir] && isRoadTile(row + this.dirVectorsY[dir], col + this.dirVectorsX[dir])) {
                setDirectionAndVelocity(dir);
                this.stuckTimer = 0;
                return;
            }
        }
        for (int i = 0; i < DIR_COUNT; i++) {
            int dir = this.dirPriority[i];
            if (this.passableDirs[dir]) {
                setDirectionAndVelocity(dir);
                this.stuckTimer = 0;
                return;
            }
        }
        this.stuckTimer = 0;
    }

    private void setDirectionAndVelocity(int dir) {
        this.direction = dir;
        this.velocityX = this.dirVectorsX[dir] * this.moveSpeed;
        this.velocityY = this.dirVectorsY[dir] * this.moveSpeed;
        this.angle = dir * ANGLES_PER_DIR;
    }

    private static boolean isRoadTile(int row, int col) {
        return MapRenderer.tileMap[row][col] >= ROAD_TILE_MIN && MapRenderer.tileMap[row][col] < ROAD_TILE_MAX;
    }

    @Override // p000.Sprite
    public final void onDeath() {
        int i = this.x / MapRenderer.tileSize;
        int i2 = this.y / MapRenderer.tileSize;
        if (this.x % MapRenderer.tileSize != 0) {
            GameLevel.clearTileOccupant(i2, i + 1, this);
        } else if (this.y % MapRenderer.tileSize != 0) {
            GameLevel.clearTileOccupant(i2 + 1, i, this);
        } else {
            System.out.println("fix check mapObj error");
            GameLevel.clearTileOccupant(i2 - this.dirVectorsY[this.direction], i - this.dirVectorsX[this.direction], this);
        }
        GameLevel.clearTileOccupant(i2, i, this);
        GameLevel.spawnEffect(EFFECT_DEBRIS, this.x + (this.width / 2), this.y + (this.height / 2), 0, 0, 0);
        GameLevel.spawnEffect(EFFECT_EXPLOSION, this.x + (this.width / 2), this.y + (this.height / 2), 0, 0, 0);
        if (this.type == 110) {
            GameLevel.triggerGameOver(false);
        } else {
            if (this.type == 111) {
                GameLevel.triggerGameOver(true);
                GameLevel.spawnFloatingText("+" + (int) this.xpReward + "XP", this.x + TILE_HALF, this.y + TILE_HALF);
                GameLevel.addXP(this.xpReward);
                GameLevel.spawnEffect(EFFECT_BIG_BOOM, this.x + (this.width / 2), this.y + (this.height / 2), 0, 0, 0);
                return;
            }
            GameLevel.spawnPickup(this.x + TILE_HALF, this.y + TILE_HALF, (byte) ResourceManager.randomPositive(PICKUP_VARIETY));
            GameLevel.spawnFloatingText("+" + (int) this.xpReward + "XP", this.x + TILE_HALF, this.y + TILE_HALF);
            GameLevel.addXP(this.xpReward);
            GameLevel.enemyCount--;
            GameLevel.enemiesKilled++;
            this.lives--;
            if (this.lives > 0) {
                this.x = this.spawnX;
                this.y = this.spawnY;
                this.currentHp = this.maxHp;
                System.out.println("tank re life");
                this.hidden = true;
                this.invincibilityTimer = RESPAWN_DELAY;
                return;
            }
        }
        this.dead = true;
    }
}
