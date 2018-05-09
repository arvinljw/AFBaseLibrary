package net.arvin.sample.nets;

import net.arvin.sample.entities.CategoryEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by arvinljw on 17/5/15 01:27
 * Function：其他模块的接口
 * Desc：虽是其他模块，但是一般情况BaseUrl相同
 */
public interface OtherApi {
    /**
     * 分页获取分类数据(假装这个接口不一样，因为这个本身没有影响)
     *
     * @param type 请求数据类型 Android，IOS，福利，all
     * @param size 请求个数
     * @param page 请求页数
     * @return 返回分类数据列表
     */
    @GET("data/{type}/{size}/{page}")
    Observable<List<CategoryEntity>> getOtherApiData(@Path("type") String type, @Path("size") int size, @Path("page") int page);

}
