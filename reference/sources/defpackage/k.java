package defpackage;

import javax.microedition.lcdui.Graphics;

/* JADX INFO: loaded from: XForce.jar:k.class */
public final class k extends an {
    private int a;
    private int b;
    private int c;
    private int F;
    private int G;
    private int H;
    private int I;

    public k(int i, int i2) {
        super(i, i2, (byte) 14);
        a(i, i2, 24, 24);
        this.w = (byte) -1;
        this.b = i;
        this.c = i2;
        this.I = 0;
        this.r = 3072;
        this.i = 0;
        this.q = c.e(360);
        ab.r++;
        this.s = ab.m;
        if (c.q == null) {
            c.q = new ak(c.a("/heli.png"), 24, 24);
            c.r = new ak(c.a("/sheli.png"), 24, 24);
            c.s = new ak(c.a("/probeller.png"), 24, 24);
        }
    }

    @Override // defpackage.an
    public final void a(Graphics graphics) {
        c.r.a(((this.q + 22) % 360) / 45, this.g + this.i, this.h + this.i, 0, graphics);
        c.q.a(((this.q + 22) % 360) / 45, this.A, this.B, 0, graphics);
        c.s.a(this.H + (this.I >= 60 ? 4 : 0), this.A, this.B, 0, graphics);
        if (this.I > 0) {
            if (this.I < 20) {
                this.F++;
                if (this.F >= 3) {
                    this.F = 0;
                    this.H++;
                }
            } else if (this.I < 40) {
                this.F++;
                if (this.F >= 2) {
                    this.F = 0;
                    this.H++;
                }
            } else {
                this.H++;
            }
            if (this.H >= 4) {
                this.H = 0;
            }
        }
        b(graphics);
    }

    @Override // defpackage.ai
    public final void a() {
        int i = this.s.g - this.g;
        int i2 = this.s.h - this.h;
        if (this.a == 0) {
            this.r = 0;
            if ((i * i) + (i2 * i2) < 16384) {
                this.a = 1;
            }
        } else if (this.a == 1) {
            if (this.I < 70) {
                this.I++;
            } else if (this.i < 30) {
                if (this.i == 0) {
                    ab.b(this.c / r.a, this.b / r.a, this);
                    this.t = (byte) 2;
                }
                this.i++;
            } else {
                d(i, i2);
            }
        } else if (this.a == 2) {
            int i3 = this.b - this.g;
            int i4 = this.c - this.h;
            if (Math.abs(i3) >= 5 || Math.abs(i4) >= 5) {
                d(i3, i4);
            } else if (this.i == 30) {
                if (!r.a(this.c / r.a, this.b / r.a)) {
                    this.i--;
                    r.a(this.c / r.a, this.b / r.a, 1);
                    this.t = (byte) 1;
                }
            } else if (this.i > 0) {
                this.i--;
                if (this.i == 0) {
                    ab.n[this.c / r.a][this.b / r.a] = this;
                }
            } else if (this.I > 0) {
                this.I--;
            } else {
                this.a = 0;
            }
        }
        this.A = (((this.g - ai.e) * this.i) / ai.d) + this.g;
        this.B = (((this.h - ai.f) * this.i) / ai.d) + this.h;
        if (this.x > 2 || this.H != 0) {
            return;
        }
        ab.a((byte) 4, this.A + (this.j >> 1), this.B + (this.k >> 1), 0, 0, 0);
    }

    private void d(int i, int i2) {
        int iA = c.a(i, i2);
        int i3 = (i * i) + (i2 * i2);
        if (Math.abs(iA - this.q) < 90 || i3 > 1024) {
            if (Math.abs(iA - this.q) < 12) {
                this.q = iA;
            } else if ((iA - this.q < 0 || iA - this.q >= 180) && iA - this.q >= -180) {
                this.q = c.c(this.q - 6);
            } else {
                this.q = c.c(this.q + 6);
            }
        }
        if (this.a == 1 && i3 < 4096) {
            this.r = 0;
        } else if (this.r < 3072) {
            this.r += 128;
        }
        this.l = (this.r * c.b(this.q)) >> 10;
        this.m = (this.r * c.a(this.q)) >> 10;
        f();
        if (this.a == 1) {
            if (this.C > 0) {
                this.C = (byte) (this.C - 1);
                return;
            }
            if (Math.abs(c.b(iA, this.q)) <= 7) {
                this.q = iA;
                this.D = true;
                this.G++;
                this.C = (byte) 50;
                ab.a((byte) 11, this.g + 12, this.h + 12, this.q, this.s);
                if (this.G == 3) {
                    this.G = 0;
                    this.a = 2;
                }
            }
        }
    }

    @Override // defpackage.an
    public final void b() {
        this.o = true;
        ab.a((byte) 0, this.g + 12, this.h + 12, 0, 0, 0);
        ab.a((byte) 6, this.g + 12, this.h + 12, 0, 0, 0);
        ab.b(this.c / r.a, this.b / r.a, this);
        ab.s++;
    }
}
