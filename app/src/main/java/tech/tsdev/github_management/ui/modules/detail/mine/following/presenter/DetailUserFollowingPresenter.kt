package tech.tsdev.github_management.ui.modules.detail.mine.following.presenter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.base.recycler.model.BaseRecyclerModel
import tech.tsdev.github_management.model.UserFollowersFollowingList
import tech.tsdev.github_management.model.github.GithubRepository


class DetailUserFollowingPresenter(
    private val view: DetailUserFollowingContract.View,
    private val githubRepository: GithubRepository,
    private val userFollowingModel: BaseRecyclerModel<UserFollowersFollowingList>
) : DetailUserFollowingContract.Presenter {

    init {
        userFollowingModel.onClick = {
            view.justViewToast()
        }
    }
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
                        userFollowingModel.clearItem()
                        userFollowingList.forEach { userFollowing ->
                            userFollowingModel.addItem(userFollowing)
                        }
                        userFollowingModel.notifyDataItems()
                    } ?: let { view.loadFailMessage(response.errorBody().toString()) }
                }
            }

        })
    }

}