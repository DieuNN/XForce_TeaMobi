package xforce.entity;

public abstract class GameEntity {

    public static final int FIXED_POINT_BITS = 10;
    public static final int FIXED_POINT_SCALE = 1 << FIXED_POINT_BITS;
    public static final int FRACTIONAL_MASK = FIXED_POINT_SCALE - 1;

    public static int zScale = 100;
    public static int cameraX;
    public static int cameraY;
    public int x;
    public int y;
    public int z;
    public int width;
    public int height;
    public int velocityX;
    public int velocityY;
    public boolean hidden;
    public boolean dead;
    private int subPixelX;
    private int subPixelY;

    public final void setBounds(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
    }

    public final void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public final void setSize(int w, int h) {
        this.width = w;
        this.height = h;
    }

    public final void applyVelocity() {
        this.subPixelX += this.velocityX;
        this.x += this.subPixelX >> FIXED_POINT_BITS;
        this.subPixelX &= FRACTIONAL_MASK;
        this.subPixelY += this.velocityY;
        this.y += this.subPixelY >> FIXED_POINT_BITS;
        this.subPixelY &= FRACTIONAL_MASK;
    }

    public boolean collidesWith(GameEntity other) {
        return this.x + this.width > other.x
            && this.x < other.x + other.width
            && this.y + this.height > other.y
            && this.y < other.y + other.height;
    }

    public final boolean collidesWithTile(int tileX, int tileY, int tileW, int tileH) {
        return this.x + this.width > tileX
            && this.x < tileX + tileW
            && this.y + this.height > tileY
            && this.y < tileY + tileH;
    }

    public final boolean containsPoint(int px, int py) {
        return this.x + this.width > px
            && this.x < px
            && this.y + this.height > py
            && this.y < py;
    }

    public void update() {
    }
}
