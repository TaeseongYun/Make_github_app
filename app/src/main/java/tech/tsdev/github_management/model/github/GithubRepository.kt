package tech.tsdev.github_management.model.github

import retrofit2.Call
import tech.tsdev.github_management.model.UserListData

object GithubRepository : GIthubDataSource{

    private val githubRemotedata = GIthubRemoteData()

    override fun loadUserList(since: Int): Call<List<UserListData>> = githubRemotedata.loadUserList(since)

}