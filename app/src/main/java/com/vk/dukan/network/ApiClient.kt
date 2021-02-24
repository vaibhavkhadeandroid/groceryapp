package com.vk.dukan.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object  ApiClient {


//    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//
//    httpClient.addInterceptor(new Interceptor() {
//        @Override
//        public Response intercept(Chain chain) throws IOException {
//            Request request = chain.request().newBuilder().addHeader("parameter", "value").build();
//            return chain.proceed(request);
//        }
//    });
  //  Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(url).client(httpClient.build()).build();

//        private val client = OkHttpClient.Builder().addInterceptor(Interceptor {
//
//             chain ->
//
//            var request=chain.request().newBuilder().addHeader("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYWFhIiwiZXhwIjoxNjE0MDY5OTQ0LCJpYXQiOjE2MTQwNTE5NDR9.ltVHhuCYOsoNWEKryeSjbf-T60I5FSPaldyIxWPoObhPizWfrSUW-AFxJorVZx8KjmHkyaZiMf0xVTWbDHol0g").build()
//
//           chain.proceed(request)
//
//
//        })
private val client = OkHttpClient.Builder().build()
        private val retrofit = Retrofit.Builder()
            .baseUrl("http://vaibhavecomapplication-env.eba-yadp7min.us-east-2.elasticbeanstalk.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(getUnsafeOkHttpClient())
            .build()

        fun<T> buildService(service: Class<T>): T{
            return retrofit.create(service)
        }


//    @Provides
//    @Singleton
    fun getUnsafeOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(interceptor)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(SupportInterceptor())
            .authenticator(SupportInterceptor())
        return builder.build()
    }

}