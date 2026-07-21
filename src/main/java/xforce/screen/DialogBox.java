package xforce.screen;

import xforce.game.GameCanvas;
import xforce.resource.BitmapFont;
import xforce.resource.ResourceManager;

import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class DialogBox {

    private static final int DIALOG_W    = 128;
    private static final int DIALOG_H    = 54;
    private static final int TEXT_MAX_W  = 120;
    private static final int MAX_LINES   = 4;
    private static final int ARROW_BLINK_ON  = 5;
    private static final int ARROW_BLINK_OFF = 10;

    private static final int COLOR_WHITE = 16777215;
    private static final int COLOR_BLACK = 0;

    public static boolean isVisible;
    private static int boxX;
    private static int boxY;
    private static int portraitX;
    private static int portraitY;
    private static String[] lines;
    private static int scrollOffset;
    private static int currentLine;
    private static int charIndex;
    private static boolean finished;
    private static int arrowBlink;
    private static Image portrait;

    public static void setText(String text, int faceId) {
        BitmapFont font = ResourceManager.fontDialog;
        Vector vector = new Vector();
        int pos = 0;
        int lineStart = 0;
        int lineWidth = 0;
        int lastSpace = -1;
        while (pos < text.length()) {
            char ch = text.charAt(pos);
            if (ch == ' ') {
                lastSpace = pos;
            }
            lineWidth += font.getCharWidth(ch);
            if (lineWidth > TEXT_MAX_W || ch == '\n') {
                if (ch != '\n' && lastSpace != -1) {
                    pos = lastSpace;
                }
                vector.addElement(text.substring(lineStart, pos));
                if (text.charAt(pos) == ' ' || ch == '\n') {
                    pos++;
                }
                lineStart = pos;
                lineWidth = 0;
                lastSpace = -1;
            } else {
                pos++;
            }
        }
        if (pos > lineStart) {
            vector.addElement(text.substring(lineStart, pos));
        }
        String[] wrapped = new String[vector.size()];
        for (int i = 0; i < wrapped.length; i++) {
            wrapped[i] = (String) vector.elementAt(i);
        }
        lines = wrapped;
        currentLine = 0;
        charIndex = 0;
        scrollOffset = 0;
        isVisible = true;
        boxX = 3;
        boxY = 33;
        portraitX = boxX + 115;
        portraitY = boxY - 30;
        if (GameCanvas.screenWidth <= 128) {
            boxY = 0;
            boxX = 0;
            portraitX = GameCanvas.screenWidth - 54;
            portraitY = GameCanvas.screenHeight - 70;
        }
        if (GameCanvas.screenWidth >= 240) {
            boxX = 30;
            boxY = 50;
            portraitX = boxX + 130;
            portraitY = boxY - 30;
        }
        portrait = ResourceManager.loadImage("/face" + faceId + ".png");
    }

    public static void updateTypewriter() {
        finished = false;
        if (GameScreen.pointerState == 1) {
            GameScreen.pointerState = 2;
        }
        if (GameScreen.pointerState == 3) {
            GameCanvas.lastKeyCode = 1;
            GameScreen.pointerState = 0;
        }
        int maxVisibleLines = MAX_LINES;
        if (charIndex < lines[scrollOffset + currentLine].length()) {
            charIndex++;
            if (GameCanvas.lastKeyCode != 0) {
                currentLine = maxVisibleLines - 1;
                if (scrollOffset + currentLine > lines.length - 1) {
                    currentLine = (lines.length - 1) - scrollOffset;
                }
                charIndex = lines[scrollOffset + currentLine].length();
            }
        } else if (scrollOffset + currentLine == lines.length - 1) {
            finished = true;
            if (GameCanvas.lastKeyCode != 0) {
                isVisible = false;
            }
        } else if (currentLine < maxVisibleLines - 1) {
            currentLine++;
            charIndex = 0;
        } else {
            finished = true;
            if (GameCanvas.lastKeyCode != 0) {
                scrollOffset += maxVisibleLines;
                currentLine = 0;
                charIndex = 0;
            }
        }
        GameScreen.resetInput();
        GameCanvas.lastKeyCode = 0;
    }

    public static void render(Graphics g) {
        g.drawImage(portrait, portraitX, portraitY, 0);
        g.setColor(COLOR_WHITE);
        g.fillRoundRect(boxX, boxY, DIALOG_W, DIALOG_H, 6, 6);
        g.setColor(COLOR_BLACK);
        g.drawRoundRect(boxX, boxY, DIALOG_W, DIALOG_H, 6, 6);
        g.drawImage(ResourceManager.dialogBackground, boxX + DIALOG_W, boxY + 16, 0);
        int y = boxY;
        for (int i = 0; i < currentLine; i++) {
            ResourceManager.fontDialog.drawString(lines[scrollOffset + i], boxX + 4, y, 0, g);
            y += ResourceManager.fontDialog.getFontHeight();
        }
        ResourceManager.fontDialog.drawSubstring(lines[scrollOffset + currentLine], 0, charIndex, boxX + 4, y, 0, g);
        if (finished) {
            if (arrowBlink < ARROW_BLINK_ON) {
                g.drawImage(ResourceManager.arrowIcon, boxX + 115, boxY + 45, 0);
            }
            arrowBlink++;
            if (arrowBlink > ARROW_BLINK_OFF) {
                arrowBlink = 0;
            }
        }
    }
}
