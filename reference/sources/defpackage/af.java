package defpackage;

import javax.microedition.lcdui.Graphics;

/* JADX INFO: loaded from: XForce.jar:af.class */
public final class af extends q {
    private int j;
    private int k;
    public static int i;
    private int l;
    private int m;
    private int n;
    private int[] o;
    private int[] p;
    private int q;
    private int s;
    private int t;
    private int u;
    private int w;
    private a x;
    private int[] r = {22, 36, 52, 59, 75, 91, 109};
    private boolean v = true;

    public af() {
        if (aj.b >= 240) {
            this.x = c.c;
        } else {
            this.x = c.b;
        }
        this.n = 18;
        if (aj.c < 200) {
            this.n = 16;
        }
        if (aj.b >= 240) {
            this.n = 24;
        }
    }

    public final void a(int i2, int i3) {
        this.j = i2;
        this.k = 0;
        int i4 = 1;
        this.l = 8;
        this.p = new int[al.f[this.j].length];
        this.p[0] = this.l;
        while (this.p[0] > -120) {
            i4 <<= 1;
            int[] iArr = this.p;
            iArr[0] = iArr[0] - i4;
        }
        for (int i5 = 1; i5 < al.f[this.j].length; i5++) {
            this.p[i5] = this.p[i5 - 1] - i4;
            i4 <<= 1;
        }
        this.m = ((aj.c - (al.f[this.j].length * this.n)) * 3) / 4;
        this.o = new int[al.f[this.j].length];
        for (int i6 = 0; i6 < al.f[this.j].length; i6++) {
            this.o[i6] = this.m;
        }
        if (this.j == al.d) {
            this.g = al.w;
            this.h = new StringBuffer(String.valueOf(al.D)).append(ab.A).append(" ").append(al.B).append(ab.B).append("$").toString();
        }
    }

    @Override // defpackage.q
    public final void a(Graphics graphics) {
        if (this.v) {
            graphics.drawImage(c.F, 0, 0, 0);
            if (this.w < 5) {
                c.d.a(al.I, aj.b >> 1, aj.c - 20, 2, graphics);
            }
            this.w++;
            if (this.w >= 10) {
                this.w = 0;
                return;
            }
            return;
        }
        graphics.drawImage(c.F, 0, 0, 0);
        graphics.drawImage(c.H, aj.b >> 1, aj.c >> 1, 0);
        if (this.j == 0) {
            this.t++;
            if ((this.s < this.r.length - 2 && this.t > 1) || this.t > 5) {
                this.t = 0;
                if (this.s < this.r.length - 1) {
                    this.s++;
                } else {
                    this.s--;
                }
            }
            graphics.setClip(8, 20, this.r[this.s], 22);
            graphics.drawImage(c.G, 8, 20, 0);
            graphics.setClip(0, 0, aj.b, aj.c);
        } else {
            c(graphics);
        }
        for (int i2 = 0; i2 < al.f[this.j].length; i2++) {
            if (this.p[i2] < this.l) {
                int[] iArr = this.p;
                int i3 = i2;
                iArr[i3] = iArr[i3] + ((this.l - this.p[i2]) >> 1);
            }
        }
        graphics.setColor(12615936);
        if (this.k > -1) {
            this.u += (((this.m + 1) + (this.k * this.n)) - this.u) >> 1;
            graphics.fillRect(this.l - 4, this.u, 116, 14);
        }
        int i4 = this.m;
        for (int i5 = 0; i5 < al.f[this.j].length; i5++) {
            this.x.a(al.f[this.j][i5], this.p[i5], i4, 0, graphics);
            i4 += this.n;
        }
        if (this.q < 5) {
            this.q++;
        }
    }

    @Override // defpackage.q
    public final void a() {
        if (this.v) {
            if (q.d == 3) {
                a(0);
                q.d = 0;
                return;
            }
            return;
        }
        if (q.a[0] && this.k > 0) {
            this.k--;
            if (al.f[this.j][this.k] == "") {
                this.k--;
            }
        }
        if (q.a[1] && this.k < al.f[this.j].length - 1) {
            this.k++;
            if (al.f[this.j][this.k] == "") {
                this.k++;
            }
        }
        if (q.a[4] || q.a[6]) {
            d();
        }
        q.c();
        if (q.d == 1) {
            this.k = -1;
            if (q.c > this.m) {
                this.k = (q.c - this.m) / this.n;
                if (this.k >= al.f[this.j].length || al.f[this.j][this.k] == "") {
                    this.k = -1;
                }
            }
            q.d = 2;
        }
        if (q.d == 3) {
            if (this.k != -1) {
                d();
            }
            q.d = 0;
        }
        if (i <= 0 || w.a) {
            return;
        }
        if (i == 1) {
            w.a(new StringBuffer(String.valueOf(ab.z)).append(". ").append(al.aa).toString(), 3);
            i = 2;
        } else if (i == 2) {
            w.a(al.ae[0], 0);
            i = 3;
        }
        if (i == 4) {
            w.a(al.af[0], 1);
            i = 0;
        }
        if (i == 5) {
            w.a(al.ag[0], 2);
            i = 0;
        }
    }

    @Override // defpackage.q
    public final void a(int i2) {
        if (this.v) {
            if (aj.b >= 240) {
                c.F = c.a("/bgab.png");
            } else {
                c.F = c.a("/bga.png");
            }
            this.v = false;
            a(0, 0);
            q.c();
        }
    }

    private void d() {
        if (this.j == 0) {
            switch (this.k) {
                case 0:
                    if (ab.A != 0) {
                        a(al.d, 0);
                    } else {
                        new am().b();
                    }
                    break;
                case 1:
                    aj.n.a(al.m, false);
                    break;
                case 2:
                    aj.h.b();
                    break;
                case 3:
                    aj.i.b();
                    break;
                case 4:
                    aj.i.d();
                    break;
                case 5:
                    aj.n.a(al.k, false);
                    break;
            }
        }
        if (this.j == al.d) {
            switch (this.k) {
                case 0:
                    aj.j.b();
                    break;
                case 1:
                    aj.l.b();
                    break;
                case 2:
                    aj.m.b();
                    break;
                case 3:
                    aj.k.b();
                    break;
                case 5:
                    a(0, 0);
                    break;
            }
        }
        if (this.j == al.e) {
            switch (this.k) {
                case 0:
                    aj.e.b();
                    break;
                case 2:
                    aj.e.b();
                    break;
                case 3:
                    aj.h.b();
                    break;
                case 4:
                    aj.n.a(al.l, false);
                    break;
            }
        }
    }

    @Override // defpackage.q
    public final void b() {
        super.b();
        if (c.H == null) {
            ag.d();
        }
        if (this.v) {
            if (aj.b >= 240) {
                c.F = c.a("/bgb.png");
            } else {
                c.F = c.a("/bg.png");
            }
        }
        this.s = 0;
        int i2 = 1;
        this.l = 8;
        this.p = new int[al.f[this.j].length];
        this.p[0] = this.l;
        while (this.p[0] > -120) {
            i2 <<= 1;
            int[] iArr = this.p;
            iArr[0] = iArr[0] - i2;
        }
        for (int i3 = 1; i3 < al.f[this.j].length; i3++) {
            this.p[i3] = this.p[i3 - 1] - i2;
            i2 <<= 1;
        }
        if (this.j == al.d) {
            this.g = al.w;
            this.h = new StringBuffer(String.valueOf(al.D)).append(ab.A).append(" ").append(al.B).append(ab.B).append("$").toString();
        }
    }
}
