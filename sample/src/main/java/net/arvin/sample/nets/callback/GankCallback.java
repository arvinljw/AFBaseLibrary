package net.arvin.sample.nets.callback;

import net.arvin.afbaselibrary.nets.ApiCallback;
import net.arvin.afbaselibrary.utils.AFLog;

/**
 * Created by arvinljw on 17/5/15 01:11
 * Function：
 * Desc：
 */
public abstract class GankCallback<T> extends ApiCallback<T> {
    public GankCallback(Object obj) {
        super(obj);
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof CallbackException) {
            CallbackException callbackException = (CallbackException) e;
            if (callbackException.getCode() == CallbackException.TOKEN_BAD) {
                reLogin();
            }
        }
        AFLog.w(e.getMessage());
    }

    private void reLogin() {

    }
}
