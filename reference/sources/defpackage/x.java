package defpackage;

/* JADX INFO: loaded from: XForce.jar:x.class */
public final class x {
    public static int a;
    private static int b;
    private static int c;
    private static boolean d;

    public static void a(int i) {
        a = i;
        c = 0;
    }

    public static void a() {
        if (b > 0) {
            b--;
            return;
        }
        if (!w.a) {
            d = false;
        }
        if (d) {
            return;
        }
        switch (a) {
            case 1:
                if (c == 20) {
                    w.a(al.U[0], 3);
                    w.a = true;
                } else if (c == 40) {
                    ab.y = ab.u;
                    d = true;
                } else if (c == 41) {
                    w.a(al.U[1], ab.D);
                    d = true;
                } else if (c == 42) {
                    w.a(al.U[2], 3);
                    ab.w = 1272;
                    ab.x = 960;
                    ab.v = 2;
                    ab.f(1272, 960);
                    d = true;
                } else if (c == 43) {
                    w.a(al.U[3], 3);
                    ab.w = 132;
                    ab.x = 132;
                    ab.v = 2;
                    ab.f(132, 132);
                    d = true;
                } else if (c == 44) {
                    w.a(al.U[4], 3);
                    ab.y = ab.m;
                    ab.v = 0;
                    ab.f(1272, 960);
                    d = true;
                } else if (c == 45) {
                    a = 0;
                }
                break;
            case 2:
                if (c == 20) {
                    w.a(al.V[0], ab.D);
                    d = true;
                } else if (c == 40) {
                    w.a(al.V[1], 2);
                    d = true;
                } else if (c == 60) {
                    d dVar = new d((byte) -3);
                    dVar.a(1056, 960);
                    ab.a((an) dVar);
                    ab.y = dVar;
                } else if (c == 80) {
                    w.a(al.V[2], 3);
                    d = true;
                } else if (c == 100) {
                    a = 0;
                    ab.D = 2;
                    aj.e.d(31);
                }
                break;
            case 3:
                if (c == 20) {
                    w.a(al.W[0], 2);
                    d = true;
                } else if (c == 21) {
                    w.a(al.W[1], 3);
                    d = true;
                } else if (c == 22) {
                    w.a(al.W[2], 0);
                    d = true;
                } else if (c == 23) {
                    w.a(al.W[3], 1);
                    d = true;
                } else if (c == 24) {
                    w.a(al.W[4], 4);
                } else if (c == 25) {
                    a = 0;
                    ab.a(true);
                }
                break;
            case 4:
                if (c == 0) {
                    ab.y = ab.u;
                } else if (c == 50) {
                    w.a(al.X, 3);
                    d = true;
                } else if (c == 52) {
                    ab.y = ab.m;
                    a = 0;
                }
                break;
            case 5:
                if (c == 20) {
                    w.a(al.Y, 3);
                    d = true;
                }
                if (c == 21) {
                    ab.w = 504;
                    ab.x = 624;
                    ab.v = 2;
                } else if (c == 50) {
                    ab.w = 1056;
                    ab.x = 624;
                } else if (c == 80) {
                    ab.w = 504;
                    ab.x = 984;
                } else if (c == 120) {
                    ab.y = ab.m;
                    ab.v = 0;
                    a = 0;
                }
                break;
            case 6:
                if (c == 20) {
                    ab.y = ab.u;
                    w.a(al.Z, 3);
                    d = true;
                } else if (c == 21) {
                    ab.y = ab.m;
                    a = 0;
                }
                break;
        }
        if (a != 0) {
            c++;
        }
    }
}
