package tech.tsdev.github_management.model.github

import retrofit2.Call
import tech.tsdev.github_management.model.*

object GithubRepository : GithubDataSource {

    private val githubRemotedata = GithubRemoteData()

    override fun loadUserList(since: Int): Call<List<UserListData>> = githubRemotedata.loadUserList(since)

    override fun searchUserList(userName: String): Call<SearchUserData> = githubRemotedata.searchUserList(userName)

    override fun getUserFollowers(username: String): Call<List<UserFollowersFollowingList>> =
        githubRemotedata.getUserFollowers(username)

    override fun getUsersRepo(username: String): Call<List<Repository>> = githubRemotedata.getUsersRepo(username)

    override fun getSingleUser(userName: String): Call<SingleUser> = githubRemotedata.getSingleUser(userName)

    override fun getSearchRepo(searchQuery: String): Call<SearchRepoData> = githubRemotedata.getSearchRepo(searchQuery)

    override fun getUserReceivedResult(userName: String): Call<List<ReceivedEvents>> =
        githubRemotedata.getUserReceivedResult(userName)

    override fun getUserFollowing(userName: String): Call<List<UserFollowersFollowingList>> =
        githubRemotedata.getUserFollowing(userName)
}