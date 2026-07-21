package xforce.entity;

import xforce.resource.ResourceManager;

import javax.microedition.lcdui.Graphics;

public final class PickupItem extends GameEntity {

    private static final int SPRITE_HALF      = 8;
    private static final int HIGHLIGHT_OFFSET = 8;
    private static final int ANIM_FLASH_FRAMES = 3;
    private static final int ANIM_CYCLE       = 6;
    private static final int DEFAULT_LIFETIME = 120;
    private static final byte TYPE_PERMANENT  = 7;

    public byte itemType;
    private byte animFrame;
    private byte lifetime;

    public PickupItem(int x, int y, byte type) {
        this.itemType = type;
        setBounds(x, y, 0, 0);
        this.lifetime = (byte) DEFAULT_LIFETIME;
    }

    public final void drawPickup(Graphics graphics) {
        if (this.animFrame < ANIM_FLASH_FRAMES) {
            ResourceManager.itemSprite.drawFrame(this.itemType, this.x - SPRITE_HALF, this.y - SPRITE_HALF, 0, graphics);
        } else {
            ResourceManager.itemSprite.drawFrame(this.itemType + HIGHLIGHT_OFFSET, this.x - SPRITE_HALF, this.y - SPRITE_HALF, 0, graphics);
        }
        this.animFrame = (byte) (this.animFrame + 1);
        if (this.animFrame >= ANIM_CYCLE) {
            this.animFrame = (byte) 0;
        }
        if (this.itemType != TYPE_PERMANENT) {
            this.lifetime = (byte) (this.lifetime - 1);
        }
        if (this.lifetime == 0) {
            this.dead = true;
        }
    }
}
