package net.arvin.afbaselibrary.utils;

import com.orhanobut.logger.Logger;

/**
 * Created by arvinljw on 17/5/11 16:38
 * Function：
 * Desc：
 */
public class AFLog {
    private static boolean DEBUG = true;

    public static void setDebug(boolean DEBUG) {
        AFLog.DEBUG = DEBUG;
    }

    public static void d(Object obj) {
        if (!DEBUG) {
            return;
        }
        Logger.d(obj);
    }

    public static void w(String message) {
        if (!DEBUG) {
            return;
        }
        Logger.w(message);
    }

    public static void d(String message, Object obj) {
        if (!DEBUG) {
            return;
        }
        Logger.d(message, obj);
    }

    public static void json(String json) {
        if (!DEBUG) {
            return;
        }
        Logger.json(json);
    }
}
