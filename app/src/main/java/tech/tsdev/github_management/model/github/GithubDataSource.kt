package tech.tsdev.github_management.model.github

import io.reactivex.Single
import retrofit2.Call
import tech.tsdev.github_management.model.*

interface GithubDataSource {

    //바텀 nav 첫번째 유저 목록 보이게 하는 함수
    fun loadUserList(since: Int ): Single<List<UserListData>>

    //유져 검색하는 함수
    fun searchUserList(userName: String): Call<SearchUserData>


    //유저 검색, 유저목록의 팔로우 수 함수
    fun getUserFollowers(username: String): Call<List<UserFollowersFollowingList>>

    fun getSingleUser(userName: String): Call<SingleUser>

    fun getSearchRepo(searchQuery: String): Call<SearchRepoData>

    fun getUserReceivedResult(userName: String): Single<List<ReceivedEvents>>

    fun getUserFollowing(userName: String): Call<List<UserFollowersFollowingList>>

    fun getUserRepoList(userName: String): Call<List<UserRepoList>>

    fun getRepoInfoBasedOnOwnerNameRepoName(repoUrl: String): Call<GetSingleRepo>

    fun getStarBasedonUserName(userName: String): Call<List<GetUserStarred>>

    fun getRepoReadme(repoUrl: String): Call<GetRepoReadme>

    // 레파지토리 커밋 내역 보는 함수
    fun getRepoCommitList(repoCommitUrl: String, page: Int): Call<List<GetRepoCommitList>>

    fun getRepoIssuesList(repoIssuesUrl: String, page: Int): Call<List<GetRepoIssuesList>>

    //이슈 하나만 가져 오는 함수
    fun getSingleRepoIssues(repoSingleIssuesUrl: String): Call<GetRepoIssuesList>

    // 해당 이슈의 코멘트 가져오는 함수
    fun getIssuesCommentsList(repoCommentsUrl: String): Call<List<GetIssuesComments>>

    //해당 레포지토리  star준 유저 목록 보여주는 함수
    fun getRepoStarredUserList(repoStarredUserList: String, page: Int): Call<List<GetRepoStarredUserList>>

    //해당 레포지토리 fork 유저목록 보여주는 함수
    fun getRepoForkedUserList(repoForkedUserList: String, page: Int): Call<List<GetForkUserList>>

    fun getRepoSubscribeUserList(repoSubscriberUserList: String, page: Int): Call<List<GetRepoSubscribers>>
}