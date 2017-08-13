package cn.abtion.keyboardtea.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author abtion.
 * @since 17/8/13 17:51.
 * email caiheng@hrsoft.net
 */

public class SquareTextView extends TextView {

    private Paint mPaint1,mPaint2;

    public SquareTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint1=new Paint();
        mPaint1.setColor(Color.BLACK);
        mPaint1.setStyle(Paint.Style.FILL);

        mPaint2 = new Paint();
        mPaint2.setColor(Color.WHITE);
        mPaint2.setStyle(Paint.Style.FILL);
    }

    public SquareTextView(Context context) {
        super(context);
        mPaint1=new Paint();
        mPaint1.setColor(Color.BLACK);
        mPaint1.setStyle(Paint.Style.FILL);

        mPaint2 = new Paint();
        mPaint2.setColor(Color.WHITE);
        mPaint2.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint1);
        canvas.drawRect(10,10,getMeasuredWidth()-10,getMeasuredHeight()-10,mPaint2);
        canvas.save();
        canvas.translate(0,0);
        super.onDraw(canvas);
        canvas.restore();
    }
}
