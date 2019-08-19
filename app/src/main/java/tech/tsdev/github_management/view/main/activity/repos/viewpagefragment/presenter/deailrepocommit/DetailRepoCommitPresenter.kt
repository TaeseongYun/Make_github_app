package tech.tsdev.github_management.view.main.activity.repos.viewpagefragment.presenter.deailrepocommit

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.model.repo.GetRepoCommitList
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.util.plusAssign
import tech.tsdev.github_management.view.main.activity.repos.viewpagefragment.adapter.model.DetailRepoCommitRecyclerModel

class DetailRepoCommitPresenter(
    private val view: DetailRepoCommitContract.View,
    private val githubRepository: GithubRepository,
    private val repoCommitRecyclerModel: DetailRepoCommitRecyclerModel,
    private val disposable: CompositeDisposable
) : DetailRepoCommitContract.Presenter {

    var isLoading = false
    var nextPage = true
    var perPage = 0

    override fun loadRepoCommitListBaseRepoName(repoUrl: String?, commitUrl: String) {
        isLoading = true
        repoUrl?.let {
            if (nextPage) {
                disposable += githubRepository.getRepoCommitList(it, ++perPage)
                    .subscribeOn(Schedulers.io())
                    .map { repoCommitList ->
                        repoCommitList.forEach { list ->
                            repoCommitRecyclerModel.addItems(list)
                        }
                    }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        repoCommitRecyclerModel.notifyedDateItem()
                        isLoading = false
                    }, {
                        it.printStackTrace()
                        view.loadFailCommitMessage()
                    })
            }
        }
    }

}