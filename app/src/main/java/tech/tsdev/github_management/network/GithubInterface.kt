package tech.tsdev.github_management.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import tech.tsdev.github_management.BuildConfig
import tech.tsdev.github_management.model.UserListData

interface GithubInterface {
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET("/users")
    fun userList(
        @Query("since") since: Int
    ): Call<List<UserListData>>
}