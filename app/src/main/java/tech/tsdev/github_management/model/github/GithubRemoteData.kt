package tech.tsdev.github_management.model.github

import io.reactivex.Maybe
import io.reactivex.Single
import retrofit2.Call
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
import tech.tsdev.github_management.network.GithubInterface
import tech.tsdev.github_management.network.createRetrofit

class GithubRemoteData : GithubDataSource {


    companion object {
        const val GITHUB_URL = "https://api.github.com"
    }

    private val githubUserList = createRetrofit(GithubInterface::class.java, GITHUB_URL)

    override fun loadUserList(since: Int): Single<List<UserListData>> = githubUserList.userList(since)

    override fun searchUserList(userName: String): Single<SearchUserData> = githubUserList.searchUsers(userName)

    override fun getUserFollowers(username: String): Single<List<UserFollowersFollowingList>> =
        githubUserList.getUserFollowers(username)

    override fun getSingleUser(userName: String): Single<SingleUser> = githubUserList.getSingleUser(userName)

    override fun getSearchRepo(searchQuery: String): Single<SearchRepoData> =
        githubUserList.getSearchRepoResult(searchQuery)

    override fun getUserReceivedResult(userName: String): Single<List<ReceivedEvents>> =
        githubUserList.getUserReceivedResult(userName)

    override fun getUserFollowing(userName: String): Single<List<UserFollowersFollowingList>> =
        githubUserList.getFollowingBasedOnUserName(userName)

    override fun getUserRepoList(userName: String): Single<List<UserRepoList>> =
        githubUserList.getRepoListBasedOnUserName(userName)

    override fun getRepoInfoBasedOnOwnerNameRepoName(repoUrl: String): Single<GetSingleRepo> =
            githubUserList.getRepoBasedOnOwnerName(repoUrl)

    override fun getStarBasedonUserName(userName: String): Call<List<GetUserStarred>> =
            githubUserList.getStarBasedonUserName(userName)

    override fun getRepoReadme(repoUrl: String): Single<GetRepoReadme> =
            githubUserList.getRepoReadMeBasedOnRepoUrl(repoUrl)

    override fun getRepoCommitList(repoCommitUrl: String, page: Int): Single<List<GetRepoCommitList>> =
            githubUserList.getRepoCommitsBasedOnRepoUrl(repoCommitUrl, page)

    override fun getRepoIssuesList(repoIssuesUrl: String, page: Int): Maybe<List<GetRepoIssuesList>> =
            githubUserList.getRepoIssuesDetailBasedOnIssuesUrl(repoIssuesUrl, page)

    override fun getSingleRepoIssues(repoSingleIssuesUrl: String): Single<GetRepoIssuesList> =
            githubUserList.getSingleRepoIssuesBasedOnIssuesUrl(repoSingleIssuesUrl)

    override fun getIssuesCommentsList(repoCommentsUrl: String): Single<List<GetIssuesComments>> =
            githubUserList.getIssuesCommentsListBasedOnCommentsUrl(repoCommentsUrl)

    override fun getRepoStarredUserList(repoStarredUserList: String, page: Int): Maybe<List<GetRepoStarredUserList>> =
            githubUserList.getRepoStargazerUserList(repoStarredUserList, page)

    override fun getRepoForkedUserList(repoForkedUserList: String, page: Int): Maybe<List<GetForkUserList>> =
            githubUserList.getRepoForkedUserList(repoForkedUserList, page)

    override fun getRepoSubscribeUserList(repoSubscriberUserList: String, page: Int): Maybe<List<GetRepoSubscribers>> =
            githubUserList.getRepoSubscriberUserList(repoSubscriberUserList, page)
}