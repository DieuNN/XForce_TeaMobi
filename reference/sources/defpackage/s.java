package defpackage;

import javax.microedition.lcdui.Graphics;

/* JADX INFO: loaded from: XForce.jar:s.class */
public final class s extends an {
    private int[] a;
    private int[] b;
    private int[] c;
    private an[] F;
    private int G;
    private int H;
    private boolean I;
    private int[] J;
    private static int K;
    private static int L;

    public s(int i, int i2) {
        super(i, i2, (byte) 103);
        this.a = new int[]{39, 63, 26, 75, 51, 35, 18, 66, 83};
        this.b = new int[]{65, 65, 28, 28, 36, 14, 18, 14, 18};
        this.c = new int[]{200, 200, 200, 200, 200, 200, 200, 200, 200};
        this.F = new an[9];
        this.G = 5;
        this.H = 9;
        this.J = new int[5];
        b(118, 88);
        c.U = c.a("/bigboss1.png");
        c.T = new ad(c.a("/bigboss.png"), new int[][]{new int[]{0, 0, 52, 22, 0, -20}, new int[]{0, 22, 52, 22, 0, -20}, new int[]{0, 44, 52, 22, -66, -20}, new int[]{0, 66, 52, 22, -66, -20}, new int[]{52, 0, 14, 88, -52, 0}, new int[]{66, 0, 14, 88, -52, 0}, new int[]{80, 0, 8, 11, -40, -9}, new int[]{88, 0, 8, 11, -40, -9}, new int[]{80, 11, 8, 7, -22, -13}, new int[]{88, 11, 8, 7, -22, -13}, new int[]{80, 18, 8, 11, -70, -9}, new int[]{88, 18, 8, 11, -70, -9}, new int[]{80, 29, 8, 7, -88, -14}, new int[]{88, 29, 8, 7, -88, -14}, new int[]{80, 36, 17, 12, -35, -67}, new int[]{80, 48, 17, 12, -35, -67}, new int[]{80, 60, 17, 12, -66, -67}, new int[]{80, 72, 17, 12, -66, -67}});
        for (int i3 = 0; i3 < this.H; i3++) {
            if (i3 < 2) {
                this.F[i3] = new m(0, 0, (byte) 104);
            } else if (i3 < 4) {
                this.F[i3] = new m(0, 0, (byte) 105);
            } else if (i3 < 5) {
                this.F[i3] = new m(0, 0, (byte) 106);
            } else {
                this.F[i3] = new an(0, 0, (byte) 107);
            }
            if (i3 > 1) {
                this.F[i3].n = true;
            }
        }
        if (f.a == 14) {
            this.i = 2;
            this.F[0].n = true;
            this.F[1].n = true;
        } else {
            this.i = 30;
            this.l = 1024;
            this.m = -2048;
        }
    }

    @Override // defpackage.an
    public final void a(Graphics graphics) {
        graphics.drawImage(c.U, this.g + this.i + 12, this.h + this.i + 12, 0);
        if (this.i <= 1) {
            return;
        }
        c.T.a(this.F[2] != null ? 0 : 1, this.g, this.h, 0, graphics);
        c.T.a(this.F[3] != null ? 2 : 3, this.g, this.h, 0, graphics);
        c.T.a(this.F[4] != null ? 4 : 5, this.g, this.h, 0, graphics);
        c.T.a(this.F[5] != null ? 6 : 7, this.g, this.h, 0, graphics);
        c.T.a(this.F[6] != null ? 8 : 9, this.g, this.h, 0, graphics);
        c.T.a(this.F[7] != null ? 10 : 11, this.g, this.h, 0, graphics);
        c.T.a(this.F[8] != null ? 12 : 13, this.g, this.h, 0, graphics);
        c.T.a(this.F[0] != null ? 14 : 15, this.g, this.h, 0, graphics);
        c.T.a(this.F[1] != null ? 16 : 17, this.g, this.h, 0, graphics);
        for (int i = 0; i < this.G; i++) {
            if (this.F[i] != null) {
                graphics.setColor(0);
                graphics.fillRect(this.F[i].g + 4, this.F[i].h + 4, this.J[i], 8);
                if (this.J[i] == 8) {
                    this.F[i].a(graphics);
                }
            }
        }
        for (int i2 = this.G; i2 < this.H; i2++) {
            if (this.F[i2] != null) {
                this.F[i2].a(graphics);
            }
        }
    }

    @Override // defpackage.ai
    public final void a() {
        f();
        for (int i = 0; i < this.H; i++) {
            if (this.F[i] != null) {
                this.F[i].a(this.g + this.a[i], this.h + this.b[i]);
                if (!this.F[i].n && i < this.G) {
                    if (this.J[i] < 8) {
                        int[] iArr = this.J;
                        int i2 = i;
                        iArr[i2] = iArr[i2] + 1;
                    } else {
                        this.F[i].a();
                    }
                }
            } else if (this.c[i] > 0) {
                if (this.c[i] % 3 == 0) {
                    ab.a((byte) 2, this.g + this.a[i] + 8, this.h + this.b[i] + 8, c.d(512), 1024, 0);
                }
                int[] iArr2 = this.c;
                int i3 = i;
                iArr2[i3] = iArr2[i3] - 1;
            }
        }
        if (f.a == 14) {
            if (this.m != 0) {
                if (this.m > -8000) {
                    this.m -= 96;
                    return;
                }
                if (this.i < 40) {
                    this.i++;
                    return;
                } else {
                    if (this.h < (-this.k)) {
                        this.o = true;
                        x.a(2);
                        return;
                    }
                    return;
                }
            }
            return;
        }
        if (!this.I) {
            if (this.g + (this.j / 2) > 120) {
                K = -32;
            }
            if (this.g + (this.j / 2) < 120) {
                K = 32;
            }
            if (this.h + (this.k / 2) > f.f - 60) {
                L = -32;
            }
            if (this.h + (this.k / 2) < f.f - 60) {
                L = 32;
            }
            this.l += K;
            this.m += L;
            return;
        }
        if (this.i > 1) {
            this.i--;
            this.m += 256;
        }
        if (this.i == 2) {
            int[] iArr3 = {59, 14, 104, 43, 73, 43, 37, 56, 60, 28, 43, 65, 85, 46, 48, 46};
            int[] iArr4 = {8, 25, 26, 30, 32, 16, 16, 45, 58, 33, 22, 32, 48, 22, 48, 64};
            int[] iArr5 = {0, 5, 2, 3, 7, 10, 15, 1, 2, 20, 16, 12, 10, 8, 20, 17};
            this.l = 0;
            this.m = 0;
            for (int i4 = 0; i4 < iArr3.length; i4++) {
                ab.a((byte) 0, this.g + iArr3[i4], this.h + iArr4[i4], 0, 0, iArr5[i4]);
            }
            ab.a((byte) 5, this.g + 50, this.h + 43, 0, 0, 0);
            ab.a((byte) 5, this.g + 26, this.h + 37, 0, 0, 0);
            ab.a((byte) 5, this.g + 58, this.h + 71, 0, 0, 0);
            c.U = null;
            c.U = c.a("/bigboss2.png");
            this.i = 0;
            x.a(3);
        }
    }

    @Override // defpackage.an
    public final boolean a(v vVar) {
        if (!a((ai) vVar)) {
            return false;
        }
        if (f.a == 14 && this.m == 0) {
            ab.y = this;
            this.m = -1;
        }
        for (int i = 0; i < this.H; i++) {
            if (this.F[i] != null && !this.F[i].n && this.F[i].a(vVar)) {
                if (!this.F[i].o) {
                    return true;
                }
                this.F[i] = null;
                if (this.F[0] == null && this.F[1] == null && i < 2) {
                    this.F[2].n = false;
                    this.F[3].n = false;
                }
                if (this.F[2] == null && this.F[3] == null && i < 4) {
                    this.F[4].n = false;
                }
                if (i == 4) {
                    this.F[5].n = false;
                    this.F[6].n = false;
                    this.F[7].n = false;
                    this.F[8].n = false;
                }
                if (this.F[5] != null || this.F[6] != null || this.F[7] != null || this.F[8] != null) {
                    return true;
                }
                this.I = true;
                return true;
            }
        }
        return false;
    }
}
