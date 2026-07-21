package defpackage;

import javax.microedition.lcdui.Graphics;

/* JADX INFO: loaded from: XForce.jar:j.class */
public final class j extends q {
    private boolean i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n = 0;
    private q o;
    private int p;
    private int q;
    private a r;

    public j() {
        this.r = aj.b >= 240 ? c.b : c.a;
        if (aj.b >= 240) {
            this.r = c.b;
            this.j = 120;
            this.p = 16;
        } else {
            this.r = c.a;
            this.j = 105;
            this.p = 14;
        }
        this.k = 8;
        this.l = (aj.c - this.j) >> 1;
        this.m = (aj.c - (al.h[0].length * this.p)) >> 1;
    }

    @Override // defpackage.q
    public final void a(Graphics graphics) {
        String strB;
        String strB2;
        String strB3;
        graphics.setColor(0);
        if (this.i) {
            q.b(graphics);
            this.i = false;
        }
        graphics.fillRect(0, this.l, aj.b, this.j);
        graphics.setColor(12615936);
        if (this.n > -1) {
            graphics.fillRect(this.k - 4, this.m + 1 + (this.n * this.p), 120, 12);
        }
        int i = this.m;
        if (this.q == 0) {
            this.r.a(new StringBuffer(String.valueOf(al.h[this.q][0])).append("<").append(p.a).append(">").toString(), this.k, i, 0, graphics);
            int i2 = i + this.p;
            this.r.a(new StringBuffer(String.valueOf(al.h[this.q][1])).append("<").append(p.b).append(">").toString(), this.k, i2, 0, graphics);
            int i3 = i2 + this.p;
            this.r.a(new StringBuffer(String.valueOf(al.h[this.q][2])).append(CMidlet.b ? al.n : al.o).toString(), this.k, i3, 0, graphics);
            int i4 = i3 + this.p;
            this.r.a(al.h[this.q][3], this.k, i4, 0, graphics);
            int i5 = i4 + this.p;
            this.r.a(new StringBuffer(String.valueOf(al.h[this.q][4])).append(al.b[al.a]).toString(), this.k, i5, 0, graphics);
            this.r.a(al.h[this.q][5], this.k, i5 + this.p, 0, graphics);
            return;
        }
        if (this.q == 1) {
            a aVar = this.r;
            StringBuffer stringBuffer = new StringBuffer(String.valueOf(al.h[this.q][0]));
            if (aj.r == 1) {
                strB = al.J;
            } else {
                strB = aj.o == 0 ? al.T : b(aj.o);
            }
            aVar.a(stringBuffer.append(strB).toString(), this.k, i, 0, graphics);
            int i6 = i + this.p;
            a aVar2 = this.r;
            StringBuffer stringBuffer2 = new StringBuffer(String.valueOf(al.h[this.q][1]));
            if (aj.r == 2) {
                strB2 = al.J;
            } else {
                strB2 = aj.p == 0 ? al.T : b(aj.p);
            }
            aVar2.a(stringBuffer2.append(strB2).toString(), this.k, i6, 0, graphics);
            int i7 = i6 + this.p;
            a aVar3 = this.r;
            StringBuffer stringBuffer3 = new StringBuffer(String.valueOf(al.h[this.q][2]));
            if (aj.r == 3) {
                strB3 = al.J;
            } else {
                strB3 = aj.q == 0 ? al.T : b(aj.q);
            }
            aVar3.a(stringBuffer3.append(strB3).toString(), this.k, i7, 0, graphics);
            int i8 = i7 + this.p;
            this.r.a(new StringBuffer(String.valueOf(al.h[this.q][3])).append(ab.E ? al.n : al.o).toString(), this.k, i8, 0, graphics);
            this.r.a(al.h[this.q][4], this.k, i8 + this.p, 0, graphics);
        }
    }

    @Override // defpackage.q
    public final void a() {
        int i;
        if (q.a[0] && this.n > 0) {
            this.n--;
        }
        if (q.a[1] && this.n < al.h[this.q].length - 1) {
            this.n++;
        }
        if (q.a[4] || q.a[6]) {
            d();
        }
        if (q.a[2]) {
            switch (this.n) {
                case 0:
                    if (p.a > 0) {
                        p.b(p.a - 1);
                    }
                    break;
                case 1:
                    if (p.b > 0) {
                        p.c(p.b - 1);
                    }
                    p.a(0);
                    break;
            }
        }
        if (q.a[3]) {
            switch (this.n) {
                case 0:
                    if (p.a < 5) {
                        p.b(p.a + 1);
                    }
                    break;
                case 1:
                    if (p.b < 5) {
                        p.c(p.b + 1);
                    }
                    p.a(0);
                    break;
            }
        }
        q.c();
        if (q.d == 1) {
            this.n = -1;
            if (q.c > this.m && (i = (q.c - this.m) / this.p) >= 0 && i < al.h[this.q].length) {
                this.n = i;
            }
            q.d = 2;
        }
        if (q.d == 3) {
            if (q.c > this.m) {
                d();
            }
            q.d = 0;
        }
    }

    private void d() {
        if (this.q != 0) {
            if (this.q == 1) {
                switch (this.n) {
                    case 0:
                        aj.r = 1;
                        break;
                    case 1:
                        aj.r = 2;
                        break;
                    case 2:
                        aj.r = 3;
                        break;
                    case 3:
                        ab.E = !ab.E;
                        break;
                    case 4:
                        this.q = 0;
                        this.n = 3;
                        break;
                }
            }
            return;
        }
        switch (this.n) {
            case 0:
                p.b((p.a + 1) % 6);
                break;
            case 1:
                p.c((p.b + 1) % 6);
                p.a(0);
                break;
            case 2:
                CMidlet.b = !CMidlet.b;
                break;
            case 3:
                this.q = 1;
                this.n = 0;
                break;
            case 4:
                al.a = 1 - al.a;
                al.a();
                break;
            case 5:
                this.o.b();
                break;
        }
    }

    private static String b(int i) {
        switch (i) {
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
                return new StringBuffer("KEY").append(i - 48).toString();
            default:
                return new StringBuffer("KEY CODE ").append(i).toString();
        }
    }

    @Override // defpackage.q
    public final void b() {
        this.i = true;
        this.n = 0;
        this.o = aj.d;
        this.q = 0;
        super.b();
    }
}
