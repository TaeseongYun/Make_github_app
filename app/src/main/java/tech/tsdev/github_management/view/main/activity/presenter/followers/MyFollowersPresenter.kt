package tech.tsdev.github_management.view.main.activity.presenter.followers

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.model.UserFollowersFollowingList
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.view.main.activity.adapter.followers.model.MyFollowersRecyclerModel

class MyFollowersPresenter(
    private val view: MyFollowersContract.View,
    private val githubRepository: GithubRepository,
    private val myFollowersRecyclerModel: MyFollowersRecyclerModel
) : MyFollowersContract.Presenter {

    init {
        view.dismissLottieProgressBar()
    }
    override fun loadUserFollowersBasedUserName(userName: String) {
        githubRepository.getUserFollowers(userName).enqueue(object : Callback<List<UserFollowersFollowingList>> {
            override fun onFailure(call: Call<List<UserFollowersFollowingList>>, t: Throwable) {
                view.loadFailedMessage()
            }

            override fun onResponse(
                call: Call<List<UserFollowersFollowingList>>,
                response: Response<List<UserFollowersFollowingList>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { userFollowersList ->
                        userFollowersList.forEach { userFollower ->
                            myFollowersRecyclerModel.addItems(userFollower)
                        }
                        myFollowersRecyclerModel.notifyItemsData()
                    }
                }
            }

        })

    }

}