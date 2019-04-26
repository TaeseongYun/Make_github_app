package tech.tsdev.github_management.model.github

import retrofit2.Call
import tech.tsdev.github_management.model.Repository
import tech.tsdev.github_management.model.SearchUserData
import tech.tsdev.github_management.model.SingleUser
import tech.tsdev.github_management.model.UserListData

object GithubRepository : GIthubDataSource{

    private val githubRemotedata = GIthubRemoteData()

    override fun loadUserList(since: Int): Call<List<UserListData>> = githubRemotedata.loadUserList(since)

    override fun searchUserList(userName: String): Call<SearchUserData> = githubRemotedata.searchUserList(userName)

    override fun getFollowers(username: String): Call<List<SearchUserData>> = githubRemotedata.getFollowers(username)

    override fun getUsersRepo(username: String): Call<List<Repository>> = githubRemotedata.getUsersRepo(username)

    override fun getSingleUser(userName: String): Call<SingleUser> = githubRemotedata.getSingleUser(userName)
}