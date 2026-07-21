package defpackage;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

/* JADX INFO: loaded from: XForce.jar:CMidlet.class */
public class CMidlet extends MIDlet {
    static CMidlet a;
    private static aj c;
    private static Display d;
    static boolean b = true;

    public CMidlet() {
        a = this;
        c = new aj();
        d = Display.getDisplay(this);
    }

    protected void startApp() {
        d.setCurrent(c);
        aj.c();
        p.c();
    }

    protected void pauseApp() {
        aj.b();
        p.b();
    }

    protected void destroyApp(boolean z) {
        aj.d();
    }

    public final void a() {
        notifyDestroyed();
        destroyApp(false);
    }

    public static void a(int i) {
        if (b) {
            d.vibrate(100);
        }
    }
}
