package com.bdgw.cc.http;

import android.text.TextUtils;

import com.socks.library.KLog;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.goldze.common.http.HttpsUtils;
import me.goldze.common.http.cookie.CookieJarImpl;
import me.goldze.common.http.cookie.store.PersistentCookieStore;
import me.goldze.common.http.interceptor.BaseInterceptor;
import me.goldze.common.http.interceptor.CacheInterceptor;
import me.goldze.common.http.interceptor.logging.Level;
import me.goldze.common.http.interceptor.logging.LoggingInterceptor;
import me.goldze.common.utils.Utils;
import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author GuoFeng
 * @date :2019/1/15 21:04
 * @description: RetrofitClient封装单例类, 实现网络请求
 */
public class RetrofitClient {

    //超时时间
    private static final int DEFAULT_TIMEOUT = 20;
    //缓存时间
    private static final int CACHE_TIMEOUT = 10 * 1024 * 1024;
    //缓存路径
    private static final String CACHEPATH = "goldze_cache";
    //服务端根路径
    private static String BASEURL = ApiService.Api.Base_URL;

    //必须继承 BaseApplication，或者使用BaseApplication.setApplication
//    private static Context mContext = Utils.getContext();

//    private static OkHttpClient okHttpClient;
    private static Retrofit retrofit;
    private ApiService apiService;
    private Retrofit.Builder builder;

    private static RetrofitClient INSTANCE;

    private Cache cache;
    private File httpCacheDirectory;

    public static RetrofitClient getInstance() {
        if (INSTANCE == null) {
            synchronized (RetrofitClient.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RetrofitClient();
                }
            }
        }
        return INSTANCE;
    }

    private RetrofitClient() {
        this(BASEURL, null);
    }

    private RetrofitClient(String url, Map<String, String> headers) {
        if (TextUtils.isEmpty(url)) {
            url = BASEURL;
        }
        if (null == httpCacheDirectory) {
            httpCacheDirectory = new File(Utils.getContext().getCacheDir(), CACHEPATH);
        }
        try {
            if (null == cache) {
                cache = new Cache(httpCacheDirectory, CACHE_TIMEOUT);
            }
        } catch (Exception e) {
            KLog.e("Could not create http cache", e);
        }
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(new CookieJarImpl(new PersistentCookieStore(Utils.getContext())))
                .addInterceptor(new BaseInterceptor(headers))
                .addInterceptor(new CacheInterceptor(Utils.getContext()))
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .addInterceptor(new LoggingInterceptor
                        .Builder()//构建者模式
                        .loggable(true) //是否开启日志打印 BuildConfig.DEBUG
                        .setLevel(Level.BASIC) //打印的等级
                        .log(Platform.INFO) // 打印类型
                        .request("Request") // request的Tag
                        .response("Response")// Response的Tag
                        // TODO: 2019/1/22 添加token 
//                        .addHeader("X-ECAPI-Authorization","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjIxNjcsImV4cCI6MTU1MDY1ODQ0NiwicGxhdGZvcm0iOiJtb3ppbGxhIn0.aP1d7r-KkGiLPRHuoE1R8IrZ8cP5YDa3thSnhtO-Ico")
                        .addHeader("log-header", "I am the log request header.") // 添加打印头, 注意 key 和 value 都不能是中文
                        .build()
                ).connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(8, 15, TimeUnit.SECONDS))
                // 这里你可以根据自己的机型设置同时连接的个数和时间，我这里8个，和每个保持时间为10s
                .build();

        builder = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url);
        retrofit = builder.build();
        apiService = retrofit.create(ApiService.class);
    }

    public ApiService getApiService() {
        return apiService;
    }

    /**
     * change ApiBaseUrl
     *
     * @param newApiBaseUrl
     */
    public void changeApiBaseUrl(String newApiBaseUrl) {
        BASEURL = newApiBaseUrl;
        builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASEURL);
    }

    /**
     * create you ApiService
     *
     * @param service
     * @param <T>
     * @return
     */
    public <T> T create(final Class<T> service) {
        if (service == null) {
            throw new RuntimeException("Api service is null!");
        }
        return retrofit.create(service);
    }

    /**
     * /**
     * execute your customer API
     * For example:
     * ApiService service =
     * RetrofitClient.getInstance().create(ApiService.class);
     * <p>
     * RetrofitClient.getInstance()
     * .execute(service.lgon("name", "password"), subscriber)
     * * @param subscriber
     */
    public <T> T execute(Observable<T> observable, Observer<T> subscriber) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        return null;
    }

}
