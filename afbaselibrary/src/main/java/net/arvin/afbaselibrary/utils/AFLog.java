package net.arvin.afbaselibrary.utils;

import com.orhanobut.logger.Logger;

import net.arvin.afbaselibrary.BuildConfig;

/**
 * Created by arvinljw on 17/5/11 16:38
 * Function：
 * Desc：
 */
public class AFLog {
    private static boolean DEBUG = BuildConfig.DEBUG;

    public static void w(String message) {
        if (!DEBUG) {
            return;
        }
        Logger.w(message);
    }
}
