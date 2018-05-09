package net.arvin.sample.nets.converts;

import com.google.gson.Gson;

import net.arvin.afbaselibrary.utils.AFLog;
import net.arvin.sample.nets.callback.CallbackException;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by arvinljw on 17/5/14 23:54
 * Function：统一处理返回结果
 * Desc：
 */
class ResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type adapter;

    ResponseBodyConverter(Gson gson, Type adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @SuppressWarnings("DanglingJavadoc")
    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        try {
            AFLog.w(response);
            ResultEntity resultModel = gson.fromJson(response, ResultEntity.class);
            if (!resultModel.isError()) {
                if (resultModel.getResults() != null) {
                    return gson.fromJson(resultModel.getResults(), adapter);
                }
                return null;
            } else {
                /**
                 一般这里会带有服务器处理后返回的code，不同错误是不一样的，这里以{@link CallbackException.TOKEN_BAD}为例
                 */
                throw new CallbackException("获取数据失败", CallbackException.TOKEN_BAD);
            }
        } finally {
            value.close();
        }
    }
}
