package xforce.entity;

import xforce.data.MissionScript;
import xforce.data.MissionState;
import xforce.game.GameLevel;
import xforce.resource.ResourceManager;

import javax.microedition.lcdui.Graphics;

public final class BigBoss extends Sprite {

    private static final byte SPRITE_TYPE_BOSS          = 103;
    private static final byte SPRITE_TYPE_TURRET_TOP    = 104;
    private static final byte SPRITE_TYPE_TURRET_SIDE   = 105;
    private static final byte SPRITE_TYPE_TURRET_CENTER = 106;
    private static final byte SPRITE_TYPE_TURRET_DECO   = 107;

    private static final int TURRET_TOTAL  = 9;
    private static final int TURRET_ACTIVE = 5;
    private static final int TURRET_EMERGE_FRAMES = 8;
    private static final int TURRET_RESPAWN_DELAY = 200;

    private static final int BOSS_MAP_CENTER_X   = 120;
    private static final int BOSS_HOMING_SPEED   = 32;
    private static final int BOSS_FLY_SPEED_X    = 1024;
    private static final int BOSS_FLY_SPEED_Y    = -2048;
    private static final int BOSS_FLY_DECEL      = 96;
    private static final int BOSS_FLY_MAX_UP     = -8000;
    private static final int BOSS_Z_FLYING       = 30;
    private static final int BOSS_Z_MAX_RISE     = 40;
    private static final int BOSS_Z_CRASHING     = 2;
    private static final int BOSS_Z_GROUNDED     = 1;
    private static final int BOSS_Z_DESTROYED    = 0;
    private static final int BOSS_GRAVITY        = 256;

    private static final int MISSION_TYPE_BOSS_RUN = 14;

    private static final int DRAW_OFFSET = 12;

    private final int[] turretOffsetX;
    private final int[] turretOffsetY;
    private final int[] turretCooldown;
    private final Sprite[] turrets;
    private final int activeTurrets;
    private final int totalTurrets;
    private boolean isDestroyed;
    private final int[] turretEmergence;
    private static int bossVelocityX;
    private static int bossVelocityY;

    public BigBoss(int spawnX, int spawnY) {
        super(spawnX, spawnY, SPRITE_TYPE_BOSS);
        this.turretOffsetX = new int[]{39, 63, 26, 75, 51, 35, 18, 66, 83};
        this.turretOffsetY = new int[]{65, 65, 28, 28, 36, 14, 18, 14, 18};
        this.turretCooldown = new int[]{TURRET_RESPAWN_DELAY, TURRET_RESPAWN_DELAY, TURRET_RESPAWN_DELAY, TURRET_RESPAWN_DELAY, TURRET_RESPAWN_DELAY, TURRET_RESPAWN_DELAY, TURRET_RESPAWN_DELAY, TURRET_RESPAWN_DELAY, TURRET_RESPAWN_DELAY};
        this.turrets = new Sprite[TURRET_TOTAL];
        this.activeTurrets = TURRET_ACTIVE;
        this.totalTurrets = TURRET_TOTAL;
        this.turretEmergence = new int[TURRET_ACTIVE];
        setSize(118, 88);
        ResourceManager.bossImage = ResourceManager.loadImage("/bigboss1.png");
        ResourceManager.bossComposite = new CompositeSprite(ResourceManager.loadImage("/bigboss.png"), new int[][]{
            new int[]{0, 0, 52, 22, 0, -20}, new int[]{0, 22, 52, 22, 0, -20},
            new int[]{0, 44, 52, 22, -66, -20}, new int[]{0, 66, 52, 22, -66, -20},
            new int[]{52, 0, 14, 88, -52, 0}, new int[]{66, 0, 14, 88, -52, 0},
            new int[]{80, 0, 8, 11, -40, -9}, new int[]{88, 0, 8, 11, -40, -9},
            new int[]{80, 11, 8, 7, -22, -13}, new int[]{88, 11, 8, 7, -22, -13},
            new int[]{80, 18, 8, 11, -70, -9}, new int[]{88, 18, 8, 11, -70, -9},
            new int[]{80, 29, 8, 7, -88, -14}, new int[]{88, 29, 8, 7, -88, -14},
            new int[]{80, 36, 17, 12, -35, -67}, new int[]{80, 48, 17, 12, -35, -67},
            new int[]{80, 60, 17, 12, -66, -67}, new int[]{80, 72, 17, 12, -66, -67}
        });
        for (int t = 0; t < this.totalTurrets; t++) {
            if (t < 2) {
                this.turrets[t] = new EnemyTurret(0, 0, SPRITE_TYPE_TURRET_TOP);
            } else if (t < 4) {
                this.turrets[t] = new EnemyTurret(0, 0, SPRITE_TYPE_TURRET_SIDE);
            } else if (t < 5) {
                this.turrets[t] = new EnemyTurret(0, 0, SPRITE_TYPE_TURRET_CENTER);
            } else {
                this.turrets[t] = new Sprite(0, 0, SPRITE_TYPE_TURRET_DECO);
            }
            if (t > 1) {
                this.turrets[t].hidden = true;
            }
        }
        if (MissionState.missionType == MISSION_TYPE_BOSS_RUN) {
            this.z = BOSS_Z_CRASHING;
            this.turrets[0].hidden = true;
            this.turrets[1].hidden = true;
        } else {
            this.z = BOSS_Z_FLYING;
            this.velocityX = BOSS_FLY_SPEED_X;
            this.velocityY = BOSS_FLY_SPEED_Y;
        }
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(ResourceManager.bossImage, this.x + this.z + DRAW_OFFSET, this.y + this.z + DRAW_OFFSET, 0);
        if (this.z <= BOSS_Z_GROUNDED) {
            return;
        }
        ResourceManager.bossComposite.drawRegion(this.turrets[2] != null ? 0 : 1, this.x, this.y, 0, graphics);
        ResourceManager.bossComposite.drawRegion(this.turrets[3] != null ? 2 : 3, this.x, this.y, 0, graphics);
        ResourceManager.bossComposite.drawRegion(this.turrets[4] != null ? 4 : 5, this.x, this.y, 0, graphics);
        ResourceManager.bossComposite.drawRegion(this.turrets[5] != null ? 6 : 7, this.x, this.y, 0, graphics);
        ResourceManager.bossComposite.drawRegion(this.turrets[6] != null ? 8 : 9, this.x, this.y, 0, graphics);
        ResourceManager.bossComposite.drawRegion(this.turrets[7] != null ? 10 : 11, this.x, this.y, 0, graphics);
        ResourceManager.bossComposite.drawRegion(this.turrets[8] != null ? 12 : 13, this.x, this.y, 0, graphics);
        ResourceManager.bossComposite.drawRegion(this.turrets[0] != null ? 14 : 15, this.x, this.y, 0, graphics);
        ResourceManager.bossComposite.drawRegion(this.turrets[1] != null ? 16 : 17, this.x, this.y, 0, graphics);
        for (int t = 0; t < this.activeTurrets; t++) {
            if (this.turrets[t] != null) {
                graphics.setColor(0);
                graphics.fillRect(this.turrets[t].x + 4, this.turrets[t].y + 4, this.turretEmergence[t], 8);
                if (this.turretEmergence[t] == TURRET_EMERGE_FRAMES) {
                    this.turrets[t].draw(graphics);
                }
            }
        }
        for (int t = this.activeTurrets; t < this.totalTurrets; t++) {
            if (this.turrets[t] != null) {
                this.turrets[t].draw(graphics);
            }
        }
    }

    @Override
    public void update() {
        applyVelocity();
        for (int t = 0; t < this.totalTurrets; t++) {
            if (this.turrets[t] != null) {
                this.turrets[t].setPosition(this.x + this.turretOffsetX[t], this.y + this.turretOffsetY[t]);
                if (!this.turrets[t].hidden && t < this.activeTurrets) {
                    if (this.turretEmergence[t] < TURRET_EMERGE_FRAMES) {
                        this.turretEmergence[t]++;
                    } else {
                        this.turrets[t].update();
                    }
                }
            } else if (this.turretCooldown[t] > 0) {
                if (this.turretCooldown[t] % 3 == 0) {
                    GameLevel.spawnEffect((byte) 2, this.x + this.turretOffsetX[t] + 8, this.y + this.turretOffsetY[t] + 8, ResourceManager.randomInt(512), 1024, 0);
                }
                this.turretCooldown[t]--;
            }
        }
        if (MissionState.missionType == MISSION_TYPE_BOSS_RUN) {
            if (this.velocityY != 0) {
                if (this.velocityY > BOSS_FLY_MAX_UP) {
                    this.velocityY -= BOSS_FLY_DECEL;
                    return;
                }
                if (this.z < BOSS_Z_MAX_RISE) {
                    this.z++;
                    return;
                } else {
                    if (this.y < (-this.height)) {
                        this.dead = true;
                        MissionScript.nextPhase(2);
                        return;
                    }
                    return;
                }
            }
            return;
        }
        if (!this.isDestroyed) {
            if (this.x + (this.width / 2) > BOSS_MAP_CENTER_X) {
                bossVelocityX = -BOSS_HOMING_SPEED;
            }
            if (this.x + (this.width / 2) < BOSS_MAP_CENTER_X) {
                bossVelocityX = BOSS_HOMING_SPEED;
            }
            if (this.y + (this.height / 2) > MissionState.scrollLimit - 60) {
                bossVelocityY = -BOSS_HOMING_SPEED;
            }
            if (this.y + (this.height / 2) < MissionState.scrollLimit - 60) {
                bossVelocityY = BOSS_HOMING_SPEED;
            }
            this.velocityX += bossVelocityX;
            this.velocityY += bossVelocityY;
            return;
        }
        if (this.z > BOSS_Z_GROUNDED) {
            this.z--;
            this.velocityY += BOSS_GRAVITY;
        }
        if (this.z == BOSS_Z_CRASHING) {
            int[] debrisX = {59, 14, 104, 43, 73, 43, 37, 56, 60, 28, 43, 65, 85, 46, 48, 46};
            int[] debrisY = {8, 25, 26, 30, 32, 16, 16, 45, 58, 33, 22, 32, 48, 22, 48, 64};
            int[] debrisDelay = {0, 5, 2, 3, 7, 10, 15, 1, 2, 20, 16, 12, 10, 8, 20, 17};
            this.velocityX = 0;
            this.velocityY = 0;
            for (int d = 0; d < debrisX.length; d++) {
                GameLevel.spawnEffect((byte) 0, this.x + debrisX[d], this.y + debrisY[d], 0, 0, debrisDelay[d]);
            }
            GameLevel.spawnEffect((byte) 5, this.x + 50, this.y + 43, 0, 0, 0);
            GameLevel.spawnEffect((byte) 5, this.x + 26, this.y + 37, 0, 0, 0);
            GameLevel.spawnEffect((byte) 5, this.x + 58, this.y + 71, 0, 0, 0);
            ResourceManager.bossImage = null;
            ResourceManager.bossImage = ResourceManager.loadImage("/bigboss2.png");
            this.z = BOSS_Z_DESTROYED;
            MissionScript.nextPhase(3);
        }
    }

    @Override
    public final boolean takeDamageFrom(Bullet bullet) {
        if (!collidesWith((GameEntity) bullet)) {
            return false;
        }
        if (MissionState.missionType == MISSION_TYPE_BOSS_RUN && this.velocityY == 0) {
            GameLevel.cameraTarget = this;
            this.velocityY = -1;
        }
        for (int t = 0; t < this.totalTurrets; t++) {
            if (this.turrets[t] != null && !this.turrets[t].hidden && this.turrets[t].takeDamageFrom(bullet)) {
                if (!this.turrets[t].dead) {
                    return true;
                }
                this.turrets[t] = null;
                if (this.turrets[0] == null && this.turrets[1] == null && t < 2) {
                    this.turrets[2].hidden = false;
                    this.turrets[3].hidden = false;
                }
                if (this.turrets[2] == null && this.turrets[3] == null && t < 4) {
                    this.turrets[4].hidden = false;
                }
                if (t == 4) {
                    this.turrets[5].hidden = false;
                    this.turrets[6].hidden = false;
                    this.turrets[7].hidden = false;
                    this.turrets[8].hidden = false;
                }
                if (this.turrets[5] != null || this.turrets[6] != null || this.turrets[7] != null || this.turrets[8] != null) {
                    return true;
                }
                this.isDestroyed = true;
                return true;
            }
        }
        return false;
    }
}
