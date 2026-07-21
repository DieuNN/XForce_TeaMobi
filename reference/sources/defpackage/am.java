package defpackage;

import javax.microedition.lcdui.Graphics;

/* JADX INFO: loaded from: XForce.jar:am.class */
public final class am extends q {
    private int m;
    private int n;
    private String o;
    private int p;
    private char[][] q = {new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'}, new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'}, new char[]{'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T'}, new char[]{'U', 'V', 'W', 'X', 'Y', 'Z', ' ', '+', '-', '$'}};
    private int i = (aj.b - 120) >> 1;
    private int j = (aj.c - 48) >> 1;
    private int k = 12;
    private int l = 12;

    public am() {
        int i = this.i;
        a aVar = c.a;
        String str = al.ak;
        this.p = i + aVar.a(str, 0, str.length());
        this.o = "PLAYER";
    }

    @Override // defpackage.q
    public final void a(Graphics graphics) {
        graphics.drawImage(c.F, 0, 0, 0);
        for (int i = 0; i < 4; i++) {
            for (int i2 = 0; i2 < 10; i2++) {
                c.d.a(this.q[i][i2], this.i + (i2 * this.k), this.j + (i * this.l), graphics);
            }
        }
        graphics.setColor(65280);
        if (this.m >= 0 && this.n >= 0) {
            graphics.drawRect((this.i + (this.n * this.k)) - 2, (this.j + (this.m * this.l)) - 2, c.d.a(this.q[this.m][this.n]) + 3, 12);
        }
        c.a.a(al.ak, this.i, this.j - 25, 0, graphics);
        a aVar = c.d;
        String str = this.o;
        int i3 = this.p;
        int i4 = this.j - 20;
        str.length();
        aVar.a(str, i3, i4, 0, graphics);
    }

    private void d() {
        if (this.m < 0 || this.n < 0) {
            return;
        }
        this.o = new StringBuffer(String.valueOf(this.o)).append(this.q[this.m][this.n]).toString();
    }

    @Override // defpackage.q
    public final void a() {
        if (q.a[0]) {
            this.m = (this.m + 3) % 4;
        }
        if (q.a[1]) {
            this.m = (this.m + 1) % 4;
        }
        if (q.a[2]) {
            this.n = (this.n + 9) % 10;
        }
        if (q.a[3]) {
            this.n = (this.n + 1) % 10;
        }
        if (q.a[4] && this.o.length() < 15) {
            d();
        }
        if (q.a[7] && this.o.length() > 0) {
            this.o = this.o.substring(0, this.o.length() - 1);
        }
        if (q.a[6] && this.o.length() > 0) {
            ab.z = this.o;
            ab.A = 100;
            ab.B = 1000;
            ab.D = 0;
            byte[] bArr = new byte[5];
            bArr[3] = 60;
            ab.C = new byte[][]{bArr, new byte[]{-1, 0, 0, 60, 0}, new byte[]{-1, 0, 0, 60, 0}};
            for (int i = 0; i < ab.H.length; i++) {
                ab.H[i] = false;
            }
            c.ad = 0;
            c.ac = System.currentTimeMillis();
            aj.f.a(al.d, 0);
            aj.f.b();
            af.i = 1;
        }
        q.c();
        if (q.d == 1) {
            this.m = -1;
            if (q.b > this.i && q.c > this.j) {
                this.n = (q.b - this.i) / this.k;
                if (this.n >= this.q[0].length) {
                    this.n = -1;
                }
                this.m = (q.c - this.j) / this.l;
                if (this.m >= this.q.length) {
                    this.m = -1;
                }
            }
            q.d = 2;
        }
        if (q.d == 3) {
            d();
            q.d = 0;
        }
    }

    @Override // defpackage.q
    public final void b() {
        super.b();
        this.e = "OK";
        this.f = "CLEAR";
        w.a(al.an, 3);
    }
}
