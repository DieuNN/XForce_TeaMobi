package xforce.entity;

import xforce.game.GameLevel;
import xforce.map.MapRenderer;
import xforce.resource.ResourceManager;

import javax.microedition.lcdui.Graphics;

public class Sprite extends GameEntity {

    private static final byte TYPE_WATER_H    = 16;
    private static final byte TYPE_WATER_V    = 17;
    private static final byte TYPE_HOUSE      = 41;
    private static final byte TYPE_POLE_H     = 43;
    private static final byte TYPE_POLE_V     = 44;
    private static final byte TYPE_CRATE_A    = 62;
    private static final byte TYPE_CRATE_B    = 63;
    private static final byte TYPE_EFFECT     = 107;
    private static final byte TYPE_SCENERY_A  = 120;
    private static final byte TYPE_SCENERY_B  = 121;
    private static final byte TYPE_SCENERY_C  = 122;

    private static final byte LAYER_GROUND  = 0;
    private static final byte LAYER_PARALLAX = 1;
    private static final int DEFAULT_HP     = 5;
    private static final int CRATE_HP       = 10;

    private static final int PARALLAX_Z     = 15;
    private static final int HOUSE_Z        = 10;
    private static final int WATER_LENGTH   = 120;
    private static final int POLE_OFFSET    = 9;
    private static final int HOUSE_SIZE     = 22;

    private static final int HP_BAR_DURATION = 100;

    private static final byte PICKUP_DYNAMITE = 4;
    private static final byte PICKUP_COMPLETE = 7;

    private static final int COLOR_WATER_LIGHT = 14106116;
    private static final int COLOR_WATER_DARK  = 16743234;
    private static final int COLOR_WHITE  = 16777215;
    private static final int COLOR_RED    = 16711680;
    private static final int COLOR_HOUSE_TOP    = 15658734;
    private static final int COLOR_HOUSE_SIDE   = 14540253;
    private static final int COLOR_HOUSE_SHADOW = 6052956;
    private static final int COLOR_HOUSE_DARK   = 8421504;
    private static final int COLOR_POLE_LIGHT   = 7096608;
    private static final int COLOR_POLE_DARK    = 10057036;
    private static final int COLOR_SPARK_A      = 8559676;
    private static final int COLOR_SPARK_B      = 15335299;

    private static final byte EFFECT_HIT       = 1;
    private static final byte EFFECT_DEBRIS    = 0;
    private static final byte EFFECT_BOOM      = 5;
    private static final byte EFFECT_EXPLOSION = 6;

    private static final byte FIGHTER_SMALL = 11;
    private static final byte FIGHTER_LARGE = 12;

    private static final int SPRITE_SIZE = 24;
    public int direction;
    public int angle;
    public int speed;
    public Sprite target;
    public byte layer;
    public boolean damageFlash;
    public byte type;
    public byte faction;
    public short currentHp;
    public short maxHp;
    public short xpReward;
    public int drawX;
    public int drawY;
    private int parallaxWidth;
    private int parallaxHeight;
    protected byte attackCooldown;
    protected boolean aimingAtPlayer;
    protected int animTimer;
    private byte hpBarTimer;

    public Sprite(int x, int y, byte type) {
        this.type = type;
        this.currentHp = (short) DEFAULT_HP;
        this.maxHp = (short) DEFAULT_HP;
        this.x = x;
        this.y = y;
        this.layer = LAYER_GROUND;
        switch (type) {
            case 16:
                if (ResourceManager.waterTileImage == null) {
                    ResourceManager.waterTileImage = ResourceManager.loadImage("/b1.png");
                }
                this.z = PARALLAX_Z;
                this.height = WATER_LENGTH;
                this.parallaxHeight = (24 * (this.z + GameEntity.zScale)) / GameEntity.zScale;
                this.layer = LAYER_PARALLAX;
                break;
            case 17:
                if (ResourceManager.waterTileImage2 == null) {
                    ResourceManager.waterTileImage2 = ResourceManager.loadImage("/b2.png");
                }
                this.z = PARALLAX_Z;
                this.width = WATER_LENGTH;
                this.parallaxWidth = (24 * (this.z + GameEntity.zScale)) / GameEntity.zScale;
                this.layer = LAYER_PARALLAX;
                break;
            case 41:
                if (ResourceManager.houseImage == null) {
                    ResourceManager.houseImage = ResourceManager.loadImage("/house2.png");
                }
                this.width = HOUSE_SIZE;
                this.height = HOUSE_SIZE;
                this.z = HOUSE_Z;
                this.parallaxWidth = (this.width * (this.z + GameEntity.zScale)) / GameEntity.zScale;
                this.parallaxHeight = (this.height * (this.z + GameEntity.zScale)) / GameEntity.zScale;
                this.layer = LAYER_PARALLAX;
                break;
            case 43:
            case 44:
                this.x += POLE_OFFSET;
                this.y += POLE_OFFSET;
                this.drawX = x;
                this.drawY = y;
                this.z = PARALLAX_Z;
                this.layer = LAYER_PARALLAX;
                break;
            case 62:
            case 63:
                this.currentHp = (short) CRATE_HP;
                this.maxHp = (short) CRATE_HP;
                setSize(SPRITE_SIZE, SPRITE_SIZE);
                break;
            case 107:
                this.width = 16;
                this.height = 16;
                break;
            case 120:
            case 121:
            case 122:
                if (type == TYPE_SCENERY_A && ResourceManager.mapImage == null) {
                    ResourceManager.mapImage = ResourceManager.loadImage("/lf1.png");
                }
                if (type == TYPE_SCENERY_B && ResourceManager.logoImage == null) {
                    ResourceManager.logoImage = ResourceManager.loadImage("/lf2.png");
                }
                if (type == TYPE_SCENERY_C && ResourceManager.buildingImage == null) {
                    ResourceManager.buildingImage = ResourceManager.loadImage("/lf3.png");
                }
                this.width = SPRITE_SIZE;
                this.height = SPRITE_SIZE;
                this.layer = LAYER_GROUND;
                this.currentHp = (short) CRATE_HP;
                this.maxHp = (short) CRATE_HP;
                break;
        }
    }
    public void draw(Graphics graphics) {
        switch (this.type) {
            case 16:
                this.drawX = (((this.x - GameEntity.cameraX) * this.z) / GameEntity.zScale) + this.x;
                this.drawY = (((this.y - GameEntity.cameraY) * this.z) / GameEntity.zScale) + this.y;
                graphics.drawImage(ResourceManager.waterTileImage, this.x, this.y, 0);
                graphics.setColor(COLOR_WATER_LIGHT);
                for (int damage = 1; damage < 5; damage++) {
                    graphics.drawLine(this.x, this.y + (damage * 24), this.drawX, this.drawY + (damage * this.parallaxHeight));
                }
                graphics.drawLine(this.x + 1, this.y, this.drawX + 1, this.drawY + this.parallaxHeight);
                graphics.drawLine(this.drawX + 1, this.drawY + this.parallaxHeight, this.drawX + 1, this.drawY + (this.parallaxHeight << 2));
                graphics.drawLine(this.x + 1, this.y + 120, this.drawX + 1, this.drawY + (this.parallaxHeight << 2));
                graphics.setColor(COLOR_WATER_DARK);
                graphics.drawLine(this.x, this.y, this.drawX, this.drawY + this.parallaxHeight);
                graphics.drawLine(this.drawX, this.drawY + this.parallaxHeight, this.drawX, this.drawY + (this.parallaxHeight << 2));
                graphics.drawLine(this.x, this.y + 120, this.drawX, this.drawY + (this.parallaxHeight << 2));
                break;
            case 17:
                this.drawX = (((this.x - GameEntity.cameraX) * this.z) / GameEntity.zScale) + this.x;
                this.drawY = (((this.y - GameEntity.cameraY) * this.z) / GameEntity.zScale) + this.y;
                graphics.drawImage(ResourceManager.waterTileImage2, this.x, this.y, 0);
                graphics.setColor(COLOR_WATER_LIGHT);
                for (int tileRow = 1; tileRow < 5; tileRow++) {
                    graphics.drawLine(this.x + (tileRow * 24), this.y, this.drawX + (tileRow * this.parallaxWidth), this.drawY);
                }
                graphics.drawLine(this.x, this.y + 1, this.drawX + this.parallaxWidth, this.drawY + 1);
                graphics.drawLine(this.drawX + this.parallaxWidth, this.drawY + 1, this.drawX + (this.parallaxWidth << 2), this.drawY + 1);
                graphics.drawLine(this.x + 120, this.y + 1, this.drawX + (this.parallaxWidth << 2), this.drawY + 1);
                graphics.setColor(COLOR_WATER_DARK);
                graphics.drawLine(this.x, this.y, this.drawX + this.parallaxWidth, this.drawY);
                graphics.drawLine(this.drawX + this.parallaxWidth, this.drawY, this.drawX + (this.parallaxWidth << 2), this.drawY);
                graphics.drawLine(this.x + 120, this.y, this.drawX + (this.parallaxWidth << 2), this.drawY);
                break;
            case 41:
                graphics.setColor(COLOR_WHITE);
                if (this.damageFlash) {
                    graphics.setColor(COLOR_WHITE);
                    graphics.fillRect(this.x, this.y, this.width, this.height);
                }
                this.drawX = (((this.x - GameEntity.cameraX) * this.z) / GameEntity.zScale) + this.x;
                this.drawY = (((this.y - GameEntity.cameraY) * this.z) / GameEntity.zScale) + this.y;
                if (this.y > GameEntity.cameraY) {
                    ResourceManager.fillQuad(this.x, this.y, this.x + this.width, this.y, this.drawX + this.parallaxWidth, this.drawY, this.drawX, this.drawY, COLOR_HOUSE_TOP, graphics);
                }
                if (this.x > GameEntity.cameraX) {
                    ResourceManager.fillQuad(this.x, this.y, this.drawX, this.drawY, this.drawX, this.drawY + this.parallaxHeight, this.x, this.y + this.height, COLOR_HOUSE_SIDE, graphics);
                }
                if (this.y + this.height < GameEntity.cameraY) {
                    ResourceManager.fillQuad(this.x, this.y + this.height, this.drawX, this.drawY + this.parallaxHeight, this.drawX + this.parallaxWidth, this.drawY + this.parallaxHeight, this.x + this.width, this.y + this.height, COLOR_HOUSE_SHADOW, graphics);
                }
                if (this.x + this.width < GameEntity.cameraX) {
                    ResourceManager.fillQuad(this.x + this.width, this.y, this.x + this.width, this.y + this.height, this.drawX + this.parallaxWidth, this.drawY + this.parallaxHeight, this.drawX + this.parallaxWidth, this.drawY, COLOR_HOUSE_DARK, graphics);
                }
                graphics.drawImage(ResourceManager.houseImage, this.drawX, this.drawY, 0);
                break;
            case 43:
            case 44:
                this.drawX = (((this.x - GameEntity.cameraX) * this.z) / GameEntity.zScale) + this.x;
                this.drawY = (((this.y - GameEntity.cameraY) * this.z) / GameEntity.zScale) + this.y;
                graphics.setColor(COLOR_POLE_LIGHT);
                graphics.drawLine(this.x, this.y, this.drawX, this.drawY);
                graphics.drawLine(this.x + 1, this.y, this.drawX + 1, this.drawY);
                graphics.drawLine(this.x, this.y + 1, this.drawX, this.drawY + 1);
                graphics.drawLine(this.x + 1, this.y + 1, this.drawX + 1, this.drawY + 1);
                if (this.type == TYPE_POLE_H) {
                    graphics.drawLine(this.drawX + 1, this.drawY + 10, this.drawX + 1, this.drawY - 10);
                    graphics.setColor(COLOR_POLE_DARK);
                    graphics.drawLine(this.drawX, this.drawY + 10, this.drawX, this.drawY - 10);
                } else {
                    graphics.drawLine(this.drawX + 10, this.drawY + 1, this.drawX - 10, this.drawY + 1);
                    graphics.setColor(COLOR_POLE_DARK);
                    graphics.drawLine(this.drawX + 10, this.drawY, this.drawX - 10, this.drawY);
                }
                break;
            case 107:
                graphics.setColor(COLOR_SPARK_A);
                graphics.drawLine(this.x, this.y, this.x + 16, this.y);
                graphics.setColor(COLOR_SPARK_B);
                graphics.drawLine(this.x + 8, this.y, this.animTimer == 0 ? this.x : this.x + 16, this.y);
                this.animTimer = 1 - this.animTimer;
                break;
            case 120:
                graphics.drawImage(ResourceManager.mapImage, this.x + 3, this.y + 3, 0);
                break;
            case 121:
                graphics.drawImage(ResourceManager.logoImage, this.x - 1, this.y - 1, 0);
                break;
            case 122:
                graphics.drawImage(ResourceManager.buildingImage, this.x - 2, this.y - 2, 0);
                break;
        }
        drawHpBar(graphics);
        if (this.damageFlash) {
            this.damageFlash = false;
        }
    }
    protected final void drawHpBar(Graphics graphics) {
        if (this.hpBarTimer <= 0 || this.currentHp >= this.maxHp) {
            return;
        }
        graphics.setColor(0);
        graphics.fillRect((this.x + (this.width >> 2)) - 1, this.y - 6, (this.width >> 1) + 2, 4);
        graphics.setColor(COLOR_RED);
        graphics.fillRect(this.x + (this.width >> 2), this.y - 5, (this.currentHp * (this.width >> 1)) / this.maxHp, 2);
        this.hpBarTimer = (byte) (this.hpBarTimer - 1);
    }
    public boolean takeDamageFrom(Bullet bullet) {
        if (this.currentHp <= 0 || !collidesWith((GameEntity) bullet)) {
            return false;
        }
        this.damageFlash = true;
        if (this.currentHp > 0) {
            this.currentHp = (short) (this.currentHp - bullet.damage);
        }
        if (this.currentHp <= 0) {
            onDeath();
            return true;
        }
        GameLevel.spawnEffect(EFFECT_HIT, bullet.x, bullet.y, 0, 0, 0);
        this.hpBarTimer = (byte) HP_BAR_DURATION;
        return true;
    }
    public void takeDamage(int i) {
        this.damageFlash = true;
        if (this.currentHp > 0) {
            this.currentHp = (short) (this.currentHp - i);
        }
        if (this.currentHp <= 0) {
            onDeath();
        }
    }
    public void onDeath() {
        int i = this.x / MapRenderer.tileSize;
        int i2 = this.y / MapRenderer.tileSize;
        switch (this.type) {
            case 41:
                MapRenderer.tileMap[i2][i] = 55;
                GameLevel.clearTileOccupant(i2, i, this);
                GameLevel.spawnEffect(EFFECT_DEBRIS, this.x + (this.width / 2), this.y + (this.height / 2), 0, 0, 0);
                GameLevel.spawnEffect(EFFECT_BOOM, this.x + (this.width / 2), this.y + (this.height / 2), 0, 0, 0);
                break;
            case 42:
                MapRenderer.tileMap[i2][i] = 1;
                GameLevel.clearTileOccupant(i2, i, this);
                GameLevel.spawnEffect(EFFECT_DEBRIS, this.x + (this.width / 2), this.y + (this.height / 2), 0, 0, 0);
                GameLevel.spawnEffect(EFFECT_EXPLOSION, this.x + (this.width / 2), this.y + (this.height / 2), 0, 0, 0);
                break;
            case 62:
                GameLevel.spawnPickup(this.x + 12, this.y + 12, PICKUP_DYNAMITE);
                GameLevel.spawnEffect(EFFECT_EXPLOSION, this.x + (this.width / 2), this.y + (this.height / 2), 0, 0, 0);
                MapRenderer.tileMap[i2][i] = 1;
                GameLevel.clearTileOccupant(i2, i, this);
                break;
            case 63:
                GameLevel.spawnPickup(this.x + 12, this.y + 12, PICKUP_COMPLETE);
                GameLevel.spawnEffect(EFFECT_EXPLOSION, this.x + (this.width / 2), this.y + (this.height / 2), 0, 0, 0);
                MapRenderer.tileMap[i2][i] = 1;
                GameLevel.clearTileOccupant(i2, i, this);
                break;
            case 107:
                GameLevel.spawnEffect(EFFECT_DEBRIS, this.x + (this.width / 2), this.y + (this.height / 2), 0, 0, 0);
                break;
            case 120:
            case 121:
            case 122:
                GameLevel.clearTileOccupant(i2, i, this);
                GameLevel.spawnEffect(EFFECT_DEBRIS, this.x + (this.width / 2), this.y + (this.height / 2), 0, 0, 0);
                GameLevel.spawnEffect(EFFECT_EXPLOSION, this.x + (this.width / 2), this.y + (this.height / 2), 0, 0, 0);
                if (ResourceManager.randomInt(2) != 0) {
                    GameLevel.addEntity((Sprite) new Fighter(this.x - 300, this.y - 300, FIGHTER_SMALL)).target = GameLevel.player;
                } else {
                    GameLevel.addEntity((Sprite) new Fighter(this.x - 300, this.y - 300, FIGHTER_LARGE)).target = GameLevel.player;
                }
                break;
        }
        this.dead = true;
        if (this.xpReward > 0) {
            GameLevel.spawnFloatingText("+" + (int) this.xpReward + "XP", this.x, this.y);
            GameLevel.addXP(this.xpReward);
        }
    }
    public void followCamera() {
        GameLevel.setCameraTarget(this.x + (this.width / 2), this.y + (this.height / 2));
    }
}
