package com.qader.ahmed.companyandmembers.network;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String URL_BASE= "https://next.json-generator.com";

    private static Retrofit retrofit = null;

    public static Retrofit getRetrofitInstance() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;

//        if (retrofit == null) {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(URL_BASE)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//
//        }
//        return retrofit;
    }
//    public static Retrofit getClientAuthentication() {
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//// set your desired log level
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//        Retrofit.Builder builder = new Retrofit.Builder()
//                .baseUrl(URL_BASE);
//        AuthenticationInterceptor interceptor = new AuthenticationInterceptor(
//                Credentials.basic("", ""));
//        if (!httpClient.interceptors().contains(interceptor)) {
//            httpClient.addInterceptor(interceptor);
//            httpClient.addInterceptor(logging);
//            builder.client(httpClient.build());
//            retrofit = builder
//                    // .baseUrl(URL_BASE)
//                    .addConverterFactory(ScalarsConverterFactory.create())
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .client(httpClient.build())
//                    .build();
//
//        }
//
//        return retrofit;
//    }

//    public static class AuthenticationInterceptor implements Interceptor {
//
//        private String authToken;
//
//        public AuthenticationInterceptor(String token) {
//            this.authToken = token;
//        }
//
//        @Override
//        public okhttp3.Response intercept(Chain chain) throws IOException {
//            Request original = chain.request();
//
//            Request.Builder builder = original.newBuilder()
//                    .header("Authorization", authToken);//Remember header() vs addHeader
//
//            Request request = builder.build();
//            return chain.proceed(request);
//        }
//    }

///////////////////////////////////////////////////////


//    private static void initOkHttp(final Context context) {
//        OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder()
//                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
//                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
//                .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);
//
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        httpClient.addInterceptor(interceptor);
//
//        httpClient.addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request original = chain.request();
//                Request.Builder requestBuilder = original.newBuilder()
//                        .addHeader("Accept", "application/json")
//                        .addHeader("Content-Type", "application/json");
//
//                // Adding Authorization token (API Key)
//                // Requests will be denied without API key
//                if (!TextUtils.isEmpty(PrefUtils.getApiKey(context))) {
//                    requestBuilder.addHeader("Authorization", PrefUtils.getApiKey(context));
//                }
//
//                Request request = requestBuilder.build();
//                return chain.proceed(request);
//            }
//        });
//
//        okHttpClient = httpClient.build();
//    }

}
