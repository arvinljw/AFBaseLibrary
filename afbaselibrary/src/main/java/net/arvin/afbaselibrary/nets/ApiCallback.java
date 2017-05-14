package net.arvin.afbaselibrary.nets;

import android.net.ParseException;

import com.google.gson.JsonParseException;
import com.orhanobut.logger.Logger;


import org.json.JSONException;

import java.net.ConnectException;

import retrofit2.HttpException;
import rx.Subscriber;

/**
 * Created by arvinljw on 17/5/12 17:01
 * Function：预处理回调
 * Desc：这里提供的基本上是一个空实现，目的是告诉使用者，回调消息可以通过继承{@link Subscriber}实现预处理
 * 例如：重写{@link #onError(Throwable)}方法，与后台约定对应的错误码，统一处理登录，权限问题，网络问题等
 */
public abstract class ApiCallback<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {
    }

}
