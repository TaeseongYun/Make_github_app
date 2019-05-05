package tech.tsdev.github_management.model.github

import retrofit2.Call
import tech.tsdev.github_management.model.Repository
import tech.tsdev.github_management.model.SearchRepoData
import tech.tsdev.github_management.model.SearchUserData
import tech.tsdev.github_management.model.SingleUser
import tech.tsdev.github_management.network.GithubInterface
import tech.tsdev.github_management.network.createRetrofit

class GIthubRemoteData : GIthubDataSource {


    companion object {
        const val GITHUB_URL = "https://api.github.com"
    }

    private val githubUserList = createRetrofit(GithubInterface::class.java, GITHUB_URL)

    override fun loadUserList(since: Int) = githubUserList.userList(since)

    override fun searchUserList(userName: String): Call<SearchUserData> = githubUserList.searchUsers(userName)

    override fun getFollowers(username: String): Call<List<SearchUserData>> = githubUserList.getFollowers(username)

    override fun getUsersRepo(username: String): Call<List<Repository>> = githubUserList.getRepo(username)

    override fun getSingleUser(userName: String): Call<SingleUser> = githubUserList.getSingleUser(userName)

    override fun getSearchRepo(searchQuery: String): Call<SearchRepoData> =
        githubUserList.getSearchRepoResult(searchQuery)
}