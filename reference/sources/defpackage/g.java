package defpackage;

import javax.microedition.lcdui.Graphics;

/* JADX INFO: loaded from: XForce.jar:g.class */
public final class g extends an {
    private int a;

    public g(int i, int i2, byte b) {
        super(i, i2, b);
        a(i, i2, 16, 16);
        this.r = 5120;
        switch (b) {
            case 11:
                this.i = 30;
                this.r = 5120;
                this.a = 600;
                if (c.j == null) {
                    c.j = new ak(c.a("/fighter.png"), 19, 19);
                }
                if (c.l == null) {
                    c.l = new ak(c.a("/fs.png"), 19, 19);
                }
                break;
            case 12:
                this.i = 30;
                this.r = 8192;
                this.a = 400;
                if (c.k == null) {
                    c.k = new ak(c.a("/fighter3.png"), 32, 32);
                }
                if (c.m == null) {
                    c.m = new ak(c.a("/fs2.png"), 24, 24);
                }
                break;
        }
        this.y = (short) 5;
        this.x = (short) 5;
        this.t = (byte) 2;
        this.w = (byte) -1;
    }

    @Override // defpackage.an
    public final void a(Graphics graphics) {
        switch (this.v) {
            case 11:
                if (this.D) {
                    graphics.setColor(11908533);
                    graphics.drawLine(this.A + 9, this.B + 9, this.A + (this.l >> 6) + 9, this.B + (this.m >> 6) + 9);
                    this.D = false;
                }
                c.l.a(((this.q + 22) % 360) / 45, this.g + this.i, this.h + this.i, 0, graphics);
                c.j.a(((this.q + 22) % 360) / 45, this.A, this.B, 0, graphics);
                break;
            case 12:
                c.m.a(((this.q + 22) % 360) / 45, this.g + this.i, this.h + this.i, 0, graphics);
                c.k.a(((this.q + 22) % 360) / 45, this.A - 6, this.B - 6, 0, graphics);
                break;
        }
    }

    @Override // defpackage.ai
    public final void a() {
        int i = 0;
        if (this.s != null) {
            int i2 = this.s.g - this.g;
            int i3 = this.s.h - this.h;
            int iA = c.a(i2, i3);
            i = iA;
            if (Math.abs(iA - this.q) < 90 || (i2 * i2) + (i3 * i3) > 16384) {
                if (Math.abs(i - this.q) < 10) {
                    this.q = i;
                } else if ((i - this.q < 0 || i - this.q >= 180) && i - this.q >= -180) {
                    this.q -= 4;
                } else {
                    this.q += 4;
                }
                if (this.q < 0) {
                    this.q += 360;
                }
                if (this.q > 360) {
                    this.q -= 360;
                }
            }
            this.l = (this.r * c.b(this.q)) >> 10;
            this.m = (this.r * c.a(this.q)) >> 10;
        }
        f();
        this.A = (((this.g - ai.e) * this.i) / ai.d) + this.g;
        this.B = (((this.h - ai.f) * this.i) / ai.d) + this.h;
        if (this.s != null) {
            if (this.C > 0) {
                this.C = (byte) (this.C - 1);
            }
            if (this.v == 11 && this.q == i && this.C == 0) {
                int i4 = this.g + (this.l >> 6) + 9;
                int i5 = this.h + (this.m >> 6) + 9;
                if (this.s.c(i4, i5)) {
                    this.s.a(1);
                    ab.a((byte) 1, i4, i5, 0, 0, 0);
                } else {
                    ab.a((byte) 10, i4, i5, 0, 0, 0);
                }
                this.C = (byte) 3;
                this.D = true;
            }
            if (this.v == 12) {
                if (this.s.t == 0) {
                    if (a(this.s) && this.C == 0) {
                        if (r.c(this.s.g, this.s.h) != 40) {
                            ab.a((byte) 15, this.g + 12, this.h + 12, 0, null);
                        }
                        this.C = (byte) 3;
                    }
                } else if (this.q == i && this.C == 0) {
                    ab.a((byte) 11, this.g + 10, this.h + 10, this.q, this.s);
                    ab.a((byte) 11, this.g - 10, this.h - 10, this.q, this.s);
                    this.C = (byte) 100;
                }
            }
        }
        if (this.x <= 0) {
            this.o = true;
            ab.a((byte) 0, this.A, this.B, 0, 0, 0);
        }
        if (this.x <= 2 && c.d(3) != 0) {
            ab.a((byte) 4, this.A + (this.j >> 1), this.B + (this.k >> 1), 0, 0, 0);
        }
        if (this.a <= 0) {
            this.o = true;
            return;
        }
        this.a--;
        if (this.a == 100) {
            this.s = null;
        }
    }
}
