package com.example.dcardhomework.network

import com.example.dcardhomework.BuildConfig
import com.example.dcardhomework.data.SearchResponse
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

const val BASE_URL: String = "https://api.github.com/"
const val GET_REPOS: String = "search/repositories"
const val USER_TOKEN_EXPIRE: Int = 403
const val USER_SUBSCRIBE_EXPIRE: Int = 406

private val authorizationInterceptor = Interceptor {
    val originalHttpUrl: HttpUrl = it.request().url
    val requestBuilder = it
        .request()
        .newBuilder()
        .url(originalHttpUrl)
        .addHeader("Content-Type", "application/json")

    val request = requestBuilder.build()
    val response: okhttp3.Response = it.proceed(request)
    when (response.code) {
        USER_TOKEN_EXPIRE -> TokenObservable.instances.setTokenStatus(TokenObservable.TokenStatus.TOKEN_EXPIRE)
        USER_SUBSCRIBE_EXPIRE -> TokenObservable.instances.setTokenStatus(TokenObservable.TokenStatus.SUBSCRIBE_EXPIRE)
    }
    response
}


private val client = OkHttpClient.Builder()
    .addInterceptor(authorizationInterceptor)
    .addInterceptor(
        HttpLoggingInterceptor().apply {
            level = when (BuildConfig.LOGGER_VISIABLE) {
                true -> HttpLoggingInterceptor.Level.BODY
                false -> HttpLoggingInterceptor.Level.NONE
            }
        }
    )
    .connectTimeout(40, TimeUnit.SECONDS)
    .build()


private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(client)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface GithubApi {

    // Search
    @GET(GET_REPOS)
    suspend fun searchRepos(
        @Query("q") searchQuery: String,
//        @Query("per_page") perPage: String
    ): SearchResponse

}

object DcardApi {
    val retrofitService: GithubApi by lazy { retrofit.create(GithubApi::class.java) }
}