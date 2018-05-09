package net.arvin.sample.nets;

import net.arvin.afbaselibrary.nets.ApiCallback;
import net.arvin.afbaselibrary.nets.BaseNetService;
import net.arvin.sample.entities.CategoryEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.imid.swipebacklayout.lib.Utils;

/**
 * Created by arvinljw on 17/5/14 23:40
 * Function：
 * Desc：
 */
public class GankService extends BaseNetService {

    public static void getData(String type, int page, int size, ApiCallback<List<CategoryEntity>> callback) {
        subscribe(GankNet.get().getData(type, size, page), callback);
    }

}
