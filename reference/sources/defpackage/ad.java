package defpackage;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/* JADX INFO: loaded from: XForce.jar:ad.class */
public final class ad {
    private int a;
    private Image b;
    private int[][] c;

    public ad(Image image, int[][] iArr) {
        this.b = image;
        this.c = iArr;
        this.a = iArr.length;
    }

    public final void a(int i, int i2, int i3, int i4, Graphics graphics) {
        if (i < 0 || i >= this.a) {
            return;
        }
        graphics.drawRegion(this.b, this.c[i][0], this.c[i][1], this.c[i][2], this.c[i][3], 0, i2 - this.c[i][4], i3 - this.c[i][5], 20);
    }
}
