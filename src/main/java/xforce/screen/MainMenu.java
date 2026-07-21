package xforce.screen;

import xforce.game.GameCanvas;
import xforce.game.GameLevel;
import xforce.resource.BitmapFont;
import xforce.resource.Localization;
import xforce.resource.ResourceManager;

import javax.microedition.lcdui.Graphics;

public final class MainMenu extends GameScreen {

    private static final int BLINK_ON  = 5;
    private static final int BLINK_OFF = 10;
    private static final int MENU_OFFSET    = 8;
    private static final int SLIDEIN_LIMIT  = -120;
    private static final int HIGHLIGHT_W    = 116;
    private static final int HIGHLIGHT_H    = 14;
    private static final int COLOR_HIGHLIGHT = 12615936;

    private int menuLevel;
    private int selectedItem;
    public static int dialogState;
    private int menuOffset;
    private int menuSpacing;
    private int menuSlideX;
    private int[] menuSlideOffsets;
    private int[] menuItemY;
    private int menuOpenTimer;
    private int logoFrameIndex;
    private int logoState;
    private int highlightY;
    private int blinkCounter;
    private BitmapFont highlightFont;
    private int[] logoFrameWidths = {22, 36, 52, 59, 75, 91, 109};
    private boolean isSplashScreen = true;

    public MainMenu() {
        if (GameCanvas.screenWidth >= 240) {
            this.highlightFont = ResourceManager.fontLarge;
        } else {
            this.highlightFont = ResourceManager.fontMedium;
        }
        this.menuSlideX = 18;
        if (GameCanvas.screenHeight < 200) {
            this.menuSlideX = 16;
        }
        if (GameCanvas.screenWidth >= 240) {
            this.menuSlideX = 24;
        }
    }
    public final void switchMenu(int menuLevel, int selectedItem) {
        this.menuLevel = menuLevel;
        this.selectedItem = selectedItem;
        int i3 = 1;
        this.menuOffset = MENU_OFFSET;
        this.menuItemY = new int[Localization.menuTexts[this.menuLevel].length];
        this.menuItemY[0] = this.menuOffset;
        while (this.menuItemY[0] > SLIDEIN_LIMIT) {
            i3 <<= 1;
            int[] iArr = this.menuItemY;
            iArr[0] = iArr[0] - i3;
        }
        for (int i4 = 1; i4 < Localization.menuTexts[this.menuLevel].length; i4++) {
            this.menuItemY[i4] = this.menuItemY[i4 - 1] - i3;
            i3 <<= 1;
        }
        this.menuSpacing = ((GameCanvas.screenHeight - (Localization.menuTexts[this.menuLevel].length * this.menuSlideX)) * 3) / 4;
        this.menuSlideOffsets = new int[Localization.menuTexts[this.menuLevel].length];
        for (int i5 = 0; i5 < Localization.menuTexts[this.menuLevel].length; i5++) {
            this.menuSlideOffsets[i5] = this.menuSpacing;
        }
        if (this.menuLevel == Localization.MENU_CAREER) {
            this.title = Localization.txtCareer;
            this.subtitle = Localization.lblXp + GameLevel.xp + " " + Localization.lblCash + GameLevel.cash + "$";
        }
    }

    @Override
    public final void paint(Graphics graphics) {
        if (this.isSplashScreen) {
            graphics.drawImage(ResourceManager.backgroundImage, 0, 0, 0);
            if (this.blinkCounter < BLINK_ON) {
                ResourceManager.fontHud.drawString(Localization.txtPressAnyKey, GameCanvas.screenWidth >> 1, GameCanvas.screenHeight - 20, 2, graphics);
            }
            this.blinkCounter++;
            if (this.blinkCounter >= BLINK_OFF) {
                this.blinkCounter = 0;
                return;
            }
            return;
        }
        graphics.drawImage(ResourceManager.backgroundImage, 0, 0, 0);
        graphics.drawImage(ResourceManager.garagePreviewImage, GameCanvas.screenWidth >> 1, GameCanvas.screenHeight >> 1, 0);
        if (this.menuLevel == 0) {
            this.logoState++;
            if ((this.logoFrameIndex < this.logoFrameWidths.length - 2 && this.logoState > 1) || this.logoState > 5) {
                this.logoState = 0;
                if (this.logoFrameIndex < this.logoFrameWidths.length - 1) {
                    this.logoFrameIndex++;
                } else {
                    this.logoFrameIndex--;
                }
            }
            graphics.setClip(8, 20, this.logoFrameWidths[this.logoFrameIndex], 22);
            graphics.drawImage(ResourceManager.xfLogo, 8, 20, 0);
            graphics.setClip(0, 0, GameCanvas.screenWidth, GameCanvas.screenHeight);
        } else {
            drawTitle(graphics);
        }
        for (int i = 0; i < Localization.menuTexts[this.menuLevel].length; i++) {
            if (this.menuItemY[i] < this.menuOffset) {
                int[] iArr = this.menuItemY;
                int i2 = i;
                iArr[i2] = iArr[i2] + ((this.menuOffset - this.menuItemY[i]) >> 1);
            }
        }
        graphics.setColor(COLOR_HIGHLIGHT);
        if (this.selectedItem > -1) {
            this.highlightY += (((this.menuSpacing + 1) + (this.selectedItem * this.menuSlideX)) - this.highlightY) >> 1;
            graphics.fillRect(this.menuOffset - 4, this.highlightY, HIGHLIGHT_W, HIGHLIGHT_H);
        }
        int i3 = this.menuSpacing;
        for (int i4 = 0; i4 < Localization.menuTexts[this.menuLevel].length; i4++) {
            this.highlightFont.drawString(Localization.menuTexts[this.menuLevel][i4], this.menuItemY[i4], i3, 0, graphics);
            i3 += this.menuSlideX;
        }
        if (this.menuOpenTimer < 5) {
            this.menuOpenTimer++;
        }
    }

    @Override
    public final void update() {
        if (this.isSplashScreen) {
            if (GameScreen.pointerState == 3) {
                onKeyPressed(0);
                GameScreen.pointerState = 0;
                return;
            }
            return;
        }
        if (GameScreen.inputState[0] && this.selectedItem > 0) {
            this.selectedItem--;
            if (Localization.menuTexts[this.menuLevel][this.selectedItem] == "") {
                this.selectedItem--;
            }
        }
        if (GameScreen.inputState[1] && this.selectedItem < Localization.menuTexts[this.menuLevel].length - 1) {
            this.selectedItem++;
            if (Localization.menuTexts[this.menuLevel][this.selectedItem] == "") {
                this.selectedItem++;
            }
        }
        if (GameScreen.inputState[4] || GameScreen.inputState[6]) {
            handleMenuSelect();
        }
        GameScreen.resetInput();
        if (GameScreen.pointerState == 1) {
            this.selectedItem = -1;
            if (GameScreen.pointerY > this.menuSpacing) {
                this.selectedItem = (GameScreen.pointerY - this.menuSpacing) / this.menuSlideX;
                if (this.selectedItem >= Localization.menuTexts[this.menuLevel].length || Localization.menuTexts[this.menuLevel][this.selectedItem] == "") {
                    this.selectedItem = -1;
                }
            }
            GameScreen.pointerState = 2;
        }
        if (GameScreen.pointerState == 3) {
            if (this.selectedItem != -1) {
                handleMenuSelect();
            }
            GameScreen.pointerState = 0;
        }
        if (dialogState <= 0 || DialogBox.isVisible) {
            return;
        }
        if (dialogState == 1) {
            DialogBox.setText(GameLevel.playerName + ". " + Localization.txtWelcome, 3);
            dialogState = 2;
        } else if (dialogState == 2) {
            DialogBox.setText(Localization.nickDialogue[0], 0);
            dialogState = 3;
        }
        if (dialogState == 4) {
            DialogBox.setText(Localization.xpDialogue[0], 1);
            dialogState = 0;
        }
        if (dialogState == 5) {
            DialogBox.setText(Localization.miaDialogue[0], 2);
            dialogState = 0;
        }
    }

    @Override
    public final void onKeyPressed(int i) {
        if (this.isSplashScreen) {
            if (GameCanvas.screenWidth >= 240) {
                ResourceManager.backgroundImage = ResourceManager.loadImage("/bgab.png");
            } else {
                ResourceManager.backgroundImage = ResourceManager.loadImage("/bga.png");
            }
            this.isSplashScreen = false;
            switchMenu(0, 0);
            GameScreen.resetInput();
        }
    }
    private void handleMenuSelect() {
        if (this.menuLevel == 0) {
            switch (this.selectedItem) {
                case 0:
                    if (GameLevel.xp != 0) {
                        switchMenu(Localization.MENU_CAREER, 0);
                    } else {
                        new NameInputScreen().show();
                    }
                    break;
                case 1:
                    GameCanvas.confirmDialog.showDialog(Localization.txtNewGameQuestion, false);
                    break;
                case 2:
                    GameCanvas.optionsScreen.show();
                    break;
                case 3:
                    GameCanvas.creditsScreen.show();
                    break;
                case 4:
                    GameCanvas.creditsScreen.showHelp();
                    break;
                case 5:
                    GameCanvas.confirmDialog.showDialog(Localization.txtExitQuestion, false);
                    break;
            }
        }
        if (this.menuLevel == Localization.MENU_CAREER) {
            switch (this.selectedItem) {
                case 0:
                    GameCanvas.worldMap.show();
                    break;
                case 1:
                    GameCanvas.shopScreen.show();
                    break;
                case 2:
                    GameCanvas.garageScreen.show();
                    break;
                case 3:
                    GameCanvas.infoScreen.show();
                    break;
                case 5:
                    switchMenu(0, 0);
                    break;
            }
        }
        if (this.menuLevel == Localization.MENU_INSTRUCTIONS) {
            switch (this.selectedItem) {
                case 0:
                    GameCanvas.gameLevel.show();
                    break;
                case 2:
                    GameCanvas.gameLevel.show();
                    break;
                case 3:
                    GameCanvas.optionsScreen.show();
                    break;
                case 4:
                    GameCanvas.confirmDialog.showDialog(Localization.txtGoHomeQuestion, false);
                    break;
            }
        }
    }

    @Override
    public final void show() {
        super.show();
        if (ResourceManager.garagePreviewImage == null) {
            GarageScreen.refreshGarage();
        }
        if (this.isSplashScreen) {
            if (GameCanvas.screenWidth >= 240) {
                ResourceManager.backgroundImage = ResourceManager.loadImage("/bgb.png");
            } else {
                ResourceManager.backgroundImage = ResourceManager.loadImage("/bg.png");
            }
        }
        this.logoFrameIndex = 0;
        int i = 1;
        this.menuOffset = MENU_OFFSET;
        this.menuItemY = new int[Localization.menuTexts[this.menuLevel].length];
        this.menuItemY[0] = this.menuOffset;
        while (this.menuItemY[0] > SLIDEIN_LIMIT) {
            i <<= 1;
            int[] iArr = this.menuItemY;
            iArr[0] = iArr[0] - i;
        }
        for (int i2 = 1; i2 < Localization.menuTexts[this.menuLevel].length; i2++) {
            this.menuItemY[i2] = this.menuItemY[i2 - 1] - i;
            i <<= 1;
        }
        if (this.menuLevel == Localization.MENU_CAREER) {
            this.title = Localization.txtCareer;
            this.subtitle = Localization.lblXp + GameLevel.xp + " " + Localization.lblCash + GameLevel.cash + "$";
        }
    }
}
