package xforce.entity;

import xforce.audio.AudioManager;
import xforce.game.GameLevel;
import xforce.resource.ResourceManager;

import javax.microedition.lcdui.Graphics;

public final class VisualEffect extends GameEntity {

    private static final byte TYPE_DEBRIS       = 0;
    private static final byte TYPE_SMALL_EXP    = 1;
    private static final byte TYPE_SPARK        = 2;
    private static final byte TYPE_SMOKE        = 3;
    private static final byte TYPE_DELAYED_SMOKE = 4;
    private static final byte TYPE_BIG_EXP      = 5;
    private static final byte TYPE_SPOTLIGHT    = 6;
    private static final byte TYPE_FLOATING_TEXT = 7;
    private static final byte TYPE_SMALL_SPOT   = 8;
    private static final byte TYPE_SMALL_SPOT2  = 9;
    private static final byte TYPE_MOVING_EXP   = 10;
    private static final byte TYPE_DEBRIS_SPARKS = 11;

    private static final byte SUBTYPE_GROUND = 0;
    private static final byte SUBTYPE_AIR    = 1;

    private static final int SFX_EXPLOSION  = 1;
    private static final int SFX_SHOOT      = 0;

    private static final int VOLUME_BASE    = 100;

    private static final int DURATION_SMALL_EXP   = 5;
    private static final int DURATION_SPARK       = 12;
    private static final int DURATION_DEBRIS      = 14;
    private static final int DURATION_FLOAT_TEXT  = 15;
    private static final int DURATION_DEBRIS_FRAME= 16;
    private static final int DURATION_SMOKE       = 18;
    private static final int DURATION_SMALL_SPOT  = 70;
    private static final int DURATION_BIG_EXP     = 100;
    private static final int DURATION_SPOTLIGHT   = 300;

    private static final int SPOT_STEP_SMALL  = 50;
    private static final int SPOT_STEP_LARGE  = 100;

    private static final int OFFSET_DEBRIS = 16;
    private static final int OFFSET_SMALL  = 8;
    private static final int OFFSET_SPARK  = 5;
    private static final int OFFSET_TINY   = 3;

    private byte effectType;
    public byte effectSubType;
    private int effectTimer;
    private int effectValue;
    private byte[] textBytes;

    public VisualEffect() {
        this.hidden = true;
    }
    public final void initEffect(byte type, int x, int y, int vx, int vy, int delay) {
        this.effectType = type;
        this.x = x;
        this.y = y;
        this.velocityX = vx;
        this.velocityY = vy;
        this.effectValue = delay;
        this.effectTimer = 0;
        if (type == TYPE_DELAYED_SMOKE) {
            this.effectTimer = 5;
            this.effectType = TYPE_SMOKE;
        }
        if (type == TYPE_DEBRIS && ResourceManager.randomInt(2) == 0) {
            this.effectType = TYPE_DEBRIS_SPARKS;
        }
        if (type == TYPE_SPOTLIGHT || type == TYPE_SMALL_SPOT || type == TYPE_SMALL_SPOT2) {
            this.effectSubType = SUBTYPE_GROUND;
        } else {
            this.effectSubType = SUBTYPE_AIR;
        }
        this.hidden = false;
    }
    public final void initText(String str, int x, int y, int vx, int vy) {
        this.textBytes = ResourceManager.fontHud.encodeString(str);
        this.effectType = TYPE_FLOATING_TEXT;
        this.x = x;
        this.y = y;
        this.velocityX = vx;
        this.velocityY = vy;
        this.hidden = false;
        this.effectSubType = SUBTYPE_AIR;
        this.effectTimer = 0;
    }
    public final void render(Graphics graphics) {
        if (this.effectValue > 0) {
            this.effectValue--;
        }
        switch (this.effectType) {
            case TYPE_DEBRIS:
                if (this.effectTimer == 0) {
                    AudioManager.playSfx(SFX_EXPLOSION, getFrameIndex());
                }
                if (this.effectTimer < DURATION_DEBRIS) {
                    ResourceManager.shotSprite.drawFrame(this.effectTimer, this.x - OFFSET_DEBRIS, this.y - OFFSET_DEBRIS, 0, graphics);
                    this.effectTimer++;
                }
                if (this.effectTimer >= DURATION_DEBRIS) {
                    this.hidden = true;
                }
                break;
            case TYPE_SMALL_EXP:
                if (this.effectTimer == 0) {
                    AudioManager.playSfx(SFX_SHOOT, getFrameIndex());
                }
                if (this.effectTimer < DURATION_SMALL_EXP) {
                    ResourceManager.smallExplosionSprite.drawFrame(this.effectTimer, this.x - OFFSET_SMALL, this.y - OFFSET_SMALL, 0, graphics);
                    this.effectTimer++;
                }
                if (this.effectTimer >= DURATION_SMALL_EXP) {
                    this.hidden = true;
                }
                break;
            case 2:
                if (this.effectTimer >= DURATION_SPARK) {
                    this.hidden = true;
                } else {
                    applyVelocity();
                    ResourceManager.sparkSprite.drawFrame(this.effectTimer, this.x - OFFSET_SPARK, this.y - OFFSET_SPARK, 0, graphics);
                    this.effectTimer++;
                }
                break;
            case 3:
                if (this.effectTimer >= DURATION_SMOKE) {
                    this.hidden = true;
                } else {
                    applyVelocity();
                    ResourceManager.sparkSprite.drawFrame(this.effectTimer, this.x - OFFSET_SPARK, this.y - OFFSET_SPARK, 0, graphics);
                    this.effectTimer++;
                }
                break;
            case TYPE_BIG_EXP:
                if (this.effectTimer % 4 == 0) {
                    this.velocityX = ResourceManager.randomInt(1024);
                    this.velocityY = ResourceManager.randomInt(1024);
                }
                if (this.effectTimer % 3 == 0) {
                    GameLevel.spawnEffect(TYPE_SMOKE, this.x, this.y, this.velocityX, this.velocityY, 0);
                }
                this.effectTimer++;
                if (this.effectTimer >= DURATION_BIG_EXP) {
                    this.hidden = true;
                }
                break;
            case TYPE_SPOTLIGHT:
                ResourceManager.spotSprite.drawFrame(this.effectTimer / SPOT_STEP_LARGE, this.x - OFFSET_DEBRIS, this.y - OFFSET_DEBRIS, 0, graphics);
                this.effectTimer++;
                if (this.effectTimer >= DURATION_SPOTLIGHT) {
                    this.hidden = true;
                }
                break;
            case TYPE_FLOATING_TEXT:
                ResourceManager.fontHud.drawEncoded(this.textBytes, this.x, this.y, 0, graphics);
                applyVelocity();
                this.effectTimer++;
                if (this.effectTimer >= DURATION_FLOAT_TEXT) {
                    this.hidden = true;
                }
                break;
            case 8:
                ResourceManager.spotSmallSprite.drawFrame(this.effectTimer / SPOT_STEP_SMALL, this.x, this.y, 0, graphics);
                this.effectTimer++;
                if (this.effectTimer >= DURATION_SMALL_SPOT) {
                    this.hidden = true;
                }
                break;
            case 9:
                ResourceManager.spotSmallSprite.drawFrame(2 + (this.effectTimer / SPOT_STEP_SMALL), this.x, this.y, 0, graphics);
                this.effectTimer++;
                if (this.effectTimer >= DURATION_SMALL_SPOT) {
                    this.hidden = true;
                }
                break;
            case 10:
                if (this.effectTimer >= DURATION_SPARK) {
                    this.hidden = true;
                } else {
                    applyVelocity();
                    ResourceManager.explosionSprite.drawFrame(this.effectTimer, this.x - OFFSET_TINY, this.y - OFFSET_TINY, 0, graphics);
                    this.effectTimer++;
                }
                break;
            case TYPE_DEBRIS_SPARKS:
                if (this.effectTimer == 0) {
                    AudioManager.playSfx(SFX_EXPLOSION, getFrameIndex());
                }
                if (this.effectTimer < DURATION_DEBRIS) {
                    ResourceManager.shotSprite.drawFrame(this.effectTimer, this.x - OFFSET_DEBRIS, this.y - OFFSET_DEBRIS, 0, graphics);
                    if (this.effectTimer % 3 == 0) {
                        GameLevel.spawnEffect(TYPE_SPARK, this.x + (this.effectTimer << 1), this.y + this.effectTimer, 0, 0, 0);
                        GameLevel.spawnEffect(TYPE_SPARK, this.x - (this.effectTimer * 3), this.y + this.effectTimer, 0, 0, 0);
                        GameLevel.spawnEffect(TYPE_SPARK, this.x + this.effectTimer, this.y - (this.effectTimer << 1), 0, 0, 0);
                    }
                    this.effectTimer++;
                }
                if (this.effectTimer >= DURATION_DEBRIS) {
                    this.hidden = true;
                }
                break;
        }
    }
    private int getFrameIndex() {
        return VOLUME_BASE - ((Math.abs((this.x - GameLevel.cameraX) - (GameLevel.viewportWidth >> 1)) + Math.abs((this.y - GameLevel.cameraY) - (GameLevel.viewportHeight >> 1))) / 2);
    }
}
