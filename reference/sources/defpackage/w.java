package defpackage;

import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/* JADX INFO: loaded from: XForce.jar:w.class */
public final class w {
    public static boolean a;
    private static int b;
    private static int c;
    private static int d;
    private static int e;
    private static String[] f;
    private static int g = 4;
    private static int h;
    private static int i;
    private static int j;
    private static boolean k;
    private static int l;
    private static Image m;

    public static void a(String str, int i2) {
        a aVar = c.e;
        Vector vector = new Vector();
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = -1;
        while (i3 < str.length()) {
            char cCharAt = str.charAt(i3);
            if (cCharAt == ' ') {
                i6 = i3;
            }
            int iA = i5 + aVar.a(cCharAt);
            i5 = iA;
            if (iA > 120 || cCharAt == '\n') {
                if (cCharAt != '\n' && i6 != -1) {
                    i3 = i6;
                }
                vector.addElement(str.substring(i4, i3));
                if (str.charAt(i3) == ' ' || cCharAt == '\n') {
                    i3++;
                }
                i4 = i3;
                i5 = 0;
                i6 = -1;
            } else {
                i3++;
            }
        }
        if (i3 > i4) {
            vector.addElement(str.substring(i4, i3));
        }
        String[] strArr = new String[vector.size()];
        for (int i7 = 0; i7 < strArr.length; i7++) {
            strArr[i7] = (String) vector.elementAt(i7);
        }
        f = strArr;
        i = 0;
        j = 0;
        h = 0;
        a = true;
        b = 3;
        c = 33;
        d = b + 115;
        e = c - 30;
        if (aj.b <= 128) {
            c = 0;
            b = 0;
            d = aj.b - 54;
            e = aj.c - 70;
        }
        if (aj.b >= 240) {
            b = 30;
            c = 50;
            d = b + 130;
            e = c - 30;
        }
        m = c.a(new StringBuffer("/face").append(i2).append(".png").toString());
    }

    public static void a() {
        k = false;
        if (q.d == 1) {
            q.d = 2;
        }
        if (q.d == 3) {
            aj.s = 1;
            q.d = 0;
        }
        if (j < f[h + i].length()) {
            j++;
            if (aj.s != 0) {
                i = g - 1;
                if (h + i > f.length - 1) {
                    i = (f.length - 1) - h;
                }
                j = f[h + i].length();
            }
        } else if (h + i == f.length - 1) {
            k = true;
            if (aj.s != 0) {
                a = false;
            }
        } else if (i < g - 1) {
            i++;
            j = 0;
        } else {
            k = true;
            if (aj.s != 0) {
                h += g;
                i = 0;
                j = 0;
            }
        }
        q.c();
        aj.s = 0;
    }

    public static void a(Graphics graphics) {
        graphics.drawImage(m, d, e, 0);
        graphics.setColor(16777215);
        graphics.fillRoundRect(b, c, 128, 54, 6, 6);
        graphics.setColor(0);
        graphics.drawRoundRect(b, c, 128, 54, 6, 6);
        graphics.drawImage(c.S, b + 128, c + 16, 0);
        int iA = c;
        for (int i2 = 0; i2 < i; i2++) {
            c.e.a(f[h + i2], b + 4, iA, 0, graphics);
            iA += c.e.a();
        }
        c.e.a(f[h + i], 0, j, b + 4, iA, 0, graphics);
        if (k) {
            if (l < 5) {
                graphics.drawImage(c.Z, b + 115, c + 45, 0);
            }
            int i3 = l + 1;
            l = i3;
            if (i3 > 10) {
                l = 0;
            }
        }
    }
}
