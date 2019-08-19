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

interface GithubDataSource {

    //바텀 nav 첫번째 유저 목록 보이게 하는 함수
    fun loadUserList(since: Int ): Single<List<UserListData>>

    //유져 검색하는 함수
    fun searchUserList(userName: String): Single<SearchUserData>


    //유저 검색, 유저목록의 팔로우 수 함수
    fun getUserFollowers(username: String): Single<List<UserFollowersFollowingList>>

    fun getSingleUser(userName: String): Single<SingleUser>

    fun getSearchRepo(searchQuery: String): Single<SearchRepoData>

    fun getUserReceivedResult(userName: String): Single<List<ReceivedEvents>>

    fun getUserFollowing(userName: String): Single<List<UserFollowersFollowingList>>

    fun getUserRepoList(userName: String): Single<List<UserRepoList>>

    fun getRepoInfoBasedOnOwnerNameRepoName(repoUrl: String): Single<GetSingleRepo>

    fun getStarBasedonUserName(userName: String): Call<List<GetUserStarred>>

    fun getRepoReadme(repoUrl: String): Single<GetRepoReadme>

    // 레파지토리 커밋 내역 보는 함수
    fun getRepoCommitList(repoCommitUrl: String, page: Int): Single<List<GetRepoCommitList>>

    fun getRepoIssuesList(repoIssuesUrl: String, page: Int): Maybe<List<GetRepoIssuesList>>

    //이슈 하나만 가져 오는 함수
    fun getSingleRepoIssues(repoSingleIssuesUrl: String): Single<GetRepoIssuesList>

    // 해당 이슈의 코멘트 가져오는 함수
    fun getIssuesCommentsList(repoCommentsUrl: String): Single<List<GetIssuesComments>>

    //해당 레포지토리  star준 유저 목록 보여주는 함수
    fun getRepoStarredUserList(repoStarredUserList: String, page: Int): Maybe<List<GetRepoStarredUserList>>

    //해당 레포지토리 fork 유저목록 보여주는 함수
    fun getRepoForkedUserList(repoForkedUserList: String, page: Int): Maybe<List<GetForkUserList>>

    fun getRepoSubscribeUserList(repoSubscriberUserList: String, page: Int): Maybe<List<GetRepoSubscribers>>
}