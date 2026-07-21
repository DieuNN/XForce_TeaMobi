package xforce.entity;

import xforce.data.MissionState;
import xforce.game.GameLevel;
import xforce.map.MapRenderer;
import xforce.resource.ResourceManager;

import javax.microedition.lcdui.Graphics;
public final class WarShip extends Sprite {

    private static final byte SPRITE_TYPE_SHIP = 102;

    private static final int SHIP_WIDTH  = 176;
    private static final int SHIP_HEIGHT = 33;
    private static final int TILE_HALF   = 16;
    private static final int SHIP_TILES  = 7;
    private static final int TURRET_COUNT = 5;

    private static final int MISSION_WARSHIP = 11;
    private static final int MISSION_BOSS    = 14;

    private static final int WATER_TILE      = 36;
    private static final int WAVE_SPEED_UP   = 256;
    private static final int WAVE_SPEED_DOWN = -256;
    private static final int MAX_SPEED       = 1024;
    private static final int ACCEL           = 128;

    private static final int SMOKE_INTERVAL  = 5;

    private static final byte EFFECT_SMOKE_TRAIN = 10;
    private static final byte EFFECT_DEBRIS      = 0;

    private Sprite[] turrets;
    private int[] turretOffsetsX;
    private boolean isExploding;
    private int bobIndex;
    private static byte[] bobOffsets = {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, 0, 0};
    private boolean activated;

    public WarShip(int x, int y) {
        super(x, y, SPRITE_TYPE_SHIP);
        this.turretOffsetsX = new int[]{14, 126, 36, 108, 70};
        this.width = SHIP_WIDTH;
        this.height = SHIP_HEIGHT;
        if (ResourceManager.shipImage == null) {
            ResourceManager.shipImage = ResourceManager.loadImage("/ship.png");
        }
        if (ResourceManager.shipDestroyedImage == null) {
            ResourceManager.shipDestroyedImage = ResourceManager.loadImage("/ship1.png");
        }
        this.velocityX = 0;
        this.activated = false;
        this.turrets = new EnemyTurret[TURRET_COUNT];
        this.turrets[0] = new EnemyTurret(0, 0, EnemyTurret.TURRET_LEADING);
        this.turrets[1] = new EnemyTurret(0, 0, EnemyTurret.TURRET_LEADING);
        this.turrets[2] = new EnemyTurret(0, 0, EnemyTurret.TURRET_LEADING);
        this.turrets[2].hidden = true;
        this.turrets[2].angle = 180;
        this.turrets[3] = new EnemyTurret(0, 0, EnemyTurret.TURRET_LEADING);
        this.turrets[3].hidden = true;
        this.turrets[4] = new EnemyTurret(0, 0, EnemyTurret.TURRET_RING);
        this.turrets[4].hidden = true;
        for (int i3 = 0; i3 < SHIP_TILES; i3++) {
            GameLevel.setTileOccupant((y + TILE_HALF) / MapRenderer.tileSize, ((x + TILE_HALF) / MapRenderer.tileSize) + i3, this);
        }
    }

    @Override
    public final void draw(Graphics graphics) {
        if (this.isExploding) {
            graphics.drawImage(ResourceManager.shipDestroyedImage, this.x, this.y, 0);
        } else {
            this.bobIndex++;
            if (this.bobIndex == bobOffsets.length) {
                this.bobIndex = 0;
            }
            graphics.drawImage(ResourceManager.shipImage, this.x, this.y + bobOffsets[this.bobIndex], 0);
        }
        for (int i = 0; i < TURRET_COUNT; i++) {
            if (this.turrets[i] != null) {
                this.turrets[i].draw(graphics);
            }
        }
    }

    @Override
    public final void update() {
        if (this.x > MapRenderer.mapWidth * MapRenderer.tileSize && GameLevel.gameOverTimer == 0) {
            GameLevel.triggerGameOver(false);
        }
        if (this.activated) {
            int tileCol = this.x / MapRenderer.tileSize;
            int tileRow = (this.y / MapRenderer.tileSize) + 2;
            this.velocityY = 0;
            if (tileCol < MapRenderer.mapWidth - 5) {
                if (MapRenderer.tileMap[tileRow][tileCol] == WATER_TILE && MapRenderer.tileMap[tileRow][tileCol + 1] == WATER_TILE && MapRenderer.tileMap[tileRow][tileCol + 2] == WATER_TILE && MapRenderer.tileMap[tileRow][tileCol + 3] == WATER_TILE && MapRenderer.tileMap[tileRow][tileCol + 4] == WATER_TILE) {
                    this.velocityY = WAVE_SPEED_UP;
                }
                if (tileCol < MapRenderer.mapWidth - 8) {
                    int lookaheadCol = tileCol + 5;
                    if (MapRenderer.tileMap[tileRow][lookaheadCol] != WATER_TILE || MapRenderer.tileMap[tileRow][lookaheadCol + 1] != WATER_TILE || MapRenderer.tileMap[tileRow][lookaheadCol + 2] != WATER_TILE) {
                        this.velocityY = WAVE_SPEED_DOWN;
                    }
                }
            }
            if (this.velocityX < MAX_SPEED) {
                this.velocityX += ACCEL;
            }
            applyVelocity();
            GameLevel.setTileOccupant((this.y + TILE_HALF) / MapRenderer.tileSize, ((this.x + TILE_HALF) / MapRenderer.tileSize) + 7, this);
            for (int rowOffset = -2; rowOffset < 3; rowOffset++) {
                GameLevel.clearTileOccupant(((this.y + TILE_HALF) / MapRenderer.tileSize) + rowOffset, ((this.x + TILE_HALF) / MapRenderer.tileSize) - 1, this);
            }
        }
        if (this.animTimer == 0) {
            GameLevel.spawnEffect(EFFECT_SMOKE_TRAIN, this.x, this.y + 14, -512, 0, 0);
        }
        if (this.animTimer == 2) {
            GameLevel.spawnEffect(EFFECT_SMOKE_TRAIN, this.x, this.y + 18, -1024, 0, 0);
        }
        this.animTimer++;
        if (this.animTimer >= SMOKE_INTERVAL) {
            this.animTimer = 0;
        }
        for (int i = 0; i < TURRET_COUNT; i++) {
            if (this.turrets[i] != null) {
                this.turrets[i].setPosition(this.x + this.turretOffsetsX[i], this.y + 5);
                if (!this.turrets[i].hidden) {
                    this.turrets[i].update();
                }
            }
        }
    }

    @Override
    public final boolean takeDamageFrom(Bullet bullet) {
        for (int i = 0; i < TURRET_COUNT; i++) {
            if (this.turrets[i] != null && !this.turrets[i].hidden && this.turrets[i].takeDamageFrom(bullet)) {
                if (!this.turrets[i].dead) {
                    return true;
                }
                if (!this.activated && MissionState.missionType == MISSION_WARSHIP) {
                    this.activated = true;
                }
                if (i == 0 && this.turrets[1] == null) {
                    this.turrets[2].hidden = false;
                    this.turrets[3].hidden = false;
                }
                if (i == 1 && this.turrets[0] == null) {
                    this.turrets[2].hidden = false;
                    this.turrets[3].hidden = false;
                }
                if (i == 2 && this.turrets[3] == null) {
                    this.turrets[4].hidden = false;
                }
                if (i == 3 && this.turrets[2] == null) {
                    this.turrets[4].hidden = false;
                }
                if (i == 4) {
                    onDeath();
                }
                this.turrets[i] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public final void onDeath() {
        this.velocityX = 0;
        this.isExploding = true;
        GameLevel.spawnEffect(EFFECT_DEBRIS, this.x + 20, this.y + 10, 0, 0, 5);
        GameLevel.spawnEffect(EFFECT_DEBRIS, this.x + 80, this.y + 20, 0, 0, 2);
        GameLevel.spawnEffect(EFFECT_DEBRIS, this.x + 120, this.y + TILE_HALF, 0, 0, 9);
        GameLevel.spawnEffect(EFFECT_DEBRIS, this.x + 40, this.y + 10, 0, 0, 12);
        GameLevel.spawnEffect(EFFECT_DEBRIS, this.x + 100, this.y + TILE_HALF, 0, 0, 15);
        if (MissionState.missionType == MISSION_BOSS) {
            BigBoss bigBoss = new BigBoss(1008, 864);
            GameLevel.missionObjective = bigBoss;
            bigBoss.faction = (byte) -1;
            GameLevel.addEntity(GameLevel.missionObjective);
        } else {
            GameLevel.triggerGameOver(true);
        }
        this.activated = false;
    }

    @Override
    public final boolean collidesWith(GameEntity other) {
        return false;
    }
}
