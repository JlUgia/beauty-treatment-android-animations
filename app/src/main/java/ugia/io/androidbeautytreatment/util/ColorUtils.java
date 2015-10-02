package ugia.io.androidbeautytreatment.util;

/**
 * Created by joseluisugia on 11/02/15.
 */
public class ColorUtils {

    public static int applyAlphaToColor(int color, float alpha) {
        return (int) (0xFF * alpha) << 24 | color & 0xFFFFFF;
    }
}
