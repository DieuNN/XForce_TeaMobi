package xforce.game;

import xforce.audio.AudioManager;
import xforce.data.MissionData;
import xforce.data.MissionScript;
import xforce.data.MissionState;
import xforce.entity.BigBoss;
import xforce.entity.Bullet;
import xforce.entity.ElectricPole;
import xforce.entity.EnemyHelicopter;
import xforce.entity.EnemyTank;
import xforce.entity.EnemyTurret;
import xforce.entity.Fighter;
import xforce.entity.GameEntity;
import xforce.entity.PickupItem;
import xforce.entity.PlayerVehicle;
import xforce.entity.Sprite;
import xforce.entity.Train;
import xforce.entity.VIP;
import xforce.entity.VisualEffect;
import xforce.entity.WarShip;
import xforce.map.MapRenderer;
import xforce.resource.Localization;
import xforce.resource.ResourceManager;
import xforce.screen.DialogBox;
import xforce.screen.GameScreen;

import javax.microedition.lcdui.Graphics;

public final class GameLevel extends GameScreen {
    private static boolean missionActive;
    public static int viewportWidth;
    public static int viewportHeight;
    public static int cameraX;
    public static int cameraY;
    private static MapRenderer currentMissionId;
    public static PlayerVehicle player;
    public static Sprite[][] tileOccupancy;
    public static int entityCount;
    public static int enemyCount;
    public static int totalEnemies;
    public static int enemiesKilled;
    private static int pickupCount;
    private static Sprite[] entityPool;
    private static int rainDropIndex;
    private static int rainDropCount;
    private static int snowCount;
    private static int snowX;
    private static int snowY;
    private static int snowV;
    private static int snowFall;
    private static short[] rainX;
    private static short[] rainY;
    private static short[] rainZ;
    private static short[] rainPrevX;
    private static short[] rainPrevY;
    private static short snowWindX;
    private static short snowWindY;
    private static short[] cloudX;
    private static short[] cloudY;
    private static short[] cloudZ;
    private static int cloudCount;
    private static int cloudAnim;
    private static boolean hasClouds;
    private static boolean hasSnow;
    private static boolean hasRain;
    public static int screenShake;
    private static int musicTrackId;
    public static Sprite missionObjective;
    public static int missionX;
    public static int missionY;
    public static int missionParam;
    public static Sprite cameraTarget;
    private static boolean waypointVisible;
    private static int waypointX;
    private static int waypointY;
    private static int waypointRadius;
    private static int minimapCenterX;
    private static int hudY;
    private static int missionXp;
    private static int missionCash;
    public static int currentVehicle;
    public static int dynamiteCount;
    public static int mineCount;
    private static boolean gameOverAnimating;
    private static int gameOverAnimPos;
    private static int gameOverAnimEnd;
    public static boolean loadingScreen;
    private static int playTime;
    private static GameScreen previousScreen;
    public static boolean missionComplete;
    public static int gameOverTimer;
    private static String pendingDialog;
    private static boolean hasHudPanel;
    private String cheatBuffer = "";
    public static Sprite[] entities = new Sprite[200];
    private static PickupItem[] pickups = new PickupItem[100];
    private static VisualEffect[] effects = new VisualEffect[50];
    public static Bullet[] playerBullets = new Bullet[50];
    private static Bullet[] landmines = new Bullet[50];
    private static int[] shakeX = {3, -3, 3, -3};
    private static int[] shakeY = {3, -3, -3, 3};
    private static int minimapCenterY = 25;
    public static String playerName = "";
    public static int xp = 0;
    public static int cash = 0;
    public static byte[][] vehicleUpgrades = new byte[3][5];
    public static boolean autoShoot = true;
    public static boolean[] missionFlags = new boolean[40];

    public static void addXP(int i) {
        missionXp += i;
        System.out.println("addXP " + i);
    }
    public static void setupMapData(int i) {
        missionCash += 100;
        System.out.println("addCash 100");
    }

    public GameLevel() {
        viewportWidth = GameCanvas.screenWidth;
        viewportHeight = GameCanvas.screenHeight;
        currentMissionId = new MapRenderer();
        for (int i = 0; i < effects.length; i++) {
            effects[i] = new VisualEffect();
        }
        for (int i2 = 0; i2 < playerBullets.length; i2++) {
            playerBullets[i2] = new Bullet();
            playerBullets[i2].hidden = true;
        }
        entityCount = 0;
        hudY = GameCanvas.screenHeight - 48;
        boolean z = GameCanvas.screenWidth == 240;
        hasHudPanel = z;
        if (z) {
            viewportHeight = hudY;
        }
        minimapCenterX = GameCanvas.screenWidth - 25;
        System.gc();
        System.out.println("free=" + Runtime.getRuntime().freeMemory());
    }
    public final void loadMission(int i) {
        musicTrackId = i;
        byte[] bArrM86b = ResourceManager.loadBytes("/map/map" + musicTrackId);
        int[] iArrM99a = MissionData.getMissionData(musicTrackId);
        this.title = Localization.txtMission;
        this.subtitle = Localization.missionObjectives[iArrM99a[MissionData.IDX_MISSION_TYPE]];
        missionActive = true;
        loadingScreen = true;
        previousScreen = null;
        show();
        GameCanvas.instance.repaint();
        GameCanvas.instance.serviceRepaints();
        entityCount = 0;
        pickupCount = 0;
        enemyCount = 0;
        totalEnemies = 0;
        enemiesKilled = 0;
        missionObjective = null;
        missionCash = 0;
        missionXp = 0;
        for (int i2 = 0; i2 < effects.length; i2++) {
            effects[i2].hidden = true;
        }
        for (int i3 = 0; i3 < playerBullets.length; i3++) {
            playerBullets[i3].hidden = true;
        }
        for (int length = landmines.length - 1; length >= 0; length--) {
            landmines[length] = null;
        }
        if (currentVehicle == 0) {
            player = new PlayerVehicle((byte) -1);
        } else if (currentVehicle == 1) {
            player = new PlayerVehicle((byte) -2);
        } else if (currentVehicle == 2) {
            player = new PlayerVehicle((byte) -3);
        }
        addEntity((Sprite) player);
        MapRenderer.loadTileset(iArrM99a[MissionData.IDX_TILESET]);
        int i4 = iArrM99a[MissionData.IDX_MAP_HEIGHT];
        int i5 = iArrM99a[MissionData.IDX_MAP_WIDTH];
        player.setPosition(iArrM99a[MissionData.IDX_PLAYER_X] * MapRenderer.tileSize, iArrM99a[MissionData.IDX_PLAYER_Y] * MapRenderer.tileSize);
        short[][] sArr = new short[i5][i4];
        hasSnow = false;
        hasRain = false;
        switch (MapRenderer.tilesetId) {
            case 1:
                boolean z = ResourceManager.randomInt(10) == 0;
                hasRain = z;
                hasClouds = !z;
                break;
            case 2:
                hasClouds = true;
                break;
            case 3:
                hasSnow = ResourceManager.randomInt(3) == 0;
                break;
        }
        if (hasClouds) {
            ResourceManager.cloudImage = ResourceManager.loadImage("/cloud1.png");
        }
        System.out.println(i4 + " " + i5);
        tileOccupancy = new Sprite[i5][i4];
        for (int i6 = 0; i6 < i5; i6++) {
            for (int i7 = 0; i7 < i4; i7++) {
                sArr[i6][i7] = bArrM86b[(i6 * i4) + i7];
                switch (sArr[i6][i7]) {
                    case 39:
                        break;
                    case 41:
                    case 43:
                    case 44:
                        Sprite[] entityArr = entities;
                        int i8 = entityCount;
                        entityCount = i8 + 1;
                        Sprite entity = new Sprite(i7 * MapRenderer.tileSize, i6 * MapRenderer.tileSize, bArrM86b[(i6 * i4) + i7] != 0 ? (byte) 1 : (byte) 0);
                        entityArr[i8] = entity;
                        tileOccupancy[i6][i7] = entity;
                        break;
                    case 42:
                        Sprite[] entityArr2 = entities;
                        int i9 = entityCount;
                        entityCount = i9 + 1;
                        ElectricPole pole = new ElectricPole(i7 * MapRenderer.tileSize, i6 * MapRenderer.tileSize);
                        entityArr2[i9] = pole;
                        tileOccupancy[i6][i7] = pole;
                        break;
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                        Sprite[] entityArr3 = entities;
                        int i10 = entityCount;
                        entityCount = i10 + 1;
                        EnemyTurret turret = new EnemyTurret(i7 * MapRenderer.tileSize, i6 * MapRenderer.tileSize, bArrM86b[(i6 * i4) + i7] != 0 ? (byte) 1 : (byte) 0);
                        entityArr3[i10] = turret;
                        tileOccupancy[i6][i7] = turret;
                        entities[entityCount - 1].target = player;
                        break;
                    case 80:
                        sArr[i6][i7] = 60;
                        tileOccupancy[i6][i7] = new Sprite(i7 * MapRenderer.tileSize, i6 * MapRenderer.tileSize, (byte) 62);
                        break;
                    case 81:
                        sArr[i6][i7] = 39;
                        Sprite[] entityArr4 = entities;
                        int i11 = entityCount;
                        entityCount = i11 + 1;
                        EnemyHelicopter heli = new EnemyHelicopter(i7 * MapRenderer.tileSize, i6 * MapRenderer.tileSize);
                        entityArr4[i11] = heli;
                        tileOccupancy[i6][i7] = heli;
                        break;
                    case 82:
                        sArr[i6][i7] = sArr[i6][i7 - 1];
                        Sprite[] entityArr5 = entities;
                        int i12 = entityCount;
                        entityCount = i12 + 1;
                        Sprite entity2 = new Sprite(i7 * MapRenderer.tileSize, i6 * MapRenderer.tileSize, (byte) 120);
                        entityArr5[i12] = entity2;
                        tileOccupancy[i6][i7] = entity2;
                        break;
                    case 83:
                        sArr[i6][i7] = sArr[i6][i7 - 1];
                        Sprite[] entityArr6 = entities;
                        int i13 = entityCount;
                        entityCount = i13 + 1;
                        Sprite entity3 = new Sprite(i7 * MapRenderer.tileSize, i6 * MapRenderer.tileSize, (byte) 121);
                        entityArr6[i13] = entity3;
                        tileOccupancy[i6][i7] = entity3;
                        break;
                    case 84:
                        sArr[i6][i7] = sArr[i6][i7 - 1];
                        Sprite[] entityArr7 = entities;
                        int i14 = entityCount;
                        entityCount = i14 + 1;
                        Sprite entity4 = new Sprite(i7 * MapRenderer.tileSize, i6 * MapRenderer.tileSize, (byte) 122);
                        entityArr7[i14] = entity4;
                        tileOccupancy[i6][i7] = entity4;
                        break;
                    case 85:
                        sArr[i6][i7] = 60;
                        tileOccupancy[i6][i7] = new Sprite(i7 * MapRenderer.tileSize, i6 * MapRenderer.tileSize, (byte) 63);
                        break;
                    default:
                        if (sArr[i6][i7] >= 86 && sArr[i6][i7] < 96) {
                            tileOccupancy[i6][i7] = addEntity((Sprite) new EnemyTank(i7 * MapRenderer.tileSize, i6 * MapRenderer.tileSize, (byte) (1 + (sArr[i6][i7] - 86))));
                            sArr[i6][i7] = 79;
                            totalEnemies += 3;
                        }
                        break;
                }
            }
        }
        System.out.println("totalEnemy=" + totalEnemies);
        System.out.println("load4");
        MapRenderer.initTileArrays(sArr);
        for (int i15 = 0; i15 < entityCount; i15++) {
            if (entities[i15].type == 120 || entities[i15].type == 121 || entities[i15].type == 122) {
                MapRenderer.tileHp[entities[i15].y / MapRenderer.tileSize][entities[i15].x / MapRenderer.tileSize] = 1;
            }
        }
        int i16 = 6;
        while (i16 < iArrM99a.length) {
            switch (iArrM99a[i16]) {
                case 66:
                    int i17 = i16 + 1;
                    i16 = i17 + 1;
                    int i18 = iArrM99a[i17];
                    System.out.println("Read bridge position: " + i18);
                    for (int i19 = 0; i19 < i18; i19++) {
                        int i20 = i16;
                        int i21 = i16 + 1;
                        if (iArrM99a[i20] == 1) {
                            int i22 = i21 + 1;
                            int i23 = iArrM99a[i21] * MapRenderer.tileSize;
                            i16 = i22 + 1;
                            addEntity(new Sprite(i23, iArrM99a[i22] * MapRenderer.tileSize, (byte) 16));
                        } else {
                            int i24 = i21 + 1;
                            int i25 = iArrM99a[i21] * MapRenderer.tileSize;
                            i16 = i24 + 1;
                            addEntity(new Sprite(i25, iArrM99a[i24] * MapRenderer.tileSize, (byte) 17));
                        }
                    }
                    break;
                case 76:
                    int i26 = i16 + 1;
                    i16 = i26 + 1;
                    entityPool = new Sprite[iArrM99a[i26]];
                    System.out.println("Read poles: " + entityPool.length);
                    for (int i27 = 0; i27 < entityPool.length; i27++) {
                        int i28 = i16;
                        int i29 = i16 + 1;
                        int i30 = iArrM99a[i28];
                        i16 = i29 + 1;
                        int i31 = iArrM99a[i29];
                        if (tileOccupancy[i31][i30] != null && (tileOccupancy[i31][i30].type == 43 || tileOccupancy[i31][i30].type == 44)) {
                            entityPool[i27] = tileOccupancy[i31][i30];
                        }
                    }
                    break;
                default:
                    i16++;
                    break;
            }
        }
        initWeather();
        System.out.println("entity: " + entityCount);
        cameraTarget = player;
        setCameraTarget(player.x, player.y);
        cameraX = rainDropCount;
        cameraY = snowCount;
        switch (MissionData.getMissionData(musicTrackId)[MissionData.IDX_MISSION_TYPE]) {
            case 0:
                MissionState.missionType = 1;
                System.out.println("kill all");
                MissionState.bonusReward = 0;
                break;
            case 1:
                MissionState.countdown = 4500;
                Train train = new Train();
                missionObjective = train;
                addEntity((Sprite) train);
                spawnPickup(1272, 960, (byte) 6);
                MissionScript.nextPhase(1);
                MissionState.missionType = 10;
                MissionState.bonusReward = 50000;
                break;
            case 2:
                WarShip warShip = new WarShip(1944, 144);
                missionObjective = warShip;
                warShip.faction = (byte) -1;
                addEntity(missionObjective);
                MissionState.missionType = 11;
                MissionState.bonusReward = 20000;
                break;
            case 3:
                WarShip warShip2 = new WarShip(624, 336);
                missionObjective = warShip2;
                warShip2.velocityX = 0;
                missionObjective.faction = (byte) -1;
                addEntity(missionObjective);
                MissionState.missionType = 14;
                MissionState.bonusReward = 30000;
                break;
            case 4:
                MissionState.missionType = 12;
                BigBoss bigBoss = new BigBoss(100, 2160);
                missionObjective = bigBoss;
                bigBoss.faction = (byte) -1;
                addEntity(missionObjective);
                cameraTarget = null;
                MissionState.scrollLimit = player.y - 100;
                MissionState.bonusReward = 100000;
                break;
            case 5:
                MissionState.missionType = 16;
                MissionState.bonusReward = 5000;
                break;
            case 6:
                MissionScript.nextPhase(5);
                MissionState.missionType = 17;
                MissionState.bonusReward = 15000;
                break;
            case 7:
                setWaypoint(360, 96);
                missionObjective = addEntity((Sprite) new VIP(192, 2376, 360, 96));
                MissionScript.nextPhase(6);
                MissionState.missionType = 15;
                MissionState.bonusReward = 10000;
                break;
            case 8:
                setWaypoint(360, 120);
                missionObjective = addEntity((Sprite) new VIP(144, 2856, 360, 120));
                MissionScript.nextPhase(6);
                MissionState.missionType = 15;
                MissionState.bonusReward = 10000;
                break;
            case 9:
                setWaypoint(456, 2352);
                missionObjective = addEntity((Sprite) new VIP(144, 48, 456, 2352));
                MissionScript.nextPhase(6);
                MissionState.missionType = 15;
                MissionState.bonusReward = 10000;
                break;
            default:
                MissionState.missionType = 0;
                break;
        }
        ResourceManager.loadEffectSprites();
        AudioManager.loadMusic("/xf" + iArrM99a[MissionData.IDX_MUSIC] + ".mid");
        missionActive = false;
    }
    private static void initWeather() {
        if (hasRain) {
            int i = (viewportWidth * viewportHeight) / 758;
            rainX = new short[i];
            rainY = new short[i];
            rainZ = new short[i];
            rainPrevX = new short[i];
            rainPrevY = new short[i];
            for (int i2 = 0; i2 < rainX.length; i2++) {
                rainX[i2] = (short) Math.abs(ResourceManager.randomInt(viewportWidth));
                rainY[i2] = (short) Math.abs(ResourceManager.randomInt(viewportHeight));
                rainZ[i2] = (short) Math.abs(ResourceManager.randomInt(80));
            }
        }
        if (hasSnow) {
            int i3 = (viewportWidth * viewportHeight) / 758;
            rainX = new short[i3];
            rainY = new short[i3];
            rainZ = new short[i3];
            for (int i4 = 0; i4 < rainX.length; i4++) {
                rainX[i4] = (short) Math.abs(ResourceManager.randomInt(viewportWidth));
                rainY[i4] = (short) Math.abs(ResourceManager.randomInt(viewportHeight));
                rainZ[i4] = (short) Math.abs(ResourceManager.randomInt(80));
            }
        }
        if (hasClouds) {
            int i5 = (MapRenderer.mapWidth * MapRenderer.mapHeight) / 36;
            cloudCount = i5;
            cloudX = new short[i5];
            cloudY = new short[cloudCount];
            cloudZ = new short[cloudCount];
            for (int i6 = 0; i6 < cloudCount; i6 += 3) {
                cloudX[i6] = (short) Math.abs(ResourceManager.randomInt(MapRenderer.mapPixelWidth));
                cloudY[i6] = (short) Math.abs(ResourceManager.randomInt(MapRenderer.mapPixelHeight));
                cloudZ[i6] = (short) (80 + ResourceManager.randomInt(20));
                for (int i7 = 1; i7 < 3 && i6 + i7 < cloudCount; i7++) {
                    cloudX[i6 + i7] = (short) (ResourceManager.randomInt(40) + cloudX[i6]);
                    cloudY[i6 + i7] = (short) (ResourceManager.randomInt(20) + cloudY[i6]);
                    cloudZ[i6 + i7] = (short) (80 + ResourceManager.randomInt(20));
                }
            }
        }
    }
    public static Sprite addEntity(Sprite entity) {
        if (entityCount >= entities.length) {
            System.out.println("full entity");
            return null;
        }
        Sprite[] entityArr = entities;
        int i = entityCount;
        entityCount = i + 1;
        entityArr[i] = entity;
        return entity;
    }
    public static void spawnEffect(byte b, int i, int i2, int i3, int i4, int i5) {
        int i6 = 0;
        int i7 = 20;
        switch (b) {
            case 0:
            case 1:
            case 6:
                i6 = 40;
                i7 = 50;
                break;
        }
        for (int i8 = i6; i8 < i7; i8++) {
            if (effects[i8].hidden) {
                effects[i8].initEffect(b, i, i2, i3, i4, i5);
                return;
            }
        }
    }
    public static void spawnPlayerDeath(int i, int i2) {
        if (checkLineOfSight(i, i2, 0, 0)) {
            spawnEffect((byte) 0, i + ResourceManager.randomInt(6), i2 + ResourceManager.randomInt(6), 0, 0, 0);
            spawnEffect((byte) 0, i + ResourceManager.randomInt(12), i2 + ResourceManager.randomInt(12), 0, 0, 6 + ResourceManager.randomInt(3));
            spawnEffect((byte) 0, i + ResourceManager.randomInt(12), i2 + ResourceManager.randomInt(12), 0, 0, 15 + ResourceManager.randomInt(3));
        }
    }
    public static void spawnFloatingText(String str, int i, int i2) {
        for (int length = effects.length - 1; length >= 0; length--) {
            if (effects[length].hidden) {
                switch (rainDropIndex) {
                    case 0:
                        effects[length].initText(str, i, i2, -2048, -2048);
                        break;
                    case 1:
                        effects[length].initText(str, i, i2, 2048, -2048);
                        break;
                    case 2:
                        effects[length].initText(str, i, i2, 2048, 2048);
                        break;
                    case 3:
                        effects[length].initText(str, i, i2, -2048, 2048);
                        break;
                }
                rainDropIndex = (rainDropIndex + 1) % 4;
                return;
            }
        }
    }
    public static Bullet spawnBullet(byte b, int i, int i2, int i3, Sprite entity) {
        for (int length = playerBullets.length - 1; length >= 0; length--) {
            if (playerBullets[length].hidden) {
                if (b == 10 || b == 11) {
                    AudioManager.playSfxMax(2);
                }
                playerBullets[length].init(b, i, i2, i3, entity);
                return playerBullets[length];
            }
        }
        return null;
    }
    public static Bullet spawnLandmine(int i, int i2) {
        for (int length = landmines.length - 1; length >= 0; length--) {
            if (landmines[length] == null) {
                landmines[length] = new Bullet((byte) 18, i, i2);
                return landmines[length];
            }
        }
        return null;
    }
    public static void spawnPickup(int i, int i2, byte b) {
        if (pickupCount < pickups.length) {
            PickupItem[] pickupsArr = pickups;
            int i3 = pickupCount;
            pickupCount = i3 + 1;
            pickupsArr[i3] = new PickupItem(i, i2, b);
        }
    }

    @Override
    public final void paint(Graphics graphics) {
        graphics.setColor(8947848);
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        graphics.setClip(0, 0, GameCanvas.screenWidth, GameCanvas.screenHeight);
        if (loadingScreen) {
            graphics.setColor(0);
            graphics.fillRect(0, 0, GameCanvas.screenWidth, GameCanvas.screenHeight);
            if (ResourceManager.backgroundImage != null) {
                graphics.drawImage(ResourceManager.backgroundImage, 0, 0, 0);
            }
            if (this.title != null) {
                ResourceManager.fontLarge.drawString(this.title, GameCanvas.screenWidth >> 1, (GameCanvas.screenHeight >> 1) - 50, 2, graphics);
            }
            if (this.subtitle != null) {
                (GameCanvas.screenWidth >= 240 ? ResourceManager.fontMedium : ResourceManager.fontSmall).drawString(this.subtitle, (GameCanvas.screenWidth - (GameCanvas.screenWidth >= 240 ? 160 : 120)) >> 1, (GameCanvas.screenHeight >> 1) - 20, 0, graphics);
            }
            if (missionActive) {
                ResourceManager.fontHud.drawString(Localization.txtLoading, GameCanvas.screenWidth >> 1, (GameCanvas.screenHeight >> 1) + 50, 2, graphics);
            } else if (playTime < 4) {
                ResourceManager.fontHud.drawString(Localization.txtPressAnyKey, GameCanvas.screenWidth >> 1, (GameCanvas.screenHeight >> 1) + 50, 2, graphics);
            }
            int i = playTime + 1;
            playTime = i;
            if (i >= 8) {
                playTime = 0;
                return;
            }
            return;
        }
        if (gameOverAnimating) {
            graphics.setColor(0);
            graphics.drawLine(0, gameOverAnimPos, GameCanvas.screenWidth, gameOverAnimPos);
            graphics.drawLine(0, gameOverAnimPos + 2, GameCanvas.screenWidth, gameOverAnimPos + 2);
            graphics.drawLine(0, gameOverAnimPos + 4, GameCanvas.screenWidth, gameOverAnimPos + 4);
            graphics.drawLine(0, gameOverAnimPos + 6, GameCanvas.screenWidth, gameOverAnimPos + 6);
            graphics.drawLine(0, gameOverAnimPos + 8, GameCanvas.screenWidth, gameOverAnimPos + 8);
            graphics.drawLine(0, gameOverAnimEnd - gameOverAnimPos, GameCanvas.screenWidth, gameOverAnimEnd - gameOverAnimPos);
            graphics.drawLine(0, (gameOverAnimEnd - gameOverAnimPos) - 2, GameCanvas.screenWidth, (gameOverAnimEnd - gameOverAnimPos) - 2);
            graphics.drawLine(0, (gameOverAnimEnd - gameOverAnimPos) - 4, GameCanvas.screenWidth, (gameOverAnimEnd - gameOverAnimPos) - 4);
            graphics.drawLine(0, (gameOverAnimEnd - gameOverAnimPos) - 6, GameCanvas.screenWidth, (gameOverAnimEnd - gameOverAnimPos) - 6);
            graphics.drawLine(0, (gameOverAnimEnd - gameOverAnimPos) - 8, GameCanvas.screenWidth, (gameOverAnimEnd - gameOverAnimPos) - 8);
            int i2 = gameOverAnimPos + 10;
            gameOverAnimPos = i2;
            if (i2 >= gameOverAnimEnd) {
                gameOverAnimating = false;
                return;
            }
            return;
        }
        graphics.setClip(0, 0, viewportWidth, viewportHeight);
        if (screenShake > 0) {
            graphics.translate((-cameraX) + shakeX[screenShake % shakeX.length], (-cameraY) + shakeY[screenShake % shakeY.length]);
            screenShake--;
        } else {
            graphics.translate(-cameraX, -cameraY);
        }
        MapRenderer.renderTiles(graphics);
        for (int length = effects.length - 1; length >= 0; length--) {
            if (!effects[length].hidden && effects[length].effectSubType == 0) {
                effects[length].render(graphics);
            }
        }
        for (int length2 = landmines.length - 1; length2 >= 0; length2--) {
            if (landmines[length2] != null) {
                landmines[length2].render(graphics);
            }
        }
        for (int i3 = 0; i3 < pickupCount; i3++) {
            if (isInViewport(pickups[i3])) {
                pickups[i3].drawPickup(graphics);
            }
        }
        for (int i4 = 0; i4 < entityCount; i4++) {
            if (entities[i4].layer == 0 && isInViewport((GameEntity) entities[i4])) {
                entities[i4].draw(graphics);
            }
        }
        for (int length3 = playerBullets.length - 1; length3 >= 0; length3--) {
            if (!playerBullets[length3].hidden) {
                playerBullets[length3].render(graphics);
            }
        }
        for (int i5 = 0; i5 < entityCount; i5++) {
            if (entities[i5].layer == 1 && isInViewport((GameEntity) entities[i5])) {
                entities[i5].draw(graphics);
            }
        }
        MapRenderer.renderTrees(graphics);
        if (entityPool != null) {
            int i6 = 0;
            int i7 = 0;
            int i8 = 0;
            int i9 = 0;
            graphics.setColor(0);
            for (int i10 = 0; i10 < entityPool.length; i10++) {
                if (entityPool[i10] != null) {
                    if (entityPool[i10].dead) {
                        entityPool[i10] = null;
                    } else {
                        entityPool[i10].drawX = (((entityPool[i10].x - GameEntity.cameraX) * entityPool[i10].z) / GameEntity.zScale) + entityPool[i10].x;
                        entityPool[i10].drawY = (((entityPool[i10].y - GameEntity.cameraY) * entityPool[i10].z) / GameEntity.zScale) + entityPool[i10].y;
                        int i11 = i6;
                        int i12 = i7;
                        int i13 = i8;
                        int i14 = i9;
                        if (entityPool[i10].type == 43) {
                            int i15 = entityPool[i10].drawX;
                            i6 = i15;
                            i8 = i15;
                            i7 = entityPool[i10].drawY - 9;
                            i9 = entityPool[i10].drawY + 9;
                        } else {
                            i6 = entityPool[i10].drawX - 9;
                            i8 = entityPool[i10].drawX + 9;
                            int i16 = entityPool[i10].drawY;
                            i7 = i16;
                            i9 = i16;
                        }
                        if (i10 > 0 && entityPool[i10 - 1] != null) {
                            graphics.drawLine(i6, i7, i11, i12);
                            graphics.drawLine(i8, i9, i13, i14);
                            graphics.drawLine(entityPool[i10].drawX, entityPool[i10].drawY, entityPool[i10 - 1].drawX, entityPool[i10 - 1].drawY);
                        }
                    }
                }
            }
        }
        for (int i17 = 0; i17 < entityCount; i17++) {
            if (entities[i17].layer == 2 && isInViewport((GameEntity) entities[i17])) {
                entities[i17].draw(graphics);
            }
        }
        for (int length4 = effects.length - 1; length4 >= 0; length4--) {
            if (!effects[length4].hidden && effects[length4].effectSubType == 1) {
                effects[length4].render(graphics);
            }
        }
        if (waypointVisible) {
            graphics.setColor(16711680);
            int i18 = waypointRadius - 2;
            waypointRadius = i18;
            if (i18 < 2) {
                waypointRadius = 24;
            }
            graphics.drawArc(waypointX - waypointRadius, waypointY - waypointRadius, waypointRadius << 1, waypointRadius << 1, 0, 360);
        }
        if (hasRain) {
            graphics.setColor(15658751);
            for (int i19 = 0; i19 < rainX.length; i19++) {
                int i20 = (((rainX[i19] - GameEntity.cameraX) * rainZ[i19]) / GameEntity.zScale) + rainX[i19];
                int i21 = (((rainY[i19] - GameEntity.cameraY) * rainZ[i19]) / GameEntity.zScale) + rainY[i19];
                if (rainZ[i19] < 80) {
                    graphics.drawLine(rainPrevX[i19], rainPrevY[i19], i20, i21);
                }
                short[] sArr = rainZ;
                int i22 = i19;
                sArr[i22] = (short) (sArr[i22] - 7);
                if (rainZ[i19] < 0) {
                    rainZ[i19] = 80;
                    rainX[i19] = (short) (cameraX + Math.abs(ResourceManager.randomInt(viewportWidth)));
                    rainY[i19] = (short) (cameraY + Math.abs(ResourceManager.randomInt(viewportHeight)));
                }
                rainPrevX[i19] = (short) i20;
                rainPrevY[i19] = (short) i21;
            }
        }
        if (hasSnow) {
            graphics.setColor(16777215);
            for (int length5 = rainX.length >> 2; length5 >= 0; length5--) {
                graphics.fillRect((((rainX[length5] - GameEntity.cameraX) * rainZ[length5]) / GameEntity.zScale) + rainX[length5], (((rainY[length5] - GameEntity.cameraY) * rainZ[length5]) / GameEntity.zScale) + rainY[length5], (rainZ[length5] >> 4) + 2, (rainZ[length5] >> 4) + 2);
                short[] sArr2 = rainZ;
                int i23 = length5;
                sArr2[i23] = (short) (sArr2[i23] - 2);
                short[] sArr3 = rainX;
                int i24 = length5;
                sArr3[i24] = (short) (sArr3[i24] + snowWindX);
                short[] sArr4 = rainY;
                int i25 = length5;
                sArr4[i25] = (short) (sArr4[i25] + snowWindY);
                if (rainZ[length5] < 0) {
                    rainZ[length5] = 80;
                    rainX[length5] = (short) (cameraX + Math.abs(ResourceManager.randomInt(viewportWidth)));
                    rainY[length5] = (short) (cameraY + Math.abs(ResourceManager.randomInt(viewportHeight)));
                    if (length5 == 0 && ResourceManager.randomInt(10) == 0) {
                        snowWindX = (short) ResourceManager.randomInt(2);
                        snowWindY = (short) ResourceManager.randomInt(2);
                    }
                }
            }
        }
        if (hasClouds) {
            for (int i26 = 0; i26 < cloudCount; i26++) {
                if (checkLineOfSight(cloudX[i26], cloudY[i26], 80, 80)) {
                    graphics.drawImage(ResourceManager.cloudImage, (((cloudX[i26] - GameEntity.cameraX) * cloudZ[i26]) / GameEntity.zScale) + cloudX[i26], (((cloudY[i26] - GameEntity.cameraY) * cloudZ[i26]) / GameEntity.zScale) + cloudY[i26], 0);
                }
                short[] sArr5 = cloudX;
                int i27 = i26;
                sArr5[i27] = (short) (sArr5[i27] - 1);
                if (cloudX[i26] < -80) {
                    cloudX[i26] = (short) MapRenderer.mapPixelWidth;
                }
            }
            int i28 = cloudAnim + 1;
            cloudAnim = i28;
            if (i28 == 4) {
                cloudAnim = 0;
            }
        }
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        if (MissionState.missionType == 15) {
            graphics.setColor(0);
            graphics.fillRect(4, 4, 34, 4);
            graphics.setColor(65280);
            graphics.fillRect(5, 5, (missionObjective.currentHp << 5) / missionObjective.maxHp, 2);
            if (MissionState.flag3) {
                ResourceManager.fontHud.drawString("LOSS", 4, 10, 0, graphics);
            }
        }
        if (MissionState.countdown > 0) {
            int i29 = MissionState.countdown / 15;
            ResourceManager.fontHud.drawString((i29 / 60) + ":" + (i29 % 60), 4, 4, 0, graphics);
        }
        if (hasHudPanel) {
            graphics.setClip(0, 0, GameCanvas.screenWidth, GameCanvas.screenHeight);
            drawHud(graphics);
        } else {
            drawMinimap(graphics);
        }
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
    }
    public static boolean rectContains(int i, int i2, int i3, int i4, int i5, int i6) {
        return i >= i3 && i2 >= i4 && i < i3 + i5 && i2 < i4 + i6;
    }

    @Override
    public final void update() {
        if (loadingScreen) {
            if (GameScreen.pointerState == 3) {
                onKeyPressed(0);
                GameScreen.pointerState = 0;
                return;
            }
            return;
        }
        if (gameOverTimer > 0) {
            if (gameOverTimer > 2) {
                gameOverTimer--;
            }
            if (gameOverTimer == 2) {
                gameOverAnimating = true;
                int i = GameCanvas.screenHeight;
                gameOverAnimEnd = i;
                if (i % 2 == 0) {
                    gameOverAnimEnd--;
                }
                gameOverAnimPos = 0;
                gameOverTimer--;
            }
            if (gameOverTimer == 1 && !gameOverAnimating) {
                gameOverTimer--;
                handleGameOver();
                return;
            }
            System.out.println("game over" + gameOverTimer);
        }
        if (GameScreen.inputState[6]) {
            GameCanvas.pauseMenu.show();
            GameScreen.resetInput();
            return;
        }
        if (GameScreen.inputState[7]) {
            switch (MissionScript.scriptPhase) {
                case 0:
                    break;
                default:
                    DialogBox.isVisible = false;
                    cameraTarget = player;
                    missionX = 0;
                    MissionScript.scriptPhase = 0;
                    break;
            }
            GameScreen.resetInput();
            return;
        }
        if (gameOverAnimating) {
            return;
        }
        if (missionX == 1) {
            if (GameScreen.inputState[0]) {
                missionParam -= 20;
            }
            if (GameScreen.inputState[1]) {
                missionParam += 20;
            }
            if (GameScreen.inputState[2]) {
                missionY -= 20;
            }
            if (GameScreen.inputState[3]) {
                missionY += 20;
            }
        }
        if (GameScreen.pointerState == 1) {
            GameScreen.resetInput();
            if (player != null) {
                if (!hasHudPanel || GameScreen.pointerY < hudY) {
                    int i2 = ((cameraY + GameScreen.pointerY) - player.y) - 12;
                    int i3 = ((cameraX + GameScreen.pointerX) - player.x) - 12;
                    if (Math.abs(i2) > Math.abs(i3)) {
                        if (i2 < 0) {
                            GameScreen.inputState[0] = true;
                        } else {
                            GameScreen.inputState[1] = true;
                        }
                    } else if (i3 < 0) {
                        GameScreen.inputState[2] = true;
                    } else {
                        GameScreen.inputState[3] = true;
                    }
                } else {
                    if (rectContains(GameScreen.pointerX, GameScreen.pointerY - hudY, 37, 6, 28, 16)) {
                        player.deployDynamite();
                    }
                    if (rectContains(GameScreen.pointerX, GameScreen.pointerY - hudY, 70, 6, 28, 16)) {
                        player.deployMine();
                    }
                    if (rectContains(GameScreen.pointerX, GameScreen.pointerY - hudY, 103, 6, 28, 16)) {
                        GameScreen.inputState[4] = true;
                    }
                }
            }
            GameScreen.pointerState = 2;
        }
        if (GameScreen.pointerState == 3) {
            GameScreen.pointerState = 0;
            GameScreen.resetInput();
        }
        GameEntity.cameraX = cameraX + (GameCanvas.screenWidth / 2);
        GameEntity.cameraY = cameraY + (GameCanvas.screenHeight / 2);
        if (MissionScript.scriptPhase == 0) {
            for (int length = playerBullets.length - 1; length >= 0; length--) {
                if (!playerBullets[length].hidden) {
                    playerBullets[length].update();
                }
            }
            for (int length2 = landmines.length - 1; length2 >= 0; length2--) {
                if (landmines[length2] != null) {
                    landmines[length2].update();
                    if (landmines[length2].dead) {
                        landmines[length2] = null;
                    }
                }
            }
            for (int i4 = entityCount - 1; i4 >= 0; i4--) {
                if (entities[i4].dead) {
                    Sprite[] entityArr = entities;
                    int i5 = entityCount - 1;
                    entityCount = i5;
                    entities[i4] = entityArr[i5];
                } else {
                    entities[i4].update();
                }
            }
            for (int i6 = pickupCount - 1; i6 >= 0; i6--) {
                if (player.collidesWith((GameEntity) pickups[i6]) && player.handlePickup(pickups[i6])) {
                    pickups[i6].dead = true;
                }
                if (pickups[i6].dead) {
                    PickupItem[] pickupsArr = pickups;
                    int i7 = pickupCount - 1;
                    pickupCount = i7;
                    pickups[i6] = pickupsArr[i7];
                }
            }
        }
        if (MissionState.missionType != 0) {
            switch (MissionState.missionType) {
                case 1:
                    if (enemiesKilled == totalEnemies) {
                        MissionState.missionType = 0;
                        triggerGameOver(true);
                    }
                    break;
                case 10:
                    if (MissionState.flag1 && !MissionState.flag2 && player.containsPoint(132, 132)) {
                        spawnBullet((byte) 17, 132, 132, 0, null);
                        MissionState.flag2 = true;
                        setWaypoint(12, 12);
                    }
                    if (MissionState.flag2 && MissionState.countdown > 2 && player.containsPoint(12, 12)) {
                        MissionState.countdown = 2;
                    }
                    if (MissionState.countdown > 1) {
                        if (MissionScript.scriptPhase == 0) {
                            MissionState.countdown--;
                        }
                    } else if (MissionState.countdown == 1) {
                        Fighter fighter = new Fighter(72, 1500, (byte) 12);
                        fighter.angle = 270;
                        fighter.velocityY = -6144;
                        addEntity((Sprite) fighter);
                        Fighter fighter2 = new Fighter(144, 1600, (byte) 12);
                        fighter2.angle = 270;
                        fighter2.velocityY = -6144;
                        addEntity((Sprite) fighter2);
                        MissionState.countdown--;
                    } else {
                        cameraTarget = missionObjective;
                        if (missionObjective.velocityY > -2048) {
                            missionObjective.velocityY -= 32;
                        } else {
                            player.dead = true;
                            MissionState.missionType = 102;
                        }
                    }
                    break;
                case 12:
                    MissionState.scrollLimit -= 2;
                    if (cameraY <= 100) {
                        MissionState.scrollLimit += 1920;
                        cameraY += 1920;
                        player.y += 1920;
                        missionObjective.y += 1920;
                        System.out.println("Loop map");
                    }
                    setCameraTarget(player.x, MissionState.scrollLimit);
                    player.y -= 2;
                    break;
                case 16:
                    if (player.y < 480 && missionObjective == null) {
                        EnemyTank bossTank = new EnemyTank(288, 168, (byte) 111);
                        missionObjective = bossTank;
                        addEntity((Sprite) bossTank);
                        MissionScript.nextPhase(4);
                    }
                    break;
            }
        }
        MissionScript.checkTrigger();
        if (missionX != 0) {
            setCameraTarget(missionY, missionParam);
        } else if (cameraTarget != null) {
            cameraTarget.followCamera();
        }
        if (cameraX == rainDropCount && cameraY == snowCount) {
            return;
        }
        snowX = (rainDropCount - cameraX) << 1;
        snowY = (snowCount - cameraY) << 1;
        snowV += snowX;
        cameraX += snowV >> 4;
        snowV &= 15;
        snowFall += snowY;
        cameraY += snowFall >> 4;
        snowFall &= 15;
    }
    public static void setCameraTarget(int i, int i2) {
        rainDropCount = i - (viewportWidth >> 1);
        snowCount = i2 - (viewportHeight >> 1);
        if (rainDropCount < 0) {
            rainDropCount = 0;
        }
        if (rainDropCount > MapRenderer.mapPixelWidth - viewportWidth) {
            rainDropCount = MapRenderer.mapPixelWidth - viewportWidth;
        }
        if (snowCount < 0) {
            snowCount = 0;
        }
        if (snowCount > MapRenderer.mapPixelHeight - viewportHeight) {
            snowCount = MapRenderer.mapPixelHeight - viewportHeight;
        }
    }

    @Override
    public final void onKeyPressed(int i) {
        if (loadingScreen) {
            if (missionActive) {
                return;
            }
            if (previousScreen != null) {
                previousScreen.show();
            }
            if (pendingDialog != null) {
                DialogBox.setText(pendingDialog, 3);
                pendingDialog = null;
            }
            loadingScreen = false;
            GameScreen.resetInput();
            return;
        }
        if (i == 48 || this.cheatBuffer.length() >= 10) {
            this.cheatBuffer = "";
        } else if (i > 48) {
            this.cheatBuffer += (i - 48);
        }
        if (this.cheatBuffer.endsWith("11373")) {
            player.currentHp = player.maxHp;
        }
        if (player == null || gameOverTimer != 0) {
            return;
        }
        if (i == GameCanvas.dynamicKey) {
            player.deployDynamite();
        }
        if (i == GameCanvas.mineKey) {
            player.deployMine();
        }
    }
    private static void drawHud(Graphics graphics) {
        if (ResourceManager.hudPanel == null) {
            ResourceManager.hudPanel = ResourceManager.loadImage("/panel.png");
        }
        graphics.translate(-graphics.getTranslateX(), hudY - graphics.getTranslateY());
        graphics.drawImage(ResourceManager.hudPanel, 0, 0, 0);
        if (player != null) {
            int i = (player.currentHp * 56) / player.maxHp;
            graphics.setColor(0);
            graphics.fillRect(i + 40, 30, 56 - i, 6);
            graphics.setColor(16711680);
            if (player.dynamiteCooldown > 0) {
                graphics.fillRect(56, 9, 5, 2);
            }
            if (player.mineCooldown > 0) {
                graphics.fillRect(89, 9, 5, 2);
            }
            if (player.fireCooldown > 0) {
                graphics.fillRect(122, 9, 5, 2);
            }
            ResourceManager.fontHud.drawString(String.valueOf(dynamiteCount), 43, 6, 0, graphics);
            ResourceManager.fontHud.drawString(String.valueOf(mineCount), 76, 6, 0, graphics);
        }
        for (int i2 = 0; i2 < entityCount; i2++) {
            if (!entities[i2].hidden && entities[i2].faction != 0) {
                if (entities[i2].faction == -1) {
                    graphics.setColor(65280);
                } else {
                    graphics.setColor(14869218);
                }
                int i3 = entities[i2].x - player.x;
                int i4 = entities[i2].y - player.y;
                if (Math.abs(i3) < 576 && Math.abs(i4) < 576) {
                    graphics.fillRect(164 + (i3 >> 5), 25 + (i4 >> 5), 2, 2);
                }
            }
        }
        if (waypointVisible) {
            int i5 = waypointX - player.x;
            int i6 = waypointY - player.y;
            if (i5 < -576) {
                i5 = -576;
            }
            if (i5 > 576) {
                i5 = 576;
            }
            if (i6 < -576) {
                i6 = -576;
            }
            if (i6 > 576) {
                i6 = 576;
            }
            graphics.setColor(16711680);
            graphics.drawRect(164 + (i5 >> 5), 25 + (i6 >> 5), 2, 2);
        }
        ResourceManager.fontHud.drawString((enemiesKilled * 100) / totalEnemies + "%", 140, 29, 1, graphics);
    }
    private static void drawMinimap(Graphics graphics) {
        graphics.translate(-graphics.getTranslateX(), -graphics.getTranslateY());
        graphics.setClip(0, 0, GameCanvas.screenWidth, GameCanvas.screenHeight);
        graphics.translate(0, hudY);
        if (player == null) {
            return;
        }
        int i = (player.currentHp * 40) / player.maxHp;
        graphics.setColor(0);
        graphics.fillRect(2, 38, 46, 8);
        graphics.setColor(16711680);
        graphics.fillRect(4, 40, i, 4);
        ResourceManager.itemSprite.drawFrame(4, 3, 24, 0, graphics);
        ResourceManager.fontHud.drawString(String.valueOf(dynamiteCount), 3, 24, 0, graphics);
        ResourceManager.itemSprite.drawFrame(5, 30, 24, 0, graphics);
        ResourceManager.fontHud.drawString(String.valueOf(mineCount), 30, 24, 0, graphics);
        graphics.setColor(16777215);
        graphics.drawLine(minimapCenterX - 2, minimapCenterY, minimapCenterX + 2, minimapCenterY);
        graphics.drawLine(minimapCenterX, minimapCenterY - 2, minimapCenterX, minimapCenterY + 2);
        graphics.drawRect(minimapCenterX - 22, minimapCenterY - 22, 44, 44);
        for (int i2 = 0; i2 < entityCount; i2++) {
            if (!entities[i2].hidden && entities[i2].faction != 0) {
                if (entities[i2].faction == -1) {
                    graphics.setColor(65280);
                } else {
                    graphics.setColor(14869218);
                }
                int i3 = entities[i2].x - player.x;
                int i4 = entities[i2].y - player.y;
                if (Math.abs(i3) < 576 && Math.abs(i4) < 576) {
                    graphics.fillRect(minimapCenterX + (i3 >> 5), 24 + (i4 >> 5), 2, 2);
                }
            }
        }
        if (waypointVisible) {
            int i5 = waypointX - player.x;
            int i6 = waypointY - player.y;
            if (i5 < -576) {
                i5 = -576;
            }
            if (i5 > 576) {
                i5 = 576;
            }
            if (i6 < -576) {
                i6 = -576;
            }
            if (i6 > 576) {
                i6 = 576;
            }
            graphics.setColor(16711680);
            graphics.drawRect((minimapCenterX + (i5 >> 5)) - 1, (24 + (i6 >> 5)) - 1, 2, 2);
        }
        ResourceManager.fontHud.drawString((enemiesKilled * 100) / totalEnemies + "%", minimapCenterX + 23, minimapCenterY + 12, 1, graphics);
    }
    public static boolean hasEnemyAt(int i, int i2) {
        return i >= 0 && i < MapRenderer.mapHeight && i2 >= 0 && i2 < MapRenderer.mapWidth && tileOccupancy[i][i2] != null && tileOccupancy[i][i2] != player && tileOccupancy[i][i2].faction == -1;
    }
    public static Sprite entityAt(int i, int i2) {
        if (i < 0 || i >= MapRenderer.mapHeight || i2 < 0 || i2 >= MapRenderer.mapWidth) {
            return null;
        }
        return tileOccupancy[i][i2];
    }
    public static void setTileOccupant(int i, int i2, Sprite entity) {
        if (i < 0 || i >= MapRenderer.mapHeight || i2 < 0 || i2 >= MapRenderer.mapWidth || tileOccupancy[i][i2] != null) {
            return;
        }
        tileOccupancy[i][i2] = entity;
        MapRenderer.tileHp[i][i2] = 1;
    }
    public static void clearTileOccupant(int i, int i2, Sprite entity) {
        if (i < 0 || i >= MapRenderer.mapHeight || i2 < 0 || i2 >= MapRenderer.mapWidth || tileOccupancy[i][i2] != entity) {
            return;
        }
        tileOccupancy[i][i2] = null;
        MapRenderer.resetTileHp(i, i2);
    }
    public static boolean isInViewport(GameEntity other) {
        return other.x + other.width >= cameraX && other.y + other.height >= cameraY && other.x <= cameraX + viewportWidth && other.y <= cameraY + viewportHeight;
    }
    private static boolean checkLineOfSight(int i, int i2, int i3, int i4) {
        return i + i3 >= cameraX && i2 + i4 >= cameraY && i <= cameraX + viewportWidth && i2 <= cameraY + viewportHeight;
    }
    public static void setWaypoint(int i, int i2) {
        waypointX = i;
        waypointY = i2;
        waypointVisible = true;
    }
    private static void cleanup() {
        for (int i = 0; i < entityCount; i++) {
            entities[i] = null;
        }
        entityCount = 0;
        for (int i2 = 0; i2 < pickupCount; i2++) {
            pickups[i2] = null;
        }
        for (int i3 = 0; i3 < effects.length; i3++) {
            effects[i3].hidden = true;
        }
        for (int i4 = 0; i4 < playerBullets.length; i4++) {
            playerBullets[i4].hidden = true;
        }
        for (int i5 = 0; i5 < landmines.length; i5++) {
            landmines[i5] = null;
        }
        System.out.println("game scr toi day");
        pickupCount = 0;
        totalEnemies = 0;
        enemiesKilled = 0;
        entityPool = null;
        tileOccupancy = null;
        cloudZ = null;
        cloudY = null;
        cloudX = null;
        rainPrevY = null;
        rainPrevX = null;
        rainZ = null;
        rainY = null;
        rainX = null;
        System.out.println("game scr toi day");
        if (player != null) {
            vehicleUpgrades[currentVehicle][3] = (byte) ((player.currentHp * 100) / player.maxHp);
            vehicleUpgrades[currentVehicle][4] = (byte) ((mineCount * 10) + dynamiteCount);
        }
        System.out.println("game scr toi day");
        MapRenderer.freeTileResources();
        System.out.println("game scr toi day");
        ResourceManager.freeAllSprites();
        System.out.println("game scr toi day");
    }

    @Override
    public final void show() {
        super.show();
    }
    public static void triggerGameOver(boolean z) {
        System.out.println("gameOver be called");
        missionComplete = z;
        gameOverTimer = 50;
    }
    public final void handleGameOver() {
        if (!missionComplete) {
            this.title = Localization.txtMissionFail;
            previousScreen = GameCanvas.mainMenu;
            missionActive = true;
            loadingScreen = true;
            show();
            GameCanvas.instance.repaint();
            GameCanvas.instance.serviceRepaints();
            AudioManager.closeMusic();
            cleanup();
            ResourceManager.loadLevelAssets();
            ResourceManager.saveGame();
            AudioManager.loadMusic("/menu.mid");
            missionActive = false;
            return;
        }
        missionFlags[musicTrackId] = true;
        int i = (enemiesKilled * 100) + MissionState.bonusReward;
        this.title = Localization.txtMissionComplete;
        this.subtitle = "- " + Localization.lblEnemyKilled + enemiesKilled + "\n- " + Localization.lblXp + "+" + missionXp + "\n- " + Localization.lblCash + "+" + missionCash + "$\n- " + Localization.lblBonus + i + "$";
        missionCash += i;
        if (xp < 50000 && xp + missionXp >= 50000) {
            pendingDialog = Localization.txtUnlockVehicle;
        }
        if (xp < 200000 && xp + missionXp >= 200000) {
            pendingDialog = Localization.txtUnlockVehicle;
        }
        if (MissionData.getMissionData(musicTrackId)[MissionData.IDX_MISSION_TYPE] != 0) {
            if (MissionData.getMissionData(musicTrackId)[MissionData.IDX_MISSION_TYPE] == 4) {
                pendingDialog = Localization.txtNothingToUnlock;
            } else {
                pendingDialog = Localization.txtGreatMission;
            }
        }
        previousScreen = GameCanvas.mainMenu;
        missionActive = true;
        loadingScreen = true;
        show();
        GameCanvas.instance.repaint();
        GameCanvas.instance.serviceRepaints();
        AudioManager.closeMusic();
        System.out.println("Mission scr toi day");
        cleanup();
        System.out.println("Mission scr toi day");
        ResourceManager.loadLevelAssets();
        System.out.println("Mission scr toi day");
        cash += missionCash;
        xp += missionXp;
        System.out.println("save score...");
        ResourceManager.saveGame();
        AudioManager.loadMusic("/menu.mid");
        missionActive = false;
    }
}
