package net.arvin.sample.nets;

import net.arvin.afbaselibrary.nets.ApiCallback;
import net.arvin.afbaselibrary.nets.BaseNetService;
import net.arvin.sample.entities.CategoryEntity;

import java.util.List;

/**
 * Created by arvinljw on 17/5/14 23:40
 * Function：
 * Desc：
 */
public class GankService extends BaseNetService {

    private static GankService mService;

    public static GankService getInstance() {
        if (mService == null) {
            synchronized (GankService.class) {
                if (mService == null) {
                    mService = new GankService();
                }
            }
        }
        return mService;
    }

    public void getData(String type, int page, int size, ApiCallback<List<CategoryEntity>> callback) {
        addCompositeSub(addSchedulers(GankNet.getInstance().getDefaultApi().getData(type, size, page)).subscribe(callback));
    }


}
