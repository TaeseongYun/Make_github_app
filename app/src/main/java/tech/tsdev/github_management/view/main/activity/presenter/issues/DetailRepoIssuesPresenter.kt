package tech.tsdev.github_management.view.main.activity.presenter.issues

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.model.repo.GetRepoIssuesList
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.util.plusAssign
import tech.tsdev.github_management.view.main.activity.adapter.issues.model.DetailRepoIssuesListRecyclerModel

class DetailRepoIssuesPresenter(
    private val view: DetailRepoIssuesContract.View,
    private val githubRepository: GithubRepository,
    private val detailIRepoIssuesListRecyclerModel: DetailRepoIssuesListRecyclerModel,
    private val disposable: CompositeDisposable
) : DetailRepoIssuesContract.Presenter {

    var perPage = 0
    var isLoading = false
    var nextPage = true

    init {
        detailIRepoIssuesListRecyclerModel.onClick = { position ->
            view.detailIssuesActivityIncludeComments(
                detailIRepoIssuesListRecyclerModel.getItems(position).url,
                detailIRepoIssuesListRecyclerModel.getItems(position).commentsUrl
            )
        }
    }

    override fun loadRepoIssuesDetailBasedIssuesUrl(repoIssuesUrl: String?) {
        isLoading = true
        if (nextPage) {
            repoIssuesUrl?.let {
                disposable += githubRepository.getRepoIssuesList(it, ++perPage)
                    .subscribeOn(Schedulers.io())
                    .map { getRepoIssuesList ->
                        getRepoIssuesList.forEach { list ->
                            detailIRepoIssuesListRecyclerModel.addItems(list)
                        }
                    }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        detailIRepoIssuesListRecyclerModel.notifiedItemData()
                    }, {
                        it.printStackTrace()
                    })
            }
        }
    }
}