package net.arvin.afbaselibrary.nets;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by arvinljw on 17/5/12 17:01
 * Function：预处理回调
 */
public abstract class ApiCallback<T> implements Observer<T> {
    public Object obj;
    private Disposable disposable;

    public ApiCallback(Object obj) {
        this.obj = obj;
        cancelCall();
    }

    public void cancelCall() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        disposable = d;
    }

    @Override
    public void onComplete() {

    }
}
