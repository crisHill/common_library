package zls.app.library.util;

import android.content.Context;

import zls.app.library.MyApp;


/**
 * Created by zls on 2016/9/2.
 */
public class DimenUtil {


    /**
     * convert px to dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int px2dip(float pxValue) {
        return px2dip(MyApp.context, pxValue);
    }

    /**
     * convert dp to px
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
    public static int dip2px(float dipValue) {
        return dip2px(MyApp.context, dipValue);
    }

    /**
     * convert px to sp
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }
    public static int px2sp( float pxValue) {
        return px2sp(MyApp.context, pxValue);
    }

    /**
     * convert sp to px
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
    public static int sp2px( float spValue){
        return sp2px(MyApp.context, spValue);
    }

}
