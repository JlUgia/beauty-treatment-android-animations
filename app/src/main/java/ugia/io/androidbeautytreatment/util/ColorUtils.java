package ugia.io.androidbeautytreatment.util;

/**
 * Utils to play with colors seamlessly
 * <p/>
 * Created by joseluisugia on 11/02/15.
 */
public class ColorUtils {

    /**
     * This method applies alpha channel to a given color without it. Useful when playing with alpha channels on
     * #android.widget.TextView. Applying a color with alpha is significantly more performing that doing the
     * same at the view level
     * <p/>
     *
     * @param color
     * @param alpha
     * @return the final result with the desired alpha applied
     */
    public static int applyAlphaToColor(int color, float alpha) {
        return (int) (0xFF * alpha) << 24 | color & 0xFFFFFF;
    }
}
