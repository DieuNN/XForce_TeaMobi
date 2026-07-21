package xforce.screen;

import xforce.game.GameCanvas;
import xforce.resource.Localization;
import xforce.resource.ResourceManager;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class SplashScreen extends GameScreen {
    private Image splashImage = ResourceManager.loadImage("/logo.png");
    private int displayCount = 50;

    @Override
    public final void paint(Graphics graphics) {
        int width = GameCanvas.instance.getWidth();
        int height = GameCanvas.instance.getHeight();
        graphics.setColor(0);
        graphics.fillRect(0, 0, width, height);
        graphics.drawImage(this.splashImage, (width - this.splashImage.getWidth()) >> 1, (height - this.splashImage.getHeight()) >> 1, 0);
    }

    @Override
    public final void update() {
        this.displayCount--;
        if (this.displayCount == 0) {
            GameCanvas.initialize();
            GameCanvas.confirmDialog.showDialog(Localization.txtSoundQuestion, true);
        }
    }
}
