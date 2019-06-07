package tech.tsdev.github_management.model.github

import retrofit2.Call
import tech.tsdev.github_management.model.*
import tech.tsdev.github_management.network.GithubInterface
import tech.tsdev.github_management.network.createRetrofit

class GithubRemoteData : GithubDataSource {

    companion object {
        const val GITHUB_URL = "https://api.github.com"
    }

    private val githubUserList = createRetrofit(GithubInterface::class.java, GITHUB_URL)

    override fun loadUserList(since: Int) = githubUserList.userList(since)

    override fun searchUserList(userName: String): Call<SearchUserData> = githubUserList.searchUsers(userName)

    override fun getUserFollowers(username: String): Call<List<UserFollowersFollowingList>> =
        githubUserList.getUserFollowers(username)

    override fun getSingleUser(userName: String): Call<SingleUser> = githubUserList.getSingleUser(userName)

    override fun getSearchRepo(searchQuery: String): Call<SearchRepoData> =
        githubUserList.getSearchRepoResult(searchQuery)

    override fun getUserReceivedResult(userName: String): Call<List<ReceivedEvents>> =
        githubUserList.getUserReceivedResult(userName)

    override fun getUserFollowing(userName: String): Call<List<UserFollowersFollowingList>> =
        githubUserList.getFollowingBasedOnUserName(userName)

    override fun getUserRepoList(userName: String): Call<List<UserRepoList>> =
        githubUserList.getRepoListBasedOnUserName(userName)

    override fun getRepoInfoBasedOnOwnerNameRepoName(repoUrl: String): Call<GetSingleRepo> =
            githubUserList.getRepoBasedOnOwnerName(repoUrl)

    override fun getStarBasedonUserName(userName: String): Call<List<GetUserStarred>> =
            githubUserList.getStarBasedonUserName(userName)

    override fun getRepoReadme(repoUrl: String): Call<GetRepoReadme> =
            githubUserList.getRepoReadMeBasedOnRepoUrl(repoUrl)

    override fun getRepoCommitList(repoCommitUrl: String, page: Int): Call<List<GetRepoCommitList>> =
            githubUserList.getRepoCommitsBasedOnRepoUrl(repoCommitUrl, page)

    override fun getRepoIssuesList(repoIssuesUrl: String): Call<List<GetRepoIssuesList>> =
            githubUserList.getRepoIssuesDetailBasedOnIssuesUrl(repoIssuesUrl)

    override fun getSingleRepoIssues(repoSingleIssuesUrl: String): Call<GetRepoIssuesList> =
            githubUserList.getSingleRepoIssuesBasedOnIssuesUrl(repoSingleIssuesUrl)

    override fun getIssuesCommentsList(repoCommentsUrl: String): Call<List<GetIssuesComments>> =
            githubUserList.getIssuesCommentsListBasedOnCommentsUrl(repoCommentsUrl)

    override fun getRepoStarredUserList(repoStarredUserList: String, page: Int): Call<List<GetRepoStarredUserList>> =
            githubUserList.getRepoStargazerUserList(repoStarredUserList, page)

    override fun getRepoForkedUserList(repoForkedUserList: String): Call<List<GetForkUserList>> =
            githubUserList.getRepoForkedUserList(repoForkedUserList)
}