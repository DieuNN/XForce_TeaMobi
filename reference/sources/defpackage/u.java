package defpackage;

import javax.microedition.lcdui.Graphics;

/* JADX INFO: loaded from: XForce.jar:u.class */
public final class u extends q {
    private int[] m;
    private int t;
    private int u;
    private int v;
    private int w;
    private int y;
    private byte[] z;
    private int[] i = {208, 221, 196, 181, 195, 201, 143, 147, 137, 159, 139, 140, 121, 138, 119, 155, 52, 41, 15, 32, 59, 69, 58, 44, 26, 70, 90, 88, 100, 95, 110};
    private int[] j = {104, 119, 119, 95, 39, 35, 106, 95, 97, 85, 66, 46, 78, 34, 19, 27, 98, 85, 69, 64, 71, 49, 38, 33, 26, 20, 20, 36, 63, 80, 91};
    private String[] k = {"DES#0", "DES#1", "DES#2", "DESERT KHMOER", "FRONT LINE\nOF BLACK GOLD", "THE HUGE TANK", "PECA CAPE", "AREA 27", "LOST IN WOODS", "OLD PORT", "TRAIN STATION\nLUTHENS", "VIP 1", "SEAL PORT", "DESERT WOLF", "INTEL 1", "WAR SHIP", "CUBEBACKER III", "V.I.P OF WARS", "CRUEL LAWS", "AREA 31", "INTEL 2", "AIR BASE", "NOWAYBACK", "HUNTING THE HUNTER", "LION HEART", "BLOOD LINE", "X PLAN", "INTEL 3", "G.U.N", "INTEL 4", "BLACK BASE", "MAP31", "MAP32", "MAP33", "MAP34", "MAP35"};
    private int o = -1;
    private int r = 241;
    private int s = 133;
    private String A = "";
    private int x = ((aj.c - this.s) >> 1) + 5;
    private int p = this.r >> 1;
    private int q = this.s >> 1;
    private int[] l = new int[this.i.length];
    private int[] n = new int[this.i.length];

    @Override // defpackage.q
    public final void b() {
        this.g = al.y;
        this.f = al.r;
        if (c.H == null) {
            ag.d();
        }
        if (c.d(3) == 0) {
            w.a(al.H[c.e(al.H.length)], 3);
        }
        for (int i = 0; i < this.i.length; i++) {
            int[] iArrA = h.a(i);
            if (!ab.H[i]) {
                if (this.p == (this.r >> 1) && this.q == (this.s >> 1)) {
                    this.p = this.i[i];
                    this.q = this.j[i];
                }
                this.y = iArrA[11];
                System.out.println(new StringBuffer("open ").append(this.y).toString());
                break;
            }
        }
        for (int i2 = 0; i2 < this.i.length; i2++) {
            int[] iArrA2 = h.a(i2);
            if (iArrA2[11] > this.y) {
                this.l[i2] = 1;
            } else {
                this.l[i2] = 0;
                if (iArrA2[7] > ab.B || iArrA2[8] > ab.A) {
                    this.l[i2] = 1;
                }
                if ((iArrA2[9] & 1) == 0 && ab.D == 0) {
                    this.l[i2] = 1;
                }
                if ((iArrA2[9] & 2) == 0 && ab.D == 1) {
                    this.l[i2] = 1;
                }
                if ((iArrA2[9] & 4) == 0 && ab.D == 2) {
                    this.l[i2] = 1;
                }
            }
            this.n[i2] = iArrA2[10];
        }
        this.h = new StringBuffer(String.valueOf(al.B)).append(ab.B).toString();
        super.b();
    }

    /* JADX WARN: Code duplicated, block: B:42:0x023a  */
    @Override // defpackage.q
    public final void a(Graphics graphics) {
        graphics.drawImage(c.F, 0, 0, 0);
        if (this.p < this.w + 10) {
            this.w = this.p - 10;
        }
        if (this.p > (this.w + aj.b) - 10) {
            this.w = (this.p - aj.b) + 10;
        }
        graphics.translate(-this.w, this.x);
        graphics.drawImage(c.R, 0, 0, 0);
        graphics.setColor(43520);
        graphics.drawRect(-2, -2, this.r + 3, this.s + 3);
        this.o = -1;
        this.e = null;
        for (int i = 0; i < this.i.length; i++) {
            if (this.l[i] == 0) {
                graphics.setColor(this.t < 5 ? 16711680 : 11141120);
            } else {
                graphics.setColor(8912896);
            }
            if (ab.H[i]) {
                graphics.drawLine(this.i[i] - 2, this.j[i] - 2, this.i[i] + 2, this.j[i] + 2);
                graphics.drawLine(this.i[i] + 2, this.j[i] - 2, this.i[i] - 2, this.j[i] + 2);
            } else if (this.n[i] == 0) {
                graphics.drawRect(this.i[i] - 1, this.j[i] - 1, 2, 2);
            } else {
                graphics.drawRect(this.i[i] - 2, this.j[i] - 2, 4, 4);
                graphics.drawRect(this.i[i] - 1, this.j[i] - 1, 2, 2);
            }
            if (Math.abs(this.p - this.i[i]) < 5 && Math.abs(this.q - this.j[i]) < 5 && this.l[i] < 2 && this.o != i) {
                this.m = h.a(i);
                this.z = c.d.a(new StringBuffer(String.valueOf(this.k[i])).append('\n').append(al.E).append(this.m[7]).append("$").toString());
                this.o = i;
                this.e = al.s;
            }
        }
        if (this.o == -1 || this.l[this.o] != 0) {
            this.u = 0;
        } else {
            graphics.setColor(16711680);
            graphics.drawArc(this.i[this.o] - this.u, this.j[this.o] - this.u, this.u + this.u, this.u + this.u, 0, 360);
            this.u++;
            if (this.u > 12) {
                this.u = 0;
            }
        }
        if (this.y < 2) {
            graphics.drawImage(c.X, 140, 30, 0);
        }
        if (this.y < 4) {
            graphics.drawImage(c.X, 60, 70, 0);
        }
        graphics.setColor(65280);
        graphics.drawLine(0, this.q, this.r, this.q);
        graphics.drawLine(this.p, 0, this.p, this.s);
        if (this.o != -1) {
            if (this.p - this.w < (aj.b >> 1)) {
                c.d.a(this.z, 0, this.v, this.p + 4, this.q + 2, 0, graphics);
            } else {
                c.d.a(this.z, 0, this.v, this.p - 4, this.q + 2, 1, graphics);
            }
            if (this.v < this.z.length) {
                this.v++;
            }
        } else {
            this.v = 0;
        }
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        c(graphics);
        this.t++;
        if (this.t > 10) {
            this.t = 0;
        }
    }

    @Override // defpackage.q
    public final void a(int i) {
        if (i == 48 || this.A.length() >= 10) {
            this.A = "";
        } else if (i > 48) {
            this.A = new StringBuffer(String.valueOf(this.A)).append(i - 48).toString();
        }
        System.out.println(new StringBuffer("cheat=").append(this.A).toString());
    }

    @Override // defpackage.q
    public final void a() {
        if (q.a[0] && this.q > 0) {
            this.q -= 3;
        }
        if (q.a[1] && this.q < this.s) {
            this.q += 3;
        }
        if (q.a[2] && this.p > 0) {
            this.p -= 3;
        }
        if (q.a[3] && this.p < this.r) {
            this.p += 3;
        }
        if (q.a[4] || q.a[6]) {
            boolean[] zArr = q.a;
            q.a[6] = false;
            zArr[4] = false;
            if (this.o != -1 && this.A.endsWith("11317")) {
                p.d();
                c.b();
                aj.e.d(this.o);
            } else if (this.o != -1 && this.l[this.o] == 0) {
                if (ab.B < this.m[7] && this.m[7] > 0) {
                    w.a(al.aj, 3);
                    return;
                }
                p.d();
                c.b();
                aj.e.d(this.o);
                ab.B -= this.m[7];
            }
        }
        if (q.a[7]) {
            q.a[7] = false;
            aj.f.b();
        }
        if (q.d == 1) {
            q.d = 1;
            if (q.c - this.x <= 0 || q.c - this.x >= this.s || q.b + this.w <= 0 || q.b + this.w >= this.r) {
                return;
            }
            this.p = q.b + this.w;
            this.q = q.c - this.x;
        }
    }
}
