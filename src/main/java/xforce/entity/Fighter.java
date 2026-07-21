package xforce.entity;

import xforce.game.GameLevel;
import xforce.map.MapRenderer;
import xforce.resource.ResourceManager;
import xforce.resource.SpriteSheet;

import javax.microedition.lcdui.Graphics;

public final class Fighter extends Sprite {

    private static final byte TYPE_SMALL    = 11;
    private static final byte TYPE_LARGE    = 12;

    private static final byte FACTION_HOSTILE = -1;

    private static final int LAYER_AIR      = 2;
    private static final int LAYER_GROUND   = 0;

    private static final int SPRITE_FRAME_ANGLE_OFFSET = 22;
    private static final int ANGLES_PER_FRAME = 45;

    private static final int HITBOX         = 16;
    private static final int SPRITE_SMALL   = 19;
    private static final int SPRITE_LARGE   = 32;
    private static final int SPRITE_EXHAUST = 24;
    private static final int HALF_SMALL     = 9;

    private static final int FLIGHT_ALTITUDE = 30;
    private static final int HP_TOTAL         = 5;

    private static final int SPEED_SMALL     = 5120;
    private static final int SPEED_LARGE     = 8192;
    private static final int FUEL_SMALL      = 600;
    private static final int FUEL_LARGE      = 400;
    private static final int FUEL_RETREAT    = 100;

    private static final int TURN_TOLERANCE_WIDE = 90;
    private static final int TURN_TOLERANCE_TIGHT = 10;
    private static final int TURN_STEP            = 4;
    private static final int AGGRO_LOSE_SQ        = 16384;

    private static final int TRIG_SCALE     = 10;
    private static final int LINE_SCALE     = 6;

    private static final int COLLISION_DAMAGE = 1;
    private static final int ATTACK_COOLDOWN  = 3;
    private static final int MISSILE_COOLDOWN = 100;

    private static final int HP_SMOKE_THRESHOLD = 2;

    private static final byte BULLET_LANDMINE = 15;
    private static final byte BULLET_HOMING   = 11;

    private static final byte EFFECT_HIT    = 1;
    private static final byte EFFECT_MISS   = 10;
    private static final byte EFFECT_SMOKE  = 4;
    private static final byte EFFECT_DEBRIS = 0;

    private static final int TILE_BRIDGE = 40;

    private static final int COLOR_TARGET  = 11908533;
    private int fuel;

    public Fighter(int spawnX, int spawnY, byte type) {
        super(spawnX, spawnY, type);
        setBounds(spawnX, spawnY, HITBOX, HITBOX);
        this.speed = SPEED_SMALL;
        switch (type) {
            case TYPE_SMALL:
                this.z = FLIGHT_ALTITUDE;
                this.fuel = FUEL_SMALL;
                if (ResourceManager.fighterSprite == null) {
                    ResourceManager.fighterSprite = new SpriteSheet(ResourceManager.loadImage("/fighter.png"), SPRITE_SMALL, SPRITE_SMALL);
                }
                if (ResourceManager.fireSprite == null) {
                    ResourceManager.fireSprite = new SpriteSheet(ResourceManager.loadImage("/fs.png"), SPRITE_SMALL, SPRITE_SMALL);
                }
                break;
            case TYPE_LARGE:
                this.z = FLIGHT_ALTITUDE;
                this.speed = SPEED_LARGE;
                this.fuel = FUEL_LARGE;
                if (ResourceManager.fighterLargeSprite == null) {
                    ResourceManager.fighterLargeSprite = new SpriteSheet(ResourceManager.loadImage("/fighter3.png"), SPRITE_LARGE, SPRITE_LARGE);
                }
                if (ResourceManager.fighterExhaustSprite == null) {
                    ResourceManager.fighterExhaustSprite = new SpriteSheet(ResourceManager.loadImage("/fs2.png"), SPRITE_EXHAUST, SPRITE_EXHAUST);
                }
                break;
        }
        this.maxHp = (short) HP_TOTAL;
        this.currentHp = (short) HP_TOTAL;
        this.layer = (byte) LAYER_AIR;
        this.faction = FACTION_HOSTILE;
    }

    @Override // p000.Sprite
    public final void draw(Graphics graphics) {
        switch (this.type) {
            case TYPE_SMALL:
                if (this.aimingAtPlayer) {
                    graphics.setColor(COLOR_TARGET);
                    graphics.drawLine(this.drawX + HALF_SMALL, this.drawY + HALF_SMALL, this.drawX + (this.velocityX >> LINE_SCALE) + HALF_SMALL, this.drawY + (this.velocityY >> LINE_SCALE) + HALF_SMALL);
                    this.aimingAtPlayer = false;
                }
                ResourceManager.fireSprite.drawFrame(((this.angle + SPRITE_FRAME_ANGLE_OFFSET) % 360) / ANGLES_PER_FRAME, this.x + this.z, this.y + this.z, 0, graphics);
                ResourceManager.fighterSprite.drawFrame(((this.angle + SPRITE_FRAME_ANGLE_OFFSET) % 360) / ANGLES_PER_FRAME, this.drawX, this.drawY, 0, graphics);
                break;
            case TYPE_LARGE:
                ResourceManager.fighterExhaustSprite.drawFrame(((this.angle + SPRITE_FRAME_ANGLE_OFFSET) % 360) / ANGLES_PER_FRAME, this.x + this.z, this.y + this.z, 0, graphics);
                ResourceManager.fighterLargeSprite.drawFrame(((this.angle + SPRITE_FRAME_ANGLE_OFFSET) % 360) / ANGLES_PER_FRAME, this.drawX - 6, this.drawY - 6, 0, graphics);
                break;
        }
    }

    @Override // p000.GameEntity
    public final void update() {
        int i = 0;
        if (this.target != null) {
            int i2 = this.target.x - this.x;
            int i3 = this.target.y - this.y;
            int iM82a = ResourceManager.angleBetween(i2, i3);
            i = iM82a;
            if (Math.abs(iM82a - this.angle) < TURN_TOLERANCE_WIDE || (i2 * i2) + (i3 * i3) > AGGRO_LOSE_SQ) {
                if (Math.abs(i - this.angle) < TURN_TOLERANCE_TIGHT) {
                    this.angle = i;
                } else if ((i - this.angle < 0 || i - this.angle >= 180) && i - this.angle >= -180) {
                    this.angle -= TURN_STEP;
                } else {
                    this.angle += TURN_STEP;
                }
                if (this.angle < 0) {
                    this.angle += 360;
                }
                if (this.angle > 360) {
                    this.angle -= 360;
                }
            }
            this.velocityX = (this.speed * ResourceManager.cos(this.angle)) >> TRIG_SCALE;
            this.velocityY = (this.speed * ResourceManager.sin(this.angle)) >> TRIG_SCALE;
        }
        applyVelocity();
        this.drawX = (((this.x - GameEntity.cameraX) * this.z) / GameEntity.zScale) + this.x;
        this.drawY = (((this.y - GameEntity.cameraY) * this.z) / GameEntity.zScale) + this.y;
        if (this.target != null) {
            if (this.attackCooldown > 0) {
                this.attackCooldown = (byte) (this.attackCooldown - 1);
            }
            if (this.type == TYPE_SMALL && this.angle == i && this.attackCooldown == 0) {
                int i4 = this.x + (this.velocityX >> LINE_SCALE) + HALF_SMALL;
                int i5 = this.y + (this.velocityY >> LINE_SCALE) + HALF_SMALL;
                if (this.target.containsPoint(i4, i5)) {
                    this.target.takeDamage(COLLISION_DAMAGE);
                    GameLevel.spawnEffect(EFFECT_HIT, i4, i5, 0, 0, 0);
                } else {
                    GameLevel.spawnEffect(EFFECT_MISS, i4, i5, 0, 0, 0);
                }
                this.attackCooldown = (byte) ATTACK_COOLDOWN;
                this.aimingAtPlayer = true;
            }
            if (this.type == TYPE_LARGE) {
                if (this.target.layer == 0) {
                    if (collidesWith(this.target) && this.attackCooldown == 0) {
                        if (MapRenderer.getTileType(this.target.x, this.target.y) != TILE_BRIDGE) {
                            GameLevel.spawnBullet(BULLET_LANDMINE, this.x + 12, this.y + 12, 0, null);
                        }
                        this.attackCooldown = (byte) ATTACK_COOLDOWN;
                    }
                } else if (this.angle == i && this.attackCooldown == 0) {
                    GameLevel.spawnBullet(BULLET_HOMING, this.x + 10, this.y + 10, this.angle, this.target);
                    GameLevel.spawnBullet(BULLET_HOMING, this.x - 10, this.y - 10, this.angle, this.target);
                    this.attackCooldown = (byte) MISSILE_COOLDOWN;
                }
            }
        }
        if (this.currentHp <= 0) {
            this.dead = true;
            GameLevel.spawnEffect(EFFECT_DEBRIS, this.drawX, this.drawY, 0, 0, 0);
        }
        if (this.currentHp <= HP_SMOKE_THRESHOLD && ResourceManager.randomInt(3) != 0) {
            GameLevel.spawnEffect(EFFECT_SMOKE, this.drawX + (this.width >> 1), this.drawY + (this.height >> 1), 0, 0, 0);
        }
        if (this.fuel <= 0) {
            this.dead = true;
            return;
        }
        this.fuel--;
        if (this.fuel == FUEL_RETREAT) {
            this.target = null;
        }
    }
}
