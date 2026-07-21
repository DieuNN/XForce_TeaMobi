package xforce.game;

import xforce.audio.AudioManager;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
public class XMIDlet extends MIDlet {
    public static XMIDlet instance;
    private static GameCanvas gameCanvas;
    private static Display display;
    public static boolean vibrationEnabled = true;

    public XMIDlet() {
        instance = this;
        gameCanvas = new GameCanvas();
        display = Display.getDisplay(this);
    }

    protected void startApp() {
        display.setCurrent(gameCanvas);
        GameCanvas.resumeGame();
        AudioManager.startMusic();
    }

    protected void pauseApp() {
        GameCanvas.pauseGame();
        AudioManager.stopMusic();
    }

    protected void destroyApp(boolean z) {
        GameCanvas.stopGame();
    }
    public final void m0a() {
        notifyDestroyed();
        destroyApp(false);
    }
    public static void m1a(int i) {
        if (vibrationEnabled) {
            display.vibrate(100);
        }
    }
}
