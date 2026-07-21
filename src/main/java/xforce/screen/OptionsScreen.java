package xforce.screen;

import xforce.audio.AudioManager;
import xforce.game.GameCanvas;
import xforce.game.GameLevel;
import xforce.game.XMIDlet;
import xforce.resource.BitmapFont;
import xforce.resource.Localization;
import xforce.resource.ResourceManager;

import javax.microedition.lcdui.Graphics;

public final class OptionsScreen extends GameScreen {

    private boolean firstShow;
    private int selectedOption;
    private int optionsCursor;
    private int subCursor;
    private int blinkTimer;
    private int mode;
    private GameScreen previousScreen;
    private int bindStep;
    private int bindIndex;
    private BitmapFont bindCursor;

    public OptionsScreen() {
        this.bindCursor = GameCanvas.screenWidth >= 240 ? ResourceManager.fontMedium : ResourceManager.fontSmall;
        if (GameCanvas.screenWidth >= 240) {
            this.bindCursor = ResourceManager.fontMedium;
            this.selectedOption = 120;
            this.bindStep = 16;
        } else {
            this.bindCursor = ResourceManager.fontSmall;
            this.selectedOption = 105;
            this.bindStep = 14;
        }
        this.optionsCursor = 8;
        this.subCursor = (GameCanvas.screenHeight - this.selectedOption) >> 1;
        this.blinkTimer = (GameCanvas.screenHeight - (Localization.optionsTexts[0].length * this.bindStep)) >> 1;
    }

    @Override
    public final void paint(Graphics g) {
        g.setColor(0);
        if (this.firstShow) {
            GameScreen.drawScanlines(g);
            this.firstShow = false;
        }
        g.fillRect(0, this.subCursor, GameCanvas.screenWidth, this.selectedOption);
        g.setColor(12615936);
        if (this.mode > -1) {
            g.fillRect(this.optionsCursor - 4, this.blinkTimer + 1 + (this.mode * this.bindStep), 120, 12);
        }
        int y = this.blinkTimer;
        if (this.bindIndex == 0) {
            this.bindCursor.drawString(Localization.optionsTexts[0][0] + "<" + AudioManager.musicVolume + ">", this.optionsCursor, y, 0, g);
            y += this.bindStep;
            this.bindCursor.drawString(Localization.optionsTexts[0][1] + "<" + AudioManager.sfxVolume + ">", this.optionsCursor, y, 0, g);
            y += this.bindStep;
            this.bindCursor.drawString(Localization.optionsTexts[0][2] + (XMIDlet.vibrationEnabled ? Localization.txtOn : Localization.txtOff), this.optionsCursor, y, 0, g);
            y += this.bindStep;
            this.bindCursor.drawString(Localization.optionsTexts[0][3], this.optionsCursor, y, 0, g);
            y += this.bindStep;
            this.bindCursor.drawString(Localization.optionsTexts[0][4] + Localization.languageNames[Localization.language], this.optionsCursor, y, 0, g);
            this.bindCursor.drawString(Localization.optionsTexts[0][5], this.optionsCursor, y + this.bindStep, 0, g);
            return;
        }
        if (this.bindIndex == 1) {
            String fireLabel = GameCanvas.keyBindingMode == 1 ? Localization.txtPress : (GameCanvas.fireKey == 0 ? Localization.txtNa : keyName(GameCanvas.fireKey));
            this.bindCursor.drawString(Localization.optionsTexts[1][0] + fireLabel, this.optionsCursor, y, 0, g);
            y += this.bindStep;

            String dynamicLabel = GameCanvas.keyBindingMode == 2 ? Localization.txtPress : (GameCanvas.dynamicKey == 0 ? Localization.txtNa : keyName(GameCanvas.dynamicKey));
            this.bindCursor.drawString(Localization.optionsTexts[1][1] + dynamicLabel, this.optionsCursor, y, 0, g);
            y += this.bindStep;

            String mineLabel = GameCanvas.keyBindingMode == 3 ? Localization.txtPress : (GameCanvas.mineKey == 0 ? Localization.txtNa : keyName(GameCanvas.mineKey));
            this.bindCursor.drawString(Localization.optionsTexts[1][2] + mineLabel, this.optionsCursor, y, 0, g);
            y += this.bindStep;

            this.bindCursor.drawString(Localization.optionsTexts[1][3] + (GameLevel.autoShoot ? Localization.txtOn : Localization.txtOff), this.optionsCursor, y, 0, g);
            this.bindCursor.drawString(Localization.optionsTexts[1][4], this.optionsCursor, y + this.bindStep, 0, g);
        }
    }

    @Override
    public final void update() {
        if (GameScreen.inputState[0] && this.mode > 0) {
            this.mode--;
        }
        if (GameScreen.inputState[1] && this.mode < Localization.optionsTexts[this.bindIndex].length - 1) {
            this.mode++;
        }
        if (GameScreen.inputState[4] || GameScreen.inputState[6]) {
            optionsSave();
        }
        if (GameScreen.inputState[2]) {
            switch (this.mode) {
                case 0:
                    if (AudioManager.musicVolume > 0) {
                        AudioManager.setMusicVolume(AudioManager.musicVolume - 1);
                    }
                    break;
                case 1:
                    if (AudioManager.sfxVolume > 0) {
                        AudioManager.setSfxVolume(AudioManager.sfxVolume - 1);
                    }
                    AudioManager.playSfxMax(0);
                    break;
            }
        }
        if (GameScreen.inputState[3]) {
            switch (this.mode) {
                case 0:
                    if (AudioManager.musicVolume < 5) {
                        AudioManager.setMusicVolume(AudioManager.musicVolume + 1);
                    }
                    break;
                case 1:
                    if (AudioManager.sfxVolume < 5) {
                        AudioManager.setSfxVolume(AudioManager.sfxVolume + 1);
                    }
                    AudioManager.playSfxMax(0);
                    break;
            }
        }
        GameScreen.resetInput();
        if (GameScreen.pointerState == 1) {
            this.mode = -1;
            if (GameScreen.pointerY > this.blinkTimer) {
                int sel = (GameScreen.pointerY - this.blinkTimer) / this.bindStep;
                if (sel >= 0 && sel < Localization.optionsTexts[this.bindIndex].length) {
                    this.mode = sel;
                }
            }
            GameScreen.pointerState = 2;
        }
        if (GameScreen.pointerState == 3) {
            if (GameScreen.pointerY > this.blinkTimer) {
                optionsSave();
            }
            GameScreen.pointerState = 0;
        }
    }

    private void optionsSave() {
        if (this.bindIndex != 0) {
            if (this.bindIndex == 1) {
                switch (this.mode) {
                    case 0:
                        GameCanvas.keyBindingMode = 1;
                        break;
                    case 1:
                        GameCanvas.keyBindingMode = 2;
                        break;
                    case 2:
                        GameCanvas.keyBindingMode = 3;
                        break;
                    case 3:
                        GameLevel.autoShoot = !GameLevel.autoShoot;
                        break;
                    case 4:
                        this.bindIndex = 0;
                        this.mode = 3;
                        break;
                }
            }
            return;
        }
        switch (this.mode) {
            case 0:
                AudioManager.setMusicVolume((AudioManager.musicVolume + 1) % 6);
                break;
            case 1:
                AudioManager.setSfxVolume((AudioManager.sfxVolume + 1) % 6);
                AudioManager.playSfxMax(0);
                break;
            case 2:
                XMIDlet.vibrationEnabled = !XMIDlet.vibrationEnabled;
                break;
            case 3:
                this.bindIndex = 1;
                this.mode = 0;
                break;
            case 4:
                Localization.language = 1 - Localization.language;
                Localization.init();
                break;
            case 5:
                this.previousScreen.show();
                break;
        }
    }

    private static String keyName(int keyCode) {
        if (keyCode >= 48 && keyCode <= 57) {
            return "KEY" + (keyCode - 48);
        }
        return "KEY CODE " + keyCode;
    }

    @Override
    public final void show() {
        this.firstShow = true;
        this.mode = 0;
        this.previousScreen = GameCanvas.currentScreen;
        this.bindIndex = 0;
        super.show();
    }
}
