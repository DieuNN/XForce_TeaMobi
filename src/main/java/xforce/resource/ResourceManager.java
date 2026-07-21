package xforce.resource;

import xforce.entity.CompositeSprite;
import xforce.game.GameCanvas;
import xforce.game.GameLevel;
import xforce.game.XMIDlet;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Random;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.rms.RecordStore;

public final class ResourceManager {

    private static final int TRIG_SCALE      = 10;
    private static final int TRIG_ENTRIES    = 91;
    private static final int DEGREES_MAX     = 90;
    private static final int DEGREES_180     = 180;
    private static final int DEGREES_270     = 270;
    private static final int DEGREES_360     = 360;

    private static final int RANDOM_POOL_SIZE = 100;

    private static final int SAVE_SIZE         = 100;
    private static final int SAVE_OFF_NAME     = 0;
    private static final int SAVE_LEN_NAME     = 20;
    private static final int SAVE_OFF_XP       = 20;
    private static final int SAVE_OFF_CASH     = 24;
    private static final int SAVE_OFF_VEHICLE  = 28;
    private static final int SAVE_OFF_UPGRADES = 30;
    private static final int SAVE_OFF_FLAGS    = 50;
    private static final int SAVE_OFF_PLAYTIME = 96;
    private static final int SAVE_OFF_VIBRATE  = 90;
    private static final int SAVE_OFF_AUTO     = 91;
    private static final int SAVE_OFF_LANG     = 92;
    private static final int SAVE_OFF_FIRE     = 93;
    private static final int SAVE_OFF_DYNAMIC  = 94;
    private static final int SAVE_OFF_MINE     = 95;
    private static final int UPGRADE_COUNT     = 5;

    private static final int DEFAULT_REPAIR_PCT = 60;
    private static final int DEFAULT_CASH      = 100;
    private static final int DEFAULT_FIRE_KEY  = -5;
    private static final int DEFAULT_DYN_KEY   = 48;
    private static short[] cosTable;
    private static int[] tanTable;
    public static BitmapFont fontSmall;
    public static BitmapFont fontMedium;
    public static BitmapFont fontLarge;
    public static BitmapFont fontHud;
    public static BitmapFont fontDialog;
    public static SpriteSheet sparkSprite;
    public static SpriteSheet explosionSprite;
    public static SpriteSheet shotSprite;
    public static SpriteSheet smallExplosionSprite;
    public static SpriteSheet fighterSprite;
    public static SpriteSheet fighterLargeSprite;
    public static SpriteSheet fireSprite;
    public static SpriteSheet fighterExhaustSprite;
    public static Image mapImage;
    public static Image logoImage;
    public static Image buildingImage;
    public static SpriteSheet helicopterSprite;
    public static SpriteSheet heliShadowSprite;
    public static SpriteSheet propellerSprite;
    public static SpriteSheet propellerSprite2;
    public static SpriteSheet tankBossSprite;
    public static SpriteSheet tankTurretSprite;
    public static SpriteSheet vipSprite;
    public static SpriteSheet spotSprite;
    public static SpriteSheet spotSmallSprite;
    public static SpriteSheet itemSprite;
    public static SpriteSheet gunSprite;
    public static SpriteSheet gunSprite2;
    public static SpriteSheet smallGunSprite;
    public static SpriteSheet tinyGunSprite;
    public static Image backgroundImage;
    public static Image xfLogo;
    public static Image garagePreviewImage;
    public static Image hudPanel;
    public static Image houseImage;
    public static CompositeSprite bulletComposite;
    public static Image trainImage;
    public static Image trainDestroyedImage;
    public static Image shipImage;
    public static Image shipDestroyedImage;
    public static Image cloudImage;
    public static Image treeImage;
    public static Image mapBgImage;
    public static Image dialogBackground;
    public static CompositeSprite bossComposite;
    public static Image bossImage;
    public static Image waterTileImage;
    public static Image waterTileImage2;
    public static Image lockIcon;
    public static Image creditsLogo;
    public static Image arrowIcon;
    public static SpriteSheet playerSprite;
    public static SpriteSheet tankBodySprite;
    private static Random rng;
    private static int[] randomPool;
    private static int randomIndex;
    public static long startTime;
    public static int totalPlayTime;
    private static short[] sinTable = {0, 18, 36, 54, 71, 89, 107, 125, 143, 160, 178, 195, 213, 230, 248, 265, 282, 299, 316, 333, 350, 367, 384, 400, 416, 433, 449, 465, 481, 496, 512, 527, 543, 558, 573, 587, 602, 616, 630, 644, 658, 672, 685, 698, 711, 724, 737, 749, 761, 773, 784, 796, 807, 818, 828, 839, 849, 859, 868, 878, 887, 896, 904, 912, 920, 928, 935, 943, 949, 956, 962, 968, 974, 979, 984, 989, 994, 998, 1002, 1005, 1008, 1011, 1014, 1016, 1018, 1020, 1022, 1023, 1023, 1024, 1024};
    public static SpriteSheet[] tankSprites = new SpriteSheet[10];
    public static void init() {
        rng = new Random();
        randomPool = new int[RANDOM_POOL_SIZE];
        for (int i = 0; i < RANDOM_POOL_SIZE; i++) {
            randomPool[i] = rng.nextInt();
        }
        fontSmall = new BitmapFont("fontSS.png", new byte[]{5, 2, 5, 5, 5, 5, 5, 5, 5, 5, 3, 3, 3, 3, 5, 4, 4, 4, 2, 5, 5, 5, 5, 5, 5, 5, 5, 5, 4, 5, 5, 5, 6, 5, 5, 5, 5, 5, 5, 4, 5, 6, 6, 6, 6, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 5, 5, 5, 6, 5}, 14, 4,
            "0123456789.,:!?()-'/ABCDEFGHIJKLMNOPQRSTUVWXYZ\u00c1\u00c0\u1ea2\u00c3\u1ea0\u0102\u1eae\u1eb0\u1eb2\u1eb4\u1eb6\u00c2\u1ea4\u1ea6\u1ea8\u1eaa\u1eac\u00c9\u00c8\u1eba\u1ebc\u1eb8\u00ca\u1ebe\u1ec0\u1ec2\u1ec4\u1ec6\u00cd\u00cc\u1ec8\u0128\u1eca\u00d3\u00d2\u1ece\u00d5\u1ecc\u00d4\u1ed0\u1ed2\u1ed4\u1ed6\u1ed8\u01a0\u1eda\u1edc\u1ede\u1ee0\u1ee2\u00da\u00d9\u1ee6\u0168\u1ee4\u01af\u1ee8\u1eea\u1eec\u1eee\u1ef0\u00dd\u1ef2\u1ef6\u1ef8\u1ef4\u0110" + "<>$%");
        fontMedium = new BitmapFont("fcg10.png", new byte[]{7, 4, 7, 7, 7, 7, 7, 7, 7, 7, 3, 3, 3, 3, 6, 4, 4, 3, 2, 4, 8, 7, 8, 7, 6, 6, 9, 7, 3, 6, 7, 6, 10, 8, 8, 7, 9, 7, 6, 7, 7, 8, 10, 8, 9, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 3, 3, 3, 3, 3, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 7, 6, 6, 6, 6}, 14, 4,
            "0123456789.,:!?()-'/ABCDEFGHIJKLMNOPQRSTUVWXYZ\u00c1\u00c0\u1ea2\u00c3\u1ea0\u0102\u1eae\u1eb0\u1eb2\u1eb4\u1eb6\u00c2\u1ea4\u1ea6\u1ea8\u1eaa\u1eac\u00c9\u00c8\u1eba\u1ebc\u1eb8\u00ca\u1ebe\u1ec0\u1ec2\u1ec4\u1ec6\u00cd\u00cc\u1ec8\u0128\u1eca\u00d3\u00d2\u1ece\u00d5\u1ecc\u00d4\u1ed0\u1ed2\u1ed4\u1ed6\u1ed8\u01a0\u1eda\u1edc\u1ede\u1ee0\u1ee2\u00da\u00d9\u1ee6\u0168\u1ee4\u01af\u1ee8\u1eea\u1eec\u1eee\u1ef0\u00dd\u1ef2\u1ef6\u1ef8\u1ef4\u0110" + "<>$%");
        fontLarge = new BitmapFont("fcg14.png", new byte[]{8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 4, 4, 4, 4, 8, 6, 6, 6, 3, 7, 10, 10, 10, 10, 8, 8, 10, 10, 5, 8, 9, 8, 13, 11, 10, 10, 10, 10, 10, 9, 10, 10, 13, 11, 11, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 5, 5, 5, 5, 5, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10}, 20, 4, "0123456789.,:!?()-'/ABCDEFGHIJKLMNOPQRSTUVWXYZ\u00c1\u00c0\u1ea2\u00c3\u1ea0\u0102\u1eae\u1eb0\u1eb2\u1eb4\u1eb6\u00c2\u1ea4\u1ea6\u1ea8\u1eaa\u1eac\u00c9\u00c8\u1eba\u1ebc\u1eb8\u00ca\u1ebe\u1ec0\u1ec2\u1ec4\u1ec6\u00cd\u00cc\u1ec8\u0128\u1eca\u00d3\u00d2\u1ece\u00d5\u1ecc\u00d4\u1ed0\u1ed2\u1ed4\u1ed6\u1ed8\u01a0\u1eda\u1edc\u1ede\u1ee0\u1ee2\u00da\u00d9\u1ee6\u0168\u1ee4\u01af\u1ee8\u1eea\u1eec\u1eee\u1ef0\u00dd\u1ef2\u1ef6\u1ef8\u1ef4\u0110");
        fontDialog = new BitmapFont("f21_10.png", new byte[]{6, 3, 5, 6, 6, 6, 5, 6, 6, 6, 3, 3, 3, 3, 6, 4, 4, 5, 3, 6, 8, 8, 8, 8, 8, 8, 8, 8, 3, 8, 8, 8, 10, 8, 8, 8, 8, 8, 7, 8, 8, 8, 10, 8, 8, 8, 6, 6, 6, 6, 6, 5, 6, 6, 3, 5, 6, 3, 8, 6, 6, 6, 6, 6, 6, 5, 6, 6, 8, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 3, 3, 3, 3, 3, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6}, 12, 4, "0123456789.,:!?()-'/ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz\u00e1\u00e0\u1ea3\u00e3\u1ea1\u0103\u1eaf\u1eb1\u1eb3\u1eb5\u1eb7\u00e2\u1ea5\u1ea7\u1ea9\u1eab\u1ead\u00e9\u00e8\u1ebb\u1ebd\u1eb9\u00ea\u1ebf\u1ec1\u1ec3\u1ec5\u1ec7\u00ed\u00ec\u1ec9\u0129\u1ecb\u00f3\u00f2\u1ecf\u00f5\u1ecd\u00f4\u1ed1\u1ed3\u1ed5\u1ed7\u1ed9\u01a1\u1edb\u1edd\u1edf\u1ee1\u1ee3\u00fa\u00f9\u1ee7\u0169\u1ee5\u01b0\u1ee9\u1eeb\u1eed\u1eef\u1ef1\u00fd\u1ef3\u1ef7\u1ef9\u1ef5\u0111\u0110");
        fontHud = new BitmapFont("fsss.png", new byte[]{5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7, 5, 3, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7, 5, 5, 5, 5, 5, 5, 5, 5, 5, 7, 5, 5, 5, 3}, 8, 4, "0123456789+-%$:ABCDEFGHIJKLMNOPQRSTUVWXYZ.");
        dialogBackground = loadImage("/dialg.png");
        loadImage("/dialg2.png");
        new SpriteSheet(loadImage("/load.png"), 24, 24);
        lockIcon = loadImage("/lock.png");
        creditsLogo = loadImage("/logo.png");
        arrowIcon = loadImage("/down.png");
        cosTable = new short[TRIG_ENTRIES];
        tanTable = new int[TRIG_ENTRIES];
        for (int i = 0; i <= DEGREES_MAX; i++) {
            cosTable[i] = sinTable[DEGREES_MAX - i];
            if (cosTable[i] == 0) {
                tanTable[i] = Integer.MAX_VALUE;
            } else {
                tanTable[i] = (sinTable[i] << TRIG_SCALE) / cosTable[i];
            }
        }
    }
    public static void unloadLevelAssets() {
        backgroundImage = null;
        xfLogo = null;
        mapBgImage = null;
        garagePreviewImage = null;
    }
    public static void loadLevelAssets() {
        if (GameCanvas.screenWidth >= 240) {
            backgroundImage = loadImage("/bgab.png");
        } else {
            backgroundImage = loadImage("/bga.png");
        }
        xfLogo = loadImage("/xf.png");
        mapBgImage = loadImage("/map.png");
        loadImage("/line1.png");
    }
    public static void freeAllSprites() {
        sparkSprite.dispose();
        explosionSprite.dispose();
        shotSprite.dispose();
        smallExplosionSprite.dispose();
        if (fighterSprite != null) {
            fighterSprite.dispose();
            fighterSprite = null;
        }
        if (fighterLargeSprite != null) {
            fighterLargeSprite.dispose();
            fighterLargeSprite = null;
        }
        if (fireSprite != null) {
            fireSprite.dispose();
            fireSprite = null;
        }
        if (fighterExhaustSprite != null) {
            fighterExhaustSprite.dispose();
            fighterExhaustSprite = null;
        }
        mapImage = null;
        logoImage = null;
        buildingImage = null;
        for (int i = 0; i < 10; i++) {
            if (tankSprites[i] != null) {
                tankSprites[i].dispose();
                tankSprites[i] = null;
            }
        }
        if (tankBossSprite != null) {
            tankBossSprite.dispose();
            tankBossSprite = null;
        }
        if (vipSprite != null) {
            vipSprite.dispose();
            vipSprite = null;
        }
        if (helicopterSprite != null) {
            helicopterSprite.dispose();
            helicopterSprite = null;
        }
        if (heliShadowSprite != null) {
            heliShadowSprite.dispose();
            heliShadowSprite = null;
        }
        if (propellerSprite != null) {
            propellerSprite.dispose();
            propellerSprite = null;
        }
        if (propellerSprite2 != null) {
            propellerSprite2.dispose();
            propellerSprite2 = null;
        }
        spotSprite.dispose();
        spotSmallSprite.dispose();
        itemSprite.dispose();
        cloudImage = null;
        waterTileImage = null;
        waterTileImage2 = null;
        treeImage = null;
        houseImage = null;
        if (gunSprite != null) {
            gunSprite.dispose();
            gunSprite = null;
        }
        if (gunSprite2 != null) {
            gunSprite2.dispose();
            gunSprite2 = null;
        }
        if (tinyGunSprite != null) {
            tinyGunSprite.dispose();
            tinyGunSprite = null;
        }
        if (smallGunSprite != null) {
            smallGunSprite.dispose();
            smallGunSprite = null;
        }
        if (tankBodySprite != null) {
            tankBodySprite.dispose();
            tankBodySprite = null;
        }
        if (playerSprite != null) {
            playerSprite.dispose();
            playerSprite = null;
        }
        hudPanel = null;
        trainImage = null;
        trainDestroyedImage = null;
        shipImage = null;
        shipDestroyedImage = null;
        bossComposite = null;
        bossImage = null;
    }
    public static void loadEffectSprites() {
        if (treeImage != null) {
            return;
        }
        sparkSprite = new SpriteSheet(loadImage("/spark.png"), 11, 11);
        explosionSprite = new SpriteSheet(loadImage("/spark2.png"), 7, 7);
        shotSprite = new SpriteSheet(loadImage("/explo.png"), 32, 32);
        smallExplosionSprite = new SpriteSheet(loadImage("/explo1.png"), 16, 16);
        fireSprite = new SpriteSheet(loadImage("/fs.png"), 19, 19);
        vipSprite = new SpriteSheet(loadImage("/vip.png"), 24, 24);
        spotSprite = new SpriteSheet(loadImage("/spot.png"), 32, 32);
        spotSmallSprite = new SpriteSheet(loadImage("/spot1.png"), 24, 24);
        itemSprite = new SpriteSheet(loadImage("/items.png"), 16, 16);
        treeImage = loadImage("/tree.png");
        bulletComposite = new CompositeSprite(loadImage("/shot.png"), new int[][]{new int[]{0, 12, 3, 8, 1, 4}, new int[]{3, 12, 3, 8, 1, 4}, new int[]{6, 12, 8, 3, 4, 1}, new int[]{6, 15, 8, 3, 4, 1}, new int[]{0, 20, 5, 10, 2, 5}, new int[]{5, 20, 5, 10, 2, 5}, new int[]{10, 20, 10, 5, 5, 2}, new int[]{10, 25, 10, 5, 5, 2}, new int[]{0, 0, 5, 12, 2, 6}, new int[]{5, 0, 5, 12, 2, 6}, new int[]{10, 0, 12, 5, 6, 2}, new int[]{10, 5, 12, 5, 6, 2}, new int[]{0, 30, 7, 16, 3, 8}, new int[]{7, 30, 7, 16, 3, 8}, new int[]{14, 30, 16, 7, 8, 3}, new int[]{14, 37, 16, 7, 8, 3}, new int[]{0, 46, 7, 20, 3, 10}, new int[]{7, 46, 7, 20, 3, 10}, new int[]{0, 66, 20, 7, 10, 3}, new int[]{0, 73, 20, 7, 10, 3}, new int[]{24, 2, 6, 6, 3, 3}, new int[]{15, 11, 6, 6, 3, 3}, new int[]{24, 11, 6, 6, 3, 3}, new int[]{24, 46, 8, 8, 4, 4}, new int[]{24, 55, 8, 8, 4, 4}, new int[]{24, 64, 8, 8, 4, 4}, new int[]{20, 18, 6, 6, 3, 3}, new int[]{26, 18, 6, 6, 3, 3}, new int[]{20, 24, 6, 6, 3, 3}, new int[]{26, 24, 6, 6, 3, 3}});
    }
    public static final int sin(int angle) {
        angle = normalizeAngle(angle);
        if (angle < DEGREES_MAX) {
            return sinTable[angle];
        }
        if (angle < DEGREES_180) {
            return sinTable[DEGREES_180 - angle];
        }
        if (angle < DEGREES_270) {
            return -sinTable[angle - DEGREES_180];
        }
        return -sinTable[DEGREES_360 - angle];
    }

    public static final int cos(int angle) {
        angle = normalizeAngle(angle);
        if (angle < DEGREES_MAX) {
            return cosTable[angle];
        }
        if (angle < DEGREES_180) {
            return -cosTable[DEGREES_180 - angle];
        }
        if (angle < DEGREES_270) {
            return -cosTable[angle - DEGREES_180];
        }
        return cosTable[DEGREES_360 - angle];
    }
    public static final int angleBetween(int dx, int dy) {
        if (dx != 0) {
            int tanAbs = Math.abs((dy << TRIG_SCALE) / dx);
            int angle;
            for (angle = 0; angle <= DEGREES_MAX; angle++) {
                if (tanTable[angle] >= tanAbs) {
                    break;
                }
            }
            if (dy >= 0 && dx < 0) {
                angle = DEGREES_180 - angle;
            }
            if (dy < 0 && dx < 0) {
                angle += DEGREES_180;
            }
            if (dy < 0 && dx >= 0) {
                angle = DEGREES_360 - angle;
            }
            return angle;
        }
        return dy > 0 ? DEGREES_MAX : DEGREES_270;
    }
    public static final int normalizeAngle(int angle) {
        if (angle >= DEGREES_360) {
            angle -= 360;
        }
        if (angle < 0) {
            angle += 360;
        }
        return angle;
    }

    public static final int angleDelta(int angle1, int angle2) {
        int delta = angle2 - angle1;
        if (delta < -DEGREES_180) {
            return delta + DEGREES_360;
        }
        return delta > DEGREES_180 ? delta - DEGREES_360 : delta;
    }
    public static final Image loadImage(String filename) {
        Image image = null;
        try {
            image = Image.createImage("/img" + filename);
        } catch (Exception unused) {
        }
        if (image == null) {
            System.out.println("Load image '" + filename + "': ERROR!!!");
        }
        return image;
    }
    public static final byte[] loadBytes(String path) {
        try {
            InputStream in = "".getClass().getResourceAsStream(path);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            while (true) {
                int b = in.read();
                if (b == -1) {
                    in.close();
                    byte[] data = out.toByteArray();
                    out.close();
                    return data;
                }
                out.write(b);
            }
        } catch (Exception unused) {
            return null;
        }
    }

    public static void fillQuad(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, int color, Graphics graphics) {
        graphics.setColor(color);
        graphics.fillTriangle(x1, y1, x2, y2, x3, y3);
        graphics.fillTriangle(x1, y1, x3, y3, x4, y4);
    }
    public static final int randomInt(int max) {
        if (randomIndex >= RANDOM_POOL_SIZE) {
            randomIndex = 0;
        }
        return randomPool[randomIndex++] % max;
    }

    public static final int randomPositive(int max) {
        return Math.abs(randomInt(max));
    }
    public static boolean loadGame() {
        byte[] data = loadRecord("XFData");
        System.out.println("load...");
        if (data == null || data.length != SAVE_SIZE) {
            GameLevel.playerName = "";
            GameLevel.xp = 0;
            GameLevel.cash = DEFAULT_CASH;
            GameLevel.currentVehicle = 0;
            byte[] bArr = new byte[5];
            bArr[3] = DEFAULT_REPAIR_PCT;
            GameLevel.vehicleUpgrades = new byte[][]{bArr, new byte[]{-1, 0, 0, DEFAULT_REPAIR_PCT, 0}, new byte[]{-1, 0, 0, DEFAULT_REPAIR_PCT, 0}};
            for (int i = 0; i < GameLevel.missionFlags.length; i++) {
                GameLevel.missionFlags[i] = false;
            }
            totalPlayTime = 0;
            startTime = System.currentTimeMillis();
            System.out.println("load fail.");
            return false;
        }
        GameLevel.playerName = new String(data, SAVE_OFF_NAME, SAVE_LEN_NAME).trim();
        GameLevel.xp = bytesToInt(SAVE_OFF_XP, data);
        GameLevel.cash = bytesToInt(SAVE_OFF_CASH, data);
        GameLevel.currentVehicle = data[SAVE_OFF_VEHICLE];
        int i2 = SAVE_OFF_UPGRADES;
        for (int i3 = 0; i3 < 3; i3++) {
            for (int i4 = 0; i4 < UPGRADE_COUNT; i4++) {
                int i5 = i2;
                i2++;
                GameLevel.vehicleUpgrades[i3][i4] = data[i5];
            }
        }
        for (int i6 = 0; i6 < GameLevel.missionFlags.length; i6++) {
            GameLevel.missionFlags[i6] = data[i6 + SAVE_OFF_FLAGS] != 0;
        }
        totalPlayTime = bytesToInt(SAVE_OFF_PLAYTIME, data);
        startTime = System.currentTimeMillis();
        XMIDlet.vibrationEnabled = data[SAVE_OFF_VIBRATE] != 0;
        GameLevel.autoShoot = data[SAVE_OFF_AUTO] != 0;
        Localization.language = data[SAVE_OFF_LANG];
        GameCanvas.fireKey = data[SAVE_OFF_FIRE];
        GameCanvas.dynamicKey = data[SAVE_OFF_DYNAMIC];
        GameCanvas.mineKey = data[SAVE_OFF_MINE];
        if (GameCanvas.fireKey == 0) {
            GameCanvas.fireKey = DEFAULT_FIRE_KEY;
        }
        if (GameCanvas.dynamicKey == 0) {
            GameCanvas.dynamicKey = DEFAULT_DYN_KEY;
        }
        System.out.println("load done.");
        return true;
    }

    public static void saveGame() {
        byte[] bArr = new byte[SAVE_SIZE];
        if (startTime == 0) {
            return;
        }
        byte[] bytes = GameLevel.playerName.getBytes();
        for (int i = 0; i < SAVE_LEN_NAME && i < bytes.length; i++) {
            bArr[i + SAVE_OFF_NAME] = bytes[i];
        }
        for (int length = bytes.length; length < SAVE_LEN_NAME; length++) {
            bArr[length + SAVE_OFF_NAME] = 32;
        }
        intToBytes(GameLevel.xp, SAVE_OFF_XP, bArr);
        intToBytes(GameLevel.cash, SAVE_OFF_CASH, bArr);
        bArr[SAVE_OFF_VEHICLE] = (byte) GameLevel.currentVehicle;
        int i2 = SAVE_OFF_UPGRADES;
        for (int i3 = 0; i3 < 3; i3++) {
            for (int i4 = 0; i4 < UPGRADE_COUNT; i4++) {
                int i5 = i2;
                i2++;
                bArr[i5] = GameLevel.vehicleUpgrades[i3][i4];
            }
        }
        for (int i6 = 0; i6 < GameLevel.missionFlags.length; i6++) {
            bArr[i6 + SAVE_OFF_FLAGS] = (byte) (GameLevel.missionFlags[i6] ? 1 : 0);
        }
        intToBytes((((int) (System.currentTimeMillis() - startTime)) / 1000) + totalPlayTime, SAVE_OFF_PLAYTIME, bArr);
        bArr[SAVE_OFF_VIBRATE] = (byte) (XMIDlet.vibrationEnabled ? 1 : 0);
        bArr[SAVE_OFF_AUTO] = (byte) (GameLevel.autoShoot ? 1 : 0);
        bArr[SAVE_OFF_LANG] = (byte) Localization.language;
        bArr[SAVE_OFF_FIRE] = (byte) GameCanvas.fireKey;
        bArr[SAVE_OFF_DYNAMIC] = (byte) GameCanvas.dynamicKey;
        bArr[SAVE_OFF_MINE] = (byte) GameCanvas.mineKey;
        try {
            RecordStore recordStore = RecordStore.openRecordStore("XFData", true);
            if (recordStore.getNumRecords() == 0) {
                recordStore.addRecord(bArr, 0, bArr.length);
            } else {
                recordStore.setRecord(1, bArr, 0, bArr.length);
            }
            recordStore.closeRecordStore();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("save...");
    }

    private static void intToBytes(int value, int offset, byte[] dest) {
        dest[offset] = (byte) (value >>> 24);
        dest[offset + 1] = (byte) (value >> 16);
        dest[offset + 2] = (byte) (value >> 8);
        dest[offset + 3] = (byte) value;
    }

    private static int bytesToInt(int offset, byte[] src) {
        return ((src[offset] & 255) << 24) | ((src[offset + 1] & 255) << 16) | ((src[offset + 2] & 255) << 8) | (src[offset + 3] & 255);
    }

    private static byte[] loadRecord(String name) {
        try {
            RecordStore store = RecordStore.openRecordStore(name, false);
            byte[] data = store.getRecord(1);
            store.closeRecordStore();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
