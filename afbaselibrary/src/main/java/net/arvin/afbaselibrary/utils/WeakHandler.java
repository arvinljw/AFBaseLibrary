package net.arvin.afbaselibrary.utils;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by arvinljw on 17/5/16 00:15
 * Function：避免内存泄露的Handler
 * Desc：使用弱引用
 */
public class WeakHandler extends Handler {

    private WeakReference<IWeakHandler> mHandler;

    public WeakHandler(IWeakHandler handler) {
        mHandler = new WeakReference<>(handler);
    }

    @Override
    public void handleMessage(Message msg) {
        if (mHandler != null) {
            IWeakHandler weakHandleInterface = mHandler.get();
            if (weakHandleInterface != null) {
                weakHandleInterface.handleMessage(msg);
            }
        }
    }

    public interface IWeakHandler {
        void handleMessage(Message msg);
    }
}
