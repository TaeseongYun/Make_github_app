package tech.tsdev.github_management.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun <T>createRetrofit(cls: Class<T>, url: String): T {
    return Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .client(clientOkHttp())
        .build()
        .create(cls)
}

fun clientOkHttp(): OkHttpClient {
    val builder = OkHttpClient.Builder()
    builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    return builder.build()
}