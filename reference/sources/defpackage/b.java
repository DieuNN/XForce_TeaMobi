package defpackage;

import javax.microedition.lcdui.Graphics;

/* JADX INFO: loaded from: XForce.jar:b.class */
public final class b extends q {
    private boolean i;
    private int j = 105;
    private int k;
    private int l;
    private String m;
    private byte[] n;
    private boolean o;

    @Override // defpackage.q
    public final void a(Graphics graphics) {
        this.k = aj.b >> 1;
        this.l = (aj.c - 105) >> 1;
        graphics.setColor(0);
        if (this.i) {
            q.b(graphics);
            this.i = false;
        }
        graphics.fillRect(0, this.l, aj.b, this.j);
        (aj.b >= 240 ? c.b : c.a).a(this.n, this.k, this.l + 20, 2, graphics);
        graphics.setColor(12615936);
        graphics.fillRect(this.o ? this.k - 40 : this.k, this.l + 60, 40, 14);
        c.b.a(al.p, this.k - 20, this.l + 60, 2, graphics);
        c.b.a(al.q, this.k + 20, this.l + 60, 2, graphics);
    }

    @Override // defpackage.q
    public final void a() {
        if (q.a[2]) {
            this.o = true;
        }
        if (q.a[3]) {
            this.o = false;
        }
        if (q.a[4] || q.a[6]) {
            d();
        }
        q.c();
        if (q.d == 1) {
            if (q.b > 80 && q.b < 120 && q.c > this.l + 60 && q.c < this.l + 74) {
                this.o = true;
            }
            if (q.b > 120 && q.b < 160 && q.c > this.l + 60 && q.c < this.l + 74) {
                this.o = false;
            }
            q.d = 2;
        }
        if (q.d == 3) {
            if ((q.b > 80 && q.b < 120 && q.c > this.l + 60 && q.c < this.l + 74) || (q.b > 120 && q.b < 160 && q.c > this.l + 60 && q.c < this.l + 74)) {
                d();
            }
            q.d = 0;
        }
    }

    private void d() {
        if (this.m == al.j) {
            if (this.o) {
                p.a = 3;
                p.b = 3;
                p.a("/menu.mid");
            } else {
                p.a = 0;
                p.b = 0;
            }
            aj.f.b();
            return;
        }
        if (this.m == al.k) {
            if (this.o) {
                CMidlet.a.a();
                return;
            } else {
                aj.f.b();
                return;
            }
        }
        if (this.m == al.m) {
            if (this.o) {
                new am().b();
                return;
            } else {
                aj.f.b();
                return;
            }
        }
        if (this.m == al.l) {
            if (!this.o) {
                aj.f.b();
            } else {
                ab.J = false;
                aj.e.d();
            }
        }
    }

    public final void a(String str, boolean z) {
        this.i = true;
        this.m = str;
        this.n = c.a.a(str);
        this.o = z;
        b();
    }
}
