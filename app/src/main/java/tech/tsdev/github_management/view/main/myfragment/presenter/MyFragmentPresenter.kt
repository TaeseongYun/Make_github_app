package tech.tsdev.github_management.view.main.myfragment.presenter


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.model.SingleUser
import tech.tsdev.github_management.model.github.GithubRepository


class MyFragmentPresenter(
    val view: MyFragmentContract.View,
    private val githubRepository: GithubRepository
) : MyFragmentContract.Presneter {
    override fun inputUserNameLoad(userName: String?) {

        userName?.let { name ->
            githubRepository.getSingleUser(name).enqueue(object : Callback<SingleUser> {
                override fun onFailure(call: Call<SingleUser>, t: Throwable) {
                    view.loadViewToastMessage()
                }

                override fun onResponse(call: Call<SingleUser>, response: Response<SingleUser>) {
                    if (response.isSuccessful) {
                        response.body()?.let { singUser ->
                            view.loadUserDetailInfo(
                                singUser.avatarUrl,
                                singUser.login,
                                singUser.location,
                                singUser.joinToGithubDate(singUser.createdAt)
                            )
                        } ?: let {
                            view.loadFailToastMessage(response.errorBody().toString())
                        }
                    }
                }

            })
        }
    }


}