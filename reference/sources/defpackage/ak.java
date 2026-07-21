package defpackage;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/* JADX INFO: loaded from: XForce.jar:ak.class */
public final class ak {
    private int a;
    private int b;
    private int c;
    private Image d;
    private int[] e;
    private int f;

    public ak(Image image, int i, int i2) {
        this.d = image;
        this.a = i;
        this.b = i2;
        this.f = image.getHeight();
        this.c = this.f / i2;
        this.e = new int[this.c];
        for (int i3 = 0; i3 < this.c; i3++) {
            this.e[i3] = i3 * i2;
        }
    }

    public final void a(int i, int i2, int i3, int i4, Graphics graphics) {
        if (i < 0 || i >= this.c) {
            return;
        }
        graphics.drawRegion(this.d, 0, this.e[i], this.a, this.b, 0, i2, i3, 0);
    }

    public final void a() {
        this.d = null;
        this.e = null;
    }
}
