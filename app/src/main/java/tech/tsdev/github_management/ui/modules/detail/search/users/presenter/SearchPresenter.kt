package tech.tsdev.github_management.ui.modules.detail.search.users.presenter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.model.SingleUser
import tech.tsdev.github_management.model.github.GithubRepository

class SearchPresenter(val view: SearchUserContract.View,
                      private val githubRepository: GithubRepository) : SearchUserContract.Presenter{
    override fun getSearchUserForQuery(userName: String) {
        githubRepository.getSingleUser(userName).enqueue(object : Callback<SingleUser>{
            override fun onFailure(call: Call<SingleUser>, t: Throwable) {

            }

            override fun onResponse(call: Call<SingleUser>, response: Response<SingleUser>) {

            }

        })
    }

}