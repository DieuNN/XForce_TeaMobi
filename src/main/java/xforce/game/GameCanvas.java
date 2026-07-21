package xforce.game;

import xforce.audio.AudioManager;
import xforce.resource.Localization;
import xforce.resource.ResourceManager;
import xforce.screen.ConfirmDialog;
import xforce.screen.CreditsScreen;
import xforce.screen.DialogBox;
import xforce.screen.GameScreen;
import xforce.screen.GarageScreen;
import xforce.screen.InfoScreen;
import xforce.screen.MainMenu;
import xforce.screen.OptionsScreen;
import xforce.screen.PauseMenu;
import xforce.screen.ShopScreen;
import xforce.screen.SplashScreen;
import xforce.screen.WorldMap;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

public final class GameCanvas extends Canvas implements Runnable {

    private static final int TARGET_FPS_MS   = 60;
    private static final int FONT_THRESHOLD  = 220;
    private static final int LABEL_MARGIN    = 8;
    private static final int SOFTKEY_AREA    = 16;
    private static final int SOFTKEY_WIDTH   = 48;

    private static final int POINTER_IDLE     = 0;
    private static final int POINTER_PRESS    = 1;
    private static final int POINTER_RELEASE  = 3;

    private static final int KEY_BIND_NONE    = 0;
    private static final int KEY_BIND_FIRE    = 1;
    private static final int KEY_BIND_DYNAMIC = 2;
    private static final int KEY_BIND_MINE    = 3;

    private static final int KEY_SOFT_LEFT   = -22;
    private static final int KEY_SOFT_RIGHT  = -7;
    private static final int KEY_SOFT_MIDDLE = -21;
    private static final int KEY_SOFT_MID2   = -6;
    private static final int KEY_NUM_0       = 48;
    private static final int KEY_NUM_1       = 49;
    private static final int KEY_NUM_3       = 51;

    public static GameCanvas instance;
    private static boolean running;
    private static boolean paused;

    public static int screenWidth;
    public static int screenHeight;
    public static GameScreen currentScreen;
    public static GameLevel gameLevel;
    public static MainMenu mainMenu;
    public static PauseMenu pauseMenu;
    public static OptionsScreen optionsScreen;
    public static CreditsScreen creditsScreen;
    public static WorldMap worldMap;
    public static InfoScreen infoScreen;
    public static ShopScreen shopScreen;
    public static GarageScreen garageScreen;
    public static ConfirmDialog confirmDialog;

    private long lastFrameTime;
    private long frameStartTime;
    public static int lastKeyCode;

    public static int fireKey = -5;
    public static int dynamicKey = KEY_NUM_1;
    public static int mineKey = KEY_NUM_3;
    public static int keyBindingMode;

    public GameCanvas() {
        setFullScreenMode(true);
        instance = this;
        running = true;
        ResourceManager.loadGame();
        Localization.init();
        ResourceManager.init();
        new SplashScreen().show();
        new Thread(this).start();
    }

    public static void initialize() {
        screenWidth = instance.getWidth();
        screenHeight = instance.getHeight();
        infoScreen = new InfoScreen();
        confirmDialog = new ConfirmDialog();
        gameLevel = new GameLevel();
        mainMenu = new MainMenu();
        pauseMenu = new PauseMenu();
        worldMap = new WorldMap();
        shopScreen = new ShopScreen();
        garageScreen = new GarageScreen();
        optionsScreen = new OptionsScreen();
        creditsScreen = new CreditsScreen();
        ResourceManager.loadLevelAssets();
        AudioManager.loadSounds();
    }

    public final void paint(Graphics g) {
        if (currentScreen != null) {
            currentScreen.paint(g);
            g.translate(-g.getTranslateX(), -g.getTranslateY());
            if (currentScreen.softLeftLabel != null) {
                if (screenWidth >= FONT_THRESHOLD) {
                    ResourceManager.fontMedium.drawString(currentScreen.softLeftLabel, LABEL_MARGIN, screenHeight - SOFTKEY_AREA, 0, g);
                } else {
                    ResourceManager.fontSmall.drawString(currentScreen.softLeftLabel, LABEL_MARGIN, screenHeight - SOFTKEY_AREA, 0, g);
                }
            }
            if (currentScreen.softRightLabel != null) {
                if (screenWidth >= FONT_THRESHOLD) {
                    ResourceManager.fontMedium.drawString(currentScreen.softRightLabel, screenWidth - LABEL_MARGIN, screenHeight - SOFTKEY_AREA, 1, g);
                } else {
                    ResourceManager.fontSmall.drawString(currentScreen.softRightLabel, screenWidth - LABEL_MARGIN, screenHeight - SOFTKEY_AREA, 1, g);
                }
            }
        }
        if (DialogBox.isVisible) {
            DialogBox.render(g);
        }
    }

    @Override
    public final void run() {
        this.frameStartTime = 0L;
        while (running) {
            this.lastFrameTime = System.currentTimeMillis();
            if (paused) {
                continue;
            }
            if (DialogBox.isVisible) {
                DialogBox.updateTypewriter();
            }
            if (currentScreen != null) {
                if (lastKeyCode != 0) {
                    if (!DialogBox.isVisible) {
                        currentScreen.onKeyPressed(lastKeyCode);
                    }
                    lastKeyCode = 0;
                }
                currentScreen.update();
            }
            repaint();
            serviceRepaints();

            this.frameStartTime = System.currentTimeMillis() - this.lastFrameTime;
            if (this.frameStartTime < TARGET_FPS_MS) {
                try {
                    Thread.sleep(TARGET_FPS_MS - this.frameStartTime);
                } catch (InterruptedException unused) {
                }
            }
        }
    }

    public static void pauseGame() {
        paused = true;
    }

    public static void resumeGame() {
        paused = false;
    }

    public static void stopGame() {
        running = false;
        ResourceManager.saveGame();
    }

    protected final void keyPressed(int keyCode) {
        if (keyBindingMode == KEY_BIND_FIRE) {
            fireKey = KEY_BIND_NONE;
        }
        if (keyBindingMode == KEY_BIND_DYNAMIC) {
            dynamicKey = KEY_BIND_NONE;
        }
        if (keyBindingMode == KEY_BIND_MINE) {
            mineKey = KEY_BIND_NONE;
        }
        switch (getGameAction(keyCode)) {
            case UP:
                GameScreen.inputState[0] = true;
                break;
            case LEFT:
                GameScreen.inputState[2] = true;
                break;
            case FIRE:
            case GAME_A:
            default:
                switch (keyCode) {
                    case -22:
                    case -7:
                        GameScreen.inputState[7] = true;
                        break;
                    case -21:
                    case -6:
                        GameScreen.inputState[6] = true;
                        break;
                    default:
                        if (keyCode == fireKey) {
                            GameScreen.inputState[4] = true;
                        }
                        if (keyBindingMode == KEY_BIND_FIRE) {
                            fireKey = keyCode;
                            if (dynamicKey == fireKey) {
                                dynamicKey = KEY_BIND_NONE;
                            }
                            if (mineKey == fireKey) {
                                mineKey = KEY_BIND_NONE;
                            }
                        }
                        if (keyBindingMode == KEY_BIND_DYNAMIC) {
                            dynamicKey = keyCode;
                            if (keyCode == fireKey) {
                                fireKey = KEY_BIND_NONE;
                            }
                            if (dynamicKey == mineKey) {
                                mineKey = KEY_BIND_NONE;
                            }
                        }
                        if (keyBindingMode == KEY_BIND_MINE) {
                            mineKey = keyCode;
                            if (keyCode == fireKey) {
                                fireKey = KEY_BIND_NONE;
                            }
                            if (mineKey == dynamicKey) {
                                dynamicKey = KEY_BIND_NONE;
                            }
                        }
                        break;
                }
                break;
            case RIGHT:
                GameScreen.inputState[3] = true;
                break;
            case DOWN:
                GameScreen.inputState[1] = true;
                break;
        }
        if (keyCode == KEY_SOFT_MIDDLE) {
            keyCode = KEY_SOFT_MID2;
        }
        if (keyCode == KEY_SOFT_LEFT) {
            keyCode = KEY_SOFT_RIGHT;
        }
        lastKeyCode = keyCode;
        if (keyBindingMode != KEY_BIND_NONE) {
            keyBindingMode = KEY_BIND_NONE;
            GameScreen.resetInput();
        }
    }

    protected final void keyReleased(int keyCode) {
        switch (getGameAction(keyCode)) {
            case UP:
                GameScreen.inputState[0] = false;
                break;
            case LEFT:
                GameScreen.inputState[2] = false;
                break;
            case FIRE:
            case GAME_A:
            default:
                if (keyCode == fireKey) {
                    GameScreen.inputState[4] = false;
                }
                switch (keyCode) {
                    case KEY_NUM_0:
                        GameScreen.inputState[5] = false;
                        break;
                }
                break;
            case RIGHT:
                GameScreen.inputState[3] = false;
                break;
            case DOWN:
                GameScreen.inputState[1] = false;
                break;
        }
    }

    protected final void pointerPressed(int x, int y) {
        if (y <= screenHeight - SOFTKEY_AREA) {
            GameScreen.pointerX = x;
            GameScreen.pointerY = y;
            GameScreen.pointerState = POINTER_PRESS;
        } else {
            if (x < SOFTKEY_WIDTH) {
                GameScreen.inputState[6] = true;
            }
            if (x > screenWidth - SOFTKEY_WIDTH) {
                GameScreen.inputState[7] = true;
            }
            GameScreen.pointerState = POINTER_IDLE;
        }
    }

    protected final void pointerDragged(int x, int y) {
        if (y > screenHeight - SOFTKEY_AREA) {
            GameScreen.pointerState = POINTER_IDLE;
            return;
        }
        GameScreen.pointerX = x;
        GameScreen.pointerY = y;
        GameScreen.pointerState = POINTER_PRESS;
    }

    protected final void pointerReleased(int x, int y) {
        if (y > screenHeight - SOFTKEY_AREA) {
            GameScreen.pointerState = POINTER_IDLE;
            return;
        }
        GameScreen.pointerX = x;
        GameScreen.pointerY = y;
        GameScreen.pointerState = POINTER_RELEASE;
    }
}
