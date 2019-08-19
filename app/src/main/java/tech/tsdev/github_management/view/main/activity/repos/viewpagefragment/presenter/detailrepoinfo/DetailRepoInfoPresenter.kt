package tech.tsdev.github_management.view.main.activity.repos.viewpagefragment.presenter.detailrepoinfo

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.util.plusAssign

class DetailRepoInfoPresenter(
    private val view: DetailRepoInfoContract.View,
    private val githubRepository: GithubRepository,
    private val disposable: CompositeDisposable
) : DetailRepoInfoContract.Presenter {

    override fun getLoadRepoInfoBasedRepoUrl(repoUrl: String) {
        disposable += githubRepository.getRepoInfoBasedOnOwnerNameRepoName(repoUrl)
            .subscribeOn(Schedulers.io())
            .map { singleRepo ->
                view.showDetailRepoInfo(
                    singleRepo.fullName,
                    singleRepo.simpleDateCreateAt(singleRepo.createdAt),
                    singleRepo.openIssuesCount.toString(),
                    singleRepo.stargazersCount.toString(),
                    singleRepo.forksCount.toString(),
                    singleRepo.subscribersCount.toString()
                )
                view.getSendRepoNameRepoUrl(
                    singleRepo.fullName,
                    singleRepo.url
                )
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

            }, {
                it.printStackTrace()
            })
    }

    override fun getLoadRepoReadmeBasedRepoUrl(repoUrl: String) {
        disposable += githubRepository.getRepoReadme(repoUrl)
            .subscribeOn(Schedulers.newThread())
            .map { repoReadme ->
                view.getOwnerRepoReadme(repoReadme.htmlUrl)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({

            }, {
                it.printStackTrace()
            })
    }
}