package tech.tsdev.github_management.model.github

import retrofit2.Call
import tech.tsdev.github_management.model.SearchUserData
import tech.tsdev.github_management.network.GithubInterface
import tech.tsdev.github_management.network.createRetrofit

class GIthubRemoteData : GIthubDataSource {


    companion object {
        val GITHUB_URL = "https://api.github.com"
    }

    private val githubUserList = createRetrofit(GithubInterface::class.java, GITHUB_URL)

    override fun loadUserList(since: Int) = githubUserList.userList(since)

    override fun searchUserList(userName: String): Call<SearchUserData> = githubUserList.searchUsers(userName)

}