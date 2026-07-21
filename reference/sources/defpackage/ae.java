package defpackage;

import javax.microedition.lcdui.Graphics;

/* JADX INFO: loaded from: XForce.jar:ae.class */
public final class ae extends an {
    private an[] a;
    private int[] b;
    private boolean c;
    private int F;
    private static byte[] G = {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, 0, 0};
    private boolean H;

    public ae(int i, int i2) {
        super(i, i2, (byte) 102);
        this.b = new int[]{14, 126, 36, 108, 70};
        this.j = 176;
        this.k = 33;
        if (c.N == null) {
            c.N = c.a("/ship.png");
        }
        if (c.O == null) {
            c.O = c.a("/ship1.png");
        }
        this.l = 0;
        this.H = false;
        this.a = new m[5];
        this.a[0] = new m(0, 0, (byte) 108);
        this.a[1] = new m(0, 0, (byte) 108);
        this.a[2] = new m(0, 0, (byte) 108);
        this.a[2].n = true;
        this.a[2].q = 180;
        this.a[3] = new m(0, 0, (byte) 108);
        this.a[3].n = true;
        this.a[4] = new m(0, 0, (byte) 109);
        this.a[4].n = true;
        for (int i3 = 0; i3 < 7; i3++) {
            ab.a((i2 + 16) / r.a, ((i + 16) / r.a) + i3, this);
        }
    }

    @Override // defpackage.an
    public final void a(Graphics graphics) {
        if (this.c) {
            graphics.drawImage(c.O, this.g, this.h, 0);
        } else {
            this.F++;
            if (this.F == G.length) {
                this.F = 0;
            }
            graphics.drawImage(c.N, this.g, this.h + G[this.F], 0);
        }
        for (int i = 0; i < 5; i++) {
            if (this.a[i] != null) {
                this.a[i].a(graphics);
            }
        }
    }

    @Override // defpackage.ai
    public final void a() {
        if (this.g > r.e * 24 && ab.K == 0) {
            ab.a(false);
        }
        if (this.H) {
            int i = this.g / 24;
            int i2 = (this.h / 24) + 2;
            this.m = 0;
            if (i < r.e - 5) {
                if (r.c[i2][i] == 36 && r.c[i2][i + 1] == 36 && r.c[i2][i + 2] == 36 && r.c[i2][i + 3] == 36 && r.c[i2][i + 4] == 36) {
                    this.m = 256;
                }
                if (i < r.e - 8) {
                    int i3 = i + 5;
                    if (r.c[i2][i3] != 36 || r.c[i2][i3 + 1] != 36 || r.c[i2][i3 + 2] != 36) {
                        this.m = -256;
                    }
                }
            }
            if (this.l < 1024) {
                this.l += 128;
            }
            f();
            ab.a((this.h + 16) / r.a, ((this.g + 16) / r.a) + 7, this);
            for (int i4 = -2; i4 < 3; i4++) {
                ab.b(((this.h + 16) / r.a) + i4, ((this.g + 16) / r.a) - 1, this);
            }
        }
        if (this.E == 0) {
            ab.a((byte) 10, this.g, this.h + 14, -512, 0, 0);
        }
        if (this.E == 2) {
            ab.a((byte) 10, this.g, this.h + 18, -1024, 0, 0);
        }
        this.E++;
        if (this.E >= 5) {
            this.E = 0;
        }
        for (int i5 = 0; i5 < 5; i5++) {
            if (this.a[i5] != null) {
                this.a[i5].a(this.g + this.b[i5], this.h + 5);
                if (!this.a[i5].n) {
                    this.a[i5].a();
                }
            }
        }
    }

    @Override // defpackage.an
    public final boolean a(v vVar) {
        for (int i = 0; i < 5; i++) {
            if (this.a[i] != null && !this.a[i].n && this.a[i].a(vVar)) {
                if (!this.a[i].o) {
                    return true;
                }
                if (!this.H && f.a == 11) {
                    this.H = true;
                }
                if (i == 0 && this.a[1] == null) {
                    this.a[2].n = false;
                    this.a[3].n = false;
                }
                if (i == 1 && this.a[0] == null) {
                    this.a[2].n = false;
                    this.a[3].n = false;
                }
                if (i == 2 && this.a[3] == null) {
                    this.a[4].n = false;
                }
                if (i == 3 && this.a[2] == null) {
                    this.a[4].n = false;
                }
                if (i == 4) {
                    b();
                }
                this.a[i] = null;
                return true;
            }
        }
        return false;
    }

    @Override // defpackage.an
    public final void b() {
        this.l = 0;
        this.c = true;
        ab.a((byte) 0, this.g + 20, this.h + 10, 0, 0, 5);
        ab.a((byte) 0, this.g + 80, this.h + 20, 0, 0, 2);
        ab.a((byte) 0, this.g + 120, this.h + 16, 0, 0, 9);
        ab.a((byte) 0, this.g + 40, this.h + 10, 0, 0, 12);
        ab.a((byte) 0, this.g + 100, this.h + 16, 0, 0, 15);
        if (f.a == 14) {
            s sVar = new s(1008, 864);
            ab.u = sVar;
            sVar.w = (byte) -1;
            ab.a(ab.u);
        } else {
            ab.a(true);
        }
        this.H = false;
    }

    @Override // defpackage.ai
    public final boolean a(ai aiVar) {
        return false;
    }
}
