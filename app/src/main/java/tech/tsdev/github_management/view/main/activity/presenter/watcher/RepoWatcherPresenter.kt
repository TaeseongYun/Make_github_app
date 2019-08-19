package tech.tsdev.github_management.view.main.activity.presenter.watcher

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.model.repo.GetRepoSubscribers
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.util.plusAssign
import tech.tsdev.github_management.view.main.activity.adapter.watcher.model.RepoWatcherRecyclerModel

class RepoWatcherPresenter(
    private val view: RepoWatcherContract.View,
    private val githubRepository: GithubRepository,
    private val repoWatcherRecyclerModel: RepoWatcherRecyclerModel,
    private val disposable: CompositeDisposable
) : RepoWatcherContract.Presenter {

    private var perPage = 0
    var isLoading = false
    var nextPage = true

    override fun loadWatcherUserListBasedRepoUrl(repoUrl: String?) {
        isLoading = true

        if (nextPage) {
            repoUrl?.let {
                disposable += githubRepository.getRepoSubscribeUserList(it, ++perPage)
                    .subscribeOn(Schedulers.io())
                    .map { getRepoSubscriber ->
                        getRepoSubscriber.forEach { list ->
                            repoWatcherRecyclerModel.addItems(list)
                        }
                    }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        repoWatcherRecyclerModel.notifiedDataItems()
                        isLoading = false
                    }, {
                        it.printStackTrace()
                    })
            }
        }
    }

}