package xforce.entity;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class CompositeSprite {

    private static final int ANCHOR_TOP_LEFT = Graphics.TOP | Graphics.LEFT;

    private int regionCount;
    private Image sourceImage;
    private int[][] regions;

    public CompositeSprite(Image image, int[][] regions) {
        this.sourceImage = image;
        this.regions = regions;
        this.regionCount = regions.length;
    }

    public final void drawRegion(int index, int x, int y, int transform, Graphics g) {
        if (index < 0 || index >= this.regionCount) {
            return;
        }
        int[] r = this.regions[index];
        g.drawRegion(this.sourceImage, r[0], r[1], r[2], r[3], transform, x - r[4], y - r[5], ANCHOR_TOP_LEFT);
    }
}
