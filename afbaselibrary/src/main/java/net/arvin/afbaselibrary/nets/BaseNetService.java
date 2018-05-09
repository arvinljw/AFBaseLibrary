package net.arvin.afbaselibrary.nets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by arvinljw on 17/5/14 23:01
 * Function：
 * Desc：
 */
public class BaseNetService {
    private static Map<Object, ApiCallback> callbacks = new HashMap<>();

    protected static <T> void subscribe(Observable<T> observable, ApiCallback<T> callback) {
        addApi(callback);
        ioMain(observable).subscribe(callback);
    }

    private static void addApi(ApiCallback callback) {
        removeCallback(callback.obj);
        callbacks.put(callback.obj, callback);
    }

    private static <T> Observable<T> ioMain(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    private static void removeCallback(Object obj) {
        if (callbacks == null) {
            return;
        }
        if (callbacks.containsKey(obj)) {
            callbacks.get(obj).cancelCall();
            callbacks.remove(obj);
        }
    }

    public static void onDestroy(Object obj) {
        removeCallback(obj);
    }
}
