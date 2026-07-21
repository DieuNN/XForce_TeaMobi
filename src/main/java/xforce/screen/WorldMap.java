package xforce.screen;

import xforce.audio.AudioManager;
import xforce.data.MissionData;
import xforce.game.GameCanvas;
import xforce.game.GameLevel;
import xforce.resource.Localization;
import xforce.resource.ResourceManager;

import javax.microedition.lcdui.Graphics;

public final class WorldMap extends GameScreen {

    private int[] missionData;
    private int blinkTimer;
    private int ringAnim;
    private int nameReveal;
    private int scrollX;
    private int requiredUnlocks;
    private byte[] missionNameEncoded;
    private int[] missionNodeX = {208, 221, 196, 181, 195, 201, 143, 147, 137, 159, 139, 140, 121, 138, 119, 155, 52, 41, 15, 32, 59, 69, 58, 44, 26, 70, 90, 88, 100, 95, 110};
    private int[] missionNodeY = {104, 119, 119, 95, 39, 35, 106, 95, 97, 85, 66, 46, 78, 34, 19, 27, 98, 85, 69, 64, 71, 49, 38, 33, 26, 20, 20, 36, 63, 80, 91};
    private String[] missionNames = {"DES#0", "DES#1", "DES#2", "DESERT KHMOER", "FRONT LINE\nOF BLACK GOLD", "THE HUGE TANK", "PECA CAPE", "AREA 27", "LOST IN WOODS", "OLD PORT", "TRAIN STATION\nLUTHENS", "VIP 1", "SEAL PORT", "DESERT WOLF", "INTEL 1", "WAR SHIP", "CUBEBACKER III", "V.I.P OF WARS", "CRUEL LAWS", "AREA 31", "INTEL 2", "AIR BASE", "NOWAYBACK", "HUNTING THE HUNTER", "LION HEART", "BLOOD LINE", "X PLAN", "INTEL 3", "G.U.N", "INTEL 4", "BLACK BASE", "MAP31", "MAP32", "MAP33", "MAP34", "MAP35"};
    private int selectedMission = -1;
    private int mapImageW = 241;
    private int mapImageH = 133;
    private String cheatBuffer = "";
    private int mapScrollY = ((GameCanvas.screenHeight - this.mapImageH) >> 1) + 5;
    private int cursorX = this.mapImageW >> 1;
    private int cursorY = this.mapImageH >> 1;
    private int[] missionUnlocked = new int[this.missionNodeX.length];
    private int[] missionTypes = new int[this.missionNodeX.length];

    @Override
    public final void show() {
        this.title = Localization.txtWorldMap;
        this.softRightLabel = Localization.txtBack;
        if (ResourceManager.garagePreviewImage == null) {
            GarageScreen.refreshGarage();
        }
        if (ResourceManager.randomInt(3) == 0) {
            DialogBox.setText(Localization.welcomeDialogue[ResourceManager.randomPositive(Localization.welcomeDialogue.length)], 3);
        }
        for (int i = 0; i < this.missionNodeX.length; i++) {
            int[] data = MissionData.getMissionData(i);
            if (!GameLevel.missionFlags[i]) {
                if (this.cursorX == (this.mapImageW >> 1) && this.cursorY == (this.mapImageH >> 1)) {
                    this.cursorX = this.missionNodeX[i];
                    this.cursorY = this.missionNodeY[i];
                }
                this.requiredUnlocks = data[MissionData.IDX_REQUIRED_UNLK];
                break;
            }
        }
        for (int i = 0; i < this.missionNodeX.length; i++) {
            int[] data = MissionData.getMissionData(i);
            if (data[MissionData.IDX_REQUIRED_UNLK] > this.requiredUnlocks) {
                this.missionUnlocked[i] = 1;
            } else {
                this.missionUnlocked[i] = 0;
                if (data[MissionData.IDX_CASH_COST] > GameLevel.cash || data[MissionData.IDX_XP_REQUIRED] > GameLevel.xp) {
                    this.missionUnlocked[i] = 1;
                }
                if ((data[MissionData.IDX_VEHICLE_MASK] & 1) == 0 && GameLevel.currentVehicle == 0) {
                    this.missionUnlocked[i] = 1;
                }
                if ((data[MissionData.IDX_VEHICLE_MASK] & 2) == 0 && GameLevel.currentVehicle == 1) {
                    this.missionUnlocked[i] = 1;
                }
                if ((data[MissionData.IDX_VEHICLE_MASK] & 4) == 0 && GameLevel.currentVehicle == 2) {
                    this.missionUnlocked[i] = 1;
                }
            }
            this.missionTypes[i] = data[MissionData.IDX_MISSION_TYPE];
        }
        this.subtitle = Localization.lblCash + GameLevel.cash;
        super.show();
    }

    @Override
    public final void paint(Graphics g) {
        g.drawImage(ResourceManager.backgroundImage, 0, 0, 0);
        if (this.cursorX < this.scrollX + 10) {
            this.scrollX = this.cursorX - 10;
        }
        if (this.cursorX > (this.scrollX + GameCanvas.screenWidth) - 10) {
            this.scrollX = (this.cursorX - GameCanvas.screenWidth) + 10;
        }
        g.translate(-this.scrollX, this.mapScrollY);
        g.drawImage(ResourceManager.mapBgImage, 0, 0, 0);
        g.setColor(43520);
        g.drawRect(-2, -2, this.mapImageW + 3, this.mapImageH + 3);
        this.selectedMission = -1;
        this.softLeftLabel = null;
        for (int i = 0; i < this.missionNodeX.length; i++) {
            if (this.missionUnlocked[i] == 0) {
                g.setColor(this.blinkTimer < 5 ? 16711680 : 11141120);
            } else {
                g.setColor(8912896);
            }
            if (GameLevel.missionFlags[i]) {
                g.drawLine(this.missionNodeX[i] - 2, this.missionNodeY[i] - 2, this.missionNodeX[i] + 2, this.missionNodeY[i] + 2);
                g.drawLine(this.missionNodeX[i] + 2, this.missionNodeY[i] - 2, this.missionNodeX[i] - 2, this.missionNodeY[i] + 2);
            } else if (this.missionTypes[i] == 0) {
                g.drawRect(this.missionNodeX[i] - 1, this.missionNodeY[i] - 1, 2, 2);
            } else {
                g.drawRect(this.missionNodeX[i] - 2, this.missionNodeY[i] - 2, 4, 4);
                g.drawRect(this.missionNodeX[i] - 1, this.missionNodeY[i] - 1, 2, 2);
            }
            if (Math.abs(this.cursorX - this.missionNodeX[i]) < 5 && Math.abs(this.cursorY - this.missionNodeY[i]) < 5 && this.missionUnlocked[i] < 2 && this.selectedMission != i) {
                this.missionData = MissionData.getMissionData(i);
                this.missionNameEncoded = ResourceManager.fontHud.encodeString(this.missionNames[i] + '\n' + Localization.lblPrice + this.missionData[MissionData.IDX_CASH_COST] + "$");
                this.selectedMission = i;
                this.softLeftLabel = Localization.txtNext;
            }
        }
        if (this.selectedMission == -1 || this.missionUnlocked[this.selectedMission] != 0) {
            this.ringAnim = 0;
        } else {
            g.setColor(16711680);
            g.drawArc(this.missionNodeX[this.selectedMission] - this.ringAnim, this.missionNodeY[this.selectedMission] - this.ringAnim, this.ringAnim + this.ringAnim, this.ringAnim + this.ringAnim, 0, 360);
            this.ringAnim++;
            if (this.ringAnim > 12) {
                this.ringAnim = 0;
            }
        }
        if (this.requiredUnlocks < 2) {
            g.drawImage(ResourceManager.lockIcon, 140, 30, 0);
        }
        if (this.requiredUnlocks < 4) {
            g.drawImage(ResourceManager.lockIcon, 60, 70, 0);
        }
        g.setColor(65280);
        g.drawLine(0, this.cursorY, this.mapImageW, this.cursorY);
        g.drawLine(this.cursorX, 0, this.cursorX, this.mapImageH);
        if (this.selectedMission != -1) {
            if (this.cursorX - this.scrollX < (GameCanvas.screenWidth >> 1)) {
                ResourceManager.fontHud.drawEncodedSubstring(this.missionNameEncoded, 0, this.nameReveal, this.cursorX + 4, this.cursorY + 2, 0, g);
            } else {
                ResourceManager.fontHud.drawEncodedSubstring(this.missionNameEncoded, 0, this.nameReveal, this.cursorX - 4, this.cursorY + 2, 1, g);
            }
            if (this.nameReveal < this.missionNameEncoded.length) {
                this.nameReveal++;
            }
        } else {
            this.nameReveal = 0;
        }
        g.translate(-g.getTranslateX(), -g.getTranslateY());
        drawTitle(g);
        this.blinkTimer++;
        if (this.blinkTimer > 10) {
            this.blinkTimer = 0;
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
        if (GameScreen.inputState[0] && this.cursorY > 0) {
            this.cursorY -= 3;
        }
        if (GameScreen.inputState[1] && this.cursorY < this.mapImageH) {
            this.cursorY += 3;
        }
        if (GameScreen.inputState[2] && this.cursorX > 0) {
            this.cursorX -= 3;
        }
        if (GameScreen.inputState[3] && this.cursorX < this.mapImageW) {
            this.cursorX += 3;
        }
        if (GameScreen.inputState[4] || GameScreen.inputState[6]) {
            GameScreen.inputState[6] = false;
            GameScreen.inputState[4] = false;
            if (this.selectedMission != -1 && this.cheatBuffer.endsWith("11317")) {
                AudioManager.closeMusic();
                ResourceManager.unloadLevelAssets();
                GameCanvas.gameLevel.loadMission(this.selectedMission);
            } else if (this.selectedMission != -1 && this.missionUnlocked[this.selectedMission] == 0) {
                if (GameLevel.cash < this.missionData[MissionData.IDX_CASH_COST] && this.missionData[MissionData.IDX_CASH_COST] > 0) {
                    DialogBox.setText(Localization.txtNotEnoughMoney, 3);
                    return;
                }
                AudioManager.closeMusic();
                ResourceManager.unloadLevelAssets();
                GameCanvas.gameLevel.loadMission(this.selectedMission);
                GameLevel.cash -= this.missionData[MissionData.IDX_CASH_COST];
            }
        }
        if (GameScreen.inputState[7]) {
            GameScreen.inputState[7] = false;
            GameCanvas.mainMenu.show();
        }
        if (GameScreen.pointerState == 1) {
            GameScreen.pointerState = 1;
            if (GameScreen.pointerY - this.mapScrollY <= 0 || GameScreen.pointerY - this.mapScrollY >= this.mapImageH || GameScreen.pointerX + this.scrollX <= 0 || GameScreen.pointerX + this.scrollX >= this.mapImageW) {
                return;
            }
            this.cursorX = GameScreen.pointerX + this.scrollX;
            this.cursorY = GameScreen.pointerY - this.mapScrollY;
        }
    }
}
