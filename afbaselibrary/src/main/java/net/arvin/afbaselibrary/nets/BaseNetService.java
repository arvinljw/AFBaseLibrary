package net.arvin.afbaselibrary.nets;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by arvinljw on 17/5/14 23:01
 * Function：
 * Desc：
 */
public class BaseNetService {

    private Map<Class, CompositeSubscription> mCompositeSubscriptions;

    public BaseNetService() {
        mCompositeSubscriptions = new HashMap<>();
    }

    protected  <K> Observable<K> addSchedulers(Observable<K> observable) {
        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    protected void addCompositeSub(Subscription subscription) {
        CompositeSubscription compositeSubscription = mCompositeSubscriptions.get(getClass());
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
            mCompositeSubscriptions.put(getClass(), compositeSubscription);
        }
        compositeSubscription.add(subscription);
    }

    private void removeCompositeSub() {
        CompositeSubscription compositeSubscription;
        if (mCompositeSubscriptions != null && mCompositeSubscriptions.get(getClass()) != null) {
            compositeSubscription = mCompositeSubscriptions.get(getClass());
            compositeSubscription.unsubscribe();
            mCompositeSubscriptions.remove(getClass());
        }
    }

    public void onDestroy() {
        removeCompositeSub();
    }

}
