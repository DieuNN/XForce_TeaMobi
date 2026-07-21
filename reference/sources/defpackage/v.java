package defpackage;

import javax.microedition.lcdui.Graphics;

/* JADX INFO: loaded from: XForce.jar:v.class */
public final class v extends ai {
    public byte a;
    private byte b;
    private int c;
    private short p;
    private static int q;
    private static int r;
    private byte s;
    private byte t;
    private byte u;
    private byte v;
    private an w;

    public v() {
    }

    public v(byte b, int i, int i2) {
        a((byte) 18, i, i2, 0, null);
    }

    public final void a(byte b, int i, int i2, int i3, an anVar) {
        this.g = i;
        this.h = i2;
        this.b = b;
        this.w = anVar;
        this.l = 0;
        this.m = 0;
        this.p = (short) 0;
        this.u = (byte) 100;
        this.i = 0;
        switch (b) {
            case 0:
                this.p = (short) 12288;
                this.v = (byte) 0;
                this.a = (byte) 1;
                break;
            case 1:
                this.p = (short) 13312;
                this.v = (byte) 4;
                this.a = (byte) 2;
                break;
            case 2:
                this.p = (short) 14336;
                this.v = (byte) 8;
                this.a = (byte) 4;
                break;
            case 3:
                this.p = (short) 15360;
                this.v = (byte) 12;
                this.a = (byte) 6;
                break;
            case 4:
                this.p = (short) 16384;
                this.v = (byte) 16;
                this.a = (byte) 10;
                break;
            case 10:
                this.p = (short) 0;
                this.a = (byte) 3;
                break;
            case 11:
                this.c = i3;
                this.p = (short) 0;
                this.u = (byte) 50;
                this.a = (byte) 2;
                break;
            case 14:
                this.p = (short) 0;
                this.u = (byte) 16;
                this.a = (byte) 10;
                break;
            case 15:
                this.p = (short) 0;
                this.u = (byte) 8;
                this.a = (byte) 5;
                break;
            case 16:
                this.p = (short) 0;
                this.u = (byte) 50;
                this.a = (byte) 100;
                break;
            case 17:
                this.a = (byte) 100;
                break;
            case 18:
                this.a = (byte) 15;
                break;
            case 20:
                this.v = (byte) 20;
                this.p = (short) 4096;
                this.a = (byte) 1;
                break;
            case 21:
                this.v = (byte) 23;
                this.p = (short) 2048;
                this.a = (byte) 3;
                break;
            case 22:
                this.v = (byte) 26;
                this.p = (short) 4096;
                this.a = (byte) 2;
                break;
        }
        this.l = (this.p * c.b(i3)) >> 10;
        this.m = (this.p * c.a(i3)) >> 10;
        switch (i3) {
            case 0:
                this.s = (byte) 3;
                break;
            case 90:
                this.s = (byte) 1;
                break;
            case 180:
                this.s = (byte) 2;
                break;
            case 270:
                this.s = (byte) 0;
                break;
        }
        this.n = false;
    }

    public final void a(Graphics graphics) {
        switch (this.b) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                c.K.a(this.v + this.s, this.g, this.h, 0, graphics);
                break;
            case 10:
            case 11:
                q = (((this.g - ai.e) * this.i) / ai.d) + this.g;
                r = (((this.h - ai.f) * this.i) / ai.d) + this.h;
                int iB = q + ((5 * c.b(this.c)) >> 10);
                int iA = r + ((5 * c.a(this.c)) >> 10);
                graphics.setColor(16777215);
                graphics.drawLine(q, r, iB, iA);
                break;
            case 14:
                int i = this.u > 8 ? 32 - ((this.u - 8) << 2) : 32 - (this.u << 2);
                graphics.setColor(16776960);
                graphics.drawArc(this.g - i, this.h - i, i + i, i + i, 0, 360);
                break;
            case 15:
                graphics.setColor(16777215);
                graphics.drawArc(this.g - this.u, this.h - this.u, this.u + this.u, this.u + this.u, 0, 360);
                break;
            case 16:
                c.A.a(4, this.g - 8, this.h - 8, 0, graphics);
                if (this.u > 20) {
                    graphics.setColor(this.u % 8 < 4 ? 0 : 16711680);
                } else {
                    graphics.setColor(this.u % 4 < 2 ? 0 : 16711680);
                }
                graphics.fillRect(this.g, this.h - 1, 3, 3);
                break;
            case 17:
                c.A.a(5, this.g - 8, this.h - 8, 0, graphics);
                graphics.setColor(this.t < 4 ? 11141120 : 16711680);
                graphics.fillRect(this.g - 1, this.h - 1, 2, 2);
                this.t = (byte) (this.t + 1);
                if (this.t >= 8) {
                    this.t = (byte) 0;
                }
                break;
            case 18:
                graphics.setColor(0);
                graphics.fillRect(this.g - 3, this.h - 3, 6, 6);
                graphics.setColor(this.t < 4 ? 11141120 : 16711680);
                graphics.fillRect(this.g - 1, this.h - 1, 2, 2);
                this.t = (byte) (this.t + 1);
                if (this.t >= 8) {
                    this.t = (byte) 0;
                }
                break;
            case 20:
            case 21:
                this.t = (byte) (this.t + 1);
                if (this.t >= 3) {
                    this.t = (byte) 0;
                }
                c.K.a(this.v + this.t, this.g, this.h, 0, graphics);
                break;
            case 22:
                this.t = (byte) (this.t + 1);
                if (this.t >= 4) {
                    this.t = (byte) 0;
                }
                c.K.a(this.v + this.t, this.g, this.h, 0, graphics);
                break;
        }
    }

    @Override // defpackage.ai
    public final void a() {
        if (this.g < 0 || this.h < 0 || this.g >= r.g || this.h >= r.h || this.u <= 0) {
            this.n = true;
            return;
        }
        if (this.b < 10) {
            an anVarE = ab.e(this.h / r.a, this.g / r.a);
            if (anVarE == null || anVarE.w == 1) {
                if (r.b(this.g, this.h, this.a)) {
                    if (this.b == 4) {
                        this.b = (byte) 3;
                    } else {
                        this.n = true;
                    }
                } else if (ab.u != null && ab.u.w == -1 && ab.u.a(this)) {
                    this.n = true;
                }
            } else if (anVarE.a(this)) {
                if (this.b == 4) {
                    this.b = (byte) 3;
                } else {
                    this.n = true;
                }
            }
            if (!ab.a(this)) {
                this.n = true;
            }
        }
        if (this.b >= 20) {
            an anVarE2 = ab.e(this.h / r.a, this.g / r.a);
            if ((anVarE2 != null && anVarE2.w == 1 && anVarE2.a(this)) || ab.m.a(this) || r.c(this.g, this.h, this.a)) {
                this.n = true;
            }
        }
        switch (this.b) {
            case 10:
                if (this.w != null) {
                    int i = (this.w.g + (this.w.j >> 1)) - this.g;
                    int i2 = (this.w.h + (this.w.k >> 1)) - this.h;
                    int iA = c.a(i, i2);
                    if (Math.abs(iA - this.c) < 90 || (i * i) + (i2 * i2) > 4096) {
                        if (Math.abs(iA - this.c) < 15) {
                            this.c = iA;
                        } else if ((iA - this.c < 0 || iA - this.c >= 180) && iA - this.c >= -180) {
                            this.c = c.c(this.c - 15);
                        } else {
                            this.c = c.c(this.c + 15);
                        }
                    }
                    if (this.p < 8192) {
                        this.p = (short) (this.p + 1024);
                    }
                    this.l = (this.p * c.b(this.c)) >> 10;
                    this.m = (this.p * c.a(this.c)) >> 10;
                    if (this.i < this.w.i) {
                        this.i++;
                    }
                    if (this.i > this.w.i) {
                        this.i--;
                    }
                    q = (((this.g - ai.e) * this.i) / ai.d) + this.g;
                    r = (((this.h - ai.f) * this.i) / ai.d) + this.h;
                    ab.a((byte) 2, q, r, 0, 0, 0);
                    if (this.i == this.w.i && this.w.a(this)) {
                        this.n = true;
                    }
                }
                break;
            case 11:
                if (this.w != null) {
                    int iA2 = c.a((this.w.g + (this.w.j >> 1)) - this.g, (this.w.h + (this.w.k >> 1)) - this.h);
                    if (this.u > 37) {
                        if (Math.abs(iA2 - this.c) < 15) {
                            this.c = iA2;
                        } else if ((iA2 - this.c < 0 || iA2 - this.c >= 180) && iA2 - this.c >= -180) {
                            this.c = c.c(this.c - 15);
                        } else {
                            this.c = c.c(this.c + 15);
                        }
                    }
                    if (this.p < 8192) {
                        this.p = (short) (this.p + 1024);
                    }
                    this.l = (this.p * c.b(this.c)) >> 10;
                    this.m = (this.p * c.a(this.c)) >> 10;
                    q = (((this.g - ai.e) * this.i) / ai.d) + this.g;
                    r = (((this.h - ai.f) * this.i) / ai.d) + this.h;
                    ab.a((byte) 10, q, r, 0, 0, 0);
                    if (this.w.a(this)) {
                        this.n = true;
                    }
                }
                break;
            case 14:
                int i3 = this.h / r.a;
                int i4 = i3;
                int i5 = this.g / r.a;
                int i6 = i5;
                if (this.u == 10) {
                    i4 = i3 - 1;
                }
                if (this.u == 7) {
                    i4 = i3 + 1;
                }
                if (this.u == 4) {
                    i6 = i5 - 1;
                }
                if (this.u == 1) {
                    i6 = i5 + 1;
                    this.n = true;
                }
                if ((this.u == 10 || this.u == 7 || this.u == 4 || this.u == 1) && i4 >= 0 && i4 < r.f && i6 >= 0 && i6 < r.e) {
                    if (ab.n[i4][i6] != null) {
                        ab.n[i4][i6].a(this.a);
                    }
                    r.b((i6 * r.a) + 12, (i4 * r.a) + 12, this.a);
                }
                break;
            case 15:
                if (this.u == 1) {
                    int i7 = this.h / r.a;
                    int i8 = this.g / r.a;
                    if (ab.n[i7][i8] != null) {
                        ab.n[i7][i8].a(this.a);
                    }
                    r.c(this.g, this.h, this.a);
                    ab.a((byte) 0, this.g, this.h, 0, 0, 0);
                    ab.a((byte) 6, this.g, this.h, 0, 0, 0);
                    ab.t = 10;
                    this.n = true;
                }
                break;
            case 16:
                if (this.u == 1) {
                    int i9 = this.h / r.a;
                    int i10 = this.g / r.a;
                    r.b(this.g, this.h - 24, this.a);
                    r.b(this.g, this.h + 24, this.a);
                    r.b(this.g - 24, this.h, this.a);
                    r.b(this.g + 24, this.h, this.a);
                    System.out.println("error yet?");
                    an anVarE3 = ab.e(i9 - 1, i10);
                    if (anVarE3 != null) {
                        anVarE3.a(this.a);
                    }
                    an anVarE4 = ab.e(i9 + 1, i10);
                    if (anVarE4 != null) {
                        anVarE4.a(this.a);
                    }
                    an anVarE5 = ab.e(i9, i10 - 1);
                    if (anVarE5 != null) {
                        anVarE5.a(this.a);
                    }
                    an anVarE6 = ab.e(i9, i10 + 1);
                    if (anVarE6 != null) {
                        anVarE6.a(this.a);
                    }
                    ab.a((byte) 0, this.g, this.h, 0, 0, 0);
                    ab.a((byte) 6, this.g, this.h, 0, 0, 0);
                    ab.t = 10;
                    this.n = true;
                }
                break;
            case 17:
                if (ab.u.a(this)) {
                    this.n = true;
                }
                this.u = (byte) 100;
                break;
            case 18:
                an anVarE7 = ab.e(this.h / r.a, this.g / r.a);
                if (anVarE7 != null && anVarE7.w == -1 && anVarE7.a(this)) {
                    this.o = true;
                }
                this.u = (byte) 100;
                break;
        }
        f();
        if (this.u > 0) {
            this.u = (byte) (this.u - 1);
        }
    }
}
