package tech.tsdev.github_management.ui.modules.detail.user.followers.presenter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.model.UserFollowersList
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.ui.modules.detail.user.followers.adapter.model.FollowersRecyclerModel

class DetailUserFollowersPresenter(
    private val view: DetailUserFollowersContract.View,
    private val githubRepository: GithubRepository,
    private val followersRecyclerModel: FollowersRecyclerModel
) : DetailUserFollowersContract.Presenter {
    override fun getFollowersBasedOnUserName(userName: String) {
        githubRepository.getUserFollowers(userName).enqueue(object : Callback<List<UserFollowersList>> {
            override fun onFailure(call: Call<List<UserFollowersList>>, t: Throwable) {
                view.loadFailUserFollowersMessage()
            }

            override fun onResponse(call: Call<List<UserFollowersList>>, response: Response<List<UserFollowersList>>) {
                if (response.isSuccessful) {
                    response.body()?.let { userFollowersList ->
                        followersRecyclerModel.deleteItemData()
                        userFollowersList.forEach { followerList ->
                            followersRecyclerModel.addItem(followerList)
                        }
                        followersRecyclerModel.notifyItemData()
                    } ?: let { view.loadFailUserFollowersMessage(response.errorBody().toString()) }
                }
            }

        })
    }

}