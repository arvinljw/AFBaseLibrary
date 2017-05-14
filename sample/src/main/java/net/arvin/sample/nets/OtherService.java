package net.arvin.sample.nets;

import net.arvin.afbaselibrary.nets.ApiCallback;
import net.arvin.afbaselibrary.nets.BaseNetService;
import net.arvin.sample.entities.CategoryEntity;

import java.util.List;

/**
 * Created by arvinljw on 17/5/15 01:30
 * Function：
 * Desc：
 */
public class OtherService extends BaseNetService {
    private static OtherService mService;

    public static OtherService getInstance() {
        if (mService == null) {
            synchronized (OtherService.class) {
                if (mService == null) {
                    mService = new OtherService();
                }
            }
        }
        return mService;
    }

    public void getOtherApiData(String type, int page, int size, ApiCallback<List<CategoryEntity>> callback) {
        addCompositeSub(addSchedulers(GankNet.getInstance().getOtherApi().getOtherApiData(type, size, page)).subscribe(callback));
    }
}
