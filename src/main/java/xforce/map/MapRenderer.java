package xforce.map;

import xforce.entity.GameEntity;
import xforce.game.GameLevel;
import xforce.resource.ResourceManager;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class MapRenderer {

    public static final boolean DEBUG_BLOCKED_TILES = false;

    private static final int TILE_COUNT    = 79;
    private static final int TILE_EMPTY    = 1;
    private static final int TILE_WATER_1  = 36;
    private static final int TILE_WATER_2  = 37;
    private static final int TILE_WATER_3  = 38;
    private static final int TILE_TREE     = 40;
    private static final int TILE_COLLAPSED_A = 54;
    private static final int TILE_COLLAPSED_B = 55;
    private static final int TILE_COLLAPSED_C = 59;
    private static final int TILE_CRATE    = 60;
    private static final int TILE_OIL      = 61;
    private static final int TILE_MIN_DESTRUCTIBLE = 60;
    private static final int TILE_MAX_DESTRUCTIBLE = 79;
    private static final int TILE_MIN_SPECIAL = 73;
    private static final int TILE_MAX_SPECIAL = 79;
    private static final int TILE_MIN_WATER_EDGE = 12;
    private static final int TILE_MAX_WATER_EDGE = 17;
    private static final int TILE_WATER_CLEAR     = 18;
    private static final int TILE_WATER_ANIM_START = 12;

    private static final int TREE_PARALLAX_Z  = 10;
    private static final byte EFFECT_HIT      = 1;
    private static final byte EFFECT_DEBRIS   = 0;
    private static final byte EFFECT_EXPLOSION = 6;
    private static final byte BULLET_AREA     = 14;
    public static short[][] tileMap;
    public static byte[][] tileHp;
    private static int tileDestructHp;
    public static int mapWidth;
    public static int mapHeight;
    public static int mapPixelWidth;
    public static int mapPixelHeight;
    private static int viewColStart;
    private static int viewRowStart;
    private static int viewColEnd;
    private static int viewRowEnd;
    private static int curTileCol;
    private static int curTileRow;
    private static int maxTileCol;
    private static int waterAnim;
    private static int renderCol;
    private static int maxRenderCol;
    private static byte[] tilePassability;
    private static Image[] tileImages;
    public static int tileSize = 24;
    public static int tilesetId = 2;
    private static int tileCount = 79;
    private static byte[] treeSway = {0, 0, 1, 1, 1, 2, 2, 2, 2, 3, 2, 2, 2, 2, 1, 1, 1, 0, 0, -1, -2, -2, -2, -3, -3, -3, -3, -3, -3, -3, -3, -3, -2, -2, -2, -1};

    public MapRenderer() {
        tileImages = new Image[tileCount];
    }
    public static void loadTileset(int i) {
        System.out.println("Set title ID:" + i);
        tilesetId = i;
        Image imageM85a = ResourceManager.loadImage("/t" + i + ".png");
        for (int i2 = 0; i2 < tileCount; i2++) {
            tileImages[i2] = Image.createImage(tileSize, tileSize);
            tileImages[i2].getGraphics().drawImage(imageM85a, 0, (-i2) * tileSize, 0);
        }
        switch (i) {
            case 1:
                tilePassability = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 5, 2, 30, 30, 30, 10, 10, 10, 10, 10, 10, 10, 10, 5, 10, 5, 5, 5, 5, 0};
                break;
            case 2:
                tilePassability = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 5, 2, 100, 100, 100, 10, 10, 10, 10, 10, 10, 10, 10, 5, 10, 5, 5, 5, 5, 0};
                break;
            case 3:
                tilePassability = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 5, 2, 30, 30, 30, 10, 10, 10, 10, 10, 10, 10, 10, 5, 10, 5, 5, 5, 5, 0};
                break;
        }
    }
    public static void initTileArrays(short[][] sArr) {
        tileMap = sArr;
        mapHeight = sArr.length;
        int length = tileMap[0].length;
        mapWidth = length;
        mapPixelWidth = length * tileSize;
        mapPixelHeight = mapHeight * tileSize;
        tileHp = new byte[mapHeight][mapWidth];
        for (int i = 0; i < mapHeight; i++) {
            for (int i2 = 0; i2 < mapWidth; i2++) {
                tileHp[i][i2] = tilePassability[tileMap[i][i2]];
            }
        }
        if (DEBUG_BLOCKED_TILES) {
            StringBuilder sb = new StringBuilder("INIT BLOCKED TILES: ");
            for (int r = 0; r < mapHeight; r++) {
                for (int c = 0; c < mapWidth; c++) {
                    if (tileHp[r][c] != 0 && tileMap[r][c] != TILE_TREE) {
                        sb.append("(").append(r).append(",").append(c).append(")t").append(tileMap[r][c]).append("hp").append((int)tileHp[r][c]).append(" ");
                    }
                }
            }
            System.out.println(sb.toString());
        }
    }
    public static void renderTiles(Graphics graphics) {
        viewColStart = (-graphics.getTranslateY()) / tileSize;
        int i = (-graphics.getTranslateX()) / tileSize;
        viewColEnd = i;
        curTileCol = i * tileSize;
        maxTileCol = (-graphics.getTranslateX()) + GameLevel.viewportWidth;
        waterAnim = viewColStart * tileSize;
        maxRenderCol = (-graphics.getTranslateY()) + GameLevel.viewportHeight;
        viewRowStart = viewColStart;
        renderCol = waterAnim;
        while (renderCol < maxRenderCol) {
            if (viewRowStart >= 0 && viewRowStart < mapHeight) {
                viewRowEnd = viewColEnd;
                curTileRow = curTileCol;
                while (curTileRow < maxTileCol) {
                    if (viewRowEnd >= 0 && viewRowEnd < mapWidth && tileMap[viewRowStart][viewRowEnd] > 0) {
                        graphics.drawImage(tileImages[tileMap[viewRowStart][viewRowEnd] - 1], curTileRow, renderCol, 0);
                        if (DEBUG_BLOCKED_TILES && tileHp[viewRowStart][viewRowEnd] != 0 && tileMap[viewRowStart][viewRowEnd] != TILE_TREE) {
                            graphics.setColor(16711680);
                            graphics.drawRect(curTileRow, renderCol, 23, 23);
                        }
                        if (tilesetId == 2) {
                            if (tileMap[viewRowStart][viewRowEnd] == TILE_WATER_1 || tileMap[viewRowStart][viewRowEnd] == TILE_WATER_2) {
                                short[] sArr = tileMap[viewRowStart];
                                int i2 = viewRowEnd;
                                sArr[i2] = (short) (sArr[i2] + 1);
                            } else if (tileMap[viewRowStart][viewRowEnd] == TILE_WATER_3) {
                                tileMap[viewRowStart][viewRowEnd] = TILE_WATER_1;
                            }
                        }
                    }
                    curTileRow += tileSize;
                    viewRowEnd++;
                }
            }
            renderCol += tileSize;
            viewRowStart++;
        }
    }
    public static void renderTrees(Graphics graphics) {
        tileDestructHp = (tileDestructHp + 1) % treeSway.length;
        viewRowStart = viewColStart;
        renderCol = waterAnim;
        while (renderCol < maxRenderCol) {
            if (viewRowStart >= 0 && viewRowStart < mapHeight) {
                viewRowEnd = viewColEnd;
                curTileRow = curTileCol;
                while (curTileRow < maxTileCol) {
                    if (viewRowEnd >= 0 && viewRowEnd < mapWidth && tileMap[viewRowStart][viewRowEnd] == TILE_TREE) {
                        int i = (((curTileRow - GameEntity.cameraX) * TREE_PARALLAX_Z) / GameEntity.zScale) + curTileRow;
                        int i2 = (((renderCol - GameEntity.cameraY) * TREE_PARALLAX_Z) / GameEntity.zScale) + renderCol;
                        int length = ((tileDestructHp + (viewRowStart * 12)) + (viewRowEnd * 24)) % treeSway.length;
                        graphics.drawImage(ResourceManager.treeImage, (i + treeSway[length]) - 3, (i2 + treeSway[(length + (treeSway.length / 3)) % treeSway.length]) - 3, 0);
                    }
                    curTileRow += tileSize;
                    viewRowEnd++;
                }
            }
            renderCol += tileSize;
            viewRowStart++;
        }
    }
    public static boolean isBlocked(int row, int col) {
        if (row < 0 || row >= mapHeight || col < 0 || col >= mapWidth) {
            return true;
        }
        return (tileMap[row][col] != TILE_TREE || GameLevel.tileOccupancy[row][col] != null) && tileHp[row][col] != 0;
    }
    public static void setTileHp(int row, int col, int hp) {
        if (row < 0 || row >= mapHeight || col < 0 || col >= mapWidth) {
            return;
        }
        tileHp[row][col] = (byte) hp;
    }
    public static void resetTileHp(int row, int col) {
        tileHp[row][col] = tilePassability[tileMap[row][col]];
    }
    public static short getTileType(int px, int py) {
        viewRowEnd = px / tileSize;
        int col = py / tileSize;
        viewRowStart = col;
        if (col < 0 || viewRowStart >= mapHeight || viewRowEnd < 0 || viewRowEnd >= mapWidth) {
            return (short) -1;
        }
        return tileMap[viewRowStart][viewRowEnd];
    }
    public static boolean damageTile(int px, int py, int damage) {
        viewRowEnd = px / tileSize;
        int col = py / tileSize;
        viewRowStart = col;
        if (col < 0 || viewRowStart >= mapHeight || viewRowEnd < 0 || viewRowEnd >= mapWidth) {
            return false;
        }
        if (tileMap[viewRowStart][viewRowEnd] < TILE_MIN_DESTRUCTIBLE || tileMap[viewRowStart][viewRowEnd] >= TILE_MAX_DESTRUCTIBLE) {
            if (tilesetId != 2 || tileMap[viewRowStart][viewRowEnd] < TILE_WATER_ANIM_START || tileMap[viewRowStart][viewRowEnd] > TILE_MAX_WATER_EDGE) {
                return false;
            }
            if (tileHp[viewRowStart][viewRowEnd] > 0) {
                byte[] hpRow = tileHp[viewRowStart];
                int colIdx = viewRowEnd;
                hpRow[colIdx] = (byte) (hpRow[colIdx] - damage);
            }
            if (tileHp[viewRowStart][viewRowEnd] > 0) {
                return true;
            }
            GameLevel.spawnEffect(EFFECT_DEBRIS, px, py, 0, 0, 0);
            tileMap[viewRowStart][viewRowEnd] = TILE_WATER_CLEAR;
            resetTileHp(viewRowStart, viewRowEnd);
            return true;
        }
        if (tileHp[viewRowStart][viewRowEnd] > 0) {
            byte[] hpRow2 = tileHp[viewRowStart];
            int colIdx2 = viewRowEnd;
            hpRow2[colIdx2] = (byte) (hpRow2[colIdx2] - damage);
        }
        if (tileHp[viewRowStart][viewRowEnd] > 0) {
            GameLevel.spawnEffect(EFFECT_HIT, px, py, 0, 0, 0);
            return true;
        }
        switch (tileMap[viewRowStart][viewRowEnd]) {
            case 60:
                GameLevel.spawnPickup(px, py, (byte) ResourceManager.randomPositive(6));
                tileMap[viewRowStart][viewRowEnd] = TILE_EMPTY;
                break;
            case 61:
                System.out.println("hur oil ");
                GameLevel.spawnEffect(EFFECT_DEBRIS, px, py, 0, 0, 0);
                GameLevel.spawnBullet(BULLET_AREA, px, py, 0, null);
                tileMap[viewRowStart][viewRowEnd] = TILE_EMPTY;
                break;
            case 62:
            case 63:
            case 64:
            default:
                GameLevel.spawnEffect(EFFECT_EXPLOSION, (viewRowEnd * tileSize) + 12, (viewRowStart * tileSize) + 12, 0, 0, 0);
                GameLevel.spawnEffect(EFFECT_DEBRIS, px, py, 0, 0, 0);
                tileMap[viewRowStart][viewRowEnd] = TILE_EMPTY;
                break;
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
                GameLevel.spawnEffect(EFFECT_DEBRIS, px, py, 0, 0, 0);
                tileMap[viewRowStart][viewRowEnd] = TILE_COLLAPSED_B;
                break;
            case 73:
                GameLevel.spawnEffect(EFFECT_DEBRIS, px, py, 0, 0, 0);
                tileMap[viewRowStart][viewRowEnd] = TILE_COLLAPSED_C;
                break;
            case 74:
                GameLevel.spawnEffect(EFFECT_DEBRIS, px, py, 0, 0, 0);
                tileMap[viewRowStart][viewRowEnd] = TILE_COLLAPSED_A;
                break;
        }
        resetTileHp(viewRowStart, viewRowEnd);
        return true;
    }
    public static boolean damageSpecialTile(int px, int py, int damage) {
        viewRowEnd = px / tileSize;
        viewRowStart = py / tileSize;
        if (tileMap[viewRowStart][viewRowEnd] < TILE_MIN_SPECIAL || tileMap[viewRowStart][viewRowEnd] >= TILE_MAX_DESTRUCTIBLE) {
            return false;
        }
        if (tileHp[viewRowStart][viewRowEnd] > 0) {
            byte[] hpRow = tileHp[viewRowStart];
            int colIdx = viewRowEnd;
            hpRow[colIdx] = (byte) (hpRow[colIdx] - damage);
        }
        if (tileHp[viewRowStart][viewRowEnd] > 0) {
            GameLevel.spawnEffect(EFFECT_HIT, px, py, 0, 0, 0);
            return true;
        }
        switch (tileMap[viewRowStart][viewRowEnd]) {
            case 73:
                GameLevel.spawnEffect(EFFECT_DEBRIS, px, py, 0, 0, 0);
                tileMap[viewRowStart][viewRowEnd] = TILE_COLLAPSED_C;
                break;
            case 74:
                GameLevel.spawnEffect(EFFECT_DEBRIS, px, py, 0, 0, 0);
                tileMap[viewRowStart][viewRowEnd] = TILE_COLLAPSED_B;
                break;
            default:
                GameLevel.spawnEffect(EFFECT_EXPLOSION, (viewRowEnd * tileSize) + 12, (viewRowStart * tileSize) + 12, 0, 0, 0);
                GameLevel.spawnEffect(EFFECT_DEBRIS, px, py, 0, 0, 0);
                tileMap[viewRowStart][viewRowEnd] = TILE_EMPTY;
                break;
        }
        resetTileHp(viewRowStart, viewRowEnd);
        return true;
    }
    public static void freeTileResources() {
        tileMap = null;
        tileHp = null;
        mapHeight = 0;
        mapWidth = 0;
        mapPixelHeight = 0;
        mapPixelWidth = 0;
        for (int i = 0; i < tileCount; i++) {
            tileImages[i] = null;
        }
    }
}
