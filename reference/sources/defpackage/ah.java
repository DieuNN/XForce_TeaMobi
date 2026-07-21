package defpackage;

import javax.microedition.lcdui.Graphics;

/* JADX INFO: loaded from: XForce.jar:ah.class */
public final class ah extends q {
    private a i;
    private static byte[] j;

    public ah() {
        this.i = aj.b >= 240 ? c.b : c.a;
    }

    @Override // defpackage.q
    public final void a(Graphics graphics) {
        graphics.drawImage(c.F, 0, 0, 0);
        c(graphics);
        this.i.a(j, 8, (aj.c - 100) >> 1, 0, graphics);
    }

    @Override // defpackage.q
    public final void a() {
        if (q.a[7]) {
            aj.f.b();
        }
        q.c();
    }

    @Override // defpackage.q
    public final void b() {
        this.f = al.r;
        this.g = al.P;
        super.b();
        int iCurrentTimeMillis = (((int) (System.currentTimeMillis() - c.ac)) / 1000) + c.ad;
        String string = new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(String.valueOf(al.ak)).append(ab.z).toString())).append("\n").append(al.al).append(iCurrentTimeMillis / 3600).append(":").append((iCurrentTimeMillis / 60) % 60).toString())).append("\n").append(al.D).append(ab.A).toString())).append("\n").append(al.B).append(ab.B).append("$").toString();
        int i = 0;
        for (int i2 = 0; i2 < ab.H.length; i2++) {
            if (ab.H[i2]) {
                i++;
            }
        }
        j = this.i.a(new StringBuffer(String.valueOf(string)).append("\n").append(al.am).append((i * 100) / ab.H.length).append("%").toString());
    }
}
