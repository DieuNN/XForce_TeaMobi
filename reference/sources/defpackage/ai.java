package defpackage;

/* JADX INFO: loaded from: XForce.jar:ai.class */
public abstract class ai {
    public static int d = 100;
    public static int e;
    public static int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public boolean n;
    public boolean o;
    private int a;
    private int b;

    public final void a(int i, int i2, int i3, int i4) {
        this.g = i;
        this.h = i2;
        this.j = i3;
        this.k = i4;
    }

    public final void a(int i, int i2) {
        this.g = i;
        this.h = i2;
    }

    public final void b(int i, int i2) {
        this.j = i;
        this.k = i2;
    }

    public final void f() {
        this.a += this.l;
        this.g += this.a >> 10;
        this.a &= 1023;
        this.b += this.m;
        this.h += this.b >> 10;
        this.b &= 1023;
    }

    public boolean a(ai aiVar) {
        return this.g + this.j > aiVar.g && this.g < aiVar.g + aiVar.j && this.h + this.k > aiVar.h && this.h < aiVar.h + aiVar.k;
    }

    public final boolean b(int i, int i2, int i3, int i4) {
        return this.g + this.j > i && this.g < i + 48 && this.h + this.k > i2 && this.h < i2 + 48;
    }

    public final boolean c(int i, int i2) {
        return this.g + this.j > i && this.g < i && this.h + this.k > i2 && this.h < i2;
    }

    public void a() {
    }
}
