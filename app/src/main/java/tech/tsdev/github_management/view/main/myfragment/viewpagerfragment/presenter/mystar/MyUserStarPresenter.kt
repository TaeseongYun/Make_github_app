package tech.tsdev.github_management.view.main.myfragment.viewpagerfragment.presenter.mystar

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.model.GetUserStarred
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.view.main.myfragment.viewpagerfragment.adapter.model.MyUserStarModel

class MyUserStarPresenter(
    private val view: MyUserStarContract.View,
    private val githubRepository: GithubRepository,
    private val userStarRecyclerModel: MyUserStarModel
) : MyUserStarContract.Presenter {

    init {
        userStarRecyclerModel.onClick = { position ->
            view.getDetailRepoBasedUserGivedStar(userStarRecyclerModel.getItem(position).url)
        }
    }
    override fun getUserGiveStarBasedOnUserName(userName: String) {

        githubRepository.getStarBasedonUserName(userName).enqueue(object : Callback<List<GetUserStarred>> {
            override fun onFailure(call: Call<List<GetUserStarred>>, t: Throwable) {
                view.loadFailRepoMessage()
            }

            override fun onResponse(call: Call<List<GetUserStarred>>, response: Response<List<GetUserStarred>>) {
                if (response.isSuccessful) {

                    response.body()?.let { userStarredList ->
                        userStarredList.forEach { userStarred ->
                            userStarRecyclerModel.additem(userStarred)
                        }
                        userStarRecyclerModel.notifyItemData()
                    }
                }
            }

        })
    }

}