package net.arvin.sample.nets;

import net.arvin.afbaselibrary.nets.BaseNet;
import net.arvin.sample.nets.converts.ResultConvertFactory;

import retrofit2.Converter;

/**
 * Created by arvinljw on 17/5/14 23:28
 * Function：网络请求实现类
 * Desc：网络请求使用的例子
 */
class GankNet extends BaseNet<GankApi> {

    private static GankNet mNet;

    @Override
    protected String getDefaultBaseUrl() {
        return GankApi.BASE_URL;
    }

    @Override
    protected Converter.Factory getDefaultConvertFactory() {
        return ResultConvertFactory.create();
    }

    static GankNet getInstance() {
        if (mNet == null) {
            synchronized (BaseNet.class) {
                if (mNet == null) {
                    mNet = new GankNet();
                }
            }
        }
        return mNet;
    }

    /**
     * 其他模块的接口可以这样写，就能很好的区分开了
     * 若是其中配置有变，就需要自己根据需求参照{@link #getApi(Class)}并重写该方法
     */
    OtherApi getOtherApi(){
        return getApi(OtherApi.class);
    }
}
