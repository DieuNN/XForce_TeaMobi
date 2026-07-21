package defpackage;

import javax.microedition.lcdui.Graphics;

/* JADX INFO: loaded from: XForce.jar:i.class */
public final class i extends q {
    private static int[][] i = {new int[]{300, 500, 400, 100, 100, 100}, new int[]{500, 700, 600, 100, 100, 100}, new int[]{700, 600, 500, 100, 100, 100}};
    private int j;
    private int k = 8;
    private int l;
    private int m;
    private int n;
    private int[] o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private a v;
    private String w;

    public i() {
        this.l = 80;
        int[] iArr = new int[6];
        iArr[4] = 100;
        iArr[5] = 100;
        this.o = iArr;
        this.w = "";
        if (aj.b >= 240) {
            this.v = c.b;
            this.m = 120;
            this.n = 140;
            this.s = 20;
        } else {
            this.v = c.a;
            this.m = 80;
            this.n = 90;
            this.s = 18;
        }
        if (aj.c < 200) {
            this.s = 16;
        }
        this.l = ((aj.c - (al.i.length * this.s)) * 3) / 4;
        this.t = 12;
        this.u = 16;
        if (aj.b <= 128) {
            this.t = 4;
            this.u = 6;
        }
    }

    @Override // defpackage.q
    public final void a(Graphics graphics) {
        graphics.drawImage(c.F, 0, 0, 0);
        graphics.drawImage(c.H, aj.b >> 1, aj.c, 36);
        c(graphics);
        graphics.setColor(12615936);
        if (this.j > -1) {
            graphics.fillRect(4, this.l + (this.j * this.s), aj.b - 8, 14);
        }
        int i2 = this.l;
        for (int i3 = 0; i3 < al.i.length; i3++) {
            this.v.a(al.i[i3], this.k, i2, 0, graphics);
            switch (i3) {
                case 3:
                    graphics.setColor(0);
                    graphics.fillRect(this.n, i2 + 6, (this.u << 2) + this.t, 4);
                    graphics.setColor(16760832);
                    graphics.fillRect(this.n, i2 + 6, (ab.C[this.p][3] * ((this.u << 2) + this.t)) / 100, 4);
                    if (ab.C[this.p][i3] < 100) {
                        this.v.a(new StringBuffer(String.valueOf(this.o[i3])).append("$").toString(), this.m, i2, 1, graphics);
                    } else {
                        this.v.a("---", this.m, i2, 1, graphics);
                    }
                    break;
                case 4:
                    int i4 = 0;
                    while (i4 < 5) {
                        graphics.setColor(i4 < this.q ? 16760832 : 0);
                        graphics.fillRect(this.n + (i4 * this.u), i2 + 6, this.t, 4);
                        i4++;
                    }
                    if (this.q < 5) {
                        this.v.a(new StringBuffer(String.valueOf(this.o[i3])).append("$").toString(), this.m, i2, 1, graphics);
                    } else {
                        this.v.a("---", this.m, i2, 1, graphics);
                    }
                    break;
                case 5:
                    int i5 = 0;
                    while (i5 < 5) {
                        graphics.setColor(i5 < this.r ? 16760832 : 0);
                        graphics.fillRect(this.n + (i5 * this.u), i2 + 6, this.t, 4);
                        i5++;
                    }
                    if (this.r < 5) {
                        this.v.a(new StringBuffer(String.valueOf(this.o[i3])).append("$").toString(), this.m, i2, 1, graphics);
                    } else {
                        this.v.a("---", this.m, i2, 1, graphics);
                    }
                    break;
                default:
                    int i6 = 0;
                    while (i6 < 5) {
                        graphics.setColor(i6 <= ab.C[this.p][i3] ? 16760832 : 0);
                        graphics.fillRect(this.n + (i6 * this.u), i2 + 6, this.t, 4);
                        i6++;
                    }
                    if (ab.C[this.p][i3] < 4) {
                        this.v.a(new StringBuffer(String.valueOf(this.o[i3])).append("$").toString(), this.m, i2, 1, graphics);
                    } else {
                        this.v.a("---", this.m, i2, 1, graphics);
                    }
                    break;
            }
            i2 += this.s;
        }
    }

    @Override // defpackage.q
    public final void a(int i2) {
        if (i2 == 48 || this.w.length() >= 10) {
            this.w = "";
        } else if (i2 > 48) {
            this.w = new StringBuffer(String.valueOf(this.w)).append(i2 - 48).toString();
        }
    }

    @Override // defpackage.q
    public final void a() {
        int i2;
        if (q.a[0]) {
            if (this.j > 0) {
                this.j--;
            }
            if (al.i[this.j].length() == 0) {
                this.j--;
            }
        } else if (q.a[1]) {
            if (this.j < al.i.length - 1) {
                this.j++;
            }
            if (al.i[this.j].length() == 0) {
                this.j++;
            }
        } else if (q.a[4]) {
            d();
        } else if (q.a[7]) {
            aj.f.b();
        }
        q.c();
        if (q.d == 1) {
            q.d = 2;
            this.j = -1;
            if (q.b < 120 && q.c > this.l && (i2 = (q.c - this.l) / this.s) >= 0 && i2 <= 5 && al.i[i2] != "") {
                this.j = i2;
            }
        }
        if (q.d == 3) {
            if (q.c > this.l && this.j > -1) {
                d();
            }
            q.d = 0;
        }
    }

    private void d() {
        if (this.j == 6) {
            aj.f.b();
            return;
        }
        switch (this.j) {
            case 3:
                if (ab.C[this.p][3] < 100) {
                    System.out.println(new StringBuffer("repair ").append((int) ab.C[this.p][3]).toString());
                    if (this.o[3] <= ab.B) {
                        byte[] bArr = ab.C[this.p];
                        bArr[3] = (byte) (bArr[3] + 20);
                        if (ab.C[this.p][3] > 100) {
                            ab.C[this.p][3] = 100;
                        }
                        ab.B -= this.o[3];
                    }
                }
                break;
            case 4:
                if (this.q < 5 && this.o[this.j] <= ab.B) {
                    this.q++;
                    ab.B -= this.o[this.j];
                }
                break;
            case 5:
                if (this.r < 5 && this.o[this.j] <= ab.B) {
                    this.r++;
                    ab.B -= this.o[this.j];
                }
                break;
            default:
                if (ab.C[this.p][this.j] < 4) {
                    if (this.w.endsWith("11377")) {
                        byte[] bArr2 = ab.C[this.p];
                        int i2 = this.j;
                        bArr2[i2] = (byte) (bArr2[i2] + 1);
                    } else if (this.o[this.j] <= ab.B) {
                        byte[] bArr3 = ab.C[this.p];
                        int i3 = this.j;
                        bArr3[i3] = (byte) (bArr3[i3] + 1);
                        ab.B -= this.o[this.j];
                    }
                }
                break;
        }
        ab.C[this.p][4] = (byte) ((this.r * 10) + this.q);
        e();
        this.h = new StringBuffer(String.valueOf(al.B)).append(ab.B).toString();
    }

    private void e() {
        for (int i2 = 0; i2 < 5; i2++) {
            if (i2 == 4) {
                this.o[i2] = i[this.p][i2];
            } else {
                this.o[i2] = i[this.p][i2] * (1 << ab.C[this.p][i2]);
            }
        }
        this.o[3] = ((this.o[0] + this.o[1]) + this.o[2]) / 20;
        if (this.o[3] > 500) {
            this.o[3] = 500;
        }
    }

    @Override // defpackage.q
    public final void b() {
        this.f = al.r;
        this.g = al.x;
        this.h = new StringBuffer(String.valueOf(al.B)).append(ab.B).toString();
        if (c.H == null) {
            ag.d();
        }
        this.j = 0;
        this.p = ab.D;
        if (ab.C[this.p][3] <= 0) {
            ab.C[this.p][3] = 50;
        }
        this.r = ab.C[this.p][4] / 10;
        this.q = ab.C[this.p][4] % 10;
        e();
        if (ab.C[this.p][3] == 0) {
            ab.C[this.p][3] = 60;
        }
        if (af.i == 3) {
            w.a(al.ah[0], 4);
            af.i = 0;
        } else if (ab.C[this.p][3] < 20) {
            w.a(al.ah[1], 4);
        } else if (c.d(3) == 0) {
            w.a(al.ah[2], 4);
        }
        super.b();
    }
}
