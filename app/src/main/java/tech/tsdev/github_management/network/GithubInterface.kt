package tech.tsdev.github_management.network

import io.reactivex.Maybe
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*
import tech.tsdev.github_management.BuildConfig
import tech.tsdev.github_management.model.*
import tech.tsdev.github_management.model.comment.GetIssuesComments
import tech.tsdev.github_management.model.fork.GetForkUserList
import tech.tsdev.github_management.model.repo.*
import tech.tsdev.github_management.model.search.SearchRepoData
import tech.tsdev.github_management.model.search.SearchUserData
import tech.tsdev.github_management.model.starred.GetUserStarred
import tech.tsdev.github_management.model.user.SingleUser
import tech.tsdev.github_management.model.user.UserFollowersFollowingList
import tech.tsdev.github_management.model.user.UserListData

interface GithubInterface {
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET("/users")
    fun userList(
        @Query("since") since: Int
    ): Single<List<UserListData>>


    //유저 검색하는 retrofit 함수
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET("/search/users")
    fun searchUsers(
        @Query("q") userName: String
    ): Single<SearchUserData>


    //깃허브 팔로우수 보이게 하는 retrofit 함수
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET("/users/{username}/followers")
    fun getUserFollowers(
        @Path("username") username: String
    ): Single<List<UserFollowersFollowingList>>


    //리사이클러 뷰 선택하면 해당 유저 Info 보여지게 하는 함수
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET("/users/{username}")
    fun getSingleUser(
        @Path("username") username: String
    ): Single<SingleUser>


    //레파지토리 검색 함수
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET("/search/repositories")
    fun getSearchRepoResult(
        @Query("q") searchRepo: String
    ): Single<SearchRepoData>


    //유저이름에 따른 Received_Event 함수
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET("/users/{username}/received_events")
    fun getUserReceivedResult(
        @Path("username") userName: String
    ): Single<List<ReceivedEvents>>

    //유저이름에 따른 Following
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET("/users/{username}/following")
    fun getFollowingBasedOnUserName(
        @Path("username") userName: String
    ): Single<List<UserFollowersFollowingList>>

    //유저의 레파지토리
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET("/users/{username}/repos")
    fun getRepoListBasedOnUserName(
        @Path("username") userName: String
    ): Maybe<List<UserRepoList>>

    //유저Name과 repo 이름으로 해당 레파지토리 가져옴
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET
    fun getRepoBasedOnOwnerName(
        @Url repoUrl: String
    ): Single<GetSingleRepo>

    //    해당 유저 스타 준 레파지토리 목록
    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET("users/{username}/starred")
    fun getStarBasedonUserName(
        @Path("username") userName: String
    ): Call<List<GetUserStarred>>

    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET
    fun getRepoReadMeBasedOnRepoUrl(
        @Url repoUrl: String
    ): Single<GetRepoReadme>

    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET
    fun getRepoCommitsBasedOnRepoUrl(
        @Url repoCommitUrl: String,
        @Query("page") page: Int
    ): Single<List<GetRepoCommitList>>

    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET
    fun getRepoIssuesDetailBasedOnIssuesUrl(
        @Url repoIssueUrl: String,
        @Query("page") page: Int
    ): Maybe<List<GetRepoIssuesList>>

    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET
    fun getSingleRepoIssuesBasedOnIssuesUrl(
        @Url repoSingleUrl: String
    ): Single<GetRepoIssuesList>

    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET
    fun getIssuesCommentsListBasedOnCommentsUrl(
        @Url repoCommentsUrl: String
    ): Single<List<GetIssuesComments>>

    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET
    fun getRepoStargazerUserList(
        @Url repoStarredUserListUrl: String,
        @Query("page") page: Int
    ): Maybe<List<GetRepoStarredUserList>>

    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET
    fun getRepoForkedUserList(
        @Url repoForkedUserListUrl: String,
        @Query("page") page: Int
    ): Maybe<List<GetForkUserList>>

    @Headers("Authorization: token ${BuildConfig.GITHUB_TOKEN}")
    @GET
    fun getRepoSubscriberUserList(
        @Url repoWatcherUserListUrl: String,
        @Query("page") page: Int
    ): Maybe<List<GetRepoSubscribers>>
}