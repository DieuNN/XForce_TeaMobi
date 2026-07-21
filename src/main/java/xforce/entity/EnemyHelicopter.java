package xforce.entity;

import xforce.game.GameLevel;
import xforce.map.MapRenderer;
import xforce.resource.ResourceManager;
import xforce.resource.SpriteSheet;

import javax.microedition.lcdui.Graphics;

public final class EnemyHelicopter extends Sprite {

    private static final byte SPRITE_TYPE_HELI   = 14;
    private static final int FACTION_HOSTILE     = -1;

    private static final int STATE_LANDED        = 0;
    private static final int STATE_ATTACKING     = 1;
    private static final int STATE_RETURNING     = 2;

    private static final int LAYER_GROUND        = 1;
    private static final int LAYER_AIR           = 2;

    private static final int SPRITE_FRAME_ANGLE_OFFSET = 22;
    private static final int ANGLES_PER_FRAME    = 45;

    private static final int HELI_SIZE           = 24;
    private static final int HELI_HALF           = 12;
    private static final int MAX_SPEED           = 3072;
    private static final int APPROACH_ACCEL      = 128;

    private static final int AGGRO_RANGE_SQ      = 16384;
    private static final int LAND_PROXIMITY_SQ   = 4096;
    private static final int TURN_FIELD_SQ       = 1024;

    private static final int TURN_TOLERANCE_WIDE = 90;
    private static final int TURN_TOLERANCE_TIGHT = 12;
    private static final int TURN_STEP           = 6;
    private static final int FIRE_ANGLE_TOLERANCE = 7;

    private static final int MAX_ALTITUDE        = 30;
    private static final int LAND_DIST_TILES     = 5;

    private static final int TAKE_OFF_WINDUP     = 70;
    private static final int PROPELLER_FAST_AT   = 60;
    private static final int PROPELLER_FRAMES    = 4;

    private static final int FIRE_COOLDOWN       = 50;
    private static final int VOLLEY_SIZE         = 3;

    private static final int TRIG_SCALE          = 10;

    private static final int DEATH_HP_THRESHOLD   = 2;
    private static final byte EFFECT_SMOKE        = 4;

    private int heliState;
    private int tileCol;
    private int tileRow;
    private int takeoffCount;
    private int targetX;
    private int targetY;
    private int attackTimer;

    public EnemyHelicopter(int i, int i2) {
        super(i, i2, SPRITE_TYPE_HELI);
        setBounds(i, i2, HELI_SIZE, HELI_SIZE);
        this.faction = (byte) FACTION_HOSTILE;
        this.tileCol = i;
        this.tileRow = i2;
        this.attackTimer = 0;
        this.speed = MAX_SPEED;
        this.z = 0;
        this.angle = ResourceManager.randomPositive(360);
        GameLevel.totalEnemies++;
        this.target = GameLevel.player;
        if (ResourceManager.helicopterSprite == null) {
            ResourceManager.helicopterSprite = new SpriteSheet(ResourceManager.loadImage("/heli.png"), HELI_SIZE, HELI_SIZE);
            ResourceManager.heliShadowSprite = new SpriteSheet(ResourceManager.loadImage("/sheli.png"), HELI_SIZE, HELI_SIZE);
            ResourceManager.propellerSprite = new SpriteSheet(ResourceManager.loadImage("/probeller.png"), HELI_SIZE, HELI_SIZE);
        }
    }

    @Override // p000.Sprite
    public final void draw(Graphics graphics) {
        ResourceManager.heliShadowSprite.drawFrame(((this.angle + SPRITE_FRAME_ANGLE_OFFSET) % 360) / ANGLES_PER_FRAME, this.x + this.z, this.y + this.z, 0, graphics);
        ResourceManager.helicopterSprite.drawFrame(((this.angle + SPRITE_FRAME_ANGLE_OFFSET) % 360) / ANGLES_PER_FRAME, this.drawX, this.drawY, 0, graphics);
        ResourceManager.propellerSprite.drawFrame(this.targetY + (this.attackTimer >= PROPELLER_FAST_AT ? 4 : 0), this.drawX, this.drawY, 0, graphics);
        if (this.attackTimer > 0) {
            if (this.attackTimer < 20) {
                this.takeoffCount++;
                if (this.takeoffCount >= 3) {
                    this.takeoffCount = 0;
                    this.targetY++;
                }
            } else if (this.attackTimer < 40) {
                this.takeoffCount++;
                if (this.takeoffCount >= 2) {
                    this.takeoffCount = 0;
                    this.targetY++;
                }
            } else {
                this.targetY++;
            }
            if (this.targetY >= PROPELLER_FRAMES) {
                this.targetY = 0;
            }
        }
        drawHpBar(graphics);
    }

    @Override // p000.GameEntity
    public final void update() {
        int i = this.target.x - this.x;
        int i2 = this.target.y - this.y;
        if (this.heliState == STATE_LANDED) {
            this.speed = 0;
            if ((i * i) + (i2 * i2) < AGGRO_RANGE_SQ) {
                this.heliState = STATE_ATTACKING;
            }
        } else if (this.heliState == STATE_ATTACKING) {
            if (this.attackTimer < TAKE_OFF_WINDUP) {
                this.attackTimer++;
            } else if (this.z < MAX_ALTITUDE) {
                if (this.z == 0) {
                    GameLevel.clearTileOccupant(this.tileRow / MapRenderer.tileSize, this.tileCol / MapRenderer.tileSize, this);
                    this.layer = (byte) LAYER_AIR;
                }
                this.z++;
            } else {
                heliDescend(i, i2);
            }
        } else if (this.heliState == STATE_RETURNING) {
            int i3 = this.tileCol - this.x;
            int i4 = this.tileRow - this.y;
            if (Math.abs(i3) >= LAND_DIST_TILES || Math.abs(i4) >= LAND_DIST_TILES) {
                heliDescend(i3, i4);
            } else if (this.z == MAX_ALTITUDE) {
                if (!MapRenderer.isBlocked(this.tileRow / MapRenderer.tileSize, this.tileCol / MapRenderer.tileSize)) {
                    this.z--;
                    MapRenderer.setTileHp(this.tileRow / MapRenderer.tileSize, this.tileCol / MapRenderer.tileSize, 1);
                    this.layer = (byte) LAYER_GROUND;
                }
            } else if (this.z > 0) {
                this.z--;
                if (this.z == 0) {
                    GameLevel.tileOccupancy[this.tileRow / MapRenderer.tileSize][this.tileCol / MapRenderer.tileSize] = this;
                }
            } else if (this.attackTimer > 0) {
                this.attackTimer--;
            } else {
                this.heliState = STATE_LANDED;
            }
        }
        this.drawX = (((this.x - GameEntity.cameraX) * this.z) / GameEntity.zScale) + this.x;
        this.drawY = (((this.y - GameEntity.cameraY) * this.z) / GameEntity.zScale) + this.y;
        if (this.currentHp > DEATH_HP_THRESHOLD || this.targetY != 0) {
            return;
        }
        GameLevel.spawnEffect(EFFECT_SMOKE, this.drawX + (this.width >> 1), this.drawY + (this.height >> 1), 0, 0, 0);
    }
    private void heliDescend(int i, int i2) {
        int iM82a = ResourceManager.angleBetween(i, i2);
        int i3 = (i * i) + (i2 * i2);
        if (Math.abs(iM82a - this.angle) < TURN_TOLERANCE_WIDE || i3 > TURN_FIELD_SQ) {
            if (Math.abs(iM82a - this.angle) < TURN_TOLERANCE_TIGHT) {
                this.angle = iM82a;
            } else if ((iM82a - this.angle < 0 || iM82a - this.angle >= 180) && iM82a - this.angle >= -180) {
                this.angle = ResourceManager.normalizeAngle(this.angle - TURN_STEP);
            } else {
                this.angle = ResourceManager.normalizeAngle(this.angle + TURN_STEP);
            }
        }
        if (this.heliState == STATE_ATTACKING && i3 < LAND_PROXIMITY_SQ) {
            this.speed = 0;
        } else if (this.speed < MAX_SPEED) {
            this.speed += APPROACH_ACCEL;
        }
        this.velocityX = (this.speed * ResourceManager.cos(this.angle)) >> TRIG_SCALE;
        this.velocityY = (this.speed * ResourceManager.sin(this.angle)) >> TRIG_SCALE;
        applyVelocity();
        if (this.heliState == STATE_ATTACKING) {
            if (this.attackCooldown > 0) {
                this.attackCooldown = (byte) (this.attackCooldown - 1);
                return;
            }
            if (Math.abs(ResourceManager.angleDelta(iM82a, this.angle)) <= FIRE_ANGLE_TOLERANCE) {
                this.angle = iM82a;
                this.aimingAtPlayer = true;
                this.targetX++;
                this.attackCooldown = (byte) FIRE_COOLDOWN;
                GameLevel.spawnBullet((byte) 11, this.x + HELI_HALF, this.y + HELI_HALF, this.angle, this.target);
                if (this.targetX == VOLLEY_SIZE) {
                    this.targetX = 0;
                    this.heliState = STATE_RETURNING;
                }
            }
        }
    }

    @Override // p000.Sprite
    public final void onDeath() {
        this.dead = true;
        GameLevel.spawnEffect((byte) 0, this.x + HELI_HALF, this.y + HELI_HALF, 0, 0, 0);
        GameLevel.spawnEffect((byte) 6, this.x + HELI_HALF, this.y + HELI_HALF, 0, 0, 0);
        GameLevel.clearTileOccupant(this.tileRow / MapRenderer.tileSize, this.tileCol / MapRenderer.tileSize, this);
        GameLevel.enemiesKilled++;
    }
}
