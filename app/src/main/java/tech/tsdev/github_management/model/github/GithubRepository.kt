package tech.tsdev.github_management.model.github

import retrofit2.Call
import tech.tsdev.github_management.model.*

object GithubRepository : GithubDataSource {

    private val githubRemotedata: GithubRemoteData by lazy {
        GithubRemoteData()
    }

    override fun loadUserList(since: Int): Call<List<UserListData>> = githubRemotedata.loadUserList(since)

    override fun searchUserList(userName: String): Call<SearchUserData> = githubRemotedata.searchUserList(userName)

    override fun getUserFollowers(username: String): Call<List<UserFollowersFollowingList>> =
        githubRemotedata.getUserFollowers(username)

    override fun getSingleUser(userName: String): Call<SingleUser> = githubRemotedata.getSingleUser(userName)

    override fun getSearchRepo(searchQuery: String): Call<SearchRepoData> = githubRemotedata.getSearchRepo(searchQuery)

    override fun getUserReceivedResult(userName: String): Call<List<ReceivedEvents>> =
        githubRemotedata.getUserReceivedResult(userName)

    override fun getUserFollowing(userName: String): Call<List<UserFollowersFollowingList>> =
        githubRemotedata.getUserFollowing(userName)

    override fun getUserRepoList(userName: String): Call<List<UserRepoList>> =
        githubRemotedata.getUserRepoList(userName)

    override fun getRepoInfoBasedOnOwnerNameRepoName(repoUrl: String): Call<GetSingleRepo> =
            githubRemotedata.getRepoInfoBasedOnOwnerNameRepoName(repoUrl)

    override fun getStarBasedonUserName(userName: String): Call<List<GetUserStarred>> =
            githubRemotedata.getStarBasedonUserName(userName)

    override fun getRepoReadme(repoUrl: String): Call<GetRepoReadme> =
            githubRemotedata.getRepoReadme(repoUrl)

    override fun getRepoCommitList(repoCommitUrl: String): Call<List<GetRepoCommitList>> =
            githubRemotedata.getRepoCommitList(repoCommitUrl)

    override fun getRepoIssuesList(repoIssuesUrl: String): Call<List<GetRepoIssuesList>> =
            githubRemotedata.getRepoIssuesList(repoIssuesUrl)
}