package xforce.entity;

import xforce.game.GameLevel;
import xforce.map.MapRenderer;
import xforce.resource.ResourceManager;
import xforce.resource.SpriteSheet;

import javax.microedition.lcdui.Graphics;

public final class EnemyTurret extends Sprite {

    private static final byte TURRET_STANDARD   = 45;
    private static final byte TURRET_DUAL       = 46;
    private static final byte TURRET_AA_WEAK    = 47;
    private static final byte TURRET_AA_STRONG  = 48;
    private static final byte TURRET_HOMING     = 49;
    private static final byte TURRET_BOSS_TOP   = 104;
    private static final byte TURRET_BOSS_SIDE  = 105;
    private static final byte TURRET_BOSS_CENTER = 106;
    public static final byte TURRET_LEADING    = 108;
    public static final byte TURRET_RING       = 109;

    private static final byte FACTION_HOSTILE = -1;

    private static final int TILE_SIZE    = 24;
    private static final int TILE_HALF    = 12;
    private static final int TINY_SIZE    = 10;
    private static final int SMALL_SIZE   = 15;
    private static final int TRIG_SCALE   = 10;

    private static final int SPRITE_FRAME_ANGLE_OFFSET = 22;
    private static final int ANGLES_PER_FRAME = 45;

    private static final int RADAR_CONE_HALF    = 15;
    private static final int AIM_ANGLE_TOLERANCE = 15;
    private static final int SWEEP_SPEED        = 2;
    private static final int FIRE_COOLDOWN      = 40;
    private static final int FIRE_COOLDOWN_SLOW = 50;
    private static final int FIRE_COOLDOWN_LONG = 100;

    private static final int DETECT_RANGE_CLOSE = 9216;
    private static final int DETECT_RANGE_FAR   = 16384;
    private static final int DETECT_RANGE_AA    = 10000;

    private static final int BURST_SPREAD_DEG   = 10;
    private static final int BURST_COUNT        = 7;
    private static final int RING_STEP_DEG      = 30;
    private static final int RING_COUNT         = 12;

    private static final int MUZZLE_OFFSET = 12;
    private static final int SPREAD_HALF   = 15;

    private static final byte BULLET_ENEMY_WEAK   = 20;
    private static final byte BULLET_ENEMY_MED    = 21;
    private static final byte BULLET_HOMING       = 11;

    private static final int COLOR_WHITE  = 16777215;
    private static final int COLOR_RED    = 16711680;
    private static final int COLOR_TARGET = 52224;

    private static final byte TILE_DEAD_GUN    = 56;
    private static final byte TILE_DEAD_AA     = 57;
    private static final byte TILE_DEAD_SMALL  = 58;

    private static final byte EFFECT_DEBRIS    = 0;
    private static final byte EFFECT_BIG_BOOM  = 5;
    public EnemyTurret(int i, int i2, byte b) {
        super(i, i2, b);
        setBounds(i, i2, TILE_SIZE, TILE_SIZE);
        this.animTimer = ResourceManager.randomInt(2);
        this.target = GameLevel.player;
        this.faction = (byte) FACTION_HOSTILE;
        switch (b) {
            case 45:
                this.currentHp = (short) 5;
                this.maxHp = (short) 5;
                this.xpReward = (short) 100;
                if (ResourceManager.gunSprite == null) {
                    ResourceManager.gunSprite = new SpriteSheet(ResourceManager.loadImage("/gun.png"), TILE_SIZE, TILE_SIZE);
                }
                break;
            case 46:
                this.currentHp = (short) 10;
                this.maxHp = (short) 10;
                this.xpReward = (short) 150;
                if (ResourceManager.gunSprite2 == null) {
                    ResourceManager.gunSprite2 = new SpriteSheet(ResourceManager.loadImage("/gun1.png"), TILE_SIZE, TILE_SIZE);
                }
                break;
            case 47:
                if (ResourceManager.tinyGunSprite == null) {
                    ResourceManager.tinyGunSprite = new SpriteSheet(ResourceManager.loadImage("/gun2.png"), TINY_SIZE, TINY_SIZE);
                }
                this.currentHp = (short) 20;
                this.maxHp = (short) 20;
                this.xpReward = (short) 200;
                break;
            case 48:
                if (ResourceManager.tinyGunSprite == null) {
                    ResourceManager.tinyGunSprite = new SpriteSheet(ResourceManager.loadImage("/gun2.png"), TINY_SIZE, TINY_SIZE);
                }
                this.currentHp = (short) 100;
                this.maxHp = (short) 100;
                this.xpReward = (short) 500;
                break;
            case 49:
                if (ResourceManager.smallGunSprite == null) {
                    ResourceManager.smallGunSprite = new SpriteSheet(ResourceManager.loadImage("/sgun.png"), SMALL_SIZE, SMALL_SIZE);
                }
                this.currentHp = (short) 20;
                this.maxHp = (short) 20;
                this.xpReward = (short) 100;
                break;
            case 104:
            case 105:
                if (ResourceManager.smallGunSprite == null) {
                    ResourceManager.smallGunSprite = new SpriteSheet(ResourceManager.loadImage("/sgun.png"), SMALL_SIZE, SMALL_SIZE);
                }
                this.currentHp = (short) 200;
                this.maxHp = (short) 200;
                this.xpReward = (short) 1000;
                break;
            case 106:
                if (ResourceManager.tinyGunSprite == null) {
                    ResourceManager.tinyGunSprite = new SpriteSheet(ResourceManager.loadImage("/gun2.png"), TINY_SIZE, TINY_SIZE);
                }
                this.currentHp = (short) 500;
                this.maxHp = (short) 500;
                this.xpReward = (short) 2000;
                break;
            case 108:
                this.currentHp = (short) 40;
                this.maxHp = (short) 40;
                this.xpReward = (short) 100;
                if (ResourceManager.gunSprite2 == null) {
                    ResourceManager.gunSprite2 = new SpriteSheet(ResourceManager.loadImage("/gun1.png"), TILE_SIZE, TILE_SIZE);
                }
                break;
            case 109:
                this.currentHp = (short) 60;
                this.maxHp = (short) 60;
                this.xpReward = (short) 100;
                if (ResourceManager.smallGunSprite == null) {
                    ResourceManager.smallGunSprite = new SpriteSheet(ResourceManager.loadImage("/sgun.png"), SMALL_SIZE, SMALL_SIZE);
                }
                break;
        }
        GameLevel.totalEnemies++;
    }

    @Override // p000.Sprite
    public final void draw(Graphics graphics) {
        switch (this.type) {
            case 45:
                ResourceManager.gunSprite.drawFrame(((this.angle + SPRITE_FRAME_ANGLE_OFFSET) % 360) / ANGLES_PER_FRAME, this.x, this.y, 0, graphics);
                break;
            case 46:
            case 108:
                ResourceManager.gunSprite2.drawFrame(((this.angle + SPRITE_FRAME_ANGLE_OFFSET) % 360) / ANGLES_PER_FRAME, this.x, this.y, 0, graphics);
                break;
            case 47:
            case 48:
                if (this.damageFlash) {
                    graphics.setColor(COLOR_WHITE);
                    graphics.fillRect(this.x, this.y, this.width, this.height);
                }
                if (this.aimingAtPlayer) {
                    graphics.setColor(COLOR_RED);
                } else {
                    graphics.setColor(COLOR_TARGET);
                }
                int iM81b = this.x + TILE_HALF + ((ResourceManager.cos(ResourceManager.normalizeAngle(this.angle - RADAR_CONE_HALF)) * 100) >> TRIG_SCALE);
                int iM80a = this.y + TILE_HALF + ((ResourceManager.sin(ResourceManager.normalizeAngle(this.angle - RADAR_CONE_HALF)) * 100) >> TRIG_SCALE);
                int iM81b2 = this.x + TILE_HALF + ((ResourceManager.cos(ResourceManager.normalizeAngle(this.angle + RADAR_CONE_HALF)) * 100) >> TRIG_SCALE);
                int iM80a2 = this.y + TILE_HALF + ((ResourceManager.sin(ResourceManager.normalizeAngle(this.angle + RADAR_CONE_HALF)) * 100) >> TRIG_SCALE);
                graphics.drawLine(this.x + TILE_HALF, this.y + TILE_HALF, iM81b, iM80a);
                graphics.drawLine(this.x + TILE_HALF, this.y + TILE_HALF, iM81b2, iM80a2);
                graphics.drawLine(iM81b, iM80a, iM81b2, iM80a2);
                ResourceManager.tinyGunSprite.drawFrame((((this.angle + SPRITE_FRAME_ANGLE_OFFSET) % 360) / ANGLES_PER_FRAME) % 4, this.x + 7, this.y + 7, 0, graphics);
                break;
            case 49:
                ResourceManager.smallGunSprite.drawFrame(((this.angle + SPRITE_FRAME_ANGLE_OFFSET) % 360) / ANGLES_PER_FRAME, this.x + 4, this.y + 4, 0, graphics);
                break;
            case 104:
            case 105:
                ResourceManager.smallGunSprite.drawFrame(((this.angle + SPRITE_FRAME_ANGLE_OFFSET) % 360) / ANGLES_PER_FRAME, this.x, this.y, 0, graphics);
                break;
            case 106:
                this.animTimer = (this.animTimer + 1) % 4;
                ResourceManager.tinyGunSprite.drawFrame(this.animTimer, this.x + 3, this.y + 3, 0, graphics);
                break;
            case 109:
                this.animTimer = (this.animTimer + 1) % 4;
                ResourceManager.tinyGunSprite.drawFrame(this.animTimer, this.x + 7, this.y + 7, 0, graphics);
                break;
        }
        drawHpBar(graphics);
        if (this.damageFlash) {
            this.damageFlash = false;
        }
    }

    @Override // p000.GameEntity
    public final void update() {
        switch (this.type) {
            case 45:
                if (this.target != null && !this.target.dead) {
                    int i = this.target.x - this.x;
                    int i2 = this.target.y - this.y;
                    this.angle = ResourceManager.angleBetween(i, i2);
                    if (this.attackCooldown > 0) {
                        this.attackCooldown = (byte) (this.attackCooldown - 1);
                    } else if ((i * i) + (i2 * i2) < DETECT_RANGE_CLOSE) {
                        this.attackCooldown = (byte) FIRE_COOLDOWN;
                        GameLevel.spawnBullet(BULLET_ENEMY_WEAK, this.x + TILE_HALF + ((ResourceManager.cos(this.angle) * 12) >> TRIG_SCALE), this.y + TILE_HALF + ((ResourceManager.sin(this.angle) * 12) >> TRIG_SCALE), this.angle, null);
                    }
                    break;
                }
                break;
            case 46:
            case 108:
                if (this.target != null && !this.target.dead) {
                    int i3 = this.target.x - this.x;
                    int i4 = this.target.y - this.y;
                    this.angle = ResourceManager.angleBetween(i3, i4);
                    if (this.attackCooldown > 0) {
                        this.attackCooldown = (byte) (this.attackCooldown - 1);
                    } else if ((i3 * i3) + (i4 * i4) < DETECT_RANGE_FAR) {
                        this.attackCooldown = (byte) FIRE_COOLDOWN;
                        GameLevel.spawnBullet(BULLET_ENEMY_WEAK, this.x + TILE_HALF + ((ResourceManager.cos(ResourceManager.normalizeAngle(this.angle + RADAR_CONE_HALF)) * 15) >> TRIG_SCALE), this.y + TILE_HALF + ((ResourceManager.sin(ResourceManager.normalizeAngle(this.angle + RADAR_CONE_HALF)) * 15) >> TRIG_SCALE), this.angle, null);
                        GameLevel.spawnBullet(BULLET_ENEMY_WEAK, this.x + TILE_HALF + ((ResourceManager.cos(ResourceManager.normalizeAngle(this.angle - RADAR_CONE_HALF)) * 15) >> TRIG_SCALE), this.y + TILE_HALF + ((ResourceManager.sin(ResourceManager.normalizeAngle(this.angle - RADAR_CONE_HALF)) * 15) >> TRIG_SCALE), this.angle, null);
                    }
                    break;
                }
                break;
            case 47:
            case 48:
                this.animTimer++;
                if (this.animTimer >= 2) {
                    this.animTimer = 0;
                }
                if (this.attackCooldown > 0) {
                    this.attackCooldown = (byte) (this.attackCooldown - 1);
                }
                if (!this.aimingAtPlayer) {
                    this.angle = ResourceManager.normalizeAngle(this.angle + SWEEP_SPEED);
                }
                if (this.target != null && !this.target.dead && this.animTimer == 0) {
                    int i5 = (this.target.x + (this.target.width >> 1)) - (this.x + TILE_HALF);
                    int i6 = (this.target.y + (this.target.height >> 1)) - (this.y + TILE_HALF);
                    this.aimingAtPlayer = false;
                    if ((i5 * i5) + (i6 * i6) < DETECT_RANGE_AA) {
                        int iM82a = ResourceManager.angleBetween(i5, i6);
                        if (Math.abs(ResourceManager.angleDelta(iM82a, this.angle)) <= AIM_ANGLE_TOLERANCE) {
                            this.angle = iM82a;
                            this.aimingAtPlayer = true;
                            if (this.attackCooldown == 0) {
                                this.attackCooldown = (byte) FIRE_COOLDOWN_SLOW;
                                for (int i7 = -3; i7 < BURST_COUNT / 2 + 1; i7++) {
                                    GameLevel.spawnBullet(BULLET_ENEMY_WEAK, this.x + TILE_HALF, this.y + TILE_HALF, ResourceManager.normalizeAngle(iM82a + (i7 * BURST_SPREAD_DEG)), null);
                                }
                            }
                        }
                    }
                    break;
                }
                break;
            case 49:
                if (this.target != null && !this.target.dead) {
                    int i8 = this.target.x - this.x;
                    int i9 = this.target.y - this.y;
                    this.angle = ResourceManager.angleBetween(i8, i9);
                    if (this.attackCooldown > 0) {
                        this.attackCooldown = (byte) (this.attackCooldown - 1);
                    } else {
                        this.attackCooldown = (byte) FIRE_COOLDOWN_LONG;
                    }
                    if ((this.attackCooldown == 80 || this.attackCooldown == FIRE_COOLDOWN_LONG) && (i8 * i8) + (i9 * i9) < DETECT_RANGE_FAR) {
                        GameLevel.spawnBullet(BULLET_HOMING, this.x + TILE_HALF, this.y + TILE_HALF, this.angle, this.target);
                    }
                    break;
                }
                break;
            case 104:
                if (this.target != null && !this.target.dead) {
                    this.angle = ResourceManager.angleBetween(this.target.x - this.x, this.target.y - this.y);
                    if (this.attackCooldown > 0) {
                        this.attackCooldown = (byte) (this.attackCooldown - 1);
                    } else {
                        this.attackCooldown = (byte) FIRE_COOLDOWN;
                        Bullet c0035vM24a = GameLevel.spawnBullet(BULLET_ENEMY_WEAK, this.x + TILE_HALF + ((ResourceManager.cos(this.angle) * 12) >> TRIG_SCALE), this.y + TILE_HALF + ((ResourceManager.sin(this.angle) * 12) >> TRIG_SCALE), this.angle, null);
                        if (c0035vM24a != null) {
                            c0035vM24a.velocityX += GameLevel.player.velocityX;
                            c0035vM24a.velocityY += GameLevel.player.velocityY;
                        }
                    }
                    break;
                }
                break;
            case 105:
                if (this.target != null && !this.target.dead) {
                    this.animTimer++;
                    if (this.animTimer >= 2) {
                        this.animTimer = 0;
                    }
                    if (this.attackCooldown > 0) {
                        this.attackCooldown = (byte) (this.attackCooldown - 1);
                    }
                    if (this.animTimer == 0) {
                        int i10 = (this.target.x + (this.target.width >> 1)) - (this.x + (this.width >> 1));
                        int i11 = (this.target.y + (this.target.height >> 1)) - (this.y + (this.height >> 1));
                        this.aimingAtPlayer = false;
                        this.angle = ResourceManager.angleBetween(i10, i11);
                        this.aimingAtPlayer = true;
                        if (this.attackCooldown == 0) {
                            this.attackCooldown = (byte) FIRE_COOLDOWN_SLOW;
                        }
                        if (this.attackCooldown > FIRE_COOLDOWN) {
                            GameLevel.spawnBullet(BULLET_ENEMY_WEAK, this.x + TILE_HALF + ((ResourceManager.cos(this.angle) * 12) >> TRIG_SCALE), this.y + TILE_HALF + ((ResourceManager.sin(this.angle) * 12) >> TRIG_SCALE), this.angle, null);
                        }
                    }
                    break;
                }
                break;
            case 106:
                if (this.target != null && !this.target.dead) {
                    this.angle = ResourceManager.angleBetween(this.target.x - this.x, this.target.y - this.y);
                    if (this.attackCooldown > 0) {
                        this.attackCooldown = (byte) (this.attackCooldown - 1);
                    } else {
                        this.attackCooldown = (byte) FIRE_COOLDOWN_SLOW;
                        for (int i12 = 0; i12 < 360; i12 += RING_STEP_DEG) {
                            GameLevel.spawnBullet(BULLET_ENEMY_MED, this.x + TILE_HALF, this.y + TILE_HALF, i12, null);
                        }
                    }
                    break;
                }
                break;
            case 109:
                if (this.target != null && !this.target.dead) {
                    int i13 = this.target.x - this.x;
                    int i14 = this.target.y - this.y;
                    if (this.attackCooldown > 0) {
                        this.attackCooldown = (byte) (this.attackCooldown - 1);
                    } else if ((i13 * i13) + (i14 * i14) < DETECT_RANGE_FAR) {
                        this.attackCooldown = (byte) FIRE_COOLDOWN_SLOW;
                        for (int i15 = 0; i15 < 360; i15 += RING_STEP_DEG) {
                            GameLevel.spawnBullet(BULLET_ENEMY_MED, this.x + TILE_HALF, this.y + TILE_HALF, i15, null);
                        }
                    }
                    break;
                }
                break;
        }
    }

    @Override // p000.Sprite
    public final void onDeath() {
        int i = this.x / MapRenderer.tileSize;
        int i2 = this.y / MapRenderer.tileSize;
        switch (this.type) {
            case 45:
            case 46:
                MapRenderer.tileMap[i2][i] = TILE_DEAD_GUN;
                break;
            case 47:
            case 48:
                MapRenderer.tileMap[i2][i] = TILE_DEAD_AA;
                break;
            case 49:
                MapRenderer.tileMap[i2][i] = TILE_DEAD_SMALL;
                break;
        }
        switch (this.type) {
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
                GameLevel.spawnEffect(EFFECT_DEBRIS, this.x + (this.width / 2), this.y + (this.height / 2), 0, 0, 0);
                GameLevel.spawnEffect(EFFECT_BIG_BOOM, this.x + (this.width / 2), this.y + (this.height / 2), 0, 0, 0);
                GameLevel.clearTileOccupant(i2, i, this);
                break;
            case 104:
            case 105:
            case 106:
            case 107:
            case 108:
                GameLevel.spawnEffect(EFFECT_DEBRIS, this.x + (this.width / 2), this.y + (this.height / 2), 0, 0, 0);
                break;
        }
        GameLevel.enemiesKilled++;
        GameLevel.spawnFloatingText("+" + (int) this.xpReward + "XP", this.x, this.y);
        GameLevel.addXP(this.xpReward);
        this.dead = true;
    }
}
