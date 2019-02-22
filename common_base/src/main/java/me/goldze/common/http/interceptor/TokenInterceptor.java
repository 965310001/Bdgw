package me.goldze.common.http.interceptor;

import com.socks.library.KLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import me.goldze.common.constants.ARouterConfig;
import me.goldze.common.utils.ActivityToActivity;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author GuoFeng
 * @date : 2019/2/22 17:08
 * @description: Token失效的跳转
 */
public class TokenInterceptor implements Interceptor {

    private JSONObject jsonObject;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        /*KLog.i(response.body().string());*/
        try {
            jsonObject = new JSONObject(response.body().string());
            if (jsonObject.has("error_desc") &&
                    jsonObject.get("error_desc").toString().contains("Token 无效")) {
                failed();
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            KLog.i(e.toString());
        }
        Request newRequest = request.newBuilder()
                .method(request.method(), request.body())
                .url(request.url())
                .build();
        return chain.proceed(newRequest);
    }

    private void failed() {
        ActivityToActivity.toActivity(ARouterConfig.LOGINACTIVITY);
    }
}
