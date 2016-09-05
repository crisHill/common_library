package zls.app.library.util;

import android.graphics.Paint;

/**
 * Created by zls on 2016/9/1.
 */
public class TextUtil {

    public static int getFontHeight(float fontSize) {
        Paint paint = new Paint();
        paint.setTextSize(fontSize);
        Paint.FontMetrics fm = paint.getFontMetrics();
        return (int) Math.ceil(fm.descent - fm.ascent);
    }

    public static int getTextWidth(float fontSize, String text){
        Paint paint = new Paint();
        paint.setTextSize(fontSize);
        int width = (int) paint.measureText(text, 0, text.length());
        return width;
    }

}
