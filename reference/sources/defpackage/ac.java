package defpackage;

import javax.microedition.lcdui.Graphics;

/* JADX INFO: loaded from: XForce.jar:ac.class */
public final class ac extends ai {
    private byte b;
    public byte a;
    private int c;
    private int p;
    private byte[] q;

    public ac() {
        this.n = true;
    }

    public final void a(byte b, int i, int i2, int i3, int i4, int i5) {
        this.b = b;
        this.g = i;
        this.h = i2;
        this.l = i3;
        this.m = i4;
        this.p = i5;
        this.c = 0;
        if (b == 4) {
            this.c = 5;
            this.b = (byte) 3;
        }
        if (b == 0 && c.d(2) == 0) {
            System.out.println("exp 1b");
            this.b = (byte) 11;
        }
        if (b == 6 || b == 8 || b == 9) {
            this.a = (byte) 0;
        } else {
            this.a = (byte) 1;
        }
        this.n = false;
    }

    public final void a(String str, int i, int i2, int i3, int i4) {
        this.q = c.d.a(str);
        this.b = (byte) 7;
        this.g = i;
        this.h = i2;
        this.l = i3;
        this.m = i4;
        this.n = false;
        this.a = (byte) 1;
        this.c = 0;
    }

    public final void a(Graphics graphics) {
        if (this.p > 0) {
            this.p--;
        }
        switch (this.b) {
            case 0:
                if (this.c == 0) {
                    p.a(1, b());
                }
                if (this.c < 14) {
                    c.h.a(this.c, this.g - 16, this.h - 16, 0, graphics);
                    this.c++;
                }
                if (this.c >= 14) {
                    this.n = true;
                }
                break;
            case 1:
                if (this.c == 0) {
                    p.a(0, b());
                }
                if (this.c < 5) {
                    c.i.a(this.c, this.g - 8, this.h - 8, 0, graphics);
                    this.c++;
                }
                if (this.c >= 5) {
                    this.n = true;
                }
                break;
            case 2:
                if (this.c >= 12) {
                    this.n = true;
                } else {
                    f();
                    c.f.a(this.c, this.g - 5, this.h - 5, 0, graphics);
                    this.c++;
                }
                break;
            case 3:
                if (this.c >= 18) {
                    this.n = true;
                } else {
                    f();
                    c.f.a(this.c, this.g - 5, this.h - 5, 0, graphics);
                    this.c++;
                }
                break;
            case 5:
                if (this.c % 4 == 0) {
                    this.l = c.d(1024);
                    this.m = c.d(1024);
                }
                if (this.c % 3 == 0) {
                    ab.a((byte) 3, this.g, this.h, this.l, this.m, 0);
                }
                this.c++;
                if (this.c >= 100) {
                    this.n = true;
                }
                break;
            case 6:
                c.y.a(this.c / 100, this.g - 16, this.h - 16, 0, graphics);
                this.c++;
                if (this.c >= 300) {
                    this.n = true;
                }
                break;
            case 7:
                c.d.a(this.q, this.g, this.h, 0, graphics);
                f();
                this.c++;
                if (this.c >= 15) {
                    this.n = true;
                }
                break;
            case 8:
                c.z.a(this.c / 50, this.g, this.h, 0, graphics);
                this.c++;
                if (this.c >= 70) {
                    this.n = true;
                }
                break;
            case 9:
                c.z.a(2 + (this.c / 50), this.g, this.h, 0, graphics);
                this.c++;
                if (this.c >= 70) {
                    this.n = true;
                }
                break;
            case 10:
                if (this.c >= 12) {
                    this.n = true;
                } else {
                    f();
                    c.g.a(this.c, this.g - 3, this.h - 3, 0, graphics);
                    this.c++;
                }
                break;
            case 11:
                if (this.c == 0) {
                    p.a(1, b());
                }
                if (this.c < 14) {
                    c.h.a(this.c, this.g - 16, this.h - 16, 0, graphics);
                    if (this.c % 3 == 0) {
                        ab.a((byte) 2, this.g + (this.c << 1), this.h + this.c, 0, 0, 0);
                        ab.a((byte) 2, this.g - (this.c * 3), this.h + this.c, 0, 0, 0);
                        ab.a((byte) 2, this.g + this.c, this.h - (this.c << 1), 0, 0, 0);
                    }
                    this.c++;
                }
                if (this.c >= 14) {
                    this.n = true;
                }
                break;
        }
    }

    private int b() {
        return 100 - ((Math.abs((this.g - ab.k) - (ab.i >> 1)) + Math.abs((this.h - ab.l) - (ab.j >> 1))) / 2);
    }
}
