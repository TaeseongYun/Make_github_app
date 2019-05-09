package tech.tsdev.github_management.network

import retrofit2.Call
import retrofit2.http.*
import tech.tsdev.github_management.BuildConfig
import tech.tsdev.github_management.model.*

interface GithubInterface {
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET("/users")
    fun userList(
        @Query("since") since: Int
    ): Call<List<UserListData>>


    //유저 검색하는 retrofit 함수
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET("/search/users")
    fun searchUsers(
        @Query("q") userName: String
    ): Call<SearchUserData>


    //깃허브 팔로우수 보이게 하는 retrofit 함수
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET("/users/{username}/followers")
    fun getFollowers(
        @Path("username") username: String
    ): Call<List<SearchUserData>>


    //해당 검색한 유저  레파지토리 검사하여 총 star 갯수가 몇개인지 검사하는 함수
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET("/users/{username}/repos")
    fun getRepo(
        @Path("username") username: String
    ): Call<List<Repository>>

    //리사이클러 뷰 선택하면 해당 유저 Info 보여지게 하는 함수
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET("/users/{username}")
    fun getSingleUser(
        @Path("username") username: String
    ): Call<SingleUser>


    //레파지토리 검색 함수
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET("/search/repositories")
    fun getSearchRepoResult(
        @Query("q") searchRepo: String
    ): Call<SearchRepoData>


    //유저이름에 따른 Received_Event 함수
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET("/users/{username}/received_events")
    fun getUserReceivedResult(
        @Path("username") userName: String
    ): Call<List<ReceivedEvents>>
}