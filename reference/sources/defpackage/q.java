package defpackage;

import javax.microedition.lcdui.Graphics;

/* JADX INFO: loaded from: XForce.jar:q.class */
public abstract class q {
    static boolean[] a = new boolean[8];
    static int b;
    static int c;
    static int d;
    public String e;
    public String f;
    public String g;
    public String h;

    public abstract void a(Graphics graphics);

    public void a(int i) {
    }

    public void a() {
    }

    static void c() {
        for (int i = 0; i < a.length; i++) {
            a[i] = false;
        }
    }

    public static void b(Graphics graphics) {
        graphics.setColor(0);
        for (int i = 0; i < 320; i += 2) {
            graphics.drawLine(0, i, 240, i);
        }
    }

    public final void c(Graphics graphics) {
        if (aj.c <= 220) {
            if (this.g != null) {
                c.c.a(this.g, 8, 8, 0, graphics);
            }
            if (this.h != null) {
                c.a.a(this.h, 8, 26, 0, graphics);
                return;
            }
            return;
        }
        if (this.g != null) {
            c.c.a(this.g, 8, 18, 0, graphics);
        }
        if (this.h != null) {
            c.b.a(this.h, 8, 40, 0, graphics);
        }
    }

    public void b() {
        c();
        aj.d = this;
    }
}
