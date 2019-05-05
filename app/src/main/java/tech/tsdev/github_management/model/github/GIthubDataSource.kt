package tech.tsdev.github_management.model.github

import retrofit2.Call
import tech.tsdev.github_management.model.*

interface GIthubDataSource {

    //바텀 nav 첫번째 유저 목록 보이게 하는 함수
    fun loadUserList(since: Int ): Call<List<UserListData>>

    //유져 검색하는 함수
    fun searchUserList(userName: String): Call<SearchUserData>


    //유저 검색, 유저목록의 팔로우 수 함수
    fun getFollowers(username: String): Call<List<SearchUserData>>

    fun getUsersRepo(username: String): Call<List<Repository>>

    fun getSingleUser(userName: String): Call<SingleUser>

    fun getSearchRepo(searchQuery: String): Call<SearchRepoData>
}