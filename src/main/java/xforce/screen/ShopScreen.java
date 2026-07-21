package xforce.screen;

import xforce.game.GameCanvas;
import xforce.game.GameLevel;
import xforce.resource.BitmapFont;
import xforce.resource.Localization;
import xforce.resource.ResourceManager;

import javax.microedition.lcdui.Graphics;

public final class ShopScreen extends GameScreen {

    private static int[][] upgradePrices = {
        new int[]{300, 500, 400, 100, 100, 100},
        new int[]{500, 700, 600, 100, 100, 100},
        new int[]{700, 600, 500, 100, 100, 100}
    };

    private int shopCursor;
    private int shopLayoutY = 8;
    private int shopDetailX;
    private int shopLabelX;
    private int shopBarX;
    private int[] upgradeLevels;
    private int shopVehicle;
    private int engineLevel;
    private int armorLevel;
    private int shopBarW;
    private int shopBarH;
    private int shopBarSpacing;
    private BitmapFont shopFont;
    private String cheatBuffer;

    public ShopScreen() {
        this.shopDetailX = 80;
        this.upgradeLevels = new int[]{0, 0, 0, 0, 100, 100};
        this.cheatBuffer = "";
        if (GameCanvas.screenWidth >= 240) {
            this.shopFont = ResourceManager.fontMedium;
            this.shopLabelX = 120;
            this.shopBarX = 140;
            this.shopBarW = 20;
        } else {
            this.shopFont = ResourceManager.fontSmall;
            this.shopLabelX = 80;
            this.shopBarX = 90;
            this.shopBarW = 18;
        }
        if (GameCanvas.screenHeight < 200) {
            this.shopBarW = 16;
        }
        this.shopDetailX = ((GameCanvas.screenHeight - (Localization.shopLabels.length * this.shopBarW)) * 3) / 4;
        this.shopBarH = 12;
        this.shopBarSpacing = 16;
        if (GameCanvas.screenWidth <= 128) {
            this.shopBarH = 4;
            this.shopBarSpacing = 6;
        }
    }

    @Override
    public final void paint(Graphics g) {
        g.drawImage(ResourceManager.backgroundImage, 0, 0, 0);
        g.drawImage(ResourceManager.garagePreviewImage, GameCanvas.screenWidth >> 1, GameCanvas.screenHeight, 36);
        drawTitle(g);
        g.setColor(12615936);
        if (this.shopCursor > -1) {
            g.fillRect(4, this.shopDetailX + (this.shopCursor * this.shopBarW), GameCanvas.screenWidth - 8, 14);
        }
        int y = this.shopDetailX;
        for (int i = 0; i < Localization.shopLabels.length; i++) {
            this.shopFont.drawString(Localization.shopLabels[i], this.shopLayoutY, y, 0, g);
            switch (i) {
                case 3:
                    g.setColor(0);
                    g.fillRect(this.shopBarX, y + 6, (this.shopBarSpacing << 2) + this.shopBarH, 4);
                    g.setColor(16760832);
                    g.fillRect(this.shopBarX, y + 6, (GameLevel.vehicleUpgrades[this.shopVehicle][3] * ((this.shopBarSpacing << 2) + this.shopBarH)) / 100, 4);
                    this.shopFont.drawString(GameLevel.vehicleUpgrades[this.shopVehicle][i] < 100 ? this.upgradeLevels[i] + "$" : "---", this.shopLabelX, y, 1, g);
                    break;
                case 4:
                    for (int p = 0; p < 5; p++) {
                        g.setColor(p < this.engineLevel ? 16760832 : 0);
                        g.fillRect(this.shopBarX + (p * this.shopBarSpacing), y + 6, this.shopBarH, 4);
                    }
                    this.shopFont.drawString(this.engineLevel < 5 ? this.upgradeLevels[i] + "$" : "---", this.shopLabelX, y, 1, g);
                    break;
                case 5:
                    for (int p = 0; p < 5; p++) {
                        g.setColor(p < this.armorLevel ? 16760832 : 0);
                        g.fillRect(this.shopBarX + (p * this.shopBarSpacing), y + 6, this.shopBarH, 4);
                    }
                    this.shopFont.drawString(this.armorLevel < 5 ? this.upgradeLevels[i] + "$" : "---", this.shopLabelX, y, 1, g);
                    break;
                default:
                    for (int p = 0; p < 5; p++) {
                        g.setColor(p <= GameLevel.vehicleUpgrades[this.shopVehicle][i] ? 16760832 : 0);
                        g.fillRect(this.shopBarX + (p * this.shopBarSpacing), y + 6, this.shopBarH, 4);
                    }
                    this.shopFont.drawString(GameLevel.vehicleUpgrades[this.shopVehicle][i] < 4 ? this.upgradeLevels[i] + "$" : "---", this.shopLabelX, y, 1, g);
                    break;
            }
            y += this.shopBarW;
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
        if (GameScreen.inputState[0]) {
            if (this.shopCursor > 0) {
                this.shopCursor--;
            }
            if (Localization.shopLabels[this.shopCursor].length() == 0) {
                this.shopCursor--;
            }
        } else if (GameScreen.inputState[1]) {
            if (this.shopCursor < Localization.shopLabels.length - 1) {
                this.shopCursor++;
            }
            if (Localization.shopLabels[this.shopCursor].length() == 0) {
                this.shopCursor++;
            }
        } else if (GameScreen.inputState[4]) {
            shopConfirm();
        } else if (GameScreen.inputState[7]) {
            GameCanvas.mainMenu.show();
        }
        GameScreen.resetInput();
        if (GameScreen.pointerState == 1) {
            GameScreen.pointerState = 2;
            this.shopCursor = -1;
            if (GameScreen.pointerX < 120 && GameScreen.pointerY > this.shopDetailX) {
                int sel = (GameScreen.pointerY - this.shopDetailX) / this.shopBarW;
                if (sel >= 0 && sel <= 5 && Localization.shopLabels[sel] != "") {
                    this.shopCursor = sel;
                }
            }
        }
        if (GameScreen.pointerState == 3) {
            if (GameScreen.pointerY > this.shopDetailX && this.shopCursor > -1) {
                shopConfirm();
            }
            GameScreen.pointerState = 0;
        }
    }

    private void shopConfirm() {
        if (this.shopCursor == 6) {
            GameCanvas.mainMenu.show();
            return;
        }
        switch (this.shopCursor) {
            case 3:
                if (GameLevel.vehicleUpgrades[this.shopVehicle][3] < 100 && this.upgradeLevels[3] <= GameLevel.cash) {
                    byte[] upgrades = GameLevel.vehicleUpgrades[this.shopVehicle];
                    upgrades[3] = (byte) Math.min(upgrades[3] + 20, 100);
                    GameLevel.cash -= this.upgradeLevels[3];
                }
                break;
            case 4:
                if (this.engineLevel < 5 && this.upgradeLevels[4] <= GameLevel.cash) {
                    this.engineLevel++;
                    GameLevel.cash -= this.upgradeLevels[4];
                }
                break;
            case 5:
                if (this.armorLevel < 5 && this.upgradeLevels[5] <= GameLevel.cash) {
                    this.armorLevel++;
                    GameLevel.cash -= this.upgradeLevels[5];
                }
                break;
            default:
                if (GameLevel.vehicleUpgrades[this.shopVehicle][this.shopCursor] < 4) {
                    if (this.cheatBuffer.endsWith("11377")) {
                        GameLevel.vehicleUpgrades[this.shopVehicle][this.shopCursor]++;
                    } else if (this.upgradeLevels[this.shopCursor] <= GameLevel.cash) {
                        GameLevel.vehicleUpgrades[this.shopVehicle][this.shopCursor]++;
                        GameLevel.cash -= this.upgradeLevels[this.shopCursor];
                    }
                }
                break;
        }
        GameLevel.vehicleUpgrades[this.shopVehicle][4] = (byte) ((this.armorLevel * 10) + this.engineLevel);
        shopUpdate();
        this.subtitle = Localization.lblCash + GameLevel.cash;
    }

    private void shopUpdate() {
        for (int i = 0; i < 5; i++) {
            if (i == 4) {
                this.upgradeLevels[i] = upgradePrices[this.shopVehicle][i];
            } else {
                this.upgradeLevels[i] = upgradePrices[this.shopVehicle][i] * (1 << GameLevel.vehicleUpgrades[this.shopVehicle][i]);
            }
        }
        this.upgradeLevels[3] = ((this.upgradeLevels[0] + this.upgradeLevels[1]) + this.upgradeLevels[2]) / 20;
        if (this.upgradeLevels[3] > 500) {
            this.upgradeLevels[3] = 500;
        }
    }

    @Override
    public final void show() {
        this.softRightLabel = Localization.txtBack;
        this.title = Localization.txtShopping;
        this.subtitle = Localization.lblCash + GameLevel.cash;
        if (ResourceManager.garagePreviewImage == null) {
            GarageScreen.refreshGarage();
        }
        this.shopCursor = 0;
        this.shopVehicle = GameLevel.currentVehicle;
        if (GameLevel.vehicleUpgrades[this.shopVehicle][3] <= 0) {
            GameLevel.vehicleUpgrades[this.shopVehicle][3] = 50;
        }
        this.armorLevel = GameLevel.vehicleUpgrades[this.shopVehicle][4] / 10;
        this.engineLevel = GameLevel.vehicleUpgrades[this.shopVehicle][4] % 10;
        shopUpdate();
        if (GameLevel.vehicleUpgrades[this.shopVehicle][3] == 0) {
            GameLevel.vehicleUpgrades[this.shopVehicle][3] = 60;
        }
        if (MainMenu.dialogState == 3) {
            DialogBox.setText(Localization.danDialogue[0], 4);
            MainMenu.dialogState = 0;
        } else if (GameLevel.vehicleUpgrades[this.shopVehicle][3] < 20) {
            DialogBox.setText(Localization.danDialogue[1], 4);
        } else if (ResourceManager.randomInt(3) == 0) {
            DialogBox.setText(Localization.danDialogue[2], 4);
        }
        super.show();
    }
}
