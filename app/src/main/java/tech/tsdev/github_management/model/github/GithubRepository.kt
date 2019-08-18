package tech.tsdev.github_management.model.github

import io.reactivex.Single
import retrofit2.Call
import tech.tsdev.github_management.model.*

object GithubRepository : GithubDataSource {

    private val githubRemotedata: GithubRemoteData by lazy {
        GithubRemoteData()
    }

    override fun loadUserList(since: Int): Single<List<UserListData>> = githubRemotedata.loadUserList(since)

    override fun searchUserList(userName: String): Single<SearchUserData> = githubRemotedata.searchUserList(userName)

    override fun getUserFollowers(username: String): Call<List<UserFollowersFollowingList>> =
        githubRemotedata.getUserFollowers(username)

    override fun getSingleUser(userName: String): Single<SingleUser> = githubRemotedata.getSingleUser(userName)

    override fun getSearchRepo(searchQuery: String): Single<SearchRepoData> = githubRemotedata.getSearchRepo(searchQuery)

    override fun getUserReceivedResult(userName: String): Single<List<ReceivedEvents>> =
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

    override fun getRepoCommitList(repoCommitUrl: String, page: Int): Call<List<GetRepoCommitList>> =
            githubRemotedata.getRepoCommitList(repoCommitUrl, page)

    override fun getRepoIssuesList(repoIssuesUrl: String, page: Int): Call<List<GetRepoIssuesList>> =
            githubRemotedata.getRepoIssuesList(repoIssuesUrl, page)

    override fun getSingleRepoIssues(repoSingleIssuesUrl: String): Single<GetRepoIssuesList> =
            githubRemotedata.getSingleRepoIssues(repoSingleIssuesUrl)

    override fun getIssuesCommentsList(repoCommentsUrl: String): Single<List<GetIssuesComments>> =
            githubRemotedata.getIssuesCommentsList(repoCommentsUrl)

    override fun getRepoStarredUserList(repoStarredUserList: String, page: Int): Call<List<GetRepoStarredUserList>> =
            githubRemotedata.getRepoStarredUserList(repoStarredUserList, page)

    override fun getRepoForkedUserList(repoForkedUserList: String, page: Int): Call<List<GetForkUserList>> =
            githubRemotedata.getRepoForkedUserList(repoForkedUserList, page)

    override fun getRepoSubscribeUserList(repoSubscriberUserList: String, page: Int): Call<List<GetRepoSubscribers>> =
            githubRemotedata.getRepoSubscribeUserList(repoSubscriberUserList, page)
}