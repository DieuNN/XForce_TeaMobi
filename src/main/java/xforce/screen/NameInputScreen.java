package xforce.screen;

import xforce.game.GameCanvas;
import xforce.game.GameLevel;
import xforce.resource.BitmapFont;
import xforce.resource.Localization;
import xforce.resource.ResourceManager;

import javax.microedition.lcdui.Graphics;

public final class NameInputScreen extends GameScreen {
    private int cursorRow;
    private int cursorCol;
    private String playerName;
    private int nameLength;
    private char[][] keyboard = {new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'}, new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'}, new char[]{'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T'}, new char[]{'U', 'V', 'W', 'X', 'Y', 'Z', ' ', '+', '-', '$'}};
    private int kbOffsetX = (GameCanvas.screenWidth - 120) >> 1;
    private int kbOffsetY = (GameCanvas.screenHeight - 48) >> 1;
    private int kbCellW = 12;
    private int kbCellH = 12;

    public NameInputScreen() {
        int i = this.kbOffsetX;
        BitmapFont font = ResourceManager.fontSmall;
        String str = Localization.lblName;
        this.nameLength = i + font.measureString(str, 0, str.length());
        this.playerName = "PLAYER";
    }

    @Override
    public final void paint(Graphics graphics) {
        graphics.drawImage(ResourceManager.backgroundImage, 0, 0, 0);
        for (int i = 0; i < 4; i++) {
            for (int i2 = 0; i2 < 10; i2++) {
                ResourceManager.fontHud.drawChar(this.keyboard[i][i2], this.kbOffsetX + (i2 * this.kbCellW), this.kbOffsetY + (i * this.kbCellH), graphics);
            }
        }
        graphics.setColor(65280);
        if (this.cursorRow >= 0 && this.cursorCol >= 0) {
            graphics.drawRect((this.kbOffsetX + (this.cursorCol * this.kbCellW)) - 2, (this.kbOffsetY + (this.cursorRow * this.kbCellH)) - 2, ResourceManager.fontHud.getCharWidth(this.keyboard[this.cursorRow][this.cursorCol]) + 3, 12);
        }
        ResourceManager.fontSmall.drawString(Localization.lblName, this.kbOffsetX, this.kbOffsetY - 25, 0, graphics);
        BitmapFont font2 = ResourceManager.fontHud;
        String str = this.playerName;
        int i3 = this.nameLength;
        int i4 = this.kbOffsetY - 20;
        
        font2.drawString(str, i3, i4, 0, graphics);
    }
    private void inputDone() {
        if (this.cursorRow < 0 || this.cursorCol < 0) {
            return;
        }
        this.playerName = this.playerName + this.keyboard[this.cursorRow][this.cursorCol];
    }

    @Override
    public final void update() {
        if (GameScreen.inputState[0]) {
            this.cursorRow = (this.cursorRow + 3) % 4;
        }
        if (GameScreen.inputState[1]) {
            this.cursorRow = (this.cursorRow + 1) % 4;
        }
        if (GameScreen.inputState[2]) {
            this.cursorCol = (this.cursorCol + 9) % 10;
        }
        if (GameScreen.inputState[3]) {
            this.cursorCol = (this.cursorCol + 1) % 10;
        }
        if (GameScreen.inputState[4] && this.playerName.length() < 15) {
            inputDone();
        }
        if (GameScreen.inputState[7] && this.playerName.length() > 0) {
            this.playerName = this.playerName.substring(0, this.playerName.length() - 1);
        }
        if (GameScreen.inputState[6] && this.playerName.length() > 0) {
            GameLevel.playerName = this.playerName;
            GameLevel.xp = 100;
            GameLevel.cash = 1000;
            GameLevel.currentVehicle = 0;
            byte[] bArr = new byte[5];
            bArr[3] = 60;
            GameLevel.vehicleUpgrades = new byte[][]{bArr, new byte[]{-1, 0, 0, 60, 0}, new byte[]{-1, 0, 0, 60, 0}};
            for (int i = 0; i < GameLevel.missionFlags.length; i++) {
                GameLevel.missionFlags[i] = false;
            }
            ResourceManager.totalPlayTime = 0;
            ResourceManager.startTime = System.currentTimeMillis();
            GameCanvas.mainMenu.switchMenu(Localization.MENU_CAREER, 0);
            GameCanvas.mainMenu.show();
            MainMenu.dialogState = 1;
        }
        GameScreen.resetInput();
        if (GameScreen.pointerState == 1) {
            this.cursorRow = -1;
            if (GameScreen.pointerX > this.kbOffsetX && GameScreen.pointerY > this.kbOffsetY) {
                this.cursorCol = (GameScreen.pointerX - this.kbOffsetX) / this.kbCellW;
                if (this.cursorCol >= this.keyboard[0].length) {
                    this.cursorCol = -1;
                }
                this.cursorRow = (GameScreen.pointerY - this.kbOffsetY) / this.kbCellH;
                if (this.cursorRow >= this.keyboard.length) {
                    this.cursorRow = -1;
                }
            }
            GameScreen.pointerState = 2;
        }
        if (GameScreen.pointerState == 3) {
            inputDone();
            GameScreen.pointerState = 0;
        }
    }

    @Override
    public final void show() {
        super.show();
        this.softLeftLabel = "OK";
        this.softRightLabel = "CLEAR";
        DialogBox.setText(Localization.txtWhatsYourName, 3);
    }
}
