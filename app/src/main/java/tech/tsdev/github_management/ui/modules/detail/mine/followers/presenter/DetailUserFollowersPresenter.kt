package tech.tsdev.github_management.ui.modules.detail.mine.followers.presenter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.base.recycler.model.BaseRecyclerModel
import tech.tsdev.github_management.model.UserFollowersFollowingList
import tech.tsdev.github_management.model.github.GithubRepository

class DetailUserFollowersPresenter(
    private val view: DetailUserFollowersContract.View,
    private val githubRepository: GithubRepository,
    private val followersRecyclerModel: BaseRecyclerModel<UserFollowersFollowingList>
) : DetailUserFollowersContract.Presenter {

    init {
        followersRecyclerModel.onClick = { position ->
            view.justShowToast()
        }
    }
    override fun getFollowersBasedOnUserName(userName: String) {
        githubRepository.getUserFollowers(userName).enqueue(object : Callback<List<UserFollowersFollowingList>> {
            override fun onFailure(call: Call<List<UserFollowersFollowingList>>, t: Throwable) {
                view.loadFailUserFollowersMessage()
            }

            override fun onResponse(call: Call<List<UserFollowersFollowingList>>, response: Response<List<UserFollowersFollowingList>>) {
                if (response.isSuccessful) {
                    response.body()?.let { userFollowersList ->
                        followersRecyclerModel.clearItem()
                        userFollowersList.forEach { followerList ->
                            followersRecyclerModel.addItem(followerList)
                        }
                        followersRecyclerModel.notifyDataItems()
                    } ?: let { view.loadFailUserFollowersMessage(response.errorBody().toString()) }
                }
            }

        })
    }

}