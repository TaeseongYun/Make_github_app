package tech.tsdev.github_management.view.main.myfragment.viewpagerfragment.presenter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.model.SingleUser
import tech.tsdev.github_management.model.github.GithubRepository

class MyInfoPresenter(
    private val view: MyInfoContract.View,
    private val githubRepository: GithubRepository
) : MyInfoContract.Presenter {
    override fun getUserInfoBasedOnUserName(userName: String?) {
        userName?.let {
            githubRepository.getSingleUser(it).enqueue(object : Callback<SingleUser> {
                override fun onFailure(call: Call<SingleUser>, t: Throwable) {
                    view.showLoadFailMessage()
                }

                override fun onResponse(call: Call<SingleUser>, response: Response<SingleUser>) {
                    if(response.isSuccessful) {
                        response.body()?.let { singleUser ->
                            view.showGetSingleUserDetailInfo(
                                singleUser.name,
                                singleUser.bio,
                                singleUser.email,
                                singleUser.blog
                            )
                        } ?: let { view.showLoadFailMessage(response.errorBody().toString()) }
                    }
                }

            })
        }
    }

}