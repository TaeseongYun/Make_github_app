package tech.tsdev.github_management.ui.modules.detail.search.users.presenter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.model.SearchUserData
import tech.tsdev.github_management.model.SingleUser
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.ui.modules.detail.search.users.adapter.model.SearchUserModel

class SearchUserPresenter(
    private val view: SearchUserContract.View,
    private val githubRepository: GithubRepository,
    private val searchRecyclerAdapter: SearchUserModel
) : SearchUserContract.Presenter {
    override fun getSearchUserForQuery(userName: String?) {
        userName?.let {
            githubRepository.searchUserList(it).enqueue(object : Callback<SearchUserData> {
                override fun onFailure(call: Call<SearchUserData>, t: Throwable) {
                    view.loadFailShowMessage()
                }

                override fun onResponse(call: Call<SearchUserData>, response: Response<SearchUserData>) {
                    if (response.isSuccessful) {
                        response.body()?.let { searchUser ->
                            searchUser.items.forEach{ items ->
                                searchRecyclerAdapter.addItem(items)
                            }
                        } ?: let {
                            view.loadFailShowMessage(response.errorBody().toString())
                        }
                        searchRecyclerAdapter.notifyDataItem()
                    }
                }
            })
        }
    }

}