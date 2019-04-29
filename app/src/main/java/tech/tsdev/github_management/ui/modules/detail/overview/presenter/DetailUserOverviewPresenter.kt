package tech.tsdev.github_management.ui.modules.detail.overview.presenter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.model.SingleUser
import tech.tsdev.github_management.model.github.GithubRepository

class DetailUserOverviewPresenter(val view: DetailUserOverviewContract.View,
                                  private val githubRepository: GithubRepository) : DetailUserOverviewContract.Presenter {

    override fun loadUserOverView(userName: String) {
        githubRepository.getSingleUser(userName).enqueue(object : Callback<SingleUser>{
            override fun onFailure(call: Call<SingleUser>, t: Throwable) {
                view.showFailMessage()
            }

            override fun onResponse(call: Call<SingleUser>, response: Response<SingleUser>) {
                if(response.isSuccessful) {
                    response.body()?.let {

                    }
                }
            }

        })
    }

}