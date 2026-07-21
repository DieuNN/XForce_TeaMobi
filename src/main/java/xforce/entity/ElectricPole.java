package xforce.entity;

import xforce.game.GameLevel;
import xforce.resource.ResourceManager;

import javax.microedition.lcdui.Graphics;

public final class ElectricPole extends Sprite {

    private static final byte SPRITE_TYPE_POLE = 42;
    private static final byte SPRITE_TYPE_FIGHTER_A = 11;
    private static final byte SPRITE_TYPE_FIGHTER_B = 12;

    private static final int FACTION_HOSTILE = -1;

    private static final int RADAR_RANGE      = 100;
    private static final int RADAR_CONE_HALF  = 5;
    private static final int RADAR_ANGLE_FOV  = 15;
    private static final int ROTATION_SPEED   = 3;

    private static final int HALF_POLE  = 8;
    private static final int HALF_PLAYER = 12;

    private static final int Z_ORDER = 20;
    private static final int HITBOX  = 16;

    private static final int SPAWN_OFFSET       = 300;
    private static final int SPAWN_COOLDOWN     = 100;
    private static final int ANIM_CYCLE         = 10;
    private static final int ANIM_TOGGLE        = 5;
    private static final int TRIG_SCALE         = 10;

    private static final int COLOR_WHITE  = 16777215;
    private static final int COLOR_RED    = 16711680;
    private static final int COLOR_GREEN  = 11141120;
    private static final int COLOR_CYAN   = 13421772;

    private int wireX1;
    private int wireY1;
    private int wireX2;
    private int wireY2;
    private int poleHalfSize;
    private int radarRange;

    public ElectricPole(int x, int y) {
        super(x + 3, y + 3, SPRITE_TYPE_POLE);
        this.radarRange = RADAR_RANGE;
        this.width = HITBOX;
        this.height = HITBOX;
        this.z = Z_ORDER;
        this.faction = (byte) FACTION_HOSTILE;
    }

    @Override
    public final void draw(Graphics g) {
        this.drawX = ((((this.x + HALF_POLE) - GameEntity.cameraX) * this.z) / GameEntity.zScale) + this.x + (this.width >> 1);
        this.drawY = ((((this.y + HALF_POLE) - GameEntity.cameraY) * this.z) / GameEntity.zScale) + this.y + (this.height >> 1);
        this.wireX1 = (this.x + this.drawX) >> 1;
        this.wireY1 = (this.y + this.drawY) >> 1;
        this.poleHalfSize = this.width / 2;
        g.setColor(COLOR_WHITE);
        g.drawLine(this.x, this.y, this.wireX1, this.wireY1 + this.poleHalfSize);
        g.drawLine(this.x, this.y + this.height, this.wireX1 + this.poleHalfSize, this.wireY1 + this.poleHalfSize);
        g.drawLine(this.x + this.width, this.y, this.wireX1, this.wireY1);
        g.drawLine(this.x + this.width, this.y + this.height, this.wireX1 + this.poleHalfSize, this.wireY1);
        g.setColor(COLOR_GREEN);
        g.drawRect(this.wireX1, this.wireY1, this.poleHalfSize, this.poleHalfSize);
        g.drawLine(this.x, this.y, this.drawX, this.drawY);
        g.drawLine(this.x + this.width, this.y, this.drawX, this.drawY);
        g.drawLine(this.x, this.y + this.height, this.drawX, this.drawY);
        g.drawLine(this.x + this.width, this.y + this.height, this.drawX, this.drawY);
        g.setColor(this.aimingAtPlayer ? COLOR_RED : COLOR_CYAN);
        this.wireX1 = this.x + HALF_POLE + ((ResourceManager.cos(ResourceManager.normalizeAngle(this.angle - RADAR_CONE_HALF)) * this.radarRange) >> TRIG_SCALE);
        this.wireY1 = this.y + HALF_POLE + ((ResourceManager.sin(ResourceManager.normalizeAngle(this.angle - RADAR_CONE_HALF)) * this.radarRange) >> TRIG_SCALE);
        this.wireX2 = this.x + HALF_POLE + ((ResourceManager.cos(ResourceManager.normalizeAngle(this.angle + RADAR_CONE_HALF)) * this.radarRange) >> TRIG_SCALE);
        this.wireY2 = this.y + HALF_POLE + ((ResourceManager.sin(ResourceManager.normalizeAngle(this.angle + RADAR_CONE_HALF)) * this.radarRange) >> TRIG_SCALE);
        g.drawLine(this.drawX, this.drawY, this.wireX1, this.wireY1);
        g.drawLine(this.drawX, this.drawY, this.wireX2, this.wireY2);
        g.drawLine(this.wireX1, this.wireY1, this.wireX2, this.wireY2);
        g.setColor(this.animTimer < ANIM_TOGGLE ? COLOR_GREEN : COLOR_RED);
        g.fillRect(this.drawX - 1, this.drawY - 1, 3, 3);
        this.animTimer++;
        if (this.animTimer > ANIM_CYCLE) {
            this.animTimer = 0;
        }
    }

    @Override
    public final void update() {
        this.angle = ResourceManager.normalizeAngle(this.angle - ROTATION_SPEED);
        if (this.attackCooldown > 0) {
            this.attackCooldown = (byte) (this.attackCooldown - 1);
        }
        if ((this.animTimer & 1) == 0) {
            int dx = (GameLevel.player.x + HALF_PLAYER) - (this.x + HALF_POLE);
            int dy = (GameLevel.player.y + HALF_PLAYER) - (this.y + HALF_POLE);
            this.aimingAtPlayer = false;
            if ((dx * dx) + (dy * dy) >= this.radarRange * this.radarRange || Math.abs(ResourceManager.angleDelta(ResourceManager.angleBetween(dx, dy), this.angle)) > RADAR_ANGLE_FOV) {
                return;
            }
            this.aimingAtPlayer = true;
            if (this.attackCooldown == 0) {
                if (ResourceManager.randomInt(2) == 0) {
                    GameLevel.addEntity((Sprite) new Fighter(this.x - SPAWN_OFFSET, this.y - SPAWN_OFFSET, SPRITE_TYPE_FIGHTER_B)).target = GameLevel.player;
                } else {
                    GameLevel.addEntity((Sprite) new Fighter(this.x - SPAWN_OFFSET, this.y - SPAWN_OFFSET, SPRITE_TYPE_FIGHTER_A)).target = GameLevel.player;
                }
                this.attackCooldown = (byte) SPAWN_COOLDOWN;
            }
        }
    }
}
