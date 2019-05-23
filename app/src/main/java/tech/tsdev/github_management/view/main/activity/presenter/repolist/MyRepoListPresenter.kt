package tech.tsdev.github_management.view.main.activity.presenter.repolist

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.model.UserRepoList
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.view.main.activity.adapter.repolist.model.MyRepoListRecyclerModel

class MyRepoListPresenter(
    private val view: MyRepoListContract.View,
    private val githubRepository: GithubRepository,
    private val myRepoListRecyclerModel: MyRepoListRecyclerModel
) : MyRepoListContract.Presenter {
    override fun getUserRepoListBasedUserName(userName: String?) {
        userName?.let {
            githubRepository.getUserRepoList(it).enqueue(object : Callback<List<UserRepoList>> {
                override fun onFailure(call: Call<List<UserRepoList>>, t: Throwable) {
                    view.showLoadFailToastMessage()
                }

                override fun onResponse(call: Call<List<UserRepoList>>, response: Response<List<UserRepoList>>) {
                    if (response.isSuccessful) {
                        response.body()?.let { userRepoList ->
                            userRepoList.forEach { userRepo ->
                                myRepoListRecyclerModel.addItem(userRepo)
                            }
                            myRepoListRecyclerModel.notifyItemData()
                        }
                    }
                }

            })
        }
    }
}