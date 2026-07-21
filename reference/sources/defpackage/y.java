package defpackage;

import javax.microedition.lcdui.Graphics;

/* JADX INFO: loaded from: XForce.jar:y.class */
public final class y extends an {
    private int a;

    public y() {
        super((5 * r.a) + 2, 53 * r.a, (byte) 15);
        this.j = 20;
        this.k = 100;
        this.m = 0;
        this.l = 0;
        c.L = c.a("/train.png");
        c.a("/train1.png");
        c.M = c.a("/train2.png");
        for (int i = 47; i < 60; i++) {
            r.d[i][5] = 1;
        }
    }

    @Override // defpackage.an
    public final void a(Graphics graphics) {
        int i = this.h;
        graphics.drawImage(c.L, this.g, i, 0);
        int i2 = i + 50;
        for (int i3 = 0; i3 < 4; i3++) {
            if (this.m == 0 || this.a != 0) {
                graphics.drawImage(c.M, this.g, i2, 0);
            } else {
                graphics.drawImage(c.M, this.g, i2 + c.d(2), 0);
            }
            i2 += 37;
        }
        if (this.a == 0) {
            ab.a((byte) 10, this.g + 8, this.h + 8, c.d(512), 1024, 0);
        }
    }

    @Override // defpackage.ai
    public final void a() {
        this.a++;
        if (this.m == 0) {
            if (this.a >= 5) {
                this.a = 0;
                return;
            }
            return;
        }
        f();
        if (this.a >= 3) {
            this.a = 0;
        }
        if (this.h < 6 * r.a && ab.K == 0) {
            ab.a(false);
        }
        for (int i = 0; i < 60; i++) {
            r.d[i][5] = 1;
        }
    }

    @Override // defpackage.an
    public final void b() {
        c.L = c.a("/traind.png");
        this.m = 0;
        ab.a(this.g + 12, this.h + 12);
        ab.a(true);
    }

    @Override // defpackage.an
    public final void c() {
        ab.c(this.g + 12, this.h + 12);
    }
}
