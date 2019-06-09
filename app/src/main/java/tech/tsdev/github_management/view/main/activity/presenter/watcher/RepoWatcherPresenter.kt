package tech.tsdev.github_management.view.main.activity.presenter.watcher

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.model.GetRepoSubscribers
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.view.main.activity.adapter.watcher.model.RepoWatcherRecyclerModel

class RepoWatcherPresenter(
    private val view: RepoWatcherContract.View,
    private val githubRepository: GithubRepository,
    private val repoWatcherRecyclerModel: RepoWatcherRecyclerModel
) : RepoWatcherContract.Presenter {

    private var perPage = 0
    var isLoading = false
    var nextPage = true

    override fun loadWatcherUserListBasedRepoUrl(repoUrl: String?) {
        isLoading = true

        if (nextPage) {
            repoUrl?.let {
                githubRepository.getRepoSubscribeUserList(it, ++perPage).enqueue(object :
                    Callback<List<GetRepoSubscribers>> {
                    override fun onFailure(call: Call<List<GetRepoSubscribers>>, t: Throwable) {
                        view.loadWatcherUserErrorMessage()

                        isLoading = false
                    }

                    override fun onResponse(
                        call: Call<List<GetRepoSubscribers>>,
                        response: Response<List<GetRepoSubscribers>>
                    ) {
                        if (response.isSuccessful) {
                            if (perPage == 1) {
                                response.body().takeIf { body -> body.isNullOrEmpty() }?.run {
                                    view.showRepoSubscribeEmptyUser()
                                }
                            }
                            response.body()?.takeIf { body -> body.isNullOrEmpty() }?.run {
                                nextPage = false
                            }

                            response.body()?.let { repoSubscriberUserList ->
                                repoSubscriberUserList.forEach { repoSubscriberUser ->
                                    repoWatcherRecyclerModel.addItems(repoSubscriberUser)
                                }
                                repoWatcherRecyclerModel.notifiedDataItems()
                            } ?: let {
                                view.loadWatcherUserErrorMessage(response.errorBody().toString())
                            }
                        }
                    }


                })
                isLoading = false
            }
        }
    }

}