package xforce.screen;

import xforce.game.GameCanvas;
import xforce.resource.ResourceManager;

import javax.microedition.lcdui.Graphics;
public abstract class GameScreen {

    private static final int SCANLINE_SCREEN_W = 240;
    private static final int SCANLINE_SCREEN_H = 320;

    private static final int TITLE_SMALL_THRESHOLD = 220;
    private static final int TITLE_Y_SMALL   = 8;
    private static final int SUBTITLE_Y_SMALL = 26;
    private static final int TITLE_Y_LARGE   = 18;
    private static final int SUBTITLE_Y_LARGE = 40;

    public static boolean[] inputState = new boolean[8];
    public static int pointerX;
    public static int pointerY;
    public static int pointerState;
    public String softLeftLabel;
    public String softRightLabel;
    public String title;
    public String subtitle;
    public abstract void paint(Graphics graphics);
    public void onKeyPressed(int i) {
    }
    public void update() {
    }
    public static void resetInput() {
        for (int i = 0; i < inputState.length; i++) {
            inputState[i] = false;
        }
    }
    public static void drawScanlines(Graphics graphics) {
        graphics.setColor(0);
        for (int i = 0; i < SCANLINE_SCREEN_H; i += 2) {
            graphics.drawLine(0, i, SCANLINE_SCREEN_W, i);
        }
    }
    public final void drawTitle(Graphics graphics) {
        if (GameCanvas.screenHeight <= TITLE_SMALL_THRESHOLD) {
            if (this.title != null) {
                ResourceManager.fontLarge.drawString(this.title, 8, TITLE_Y_SMALL, 0, graphics);
            }
            if (this.subtitle != null) {
                ResourceManager.fontSmall.drawString(this.subtitle, 8, SUBTITLE_Y_SMALL, 0, graphics);
                return;
            }
            return;
        }
        if (this.title != null) {
            ResourceManager.fontLarge.drawString(this.title, 8, TITLE_Y_LARGE, 0, graphics);
        }
        if (this.subtitle != null) {
            ResourceManager.fontMedium.drawString(this.subtitle, 8, SUBTITLE_Y_LARGE, 0, graphics);
        }
    }
    public void show() {
        resetInput();
        GameCanvas.currentScreen = this;
    }
}
