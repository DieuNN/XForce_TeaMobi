package defpackage;

import java.io.IOException;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;

/* JADX INFO: loaded from: XForce.jar:p.class */
public final class p implements Runnable {
    private static Thread c;
    private static boolean d;
    public static int a;
    public static int b;
    private static Player[] e;
    private static Player f;
    private static int h;
    private static p i;
    private static int g = -1;
    private static String j = "/sound";

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v15, types: [javax.microedition.media.Player[]] */
    /* JADX WARN: Type inference failed for: r0v16, types: [javax.microedition.media.Player] */
    /* JADX WARN: Type inference failed for: r0v2, types: [javax.microedition.media.Player[]] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Throwable] */
    public static void a() {
        if (i == null) {
            ?? r0 = new Player[3];
            e = r0;
            try {
                e[0] = Manager.createPlayer("".getClass().getResourceAsStream(new StringBuffer(String.valueOf(j)).append("/shoot.wav").toString()), "audio/x-wav");
                e[0].prefetch();
                e[1] = Manager.createPlayer("".getClass().getResourceAsStream(new StringBuffer(String.valueOf(j)).append("/explo.wav").toString()), "audio/x-wav");
                e[1].prefetch();
                e[2] = Manager.createPlayer("".getClass().getResourceAsStream(new StringBuffer(String.valueOf(j)).append("/missile.wav").toString()), "audio/x-wav");
                r0 = e[2];
                r0.prefetch();
            } catch (MediaException e2) {
                r0.printStackTrace();
            } catch (IOException e3) {
                r0.printStackTrace();
            }
            i = new p();
            d = true;
            Thread thread = new Thread(i);
            c = thread;
            thread.start();
        }
    }

    public static void a(int i2, int i3) {
        if (i3 <= 0) {
            return;
        }
        h = i3;
        g = i2;
    }

    public static void a(int i2) {
        h = 100;
        g = i2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [int] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v4, types: [javax.microedition.media.Player] */
    public static void b() {
        ?? state;
        if (f == null || (state = f.getState()) != 400) {
            return;
        }
        try {
            state = f;
            state.stop();
        } catch (MediaException e2) {
            state.printStackTrace();
        }
    }

    public static void c() {
        Player player = f;
        if (player != null) {
            try {
                if (a > 0) {
                    player = f;
                    player.start();
                }
            } catch (MediaException e2) {
                player.printStackTrace();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [int] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v4, types: [javax.microedition.media.Player] */
    public static void d() {
        if (f != null) {
            ?? state = f.getState();
            if (state == 400) {
                try {
                    state = f;
                    state.stop();
                } catch (MediaException e2) {
                    state.printStackTrace();
                }
            }
            f.close();
        }
    }

    /* JADX WARN: Type inference failed for: r0v11, types: [int, java.lang.Throwable] */
    public static void a(String str) {
        ?? level;
        d();
        try {
            Player playerCreatePlayer = Manager.createPlayer("".getClass().getResourceAsStream(new StringBuffer(String.valueOf(j)).append(str).toString()), "audio/midi");
            f = playerCreatePlayer;
            playerCreatePlayer.setLoopCount(-1);
            if (a > 0) {
                f.start();
                level = f.getControl("VolumeControl").setLevel(a * 20);
            }
        } catch (MediaException e2) {
            level.printStackTrace();
        } catch (IOException e3) {
            level.printStackTrace();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v7 */
    public static void b(int i2) {
        if (f != null && f.getState() != 0) {
            ?? level = i2;
            try {
                if (level > 0) {
                    f.start();
                    level = f.getControl("VolumeControl").setLevel(i2 * 20);
                } else {
                    Player player = f;
                    player.stop();
                    level = player;
                }
            } catch (MediaException e2) {
                level.printStackTrace();
            }
        }
        a = i2;
    }

    public static void c(int i2) {
        b = i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        while (d) {
            if (g > -1 && g < e.length) {
                int i2 = g;
                g = -1;
                try {
                    if (e[i2].getState() != 400 && b > 0) {
                        e[i2].getControl("VolumeControl").setLevel(((b * 20) * h) / 100);
                        e[i2].start();
                    }
                } catch (MediaException unused) {
                }
            }
            try {
                Thread.sleep(50L);
            } catch (InterruptedException unused2) {
            }
        }
    }
}
