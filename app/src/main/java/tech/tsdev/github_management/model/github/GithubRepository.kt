package tech.tsdev.github_management.model.github

import io.reactivex.Maybe
import io.reactivex.Observable
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

class GithubRepository(private val githubAPI: GithubInterface) {


    companion object {
        private var instance: GithubRepository? = null

        fun getInstance(githubAPI: GithubInterface) =
            instance ?: synchronized(this) {
                instance ?: GithubRepository(githubAPI = githubAPI).also { instance = it }
            }

    }

    private val githubRemoteData: GithubRemoteDataSource by lazy {
        GithubRemoteDataSource(githubAPI)
    }

    fun loadUserList(since: Int): Single<List<UserListData>> = githubRemoteData.loadUserList(since)

    fun searchUserList(userName: String): Single<SearchUserData> = githubRemoteData.searchUserList(userName)

    fun getUserFollowers(username: String): Single<List<UserFollowersFollowingList>> =
        githubRemoteData.getUserFollowers(username)

    fun getSingleUser(userName: String): Single<SingleUser> = githubRemoteData.getSingleUser(userName)

    fun getSearchRepo(searchQuery: String): Single<SearchRepoData> =
        githubRemoteData.getSearchRepo(searchQuery)

    fun getUserReceivedResult(userName: String): Single<List<ReceivedEvents>> =
        githubRemoteData.getUserReceivedResult(userName)

    fun getUserFollowing(userName: String): Single<List<UserFollowersFollowingList>> =
        githubRemoteData.getUserFollowing(userName)

    fun getUserRepoList(userName: String): Observable<List<UserRepoList>> =
        githubRemoteData.getUserRepoList(userName)

    fun getRepoInfoBasedOnOwnerNameRepoName(repoUrl: String): Single<GetSingleRepo> =
        githubRemoteData.getRepoInfoBasedOnOwnerNameRepoName(repoUrl)

    fun getStarBasedonUserName(userName: String): Call<List<GetUserStarred>> =
        githubRemoteData.getStarBasedonUserName(userName)

    fun getRepoReadme(repoUrl: String): Single<GetRepoReadme> =
        githubRemoteData.getRepoReadme(repoUrl)

    fun getRepoCommitList(repoCommitUrl: String, page: Int): Single<List<GetRepoCommitList>> =
        githubRemoteData.getRepoCommitList(repoCommitUrl, page)

    fun getRepoIssuesList(repoIssuesUrl: String, page: Int): Maybe<List<GetRepoIssuesList>> =
        githubRemoteData.getRepoIssuesList(repoIssuesUrl, page)

    fun getSingleRepoIssues(repoSingleIssuesUrl: String): Single<GetRepoIssuesList> =
        githubRemoteData.getSingleRepoIssues(repoSingleIssuesUrl)

    fun getIssuesCommentsList(repoCommentsUrl: String): Single<List<GetIssuesComments>> =
        githubRemoteData.getIssuesCommentsList(repoCommentsUrl)

    fun getRepoStarredUserList(repoStarredUserList: String, page: Int): Maybe<List<GetRepoStarredUserList>> =
        githubRemoteData.getRepoStarredUserList(repoStarredUserList, page)

    fun getRepoForkedUserList(repoForkedUserList: String, page: Int): Maybe<List<GetForkUserList>> =
        githubRemoteData.getRepoForkedUserList(repoForkedUserList, page)

    fun getRepoSubscribeUserList(repoSubscriberUserList: String, page: Int): Maybe<List<GetRepoSubscribers>> =
        githubRemoteData.getRepoSubscribeUserList(repoSubscriberUserList, page)
}