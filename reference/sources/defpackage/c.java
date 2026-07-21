package defpackage;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Random;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.rms.RecordStore;

/* JADX INFO: loaded from: XForce.jar:c.class */
public final class c {
    private static short[] af;
    private static int[] ag;
    public static a a;
    public static a b;
    public static a c;
    public static a d;
    public static a e;
    public static ak f;
    public static ak g;
    public static ak h;
    public static ak i;
    public static ak j;
    public static ak k;
    public static ak l;
    public static ak m;
    public static Image n;
    public static Image o;
    public static Image p;
    public static ak q;
    public static ak r;
    public static ak s;
    public static ak t;
    public static ak v;
    public static ak w;
    public static ak x;
    public static ak y;
    public static ak z;
    public static ak A;
    public static ak B;
    public static ak C;
    public static ak D;
    public static ak E;
    public static Image F;
    public static Image G;
    public static Image H;
    public static Image I;
    public static Image J;
    public static ad K;
    public static Image L;
    public static Image M;
    public static Image N;
    public static Image O;
    public static Image P;
    public static Image Q;
    public static Image R;
    public static Image S;
    public static ad T;
    public static Image U;
    public static Image V;
    public static Image W;
    public static Image X;
    public static Image Y;
    public static Image Z;
    public static ak aa;
    public static ak ab;
    private static Random ah;
    private static int[] ai;
    private static int aj;
    public static long ac;
    public static int ad;
    private static short[] ae = {0, 18, 36, 54, 71, 89, 107, 125, 143, 160, 178, 195, 213, 230, 248, 265, 282, 299, 316, 333, 350, 367, 384, 400, 416, 433, 449, 465, 481, 496, 512, 527, 543, 558, 573, 587, 602, 616, 630, 644, 658, 672, 685, 698, 711, 724, 737, 749, 761, 773, 784, 796, 807, 818, 828, 839, 849, 859, 868, 878, 887, 896, 904, 912, 920, 928, 935, 943, 949, 956, 962, 968, 974, 979, 984, 989, 994, 998, 1002, 1005, 1008, 1011, 1014, 1016, 1018, 1020, 1022, 1023, 1023, 1024, 1024};
    public static ak[] u = new ak[10];

    public static void a() {
        ah = new Random();
        ai = new int[100];
        for (int i2 = 0; i2 < 100; i2++) {
            ai[i2] = ah.nextInt();
        }
        a = new a("fontSS.png", new byte[]{5, 2, 5, 5, 5, 5, 5, 5, 5, 5, 3, 3, 3, 3, 5, 4, 4, 4, 2, 5, 5, 5, 5, 5, 5, 5, 5, 5, 4, 5, 5, 5, 6, 5, 5, 5, 5, 5, 5, 4, 5, 6, 6, 6, 6, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 5, 5, 5, 6, 5}, 14, 4, new StringBuffer(String.valueOf("0123456789.,:!?()-'/ABCDEFGHIJKLMNOPQRSTUVWXYZÁÀẢÃẠĂẮẰẲẴẶÂẤẦẨẪẬÉÈẺẼẸÊẾỀỂỄỆÍÌỈĨỊÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢÚÙỦŨỤƯỨỪỬỮỰÝỲỶỸỴĐ")).append("<>$%").toString());
        b = new a("fcg10.png", new byte[]{7, 4, 7, 7, 7, 7, 7, 7, 7, 7, 3, 3, 3, 3, 6, 4, 4, 3, 2, 4, 8, 7, 8, 7, 6, 6, 9, 7, 3, 6, 7, 6, 10, 8, 8, 7, 9, 7, 6, 7, 7, 8, 10, 8, 9, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 3, 3, 3, 3, 3, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 7, 6, 6, 6, 6}, 14, 4, new StringBuffer(String.valueOf("0123456789.,:!?()-'/ABCDEFGHIJKLMNOPQRSTUVWXYZÁÀẢÃẠĂẮẰẲẴẶÂẤẦẨẪẬÉÈẺẼẸÊẾỀỂỄỆÍÌỈĨỊÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢÚÙỦŨỤƯỨỪỬỮỰÝỲỶỸỴĐ")).append("<>$%").toString());
        c = new a("fcg14.png", new byte[]{8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 4, 4, 4, 4, 8, 6, 6, 6, 3, 7, 10, 10, 10, 10, 8, 8, 10, 10, 5, 8, 9, 8, 13, 11, 10, 10, 10, 10, 10, 9, 10, 10, 13, 11, 11, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 5, 5, 5, 5, 5, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10}, 20, 4, "0123456789.,:!?()-'/ABCDEFGHIJKLMNOPQRSTUVWXYZÁÀẢÃẠĂẮẰẲẴẶÂẤẦẨẪẬÉÈẺẼẸÊẾỀỂỄỆÍÌỈĨỊÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢÚÙỦŨỤƯỨỪỬỮỰÝỲỶỸỴĐ");
        e = new a("f21_10.png", new byte[]{6, 3, 5, 6, 6, 6, 5, 6, 6, 6, 3, 3, 3, 3, 6, 4, 4, 5, 3, 6, 8, 8, 8, 8, 8, 8, 8, 8, 3, 8, 8, 8, 10, 8, 8, 8, 8, 8, 7, 8, 8, 8, 10, 8, 8, 8, 6, 6, 6, 6, 6, 5, 6, 6, 3, 5, 6, 3, 8, 6, 6, 6, 6, 6, 6, 5, 6, 6, 8, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 3, 3, 3, 3, 3, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6}, 12, 4, "0123456789.,:!?()-'/ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzáàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵđĐ");
        d = new a("fsss.png", new byte[]{5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7, 5, 3, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7, 5, 5, 5, 3}, 8, 4, "0123456789+-%$:ABCDEFGHIJKLMNOPQRSTUVWXYZ.");
        S = a("/dialg.png");
        a("/dialg2.png");
        new ak(a("/load.png"), 24, 24);
        X = a("/lock.png");
        Y = a("/logo.png");
        Z = a("/down.png");
        af = new short[91];
        ag = new int[91];
        for (int i3 = 0; i3 <= 90; i3++) {
            af[i3] = ae[90 - i3];
            if (af[i3] == 0) {
                ag[i3] = Integer.MAX_VALUE;
            } else {
                ag[i3] = (ae[i3] << 10) / af[i3];
            }
        }
    }

    public static void b() {
        F = null;
        G = null;
        R = null;
        H = null;
    }

    public static void c() {
        if (aj.b >= 240) {
            F = a("/bgab.png");
        } else {
            F = a("/bga.png");
        }
        G = a("/xf.png");
        R = a("/map.png");
        a("/line1.png");
    }

    public static void d() {
        f.a();
        g.a();
        h.a();
        i.a();
        if (j != null) {
            j.a();
            j = null;
        }
        if (k != null) {
            k.a();
            k = null;
        }
        if (l != null) {
            l.a();
            l = null;
        }
        if (m != null) {
            m.a();
            m = null;
        }
        n = null;
        o = null;
        p = null;
        for (int i2 = 0; i2 < 10; i2++) {
            if (u[i2] != null) {
                u[i2].a();
                u[i2] = null;
            }
        }
        if (v != null) {
            v.a();
            v = null;
        }
        if (x != null) {
            x.a();
            x = null;
        }
        if (q != null) {
            q.a();
            q = null;
        }
        if (r != null) {
            r.a();
            r = null;
        }
        if (s != null) {
            s.a();
            s = null;
        }
        if (t != null) {
            t.a();
            t = null;
        }
        y.a();
        z.a();
        A.a();
        P = null;
        V = null;
        W = null;
        Q = null;
        J = null;
        if (B != null) {
            B.a();
            B = null;
        }
        if (C != null) {
            C.a();
            C = null;
        }
        if (E != null) {
            E.a();
            E = null;
        }
        if (D != null) {
            D.a();
            D = null;
        }
        if (ab != null) {
            ab.a();
            ab = null;
        }
        if (aa != null) {
            aa.a();
            aa = null;
        }
        I = null;
        L = null;
        M = null;
        N = null;
        O = null;
        T = null;
        U = null;
    }

    public static void e() {
        if (Q != null) {
            return;
        }
        f = new ak(a("/spark.png"), 11, 11);
        g = new ak(a("/spark2.png"), 7, 7);
        h = new ak(a("/explo.png"), 32, 32);
        i = new ak(a("/explo1.png"), 16, 16);
        l = new ak(a("/fs.png"), 19, 19);
        x = new ak(a("/vip.png"), 24, 24);
        y = new ak(a("/spot.png"), 32, 32);
        z = new ak(a("/spot1.png"), 24, 24);
        A = new ak(a("/items.png"), 16, 16);
        Q = a("/tree.png");
        K = new ad(a("/shot.png"), new int[][]{new int[]{0, 12, 3, 8, 1, 4}, new int[]{3, 12, 3, 8, 1, 4}, new int[]{6, 12, 8, 3, 4, 1}, new int[]{6, 15, 8, 3, 4, 1}, new int[]{0, 20, 5, 10, 2, 5}, new int[]{5, 20, 5, 10, 2, 5}, new int[]{10, 20, 10, 5, 5, 2}, new int[]{10, 25, 10, 5, 5, 2}, new int[]{0, 0, 5, 12, 2, 6}, new int[]{5, 0, 5, 12, 2, 6}, new int[]{10, 0, 12, 5, 6, 2}, new int[]{10, 5, 12, 5, 6, 2}, new int[]{0, 30, 7, 16, 3, 8}, new int[]{7, 30, 7, 16, 3, 8}, new int[]{14, 30, 16, 7, 8, 3}, new int[]{14, 37, 16, 7, 8, 3}, new int[]{0, 46, 7, 20, 3, 10}, new int[]{7, 46, 7, 20, 3, 10}, new int[]{0, 66, 20, 7, 10, 3}, new int[]{0, 73, 20, 7, 10, 3}, new int[]{24, 2, 6, 6, 3, 3}, new int[]{15, 11, 6, 6, 3, 3}, new int[]{24, 11, 6, 6, 3, 3}, new int[]{24, 46, 8, 8, 4, 4}, new int[]{24, 55, 8, 8, 4, 4}, new int[]{24, 64, 8, 8, 4, 4}, new int[]{20, 18, 6, 6, 3, 3}, new int[]{26, 18, 6, 6, 3, 3}, new int[]{20, 24, 6, 6, 3, 3}, new int[]{26, 24, 6, 6, 3, 3}});
    }

    public static final int a(int i2) {
        if (i2 >= 0 && i2 < 90) {
            return ae[i2];
        }
        if (i2 < 90 || i2 >= 180) {
            return (i2 < 180 || i2 >= 270) ? -ae[360 - i2] : -ae[i2 - 180];
        }
        return ae[180 - i2];
    }

    public static final int b(int i2) {
        if (i2 >= 0 && i2 < 90) {
            return af[i2];
        }
        if (i2 < 90 || i2 >= 180) {
            return (i2 < 180 || i2 >= 270) ? af[360 - i2] : -af[i2 - 180];
        }
        return -af[180 - i2];
    }

    public static final int a(int i2, int i3) {
        int i4;
        int i5;
        int i6;
        if (i2 != 0) {
            int iAbs = Math.abs((i3 << 10) / i2);
            int i7 = 0;
            while (true) {
                if (i7 > 90) {
                    i6 = 0;
                    break;
                }
                if (ag[i7] >= iAbs) {
                    i6 = i7;
                    break;
                }
                i7++;
            }
            i5 = i6;
            if (i3 >= 0 && i2 < 0) {
                i5 = 180 - i5;
            }
            if (i3 < 0 && i2 < 0) {
                i5 += 180;
            }
            if (i3 < 0 && i2 >= 0) {
                i4 = 360 - i5;
            }
            return i5;
        }
        i4 = i3 > 0 ? 90 : 270;
        i5 = i4;
        return i5;
    }

    public static final int c(int i2) {
        if (i2 >= 360) {
            i2 -= 360;
        }
        if (i2 < 0) {
            i2 += 360;
        }
        return i2;
    }

    public static final int b(int i2, int i3) {
        int i4 = i3 - i2;
        if (i4 < -180) {
            return i4 + 360;
        }
        return i4 > 180 ? i4 - 360 : i4;
    }

    public static final Image a(String str) {
        Image imageCreateImage = null;
        try {
            imageCreateImage = Image.createImage(new StringBuffer("/img").append(str).toString());
        } catch (Exception unused) {
        }
        if (imageCreateImage == null) {
            System.out.println(new StringBuffer("Load image '").append(str).append("': ERROR!!!").toString());
        }
        return imageCreateImage;
    }

    public static final byte[] b(String str) {
        try {
            InputStream resourceAsStream = "".getClass().getResourceAsStream(str);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int i2 = resourceAsStream.read();
                if (i2 == -1) {
                    resourceAsStream.close();
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    return byteArray;
                }
                byteArrayOutputStream.write(i2);
            }
        } catch (Exception unused) {
            return null;
        }
    }

    public static void a(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, Graphics graphics) {
        graphics.setColor(i10);
        graphics.fillTriangle(i2, i3, i4, i5, i6, i7);
        graphics.fillTriangle(i2, i3, i6, i7, i8, i9);
    }

    public static final int d(int i2) {
        if (aj >= 100) {
            aj = 0;
        }
        int[] iArr = ai;
        int i3 = aj;
        aj = i3 + 1;
        return iArr[i3] % i2;
    }

    public static final int e(int i2) {
        int iD = d(i2);
        return iD < 0 ? -iD : iD;
    }

    public static boolean f() {
        byte[] bArrC = c("XFData");
        System.out.println("load...");
        if (bArrC == null || bArrC.length != 100) {
            ab.z = "";
            ab.A = 0;
            ab.B = 100;
            ab.D = 0;
            byte[] bArr = new byte[5];
            bArr[3] = 60;
            ab.C = new byte[][]{bArr, new byte[]{-1, 0, 0, 60, 0}, new byte[]{-1, 0, 0, 60, 0}};
            for (int i2 = 0; i2 < ab.H.length; i2++) {
                ab.H[i2] = false;
            }
            ad = 0;
            ac = System.currentTimeMillis();
            System.out.println("load fail.");
            return false;
        }
        ab.z = new String(bArrC, 0, 20).trim();
        ab.A = a(20, bArrC);
        ab.B = a(24, bArrC);
        ab.D = bArrC[28];
        int i3 = 30;
        for (int i4 = 0; i4 < 3; i4++) {
            for (int i5 = 0; i5 < 5; i5++) {
                int i6 = i3;
                i3++;
                ab.C[i4][i5] = bArrC[i6];
            }
        }
        for (int i7 = 0; i7 < ab.H.length; i7++) {
            ab.H[i7] = bArrC[i7 + 50] != 0;
        }
        ad = a(96, bArrC);
        ac = System.currentTimeMillis();
        CMidlet.b = bArrC[90] != 0;
        ab.E = bArrC[91] != 0;
        al.a = bArrC[92];
        aj.o = bArrC[93];
        aj.p = bArrC[94];
        aj.q = bArrC[95];
        if (aj.o == 0) {
            aj.o = -5;
        }
        if (aj.p == 0) {
            aj.p = 48;
        }
        System.out.println("load done.");
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v31, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r0v32, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v38, types: [javax.microedition.rms.RecordStore] */
    public static void g() {
        byte[] bArr = new byte[100];
        if (ac == 0) {
            return;
        }
        byte[] bytes = ab.z.getBytes();
        for (int i2 = 0; i2 < 20 && i2 < bytes.length; i2++) {
            bArr[i2 + 0] = bytes[i2];
        }
        for (int length = bytes.length; length < 20; length++) {
            bArr[length + 0] = 32;
        }
        a(ab.A, 20, bArr);
        a(ab.B, 24, bArr);
        bArr[28] = (byte) ab.D;
        int i3 = 30;
        for (int i4 = 0; i4 < 3; i4++) {
            for (int i5 = 0; i5 < 5; i5++) {
                int i6 = i3;
                i3++;
                bArr[i6] = ab.C[i4][i5];
            }
        }
        for (int i7 = 0; i7 < ab.H.length; i7++) {
            bArr[i7 + 50] = (byte) (ab.H[i7] ? 1 : 0);
        }
        a((((int) (System.currentTimeMillis() - ac)) / 1000) + ad, 96, bArr);
        bArr[90] = (byte) (CMidlet.b ? 1 : 0);
        bArr[91] = (byte) (ab.E ? 1 : 0);
        bArr[92] = (byte) al.a;
        bArr[93] = (byte) aj.o;
        bArr[94] = (byte) aj.p;
        bArr[95] = (byte) aj.q;
        ?? r0 = bArr;
        try {
            RecordStore recordStoreOpenRecordStore = RecordStore.openRecordStore("XFData", true);
            if (recordStoreOpenRecordStore.getNumRecords() == 0) {
                recordStoreOpenRecordStore.addRecord((byte[]) r0, 0, r0.length);
            } else {
                recordStoreOpenRecordStore.setRecord(1, (byte[]) r0, 0, r0.length);
            }
            r0 = recordStoreOpenRecordStore;
            r0.closeRecordStore();
        } catch (Exception e2) {
            r0.printStackTrace();
        }
        System.out.println("save...");
    }

    private static void a(int i2, int i3, byte[] bArr) {
        bArr[i3] = (byte) (i2 >>> 24);
        bArr[i3 + 1] = (byte) (i2 >> 16);
        bArr[i3 + 2] = (byte) (i2 >> 8);
        bArr[i3 + 3] = (byte) i2;
    }

    private static int a(int i2, byte[] bArr) {
        return ((bArr[i2] & 255) << 24) | ((bArr[i2 + 1] & 255) << 16) | ((bArr[i2 + 2] & 255) << 8) | (bArr[i2 + 3] & 255);
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [byte[], java.lang.Throwable] */
    private static byte[] c(String str) {
        ?? record;
        try {
            RecordStore recordStoreOpenRecordStore = RecordStore.openRecordStore(str, false);
            record = recordStoreOpenRecordStore.getRecord(1);
            recordStoreOpenRecordStore.closeRecordStore();
            return record;
        } catch (Exception e2) {
            record.printStackTrace();
            return null;
        }
    }
}
