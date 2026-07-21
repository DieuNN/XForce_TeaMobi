package defpackage;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

/* JADX INFO: loaded from: XForce.jar:aj.class */
public final class aj extends Canvas implements Runnable {
    static aj a;
    private static boolean t;
    private static boolean u;
    static int b;
    static int c;
    static q d;
    static ab e;
    static af f;
    static l g;
    static j h;
    static t i;
    static u j;
    static ah k;
    static i l;
    static ag m;
    static b n;
    private long w;
    private long x;
    public static int s;
    private static int v = 60;
    static int o = -5;
    static int p = 49;
    static int q = 51;
    static int r = 0;

    public aj() {
        setFullScreenMode(true);
        a = this;
        t = true;
        c.f();
        al.a();
        c.a();
        new aa().b();
        new Thread(this).start();
    }

    public static void a() {
        b = a.getWidth();
        c = a.getHeight();
        k = new ah();
        n = new b();
        e = new ab();
        f = new af();
        g = new l();
        j = new u();
        l = new i();
        m = new ag();
        h = new j();
        i = new t();
        c.c();
        p.a();
    }

    public final void paint(Graphics graphics) {
        if (d != null) {
            d.a(graphics);
            graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
            if (d.e != null) {
                if (b >= 220) {
                    c.b.a(d.e, 8, c - 16, 0, graphics);
                } else {
                    c.a.a(d.e, 8, c - 16, 0, graphics);
                }
            }
            if (d.f != null) {
                if (b >= 220) {
                    c.b.a(d.f, b - 8, c - 16, 1, graphics);
                } else {
                    c.a.a(d.f, b - 8, c - 16, 1, graphics);
                }
            }
        }
        if (w.a) {
            w.a(graphics);
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.x = 0L;
        while (t) {
            this.w = System.currentTimeMillis();
            Thread.yield();
            if (u) {
                System.out.println("pause");
            } else {
                if (w.a) {
                    w.a();
                }
                if (d != null) {
                    if (s != 0) {
                        if (!w.a) {
                            d.a(s);
                        }
                        s = 0;
                    }
                    d.a();
                }
                repaint();
                serviceRepaints();
            }
            this.x = System.currentTimeMillis() - this.w;
            if (this.x < v) {
                try {
                    Thread.sleep(((long) v) - this.x);
                } catch (InterruptedException unused) {
                }
            }
        }
    }

    public static void b() {
        u = true;
        System.out.println("pause");
    }

    public static void c() {
        u = false;
        System.out.println("resume");
    }

    public static void d() {
        t = false;
        c.g();
        System.out.println("stop");
    }

    protected final void keyPressed(int i2) {
        if (r == 1) {
            o = 0;
        }
        if (r == 2) {
            p = 0;
        }
        if (r == 3) {
            q = 0;
        }
        switch (getGameAction(i2)) {
            case 1:
                q.a[0] = true;
                break;
            case 2:
                q.a[2] = true;
                break;
            case 3:
            case 4:
            default:
                switch (i2) {
                    case -22:
                    case -7:
                        q.a[7] = true;
                        break;
                    case -21:
                    case -6:
                        q.a[6] = true;
                        break;
                    default:
                        if (i2 == o) {
                            q.a[4] = true;
                        }
                        if (r == 1) {
                            o = i2;
                            if (p == o) {
                                p = 0;
                            }
                            if (q == o) {
                                q = 0;
                            }
                        }
                        if (r == 2) {
                            p = i2;
                            if (i2 == o) {
                                o = 0;
                            }
                            if (p == q) {
                                q = 0;
                            }
                        }
                        if (r == 3) {
                            q = i2;
                            if (i2 == o) {
                                o = 0;
                            }
                            if (q == p) {
                                p = 0;
                            }
                        }
                        break;
                }
                break;
            case 5:
                q.a[3] = true;
                break;
            case 6:
                q.a[1] = true;
                break;
        }
        if (i2 == -21) {
            i2 = -6;
        }
        if (i2 == -22) {
            i2 = -7;
        }
        s = i2;
        if (r != 0) {
            r = 0;
            q.c();
        }
    }

    protected final void keyReleased(int i2) {
        switch (getGameAction(i2)) {
            case 1:
                q.a[0] = false;
                break;
            case 2:
                q.a[2] = false;
                break;
            case 3:
            case 4:
            default:
                if (i2 == o) {
                    q.a[4] = false;
                }
                switch (i2) {
                    case 48:
                        q.a[5] = false;
                        break;
                }
                break;
            case 5:
                q.a[3] = false;
                break;
            case 6:
                q.a[1] = false;
                break;
        }
    }

    protected final void pointerPressed(int i2, int i3) {
        if (i3 <= c - 16) {
            q.b = i2;
            q.c = i3;
            q.d = 1;
        } else {
            if (i2 < 48) {
                q.a[6] = true;
            }
            if (i2 > b - 48) {
                q.a[7] = true;
            }
            q.d = 0;
        }
    }

    protected final void pointerDragged(int i2, int i3) {
        if (i3 > c - 16) {
            q.d = 0;
            return;
        }
        q.b = i2;
        q.c = i3;
        q.d = 1;
    }

    protected final void pointerReleased(int i2, int i3) {
        if (i3 > c - 16) {
            q.d = 0;
            return;
        }
        q.b = i2;
        q.c = i3;
        q.d = 3;
    }
}
