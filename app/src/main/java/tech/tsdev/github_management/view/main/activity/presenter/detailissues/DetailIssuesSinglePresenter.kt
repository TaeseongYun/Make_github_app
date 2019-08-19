package tech.tsdev.github_management.view.main.activity.presenter.detailissues

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.util.plusAssign
import tech.tsdev.github_management.view.main.activity.adapter.comments.model.DetailIssuesCommentsRecyclerModel

class DetailIssuesSinglePresenter(
    private val view: DetailIssuesSingleContract.View,
    private val githubRepository: GithubRepository,
    private val detailIssuesCommentsModel: DetailIssuesCommentsRecyclerModel,
    private val disposable: CompositeDisposable
) : DetailIssuesSingleContract.Presenter {

    override fun getSingleIssues(repoSingleIssuesUrl: String?) {
        repoSingleIssuesUrl?.let {
            disposable += githubRepository.getSingleRepoIssues(it)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ repoSingleIssues ->
                    view.getViewLoadToolbar(
                        repoSingleIssues.user.avatarUrl,
                        repoSingleIssues.number.toString(),
                        repoSingleIssues.comments.toString(),
                        repoSingleIssues.body
                    )
                }, {
                    it.printStackTrace()
                    view.getSingleIssuesLoadFailMessage()
                })
        }
    }

    override fun getIssuesCommentsList(repoIssuesCommentsList: String?) {
        repoIssuesCommentsList?.let {
            disposable += githubRepository.getIssuesCommentsList(it)
                .subscribeOn(Schedulers.io())
                .map { getIssuesCommentList ->
                    getIssuesCommentList.forEach { getIssuesComment ->
                        detailIssuesCommentsModel.addItems(getIssuesComment)
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    detailIssuesCommentsModel.nofityedItemData()
                }, {
                    it.printStackTrace()
                    view.getSingleIssuesLoadFailMessage()
                })
        }
    }
}