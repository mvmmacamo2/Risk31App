package com.kaya.risk31app.Storage;

import com.facebook.stetho.okhttp3.BuildConfig;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetrofitBuilder {
//    private static final String BASE_URL = "http://10.0.3.2:8000/api/";
    private static final String BASE_URL = "http://risk31api.paytek-africa.com/api/";
    private final static OkHttpClient client = buildClient();
    private final static Retrofit retrofit = buildRetrofit(client);



    private static OkHttpClient buildClient() {
       OkHttpClient.Builder builder = new OkHttpClient.Builder()
               .addInterceptor(new Interceptor() {
                   @Override
                   public Response intercept(Chain chain) throws IOException {
                       Request request = chain.request();

                       Request.Builder builder1 = request.newBuilder()
                               .addHeader("Accept", "application/json")
                               .addHeader("Connection", "close");

                       request = builder1.build();
                       return chain.proceed(request);
                   }
               });

       if (BuildConfig.DEBUG) {
           builder.addNetworkInterceptor(new StethoInterceptor());
       }
       return builder.build();
    }


    private static Retrofit buildRetrofit(OkHttpClient client) {
//        Moshi moshi = new Moshi.Builder()
//                .add(new RealmListJsonAdapterFactory())
//                .build();
        return new Retrofit.Builder()
                              .baseUrl(BASE_URL)
                              .client(client)
                              .addConverterFactory(MoshiConverterFactory.create())
                              .build();
    }
    public static <T> T createService(Class<T> service) {
        return retrofit.create(service);
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }


    public static <T> T createServiceWithAuth(Class<T> service, final TokenManager tokenManager) {
        OkHttpClient newClient = client.newBuilder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                Request.Builder builder = request.newBuilder();

                if (tokenManager.getToken().getAccessToken() != null) {
                    builder.addHeader("Authorization", "Bearer " + tokenManager.getToken().getAccessToken());
                }
                request = builder.build();
                return chain.proceed(request);
            }
        }).build();
        Retrofit newRetrofit = retrofit.newBuilder().client(newClient).build();
        return newRetrofit.create(service);
    }
}