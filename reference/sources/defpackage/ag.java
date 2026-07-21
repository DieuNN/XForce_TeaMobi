package defpackage;

import javax.microedition.lcdui.Graphics;

/* JADX INFO: loaded from: XForce.jar:ag.class */
public final class ag extends q {
    private int i;
    private int j;
    private int l;
    private static String[] p = {"/humer_big.png", "/tank_big.png", "/heli_big.png"};
    private static byte[] q;
    private int r;
    private int s;
    private int t;
    private int k = 1;
    private int m = 1;
    private int[] n = {0, 20000, 100000};
    private int[] o = {0, 50000, 200000};
    private String v = "";
    private int u = (aj.c >> 1) + 60;

    public ag() {
        int[][] iArr = {new int[]{50, 30, 40}, new int[]{40, 60, 70}, new int[]{75, 70, 50}};
        if (aj.c < 200) {
            this.u -= 15;
        }
    }

    @Override // defpackage.q
    public final void a(Graphics graphics) {
        graphics.drawImage(c.F, 0, 0, 0);
        c(graphics);
        this.i = this.j;
        if (this.l < this.k) {
            if (this.j > (-aj.b)) {
                this.j -= this.m;
                this.m <<= 1;
            } else {
                c.H = c.a(p[this.k]);
                this.m = 1;
                this.l = this.k;
                this.j = aj.b << 1;
            }
        }
        if (this.l > this.k) {
            if (this.j < (aj.b << 1)) {
                this.j += this.m;
                this.m <<= 1;
            } else {
                c.H = c.a(p[this.k]);
                this.m = 1;
                this.l = this.k;
                this.j = -aj.b;
            }
        }
        if (this.l == this.k) {
            this.j -= (this.j - (aj.b >> 1)) >> 1;
        }
        graphics.drawImage(c.H, this.j, (aj.c >> 1) + 10, 3);
        if (this.i == this.j) {
            if (q == null) {
                q = c.a.a(al.G[this.k]);
                this.t = c.a.a(q);
                this.s = aj.b;
            }
            c.a.a(q, this.s, this.u, 0, graphics);
            this.s -= 2;
            if (this.s < (-this.t)) {
                this.s = aj.b;
            }
        }
        c.b.a(al.F[this.k], aj.b >> 1, (aj.c >> 1) - 30, 2, graphics);
        if (ab.C[this.k][0] == -1) {
            if (this.o[this.k] > ab.A) {
                graphics.drawImage(c.X, aj.b >> 1, aj.c >> 1, 3);
                c.d.a(new StringBuffer("PRICE: ").append(this.n[this.k]).append("$\nXP: ").append(this.o[this.k]).toString(), aj.b >> 1, (aj.c >> 1) + 20, 2, graphics);
            } else {
                c.d.a(new StringBuffer("PRICE: ").append(this.n[this.k]).append("$").toString(), aj.b >> 1, (aj.c >> 1) + 20, 2, graphics);
            }
        }
        if (this.k > 0) {
            c.b.a("<", 4 + this.r, aj.c >> 1, 0, graphics);
        }
        if (this.k < 2) {
            c.b.a(">", (aj.b - 4) - this.r, aj.c >> 1, 1, graphics);
        }
        if (this.r < 4) {
            this.r++;
        } else {
            this.r = 0;
        }
    }

    @Override // defpackage.q
    public final void a(int i) {
        if (i == 48 || this.v.length() >= 10) {
            this.v = "";
        } else if (i > 48) {
            this.v = new StringBuffer(String.valueOf(this.v)).append(i - 48).toString();
        }
        System.out.println(new StringBuffer("cheat=").append(this.v).toString());
    }

    @Override // defpackage.q
    public final void a() {
        if (q.d == 1) {
            if (ab.a(q.b, q.c, 0, (aj.c >> 1) - 10, 20, 20)) {
                q.a[2] = true;
            }
            if (ab.a(q.b, q.c, aj.b - 20, (aj.c >> 1) - 10, 20, 20)) {
                q.a[3] = true;
            }
            q.d = 2;
        }
        if (q.d == 3) {
            q.d = 0;
        }
        if (q.a[2] || q.a[3]) {
            if (q.a[2] && this.k > 0) {
                this.k--;
                q = null;
            }
            if (q.a[3] && this.k < 2) {
                this.k++;
                q = null;
            }
            if (ab.C[this.k][0] != -1 || (this.n[this.k] <= ab.B && this.o[this.k] <= ab.A)) {
                this.e = al.t;
            } else {
                this.e = al.u;
            }
        }
        if (q.a[6]) {
            if (ab.C[this.k][0] != -1 || this.v.endsWith("11379")) {
                ab.D = this.k;
                aj.f.b();
            } else if (this.o[this.k] > ab.A) {
                w.a(al.ai, 3);
            } else if (this.n[this.k] > ab.B) {
                w.a(al.aj, 4);
            } else {
                ab.C[this.k][0] = 0;
                ab.B -= this.n[this.k];
                af.i = 3 + this.k;
            }
        }
        if (q.a[7]) {
            if (ab.D != this.k) {
                c.H = c.a(p[ab.D]);
            }
            aj.f.b();
        }
        q.c();
    }

    public static void d() {
        c.H = c.a(p[ab.D]);
    }

    @Override // defpackage.q
    public final void b() {
        this.e = al.t;
        this.f = al.r;
        this.g = al.v;
        q = null;
        int i = ab.D;
        this.k = i;
        this.l = i;
        this.j = aj.b >> 1;
        if (c.H == null) {
            c.H = c.a(p[this.k]);
        }
        this.h = new StringBuffer(String.valueOf(al.B)).append(ab.B).toString();
        super.b();
    }
}
