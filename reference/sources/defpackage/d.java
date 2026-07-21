package defpackage;

import javax.microedition.lcdui.Graphics;

/* JADX INFO: loaded from: XForce.jar:d.class */
public final class d extends an {
    private int F;
    private int G;
    private int H;
    private int I;
    private int J;
    private int K;
    private int L;
    private int M;
    public int a;
    public int b;
    public int c;
    private int N;
    private byte O;
    private boolean P;
    private boolean Q;
    private int R;
    private int S;
    private int T;
    private int U;
    private int V;

    public d(byte b) {
        super(0, 0, b);
        byte[] bArr = {1, 0, -1, 0};
        byte[] bArr2 = {0, 1, 0, -1};
        this.L = 0;
        this.T = 128;
        this.U = 512;
        this.w = (byte) 1;
        b(24, 24);
        this.G = 0;
        if (b == -3) {
            this.i = 20;
            this.t = (byte) 2;
        }
        if (this.v == -2) {
            c.ab = new ak(c.a("/tg0.png"), 32, 32);
            c.aa = new ak(c.a("/mt0.png"), 24, 24);
            this.r = ((3072 + (ab.C[1][0] << 9)) - (ab.C[1][2] << 7)) - (ab.C[1][1] << 7);
            this.Q = false;
            switch (ab.C[1][1]) {
                case 0:
                    this.O = (byte) 2;
                    this.I = 10;
                    break;
                case 1:
                    this.O = (byte) 2;
                    this.I = 7;
                    break;
                case 2:
                    this.O = (byte) 3;
                    this.I = 10;
                    break;
                case 3:
                    this.O = (byte) 3;
                    this.I = 7;
                    this.Q = true;
                    break;
                case 4:
                    this.O = (byte) 4;
                    this.I = 10;
                    this.Q = true;
                    break;
            }
            this.y = (short) (80 + (ab.C[1][2] * 10));
            this.x = (short) ((ab.C[1][3] * this.y) / 100);
            ab.F = ab.C[1][4] % 10;
            ab.G = ab.C[1][4] / 10;
        }
        if (this.v == -1) {
            c.ab = new ak(c.a("/sgun.png"), 15, 15);
            c.aa = new ak(c.a("/humer.png"), 24, 24);
            this.r = ((3072 + (ab.C[0][0] << 9)) - (ab.C[0][2] << 7)) - (ab.C[0][1] << 7);
            switch (ab.C[0][1]) {
                case 0:
                    this.O = (byte) 0;
                    this.I = 10;
                    break;
                case 1:
                    this.O = (byte) 0;
                    this.I = 7;
                    break;
                case 2:
                    this.O = (byte) 1;
                    this.I = 7;
                    break;
                case 3:
                    this.O = (byte) 1;
                    this.I = 5;
                    break;
                case 4:
                    this.O = (byte) 2;
                    this.I = 5;
                    break;
            }
            this.y = (short) (50 + (ab.C[0][2] * 10));
            this.x = (short) ((ab.C[0][3] * this.y) / 100);
            ab.F = ab.C[0][4] % 10;
            ab.G = ab.C[0][4] / 10;
        }
        if (this.v == -3) {
            c.aa = new ak(c.a("/heli.png"), 24, 24);
            c.r = new ak(c.a("/sheli.png"), 24, 24);
            c.t = new ak(c.a("/probeller2.png"), 24, 24);
            this.r = ((3072 + (ab.C[2][0] << 9)) - (ab.C[2][2] << 7)) - (ab.C[2][1] << 7);
            switch (ab.C[2][1]) {
                case 0:
                    this.O = (byte) 0;
                    this.I = 10;
                    break;
                case 1:
                    this.O = (byte) 0;
                    this.I = 7;
                    break;
                case 2:
                    this.O = (byte) 1;
                    this.I = 7;
                    break;
                case 3:
                    this.O = (byte) 1;
                    this.I = 5;
                    break;
                case 4:
                    this.O = (byte) 2;
                    this.I = 5;
                    break;
            }
            this.y = (short) (20 + (ab.C[2][2] * 10));
            this.x = (short) ((ab.C[2][3] * this.y) / 100);
            ab.F = ab.C[2][4] % 10;
            ab.G = ab.C[2][4] / 10;
        }
        if (this.x <= 0) {
            this.x = (short) (this.y / 2);
        }
        this.V = 8;
    }

    @Override // defpackage.an
    public final void a(Graphics graphics) {
        if (this.P) {
            c.aa.a(8, this.g, this.h, 0, graphics);
            return;
        }
        if (this.v == -3) {
            c.r.a(this.S, this.g + this.i, this.h + this.i, 0, graphics);
            c.aa.a(this.S, this.g, this.h, 0, graphics);
            c.t.a(this.R, this.g, this.h, 0, graphics);
            this.R++;
            if (this.R >= 4) {
                this.R = 0;
            }
            if (this.F == 6 && this.S == 0) {
                this.S = 7;
                return;
            }
            if (this.F == 0 && this.S == 6) {
                this.S = 7;
                return;
            }
            if (this.F == 0 && this.S == 7) {
                this.S = 0;
                return;
            }
            if (this.S < this.F) {
                this.S++;
            }
            if (this.S > this.F) {
                this.S--;
                return;
            }
            return;
        }
        c.aa.a((this.F << 1) + this.M, this.g, this.h, 0, graphics);
        if (this.v == -2) {
            if (this.K > 0) {
                switch (this.H) {
                    case 0:
                        c.ab.a(this.H, (this.g - this.K) - 4, this.h - 4, 0, graphics);
                        break;
                    case 2:
                        c.ab.a(this.H, this.g - 4, (this.h - this.K) - 4, 0, graphics);
                        break;
                    case 4:
                        c.ab.a(this.H, (this.g + this.K) - 4, this.h - 4, 0, graphics);
                        break;
                    case 6:
                        c.ab.a(this.H, this.g - 4, (this.h + this.K) - 4, 0, graphics);
                        break;
                }
                this.K--;
            } else {
                c.ab.a(this.H, this.g - 4, this.h - 4, 0, graphics);
            }
        } else if (this.K > 0) {
            switch (this.H) {
                case 0:
                    c.ab.a(this.H, (this.g - this.K) + 4, this.h + 4, 0, graphics);
                    break;
                case 2:
                    c.ab.a(this.H, this.g + 4, (this.h - this.K) + 4, 0, graphics);
                    break;
                case 4:
                    c.ab.a(this.H, this.g + this.K + 4, this.h + 4, 0, graphics);
                    break;
                case 6:
                    c.ab.a(this.H, this.g + 4, this.h + this.K + 4, 0, graphics);
                    break;
            }
            this.K--;
        } else {
            c.ab.a(this.H, this.g + 4, this.h + 4, 0, graphics);
        }
        if (this.G == 6 && this.H == 0) {
            this.H = 7;
            return;
        }
        if (this.G == 0 && this.H == 6) {
            this.H = 7;
            return;
        }
        if (this.G == 0 && this.H == 7) {
            this.H = 0;
            return;
        }
        if (this.H < this.G) {
            this.H++;
        }
        if (this.H > this.G) {
            this.H--;
        }
    }

    @Override // defpackage.ai
    public final void a() {
        if (this.v == -3) {
            if (this.P) {
                return;
            }
            if (this.L > 0) {
                if (this.L == 1) {
                    this.r -= 1024;
                    ab.a("SPEED--", this.g, this.h);
                }
                this.L--;
            }
            if (this.l > this.T) {
                this.l -= this.T;
            } else if (this.l < (-this.T)) {
                this.l += this.T;
            } else {
                this.l = 0;
            }
            if (this.m > this.T) {
                this.m -= this.T;
            } else if (this.m < (-this.T)) {
                this.m += this.T;
            } else {
                this.m = 0;
            }
            if (ab.m == this) {
                if (q.a[0]) {
                    this.m = this.m > (-this.r) ? this.m - this.U : -this.r;
                } else if (q.a[1]) {
                    this.m = this.m < this.r ? this.m + this.U : this.r;
                } else if (q.a[2]) {
                    this.l = this.l > (-this.r) ? this.l - this.U : -this.r;
                } else if (q.a[3]) {
                    this.l = this.l < this.r ? this.l + this.U : this.r;
                }
                if (f.a == 12) {
                    this.F = 6;
                } else if (!q.a[4]) {
                    if (q.a[0]) {
                        this.F = 6;
                        this.G = 6;
                    } else if (q.a[1]) {
                        this.F = 2;
                        this.G = 2;
                    } else if (q.a[2]) {
                        this.F = 4;
                        this.G = 4;
                    } else if (q.a[3]) {
                        this.F = 0;
                        this.G = 0;
                    }
                }
                f();
                if (this.g < 0) {
                    this.g = 0;
                }
                if (this.g + this.j > r.g) {
                    this.g = r.g - this.j;
                }
                if (this.h < 0) {
                    this.h = 0;
                }
                if (this.h + this.k > r.h) {
                    this.h = r.h - this.k;
                }
                if (this.a > 0) {
                    this.a--;
                }
                if (this.a == 0) {
                    boolean zG = ab.E ? f.a == 12 ? true : g() : false;
                    if (q.a[4] || zG) {
                        switch (this.F) {
                            case 0:
                                ab.a(this.O, this.g + (this.j >> 1) + 15, this.h + 6, 0, null);
                                ab.a(this.O, this.g + (this.j >> 1) + 15, this.h + 18, 0, null);
                                break;
                            case 2:
                                ab.a(this.O, this.g + 6, this.h + (this.k >> 1) + 15, 90, null);
                                ab.a(this.O, this.g + 18, this.h + (this.k >> 1) + 15, 90, null);
                                break;
                            case 4:
                                ab.a(this.O, (this.g + (this.j >> 1)) - 15, this.h + 6, 180, null);
                                ab.a(this.O, (this.g + (this.j >> 1)) - 15, this.h + 18, 180, null);
                                break;
                            case 6:
                                ab.a(this.O, this.g + 6, (this.h + (this.k >> 1)) - 15, 270, null);
                                ab.a(this.O, this.g + 18, (this.h + (this.k >> 1)) - 15, 270, null);
                                break;
                        }
                        this.a = this.I;
                    }
                }
                if (this.J <= 0) {
                    this.J = 20;
                    for (int i = 0; i < ab.p; i++) {
                        if (ab.o[i].t == 2 && ab.o[i].w == -1 && ab.o[i].g > this.g - 100 && ab.o[i].g < this.g + 100 && ab.o[i].h > this.h - 100 && ab.o[i].h < this.h + 100) {
                            ab.a((byte) 10, this.g, this.h, c.a(ab.o[i].g - this.g, ab.o[i].h - this.h), ab.o[i]);
                            this.J = 70;
                            System.out.println("shoot socket");
                        }
                    }
                } else {
                    this.J--;
                }
                if (this.b > 0) {
                    this.b--;
                }
                if (this.c > 0) {
                    this.c--;
                    return;
                }
                return;
            }
            return;
        }
        if (this.P) {
            return;
        }
        if (this.L > 0) {
            if (this.L == 1) {
                this.r -= 1024;
                ab.a("SPEED--", this.g, this.h);
            }
            this.L--;
        }
        if (ab.m != this) {
            return;
        }
        int i2 = this.g % r.a;
        int i3 = this.h % r.a;
        int i4 = this.h / r.a;
        if (this.h < 0) {
            i4--;
        }
        int i5 = this.g / r.a;
        if (this.g < 0) {
            i5--;
        }
        ab.b(i4, i5, this);
        if (i2 != 0) {
            ab.b(i4, i5 + 1, this);
        }
        if (i3 != 0) {
            ab.b(i4 + 1, i5, this);
        }
        if (i2 != 0 && i3 != 0) {
            ab.b(i4 + 1, i5 + 1, this);
        }
        this.m = 0;
        this.l = 0;
        this.N = this.r;
        if (q.a[0]) {
            if (r.a(i4, i5)) {
                if (!r.a(i4, i5 + 1)) {
                    if (r.a - i2 <= 5) {
                        this.g += r.a - i2;
                    } else if (!r.a(i4 + 1, i5 + 1)) {
                        this.F = 0;
                        this.l = this.N;
                    }
                }
            } else if (i2 == 0 || !r.a(i4, i5 + 1)) {
                this.F = 3;
                this.m = -this.N;
            } else if (i2 <= 5) {
                this.g -= i2;
            } else if (!r.a(i4 + 1, i5)) {
                this.F = 2;
                this.l = -this.N;
            }
        } else if (q.a[1]) {
            if (r.a(i4 + 1, i5)) {
                if (!r.a(i4 + 1, i5 + 1)) {
                    if (r.a - i2 <= 5) {
                        this.g += r.a - i2;
                    } else if (!r.a(i4, i5 + 1)) {
                        this.F = 0;
                        this.l = this.N;
                    }
                }
            } else if (i2 == 0 || !r.a(i4 + 1, i5 + 1)) {
                this.F = 1;
                this.m = this.N;
            } else if (i2 <= 5) {
                this.g -= i2;
            } else if (!r.a(i4, i5)) {
                this.F = 2;
                this.l = -this.N;
            }
        } else if (q.a[2]) {
            if (r.a(i4, i5)) {
                if (!r.a(i4 + 1, i5)) {
                    if (r.a - i3 <= 5) {
                        this.h += r.a - i3;
                    } else if (!r.a(i4 + 1, i5 + 1)) {
                        this.F = 1;
                        this.m = this.N;
                    }
                }
            } else if (i3 == 0 || !r.a(i4 + 1, i5)) {
                this.F = 2;
                this.l = -this.N;
            } else if (i3 <= 5) {
                this.h -= i3;
            } else if (!r.a(i4, i5 + 1)) {
                this.F = 3;
                this.m = -this.N;
            }
        } else if (!q.a[3]) {
            this.N = 0;
        } else if (r.a(i4, i5 + 1)) {
            if (!r.a(i4 + 1, i5 + 1) && r.a - i3 <= 5) {
                this.h += r.a - i3;
            }
        } else if (i3 == 0 || !r.a(i4 + 1, i5 + 1)) {
            this.F = 0;
            this.l = this.N;
        } else if (i3 <= 5) {
            this.h -= i3;
        } else if (!r.a(i4, i5)) {
            this.F = 3;
            this.m = -this.N;
        }
        f();
        if (this.v == -2) {
            if (i4 != this.h / r.a) {
                ab.a((byte) 8, this.g, this.h, 0, 0, 0);
            } else if (i5 != this.g / r.a) {
                ab.a((byte) 9, this.g, this.h, 0, 0, 0);
            }
        }
        int i6 = this.h / r.a;
        if (this.h < 0) {
            i6--;
        }
        int i7 = this.g / r.a;
        if (this.g < 0) {
            i7--;
        }
        int i8 = this.g % r.a;
        int i9 = this.h % r.a;
        ab.a(i6, i7, this);
        if (i8 != 0) {
            ab.a(i6, i7 + 1, this);
        }
        if (i9 != 0) {
            ab.a(i6 + 1, i7, this);
        }
        if (i8 != 0 && i9 != 0) {
            ab.a(i6 + 1, i7 + 1, this);
        }
        if (this.N != 0) {
            this.M = 1 - this.M;
        }
        if (!q.a[4]) {
            if (q.a[0]) {
                this.G = 6;
            } else if (q.a[1]) {
                this.G = 2;
            } else if (q.a[2]) {
                this.G = 4;
            } else if (q.a[3]) {
                this.G = 0;
            }
        }
        if (this.a > 0) {
            this.a--;
        }
        if (this.a == 0) {
            boolean z = false;
            if (ab.E && g()) {
                z = true;
            }
            if (q.a[4] || z) {
                switch (this.G) {
                    case 0:
                        ab.a(this.O, this.g + (this.j >> 1) + 15, this.h + (this.k >> 1), 0, null);
                        ab.a((byte) 2, this.g + (this.j >> 1) + 15, this.h + (this.k >> 1), 1024, 0, 0);
                        break;
                    case 2:
                        ab.a(this.O, this.g + (this.j >> 1), this.h + (this.k >> 1) + 15, 90, null);
                        ab.a((byte) 2, this.g + (this.j >> 1), this.h + (this.k >> 1) + 15, 0, 1024, 0);
                        break;
                    case 4:
                        ab.a(this.O, (this.g + (this.j >> 1)) - 15, this.h + (this.k >> 1), 180, null);
                        ab.a((byte) 2, (this.g + (this.j >> 1)) - 15, this.h + (this.k >> 1), -1024, 0, 0);
                        break;
                    case 6:
                        ab.a(this.O, this.g + (this.j >> 1), (this.h + (this.k >> 1)) - 15, 270, null);
                        ab.a((byte) 2, this.g + (this.j >> 1), (this.h + (this.k >> 1)) - 15, 0, -1024, 0);
                        break;
                }
                this.K = 2;
                this.a = this.I;
            }
        }
        if (this.b > 0) {
            this.b--;
        }
        if (this.c > 0) {
            this.c--;
        }
        if (this.Q) {
            if (this.J > 0) {
                this.J--;
                return;
            }
            this.J = 20;
            for (int i10 = 0; i10 < ab.p; i10++) {
                if (ab.o[i10].t == 2 && ab.o[i10].w == -1 && ab.o[i10].g > this.g - 100 && ab.o[i10].g < this.g + 100 && ab.o[i10].h > this.h - 100 && ab.o[i10].h < this.h + 100) {
                    ab.a((byte) 10, this.g, this.h, c.a(ab.o[i10].g - this.g, ab.o[i10].h - this.h), ab.o[i10]);
                    this.J = 70;
                    System.out.println("shoot socket");
                    return;
                }
            }
        }
    }

    @Override // defpackage.an
    public final boolean a(v vVar) {
        if (!super.a(vVar)) {
            return false;
        }
        CMidlet.a(100);
        return true;
    }

    @Override // defpackage.an
    public final void a(int i) {
        super.a(i);
        CMidlet.a(100);
    }

    @Override // defpackage.an
    public final void b() {
        if (this.P) {
            return;
        }
        this.P = true;
        ab.a(this.g + (this.j / 2), this.h + (this.k / 2));
        ab.a((byte) 5, this.g + (this.j / 2), this.h + (this.k / 2), 0, 0, 0);
        ab.a(false);
    }

    public final boolean a(z zVar) {
        switch (zVar.a) {
            case 0:
                ab.b(100);
                ab.a("+100XP", this.g, this.h);
                return true;
            case 1:
                ab.c(100);
                ab.a("+100$", this.g, this.h);
                return true;
            case 2:
                this.x = (short) (this.x + ((this.y * 5) / 100));
                if (this.x > this.y) {
                    this.x = this.y;
                }
                ab.a("+5%HP", this.g, this.h);
                return true;
            case 3:
                if (this.L == 0) {
                    this.r += 1024;
                }
                this.L = 1000;
                ab.a("SPEED++", this.g, this.h);
                return true;
            case 4:
                if (ab.F >= 5) {
                    return false;
                }
                ab.a("DYNAMIC", this.g, this.h);
                ab.F++;
                return true;
            case 5:
                if (ab.G >= 5) {
                    return false;
                }
                ab.a("MINE", this.g, this.h);
                ab.G++;
                return true;
            case 6:
                f.c = true;
                if (f.a != 10) {
                    return true;
                }
                ab.f(132, 132);
                return true;
            case 7:
                ab.a(true);
                return true;
            default:
                return false;
        }
    }

    @Override // defpackage.an
    public final void c() {
        switch (this.G) {
            case 0:
                ab.c(this.g + (this.j / 2) + 32, this.h + (this.j / 2));
                break;
            case 2:
                ab.c(this.g + (this.j / 2), this.h + (this.j / 2) + 32);
                break;
            case 4:
                ab.c((this.g + (this.j / 2)) - 32, this.h + (this.j / 2));
                break;
            case 6:
                ab.c(this.g + (this.j / 2), (this.h + (this.j / 2)) - 32);
                break;
        }
    }

    private boolean g() {
        int i = (this.h + 12) / r.a;
        int i2 = (this.g + 12) / r.a;
        switch (this.G) {
            case 0:
                for (int i3 = 1; i3 < this.V; i3++) {
                    if (ab.d(i, i2 + i3)) {
                        return true;
                    }
                }
                return false;
            case 1:
            case 3:
            case 5:
            default:
                return false;
            case 2:
                for (int i4 = 1; i4 < this.V; i4++) {
                    if (ab.d(i + i4, i2)) {
                        return true;
                    }
                }
                return false;
            case 4:
                for (int i5 = 1; i5 < this.V; i5++) {
                    if (ab.d(i, i2 - i5)) {
                        return true;
                    }
                }
                return false;
            case 6:
                for (int i6 = 1; i6 < this.V; i6++) {
                    if (ab.d(i - i6, i2)) {
                        return true;
                    }
                }
                return false;
        }
    }

    public final void d() {
        if (this.c != 0 || ab.F <= 0) {
            return;
        }
        ab.a((byte) 16, (((this.g + 12) / r.a) * r.a) + 12, (((this.h + 12) / r.a) * r.a) + 12, 0, null);
        this.c = 50;
        ab.F--;
    }

    public final void e() {
        if (this.b != 0 || ab.G <= 0) {
            return;
        }
        ab.b(this.g + (this.j / 2), this.h + (this.k / 2));
        this.b = 50;
        ab.G--;
    }
}
