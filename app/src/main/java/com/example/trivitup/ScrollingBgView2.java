package com.example.trivitup;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


public class ScrollingBgView2 extends View {
    private Bitmap bitmap;
    private Paint paint;
    private float offset;

    public ScrollingBgView2(Context context) {
        super(context);
        init();
    }

    public ScrollingBgView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        // Load the background image from the drawable resources
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.scrollable);

        // Create a Paint object for drawing the bitmap
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Calculate the height of the bitmap and the view
        int bitmapHeight = bitmap.getHeight();

        // Update the offset for scrolling
        offset += 1;
        if (offset > bitmapHeight) {
            offset -= bitmapHeight;
        }

        // Draw the bitmap at the calculated offset
        canvas.drawBitmap(bitmap, 0, -offset, paint);

        // Draw the bitmap again with an offset equal to the height of the bitmap
        canvas.drawBitmap(bitmap, 0, bitmapHeight - offset, paint);

        // Invalidate the view to cause a redraw
        invalidate();
    }

}


