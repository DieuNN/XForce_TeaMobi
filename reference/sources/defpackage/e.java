package defpackage;

import javax.microedition.lcdui.Graphics;

/* JADX INFO: loaded from: XForce.jar:e.class */
public final class e extends an {
    private int a;
    private boolean[] b;
    private int c;
    private byte[] F;
    private byte[] G;
    private byte[] H;
    private int I;
    private int J;

    public e(int i, int i2, int i3, int i4) {
        super(i, i2, (byte) 110);
        this.b = new boolean[4];
        this.F = new byte[]{0, 1, 2, 3};
        this.G = new byte[]{1, 0, -1, 0};
        this.H = new byte[]{0, 1, 0, -1};
        b(24, 24);
        this.a = 2048;
        this.y = (short) 30;
        this.x = (short) 30;
        this.z = (short) 200;
        this.w = (byte) 1;
        this.s = ab.m;
        this.I = i3;
        this.J = i4;
    }

    @Override // defpackage.ai
    public final void a() {
        if (ab.K > 0) {
            return;
        }
        if (this.g % r.a == 0 && this.h % r.a == 0) {
            boolean z = false;
            int i = this.h / r.a;
            int i2 = this.g / r.a;
            if (this.c > 1) {
                this.c--;
            } else {
                ab.b(i - this.H[this.p], i2 - this.G[this.p], this);
                this.b[0] = !r.a(i, i2 + 1);
                this.b[1] = !r.a(i + 1, i2);
                this.b[2] = !r.a(i, i2 - 1);
                this.b[3] = !r.a(i - 1, i2);
                int i3 = ((this.s.g + 12) / 24) - ((this.g + 12) / 24);
                int i4 = ((this.s.h + 12) / 24) - ((this.h + 12) / 24);
                if (Math.abs(i3) < 10 && Math.abs(i4) < 10) {
                    if ((i3 == 0 || i4 == 0) && Math.abs(i3) < 3 && Math.abs(i4) < 3) {
                        this.l = 0;
                        this.m = 0;
                        z = true;
                        this.c = 50;
                    } else {
                        for (int i5 = 0; i5 < 4; i5++) {
                            if (this.b[this.F[i5]]) {
                                switch (this.F[i5]) {
                                    case 0:
                                        if (i3 > 0) {
                                            z = true;
                                        }
                                        break;
                                    case 1:
                                        if (i4 > 0) {
                                            z = true;
                                        }
                                        break;
                                    case 2:
                                        if (i3 < 0) {
                                            z = true;
                                        }
                                        break;
                                    case 3:
                                        if (i4 < 0) {
                                            z = true;
                                        }
                                        break;
                                }
                                if (z) {
                                    this.p = this.F[i5];
                                    this.l = this.G[this.F[i5]] * this.a;
                                    this.m = this.H[this.F[i5]] * this.a;
                                    this.q = this.p * 90;
                                }
                            }
                        }
                    }
                }
                if (!z) {
                    this.l = 0;
                    this.m = 0;
                }
                f.e = !z;
                if (this.l != 0 || this.m != 0) {
                    ab.a(i + this.H[this.p], i2 + this.G[this.p], this);
                }
            }
        }
        f();
        if (f.a == 15 && b(this.I - 24, this.J - 24, 48, 48)) {
            ab.a(true);
        }
    }

    @Override // defpackage.an
    public final void a(Graphics graphics) {
        if (this.u) {
            this.u = false;
        } else {
            c.x.a(this.p, this.g, this.h, 0, graphics);
        }
    }

    @Override // defpackage.an
    public final void b() {
        int i = this.g / r.a;
        int i2 = this.h / r.a;
        ab.b(i2, i, this);
        r.a(i2, i, 0);
        if (this.g % r.a != 0) {
            ab.b(i2, i + 1, this);
        } else if (this.h % r.a != 0) {
            ab.b(i2 + 1, i, this);
        }
        ab.b(i2, i, this);
        ab.a((byte) 0, this.g + (this.j / 2), this.h + (this.k / 2), 0, 0, 0);
        ab.a((byte) 6, this.g + (this.j / 2), this.h + (this.k / 2), 0, 0, 0);
        ab.a(false);
        this.o = true;
    }
}
