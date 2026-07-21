package xforce.resource;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class BitmapFont {

    private static final int ANCHOR_TOP_LEFT = Graphics.TOP | Graphics.LEFT;
    private static final int ALIGN_LEFT   = 0;
    private static final int ALIGN_RIGHT  = 1;
    private static final byte CHAR_SPACE   = -2;
    private static final byte CHAR_NEWLINE = -3;

    private Image fontImage;
    private final String charMap;
    private byte[] charWidths;
    private int fontHeight;
    private final int charWidth;
    private int imageWidth;
    private int lastCharIndex;

    public BitmapFont(String filename, byte[] widthTable, int height, int defaultWidth, String charset) {
        try {
            this.fontImage = Image.createImage("/font/" + filename);
        } catch (Exception unused) {
        }
        this.charWidths = widthTable;
        this.fontHeight = height;
        this.charWidth = defaultWidth;
        this.charMap = charset;
        
        this.imageWidth = this.fontImage.getWidth();
        if (widthTable.length != charset.length()) {
            System.out.println("Font '" + filename + "' error!!! " + widthTable.length + "-" + charset.length());
        }
    }

    public final int measureByteString(byte[] encoded) {
        int w = 0;
        for (int i = 0; i < encoded.length; i++) {
            w += encoded[i] >= 0 ? this.charWidths[encoded[i]] : this.charWidth;
        }
        return w;
    }

    private int measureByteSubstring(byte[] encoded, int offset, int len) {
        int w = 0;
        int end = Math.min(offset + len, encoded.length);
        for (int i = offset; i < end && encoded[i] != CHAR_NEWLINE; i++) {
            w += encoded[i] >= 0 ? this.charWidths[encoded[i]] : this.charWidth;
        }
        return w;
    }

    public final int measureString(String text, int offset, int len) {
        int w = 0;
        int end = Math.min(offset + len, text.length());
        for (int i = offset; i < end; i++) {
            char ch = text.charAt(i);
            if (ch == '\n') break;
            w += getCharWidth(ch);
        }
        return w;
    }

    public final byte[] encodeString(String text) {
        byte[] encoded = new byte[text.length()];
        for (int i = 0; i < encoded.length; i++) {
            char ch = text.charAt(i);
            if (ch == ' ') {
                encoded[i] = CHAR_SPACE;
            } else if (ch == '\n') {
                encoded[i] = CHAR_NEWLINE;
            } else {
                encoded[i] = (byte) this.charMap.indexOf(ch);
            }
        }
        return encoded;
    }

    public void drawString(String text, int x, int y, int align, Graphics g) {
        drawSubstring(text, 0, text.length(), x, y, align, g);
    }

    public void drawSubstring(String text, int offset, int len, int x, int y, int align, Graphics g) {
        int end = Math.min(offset + len, text.length());
        int curX = align == ALIGN_LEFT ? x : align == ALIGN_RIGHT ? x - measureString(text, 0, len) : x - (measureString(text, 0, len) >> 1);
        int curY = y;
        for (int i = offset; i < end; i++) {
            char ch = text.charAt(i);
            if (ch == '\n') {
                int remaining = len - (i - offset) - 1;
                curX = align == ALIGN_LEFT ? x : align == ALIGN_RIGHT ? x - measureString(text, i + 1, remaining) : x - (measureString(text, i + 1, remaining) >> 1);
                curY += this.fontHeight;
            } else {
                int idx = findCharIndex(ch);
                if (idx >= 0) {
                    g.drawRegion(this.fontImage, 0, idx * this.fontHeight, this.imageWidth, this.fontHeight, 0, curX, curY, ANCHOR_TOP_LEFT);
                    curX += this.charWidths[idx];
                } else {
                    curX += this.charWidth;
                }
            }
        }
    }

    public final void drawChar(char ch, int x, int y, Graphics g) {
        this.lastCharIndex = findCharIndex(ch);
        if (this.lastCharIndex >= 0) {
            g.drawRegion(this.fontImage, 0, this.lastCharIndex * this.fontHeight, this.imageWidth, this.fontHeight, 0, x, y, 0);
        }
    }

    public final void drawEncoded(byte[] encoded, int x, int y, int align, Graphics g) {
        drawEncodedSubstring(encoded, 0, encoded.length, x, y, align, g);
    }

    public final void drawEncodedSubstring(byte[] encoded, int offset, int len, int x, int y, int align, Graphics g) {
        int end = Math.min(offset + len, encoded.length);
        int curX = align == ALIGN_LEFT ? x : align == ALIGN_RIGHT ? x - measureByteSubstring(encoded, 0, len) : x - (measureByteSubstring(encoded, 0, len) >> 1);
        int curY = y;
        for (int i = offset; i < end; i++) {
            if (encoded[i] == CHAR_NEWLINE) {
                int remaining = len - (i - offset) - 1;
                curX = align == ALIGN_LEFT ? x : align == ALIGN_RIGHT ? x - measureByteSubstring(encoded, i + 1, remaining) : x - (measureByteSubstring(encoded, i + 1, remaining) >> 1);
                curY += this.fontHeight;
            } else if (encoded[i] >= 0) {
                g.drawRegion(this.fontImage, 0, encoded[i] * this.fontHeight, this.imageWidth, this.fontHeight, 0, curX, curY, ANCHOR_TOP_LEFT);
                curX += this.charWidths[encoded[i]];
            } else {
                curX += this.charWidth;
            }
        }
    }

    public final int getFontHeight() {
        return this.fontHeight;
    }

    private int findCharIndex(char ch) {
        return this.charMap.indexOf(ch);
    }

    public final int getCharWidth(char ch) {
        this.lastCharIndex = findCharIndex(ch);
        return this.lastCharIndex != -1 ? this.charWidths[this.lastCharIndex] : this.charWidth;
    }
}
