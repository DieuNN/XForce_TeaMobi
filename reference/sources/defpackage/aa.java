package defpackage;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/* JADX INFO: loaded from: XForce.jar:aa.class */
public final class aa extends q {
    private Image i = c.a("/logo.png");
    private int j = 50;

    @Override // defpackage.q
    public final void a(Graphics graphics) {
        int width = aj.a.getWidth();
        int height = aj.a.getHeight();
        graphics.setColor(0);
        graphics.fillRect(0, 0, width, height);
        graphics.drawImage(this.i, (width - this.i.getWidth()) >> 1, (height - this.i.getHeight()) >> 1, 0);
    }

    @Override // defpackage.q
    public final void a() {
        this.j--;
        if (this.j == 0) {
            aj.a();
            aj.n.a(al.j, true);
        }
    }
}
