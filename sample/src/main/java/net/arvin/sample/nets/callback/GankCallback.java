package net.arvin.sample.nets.callback;

import net.arvin.afbaselibrary.nets.ApiCallback;
import net.arvin.sample.nets.callback.CallbackException;

/**
 * Created by arvinljw on 17/5/15 01:11
 * Function：
 * Desc：
 */
public abstract class GankCallback<T> extends ApiCallback<T> {

    @Override
    public void onError(Throwable e) {
        if (e instanceof CallbackException) {
            CallbackException callbackException = (CallbackException) e;
            if (callbackException.getCode() == CallbackException.TOKEN_BAD) {
                reLogin();
            }
        }
        onCallbackError(e);
    }

    private void reLogin() {
        // TODO: 17/5/15 重新登录
    }

    public abstract void onCallbackError(Throwable e);
}
