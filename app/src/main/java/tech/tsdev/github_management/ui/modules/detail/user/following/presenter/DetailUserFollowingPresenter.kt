package tech.tsdev.github_management.ui.modules.detail.user.following.presenter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.model.UserFollowersFollowingList
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.ui.modules.detail.user.following.adapter.model.UserFollowingRecyclerModel

class DetailUserFollowingPresenter(
    private val view: DetailUserFollowingContract.View,
    private val githubRepository: GithubRepository,
    private val userFollowingModel: UserFollowingRecyclerModel
) : DetailUserFollowingContract.Presenter {
    override fun loadUserFollowingBasedUserName(userName: String) {
        githubRepository.getUserFollowing(userName).enqueue(object : Callback<List<UserFollowersFollowingList>> {
            override fun onFailure(call: Call<List<UserFollowersFollowingList>>, t: Throwable) {
                view.loadFailMessage()
            }

            override fun onResponse(
                call: Call<List<UserFollowersFollowingList>>,
                response: Response<List<UserFollowersFollowingList>>
            ) {
                if(response.isSuccessful) {
                    response.body()?.let { userFollowingList ->
                        userFollowingModel.deleteItemDate()
                        userFollowingList.forEach { userFollowing ->
                            userFollowingModel.addItem(userFollowing)
                        }
                        userFollowingModel.notifyItemsDate()
                    } ?: let { view.loadFailMessage(response.errorBody().toString()) }
                }
            }

        })
    }

}