package xforce.entity;

import xforce.data.MissionState;
import xforce.game.GameLevel;
import xforce.map.MapRenderer;
import xforce.resource.ResourceManager;

import javax.microedition.lcdui.Graphics;
public final class VIP extends Sprite {

    private static final byte SPRITE_TYPE_VIP = 110;
    private static final byte FACTION_FRIEND  = 1;

    private static final int DIR_COUNT   = 4;
    private static final int ANGLES_PER_DIR = 90;

    private static final int TILE_SIZE   = 24;
    private static final int TILE_HALF   = 12;
    private static final int VIP_HP      = 30;
    private static final int VIP_XP      = 200;
    private static final int WALK_SPEED  = 2048;

    private static final int PATHFIND_RANGE_LONG  = 10;
    private static final int PATHFIND_RANGE_SHORT = 3;
    private static final int STUCK_PAUSE          = 50;

    private static final int MISSION_VIP_ESCORT   = 15;

    private static final byte EFFECT_DEBRIS    = 0;
    private static final byte EFFECT_EXPLOSION = 6;

    private int walkSpeed;
    private boolean[] passableDirs;
    private int stuckTimer;
    private byte[] dirPriority;
    private byte[] dirVectorsX;
    private byte[] dirVectorsY;
    private int destX;
    private int destY;

    public VIP(int x, int y, int destX, int destY) {
        super(x, y, SPRITE_TYPE_VIP);
        this.passableDirs = new boolean[DIR_COUNT];
        this.dirPriority = new byte[]{0, 1, 2, 3};
        this.dirVectorsX = new byte[]{1, 0, -1, 0};
        this.dirVectorsY = new byte[]{0, 1, 0, -1};
        setSize(TILE_SIZE, TILE_SIZE);
        this.walkSpeed = WALK_SPEED;
        this.maxHp = (short) VIP_HP;
        this.currentHp = (short) VIP_HP;
        this.xpReward = (short) VIP_XP;
        this.faction = FACTION_FRIEND;
        this.target = GameLevel.player;
        this.destX = destX;
        this.destY = destY;
    }

    @Override // p000.GameEntity
    public final void update() {
        if (GameLevel.gameOverTimer > 0) {
            return;
        }
        if (this.x % MapRenderer.tileSize == 0 && this.y % MapRenderer.tileSize == 0) {
            boolean z = false;
            int tileRow = this.y / MapRenderer.tileSize;
            int tileCol = this.x / MapRenderer.tileSize;
            if (this.stuckTimer > 1) {
                this.stuckTimer--;
            } else {
                GameLevel.clearTileOccupant(tileRow - this.dirVectorsY[this.direction], tileCol - this.dirVectorsX[this.direction], this);
                this.passableDirs[0] = !MapRenderer.isBlocked(tileRow, tileCol + 1);
                this.passableDirs[1] = !MapRenderer.isBlocked(tileRow + 1, tileCol);
                this.passableDirs[2] = !MapRenderer.isBlocked(tileRow, tileCol - 1);
                this.passableDirs[3] = !MapRenderer.isBlocked(tileRow - 1, tileCol);
                int distX = ((this.target.x + 12) / 24) - ((this.x + 12) / 24);
                int distY = ((this.target.y + 12) / 24) - ((this.y + 12) / 24);
                if (Math.abs(distX) < 10 && Math.abs(distY) < 10) {
                    if ((distX == 0 || distY == 0) && Math.abs(distX) < 3 && Math.abs(distY) < 3) {
                        this.velocityX = 0;
                        this.velocityY = 0;
                        z = true;
                        this.stuckTimer = 50;
                    } else {
                        for (int dirIndex = 0; dirIndex < 4; dirIndex++) {
                            if (this.passableDirs[this.dirPriority[dirIndex]]) {
                                switch (this.dirPriority[dirIndex]) {
                                    case 0:
                                        if (distX > 0) {
                                            z = true;
                                        }
                                        break;
                                    case 1:
                                        if (distY > 0) {
                                            z = true;
                                        }
                                        break;
                                    case 2:
                                        if (distX < 0) {
                                            z = true;
                                        }
                                        break;
                                    case 3:
                                        if (distY < 0) {
                                            z = true;
                                        }
                                        break;
                                }
                                if (z) {
                                    this.direction = this.dirPriority[dirIndex];
                                    this.velocityX = this.dirVectorsX[this.dirPriority[dirIndex]] * this.walkSpeed;
                                    this.velocityY = this.dirVectorsY[this.dirPriority[dirIndex]] * this.walkSpeed;
                                    this.angle = this.direction * 90;
                                    break;
                                }
                            }
                        }
                    }
                }
                if (!z) {
                    this.velocityX = 0;
                    this.velocityY = 0;
                }
                MissionState.flag3 = !z;
                if (this.velocityX != 0 || this.velocityY != 0) {
                    GameLevel.setTileOccupant(tileRow + this.dirVectorsY[this.direction], tileCol + this.dirVectorsX[this.direction], this);
                }
            }
        }
        applyVelocity();
        if (MissionState.missionType == MISSION_VIP_ESCORT && collidesWithTile(this.destX - TILE_SIZE, this.destY - TILE_SIZE, TILE_SIZE * 2, TILE_SIZE * 2)) {
            GameLevel.triggerGameOver(true);
        }
    }

    @Override // p000.Sprite
    public final void draw(Graphics graphics) {
        if (this.damageFlash) {
            this.damageFlash = false;
        } else {
            ResourceManager.vipSprite.drawFrame(this.direction, this.x, this.y, 0, graphics);
        }
    }

    @Override // p000.Sprite
    public final void onDeath() {
        int tileCol = this.x / MapRenderer.tileSize;
        int tileRow = this.y / MapRenderer.tileSize;
        GameLevel.clearTileOccupant(tileRow, tileCol, this);
        MapRenderer.setTileHp(tileRow, tileCol, 0);
        if (this.x % MapRenderer.tileSize != 0) {
            GameLevel.clearTileOccupant(tileRow, tileCol + 1, this);
        } else if (this.y % MapRenderer.tileSize != 0) {
            GameLevel.clearTileOccupant(tileRow + 1, tileCol, this);
        }
        GameLevel.clearTileOccupant(tileRow, tileCol, this);
        GameLevel.spawnEffect(EFFECT_DEBRIS, this.x + (this.width / 2), this.y + (this.height / 2), 0, 0, 0);
        GameLevel.spawnEffect(EFFECT_EXPLOSION, this.x + (this.width / 2), this.y + (this.height / 2), 0, 0, 0);
        GameLevel.triggerGameOver(false);
        this.dead = true;
    }
}
