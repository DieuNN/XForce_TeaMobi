package defpackage;

import java.util.Hashtable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/* JADX INFO: loaded from: XForce.jar:a.class */
public final class a {
    private Image a;
    private String b;
    private byte[] c;
    private int d;
    private int e;
    private int f;
    private int g;

    public a(String str, byte[] bArr, int i, int i2, String str2) {
        try {
            this.a = Image.createImage(new StringBuffer("/font/").append(str).toString());
        } catch (Exception unused) {
        }
        this.c = bArr;
        this.d = i;
        this.e = 4;
        this.b = str2;
        new Hashtable();
        this.f = this.a.getWidth();
        if (bArr.length != str2.length()) {
            System.out.println(new StringBuffer("Font '").append(str).append("' error!!! ").append(bArr.length).append("-").append(str2.length()).toString());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v4, types: [int] */
    /* JADX WARN: Type inference failed for: r1v8 */
    public final int a(byte[] bArr) {
        int i = 0;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            i += bArr[i2] >= 0 ? this.c[bArr[i2]] : this.e;
        }
        return i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v9, types: [int] */
    private int a(byte[] bArr, int i, int i2) {
        int i3 = 0;
        int i4 = i + i2;
        int length = i4;
        if (i4 > bArr.length) {
            length = bArr.length;
        }
        for (int i5 = i; i5 < length && bArr[i5] != -3; i5++) {
            i3 += bArr[i5] >= 0 ? this.c[bArr[i5]] : this.e;
        }
        return i3;
    }

    public final int a(String str, int i, int i2) {
        char cCharAt;
        int iA = 0;
        int i3 = i + i2;
        int length = i3;
        if (i3 > str.length()) {
            length = str.length();
        }
        for (int i4 = i; i4 < length && (cCharAt = str.charAt(i4)) != '\n'; i4++) {
            iA += a(cCharAt);
        }
        return iA;
    }

    public final byte[] a(String str) {
        byte[] bArr = new byte[str.length()];
        for (int i = 0; i < bArr.length; i++) {
            if (str.charAt(i) == ' ') {
                bArr[i] = -2;
            } else if (str.charAt(i) == '\n') {
                bArr[i] = -3;
            } else {
                bArr[i] = (byte) this.b.indexOf(str.charAt(i));
            }
        }
        return bArr;
    }

    public final void a(String str, int i, int i2, int i3, Graphics graphics) {
        a(str, 0, str.length(), i, i2, i3, graphics);
    }

    public final void a(String str, int i, int i2, int i3, int i4, int i5, Graphics graphics) {
        int i6 = i2 + 0;
        int length = i6;
        if (i6 > str.length()) {
            length = str.length();
        }
        int iA = i5 == 0 ? i3 : i5 == 1 ? i3 - a(str, 0, i2) : i3 - (a(str, 0, i2) >> 1);
        int i7 = i4;
        for (int i8 = 0; i8 < length; i8++) {
            char cCharAt = str.charAt(i8);
            if (cCharAt == '\n') {
                iA = i5 == 0 ? i3 : i5 == 1 ? i3 - a(str, i8 + 1, (i2 - i8) - 1) : i3 - (a(str, i8 + 1, (i2 - i8) - 1) >> 1);
                i7 += this.d;
            } else {
                int iB = b(cCharAt);
                if (iB >= 0) {
                    graphics.drawRegion(this.a, 0, iB * this.d, this.f, this.d, 0, iA, i7, 20);
                    iA += this.c[iB];
                } else {
                    iA += this.e;
                }
            }
        }
    }

    public final void a(char c, int i, int i2, Graphics graphics) {
        int iB = b(c);
        this.g = iB;
        if (iB >= 0) {
            graphics.drawRegion(this.a, 0, this.g * this.d, this.f, this.d, 0, i, i2, 0);
        }
    }

    public final void a(byte[] bArr, int i, int i2, int i3, Graphics graphics) {
        a(bArr, 0, bArr.length, i, i2, i3, graphics);
    }

    public final void a(byte[] bArr, int i, int i2, int i3, int i4, int i5, Graphics graphics) {
        int i6 = i2 + 0;
        int length = i6;
        if (i6 > bArr.length) {
            length = bArr.length;
        }
        int iA = i5 == 0 ? i3 : i5 == 1 ? i3 - a(bArr, 0, i2) : i3 - (a(bArr, 0, i2) >> 1);
        int i7 = i4;
        for (int i8 = 0; i8 < length; i8++) {
            if (bArr[i8] == -3) {
                iA = i5 == 0 ? i3 : i5 == 1 ? i3 - a(bArr, i8 + 1, (i2 - i8) - 1) : i3 - (a(bArr, i8 + 1, (i2 - i8) - 1) >> 1);
                i7 += this.d;
            } else if (bArr[i8] >= 0) {
                graphics.drawRegion(this.a, 0, bArr[i8] * this.d, this.f, this.d, 0, iA, i7, 20);
                iA += this.c[bArr[i8]];
            } else {
                iA += this.e;
            }
        }
    }

    public final int a() {
        return this.d;
    }

    private int b(char c) {
        return this.b.indexOf(c);
    }

    public final int a(char c) {
        this.g = b(c);
        return this.g != -1 ? this.c[this.g] : this.e;
    }
}
