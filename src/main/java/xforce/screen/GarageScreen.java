package xforce.screen;

import xforce.game.GameCanvas;
import xforce.game.GameLevel;
import xforce.resource.Localization;
import xforce.resource.ResourceManager;

import javax.microedition.lcdui.Graphics;

public final class GarageScreen extends GameScreen {

    private int garageCursor;
    private int garageLayoutY;
    private int garageNameX;
    private static String[] vehicleImages = {"/humer_big.png", "/tank_big.png", "/heli_big.png"};
    private static byte[] garageData;
    private int garageDescY;
    private int garageBarX;
    private int garageBarY;
    private int selectedVehicle = 1;
    private int selectionMode = 1;
    private int[] vehicleXpReq = {0, 20000, 100000};
    private int[] vehiclePrice = {0, 50000, 200000};
    private String cheatBuffer = "";
    private int garageTextY = (GameCanvas.screenHeight >> 1) + 60;

    public GarageScreen() {
        if (GameCanvas.screenHeight < 200) {
            this.garageTextY -= 15;
        }
    }

    @Override
    public final void paint(Graphics g) {
        g.drawImage(ResourceManager.backgroundImage, 0, 0, 0);
        drawTitle(g);
        this.garageCursor = this.garageLayoutY;
        if (this.garageNameX < this.selectedVehicle) {
            if (this.garageLayoutY > (-GameCanvas.screenWidth)) {
                this.garageLayoutY -= this.selectionMode;
                this.selectionMode <<= 1;
            } else {
                ResourceManager.garagePreviewImage = ResourceManager.loadImage(vehicleImages[this.selectedVehicle]);
                this.selectionMode = 1;
                this.garageNameX = this.selectedVehicle;
                this.garageLayoutY = GameCanvas.screenWidth << 1;
            }
        }
        if (this.garageNameX > this.selectedVehicle) {
            if (this.garageLayoutY < (GameCanvas.screenWidth << 1)) {
                this.garageLayoutY += this.selectionMode;
                this.selectionMode <<= 1;
            } else {
                ResourceManager.garagePreviewImage = ResourceManager.loadImage(vehicleImages[this.selectedVehicle]);
                this.selectionMode = 1;
                this.garageNameX = this.selectedVehicle;
                this.garageLayoutY = -GameCanvas.screenWidth;
            }
        }
        if (this.garageNameX == this.selectedVehicle) {
            this.garageLayoutY -= (this.garageLayoutY - (GameCanvas.screenWidth >> 1)) >> 1;
        }
        g.drawImage(ResourceManager.garagePreviewImage, this.garageLayoutY, (GameCanvas.screenHeight >> 1) + 10, 3);
        if (this.garageCursor == this.garageLayoutY) {
            if (garageData == null) {
                garageData = ResourceManager.fontSmall.encodeString(Localization.vehicleDescs[this.selectedVehicle]);
                this.garageBarY = ResourceManager.fontSmall.measureByteString(garageData);
                this.garageBarX = GameCanvas.screenWidth;
            }
            ResourceManager.fontSmall.drawEncoded(garageData, this.garageBarX, this.garageTextY, 0, g);
            this.garageBarX -= 2;
            if (this.garageBarX < (-this.garageBarY)) {
                this.garageBarX = GameCanvas.screenWidth;
            }
        }
        ResourceManager.fontMedium.drawString(Localization.vehicleNames[this.selectedVehicle], GameCanvas.screenWidth >> 1, (GameCanvas.screenHeight >> 1) - 30, 2, g);
        if (GameLevel.vehicleUpgrades[this.selectedVehicle][0] == -1) {
            if (this.vehiclePrice[this.selectedVehicle] > GameLevel.xp) {
                g.drawImage(ResourceManager.lockIcon, GameCanvas.screenWidth >> 1, GameCanvas.screenHeight >> 1, 3);
                ResourceManager.fontHud.drawString("PRICE: " + this.vehicleXpReq[this.selectedVehicle] + "$\nXP: " + this.vehiclePrice[this.selectedVehicle], GameCanvas.screenWidth >> 1, (GameCanvas.screenHeight >> 1) + 20, 2, g);
            } else {
                ResourceManager.fontHud.drawString("PRICE: " + this.vehicleXpReq[this.selectedVehicle] + "$", GameCanvas.screenWidth >> 1, (GameCanvas.screenHeight >> 1) + 20, 2, g);
            }
        }
        if (this.selectedVehicle > 0) {
            ResourceManager.fontMedium.drawString("<", 4 + this.garageDescY, GameCanvas.screenHeight >> 1, 0, g);
        }
        if (this.selectedVehicle < 2) {
            ResourceManager.fontMedium.drawString(">", (GameCanvas.screenWidth - 4) - this.garageDescY, GameCanvas.screenHeight >> 1, 1, g);
        }
        if (this.garageDescY < 4) {
            this.garageDescY++;
        } else {
            this.garageDescY = 0;
        }
    }

    @Override
    public final void onKeyPressed(int keyCode) {
        if (keyCode == 48 || this.cheatBuffer.length() >= 10) {
            this.cheatBuffer = "";
        } else if (keyCode > 48) {
            this.cheatBuffer += (keyCode - 48);
        }
    }

    @Override
    public final void update() {
        if (GameScreen.pointerState == 1) {
            if (GameLevel.rectContains(GameScreen.pointerX, GameScreen.pointerY, 0, (GameCanvas.screenHeight >> 1) - 10, 20, 20)) {
                GameScreen.inputState[2] = true;
            }
            if (GameLevel.rectContains(GameScreen.pointerX, GameScreen.pointerY, GameCanvas.screenWidth - 20, (GameCanvas.screenHeight >> 1) - 10, 20, 20)) {
                GameScreen.inputState[3] = true;
            }
            GameScreen.pointerState = 2;
        }
        if (GameScreen.pointerState == 3) {
            GameScreen.pointerState = 0;
        }
        if (GameScreen.inputState[2] || GameScreen.inputState[3]) {
            if (GameScreen.inputState[2] && this.selectedVehicle > 0) {
                this.selectedVehicle--;
                garageData = null;
            }
            if (GameScreen.inputState[3] && this.selectedVehicle < 2) {
                this.selectedVehicle++;
                garageData = null;
            }
            if (GameLevel.vehicleUpgrades[this.selectedVehicle][0] != -1 || (this.vehicleXpReq[this.selectedVehicle] <= GameLevel.cash && this.vehiclePrice[this.selectedVehicle] <= GameLevel.xp)) {
                this.softLeftLabel = Localization.txtSelect;
            } else {
                this.softLeftLabel = Localization.txtBuy;
            }
        }
        if (GameScreen.inputState[6]) {
            if (GameLevel.vehicleUpgrades[this.selectedVehicle][0] != -1 || this.cheatBuffer.endsWith("11379")) {
                GameLevel.currentVehicle = this.selectedVehicle;
                GameCanvas.mainMenu.show();
            } else if (this.vehiclePrice[this.selectedVehicle] > GameLevel.xp) {
                DialogBox.setText(Localization.txtNotEnoughXp, 3);
            } else if (this.vehicleXpReq[this.selectedVehicle] > GameLevel.cash) {
                DialogBox.setText(Localization.txtNotEnoughMoney, 4);
            } else {
                GameLevel.vehicleUpgrades[this.selectedVehicle][0] = 0;
                GameLevel.cash -= this.vehicleXpReq[this.selectedVehicle];
                MainMenu.dialogState = 3 + this.selectedVehicle;
            }
        }
        if (GameScreen.inputState[7]) {
            if (GameLevel.currentVehicle != this.selectedVehicle) {
                ResourceManager.garagePreviewImage = ResourceManager.loadImage(vehicleImages[GameLevel.currentVehicle]);
            }
            GameCanvas.mainMenu.show();
        }
        GameScreen.resetInput();
    }

    public static void refreshGarage() {
        ResourceManager.garagePreviewImage = ResourceManager.loadImage(vehicleImages[GameLevel.currentVehicle]);
    }

    @Override
    public final void show() {
        this.softLeftLabel = Localization.txtSelect;
        this.softRightLabel = Localization.txtBack;
        this.title = Localization.txtGarage;
        garageData = null;
        this.selectedVehicle = GameLevel.currentVehicle;
        this.garageNameX = GameLevel.currentVehicle;
        this.garageLayoutY = GameCanvas.screenWidth >> 1;
        if (ResourceManager.garagePreviewImage == null) {
            ResourceManager.garagePreviewImage = ResourceManager.loadImage(vehicleImages[this.selectedVehicle]);
        }
        this.subtitle = Localization.lblCash + GameLevel.cash;
        super.show();
    }
}
