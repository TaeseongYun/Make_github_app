package tech.tsdev.github_management.view.main.activity.presenter.fork

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.model.GetForkUserList
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.view.main.activity.adapter.fork.model.ForkUserListRecyclerModel

class ForkUserListPresenter(
    private val view: ForkUserListContract.View,
    private val githubRepository: GithubRepository,
    private val forkUserRecyclerModel: ForkUserListRecyclerModel
) : ForkUserListContract.Presenter {

    var isLoading = false
    var nextPage = true
    var perPage = 0

    override fun getLoadForkUserListBasedForkUrl(repoForkUrl: String?) {
        isLoading = true
        if(nextPage) {
            repoForkUrl?.let {
                githubRepository.getRepoForkedUserList(it, ++perPage).enqueue(object : Callback<List<GetForkUserList>> {
                    override fun onFailure(call: Call<List<GetForkUserList>>, t: Throwable) {
                        view.forkUserListLoadFailMessage()

                        isLoading = false
                    }

                    override fun onResponse(
                        call: Call<List<GetForkUserList>>,
                        response: Response<List<GetForkUserList>>
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.takeIf { body -> body.isNullOrEmpty() }?.run {
                                nextPage = false
                                view.showNothingUserLottieAni()
                            }
                            response.body()?.let { forkUserList ->
                                forkUserList.forEach { forkUser ->
                                    forkUserRecyclerModel.addItems(forkUser)
                                }
                                forkUserRecyclerModel.notifiedDataItems()
                            }
                        }
                        isLoading = false
                    }
                })
            }
        }
    }

}