package tech.tsdev.github_management.model.github

import retrofit2.Call
import tech.tsdev.github_management.model.SearchUserData
import tech.tsdev.github_management.model.UserListData

interface GIthubDataSource {


    fun loadUserList(since: Int): Call<List<UserListData>>

    fun searchUserList(userName: String): Call<SearchUserData>
}