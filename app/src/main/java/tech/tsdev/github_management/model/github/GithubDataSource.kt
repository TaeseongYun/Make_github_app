package tech.tsdev.github_management.model.github

import retrofit2.Call
import tech.tsdev.github_management.model.*

interface GithubDataSource {

    //바텀 nav 첫번째 유저 목록 보이게 하는 함수
    fun loadUserList(since: Int ): Call<List<UserListData>>

    //유져 검색하는 함수
    fun searchUserList(userName: String): Call<SearchUserData>


    //유저 검색, 유저목록의 팔로우 수 함수
    fun getUserFollowers(username: String): Call<List<UserFollowersFollowingList>>

    fun getSingleUser(userName: String): Call<SingleUser>

    fun getSearchRepo(searchQuery: String): Call<SearchRepoData>

    fun getUserReceivedResult(userName: String): Call<List<ReceivedEvents>>

    fun getUserFollowing(userName: String): Call<List<UserFollowersFollowingList>>

    fun getUserRepoList(userName: String): Call<List<UserRepoList>>

    fun getRepoInfoBasedOnOwnerNameRepoName(repoUrl: String): Call<GetSingleRepo>

    fun getStarBasedonUserName(userName: String): Call<List<GetUserStarred>>

    fun getRepoReadme(repoUrl: String): Call<GetRepoReadme>
}