package com.porterduffxfermode;

import android.content.Context;
import android.graphics.*;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author weishukai
 * @date 2019/1/21   6:10 PM
 * @describe
 */
public class PorterDuffXfermodeView extends View {
    private Paint paint;
    private Bitmap srcBitmap, dstBitmap;
    private Xfermode xfermode;
    private RectF srcRectf, dstRectf;

    public PorterDuffXfermodeView(Context context) {
        super(context);
        init();
    }

    public PorterDuffXfermodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PorterDuffXfermodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        srcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.source);
        dstBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.destination);
        xfermode = new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY);
    }

    public void setXfermode(PorterDuff.Mode porterDuffMode) {
        this.xfermode = new PorterDuffXfermode(porterDuffMode);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (srcBitmap != null && dstBitmap != null) {
            canvas.drawColor(Color.WHITE);
            int saveCount = canvas.saveLayer(srcRectf, paint, Canvas.ALL_SAVE_FLAG);
            canvas.drawBitmap(dstBitmap, null, dstRectf, paint);
            paint.setXfermode(xfermode);
            canvas.drawBitmap(srcBitmap, null, srcRectf, paint);
            paint.setXfermode(null);
            canvas.restoreToCount(saveCount);
        } else {
            canvas.drawColor(Color.GRAY);
            int saveCount = canvas.saveLayer(srcRectf, paint, Canvas.ALL_SAVE_FLAG);
            paint.setColor(Color.YELLOW);
            canvas.drawCircle(getWidth() / 3, getHeight() / 3, getWidth() / 3, paint);
            paint.setXfermode(xfermode);
            paint.setColor(Color.BLUE);
            canvas.drawRect(getWidth() / 3, getHeight() / 3, getWidth() / 3 * 2, getWidth() / 3 * 2, paint);
            paint.setXfermode(null);
            canvas.restoreToCount(saveCount);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int width = w <= h ? w : h;
        int centerX = w / 2;
        int centerY = h / 2;
        int quarterWidth = width / 4;
        srcRectf = new RectF(centerX - quarterWidth, centerY - quarterWidth, centerX + quarterWidth, centerY + quarterWidth);
        dstRectf = new RectF(centerX - quarterWidth, centerY - quarterWidth, centerX + quarterWidth, centerY + quarterWidth);
    }
}
