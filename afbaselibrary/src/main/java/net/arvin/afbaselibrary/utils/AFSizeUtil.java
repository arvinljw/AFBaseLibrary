package net.arvin.afbaselibrary.utils;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.view.WindowManager;

import net.arvin.afbaselibrary.R;

/**
 * Created by arvin on 2016/2/2 16:42.
 * 屏幕尺寸相关方法
 */
@SuppressWarnings("all")
public class AFSizeUtil {
    private static WindowManager getWindowManager(Context context) {
        return (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }

    public static int getScreenWidth(Context context) {
        return getWindowManager(context).getDefaultDisplay().getWidth();
    }

    public static int getScreenHeight(Context context) {
        return getWindowManager(context).getDefaultDisplay().getHeight();
    }

    public static int px2dp(float pxValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dp2px(float dipValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2sp(float pxValue) {
        final float fontScale = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int sp2px(float spValue) {
        final float fontScale = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    private static final int[] APPCOMPAT_CHECK_ATTRS = {R.attr.colorPrimary};

    public static void checkAppCompatTheme(Context context) {
        TypedArray typedArray = context.obtainStyledAttributes(APPCOMPAT_CHECK_ATTRS);
        final boolean failed = !typedArray.hasValue(0);
        if (typedArray != null) {
            typedArray.recycle();
        }
        if (failed) {
            throw new IllegalArgumentException("You need to use a Theme.AppCompat theme (or descendant) with the design library.");
        }
    }

}
