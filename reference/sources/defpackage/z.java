package defpackage;

import javax.microedition.lcdui.Graphics;

/* JADX INFO: loaded from: XForce.jar:z.class */
public final class z extends ai {
    public byte a;
    private byte b;
    private byte c;

    public z(int i, int i2, byte b) {
        this.a = b;
        a(i, i2, 0, 0);
        this.c = (byte) 120;
    }

    public final void a(Graphics graphics) {
        if (this.b < 3) {
            c.A.a(this.a, this.g - 8, this.h - 8, 0, graphics);
        } else {
            c.A.a(this.a + 8, this.g - 8, this.h - 8, 0, graphics);
        }
        this.b = (byte) (this.b + 1);
        if (this.b >= 6) {
            this.b = (byte) 0;
        }
        if (this.a != 7) {
            this.c = (byte) (this.c - 1);
        }
        if (this.c == 0) {
            this.o = true;
        }
    }
}
