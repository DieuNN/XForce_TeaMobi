package xforce.screen;

import xforce.game.GameCanvas;
import xforce.game.GameLevel;
import xforce.resource.BitmapFont;
import xforce.resource.Localization;
import xforce.resource.ResourceManager;

import javax.microedition.lcdui.Graphics;

public final class PauseMenu extends GameScreen {
    private boolean firstPaint;
    private int menuW;
    private int menuH;
    private int menuX;
    private int menuY;
    private int menuCursor = 0;
    private BitmapFont menuFont;

    public PauseMenu() {
        if (GameCanvas.screenWidth >= 240) {
            this.menuW = 120;
            this.menuH = 24;
            this.menuFont = ResourceManager.fontLarge;
        } else {
            this.menuW = 105;
            this.menuH = 18;
            this.menuFont = ResourceManager.fontMedium;
        }
        this.menuX = (GameCanvas.screenHeight - this.menuW) >> 1;
        this.menuY = (GameCanvas.screenHeight - (Localization.pauseLabels.length * this.menuH)) >> 1;
    }

    @Override
    public final void paint(Graphics graphics) {
        if (this.firstPaint) {
            GameScreen.drawScanlines(graphics);
            this.firstPaint = false;
        }
        graphics.setColor(0);
        graphics.fillRect(0, this.menuX, GameCanvas.screenWidth, this.menuW);
        graphics.setColor(12615936);
        graphics.fillRect((GameCanvas.screenWidth - 120) >> 1, this.menuY + 1 + (this.menuCursor * this.menuH), 120, 14);
        int i = this.menuY;
        for (int i2 = 0; i2 < Localization.pauseLabels.length; i2++) {
            this.menuFont.drawString(Localization.pauseLabels[i2], GameCanvas.screenWidth >> 1, i, 2, graphics);
            i += this.menuH;
        }
    }

    @Override
    public final void update() {
        if (GameScreen.pointerState == 1) {
            this.menuCursor = -1;
            if (GameScreen.pointerY > this.menuY) {
                this.menuCursor = (GameScreen.pointerY - this.menuY) / this.menuH;
                if (this.menuCursor >= Localization.pauseLabels.length || Localization.pauseLabels[this.menuCursor] == "") {
                    this.menuCursor = -1;
                }
            }
            GameScreen.pointerState = 2;
        }
        if (GameScreen.pointerState == 3) {
            if (this.menuCursor != -1) {
                handleSelection();
            }
            GameScreen.pointerState = 0;
        }
    }

    @Override
    public final void onKeyPressed(int i) {
        if (GameScreen.inputState[0] && this.menuCursor > 0) {
            this.menuCursor--;
        }
        if (GameScreen.inputState[1] && this.menuCursor < Localization.pauseLabels.length - 1) {
            this.menuCursor++;
        }
        if (GameScreen.inputState[4]) {
            handleSelection();
        }
    }
    private void handleSelection() {
        switch (this.menuCursor) {
            case 0:
                GameCanvas.gameLevel.show();
                break;
            case 1:
                GameLevel.loadingScreen = true;
                GameCanvas.gameLevel.show();
                break;
            case 2:
                GameCanvas.optionsScreen.show();
                break;
            case 3:
                GameCanvas.confirmDialog.showDialog(Localization.txtGoHomeQuestion, false);
                break;
        }
    }

    @Override
    public final void show() {
        this.firstPaint = true;
        this.menuCursor = 0;
        GameCanvas.currentScreen = this;
    }
}
