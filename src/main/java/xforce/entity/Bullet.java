package xforce.entity;

import xforce.game.GameLevel;
import xforce.map.MapRenderer;
import xforce.resource.ResourceManager;

import javax.microedition.lcdui.Graphics;

public final class Bullet extends GameEntity {

    private static final byte KIND_TANK_SHELL  = 0;
    private static final byte KIND_HELI_SHELL  = 1;
    private static final byte KIND_CANNON      = 2;
    private static final byte KIND_ROCKET      = 3;
    private static final byte KIND_PIERCING    = 4;
    private static final byte KIND_HOMING      = 10;
    private static final byte KIND_ENEMY_HOMING = 11;
    private static final byte KIND_AREA_BLAST  = 14;
    private static final byte KIND_LANDMINE    = 15;
    private static final byte KIND_DYNAMITE    = 16;
    private static final byte KIND_OBJECTIVE   = 17;
    private static final byte KIND_OBJ_CHECK   = 18;
    private static final byte KIND_ENEMY_WEAK  = 20;
    private static final byte KIND_ENEMY_MED   = 21;
    private static final byte KIND_ENEMY_STRONG = 22;

    private static final int FACTION_HOSTILE = -1;
    private static final int FACTION_FRIEND  = 1;

    private static final int HOMING_TURN_RATE    = 15;
    private static final int HOMING_MAX_SPEED    = 8192;
    private static final int HOMING_ACCEL        = 1024;
    private static final int HOMING_LOCK_RANGE_SQ = 4096;

    private static final int TRIG_SCALE = 10;

    private static final int COLOR_WHITE  = 16777215;
    private static final int COLOR_YELLOW = 16776960;
    private static final int COLOR_RED    = 16711680;
    private static final int COLOR_GREEN  = 11141120;
    private static final int COLOR_BLACK  = 0;

    private static final int DEFAULT_LIFETIME = 100;
    private static final int SCREENSHAKE_DURATION = 10;

    public byte damage;
    private byte bulletKind;
    private int homingAngle;
    private short bulletSpeed;
    private static int renderX;
    private static int renderY;
    private byte facingQuadrant;
    private byte animTimer;
    private byte lifetime;
    private byte spriteFrame;
    private Sprite targeter;

    public Bullet() {
    }

    public Bullet(byte kind, int x, int y) {
        init((byte) 18, x, y, 0, null);
    }

    public void init(byte kind, int x, int y, int angle, Sprite target) {
        this.x = x;
        this.y = y;
        this.bulletKind = kind;
        this.targeter = target;
        this.velocityX = 0;
        this.velocityY = 0;
        this.bulletSpeed = (short) 0;
        this.lifetime = (byte) DEFAULT_LIFETIME;
        this.z = 0;
        switch (kind) {
            case KIND_TANK_SHELL:
                this.bulletSpeed = (short) 12288;
                this.spriteFrame = (byte) 0;
                this.damage = (byte) 1;
                break;
            case KIND_HELI_SHELL:
                this.bulletSpeed = (short) 13312;
                this.spriteFrame = (byte) 4;
                this.damage = (byte) 2;
                break;
            case KIND_CANNON:
                this.bulletSpeed = (short) 14336;
                this.spriteFrame = (byte) 8;
                this.damage = (byte) 4;
                break;
            case KIND_ROCKET:
                this.bulletSpeed = (short) 15360;
                this.spriteFrame = (byte) 12;
                this.damage = (byte) 6;
                break;
            case KIND_PIERCING:
                this.bulletSpeed = (short) 16384;
                this.spriteFrame = (byte) 16;
                this.damage = (byte) 10;
                break;
            case KIND_HOMING:
                this.bulletSpeed = (short) 0;
                this.damage = (byte) 3;
                break;
            case KIND_ENEMY_HOMING:
                this.homingAngle = angle;
                this.bulletSpeed = (short) 0;
                this.lifetime = (byte) 50;
                this.damage = (byte) 2;
                break;
            case KIND_AREA_BLAST:
                this.bulletSpeed = (short) 0;
                this.lifetime = (byte) 16;
                this.damage = (byte) 10;
                break;
            case KIND_LANDMINE:
                this.bulletSpeed = (short) 0;
                this.lifetime = (byte) 8;
                this.damage = (byte) 5;
                break;
            case KIND_DYNAMITE:
                this.bulletSpeed = (short) 0;
                this.lifetime = (byte) 50;
                this.damage = (byte) 100;
                break;
            case KIND_OBJECTIVE:
                this.damage = (byte) 100;
                break;
            case KIND_OBJ_CHECK:
                this.damage = (byte) 15;
                break;
            case KIND_ENEMY_WEAK:
                this.spriteFrame = (byte) 20;
                this.bulletSpeed = (short) 4096;
                this.damage = (byte) 1;
                break;
            case KIND_ENEMY_MED:
                this.spriteFrame = (byte) 23;
                this.bulletSpeed = (short) 2048;
                this.damage = (byte) 3;
                break;
            case KIND_ENEMY_STRONG:
                this.spriteFrame = (byte) 26;
                this.bulletSpeed = (short) 4096;
                this.damage = (byte) 2;
                break;
        }
        this.velocityX = (this.bulletSpeed * ResourceManager.cos(angle)) >> TRIG_SCALE;
        this.velocityY = (this.bulletSpeed * ResourceManager.sin(angle)) >> TRIG_SCALE;
        switch (angle) {
            case 0:
                this.facingQuadrant = (byte) 3;
                break;
            case 90:
                this.facingQuadrant = (byte) 1;
                break;
            case 180:
                this.facingQuadrant = (byte) 2;
                break;
            case 270:
                this.facingQuadrant = (byte) 0;
                break;
        }
        this.hidden = false;
    }

    public final void render(Graphics g) {
        switch (this.bulletKind) {
            case KIND_TANK_SHELL:
            case KIND_HELI_SHELL:
            case KIND_CANNON:
            case KIND_ROCKET:
            case KIND_PIERCING:
                ResourceManager.bulletComposite.drawRegion(this.spriteFrame + this.facingQuadrant, this.x, this.y, 0, g);
                break;
            case KIND_HOMING:
            case KIND_ENEMY_HOMING:
                renderX = (((this.x - GameEntity.cameraX) * this.z) / GameEntity.zScale) + this.x;
                renderY = (((this.y - GameEntity.cameraY) * this.z) / GameEntity.zScale) + this.y;
                int tipX = renderX + ((5 * ResourceManager.cos(this.homingAngle)) >> TRIG_SCALE);
                int tipY = renderY + ((5 * ResourceManager.sin(this.homingAngle)) >> TRIG_SCALE);
                g.setColor(COLOR_WHITE);
                g.drawLine(renderX, renderY, tipX, tipY);
                break;
            case KIND_AREA_BLAST:
                int radius = this.lifetime > 8 ? 32 - ((this.lifetime - 8) << 2) : 32 - (this.lifetime << 2);
                g.setColor(COLOR_YELLOW);
                g.drawArc(this.x - radius, this.y - radius, radius + radius, radius + radius, 0, 360);
                break;
            case KIND_LANDMINE:
                g.setColor(COLOR_WHITE);
                g.drawArc(this.x - this.lifetime, this.y - this.lifetime, this.lifetime + this.lifetime, this.lifetime + this.lifetime, 0, 360);
                break;
            case KIND_DYNAMITE:
                ResourceManager.itemSprite.drawFrame(4, this.x - 8, this.y - 8, 0, g);
                if (this.lifetime > 20) {
                    g.setColor(this.lifetime % 8 < 4 ? 0 : COLOR_RED);
                } else {
                    g.setColor(this.lifetime % 4 < 2 ? 0 : COLOR_RED);
                }
                g.fillRect(this.x, this.y - 1, 3, 3);
                break;
            case KIND_OBJECTIVE:
                ResourceManager.itemSprite.drawFrame(5, this.x - 8, this.y - 8, 0, g);
                g.setColor(this.animTimer < 4 ? COLOR_GREEN : COLOR_RED);
                g.fillRect(this.x - 1, this.y - 1, 2, 2);
                this.animTimer = (byte) (this.animTimer + 1);
                if (this.animTimer >= 8) {
                    this.animTimer = (byte) 0;
                }
                break;
            case KIND_OBJ_CHECK:
                g.setColor(COLOR_BLACK);
                g.fillRect(this.x - 3, this.y - 3, 6, 6);
                g.setColor(this.animTimer < 4 ? COLOR_GREEN : COLOR_RED);
                g.fillRect(this.x - 1, this.y - 1, 2, 2);
                this.animTimer = (byte) (this.animTimer + 1);
                if (this.animTimer >= 8) {
                    this.animTimer = (byte) 0;
                }
                break;
            case KIND_ENEMY_WEAK:
            case KIND_ENEMY_MED:
                this.animTimer = (byte) (this.animTimer + 1);
                if (this.animTimer >= 3) {
                    this.animTimer = (byte) 0;
                }
                ResourceManager.bulletComposite.drawRegion(this.spriteFrame + this.animTimer, this.x, this.y, 0, g);
                break;
            case KIND_ENEMY_STRONG:
                this.animTimer = (byte) (this.animTimer + 1);
                if (this.animTimer >= 4) {
                    this.animTimer = (byte) 0;
                }
                ResourceManager.bulletComposite.drawRegion(this.spriteFrame + this.animTimer, this.x, this.y, 0, g);
                break;
        }
    }

    @Override
    public final void update() {
        if (this.x < 0 || this.y < 0 || this.x >= MapRenderer.mapPixelWidth || this.y >= MapRenderer.mapPixelHeight || this.lifetime <= 0) {
            this.hidden = true;
            return;
        }
        if (this.bulletKind < KIND_HOMING) {
            Sprite entity = GameLevel.entityAt(this.y / MapRenderer.tileSize, this.x / MapRenderer.tileSize);
            if (entity == null || entity.faction == FACTION_FRIEND) {
                if (MapRenderer.damageTile(this.x, this.y, this.damage)) {
                    if (this.bulletKind == 4) {
                        this.bulletKind = (byte) 3;
                    } else {
                        this.hidden = true;
                    }
                } else if (GameLevel.missionObjective != null && GameLevel.missionObjective.faction == FACTION_HOSTILE && GameLevel.missionObjective.takeDamageFrom(this)) {
                    this.hidden = true;
                }
            } else if (entity.takeDamageFrom(this)) {
                if (this.bulletKind == 4) {
                    this.bulletKind = (byte) 3;
                } else {
                    this.hidden = true;
                }
            }
            if (!GameLevel.isInViewport(this)) {
                this.hidden = true;
            }
        }
        if (this.bulletKind >= KIND_ENEMY_WEAK) {
            Sprite entity = GameLevel.entityAt(this.y / MapRenderer.tileSize, this.x / MapRenderer.tileSize);
            if ((entity != null && entity.faction == FACTION_FRIEND && entity.takeDamageFrom(this)) || GameLevel.player.takeDamageFrom(this) || MapRenderer.damageSpecialTile(this.x, this.y, this.damage)) {
                this.hidden = true;
            }
        }
        switch (this.bulletKind) {
            case KIND_HOMING:
                if (this.targeter != null) {
                    int dx = (this.targeter.x + (this.targeter.width >> 1)) - this.x;
                    int dy = (this.targeter.y + (this.targeter.height >> 1)) - this.y;
                    int angleToTarget = ResourceManager.angleBetween(dx, dy);
                    if (Math.abs(angleToTarget - this.homingAngle) < 90 || (dx * dx) + (dy * dy) > HOMING_LOCK_RANGE_SQ) {
                        if (Math.abs(angleToTarget - this.homingAngle) < HOMING_TURN_RATE) {
                            this.homingAngle = angleToTarget;
                        } else if ((angleToTarget - this.homingAngle < 0 || angleToTarget - this.homingAngle >= 180) && angleToTarget - this.homingAngle >= -180) {
                            this.homingAngle = ResourceManager.normalizeAngle(this.homingAngle - HOMING_TURN_RATE);
                        } else {
                            this.homingAngle = ResourceManager.normalizeAngle(this.homingAngle + HOMING_TURN_RATE);
                        }
                    }
                    if (this.bulletSpeed < HOMING_MAX_SPEED) {
                        this.bulletSpeed = (short) (this.bulletSpeed + HOMING_ACCEL);
                    }
                    this.velocityX = (this.bulletSpeed * ResourceManager.cos(this.homingAngle)) >> TRIG_SCALE;
                    this.velocityY = (this.bulletSpeed * ResourceManager.sin(this.homingAngle)) >> TRIG_SCALE;
                    if (this.z < this.targeter.z) {
                        this.z++;
                    }
                    if (this.z > this.targeter.z) {
                        this.z--;
                    }
                    renderX = (((this.x - GameEntity.cameraX) * this.z) / GameEntity.zScale) + this.x;
                    renderY = (((this.y - GameEntity.cameraY) * this.z) / GameEntity.zScale) + this.y;
                    GameLevel.spawnEffect((byte) 2, renderX, renderY, 0, 0, 0);
                    if (this.z == this.targeter.z && this.targeter.takeDamageFrom(this)) {
                        this.hidden = true;
                    }
                }
                break;
            case KIND_ENEMY_HOMING:
                if (this.targeter != null) {
                    int angleToTarget = ResourceManager.angleBetween((this.targeter.x + (this.targeter.width >> 1)) - this.x, (this.targeter.y + (this.targeter.height >> 1)) - this.y);
                    if (this.lifetime > 37) {
                        if (Math.abs(angleToTarget - this.homingAngle) < HOMING_TURN_RATE) {
                            this.homingAngle = angleToTarget;
                        } else if ((angleToTarget - this.homingAngle < 0 || angleToTarget - this.homingAngle >= 180) && angleToTarget - this.homingAngle >= -180) {
                            this.homingAngle = ResourceManager.normalizeAngle(this.homingAngle - HOMING_TURN_RATE);
                        } else {
                            this.homingAngle = ResourceManager.normalizeAngle(this.homingAngle + HOMING_TURN_RATE);
                        }
                    }
                    if (this.bulletSpeed < HOMING_MAX_SPEED) {
                        this.bulletSpeed = (short) (this.bulletSpeed + HOMING_ACCEL);
                    }
                    this.velocityX = (this.bulletSpeed * ResourceManager.cos(this.homingAngle)) >> TRIG_SCALE;
                    this.velocityY = (this.bulletSpeed * ResourceManager.sin(this.homingAngle)) >> TRIG_SCALE;
                    renderX = (((this.x - GameEntity.cameraX) * this.z) / GameEntity.zScale) + this.x;
                    renderY = (((this.y - GameEntity.cameraY) * this.z) / GameEntity.zScale) + this.y;
                    GameLevel.spawnEffect((byte) 10, renderX, renderY, 0, 0, 0);
                    if (this.targeter.takeDamageFrom(this)) {
                        this.hidden = true;
                    }
                }
                break;
            case KIND_AREA_BLAST:
                int tileY = this.y / MapRenderer.tileSize;
                int tileX = this.x / MapRenderer.tileSize;
                if (this.lifetime == 10) {
                    tileY--;
                }
                if (this.lifetime == 7) {
                    tileY++;
                }
                if (this.lifetime == 4) {
                    tileX--;
                }
                if (this.lifetime == 1) {
                    tileX++;
                    this.hidden = true;
                }
                if ((this.lifetime == 10 || this.lifetime == 7 || this.lifetime == 4 || this.lifetime == 1) && tileY >= 0 && tileY < MapRenderer.mapHeight && tileX >= 0 && tileX < MapRenderer.mapWidth) {
                    if (GameLevel.tileOccupancy[tileY][tileX] != null) {
                        GameLevel.tileOccupancy[tileY][tileX].takeDamage(this.damage);
                    }
                    MapRenderer.damageTile((tileX * MapRenderer.tileSize) + 12, (tileY * MapRenderer.tileSize) + 12, this.damage);
                }
                break;
            case KIND_LANDMINE:
                if (this.lifetime == 1) {
                    int ty = this.y / MapRenderer.tileSize;
                    int tx = this.x / MapRenderer.tileSize;
                    if (GameLevel.tileOccupancy[ty][tx] != null) {
                        GameLevel.tileOccupancy[ty][tx].takeDamage(this.damage);
                    }
                    MapRenderer.damageSpecialTile(this.x, this.y, this.damage);
                    GameLevel.spawnEffect((byte) 0, this.x, this.y, 0, 0, 0);
                    GameLevel.spawnEffect((byte) 6, this.x, this.y, 0, 0, 0);
                    GameLevel.screenShake = SCREENSHAKE_DURATION;
                    this.hidden = true;
                }
                break;
            case KIND_DYNAMITE:
                if (this.lifetime == 1) {
                    int ty = this.y / MapRenderer.tileSize;
                    int tx = this.x / MapRenderer.tileSize;
                    MapRenderer.damageTile(this.x, this.y - 24, this.damage);
                    MapRenderer.damageTile(this.x, this.y + 24, this.damage);
                    MapRenderer.damageTile(this.x - 24, this.y, this.damage);
                    MapRenderer.damageTile(this.x + 24, this.y, this.damage);
                    System.out.println("error yet?");
                    Sprite entity = GameLevel.entityAt(ty - 1, tx);
                    if (entity != null) entity.takeDamage(this.damage);
                    entity = GameLevel.entityAt(ty + 1, tx);
                    if (entity != null) entity.takeDamage(this.damage);
                    entity = GameLevel.entityAt(ty, tx - 1);
                    if (entity != null) entity.takeDamage(this.damage);
                    entity = GameLevel.entityAt(ty, tx + 1);
                    if (entity != null) entity.takeDamage(this.damage);
                    GameLevel.spawnEffect((byte) 0, this.x, this.y, 0, 0, 0);
                    GameLevel.spawnEffect((byte) 6, this.x, this.y, 0, 0, 0);
                    GameLevel.screenShake = SCREENSHAKE_DURATION;
                    this.hidden = true;
                }
                break;
            case KIND_OBJECTIVE:
                if (GameLevel.missionObjective.takeDamageFrom(this)) {
                    this.hidden = true;
                }
                this.lifetime = (byte) DEFAULT_LIFETIME;
                break;
            case KIND_OBJ_CHECK:
                Sprite entity = GameLevel.entityAt(this.y / MapRenderer.tileSize, this.x / MapRenderer.tileSize);
                if (entity != null && entity.faction == FACTION_HOSTILE && entity.takeDamageFrom(this)) {
                    this.dead = true;
                }
                this.lifetime = (byte) DEFAULT_LIFETIME;
                break;
        }
        applyVelocity();
        if (this.lifetime > 0) {
            this.lifetime = (byte) (this.lifetime - 1);
        }
    }
}
