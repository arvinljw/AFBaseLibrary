package net.arvin.sample.nets;

import net.arvin.sample.entities.CategoryEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by arvinljw on 17/5/14 23:29
 * Function：
 * Desc：
 */
public interface GankApi {

    String BASE_URL = "http://gank.io/api/";

    /**
     * 分页获取分类数据
     *
     * @param type 请求数据类型 Android，IOS，福利，all
     * @param size 请求个数
     * @param page 请求页数
     * @return 返回分类数据列表
     */
    @GET("data/{type}/{size}/{page}")
    Observable<List<CategoryEntity>> getData(@Path("type") String type, @Path("size") int size, @Path("page") int page);

}
