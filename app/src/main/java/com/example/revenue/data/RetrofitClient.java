package com.example.revenue.data;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit INSTANCE;
    private static Retrofit INSTANCE_WITHOUT_AUTH;
    private static String token = null;

    public static Retrofit getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Retrofit.Builder()
                    .baseUrl("http://www.recipepuppy.com/")
                    .client(getClient(true))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return INSTANCE;
    }

    public static Retrofit getInstanceWithoutAuth(){
        if(INSTANCE_WITHOUT_AUTH == null){
            INSTANCE_WITHOUT_AUTH = new Retrofit.Builder()
                    .baseUrl("http://www.recipepuppy.com/")
                    .client(getClient(false))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return INSTANCE_WITHOUT_AUTH;
    }

    public static OkHttpClient getClient(boolean withAuth){

        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.cookieJar(new JavaNetCookieJar(cookieManager));

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        builder.addInterceptor(loggingInterceptor);

        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);

        if (withAuth) {
            builder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original  = chain.request();
                    Request request = original.newBuilder()
                            .header("Authorization",(token != null) ? token : "")
                            .header("Accept","application/json")
                            .method(original.method(), original.body())
                            .build();
                    return chain.proceed(request);
                }
            });
//            builder.authenticator(new TokenAthenticator());
        }

        return builder.build();
    }

    public static void updateToken(String token){
        RetrofitClient.token = String.format("Bearer %s", token);
        INSTANCE = null;
    }

}
