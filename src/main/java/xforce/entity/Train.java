package xforce.entity;

import xforce.game.GameLevel;
import xforce.map.MapRenderer;
import xforce.resource.ResourceManager;

import javax.microedition.lcdui.Graphics;
public final class Train extends Sprite {

    private static final byte SPRITE_TYPE_TRAIN = 15;
    private static final int TRAIN_WIDTH        = 20;
    private static final int TRAIN_HEIGHT       = 100;
    private static final int CAR_COUNT          = 4;
    private static final int CAR_Y_OFFSET       = 50;
    private static final int CAR_SPACING        = 37;
    private static final int TRAIN_TILE_X       = 5;
    private static final int TRAIN_TILE_Y_START = 53;
    private static final int TRACK_ROW_START    = 47;
    private static final int TRACK_ROW_END      = 60;
    private static final int ARRIVAL_TILE_Y     = 6;

    private static final int ANIM_STOPPED_RATE  = 5;
    private static final int ANIM_MOVING_RATE   = 3;

    private static final byte EFFECT_SMOKE_TRAIN = 10;

    private static final int SPRITE_CENTER = 12;

    private int animCounter;

    public Train() {
        super((TRAIN_TILE_X * MapRenderer.tileSize) + 2, TRAIN_TILE_Y_START * MapRenderer.tileSize, SPRITE_TYPE_TRAIN);
        this.width = TRAIN_WIDTH;
        this.height = TRAIN_HEIGHT;
        this.velocityY = 0;
        this.velocityX = 0;
        ResourceManager.trainImage = ResourceManager.loadImage("/train.png");
        ResourceManager.loadImage("/train1.png");
        ResourceManager.trainDestroyedImage = ResourceManager.loadImage("/train2.png");
        for (int row = TRACK_ROW_START; row < TRACK_ROW_END; row++) {
            MapRenderer.tileHp[row][TRAIN_TILE_X] = 1;
        }
    }

    @Override
    public final void draw(Graphics graphics) {
        int baseY = this.y;
        graphics.drawImage(ResourceManager.trainImage, this.x, baseY, 0);
        int carY = baseY + CAR_Y_OFFSET;
        for (int carIndex = 0; carIndex < CAR_COUNT; carIndex++) {
            if (this.velocityY == 0 || this.animCounter != 0) {
                graphics.drawImage(ResourceManager.trainDestroyedImage, this.x, carY, 0);
            } else {
                graphics.drawImage(ResourceManager.trainDestroyedImage, this.x, carY + ResourceManager.randomInt(2), 0);
            }
            carY += CAR_SPACING;
        }
        if (this.animCounter == 0) {
            GameLevel.spawnEffect(EFFECT_SMOKE_TRAIN, this.x + 8, this.y + 8, ResourceManager.randomInt(512), 1024, 0);
        }
    }

    @Override
    public final void update() {
        this.animCounter++;
        if (this.velocityY == 0) {
            if (this.animCounter >= ANIM_STOPPED_RATE) {
                this.animCounter = 0;
                return;
            }
            return;
        }
        applyVelocity();
        if (this.animCounter >= ANIM_MOVING_RATE) {
            this.animCounter = 0;
        }
        if (this.y < ARRIVAL_TILE_Y * MapRenderer.tileSize && GameLevel.gameOverTimer == 0) {
            GameLevel.triggerGameOver(false);
        }
        for (int row = 0; row < TRACK_ROW_END; row++) {
            MapRenderer.tileHp[row][TRAIN_TILE_X] = 1;
        }
    }

    @Override
    public final void onDeath() {
        ResourceManager.trainImage = ResourceManager.loadImage("/traind.png");
        this.velocityY = 0;
        GameLevel.spawnPlayerDeath(this.x + SPRITE_CENTER, this.y + SPRITE_CENTER);
        GameLevel.triggerGameOver(true);
    }

    @Override
    public final void followCamera() {
        GameLevel.setCameraTarget(this.x + SPRITE_CENTER, this.y + SPRITE_CENTER);
    }
}
