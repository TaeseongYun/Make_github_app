package tech.tsdev.github_management.view.main.activity.presenter.following

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.model.UserFollowersFollowingList
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.view.main.activity.adapter.following.model.MyFollowingModel

class MyFollowingPresenter(
    private val view: MyFollowingContract.View,
    private val githubRepository: GithubRepository,
    private val myFollowingModel: MyFollowingModel
) : MyFollowingContract.Presenter {
    override fun getFollowingUserListBasedUserName(userName: String?) {
        userName?.let {
            githubRepository.getUserFollowing(it).enqueue(object : Callback<List<UserFollowersFollowingList>> {
                override fun onFailure(call: Call<List<UserFollowersFollowingList>>, t: Throwable) {
                    view.loadFailedAnkoMessage()
                }

                override fun onResponse(
                    call: Call<List<UserFollowersFollowingList>>,
                    response: Response<List<UserFollowersFollowingList>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { userFollowingList ->
                            userFollowingList.forEach { userFolloingItems ->
                                myFollowingModel.addItems(userFolloingItems)
                            }
                            myFollowingModel.notifyItemsData()
                        }
                        view.dismissLottieProgressbar()
                    }
                }

            })
        }
    }
}