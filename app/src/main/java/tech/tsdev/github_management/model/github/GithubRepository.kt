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

object GithubRepository : GithubDataSource {

    private val githubRemotedata: GithubRemoteData by lazy {
        GithubRemoteData()
    }

    override fun loadUserList(since: Int): Single<List<UserListData>> = githubRemotedata.loadUserList(since)

    override fun searchUserList(userName: String): Single<SearchUserData> = githubRemotedata.searchUserList(userName)

    override fun getUserFollowers(username: String): Single<List<UserFollowersFollowingList>> =
        githubRemotedata.getUserFollowers(username)

    override fun getSingleUser(userName: String): Single<SingleUser> = githubRemotedata.getSingleUser(userName)

    override fun getSearchRepo(searchQuery: String): Single<SearchRepoData> = githubRemotedata.getSearchRepo(searchQuery)

    override fun getUserReceivedResult(userName: String): Single<List<ReceivedEvents>> =
        githubRemotedata.getUserReceivedResult(userName)

    override fun getUserFollowing(userName: String): Single<List<UserFollowersFollowingList>> =
        githubRemotedata.getUserFollowing(userName)

    override fun getUserRepoList(userName: String): Single<List<UserRepoList>> =
        githubRemotedata.getUserRepoList(userName)

    override fun getRepoInfoBasedOnOwnerNameRepoName(repoUrl: String): Single<GetSingleRepo> =
            githubRemotedata.getRepoInfoBasedOnOwnerNameRepoName(repoUrl)

    override fun getStarBasedonUserName(userName: String): Call<List<GetUserStarred>> =
            githubRemotedata.getStarBasedonUserName(userName)

    override fun getRepoReadme(repoUrl: String): Single<GetRepoReadme> =
            githubRemotedata.getRepoReadme(repoUrl)

    override fun getRepoCommitList(repoCommitUrl: String, page: Int): Single<List<GetRepoCommitList>> =
            githubRemotedata.getRepoCommitList(repoCommitUrl, page)

    override fun getRepoIssuesList(repoIssuesUrl: String, page: Int): Maybe<List<GetRepoIssuesList>> =
            githubRemotedata.getRepoIssuesList(repoIssuesUrl, page)

    override fun getSingleRepoIssues(repoSingleIssuesUrl: String): Single<GetRepoIssuesList> =
            githubRemotedata.getSingleRepoIssues(repoSingleIssuesUrl)

    override fun getIssuesCommentsList(repoCommentsUrl: String): Single<List<GetIssuesComments>> =
            githubRemotedata.getIssuesCommentsList(repoCommentsUrl)

    override fun getRepoStarredUserList(repoStarredUserList: String, page: Int): Maybe<List<GetRepoStarredUserList>> =
            githubRemotedata.getRepoStarredUserList(repoStarredUserList, page)

    override fun getRepoForkedUserList(repoForkedUserList: String, page: Int): Maybe<List<GetForkUserList>> =
            githubRemotedata.getRepoForkedUserList(repoForkedUserList, page)

    override fun getRepoSubscribeUserList(repoSubscriberUserList: String, page: Int): Maybe<List<GetRepoSubscribers>> =
            githubRemotedata.getRepoSubscribeUserList(repoSubscriberUserList, page)
}