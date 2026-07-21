package xforce.resource;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class SpriteSheet {

    private int frameWidth;
    private int frameHeight;
    private int frameCount;
    private Image spritesheet;
    private int[] frameOffsets;
    private int imageHeight;

    public SpriteSheet(Image image, int frameWidth, int frameHeight) {
        this.spritesheet = image;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.imageHeight = image.getHeight();
        this.frameCount = this.imageHeight / frameHeight;
        this.frameOffsets = new int[this.frameCount];
        for (int i = 0; i < this.frameCount; i++) {
            this.frameOffsets[i] = i * frameHeight;
        }
    }

    public final void drawFrame(int frameIndex, int x, int y, int transform, Graphics graphics) {
        if (frameIndex < 0 || frameIndex >= this.frameCount) {
            return;
        }
        graphics.drawRegion(this.spritesheet, 0, this.frameOffsets[frameIndex], this.frameWidth, this.frameHeight, transform, x, y, 0);
    }

    public final void dispose() {
        this.spritesheet = null;
        this.frameOffsets = null;
    }
}
