package defpackage;

import javax.microedition.lcdui.Graphics;

/* JADX INFO: loaded from: XForce.jar:n.class */
public final class n extends an {
    private int a;
    private int b;
    private int c;
    private int F;
    private int G;
    private int H;

    public n(int i, int i2) {
        super(i + 3, i2 + 3, (byte) 42);
        this.H = 100;
        this.j = 16;
        this.k = 16;
        this.i = 20;
        this.w = (byte) -1;
    }

    @Override // defpackage.an
    public final void a(Graphics graphics) {
        this.A = ((((this.g + 8) - ai.e) * this.i) / ai.d) + this.g + (this.j >> 1);
        this.B = ((((this.h + 8) - ai.f) * this.i) / ai.d) + this.h + (this.k >> 1);
        this.a = (this.g + this.A) >> 1;
        this.b = (this.h + this.B) >> 1;
        this.G = this.j / 2;
        graphics.setColor(16777215);
        graphics.drawLine(this.g, this.h, this.a, this.b + this.G);
        graphics.drawLine(this.g, this.h + this.k, this.a + this.G, this.b + this.G);
        graphics.drawLine(this.g + this.j, this.h, this.a, this.b);
        graphics.drawLine(this.g + this.j, this.h + this.k, this.a + this.G, this.b);
        graphics.setColor(11141120);
        graphics.drawRect(this.a, this.b, this.G, this.G);
        graphics.drawLine(this.g, this.h, this.A, this.B);
        graphics.drawLine(this.g + this.j, this.h, this.A, this.B);
        graphics.drawLine(this.g, this.h + this.k, this.A, this.B);
        graphics.drawLine(this.g + this.j, this.h + this.k, this.A, this.B);
        graphics.setColor(this.D ? 16711680 : 13421772);
        this.a = this.g + 8 + ((c.b(c.c(this.q - 5)) * this.H) >> 10);
        this.b = this.h + 8 + ((c.a(c.c(this.q - 5)) * this.H) >> 10);
        this.c = this.g + 8 + ((c.b(c.c(this.q + 5)) * this.H) >> 10);
        this.F = this.h + 8 + ((c.a(c.c(this.q + 5)) * this.H) >> 10);
        graphics.drawLine(this.A, this.B, this.a, this.b);
        graphics.drawLine(this.A, this.B, this.c, this.F);
        graphics.drawLine(this.a, this.b, this.c, this.F);
        graphics.setColor(this.E < 5 ? 11141120 : 16711680);
        graphics.fillRect(this.A - 1, this.B - 1, 3, 3);
        this.E++;
        if (this.E > 10) {
            this.E = 0;
        }
    }

    @Override // defpackage.ai
    public final void a() {
        this.q = c.c(this.q - 3);
        if (this.C > 0) {
            this.C = (byte) (this.C - 1);
        }
        if ((this.E & 1) == 0) {
            int i = (ab.m.g + 12) - (this.g + 8);
            int i2 = (ab.m.h + 12) - (this.h + 8);
            this.D = false;
            if ((i * i) + (i2 * i2) >= this.H * this.H || Math.abs(c.b(c.a(i, i2), this.q)) > 15) {
                return;
            }
            this.D = true;
            if (this.C == 0) {
                if (c.d(2) == 0) {
                    ab.a((an) new g(this.g - 300, this.h - 300, (byte) 12)).s = ab.m;
                } else {
                    ab.a((an) new g(this.g - 300, this.h - 300, (byte) 11)).s = ab.m;
                }
                this.C = (byte) 100;
            }
        }
    }
}
