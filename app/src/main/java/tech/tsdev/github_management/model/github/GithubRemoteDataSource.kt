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

class GithubRemoteDataSource(private val githubAPI: GithubInterface) {

    fun loadUserList(since: Int): Single<List<UserListData>> = githubAPI.userList(since)

    fun searchUserList(userName: String): Single<SearchUserData> = githubAPI.searchUsers(userName)

    fun getUserFollowers(username: String): Single<List<UserFollowersFollowingList>> =
        githubAPI.getUserFollowers(username)

    fun getSingleUser(userName: String): Single<SingleUser> = githubAPI.getSingleUser(userName)

    fun getSearchRepo(searchQuery: String): Single<SearchRepoData> =
        githubAPI.getSearchRepoResult(searchQuery)

    fun getUserReceivedResult(userName: String): Single<List<ReceivedEvents>> =
        githubAPI.getUserReceivedResult(userName)

    fun getUserFollowing(userName: String): Single<List<UserFollowersFollowingList>> =
        githubAPI.getFollowingBasedOnUserName(userName)

    fun getUserRepoList(userName: String): Single<List<UserRepoList>> =
        githubAPI.getRepoListBasedOnUserName(userName)

    fun getRepoInfoBasedOnOwnerNameRepoName(repoUrl: String): Single<GetSingleRepo> =
        githubAPI.getRepoBasedOnOwnerName(repoUrl)

    fun getStarBasedonUserName(userName: String): Call<List<GetUserStarred>> =
        githubAPI.getStarBasedonUserName(userName)

    fun getRepoReadme(repoUrl: String): Single<GetRepoReadme> =
        githubAPI.getRepoReadMeBasedOnRepoUrl(repoUrl)

    fun getRepoCommitList(repoCommitUrl: String, page: Int): Single<List<GetRepoCommitList>> =
        githubAPI.getRepoCommitsBasedOnRepoUrl(repoCommitUrl, page)

    fun getRepoIssuesList(repoIssuesUrl: String, page: Int): Maybe<List<GetRepoIssuesList>> =
        githubAPI.getRepoIssuesDetailBasedOnIssuesUrl(repoIssuesUrl, page)

    fun getSingleRepoIssues(repoSingleIssuesUrl: String): Single<GetRepoIssuesList> =
        githubAPI.getSingleRepoIssuesBasedOnIssuesUrl(repoSingleIssuesUrl)

    fun getIssuesCommentsList(repoCommentsUrl: String): Single<List<GetIssuesComments>> =
        githubAPI.getIssuesCommentsListBasedOnCommentsUrl(repoCommentsUrl)

    fun getRepoStarredUserList(repoStarredUserList: String, page: Int): Maybe<List<GetRepoStarredUserList>> =
        githubAPI.getRepoStargazerUserList(repoStarredUserList, page)

    fun getRepoForkedUserList(repoForkedUserList: String, page: Int): Maybe<List<GetForkUserList>> =
        githubAPI.getRepoForkedUserList(repoForkedUserList, page)

    fun getRepoSubscribeUserList(repoSubscriberUserList: String, page: Int): Maybe<List<GetRepoSubscribers>> =
        githubAPI.getRepoSubscriberUserList(repoSubscriberUserList, page)
}