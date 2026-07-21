package defpackage;

import javax.microedition.lcdui.Graphics;

/* JADX INFO: loaded from: XForce.jar:m.class */
public final class m extends an {
    public m(int i, int i2, byte b) {
        super(i, i2, b);
        a(i, i2, 24, 24);
        this.E = c.d(2);
        this.s = ab.m;
        this.w = (byte) -1;
        switch (b) {
            case 45:
                this.x = (short) 5;
                this.y = (short) 5;
                this.z = (short) 100;
                if (c.B == null) {
                    c.B = new ak(c.a("/gun.png"), 24, 24);
                }
                break;
            case 46:
                this.x = (short) 10;
                this.y = (short) 10;
                this.z = (short) 150;
                if (c.C == null) {
                    c.C = new ak(c.a("/gun1.png"), 24, 24);
                }
                break;
            case 47:
                if (c.E == null) {
                    c.E = new ak(c.a("/gun2.png"), 10, 10);
                }
                this.x = (short) 20;
                this.y = (short) 20;
                this.z = (short) 200;
                break;
            case 48:
                if (c.E == null) {
                    c.E = new ak(c.a("/gun2.png"), 10, 10);
                }
                this.x = (short) 100;
                this.y = (short) 100;
                this.z = (short) 500;
                break;
            case 49:
                if (c.D == null) {
                    c.D = new ak(c.a("/sgun.png"), 15, 15);
                }
                this.x = (short) 20;
                this.y = (short) 20;
                this.z = (short) 100;
                break;
            case 104:
            case 105:
                if (c.D == null) {
                    c.D = new ak(c.a("/sgun.png"), 15, 15);
                }
                this.x = (short) 200;
                this.y = (short) 200;
                this.z = (short) 1000;
                break;
            case 106:
                if (c.E == null) {
                    c.E = new ak(c.a("/gun2.png"), 10, 10);
                }
                this.x = (short) 500;
                this.y = (short) 500;
                this.z = (short) 2000;
                break;
            case 108:
                this.x = (short) 40;
                this.y = (short) 40;
                this.z = (short) 100;
                if (c.C == null) {
                    c.C = new ak(c.a("/gun1.png"), 24, 24);
                }
                break;
            case 109:
                this.x = (short) 60;
                this.y = (short) 60;
                this.z = (short) 100;
                if (c.D == null) {
                    c.D = new ak(c.a("/sgun.png"), 15, 15);
                }
                break;
        }
        ab.r++;
    }

    @Override // defpackage.an
    public final void a(Graphics graphics) {
        switch (this.v) {
            case 45:
                c.B.a(((this.q + 22) % 360) / 45, this.g, this.h, 0, graphics);
                break;
            case 46:
            case 108:
                c.C.a(((this.q + 22) % 360) / 45, this.g, this.h, 0, graphics);
                break;
            case 47:
            case 48:
                if (this.u) {
                    graphics.setColor(16777215);
                    graphics.fillRect(this.g, this.h, this.j, this.k);
                }
                if (this.D) {
                    graphics.setColor(16711680);
                } else {
                    graphics.setColor(52224);
                }
                int iB = this.g + 12 + ((c.b(c.c(this.q - 15)) * 100) >> 10);
                int iA = this.h + 12 + ((c.a(c.c(this.q - 15)) * 100) >> 10);
                int iB2 = this.g + 12 + ((c.b(c.c(this.q + 15)) * 100) >> 10);
                int iA2 = this.h + 12 + ((c.a(c.c(this.q + 15)) * 100) >> 10);
                graphics.drawLine(this.g + 12, this.h + 12, iB, iA);
                graphics.drawLine(this.g + 12, this.h + 12, iB2, iA2);
                graphics.drawLine(iB, iA, iB2, iA2);
                c.E.a((((this.q + 22) % 360) / 45) % 4, this.g + 7, this.h + 7, 0, graphics);
                break;
            case 49:
                c.D.a(((this.q + 22) % 360) / 45, this.g + 4, this.h + 4, 0, graphics);
                break;
            case 104:
            case 105:
                c.D.a(((this.q + 22) % 360) / 45, this.g, this.h, 0, graphics);
                break;
            case 106:
                this.E = (this.E + 1) % 4;
                c.E.a(this.E, this.g + 3, this.h + 3, 0, graphics);
                break;
            case 109:
                this.E = (this.E + 1) % 4;
                c.E.a(this.E, this.g + 7, this.h + 7, 0, graphics);
                break;
        }
        b(graphics);
        if (this.u) {
            this.u = false;
        }
    }

    @Override // defpackage.ai
    public final void a() {
        switch (this.v) {
            case 45:
                if (this.s != null && !this.s.o) {
                    int i = this.s.g - this.g;
                    int i2 = this.s.h - this.h;
                    this.q = c.a(i, i2);
                    if (this.C > 0) {
                        this.C = (byte) (this.C - 1);
                    } else if ((i * i) + (i2 * i2) < 9216) {
                        this.C = (byte) 40;
                        ab.a((byte) 20, this.g + 12 + ((c.b(this.q) * 12) >> 10), this.h + 12 + ((c.a(this.q) * 12) >> 10), this.q, null);
                    }
                    break;
                }
                break;
            case 46:
            case 108:
                if (this.s != null && !this.s.o) {
                    int i3 = this.s.g - this.g;
                    int i4 = this.s.h - this.h;
                    this.q = c.a(i3, i4);
                    if (this.C > 0) {
                        this.C = (byte) (this.C - 1);
                    } else if ((i3 * i3) + (i4 * i4) < 16384) {
                        this.C = (byte) 40;
                        ab.a((byte) 20, this.g + 12 + ((c.b(c.c(this.q + 15)) * 15) >> 10), this.h + 12 + ((c.a(c.c(this.q + 15)) * 15) >> 10), this.q, null);
                        ab.a((byte) 20, this.g + 12 + ((c.b(c.c(this.q - 15)) * 15) >> 10), this.h + 12 + ((c.a(c.c(this.q - 15)) * 15) >> 10), this.q, null);
                    }
                    break;
                }
                break;
            case 47:
            case 48:
                this.E++;
                if (this.E >= 2) {
                    this.E = 0;
                }
                if (this.C > 0) {
                    this.C = (byte) (this.C - 1);
                }
                if (!this.D) {
                    this.q = c.c(this.q + 2);
                }
                if (this.s != null && !this.s.o && this.E == 0) {
                    int i5 = (this.s.g + (this.s.j >> 1)) - (this.g + 12);
                    int i6 = (this.s.h + (this.s.k >> 1)) - (this.h + 12);
                    this.D = false;
                    if ((i5 * i5) + (i6 * i6) < 10000) {
                        int iA = c.a(i5, i6);
                        if (Math.abs(c.b(iA, this.q)) <= 15) {
                            this.q = iA;
                            this.D = true;
                            if (this.C == 0) {
                                this.C = (byte) 50;
                                for (int i7 = -3; i7 < 4; i7++) {
                                    ab.a((byte) 20, this.g + 12, this.h + 12, c.c(iA + (i7 * 10)), null);
                                }
                            }
                        }
                    }
                    break;
                }
                break;
            case 49:
                if (this.s != null && !this.s.o) {
                    int i8 = this.s.g - this.g;
                    int i9 = this.s.h - this.h;
                    this.q = c.a(i8, i9);
                    if (this.C > 0) {
                        this.C = (byte) (this.C - 1);
                    } else {
                        this.C = (byte) 100;
                    }
                    if ((this.C == 80 || this.C == 100) && (i8 * i8) + (i9 * i9) < 16384) {
                        ab.a((byte) 11, this.g + 12, this.h + 12, this.q, this.s);
                    }
                    break;
                }
                break;
            case 104:
                if (this.s != null && !this.s.o) {
                    this.q = c.a(this.s.g - this.g, this.s.h - this.h);
                    if (this.C > 0) {
                        this.C = (byte) (this.C - 1);
                    } else {
                        this.C = (byte) 40;
                        v vVarA = ab.a((byte) 20, this.g + 12 + ((c.b(this.q) * 12) >> 10), this.h + 12 + ((c.a(this.q) * 12) >> 10), this.q, null);
                        if (vVarA != null) {
                            vVarA.l += ab.m.l;
                            vVarA.m += ab.m.m;
                        }
                    }
                    break;
                }
                break;
            case 105:
                if (this.s != null && !this.s.o) {
                    this.E++;
                    if (this.E >= 2) {
                        this.E = 0;
                    }
                    if (this.C > 0) {
                        this.C = (byte) (this.C - 1);
                    }
                    if (this.E == 0) {
                        int i10 = (this.s.g + (this.s.j >> 1)) - (this.g + (this.j >> 1));
                        int i11 = (this.s.h + (this.s.k >> 1)) - (this.h + (this.k >> 1));
                        this.D = false;
                        this.q = c.a(i10, i11);
                        this.D = true;
                        if (this.C == 0) {
                            this.C = (byte) 50;
                        }
                        if (this.C > 40) {
                            ab.a((byte) 20, this.g + 12 + ((c.b(this.q) * 12) >> 10), this.h + 12 + ((c.a(this.q) * 12) >> 10), this.q, null);
                        }
                    }
                    break;
                }
                break;
            case 106:
                if (this.s != null && !this.s.o) {
                    this.q = c.a(this.s.g - this.g, this.s.h - this.h);
                    if (this.C > 0) {
                        this.C = (byte) (this.C - 1);
                    } else {
                        this.C = (byte) 50;
                        for (int i12 = 0; i12 < 360; i12 += 30) {
                            ab.a((byte) 21, this.g + 12, this.h + 12, i12, null);
                        }
                    }
                    break;
                }
                break;
            case 109:
                if (this.s != null && !this.s.o) {
                    int i13 = this.s.g - this.g;
                    int i14 = this.s.h - this.h;
                    if (this.C > 0) {
                        this.C = (byte) (this.C - 1);
                    } else if ((i13 * i13) + (i14 * i14) < 16384) {
                        this.C = (byte) 50;
                        for (int i15 = 0; i15 < 360; i15 += 30) {
                            ab.a((byte) 21, this.g + 12, this.h + 12, i15, null);
                        }
                    }
                    break;
                }
                break;
        }
    }

    @Override // defpackage.an
    public final void b() {
        int i = this.g / r.a;
        int i2 = this.h / r.a;
        switch (this.v) {
            case 45:
            case 46:
                r.c[i2][i] = 56;
                break;
            case 47:
            case 48:
                r.c[i2][i] = 57;
                break;
            case 49:
                r.c[i2][i] = 58;
                break;
        }
        switch (this.v) {
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
                ab.a((byte) 0, this.g + (this.j / 2), this.h + (this.k / 2), 0, 0, 0);
                ab.a((byte) 5, this.g + (this.j / 2), this.h + (this.k / 2), 0, 0, 0);
                ab.b(i2, i, this);
                break;
            case 104:
            case 105:
            case 106:
            case 107:
            case 108:
                ab.a((byte) 0, this.g + (this.j / 2), this.h + (this.k / 2), 0, 0, 0);
                break;
        }
        ab.s++;
        ab.a(new StringBuffer("+").append((int) this.z).append("XP").toString(), this.g, this.h);
        ab.b(this.z);
        this.o = true;
    }
}
