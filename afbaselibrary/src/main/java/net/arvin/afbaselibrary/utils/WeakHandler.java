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

    private WeakReference<IWeakHandler> mActivity;

    public WeakHandler(IWeakHandler activity) {
        mActivity = new WeakReference<>(activity);
    }

    @Override
    public void handleMessage(Message msg) {
        if (mActivity != null) {
            IWeakHandler weakHandleInterface = mActivity.get();
            if (weakHandleInterface != null) {
                weakHandleInterface.handleMessage(msg);
            }
        }
    }

    public interface IWeakHandler {
        void handleMessage(Message msg);
    }
}
