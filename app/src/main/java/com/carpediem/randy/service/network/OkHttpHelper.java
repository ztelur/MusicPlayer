package com.carpediem.randy.service.network;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by randy on 16-5-26.
 */
public class OkHttpHelper {
    public static OkHttpClient getOkHttpClient(final ProgressResponseListener listener) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder().addNetworkInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Log.e("TEST","intercept");
                Response originResposne  = chain.proceed(chain.request());
                return originResposne.newBuilder().body(
                                new ProgressResponseBody(originResposne.body(),listener)).build();
            }
        });
        return builder.build();
    }
    public static ProgressRequestBody addRequestListener(RequestBody requestBody,
                                                            ProgressRequestListener listener) {
        return new ProgressRequestBody(requestBody,listener);
    }
}
