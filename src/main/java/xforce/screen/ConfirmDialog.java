package xforce.screen;

import xforce.audio.AudioManager;
import xforce.game.GameCanvas;
import xforce.game.GameLevel;
import xforce.game.XMIDlet;
import xforce.resource.Localization;
import xforce.resource.ResourceManager;

import javax.microedition.lcdui.Graphics;

public final class ConfirmDialog extends GameScreen {

    private static final int DIALOG_HEIGHT  = 105;
    private static final int BUTTON_W       = 40;
    private static final int BUTTON_H       = 14;
    private static final int TEXT_Y_OFFSET  = 20;
    private static final int BUTTON_Y_OFFSET = 60;
    private static final int COLOR_HIGHLIGHT = 12615936;
    private static final int COLOR_BLACK     = 0;

    private boolean firstPaint;
    private int centerX;
    private int centerY;
    private String message;
    private byte[] encodedMessage;
    private boolean yesSelected;

    @Override
    public final void paint(Graphics g) {
        this.centerX = GameCanvas.screenWidth >> 1;
        this.centerY = (GameCanvas.screenHeight - DIALOG_HEIGHT) >> 1;
        g.setColor(COLOR_BLACK);
        if (this.firstPaint) {
            GameScreen.drawScanlines(g);
            this.firstPaint = false;
        }
        g.fillRect(0, this.centerY, GameCanvas.screenWidth, DIALOG_HEIGHT);
        (GameCanvas.screenWidth >= 240 ? ResourceManager.fontMedium : ResourceManager.fontSmall).drawEncoded(this.encodedMessage, this.centerX, this.centerY + TEXT_Y_OFFSET, 2, g);
        g.setColor(COLOR_HIGHLIGHT);
        g.fillRect(this.yesSelected ? this.centerX - BUTTON_W : this.centerX, this.centerY + BUTTON_Y_OFFSET, BUTTON_W, BUTTON_H);
        ResourceManager.fontMedium.drawString(Localization.txtYes, this.centerX - 20, this.centerY + BUTTON_Y_OFFSET, 2, g);
        ResourceManager.fontMedium.drawString(Localization.txtNo, this.centerX + 20, this.centerY + BUTTON_Y_OFFSET, 2, g);
    }

    @Override
    public final void update() {
        if (GameScreen.inputState[2]) {
            this.yesSelected = true;
        }
        if (GameScreen.inputState[3]) {
            this.yesSelected = false;
        }
        if (GameScreen.inputState[4] || GameScreen.inputState[6]) {
            confirmChoice();
        }
        GameScreen.resetInput();
        if (GameScreen.pointerState == 1) {
            if (GameScreen.pointerX > 80 && GameScreen.pointerX < 120 && GameScreen.pointerY > this.centerY + 60 && GameScreen.pointerY < this.centerY + 74) {
                this.yesSelected = true;
            }
            if (GameScreen.pointerX > 120 && GameScreen.pointerX < 160 && GameScreen.pointerY > this.centerY + 60 && GameScreen.pointerY < this.centerY + 74) {
                this.yesSelected = false;
            }
            GameScreen.pointerState = 2;
        }
        if (GameScreen.pointerState == 3) {
            if ((GameScreen.pointerX > 80 && GameScreen.pointerX < 120 && GameScreen.pointerY > this.centerY + 60 && GameScreen.pointerY < this.centerY + 74) || (GameScreen.pointerX > 120 && GameScreen.pointerX < 160 && GameScreen.pointerY > this.centerY + 60 && GameScreen.pointerY < this.centerY + 74)) {
                confirmChoice();
            }
            GameScreen.pointerState = 0;
        }
    }

    private void confirmChoice() {
        if (this.message == Localization.txtSoundQuestion) {
            if (this.yesSelected) {
                AudioManager.musicVolume = 3;
                AudioManager.sfxVolume = 3;
                AudioManager.loadMusic("/menu.mid");
            } else {
                AudioManager.musicVolume = 0;
                AudioManager.sfxVolume = 0;
            }
            GameCanvas.mainMenu.show();
            return;
        }
        if (this.message == Localization.txtExitQuestion) {
            if (this.yesSelected) {
                XMIDlet.instance.m0a();
                return;
            } else {
                GameCanvas.mainMenu.show();
                return;
            }
        }
        if (this.message == Localization.txtNewGameQuestion) {
            if (this.yesSelected) {
                new NameInputScreen().show();
                return;
            } else {
                GameCanvas.mainMenu.show();
                return;
            }
        }
        if (this.message == Localization.txtGoHomeQuestion) {
            if (!this.yesSelected) {
                GameCanvas.mainMenu.show();
            } else {
                GameLevel.missionComplete = false;
                GameCanvas.gameLevel.handleGameOver();
            }
        }
    }

    public final void showDialog(String message, boolean defaultYes) {
        this.firstPaint = true;
        this.message = message;
        this.encodedMessage = ResourceManager.fontSmall.encodeString(message);
        this.yesSelected = defaultYes;
        show();
    }
}
