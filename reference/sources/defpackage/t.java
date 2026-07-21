package defpackage;

import javax.microedition.lcdui.Graphics;

/* JADX INFO: loaded from: XForce.jar:t.class */
public final class t extends q {
    private byte[][] i;
    private byte[] j;
    private int k;
    private int l = 60;
    private int m;
    private boolean n;

    @Override // defpackage.q
    public final void a(Graphics graphics) {
        if (this.n) {
            graphics.drawImage(c.F, 0, 0, 0);
            c(graphics);
            this.n = false;
        }
        graphics.setClip(0, this.l, 120, this.m - this.l);
        graphics.drawImage(c.F, 0, 0, 0);
        if (this.g == al.z) {
            int i = 0;
            for (int i2 = 0; i2 < al.R.length; i2++) {
                if (i2 == 0 || al.R[i2 - 1] == "") {
                    c.a.a(this.i[i2], 8, this.k + i, 0, graphics);
                } else {
                    c.b.a(this.i[i2], 120, this.k + i, 1, graphics);
                }
                i += 20;
            }
            int i3 = i + 40;
            graphics.drawImage(c.Y, 16, this.k + i3, 0);
            if (this.k > (this.l - i3) - 50) {
                this.k--;
                return;
            } else {
                this.k = this.m;
                return;
            }
        }
        c.a.a(this.j, 8, this.k, 0, graphics);
        if (q.a[4]) {
            return;
        }
        if (q.a[0] && this.k > (-260) - this.l) {
            this.k -= 3;
            return;
        }
        if (q.a[1] && this.k < this.m) {
            this.k += 3;
        } else if (this.k > (-260) - this.l) {
            this.k--;
        } else {
            this.k = this.m;
        }
    }

    @Override // defpackage.q
    public final void b() {
        this.i = new byte[al.R.length][];
        for (int i = 0; i < al.R.length; i++) {
            if (i == 0 || al.R[i - 1] == "") {
                this.i[i] = c.a.a(al.R[i]);
            } else {
                this.i[i] = c.b.a(al.R[i]);
            }
        }
        this.g = al.z;
        this.m = aj.c;
        this.k = this.m;
        this.n = true;
        super.b();
    }

    public final void d() {
        this.j = c.a.a(al.S);
        this.g = al.A;
        this.m = aj.c;
        this.k = this.m;
        this.n = true;
        super.b();
    }

    @Override // defpackage.q
    public final void a(int i) {
        if (i == -5) {
            aj.f.b();
        }
    }
}
