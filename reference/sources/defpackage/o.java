package defpackage;

import javax.microedition.lcdui.Graphics;

/* JADX INFO: loaded from: XForce.jar:o.class */
public final class o extends an {
    private boolean[] a;
    private byte[] b;
    private byte[] c;
    private byte[] F;
    private int G;
    private int H;
    private int I;
    private int J;
    private int K;
    private boolean L;
    private boolean M;
    private int N;
    private int O;
    private int P;
    private int Q;

    public o(int i, int i2, byte b) {
        super(i, i2, b);
        this.a = new boolean[4];
        this.b = new byte[]{1, 0, -1, 0};
        this.c = new byte[]{0, 1, 0, -1};
        this.F = new byte[]{0, 1, 2, 3};
        this.P = 3;
        a(i, i2, 24, 24);
        this.N = i;
        this.O = i2;
        this.w = (byte) -1;
        switch (b) {
            case 1:
                this.I = 2048;
                this.y = (short) 2;
                this.x = (short) 2;
                this.z = (short) 50;
                if (c.u[0] == null) {
                    c.u[0] = new ak(c.a("/tank1.png"), 24, 24);
                }
                break;
            case 2:
                this.I = 3072;
                this.y = (short) 5;
                this.x = (short) 5;
                this.z = (short) 100;
                if (c.u[1] == null) {
                    c.u[1] = new ak(c.a("/tank2.png"), 24, 24);
                }
                break;
            case 3:
                this.I = 1024;
                this.J = 100;
                this.y = (short) 7;
                this.x = (short) 7;
                this.z = (short) 150;
                if (c.u[2] == null) {
                    c.u[2] = new ak(c.a("/tank3.png"), 24, 24);
                }
                break;
            case 4:
                this.I = 2048;
                this.J = 120;
                this.y = (short) 10;
                this.x = (short) 10;
                this.z = (short) 200;
                if (c.u[3] == null) {
                    c.u[3] = new ak(c.a("/tank4.png"), 24, 24);
                }
                break;
            case 5:
                this.I = 2048;
                this.y = (short) 12;
                this.x = (short) 12;
                this.z = (short) 50;
                if (c.u[4] == null) {
                    c.u[4] = new ak(c.a("/tank5.png"), 24, 24);
                }
                break;
            case 6:
                this.I = 3072;
                this.y = (short) 15;
                this.x = (short) 15;
                this.z = (short) 100;
                if (c.u[5] == null) {
                    c.u[5] = new ak(c.a("/tank6.png"), 24, 24);
                }
                break;
            case 7:
                this.I = 1024;
                this.y = (short) 17;
                this.x = (short) 17;
                this.z = (short) 150;
                this.L = true;
                if (c.u[6] == null) {
                    c.u[6] = new ak(c.a("/tank7.png"), 24, 24);
                }
                break;
            case 8:
                this.I = 2048;
                this.J = 120;
                this.y = (short) 20;
                this.x = (short) 20;
                this.z = (short) 200;
                this.L = true;
                if (c.u[7] == null) {
                    c.u[7] = new ak(c.a("/tank8.png"), 24, 24);
                }
                break;
            case 9:
                this.I = 1024;
                this.J = 100;
                this.y = (short) 27;
                this.x = (short) 27;
                this.z = (short) 150;
                this.L = true;
                if (c.u[8] == null) {
                    c.u[8] = new ak(c.a("/tank9.png"), 24, 24);
                }
                break;
            case 10:
                this.I = 2048;
                this.y = (short) 30;
                this.x = (short) 30;
                this.z = (short) 200;
                this.L = true;
                if (c.u[9] == null) {
                    c.u[9] = new ak(c.a("/tank10.png"), 24, 24);
                }
                break;
            case 111:
                this.I = 1024;
                this.y = (short) 80;
                this.x = (short) 80;
                this.z = (short) 2000;
                this.M = true;
                this.L = true;
                if (c.v == null) {
                    c.v = new ak(c.a("/tankboss.png"), 24, 24);
                    c.w = new ak(c.a("/tg3.png"), 32, 32);
                }
                break;
        }
        this.p = c.e(4);
        this.m = 0;
        this.l = 0;
        this.H = 5;
        this.G = 5;
        this.s = ab.m;
        System.out.println(new StringBuffer("create tank ").append((int) b).toString());
    }

    @Override // defpackage.an
    public final void a(Graphics graphics) {
        if (this.Q > 0) {
            return;
        }
        if (!this.u) {
            if (this.v == 4 || this.v == 3 || this.v == 8 || this.v == 9) {
                graphics.setColor(this.D ? 16711680 : 52224);
                int iB = this.g + 12 + ((c.b(c.c(this.q - 15)) * this.J) >> 10);
                int iA = this.h + 12 + ((c.a(c.c(this.q - 15)) * this.J) >> 10);
                int iB2 = this.g + 12 + ((c.b(c.c(this.q + 15)) * this.J) >> 10);
                int iA2 = this.h + 12 + ((c.a(c.c(this.q + 15)) * this.J) >> 10);
                graphics.drawLine(this.g + (this.j >> 1), this.h + (this.k >> 1), iB, iA);
                graphics.drawLine(this.g + (this.j >> 1), this.h + (this.k >> 1), iB2, iA2);
                graphics.drawLine(iB, iA, iB2, iA2);
            }
            switch (this.v) {
                case 1:
                    c.u[0].a(this.p, this.g, this.h, 0, graphics);
                    break;
                case 2:
                    c.u[1].a(this.p, this.g, this.h, 0, graphics);
                    break;
                case 3:
                    c.u[2].a(this.p, this.g, this.h, 0, graphics);
                    break;
                case 4:
                    c.u[3].a(this.p, this.g, this.h, 0, graphics);
                    break;
                case 5:
                    c.u[4].a(this.p, this.g, this.h, 0, graphics);
                    break;
                case 6:
                    c.u[5].a(this.p, this.g, this.h, 0, graphics);
                    break;
                case 7:
                    c.u[6].a(this.p, this.g, this.h, 0, graphics);
                    break;
                case 8:
                    c.u[7].a(this.p, this.g, this.h, 0, graphics);
                    break;
                case 9:
                    c.u[8].a(this.p, this.g, this.h, 0, graphics);
                    break;
                case 10:
                    c.u[9].a(this.p, this.g, this.h, 0, graphics);
                    break;
                case 111:
                    if (this.x <= 0) {
                        c.v.a(4, this.g, this.h, 0, graphics);
                    } else {
                        c.v.a(this.p, this.g, this.h, 0, graphics);
                    }
                    if (this.M) {
                        c.w.a(((this.q + 22) % 360) / 45, this.g - 4, this.h - 4, 0, graphics);
                    }
                    break;
            }
        } else {
            this.u = false;
        }
        b(graphics);
    }

    /* JADX WARN: Code duplicated, block: B:143:0x04d1  */
    /* JADX WARN: Code duplicated, block: B:145:0x04fa  */
    /* JADX WARN: Code duplicated, block: B:146:0x04fe  */
    /* JADX WARN: Code duplicated, block: B:149:0x050f  */
    /* JADX WARN: Code duplicated, block: B:150:0x0513  */
    /* JADX WARN: Code duplicated, block: B:153:0x0524  */
    /* JADX WARN: Code duplicated, block: B:154:0x0528  */
    /* JADX WARN: Code duplicated, block: B:157:0x0539  */
    /* JADX WARN: Code duplicated, block: B:158:0x053d  */
    /* JADX WARN: Code duplicated, block: B:161:0x0546  */
    /* JADX WARN: Code duplicated, block: B:175:0x05af  */
    /* JADX WARN: Code duplicated, block: B:178:0x05b9  */
    /* JADX WARN: Code duplicated, block: B:181:0x05c3  */
    /* JADX WARN: Code duplicated, block: B:184:0x05cd  */
    /* JADX WARN: Code duplicated, block: B:211:0x069b  */
    /* JADX WARN: Code duplicated, block: B:213:0x06a3  */
    /* JADX WARN: Code duplicated, block: B:214:0x06ac A[LOOP:1: B:215:0x06e2->B:214:0x06ac, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:218:0x06f8  */
    /* JADX WARN: Code duplicated, block: B:230:0x07a5  */
    /* JADX WARN: Code duplicated, block: B:231:0x07ab  */
    /* JADX WARN: Code duplicated, block: B:234:0x07fd A[LOOP:3: B:235:0x0800->B:234:0x07fd, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:238:0x080e  */
    /* JADX WARN: Code duplicated, block: B:242:0x0822  */
    /* JADX WARN: Code duplicated, block: B:247:0x0846  */
    /* JADX WARN: Code duplicated, block: B:260:0x07ba A[SYNTHETIC] */
    @Override // defpackage.ai
    public final void a() {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        if (this.Q > 0) {
            if (this.Q > 0) {
                this.Q--;
            }
            if (this.Q == 0) {
                if (ab.a((ai) this)) {
                    this.Q = 1;
                    return;
                } else {
                    this.n = false;
                    return;
                }
            }
            return;
        }
        if (this.x <= 0) {
            return;
        }
        switch (this.v) {
            case 3:
            case 4:
            case 8:
            case 9:
                this.E++;
                if (this.E >= 2) {
                    this.E = 0;
                }
                if (this.C > 0) {
                    this.C = (byte) (this.C - 1);
                }
                if (!this.D) {
                    switch (this.p) {
                        case 0:
                            if (this.q < 315 && this.q > 180) {
                                this.G = this.H;
                            }
                            if (this.q > 45 && this.q < 180) {
                                this.G = -this.H;
                            }
                            break;
                        case 1:
                            if (this.q < 45) {
                                this.G = this.H;
                            }
                            if (this.q > 135) {
                                this.G = -this.H;
                            }
                            break;
                        case 2:
                            if (this.q < 135) {
                                this.G = this.H;
                            }
                            if (this.q > 225) {
                                this.G = -this.H;
                            }
                            break;
                        case 3:
                            if (this.q < 225) {
                                this.G = this.H;
                            }
                            if (this.q > 315) {
                                this.G = -this.H;
                            }
                            break;
                    }
                    this.q = c.c(this.q + this.G);
                }
                if (this.s != null && !this.s.o && this.E == 0) {
                    int i6 = (this.s.g + (this.s.j >> 1)) - (this.g + (this.j >> 1));
                    int i7 = (this.s.h + (this.s.k >> 1)) - (this.h + (this.k >> 1));
                    this.D = false;
                    if ((i6 * i6) + (i7 * i7) < this.J * this.J) {
                        int iA = c.a(i6, i7);
                        if (Math.abs(c.b(iA, this.q)) <= 15) {
                            this.q = iA;
                            this.D = true;
                            if (this.C == 0) {
                                this.C = (byte) 50;
                            }
                            if (this.C > 40) {
                                ab.a((byte) 20, this.g + (this.j >> 1), this.h + (this.k >> 1), iA, null);
                            }
                        }
                    }
                }
                break;
            case 5:
            case 6:
            case 7:
                if (this.C > 0) {
                    this.C = (byte) (this.C - 1);
                } else {
                    this.C = (byte) 50;
                }
                if (this.C == 50 || this.C == 40) {
                    ab.a((byte) 22, this.g + 12, this.h + 12, this.p * 90, null);
                }
                break;
            case 10:
                if (this.C > 0) {
                    this.C = (byte) (this.C - 1);
                } else {
                    this.C = (byte) 50;
                }
                if (this.C == 50 || this.C == 45 || this.C == 40) {
                    ab.a((byte) 22, this.g + 12, this.h + 12, this.p * 90, null);
                }
                break;
            case 111:
                if (this.M) {
                    if (this.x < 20) {
                        this.M = false;
                        ab.a((byte) 0, this.g + 12, this.h + 12, 0, 0, 0);
                    } else {
                        this.q = c.a(this.s.g - this.g, this.s.h - this.h);
                        if (this.C > 0) {
                            this.C = (byte) (this.C - 1);
                        } else {
                            this.C = (byte) 60;
                        }
                        if (this.C > 40 && this.C % 4 == 0) {
                            ab.a((byte) 22, this.g + 12 + ((c.b(this.q) << 4) >> 10), this.h + 12 + ((c.a(this.q) << 4) >> 10), this.q, null);
                        }
                    }
                } else if (c.d(3) == 0) {
                    ab.a((byte) 2, this.g + 12, this.h + 12, c.d(1024), c.d(1024), 0);
                }
                break;
            default:
                if (this.C > 0) {
                    this.C = (byte) (this.C - 1);
                } else {
                    this.C = (byte) 50;
                    ab.a((byte) 20, this.g + 12, this.h + 12, this.p * 90, null);
                }
                break;
        }
        if (this.D) {
            return;
        }
        if (this.g % r.a == 0 && this.h % r.a == 0) {
            if (this.v == 111) {
                if (this.p == 0 || this.p == 2) {
                    ab.a((byte) 9, this.g, this.h, 0, 0, 0);
                } else {
                    ab.a((byte) 8, this.g, this.h, 0, 0, 0);
                }
                if (this.x < 50 && this.L) {
                    this.I = 2048;
                    this.L = false;
                }
                if (this.x < 20) {
                    this.I = 3072;
                }
            }
            boolean z5 = false;
            int i8 = this.h / r.a;
            int i9 = this.g / r.a;
            if (this.K > 1) {
                this.K--;
                if (this.L) {
                    ab.b(i8 - this.c[this.p], i9 - this.b[this.p], this);
                    boolean[] zArr = this.a;
                    if (r.a(i8, i9 + 1)) {
                        z = false;
                    } else {
                        z = true;
                    }
                    zArr[0] = z;
                    boolean[] zArr2 = this.a;
                    if (r.a(i8 + 1, i9)) {
                        z2 = false;
                    } else {
                        z2 = true;
                    }
                    zArr2[1] = z2;
                    boolean[] zArr3 = this.a;
                    if (r.a(i8, i9 - 1)) {
                        z3 = false;
                    } else {
                        z3 = true;
                    }
                    zArr3[2] = z3;
                    boolean[] zArr4 = this.a;
                    if (r.a(i8 - 1, i9)) {
                        z4 = false;
                    } else {
                        z4 = true;
                    }
                    zArr4[3] = z4;
                    if (this.L) {
                        i4 = ((this.s.g + 12) / 24) - ((this.g + 12) / 24);
                        i5 = ((this.s.h + 12) / 24) - ((this.h + 12) / 24);
                        if (Math.abs(i4) < 10 && Math.abs(i5) < 10) {
                            if ((i4 != 0 || i5 == 0) && Math.abs(i4) < 4 && Math.abs(i5) < 4) {
                                if (i4 > 0) {
                                    this.p = 0;
                                }
                                if (i4 < 0) {
                                    this.p = 2;
                                }
                                if (i5 > 0) {
                                    this.p = 1;
                                }
                                if (i5 < 0) {
                                    this.p = 3;
                                }
                                this.l = 0;
                                this.m = 0;
                                z5 = true;
                            } else {
                                for (int i10 = 0; i10 < 4; i10++) {
                                    if (this.a[this.F[i10]]) {
                                        switch (this.F[i10]) {
                                            case 0:
                                                if (i4 > 0) {
                                                    z5 = true;
                                                }
                                                break;
                                            case 1:
                                                if (i5 > 0) {
                                                    z5 = true;
                                                }
                                                break;
                                            case 2:
                                                if (i4 < 0) {
                                                    z5 = true;
                                                }
                                                break;
                                            case 3:
                                                if (i5 < 0) {
                                                    z5 = true;
                                                }
                                                break;
                                        }
                                        if (z5) {
                                            this.p = this.F[i10];
                                            this.l = this.b[this.F[i10]] * this.I;
                                            this.m = this.c[this.F[i10]] * this.I;
                                            this.q = this.p * 90;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!z5) {
                        if (this.K == 1) {
                            for (i = 0; i < 4; i++) {
                                byte b = this.F[Math.abs(c.d(4))];
                                byte b2 = this.F[i];
                                this.F[i] = this.F[b];
                                this.F[b] = b2;
                            }
                            this.l = 0;
                            this.m = 0;
                            for (i2 = 0; i2 < 4; i2++) {
                                if (!this.a[this.F[i2]] && r.c[i8 + this.c[this.F[i2]]][i9 + this.b[this.F[i2]]] >= 12 && r.c[i8 + this.c[this.F[i2]]][i9 + this.b[this.F[i2]]] < 24) {
                                    this.p = this.F[i2];
                                    this.l = this.b[this.F[i2]] * this.I;
                                    this.m = this.c[this.F[i2]] * this.I;
                                    this.q = this.p * 90;
                                    if (i2 == 4) {
                                        for (i3 = 0; i3 < 4; i3++) {
                                            if (this.a[this.F[i3]]) {
                                                this.p = this.F[i3];
                                                this.l = this.b[this.F[i3]] * this.I;
                                                this.m = this.c[this.F[i3]] * this.I;
                                                this.q = this.p * 90;
                                            }
                                        }
                                    }
                                    this.K = 0;
                                }
                            }
                            if (i2 == 4) {
                                while (i3 < 4) {
                                    if (this.a[this.F[i3]]) {
                                        this.p = this.F[i3];
                                        this.l = this.b[this.F[i3]] * this.I;
                                        this.m = this.c[this.F[i3]] * this.I;
                                        this.q = this.p * 90;
                                    }
                                }
                            }
                            this.K = 0;
                        } else if (this.a[this.p] || c.d(10) == 0) {
                            this.K = 15 + c.d(10);
                            this.l = 0;
                            this.m = 0;
                        }
                    }
                    if (this.l == 0 || this.m != 0) {
                        ab.a(i8 + this.c[this.p], i9 + this.b[this.p], this);
                    }
                }
            } else {
                ab.b(i8 - this.c[this.p], i9 - this.b[this.p], this);
                boolean[] zArr5 = this.a;
                if (r.a(i8, i9 + 1)) {
                    z = false;
                } else {
                    z = true;
                }
                zArr5[0] = z;
                boolean[] zArr6 = this.a;
                if (r.a(i8 + 1, i9)) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                zArr6[1] = z2;
                boolean[] zArr7 = this.a;
                if (r.a(i8, i9 - 1)) {
                    z3 = false;
                } else {
                    z3 = true;
                }
                zArr7[2] = z3;
                boolean[] zArr8 = this.a;
                if (r.a(i8 - 1, i9)) {
                    z4 = false;
                } else {
                    z4 = true;
                }
                zArr8[3] = z4;
                if (this.L) {
                    i4 = ((this.s.g + 12) / 24) - ((this.g + 12) / 24);
                    i5 = ((this.s.h + 12) / 24) - ((this.h + 12) / 24);
                    if (Math.abs(i4) < 10) {
                        if (i4 != 0) {
                            if (i4 > 0) {
                                this.p = 0;
                            }
                            if (i4 < 0) {
                                this.p = 2;
                            }
                            if (i5 > 0) {
                                this.p = 1;
                            }
                            if (i5 < 0) {
                                this.p = 3;
                            }
                            this.l = 0;
                            this.m = 0;
                            z5 = true;
                        } else {
                            if (i4 > 0) {
                                this.p = 0;
                            }
                            if (i4 < 0) {
                                this.p = 2;
                            }
                            if (i5 > 0) {
                                this.p = 1;
                            }
                            if (i5 < 0) {
                                this.p = 3;
                            }
                            this.l = 0;
                            this.m = 0;
                            z5 = true;
                        }
                    }
                }
                if (!z5) {
                    if (this.K == 1) {
                        while (i < 4) {
                            byte b3 = this.F[Math.abs(c.d(4))];
                            byte b4 = this.F[i];
                            this.F[i] = this.F[b3];
                            this.F[b3] = b4;
                        }
                        this.l = 0;
                        this.m = 0;
                        while (i2 < 4) {
                            if (!this.a[this.F[i2]]) {
                            }
                        }
                        if (i2 == 4) {
                            while (i3 < 4) {
                                if (this.a[this.F[i3]]) {
                                    this.p = this.F[i3];
                                    this.l = this.b[this.F[i3]] * this.I;
                                    this.m = this.c[this.F[i3]] * this.I;
                                    this.q = this.p * 90;
                                }
                            }
                        }
                        this.K = 0;
                    } else if (this.a[this.p]) {
                        this.K = 15 + c.d(10);
                        this.l = 0;
                        this.m = 0;
                    } else {
                        this.K = 15 + c.d(10);
                        this.l = 0;
                        this.m = 0;
                    }
                }
                if (this.l == 0) {
                    ab.a(i8 + this.c[this.p], i9 + this.b[this.p], this);
                } else {
                    ab.a(i8 + this.c[this.p], i9 + this.b[this.p], this);
                }
            }
        }
        f();
    }

    @Override // defpackage.an
    public final void b() {
        int i = this.g / r.a;
        int i2 = this.h / r.a;
        if (this.g % r.a != 0) {
            ab.b(i2, i + 1, this);
        } else if (this.h % r.a != 0) {
            ab.b(i2 + 1, i, this);
        } else {
            System.out.println("fix check mapObj error");
            ab.b(i2 - this.c[this.p], i - this.b[this.p], this);
        }
        ab.b(i2, i, this);
        ab.a((byte) 0, this.g + (this.j / 2), this.h + (this.k / 2), 0, 0, 0);
        ab.a((byte) 6, this.g + (this.j / 2), this.h + (this.k / 2), 0, 0, 0);
        if (this.v == 110) {
            ab.a(false);
        } else {
            if (this.v == 111) {
                ab.a(true);
                ab.a(new StringBuffer("+").append((int) this.z).append("XP").toString(), this.g + 12, this.h + 12);
                ab.b(this.z);
                ab.a((byte) 5, this.g + (this.j / 2), this.h + (this.k / 2), 0, 0, 0);
                return;
            }
            ab.a(this.g + 12, this.h + 12, (byte) c.e(6));
            ab.a(new StringBuffer("+").append((int) this.z).append("XP").toString(), this.g + 12, this.h + 12);
            ab.b(this.z);
            ab.q--;
            ab.s++;
            this.P--;
            if (this.P > 0) {
                this.g = this.N;
                this.h = this.O;
                this.x = this.y;
                System.out.println("tank re life");
                this.n = true;
                this.Q = 20;
                return;
            }
        }
        this.o = true;
    }
}
