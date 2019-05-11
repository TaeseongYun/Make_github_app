package tech.tsdev.github_management.ui.modules.detail.user.followers.presenter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.model.UserFollowersList
import tech.tsdev.github_management.model.github.GithubRepository

class DetailUserFollwersPresenter(val view: DetailUserFollwersConstract.View,
                                  private val githubRepository: GithubRepository) : DetailUserFollwersConstract.Presenter {
    override fun getFollowersBasedOnUserName(userName: String) {
        githubRepository.getUserFollowers(userName).enqueue(object : Callback<List<UserFollowersList>>{
            override fun onFailure(call: Call<List<UserFollowersList>>, t: Throwable) {
                view.loadFailUserFollowersMessage()
            }

            override fun onResponse(call: Call<List<UserFollowersList>>, response: Response<List<UserFollowersList>>) {
                if(response.isSuccessful) {
                    response.body()?.let { userFollowersList ->
                        userFollowersList.forEach { followerList ->

                        }
                    }
                }
            }

        })
    }

}