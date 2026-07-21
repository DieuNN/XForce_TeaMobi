package defpackage;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/* JADX INFO: loaded from: XForce.jar:r.class */
public final class r {
    public static short[][] c;
    public static byte[][] d;
    private static int i;
    public static int e;
    public static int f;
    public static int g;
    public static int h;
    private static int j;
    private static int k;
    private static int l;
    private static int m;
    private static int n;
    private static int o;
    private static int p;
    private static int q;
    private static int r;
    private static int s;
    private static byte[] t;
    private static Image[] u;
    public static int a = 24;
    public static int b = 2;
    private static int v = 79;
    private static byte[] w = {0, 0, 1, 1, 1, 2, 2, 2, 2, 3, 2, 2, 2, 2, 1, 1, 1, 0, 0, -1, -2, -2, -2, -3, -3, -3, -3, -3, -3, -3, -3, -3, -2, -2, -2, -1};

    public r() {
        u = new Image[v];
    }

    public static void a(int i2) {
        System.out.println(new StringBuffer("Set title ID:").append(i2).toString());
        b = i2;
        Image imageA = c.a(new StringBuffer("/t").append(i2).append(".png").toString());
        for (int i3 = 0; i3 < v; i3++) {
            u[i3] = Image.createImage(a, a);
            u[i3].getGraphics().drawImage(imageA, 0, (-i3) * a, 0);
        }
        switch (i2) {
            case 1:
                t = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 5, 2, 30, 30, 30, 10, 10, 10, 10, 10, 10, 10, 10, 5, 10, 5, 5, 5, 5, 0};
                break;
            case 2:
                t = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 5, 2, 100, 100, 100, 10, 10, 10, 10, 10, 10, 10, 10, 5, 10, 5, 5, 5, 5, 0};
                break;
            case 3:
                t = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 5, 2, 30, 30, 30, 10, 10, 10, 10, 10, 10, 10, 10, 5, 10, 5, 5, 5, 5, 0};
                break;
        }
    }

    public static void a(short[][] sArr) {
        c = sArr;
        f = sArr.length;
        int length = c[0].length;
        e = length;
        g = length * a;
        h = f * a;
        d = new byte[f][e];
        for (int i2 = 0; i2 < f; i2++) {
            for (int i3 = 0; i3 < e; i3++) {
                d[i2][i3] = t[c[i2][i3]];
            }
        }
    }

    public static void a(Graphics graphics) {
        j = (-graphics.getTranslateY()) / a;
        int i2 = (-graphics.getTranslateX()) / a;
        l = i2;
        n = i2 * a;
        p = (-graphics.getTranslateX()) + ab.i;
        q = j * a;
        s = (-graphics.getTranslateY()) + ab.j;
        k = j;
        r = q;
        while (r < s) {
            if (k >= 0 && k < f) {
                m = l;
                o = n;
                while (o < p) {
                    if (m >= 0 && m < e && c[k][m] > 0) {
                        graphics.drawImage(u[c[k][m] - 1], o, r, 0);
                        if (b == 2) {
                            if (c[k][m] == 36 || c[k][m] == 37) {
                                short[] sArr = c[k];
                                int i3 = m;
                                sArr[i3] = (short) (sArr[i3] + 1);
                            } else if (c[k][m] == 38) {
                                c[k][m] = 36;
                            }
                        }
                    }
                    o += a;
                    m++;
                }
            }
            r += a;
            k++;
        }
    }

    public static void b(Graphics graphics) {
        i = (i + 1) % w.length;
        k = j;
        r = q;
        while (r < s) {
            if (k >= 0 && k < f) {
                m = l;
                o = n;
                while (o < p) {
                    if (m >= 0 && m < e && c[k][m] == 40) {
                        int i2 = (((o - ai.e) * 10) / ai.d) + o;
                        int i3 = (((r - ai.f) * 10) / ai.d) + r;
                        int length = ((i + (k * 12)) + (m * 24)) % w.length;
                        graphics.drawImage(c.Q, (i2 + w[length]) - 3, (i3 + w[(length + (w.length / 3)) % w.length]) - 3, 0);
                    }
                    o += a;
                    m++;
                }
            }
            r += a;
            k++;
        }
    }

    public static boolean a(int i2, int i3) {
        if (i2 < 0 || i2 >= f || i3 < 0 || i3 >= e) {
            return true;
        }
        return ((c[i2][i3] == 40 && ab.n[i2][i3] == null) || d[i2][i3] == 0) ? false : true;
    }

    public static void a(int i2, int i3, int i4) {
        if (i2 < 0 || i2 >= f || i3 < 0 || i3 >= e) {
            return;
        }
        d[i2][i3] = (byte) i4;
    }

    public static void b(int i2, int i3) {
        d[i2][i3] = t[c[i2][i3]];
    }

    public static short c(int i2, int i3) {
        m = i2 / a;
        int i4 = i3 / a;
        k = i4;
        if (i4 < 0 || k >= f || m < 0 || m >= e) {
            return (short) -1;
        }
        return c[k][m];
    }

    public static boolean b(int i2, int i3, int i4) {
        m = i2 / a;
        int i5 = i3 / a;
        k = i5;
        if (i5 < 0 || k >= f || m < 0 || m >= e) {
            return false;
        }
        if (c[k][m] < 60 || c[k][m] >= 79) {
            if (b != 2 || c[k][m] < 12 || c[k][m] > 17) {
                return false;
            }
            if (d[k][m] > 0) {
                byte[] bArr = d[k];
                int i6 = m;
                bArr[i6] = (byte) (bArr[i6] - i4);
            }
            if (d[k][m] > 0) {
                return true;
            }
            ab.a((byte) 0, i2, i3, 0, 0, 0);
            c[k][m] = 18;
            b(k, m);
            return true;
        }
        if (d[k][m] > 0) {
            byte[] bArr2 = d[k];
            int i7 = m;
            bArr2[i7] = (byte) (bArr2[i7] - i4);
        }
        if (d[k][m] > 0) {
            ab.a((byte) 1, i2, i3, 0, 0, 0);
            return true;
        }
        switch (c[k][m]) {
            case 60:
                ab.a(i2, i3, (byte) c.e(6));
                c[k][m] = 1;
                break;
            case 61:
                System.out.println("hur oil ");
                ab.a((byte) 0, i2, i3, 0, 0, 0);
                ab.a((byte) 14, i2, i3, 0, null);
                c[k][m] = 1;
                break;
            case 62:
            case 63:
            case 64:
            default:
                ab.a((byte) 6, (m * a) + 12, (k * a) + 12, 0, 0, 0);
                ab.a((byte) 0, i2, i3, 0, 0, 0);
                c[k][m] = 1;
                break;
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
                ab.a((byte) 0, i2, i3, 0, 0, 0);
                c[k][m] = 55;
                break;
            case 73:
                ab.a((byte) 0, i2, i3, 0, 0, 0);
                c[k][m] = 59;
                break;
            case 74:
                ab.a((byte) 0, i2, i3, 0, 0, 0);
                c[k][m] = 54;
                break;
        }
        b(k, m);
        return true;
    }

    public static boolean c(int i2, int i3, int i4) {
        m = i2 / a;
        k = i3 / a;
        if (c[k][m] < 73 || c[k][m] >= 79) {
            return false;
        }
        if (d[k][m] > 0) {
            byte[] bArr = d[k];
            int i5 = m;
            bArr[i5] = (byte) (bArr[i5] - i4);
        }
        if (d[k][m] > 0) {
            ab.a((byte) 1, i2, i3, 0, 0, 0);
            return true;
        }
        switch (c[k][m]) {
            case 73:
                ab.a((byte) 0, i2, i3, 0, 0, 0);
                c[k][m] = 59;
                break;
            case 74:
                ab.a((byte) 0, i2, i3, 0, 0, 0);
                c[k][m] = 55;
                break;
            default:
                ab.a((byte) 6, (m * a) + 12, (k * a) + 12, 0, 0, 0);
                ab.a((byte) 0, i2, i3, 0, 0, 0);
                c[k][m] = 1;
                break;
        }
        b(k, m);
        return true;
    }

    public static void a() {
        c = null;
        d = null;
        f = 0;
        e = 0;
        h = 0;
        g = 0;
        for (int i2 = 0; i2 < v; i2++) {
            u[i2] = null;
        }
    }
}
