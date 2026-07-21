package xforce.screen;

import xforce.game.GameCanvas;
import xforce.game.GameLevel;
import xforce.resource.BitmapFont;
import xforce.resource.Localization;
import xforce.resource.ResourceManager;

import javax.microedition.lcdui.Graphics;

public final class InfoScreen extends GameScreen {

    private BitmapFont screenFont;
    private static byte[] infoData;

    public InfoScreen() {
        this.screenFont = GameCanvas.screenWidth >= 240 ? ResourceManager.fontMedium : ResourceManager.fontSmall;
    }

    @Override
    public final void paint(Graphics g) {
        g.drawImage(ResourceManager.backgroundImage, 0, 0, 0);
        drawTitle(g);
        this.screenFont.drawEncoded(infoData, 8, (GameCanvas.screenHeight - 100) >> 1, 0, g);
    }

    @Override
    public final void update() {
        if (GameScreen.inputState[7]) {
            GameCanvas.mainMenu.show();
        }
        GameScreen.resetInput();
    }

    @Override
    public final void show() {
        this.softRightLabel = Localization.txtBack;
        this.title = Localization.txtInfo;
        super.show();
        int playSecs = (int) (((System.currentTimeMillis() - ResourceManager.startTime) / 1000) + ResourceManager.totalPlayTime);
        String text = Localization.lblName + GameLevel.playerName + "\n"
            + Localization.lblPlayTime + (playSecs / 3600) + ":" + ((playSecs / 60) % 60) + "\n"
            + Localization.lblXp + GameLevel.xp + "\n"
            + Localization.lblCash + GameLevel.cash + "$";

        int completed = 0;
        for (int i = 0; i < GameLevel.missionFlags.length; i++) {
            if (GameLevel.missionFlags[i]) completed++;
        }
        infoData = this.screenFont.encodeString(text + "\n" + Localization.lblCompleted + ((completed * 100) / GameLevel.missionFlags.length) + "%");
    }
}
