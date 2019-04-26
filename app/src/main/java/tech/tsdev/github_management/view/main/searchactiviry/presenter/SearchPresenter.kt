package tech.tsdev.github_management.view.main.searchactiviry.presenter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.model.Repository
import tech.tsdev.github_management.model.SearchUserData
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.ui.modules.detail.DetailActivity
import tech.tsdev.github_management.view.main.searchactiviry.adapter.model.SearchRecyclerModel

class SearchPresenter(
    val view: SearchContract.View,
    private val userSearchRepository: GithubRepository,
    val userSearchRecyclerView: SearchRecyclerModel
) : SearchContract.Presenter {


    init {
        userSearchRecyclerView.onClick = { position ->
            view.loadDetailActivity(userSearchRecyclerView.getItemData(position).login)
        }

    }

    override fun searchLoadUsers(userName: String) {

        userSearchRepository.searchUserList(userName).enqueue(object : Callback<SearchUserData> {
            override fun onFailure(call: Call<SearchUserData>, t: Throwable) {
                view.loadErrorMessage()
            }

            override fun onResponse(call: Call<SearchUserData>, response: Response<SearchUserData>) {
                if (response.isSuccessful) {
                    response.body()?.let { searchUserData ->
                        searchUserData.items.forEach { userData ->
                            userSearchRecyclerView.addItem(userData)
                        }
                    }
                    userSearchRecyclerView.notifyDataChanged()
                } else {
                    view.loadErrorMessage(response.errorBody().toString())
                }
            }
        })

    }

}