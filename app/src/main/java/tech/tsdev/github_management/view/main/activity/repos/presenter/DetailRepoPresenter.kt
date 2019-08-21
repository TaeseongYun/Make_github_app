package tech.tsdev.github_management.view.main.activity.repos.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.util.plusAssign

class DetailRepoPresenter(
    private val view: DetailRepoContract.View,
    private val githubRepository: GithubRepository,
    private val disposable: CompositeDisposable
) : DetailRepoContract.Presenter {
    override fun getLoadRepoInfo(repoUrl: String) {
        disposable +=
            githubRepository.getRepoInfoBasedOnOwnerNameRepoName(repoUrl)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ getSingleRepo ->
                    view.updateToolbarImg(
                        getSingleRepo.owner.avatarUrl,
                        getSingleRepo.name,
                        getSingleRepo.description,
                        getSingleRepo.owner.login
                    )
                    view.dismissProgressBar()
                }, {
                    it.printStackTrace()
                })
    }
}