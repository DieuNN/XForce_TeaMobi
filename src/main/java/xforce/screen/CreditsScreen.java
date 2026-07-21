package xforce.screen;

import xforce.game.GameCanvas;
import xforce.resource.Localization;
import xforce.resource.ResourceManager;

import javax.microedition.lcdui.Graphics;
import java.util.Objects;

public final class CreditsScreen extends GameScreen {

    private byte[][] encodedCreditLines;
    private byte[] encodedHelp;
    private int scrollY;
    private int scrollBottom;
    private boolean firstPaint;

    @Override
    public final void paint(Graphics g) {
        if (this.firstPaint) {
            g.drawImage(ResourceManager.backgroundImage, 0, 0, 0);
            drawTitle(g);
            this.firstPaint = false;
        }
        int topMargin = 60;
        g.setClip(0, topMargin, 120, this.scrollBottom - topMargin);
        g.drawImage(ResourceManager.backgroundImage, 0, 0, 0);
        if (Objects.equals(this.title, Localization.txtCredits)) {
            int y = 0;
            for (int i = 0; i < Localization.creditsLines.length; i++) {
                if (i == 0 || Localization.creditsLines[i - 1] == "") {
                    ResourceManager.fontSmall.drawEncoded(this.encodedCreditLines[i], 8, this.scrollY + y, 0, g);
                } else {
                    ResourceManager.fontMedium.drawEncoded(this.encodedCreditLines[i], 120, this.scrollY + y, 1, g);
                }
                y += 20;
            }
            int logoY = y + 40;
            g.drawImage(ResourceManager.creditsLogo, 16, this.scrollY + logoY, 0);
            if (this.scrollY > (topMargin - logoY) - 50) {
                this.scrollY--;
                return;
            } else {
                this.scrollY = this.scrollBottom;
                return;
            }
        }
        ResourceManager.fontSmall.drawEncoded(this.encodedHelp, 8, this.scrollY, 0, g);
        if (GameScreen.inputState[4]) {
            return;
        }
        if (GameScreen.inputState[0] && this.scrollY > (-260) - topMargin) {
            this.scrollY -= 3;
            return;
        }
        if (GameScreen.inputState[1] && this.scrollY < this.scrollBottom) {
            this.scrollY += 3;
        } else if (this.scrollY > (-260) - topMargin) {
            this.scrollY--;
        } else {
            this.scrollY = this.scrollBottom;
        }
    }

    @Override
    public final void show() {
        this.encodedCreditLines = new byte[Localization.creditsLines.length][];
        for (int i = 0; i < Localization.creditsLines.length; i++) {
            if (i == 0 || Localization.creditsLines[i - 1] == "") {
                this.encodedCreditLines[i] = ResourceManager.fontSmall.encodeString(Localization.creditsLines[i]);
            } else {
                this.encodedCreditLines[i] = ResourceManager.fontMedium.encodeString(Localization.creditsLines[i]);
            }
        }
        this.title = Localization.txtCredits;
        this.scrollBottom = GameCanvas.screenHeight;
        this.scrollY = this.scrollBottom;
        this.firstPaint = true;
        super.show();
    }

    public final void showHelp() {
        this.encodedHelp = ResourceManager.fontSmall.encodeString(Localization.helpText);
        this.title = Localization.txtInstructions;
        this.scrollBottom = GameCanvas.screenHeight;
        this.scrollY = this.scrollBottom;
        this.firstPaint = true;
        super.show();
    }

    @Override
    public void onKeyPressed(int key) {
        if (key == -5) {
            GameCanvas.mainMenu.show();
        }
    }
}
