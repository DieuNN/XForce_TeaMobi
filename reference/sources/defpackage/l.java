package defpackage;

import javax.microedition.lcdui.Graphics;

/* JADX INFO: loaded from: XForce.jar:l.class */
public final class l extends q {
    private boolean i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n = 0;
    private a o;

    public l() {
        if (aj.b >= 240) {
            this.j = 120;
            this.k = 24;
            this.o = c.c;
        } else {
            this.j = 105;
            this.k = 18;
            this.o = c.b;
        }
        this.l = (aj.c - this.j) >> 1;
        this.m = (aj.c - (al.g.length * this.k)) >> 1;
    }

    @Override // defpackage.q
    public final void a(Graphics graphics) {
        if (this.i) {
            q.b(graphics);
            this.i = false;
        }
        graphics.setColor(0);
        graphics.fillRect(0, this.l, aj.b, this.j);
        graphics.setColor(12615936);
        graphics.fillRect((aj.b - 120) >> 1, this.m + 1 + (this.n * this.k), 120, 14);
        int i = this.m;
        for (int i2 = 0; i2 < al.g.length; i2++) {
            this.o.a(al.g[i2], aj.b >> 1, i, 2, graphics);
            i += this.k;
        }
    }

    @Override // defpackage.q
    public final void a() {
        if (q.d == 1) {
            this.n = -1;
            if (q.c > this.m) {
                this.n = (q.c - this.m) / this.k;
                if (this.n >= al.g.length || al.g[this.n] == "") {
                    this.n = -1;
                }
            }
            q.d = 2;
        }
        if (q.d == 3) {
            if (this.n != -1) {
                d();
            }
            q.d = 0;
        }
    }

    @Override // defpackage.q
    public final void a(int i) {
        if (q.a[0] && this.n > 0) {
            this.n--;
        }
        if (q.a[1] && this.n < al.g.length - 1) {
            this.n++;
        }
        if (q.a[4]) {
            d();
        }
    }

    private void d() {
        switch (this.n) {
            case 0:
                aj.e.b();
                break;
            case 1:
                ab.I = true;
                aj.e.b();
                break;
            case 2:
                aj.h.b();
                break;
            case 3:
                aj.n.a(al.l, false);
                break;
        }
    }

    @Override // defpackage.q
    public final void b() {
        this.i = true;
        this.n = 0;
        aj.d = this;
    }
}
