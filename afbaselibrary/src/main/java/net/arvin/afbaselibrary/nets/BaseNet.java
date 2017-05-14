package net.arvin.afbaselibrary.nets;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;

import net.arvin.afbaselibrary.utils.AFLog;
import net.arvin.afbaselibrary.utils.AFCertificateUtil;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by arvinljw on 17/5/12 17:01
 * Function：封装网络请求类
 * Desc：
 */
@SuppressWarnings("WeakerAccess")
public abstract class BaseNet<T> {
    private T mDefaultApi;
    private Class<T> mDefaultApiClazz;

    protected OkHttpClient mHttpClient;
    protected CallAdapter.Factory mRxJavaCallAdapterFactory;
    protected Converter.Factory mConverterFactory;

    protected Map<String, String> mHeaders;

    protected Map<Class, Object> mApis;

    @SuppressWarnings("unchecked")
    protected BaseNet() {
        mHeaders = new HashMap<>();
        mApis = new HashMap<>();
        //默认的api的class对象，因为用到了泛型所以这个值肯定是会随着T的改变而改变，所以没必要重写
        mDefaultApiClazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public T getDefaultApi() {
        if (mDefaultApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(getHttpClient())
                    .baseUrl(getDefaultBaseUrl())
                    .addConverterFactory(getDefaultConvertFactory())
                    .addCallAdapterFactory(getDefaultCallAdapterFactory())
                    .build();
            mDefaultApi = retrofit.create(mDefaultApiClazz);
        }
        return mDefaultApi;
    }

    /**
     * 默认使用Gson转换器，可以根据后台情况约定返回类型，并自定义转换器
     */
    protected Converter.Factory getDefaultConvertFactory() {
        if (mConverterFactory == null) {
            mConverterFactory = GsonConverterFactory.create();
        }
        return mConverterFactory;
    }

    /**
     * 默认使用rxjava call adapter，和{@link BaseNetService}结合使用，可避免rx引起的内存泄漏问题
     */
    protected CallAdapter.Factory getDefaultCallAdapterFactory() {
        if (mRxJavaCallAdapterFactory == null) {
            mRxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();
        }
        return mRxJavaCallAdapterFactory;
    }

    @SuppressWarnings("ConstantConditions")
    protected OkHttpClient getHttpClient() {
        if (mHttpClient == null) {
            OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder().addInterceptor(getDefaultInterceptor());
            if (isHttpsRequest()) {
                try {
                    clientBuilder.sslSocketFactory(AFCertificateUtil.setCertificates(getApplicationContext(), getCertificateNames()))
                            .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            mHttpClient = clientBuilder.build();
            makeGlideSupportHttps();
        }
        return mHttpClient;
    }

    /**
     * @return 返回默认请求拦截器
     */
    protected Interceptor getDefaultInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request;
                Request.Builder requestBuilder = chain.request().newBuilder();
                for (String key : mHeaders.keySet()) {
                    requestBuilder.addHeader(key, mHeaders.get(key));
                }
                request = requestBuilder.build();

                request = dealRequest(request);
                Response response = chain.proceed(request);
                dealResponse(response);
                return response;
            }
        };
    }

    /**
     * @param request 用于处理header意外的数据，例如对请求参数的处理
     * @return 返回处理后的request对象
     */
    protected Request dealRequest(Request request) {
        return request;
    }

    /**
     * @param response 对请求结果的处理，默认打印请求和返回信息
     */
    protected void dealResponse(Response response) {
        AFLog.d(response.request() + "\n" + response);
    }

    /**
     * 若是https请求，则还需要重写{@link #getCertificateNames()}和{@link #getApplicationContext()}方法
     */
    protected boolean isHttpsRequest() {
        return false;
    }

    /**
     * https请求证书名字，需要存放在assets文件夹中
     *
     * @return 返回证书的名字，例如：文件名.后缀名
     */
    protected String[] getCertificateNames() {
        return null;
    }

    /**
     * 用于https请求是加载证书所用
     *
     * @return 返回ApplicationContext即可
     */
    protected Context getApplicationContext() {
        return null;
    }

    /**
     * @param clazz 保证传入的参数是要获得的api的class
     * @return 返回api实例，方便调用接口
     */
    @SuppressWarnings("unchecked")
    protected <K> K getApi(Class<K> clazz) {
        if (mApis.containsKey(clazz)) {
            return (K) mApis.get(clazz);
        }
        K api = new Retrofit.Builder()
                .client(getHttpClient())
                .baseUrl(getDefaultBaseUrl())
                .addConverterFactory(mConverterFactory)
                .addCallAdapterFactory(getDefaultCallAdapterFactory())
                .build().create(clazz);
        mApis.put(clazz, api);
        return api;
    }

    protected void makeGlideSupportHttps() {
        if (!isHttpsRequest()) {
            return;
        }
        Glide.get(getApplicationContext()).register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(mHttpClient));
    }

    /**
     * @return 返回baseUrl，最好保证统一，注意一定要'/'符号结尾
     */
    protected abstract String getDefaultBaseUrl();

}
