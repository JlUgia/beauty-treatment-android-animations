package ugia.io.androidbeautytreatment.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import ugia.io.androidbeautytreatment.R;

/**
 * This class provides a straightforward way to add clipping and stroke to an #ImageView
 * <p/>
 * Created by joseluisugia on 1/28/14.
 */
public class ClippedImageView extends ImageView {

    private float cornerRadius;
    private Path path;
    private RectF rect;

    private Paint strokePaint;

    public ClippedImageView(Context context) {
        this(context, null);
    }

    public ClippedImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClippedImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        if (attrs == null) return;

        path = new Path();
        rect = new RectF();

        TypedArray attrsArray = context.obtainStyledAttributes(attrs, R.styleable.ClippedImageView, defStyle, 0);
        cornerRadius = attrsArray.getDimensionPixelSize(R.styleable.ClippedImageView_cornerRadius, 0);

        TypedArray bgAttrsArray = context.obtainStyledAttributes(attrs, R.styleable.BackgroundShape, defStyle, 0);
        int strokeColor = bgAttrsArray.getColor(R.styleable.BackgroundShape_backgroundStrokeColor, 0);
        int strokeWidth = bgAttrsArray.getDimensionPixelSize(R.styleable.BackgroundShape_backgroundStrokeSize, 0);

        if (strokeWidth > 0) {
            setupStrokePaint(strokeColor, strokeWidth);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH
                && Build.VERSION.SDK_INT <= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {

            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    private void setupStrokePaint(int color, int borderWidth) {

        strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        strokePaint.setStrokeJoin(Paint.Join.ROUND);
        strokePaint.setStrokeCap(Paint.Cap.ROUND);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setColor(color);
        strokePaint.setStrokeWidth(borderWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int width = getWidth();
        int height = getHeight();

        rect.set(getPaddingLeft(),
                getPaddingTop(),
                width - getPaddingRight(),
                height - getPaddingBottom());

        path.addRoundRect(rect, cornerRadius, cornerRadius, Path.Direction.CW);
        canvas.clipPath(path);

        super.onDraw(canvas);

        if (strokePaint != null) {
            canvas.drawCircle(width / 2, height / 2, (cornerRadius - strokePaint.getStrokeWidth() + 1) / 2,
                    strokePaint);
        }
    }
}