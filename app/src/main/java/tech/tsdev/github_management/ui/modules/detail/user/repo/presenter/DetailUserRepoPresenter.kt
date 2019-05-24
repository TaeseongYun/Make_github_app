package tech.tsdev.github_management.ui.modules.detail.user.repo.presenter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.model.UserRepoList
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.ui.modules.detail.user.repo.adapter.model.DetailRepoRecyclerModel

class DetailUserRepoPresenter(
    private val view: DetailUserRepoContract.View,
    private val githubRepository: GithubRepository,
    private val detailRepoRecyclerAdapter: DetailRepoRecyclerModel
) : DetailUserRepoContract.Presenter {

    init {
        detailRepoRecyclerAdapter.onClick = { position ->
            view.getLoadDetailMyRepository(detailRepoRecyclerAdapter.getItem(position).url)
        }
    }

    override fun getUserRepoBaseUserName(userName: String) {
        githubRepository.getUserRepoList(userName).enqueue(object : Callback<List<UserRepoList>> {
            override fun onFailure(call: Call<List<UserRepoList>>, t: Throwable) {
                view.loadFailMessage()
            }

            override fun onResponse(call: Call<List<UserRepoList>>, response: Response<List<UserRepoList>>) {
                if (response.isSuccessful) {
                    response.body()?.let { userRepoList ->
                        detailRepoRecyclerAdapter.deleteItemsData()
                        userRepoList.forEach { userRepo ->
                            detailRepoRecyclerAdapter.addItem(userRepo)
                        }
                        detailRepoRecyclerAdapter.notifiedItemData()
                    }
                }
            }

        })
    }

}