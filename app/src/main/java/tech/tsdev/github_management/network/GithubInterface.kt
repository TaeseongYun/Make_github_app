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
    fun getUserFollowers(
        @Path("username") username: String
    ): Call<List<UserFollowersFollowingList>>


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

    //유저이름에 따른 Following
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET("/users/{username}/following")
    fun getFollowingBasedOnUserName(
        @Path("username") userName: String
    ): Call<List<UserFollowersFollowingList>>

    //유저의 레파지토리
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET("/users/{username}/repos")
    fun getRepoListBasedOnUserName (
        @Path("username") userName: String
    ): Call<List<UserRepoList>>

    //유저Name과 repo 이름으로 해당 레파지토리 가져옴
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET
    fun getRepoBasedOnOwnerName(
        @Url repoUrl: String
    ): Call<GetSingleRepo>

//    해당 유저 스타 준 레파지토리 목록
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET("users/{username}/starred")
    fun getStarBasedonUserName(
        @Path("username") userName: String
    ):Call<List<GetUserStarred>>
}