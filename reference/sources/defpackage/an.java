package defpackage;

import javax.microedition.lcdui.Graphics;

/* JADX INFO: loaded from: XForce.jar:an.class */
public class an extends ai {
    public int p;
    public int q;
    public int r;
    public an s;
    public byte t;
    public boolean u;
    public byte v;
    public byte w;
    public short x;
    public short y;
    public short z;
    public int A;
    public int B;
    private int a;
    private int b;
    protected byte C;
    protected boolean D;
    protected int E;
    private byte c;

    static {
        byte[] bArr = {0, 0, 1, 1, 1, 2, 2, 2, 2, 3, 2, 2, 2, 2, 1, 1, 1, 0, 0, -1, -2, -2, -2, -3, -3, -3, -3, -3, -3, -3, -3, -3, -2, -2, -2, -1};
    }

    public an(int i, int i2, byte b) {
        this.v = b;
        this.x = (short) 5;
        this.y = (short) 5;
        this.g = i;
        this.h = i2;
        this.t = (byte) 0;
        switch (b) {
            case 16:
                if (c.V == null) {
                    c.V = c.a("/b1.png");
                }
                this.i = 15;
                this.k = 120;
                this.b = (24 * (this.i + ai.d)) / ai.d;
                this.t = (byte) 1;
                break;
            case 17:
                if (c.W == null) {
                    c.W = c.a("/b2.png");
                }
                this.i = 15;
                this.j = 120;
                this.a = (24 * (this.i + ai.d)) / ai.d;
                this.t = (byte) 1;
                break;
            case 41:
                if (c.J == null) {
                    c.J = c.a("/house2.png");
                }
                this.j = 22;
                this.k = 22;
                this.i = 10;
                this.a = (this.j * (this.i + ai.d)) / ai.d;
                this.b = (this.k * (this.i + ai.d)) / ai.d;
                this.t = (byte) 1;
                break;
            case 43:
            case 44:
                this.g += 9;
                this.h += 9;
                this.A = i;
                this.B = i2;
                this.i = 15;
                this.t = (byte) 1;
                break;
            case 62:
            case 63:
                this.x = (short) 10;
                this.y = (short) 10;
                b(24, 24);
                break;
            case 107:
                this.j = 16;
                this.k = 16;
                break;
            case 120:
            case 121:
            case 122:
                if (b == 120 && c.n == null) {
                    c.n = c.a("/lf1.png");
                }
                if (b == 121 && c.o == null) {
                    c.o = c.a("/lf2.png");
                }
                if (b == 122 && c.p == null) {
                    c.p = c.a("/lf3.png");
                }
                this.j = 24;
                this.k = 24;
                this.t = (byte) 0;
                this.x = (short) 10;
                this.y = (short) 10;
                break;
        }
    }

    public void a(Graphics graphics) {
        switch (this.v) {
            case 16:
                this.A = (((this.g - ai.e) * this.i) / ai.d) + this.g;
                this.B = (((this.h - ai.f) * this.i) / ai.d) + this.h;
                graphics.drawImage(c.V, this.g, this.h, 0);
                graphics.setColor(14106116);
                for (int i = 1; i < 5; i++) {
                    graphics.drawLine(this.g, this.h + (i * 24), this.A, this.B + (i * this.b));
                }
                graphics.drawLine(this.g + 1, this.h, this.A + 1, this.B + this.b);
                graphics.drawLine(this.A + 1, this.B + this.b, this.A + 1, this.B + (this.b << 2));
                graphics.drawLine(this.g + 1, this.h + 120, this.A + 1, this.B + (this.b << 2));
                graphics.setColor(16743234);
                graphics.drawLine(this.g, this.h, this.A, this.B + this.b);
                graphics.drawLine(this.A, this.B + this.b, this.A, this.B + (this.b << 2));
                graphics.drawLine(this.g, this.h + 120, this.A, this.B + (this.b << 2));
                break;
            case 17:
                this.A = (((this.g - ai.e) * this.i) / ai.d) + this.g;
                this.B = (((this.h - ai.f) * this.i) / ai.d) + this.h;
                graphics.drawImage(c.W, this.g, this.h, 0);
                graphics.setColor(14106116);
                for (int i2 = 1; i2 < 5; i2++) {
                    graphics.drawLine(this.g + (i2 * 24), this.h, this.A + (i2 * this.a), this.B);
                }
                graphics.drawLine(this.g, this.h + 1, this.A + this.a, this.B + 1);
                graphics.drawLine(this.A + this.a, this.B + 1, this.A + (this.a << 2), this.B + 1);
                graphics.drawLine(this.g + 120, this.h + 1, this.A + (this.a << 2), this.B + 1);
                graphics.setColor(16743234);
                graphics.drawLine(this.g, this.h, this.A + this.a, this.B);
                graphics.drawLine(this.A + this.a, this.B, this.A + (this.a << 2), this.B);
                graphics.drawLine(this.g + 120, this.h, this.A + (this.a << 2), this.B);
                break;
            case 41:
                graphics.setColor(16777215);
                if (this.u) {
                    graphics.setColor(16777215);
                    graphics.fillRect(this.g, this.h, this.j, this.k);
                }
                this.A = (((this.g - ai.e) * this.i) / ai.d) + this.g;
                this.B = (((this.h - ai.f) * this.i) / ai.d) + this.h;
                if (this.h > ai.f) {
                    c.a(this.g, this.h, this.g + this.j, this.h, this.A + this.a, this.B, this.A, this.B, 15658734, graphics);
                }
                if (this.g > ai.e) {
                    c.a(this.g, this.h, this.A, this.B, this.A, this.B + this.b, this.g, this.h + this.k, 14540253, graphics);
                }
                if (this.h + this.k < ai.f) {
                    c.a(this.g, this.h + this.k, this.A, this.B + this.b, this.A + this.a, this.B + this.b, this.g + this.j, this.h + this.k, 6052956, graphics);
                }
                if (this.g + this.j < ai.e) {
                    c.a(this.g + this.j, this.h, this.g + this.j, this.h + this.k, this.A + this.a, this.B + this.b, this.A + this.a, this.B, 8421504, graphics);
                }
                graphics.drawImage(c.J, this.A, this.B, 0);
                break;
            case 43:
            case 44:
                this.A = (((this.g - ai.e) * this.i) / ai.d) + this.g;
                this.B = (((this.h - ai.f) * this.i) / ai.d) + this.h;
                graphics.setColor(7096608);
                graphics.drawLine(this.g, this.h, this.A, this.B);
                graphics.drawLine(this.g + 1, this.h, this.A + 1, this.B);
                graphics.drawLine(this.g, this.h + 1, this.A, this.B + 1);
                graphics.drawLine(this.g + 1, this.h + 1, this.A + 1, this.B + 1);
                if (this.v == 43) {
                    graphics.drawLine(this.A + 1, this.B + 10, this.A + 1, this.B - 10);
                    graphics.setColor(10057036);
                    graphics.drawLine(this.A, this.B + 10, this.A, this.B - 10);
                } else {
                    graphics.drawLine(this.A + 10, this.B + 1, this.A - 10, this.B + 1);
                    graphics.setColor(10057036);
                    graphics.drawLine(this.A + 10, this.B, this.A - 10, this.B);
                }
                break;
            case 107:
                graphics.setColor(8559676);
                graphics.drawLine(this.g, this.h, this.g + 16, this.h);
                graphics.setColor(15335299);
                graphics.drawLine(this.g + 8, this.h, this.E == 0 ? this.g : this.g + 16, this.h);
                this.E = 1 - this.E;
                break;
            case 120:
                graphics.drawImage(c.n, this.g + 3, this.h + 3, 0);
                break;
            case 121:
                graphics.drawImage(c.o, this.g - 1, this.h - 1, 0);
                break;
            case 122:
                graphics.drawImage(c.p, this.g - 2, this.h - 2, 0);
                break;
        }
        b(graphics);
        if (this.u) {
            this.u = false;
        }
    }

    protected final void b(Graphics graphics) {
        if (this.c <= 0 || this.x >= this.y) {
            return;
        }
        graphics.setColor(0);
        graphics.fillRect((this.g + (this.j >> 2)) - 1, this.h - 6, (this.j >> 1) + 2, 4);
        graphics.setColor(16711680);
        graphics.fillRect(this.g + (this.j >> 2), this.h - 5, (this.x * (this.j >> 1)) / this.y, 2);
        this.c = (byte) (this.c - 1);
    }

    public boolean a(v vVar) {
        if (this.x <= 0 || !a((ai) vVar)) {
            return false;
        }
        this.u = true;
        if (this.x > 0) {
            this.x = (short) (this.x - vVar.a);
        }
        if (this.x <= 0) {
            b();
            return true;
        }
        ab.a((byte) 1, vVar.g, vVar.h, 0, 0, 0);
        this.c = (byte) 100;
        return true;
    }

    public void a(int i) {
        this.u = true;
        if (this.x > 0) {
            this.x = (short) (this.x - i);
        }
        if (this.x <= 0) {
            b();
        }
    }

    public void b() {
        int i = this.g / r.a;
        int i2 = this.h / r.a;
        switch (this.v) {
            case 41:
                r.c[i2][i] = 55;
                ab.b(i2, i, this);
                ab.a((byte) 0, this.g + (this.j / 2), this.h + (this.k / 2), 0, 0, 0);
                ab.a((byte) 5, this.g + (this.j / 2), this.h + (this.k / 2), 0, 0, 0);
                break;
            case 42:
                r.c[i2][i] = 1;
                ab.b(i2, i, this);
                ab.a((byte) 0, this.g + (this.j / 2), this.h + (this.k / 2), 0, 0, 0);
                ab.a((byte) 6, this.g + (this.j / 2), this.h + (this.k / 2), 0, 0, 0);
                break;
            case 62:
                ab.a(this.g + 12, this.h + 12, (byte) 4);
                ab.a((byte) 6, this.g + (this.j / 2), this.h + (this.k / 2), 0, 0, 0);
                r.c[i2][i] = 1;
                ab.b(i2, i, this);
                break;
            case 63:
                ab.a(this.g + 12, this.h + 12, (byte) 7);
                ab.a((byte) 6, this.g + (this.j / 2), this.h + (this.k / 2), 0, 0, 0);
                r.c[i2][i] = 1;
                ab.b(i2, i, this);
                break;
            case 107:
                ab.a((byte) 0, this.g + (this.j / 2), this.h + (this.k / 2), 0, 0, 0);
                break;
            case 120:
            case 121:
            case 122:
                ab.b(i2, i, this);
                ab.a((byte) 0, this.g + (this.j / 2), this.h + (this.k / 2), 0, 0, 0);
                ab.a((byte) 6, this.g + (this.j / 2), this.h + (this.k / 2), 0, 0, 0);
                if (c.d(2) != 0) {
                    ab.a((an) new g(this.g - 300, this.h - 300, (byte) 11)).s = ab.m;
                } else {
                    ab.a((an) new g(this.g - 300, this.h - 300, (byte) 12)).s = ab.m;
                }
                break;
        }
        this.o = true;
        if (this.z > 0) {
            ab.a(new StringBuffer("+").append((int) this.z).append("XP").toString(), this.g, this.h);
            ab.b(this.z);
        }
    }

    public void c() {
        ab.c(this.g + (this.j / 2), this.h + (this.k / 2));
    }
}
