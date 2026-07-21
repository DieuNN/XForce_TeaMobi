package defpackage;

import javax.microedition.lcdui.Graphics;

/* JADX INFO: loaded from: XForce.jar:ab.class */
public final class ab extends q {
    private static boolean L;
    public static int i;
    public static int j;
    public static int k;
    public static int l;
    private static r M;
    public static d m;
    public static an[][] n;
    public static int p;
    public static int q;
    public static int r;
    public static int s;
    private static int O;
    private static an[] P;
    private static int R;
    private static int U;
    private static int V;
    private static int W;
    private static int X;
    private static int Y;
    private static int Z;
    private static short[] aa;
    private static short[] ab;
    private static short[] ac;
    private static short[] ad;
    private static short[] ae;
    private static short af;
    private static short ag;
    private static short[] ah;
    private static short[] ai;
    private static short[] aj;
    private static int ak;
    private static int al;
    private static boolean am;
    private static boolean an;
    private static boolean ao;
    public static int t;
    private static int ar;
    public static an u;
    public static int v;
    public static int w;
    public static int x;
    public static an y;
    private static boolean as;
    private static int at;
    private static int au;
    private static int av;
    private static int aw;
    private static int ay;
    private static int az;
    private static int aA;
    static int D;
    static int F;
    static int G;
    private static boolean aB;
    private static int aC;
    private static int aD;
    static boolean I;
    private static int aE;
    private static q aF;
    static boolean J;
    static int K;
    private static String aG;
    private static boolean aH;
    private String aI = "";
    public static an[] o = new an[200];
    private static z[] N = new z[100];
    private static ac[] Q = new ac[50];
    private static v[] S = new v[50];
    private static v[] T = new v[50];
    private static int[] ap = {3, -3, 3, -3};
    private static int[] aq = {3, -3, -3, 3};
    private static int ax = 25;
    static String z = "";
    static int A = 0;
    static int B = 0;
    static byte[][] C = new byte[3][5];
    static boolean E = true;
    static boolean[] H = new boolean[40];

    static {
        byte[] bArr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    }

    static void b(int i2) {
        az += i2;
        System.out.println(new StringBuffer("addXP ").append(i2).toString());
    }

    static void c(int i2) {
        aA += 100;
        System.out.println(new StringBuffer("addCash ").append(100).toString());
    }

    public ab() {
        i = aj.b;
        j = aj.c;
        M = new r();
        for (int i2 = 0; i2 < Q.length; i2++) {
            Q[i2] = new ac();
        }
        for (int i3 = 0; i3 < S.length; i3++) {
            S[i3] = new v();
            S[i3].n = true;
        }
        p = 0;
        ay = aj.c - 48;
        boolean z2 = aj.b == 240;
        aH = z2;
        if (z2) {
            j = ay;
        }
        aw = aj.b - 25;
        System.gc();
        System.out.println(new StringBuffer("free=").append(Runtime.getRuntime().freeMemory()).toString());
    }

    public final void d(int i2) {
        ar = i2;
        byte[] bArrB = c.b(new StringBuffer("/map/map").append(ar).toString());
        int[] iArrA = h.a(ar);
        this.g = al.N;
        this.h = al.Q[iArrA[10]];
        L = true;
        I = true;
        aF = null;
        b();
        aj.a.repaint();
        aj.a.serviceRepaints();
        p = 0;
        O = 0;
        q = 0;
        r = 0;
        s = 0;
        u = null;
        aA = 0;
        az = 0;
        for (int i3 = 0; i3 < Q.length; i3++) {
            Q[i3].n = true;
        }
        for (int i4 = 0; i4 < S.length; i4++) {
            S[i4].n = true;
        }
        for (int length = T.length - 1; length >= 0; length--) {
            T[length] = null;
        }
        if (D == 0) {
            m = new d((byte) -1);
        } else if (D == 1) {
            m = new d((byte) -2);
        } else if (D == 2) {
            m = new d((byte) -3);
        }
        a((an) m);
        r.a(iArrA[0]);
        int i5 = iArrA[1];
        int i6 = iArrA[2];
        m.a(iArrA[3] * r.a, iArrA[4] * r.a);
        short[][] sArr = new short[i6][i5];
        an = false;
        ao = false;
        switch (r.b) {
            case 1:
                boolean z2 = c.d(10) == 0;
                ao = z2;
                am = !z2;
                break;
            case 2:
                am = true;
                break;
            case 3:
                an = c.d(3) == 0;
                break;
        }
        if (am) {
            c.P = c.a("/cloud1.png");
        }
        System.out.println(new StringBuffer(String.valueOf(i5)).append(" ").append(i6).toString());
        n = new an[i6][i5];
        for (int i7 = 0; i7 < i6; i7++) {
            for (int i8 = 0; i8 < i5; i8++) {
                sArr[i7][i8] = bArrB[(i7 * i5) + i8];
                switch (sArr[i7][i8]) {
                    case 39:
                        break;
                    case 41:
                    case 43:
                    case 44:
                        an[] anVarArr = o;
                        int i9 = p;
                        p = i9 + 1;
                        an anVar = new an(i8 * r.a, i7 * r.a, bArrB[(i7 * i5) + i8] == true ? (byte) 1 : (byte) 0);
                        anVarArr[i9] = anVar;
                        n[i7][i8] = anVar;
                        break;
                    case 42:
                        an[] anVarArr2 = o;
                        int i10 = p;
                        p = i10 + 1;
                        n nVar = new n(i8 * r.a, i7 * r.a);
                        anVarArr2[i10] = nVar;
                        n[i7][i8] = nVar;
                        break;
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                        an[] anVarArr3 = o;
                        int i11 = p;
                        p = i11 + 1;
                        m mVar = new m(i8 * r.a, i7 * r.a, bArrB[(i7 * i5) + i8] == true ? (byte) 1 : (byte) 0);
                        anVarArr3[i11] = mVar;
                        n[i7][i8] = mVar;
                        o[p - 1].s = m;
                        break;
                    case 80:
                        sArr[i7][i8] = 60;
                        n[i7][i8] = new an(i8 * r.a, i7 * r.a, (byte) 62);
                        break;
                    case 81:
                        sArr[i7][i8] = 39;
                        an[] anVarArr4 = o;
                        int i12 = p;
                        p = i12 + 1;
                        k kVar = new k(i8 * r.a, i7 * r.a);
                        anVarArr4[i12] = kVar;
                        n[i7][i8] = kVar;
                        break;
                    case 82:
                        sArr[i7][i8] = sArr[i7][i8 - 1];
                        an[] anVarArr5 = o;
                        int i13 = p;
                        p = i13 + 1;
                        an anVar2 = new an(i8 * r.a, i7 * r.a, (byte) 120);
                        anVarArr5[i13] = anVar2;
                        n[i7][i8] = anVar2;
                        break;
                    case 83:
                        sArr[i7][i8] = sArr[i7][i8 - 1];
                        an[] anVarArr6 = o;
                        int i14 = p;
                        p = i14 + 1;
                        an anVar3 = new an(i8 * r.a, i7 * r.a, (byte) 121);
                        anVarArr6[i14] = anVar3;
                        n[i7][i8] = anVar3;
                        break;
                    case 84:
                        sArr[i7][i8] = sArr[i7][i8 - 1];
                        an[] anVarArr7 = o;
                        int i15 = p;
                        p = i15 + 1;
                        an anVar4 = new an(i8 * r.a, i7 * r.a, (byte) 122);
                        anVarArr7[i15] = anVar4;
                        n[i7][i8] = anVar4;
                        break;
                    case 85:
                        sArr[i7][i8] = 60;
                        n[i7][i8] = new an(i8 * r.a, i7 * r.a, (byte) 63);
                        break;
                    default:
                        if (sArr[i7][i8] >= 86 && sArr[i7][i8] < 96) {
                            n[i7][i8] = a((an) new o(i8 * r.a, i7 * r.a, (byte) (1 + (sArr[i7][i8] - 86))));
                            sArr[i7][i8] = 79;
                            r += 3;
                        }
                        break;
                }
            }
        }
        System.out.println(new StringBuffer("totalEnemy=").append(r).toString());
        System.out.println("load4");
        r.a(sArr);
        for (int i16 = 0; i16 < p; i16++) {
            if (o[i16].v == 120 || o[i16].v == 121 || o[i16].v == 122) {
                r.d[o[i16].h / r.a][o[i16].g / r.a] = 1;
            }
        }
        int i17 = 6;
        while (i17 < iArrA.length) {
            switch (iArrA[i17]) {
                case 66:
                    int i18 = i17 + 1;
                    i17 = i18 + 1;
                    int i19 = iArrA[i18];
                    System.out.println(new StringBuffer("Read bridge position: ").append(i19).toString());
                    for (int i20 = 0; i20 < i19; i20++) {
                        int i21 = i17;
                        int i22 = i17 + 1;
                        if (iArrA[i21] == 1) {
                            int i23 = i22 + 1;
                            int i24 = iArrA[i22] * r.a;
                            i17 = i23 + 1;
                            a(new an(i24, iArrA[i23] * r.a, (byte) 16));
                        } else {
                            int i25 = i22 + 1;
                            int i26 = iArrA[i22] * r.a;
                            i17 = i25 + 1;
                            a(new an(i26, iArrA[i25] * r.a, (byte) 17));
                        }
                    }
                    break;
                case 76:
                    int i27 = i17 + 1;
                    i17 = i27 + 1;
                    P = new an[iArrA[i27]];
                    System.out.println(new StringBuffer("Read poles: ").append(P.length).toString());
                    for (int i28 = 0; i28 < P.length; i28++) {
                        int i29 = i17;
                        int i30 = i17 + 1;
                        int i31 = iArrA[i29];
                        i17 = i30 + 1;
                        int i32 = iArrA[i30];
                        if (n[i32][i31] != null && (n[i32][i31].v == 43 || n[i32][i31].v == 44)) {
                            P[i28] = n[i32][i31];
                        }
                    }
                    break;
                default:
                    i17++;
                    break;
            }
        }
        e();
        System.out.println(new StringBuffer("entity: ").append(p).toString());
        y = m;
        c(m.g, m.h);
        k = U;
        l = V;
        switch (h.a(ar)[10]) {
            case 0:
                f.a = 1;
                System.out.println("kill all");
                f.g = 0;
                break;
            case 1:
                f.b = 4500;
                y yVar = new y();
                u = yVar;
                a((an) yVar);
                a(1272, 960, (byte) 6);
                x.a(1);
                f.a = 10;
                f.g = 50000;
                break;
            case 2:
                ae aeVar = new ae(1944, 144);
                u = aeVar;
                aeVar.w = (byte) -1;
                a(u);
                f.a = 11;
                f.g = 20000;
                break;
            case 3:
                ae aeVar2 = new ae(624, 336);
                u = aeVar2;
                aeVar2.l = 0;
                u.w = (byte) -1;
                a(u);
                f.a = 14;
                f.g = 30000;
                break;
            case 4:
                f.a = 12;
                s sVar = new s(100, 2160);
                u = sVar;
                sVar.w = (byte) -1;
                a(u);
                y = null;
                f.f = m.h - 100;
                f.g = 100000;
                break;
            case 5:
                f.a = 16;
                f.g = 5000;
                break;
            case 6:
                x.a(5);
                f.a = 17;
                f.g = 15000;
                break;
            case 7:
                f(360, 96);
                u = a((an) new e(192, 2376, 360, 96));
                x.a(6);
                f.a = 15;
                f.g = 10000;
                break;
            case 8:
                f(360, 120);
                u = a((an) new e(144, 2856, 360, 120));
                x.a(6);
                f.a = 15;
                f.g = 10000;
                break;
            case 9:
                f(456, 2352);
                u = a((an) new e(144, 48, 456, 2352));
                x.a(6);
                f.a = 15;
                f.g = 10000;
                break;
            default:
                f.a = 0;
                break;
        }
        c.e();
        p.a(new StringBuffer("/xf").append(iArrA[5]).append(".mid").toString());
        L = false;
    }

    private static void e() {
        if (ao) {
            int i2 = (i * j) / 758;
            aa = new short[i2];
            ab = new short[i2];
            ac = new short[i2];
            ad = new short[i2];
            ae = new short[i2];
            for (int i3 = 0; i3 < aa.length; i3++) {
                aa[i3] = (short) Math.abs(c.d(i));
                ab[i3] = (short) Math.abs(c.d(j));
                ac[i3] = (short) Math.abs(c.d(80));
            }
        }
        if (an) {
            int i4 = (i * j) / 758;
            aa = new short[i4];
            ab = new short[i4];
            ac = new short[i4];
            for (int i5 = 0; i5 < aa.length; i5++) {
                aa[i5] = (short) Math.abs(c.d(i));
                ab[i5] = (short) Math.abs(c.d(j));
                ac[i5] = (short) Math.abs(c.d(80));
            }
        }
        if (am) {
            int i6 = (r.e * r.f) / 36;
            ak = i6;
            ah = new short[i6];
            ai = new short[ak];
            aj = new short[ak];
            for (int i7 = 0; i7 < ak; i7 += 3) {
                ah[i7] = (short) Math.abs(c.d(r.g));
                ai[i7] = (short) Math.abs(c.d(r.h));
                aj[i7] = (short) (80 + c.d(20));
                for (int i8 = 1; i8 < 3 && i7 + i8 < ak; i8++) {
                    ah[i7 + i8] = (short) (c.d(40) + ah[i7]);
                    ai[i7 + i8] = (short) (c.d(20) + ai[i7]);
                    aj[i7 + i8] = (short) (80 + c.d(20));
                }
            }
        }
    }

    public static an a(an anVar) {
        if (p >= o.length) {
            System.out.println("full entity");
            return null;
        }
        an[] anVarArr = o;
        int i2 = p;
        p = i2 + 1;
        anVarArr[i2] = anVar;
        return anVar;
    }

    public static void a(byte b, int i2, int i3, int i4, int i5, int i6) {
        int i7 = 0;
        int i8 = 20;
        switch (b) {
            case 0:
            case 1:
            case 6:
                i7 = 40;
                i8 = 50;
                break;
        }
        for (int i9 = i7; i9 < i8; i9++) {
            if (Q[i9].n) {
                Q[i9].a(b, i2, i3, i4, i5, i6);
                return;
            }
        }
    }

    public static void a(int i2, int i3) {
        if (a(i2, i3, 0, 0)) {
            a((byte) 0, i2 + c.d(6), i3 + c.d(6), 0, 0, 0);
            a((byte) 0, i2 + c.d(12), i3 + c.d(12), 0, 0, 6 + c.d(3));
            a((byte) 0, i2 + c.d(12), i3 + c.d(12), 0, 0, 15 + c.d(3));
        }
    }

    public static void a(String str, int i2, int i3) {
        for (int length = Q.length - 1; length >= 0; length--) {
            if (Q[length].n) {
                switch (R) {
                    case 0:
                        Q[length].a(str, i2, i3, -2048, -2048);
                        break;
                    case 1:
                        Q[length].a(str, i2, i3, 2048, -2048);
                        break;
                    case 2:
                        Q[length].a(str, i2, i3, 2048, 2048);
                        break;
                    case 3:
                        Q[length].a(str, i2, i3, -2048, 2048);
                        break;
                }
                R = (R + 1) % 4;
                return;
            }
        }
    }

    public static v a(byte b, int i2, int i3, int i4, an anVar) {
        for (int length = S.length - 1; length >= 0; length--) {
            if (S[length].n) {
                if (b == 10 || b == 11) {
                    p.a(2);
                }
                S[length].a(b, i2, i3, i4, anVar);
                return S[length];
            }
        }
        return null;
    }

    public static v b(int i2, int i3) {
        for (int length = T.length - 1; length >= 0; length--) {
            if (T[length] == null) {
                T[length] = new v((byte) 18, i2, i3);
                return T[length];
            }
        }
        return null;
    }

    public static void a(int i2, int i3, byte b) {
        if (O < N.length) {
            z[] zVarArr = N;
            int i4 = O;
            O = i4 + 1;
            zVarArr[i4] = new z(i2, i3, b);
        }
    }

    @Override // defpackage.q
    public final void a(Graphics graphics) {
        graphics.setColor(8947848);
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        graphics.setClip(0, 0, aj.b, aj.c);
        if (I) {
            graphics.setColor(0);
            graphics.fillRect(0, 0, aj.b, aj.c);
            if (c.F != null) {
                graphics.drawImage(c.F, 0, 0, 0);
            }
            if (this.g != null) {
                c.c.a(this.g, aj.b >> 1, (aj.c >> 1) - 50, 2, graphics);
            }
            if (this.h != null) {
                (aj.b >= 240 ? c.b : c.a).a(this.h, (aj.b - (aj.b >= 240 ? 160 : 120)) >> 1, (aj.c >> 1) - 20, 0, graphics);
            }
            if (L) {
                c.d.a(al.K, aj.b >> 1, (aj.c >> 1) + 50, 2, graphics);
            } else if (aE < 4) {
                c.d.a(al.I, aj.b >> 1, (aj.c >> 1) + 50, 2, graphics);
            }
            int i2 = aE + 1;
            aE = i2;
            if (i2 >= 8) {
                aE = 0;
                return;
            }
            return;
        }
        if (aB) {
            graphics.setColor(0);
            graphics.drawLine(0, aC, aj.b, aC);
            graphics.drawLine(0, aC + 2, aj.b, aC + 2);
            graphics.drawLine(0, aC + 4, aj.b, aC + 4);
            graphics.drawLine(0, aC + 6, aj.b, aC + 6);
            graphics.drawLine(0, aC + 8, aj.b, aC + 8);
            graphics.drawLine(0, aD - aC, aj.b, aD - aC);
            graphics.drawLine(0, (aD - aC) - 2, aj.b, (aD - aC) - 2);
            graphics.drawLine(0, (aD - aC) - 4, aj.b, (aD - aC) - 4);
            graphics.drawLine(0, (aD - aC) - 6, aj.b, (aD - aC) - 6);
            graphics.drawLine(0, (aD - aC) - 8, aj.b, (aD - aC) - 8);
            int i3 = aC + 10;
            aC = i3;
            if (i3 >= aD) {
                aB = false;
                return;
            }
            return;
        }
        graphics.setClip(0, 0, i, j);
        if (t > 0) {
            graphics.translate((-k) + ap[t % ap.length], (-l) + aq[t % aq.length]);
            t--;
        } else {
            graphics.translate(-k, -l);
        }
        r.a(graphics);
        for (int length = Q.length - 1; length >= 0; length--) {
            if (!Q[length].n && Q[length].a == 0) {
                Q[length].a(graphics);
            }
        }
        for (int length2 = T.length - 1; length2 >= 0; length2--) {
            if (T[length2] != null) {
                T[length2].a(graphics);
            }
        }
        for (int i4 = 0; i4 < O; i4++) {
            if (a(N[i4])) {
                N[i4].a(graphics);
            }
        }
        for (int i5 = 0; i5 < p; i5++) {
            if (o[i5].t == 0 && a((ai) o[i5])) {
                o[i5].a(graphics);
            }
        }
        for (int length3 = S.length - 1; length3 >= 0; length3--) {
            if (!S[length3].n) {
                S[length3].a(graphics);
            }
        }
        for (int i6 = 0; i6 < p; i6++) {
            if (o[i6].t == 1 && a((ai) o[i6])) {
                o[i6].a(graphics);
            }
        }
        r.b(graphics);
        if (P != null) {
            int i7 = 0;
            int i8 = 0;
            int i9 = 0;
            int i10 = 0;
            graphics.setColor(0);
            for (int i11 = 0; i11 < P.length; i11++) {
                if (P[i11] != null) {
                    if (P[i11].o) {
                        P[i11] = null;
                    } else {
                        P[i11].A = (((P[i11].g - ai.e) * P[i11].i) / ai.d) + P[i11].g;
                        P[i11].B = (((P[i11].h - ai.f) * P[i11].i) / ai.d) + P[i11].h;
                        int i12 = i7;
                        int i13 = i8;
                        int i14 = i9;
                        int i15 = i10;
                        if (P[i11].v == 43) {
                            int i16 = P[i11].A;
                            i7 = i16;
                            i9 = i16;
                            i8 = P[i11].B - 9;
                            i10 = P[i11].B + 9;
                        } else {
                            i7 = P[i11].A - 9;
                            i9 = P[i11].A + 9;
                            int i17 = P[i11].B;
                            i8 = i17;
                            i10 = i17;
                        }
                        if (i11 > 0 && P[i11 - 1] != null) {
                            graphics.drawLine(i7, i8, i12, i13);
                            graphics.drawLine(i9, i10, i14, i15);
                            graphics.drawLine(P[i11].A, P[i11].B, P[i11 - 1].A, P[i11 - 1].B);
                        }
                    }
                }
            }
        }
        for (int i18 = 0; i18 < p; i18++) {
            if (o[i18].t == 2 && a((ai) o[i18])) {
                o[i18].a(graphics);
            }
        }
        for (int length4 = Q.length - 1; length4 >= 0; length4--) {
            if (!Q[length4].n && Q[length4].a == 1) {
                Q[length4].a(graphics);
            }
        }
        if (as) {
            graphics.setColor(16711680);
            int i19 = av - 2;
            av = i19;
            if (i19 < 2) {
                av = 24;
            }
            graphics.drawArc(at - av, au - av, av << 1, av << 1, 0, 360);
        }
        if (ao) {
            graphics.setColor(15658751);
            for (int i20 = 0; i20 < aa.length; i20++) {
                int i21 = (((aa[i20] - ai.e) * ac[i20]) / ai.d) + aa[i20];
                int i22 = (((ab[i20] - ai.f) * ac[i20]) / ai.d) + ab[i20];
                if (ac[i20] < 80) {
                    graphics.drawLine(ad[i20], ae[i20], i21, i22);
                }
                short[] sArr = ac;
                int i23 = i20;
                sArr[i23] = (short) (sArr[i23] - 7);
                if (ac[i20] < 0) {
                    ac[i20] = 80;
                    aa[i20] = (short) (k + Math.abs(c.d(i)));
                    ab[i20] = (short) (l + Math.abs(c.d(j)));
                }
                ad[i20] = (short) i21;
                ae[i20] = (short) i22;
            }
        }
        if (an) {
            graphics.setColor(16777215);
            for (int length5 = aa.length >> 2; length5 >= 0; length5--) {
                graphics.fillRect((((aa[length5] - ai.e) * ac[length5]) / ai.d) + aa[length5], (((ab[length5] - ai.f) * ac[length5]) / ai.d) + ab[length5], (ac[length5] >> 4) + 2, (ac[length5] >> 4) + 2);
                short[] sArr2 = ac;
                int i24 = length5;
                sArr2[i24] = (short) (sArr2[i24] - 2);
                short[] sArr3 = aa;
                int i25 = length5;
                sArr3[i25] = (short) (sArr3[i25] + af);
                short[] sArr4 = ab;
                int i26 = length5;
                sArr4[i26] = (short) (sArr4[i26] + ag);
                if (ac[length5] < 0) {
                    ac[length5] = 80;
                    aa[length5] = (short) (k + Math.abs(c.d(i)));
                    ab[length5] = (short) (l + Math.abs(c.d(j)));
                    if (length5 == 0 && c.d(10) == 0) {
                        af = (short) c.d(2);
                        ag = (short) c.d(2);
                    }
                }
            }
        }
        if (am) {
            for (int i27 = 0; i27 < ak; i27++) {
                if (a(ah[i27], ai[i27], 80, 80)) {
                    graphics.drawImage(c.P, (((ah[i27] - ai.e) * aj[i27]) / ai.d) + ah[i27], (((ai[i27] - ai.f) * aj[i27]) / ai.d) + ai[i27], 0);
                }
                short[] sArr5 = ah;
                int i28 = i27;
                sArr5[i28] = (short) (sArr5[i28] - 1);
                if (ah[i27] < -80) {
                    ah[i27] = (short) r.g;
                }
            }
            int i29 = al + 1;
            al = i29;
            if (i29 == 4) {
                al = 0;
            }
        }
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        if (f.a == 15) {
            graphics.setColor(0);
            graphics.fillRect(4, 4, 34, 4);
            graphics.setColor(65280);
            graphics.fillRect(5, 5, (u.x << 5) / u.y, 2);
            if (f.e) {
                c.d.a("LOSS", 4, 10, 0, graphics);
            }
        }
        if (f.b > 0) {
            int i30 = f.b / 15;
            c.d.a(new StringBuffer(String.valueOf(i30 / 60)).append(":").append(i30 % 60).toString(), 4, 4, 0, graphics);
        }
        if (aH) {
            graphics.setClip(0, 0, aj.b, aj.c);
            d(graphics);
        } else {
            e(graphics);
        }
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
    }

    static boolean a(int i2, int i3, int i4, int i5, int i6, int i7) {
        return i2 >= i4 && i3 >= i5 && i2 < i4 + i6 && i3 < i5 + i7;
    }

    @Override // defpackage.q
    public final void a() {
        if (I) {
            if (q.d == 3) {
                a(0);
                q.d = 0;
                return;
            }
            return;
        }
        if (K > 0) {
            if (K > 2) {
                K--;
            }
            if (K == 2) {
                aB = true;
                int i2 = aj.c;
                aD = i2;
                if (i2 % 2 == 0) {
                    aD--;
                }
                aC = 0;
                K--;
            }
            if (K == 1 && !aB) {
                K--;
                d();
                return;
            }
            System.out.println(new StringBuffer("game over").append(K).toString());
        }
        if (q.a[6]) {
            aj.g.b();
            q.c();
            return;
        }
        if (q.a[7]) {
            switch (x.a) {
                case 0:
                    break;
                default:
                    w.a = false;
                    y = m;
                    v = 0;
                    x.a = 0;
                    break;
            }
            q.c();
            return;
        }
        if (aB) {
            return;
        }
        if (v == 1) {
            if (q.a[0]) {
                x -= 20;
            }
            if (q.a[1]) {
                x += 20;
            }
            if (q.a[2]) {
                w -= 20;
            }
            if (q.a[3]) {
                w += 20;
            }
        }
        if (q.d == 1) {
            q.c();
            if (m != null) {
                if (!aH || q.c < ay) {
                    int i3 = ((l + q.c) - m.h) - 12;
                    int i4 = ((k + q.b) - m.g) - 12;
                    if (Math.abs(i3) > Math.abs(i4)) {
                        if (i3 < 0) {
                            q.a[0] = true;
                        } else {
                            q.a[1] = true;
                        }
                    } else if (i4 < 0) {
                        q.a[2] = true;
                    } else {
                        q.a[3] = true;
                    }
                } else {
                    if (a(q.b, q.c - ay, 37, 6, 28, 16)) {
                        m.d();
                    }
                    if (a(q.b, q.c - ay, 70, 6, 28, 16)) {
                        m.e();
                    }
                    if (a(q.b, q.c - ay, 103, 6, 28, 16)) {
                        q.a[4] = true;
                    }
                }
            }
            q.d = 2;
        }
        if (q.d == 3) {
            q.d = 0;
            q.c();
        }
        ai.e = k + (aj.b / 2);
        ai.f = l + (aj.c / 2);
        if (x.a == 0) {
            for (int length = S.length - 1; length >= 0; length--) {
                if (!S[length].n) {
                    S[length].a();
                }
            }
            for (int length2 = T.length - 1; length2 >= 0; length2--) {
                if (T[length2] != null) {
                    T[length2].a();
                    if (T[length2].o) {
                        T[length2] = null;
                    }
                }
            }
            for (int i5 = p - 1; i5 >= 0; i5--) {
                if (o[i5].o) {
                    an[] anVarArr = o;
                    int i6 = p - 1;
                    p = i6;
                    o[i5] = anVarArr[i6];
                } else {
                    o[i5].a();
                }
            }
            for (int i7 = O - 1; i7 >= 0; i7--) {
                if (m.a((ai) N[i7]) && m.a(N[i7])) {
                    N[i7].o = true;
                }
                if (N[i7].o) {
                    z[] zVarArr = N;
                    int i8 = O - 1;
                    O = i8;
                    N[i7] = zVarArr[i8];
                }
            }
        }
        if (f.a != 0) {
            switch (f.a) {
                case 1:
                    if (s == r) {
                        f.a = 0;
                        a(true);
                    }
                    break;
                case 10:
                    if (f.c && !f.d && m.c(132, 132)) {
                        a((byte) 17, 132, 132, 0, null);
                        f.d = true;
                        f(12, 12);
                    }
                    if (f.d && f.b > 2 && m.c(12, 12)) {
                        f.b = 2;
                    }
                    if (f.b > 1) {
                        if (x.a == 0) {
                            f.b--;
                        }
                    } else if (f.b == 1) {
                        g gVar = new g(72, 1500, (byte) 12);
                        gVar.q = 270;
                        gVar.m = -6144;
                        a((an) gVar);
                        g gVar2 = new g(144, 1600, (byte) 12);
                        gVar2.q = 270;
                        gVar2.m = -6144;
                        a((an) gVar2);
                        f.b--;
                    } else {
                        y = u;
                        if (u.m > -2048) {
                            u.m -= 32;
                        } else {
                            m.o = true;
                            f.a = 102;
                        }
                    }
                    break;
                case 12:
                    f.f -= 2;
                    if (l <= 100) {
                        f.f += 1920;
                        l += 1920;
                        m.h += 1920;
                        u.h += 1920;
                        System.out.println("Loop map");
                    }
                    c(m.g, f.f);
                    m.h -= 2;
                    break;
                case 16:
                    if (m.h < 480 && u == null) {
                        o oVar = new o(288, 168, (byte) 111);
                        u = oVar;
                        a((an) oVar);
                        x.a(4);
                    }
                    break;
            }
        }
        x.a();
        if (v != 0) {
            c(w, x);
        } else if (y != null) {
            y.c();
        }
        if (k == U && l == V) {
            return;
        }
        W = (U - k) << 1;
        X = (V - l) << 1;
        Y += W;
        k += Y >> 4;
        Y &= 15;
        Z += X;
        l += Z >> 4;
        Z &= 15;
    }

    public static void c(int i2, int i3) {
        U = i2 - (i >> 1);
        V = i3 - (j >> 1);
        if (U < 0) {
            U = 0;
        }
        if (U > r.g - i) {
            U = r.g - i;
        }
        if (V < 0) {
            V = 0;
        }
        if (V > r.h - j) {
            V = r.h - j;
        }
    }

    @Override // defpackage.q
    public final void a(int i2) {
        if (I) {
            if (L) {
                return;
            }
            if (aF != null) {
                aF.b();
            }
            if (aG != null) {
                w.a(aG, 3);
                aG = null;
            }
            I = false;
            q.c();
            return;
        }
        if (i2 == 48 || this.aI.length() >= 10) {
            this.aI = "";
        } else if (i2 > 48) {
            this.aI = new StringBuffer(String.valueOf(this.aI)).append(i2 - 48).toString();
        }
        if (this.aI.endsWith("11373")) {
            m.x = m.y;
        }
        if (m == null || K != 0) {
            return;
        }
        if (i2 == aj.p) {
            m.d();
        }
        if (i2 == aj.q) {
            m.e();
        }
    }

    private static void d(Graphics graphics) {
        if (c.I == null) {
            c.I = c.a("/panel.png");
        }
        graphics.translate(-graphics.getTranslateX(), ay - graphics.getTranslateY());
        graphics.drawImage(c.I, 0, 0, 0);
        if (m != null) {
            int i2 = (m.x * 56) / m.y;
            graphics.setColor(0);
            graphics.fillRect(i2 + 40, 30, 56 - i2, 6);
            graphics.setColor(16711680);
            if (m.c > 0) {
                graphics.fillRect(56, 9, 5, 2);
            }
            if (m.b > 0) {
                graphics.fillRect(89, 9, 5, 2);
            }
            if (m.a > 0) {
                graphics.fillRect(122, 9, 5, 2);
            }
            c.d.a(new StringBuffer(String.valueOf(F)).toString(), 43, 6, 0, graphics);
            c.d.a(new StringBuffer(String.valueOf(G)).toString(), 76, 6, 0, graphics);
        }
        for (int i3 = 0; i3 < p; i3++) {
            if (!o[i3].n && o[i3].w != 0) {
                if (o[i3].w == -1) {
                    graphics.setColor(65280);
                } else {
                    graphics.setColor(14869218);
                }
                int i4 = o[i3].g - m.g;
                int i5 = o[i3].h - m.h;
                if (Math.abs(i4) < 576 && Math.abs(i5) < 576) {
                    graphics.fillRect(164 + (i4 >> 5), 25 + (i5 >> 5), 2, 2);
                }
            }
        }
        if (as) {
            int i6 = at - m.g;
            int i7 = au - m.h;
            if (i6 < -576) {
                i6 = -576;
            }
            if (i6 > 576) {
                i6 = 576;
            }
            if (i7 < -576) {
                i7 = -576;
            }
            if (i7 > 576) {
                i7 = 576;
            }
            graphics.setColor(16711680);
            graphics.drawRect(164 + (i6 >> 5), 25 + (i7 >> 5), 2, 2);
        }
        c.d.a(new StringBuffer(String.valueOf((s * 100) / r)).append("%").toString(), 140, 29, 1, graphics);
    }

    private static void e(Graphics graphics) {
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        graphics.setClip(0, 0, aj.b, aj.c);
        graphics.translate(0, ay);
        if (m == null) {
            return;
        }
        int i2 = (m.x * 40) / m.y;
        graphics.setColor(0);
        graphics.fillRect(2, 38, 46, 8);
        graphics.setColor(16711680);
        graphics.fillRect(4, 40, i2, 4);
        c.A.a(4, 3, 24, 0, graphics);
        c.d.a(new StringBuffer(String.valueOf(F)).toString(), 3, 24, 0, graphics);
        c.A.a(5, 30, 24, 0, graphics);
        c.d.a(new StringBuffer(String.valueOf(G)).toString(), 30, 24, 0, graphics);
        graphics.setColor(16777215);
        graphics.drawLine(aw - 2, ax, aw + 2, ax);
        graphics.drawLine(aw, ax - 2, aw, ax + 2);
        graphics.drawRect(aw - 22, ax - 22, 44, 44);
        for (int i3 = 0; i3 < p; i3++) {
            if (!o[i3].n && o[i3].w != 0) {
                if (o[i3].w == -1) {
                    graphics.setColor(65280);
                } else {
                    graphics.setColor(14869218);
                }
                int i4 = o[i3].g - m.g;
                int i5 = o[i3].h - m.h;
                if (Math.abs(i4) < 576 && Math.abs(i5) < 576) {
                    graphics.fillRect(aw + (i4 >> 5), 24 + (i5 >> 5), 2, 2);
                }
            }
        }
        if (as) {
            int i6 = at - m.g;
            int i7 = au - m.h;
            if (i6 < -576) {
                i6 = -576;
            }
            if (i6 > 576) {
                i6 = 576;
            }
            if (i7 < -576) {
                i7 = -576;
            }
            if (i7 > 576) {
                i7 = 576;
            }
            graphics.setColor(16711680);
            graphics.drawRect((aw + (i6 >> 5)) - 1, (24 + (i7 >> 5)) - 1, 2, 2);
        }
        c.d.a(new StringBuffer(String.valueOf((s * 100) / r)).append("%").toString(), aw + 23, ax + 12, 1, graphics);
    }

    public static boolean d(int i2, int i3) {
        return i2 >= 0 && i2 < r.f && i3 >= 0 && i3 < r.e && n[i2][i3] != null && n[i2][i3] != m && n[i2][i3].w == -1;
    }

    public static an e(int i2, int i3) {
        if (i2 < 0 || i2 >= r.f || i3 < 0 || i3 >= r.e) {
            return null;
        }
        return n[i2][i3];
    }

    public static void a(int i2, int i3, an anVar) {
        if (i2 < 0 || i2 >= r.f || i3 < 0 || i3 >= r.e || n[i2][i3] != null) {
            return;
        }
        n[i2][i3] = anVar;
        r.d[i2][i3] = 1;
    }

    public static void b(int i2, int i3, an anVar) {
        if (i2 < 0 || i2 >= r.f || i3 < 0 || i3 >= r.e || n[i2][i3] != anVar) {
            return;
        }
        n[i2][i3] = null;
        r.b(i2, i3);
    }

    public static boolean a(ai aiVar) {
        return aiVar.g + aiVar.j >= k && aiVar.h + aiVar.k >= l && aiVar.g <= k + i && aiVar.h <= l + j;
    }

    private static boolean a(int i2, int i3, int i4, int i5) {
        return i2 + i4 >= k && i3 + i5 >= l && i2 <= k + i && i3 <= l + j;
    }

    public static void f(int i2, int i3) {
        at = i2;
        au = i3;
        as = true;
    }

    private static void f() {
        for (int i2 = 0; i2 < p; i2++) {
            o[i2] = null;
        }
        p = 0;
        for (int i3 = 0; i3 < O; i3++) {
            N[i3] = null;
        }
        for (int i4 = 0; i4 < Q.length; i4++) {
            Q[i4].n = true;
        }
        for (int i5 = 0; i5 < S.length; i5++) {
            S[i5].n = true;
        }
        for (int i6 = 0; i6 < T.length; i6++) {
            T[i6] = null;
        }
        System.out.println("game scr toi day");
        O = 0;
        r = 0;
        s = 0;
        P = null;
        n = null;
        aj = null;
        ai = null;
        ah = null;
        ae = null;
        ad = null;
        ac = null;
        ab = null;
        aa = null;
        System.out.println("game scr toi day");
        if (m != null) {
            C[D][3] = (byte) ((m.x * 100) / m.y);
            C[D][4] = (byte) ((G * 10) + F);
        }
        System.out.println("game scr toi day");
        r.a();
        System.out.println("game scr toi day");
        c.d();
        System.out.println("game scr toi day");
    }

    @Override // defpackage.q
    public final void b() {
        super.b();
    }

    public static void a(boolean z2) {
        System.out.println("gameOver be called");
        J = z2;
        K = 50;
    }

    public final void d() {
        if (!J) {
            this.g = al.M;
            aF = aj.f;
            L = true;
            I = true;
            b();
            aj.a.repaint();
            aj.a.serviceRepaints();
            p.d();
            f();
            c.c();
            c.g();
            p.a("/menu.mid");
            L = false;
            return;
        }
        H[ar] = true;
        int i2 = (s * 100) + f.g;
        this.g = al.L;
        this.h = new StringBuffer("- ").append(al.O).append(s).append("\n- ").append(al.D).append("+").append(az).append("\n- ").append(al.B).append("+").append(aA).append("$").append("\n- ").append(al.C).append(i2).append("$").toString();
        aA += i2;
        if (A < 50000 && A + az >= 50000) {
            aG = al.ab;
        }
        if (A < 200000 && A + az >= 200000) {
            aG = al.ab;
        }
        if (h.a(ar)[10] != 0) {
            if (h.a(ar)[10] == 4) {
                aG = al.ac;
            } else {
                aG = al.ad;
            }
        }
        aF = aj.f;
        L = true;
        I = true;
        b();
        aj.a.repaint();
        aj.a.serviceRepaints();
        p.d();
        System.out.println("Mission scr toi day");
        f();
        System.out.println("Mission scr toi day");
        c.c();
        System.out.println("Mission scr toi day");
        B += aA;
        A += az;
        System.out.println("save score...");
        c.g();
        p.a("/menu.mid");
        L = false;
    }
}
