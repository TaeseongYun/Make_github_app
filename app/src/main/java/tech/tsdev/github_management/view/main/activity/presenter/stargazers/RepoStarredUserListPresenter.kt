package tech.tsdev.github_management.view.main.activity.presenter.stargazers

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.model.repo.GetRepoStarredUserList
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.util.plusAssign
import tech.tsdev.github_management.view.main.activity.adapter.stargazers.model.StarredUserListRecyclerModel

class RepoStarredUserListPresenter(
    private val view: RepoStarredUserListContract.View,
    private val githubRepository: GithubRepository,
    private val starredUserListRecyclerModel: StarredUserListRecyclerModel,
    private val disposable: CompositeDisposable
) : RepoStarredUserListContract.Presenter {

    var isLoading = false

    var nextPageSearch = true
    private var perPage = 0

    override fun loadRepoStarredUserList(repoStarredUserList: String?) {
        isLoading = true

        if (nextPageSearch) {
            repoStarredUserList?.let {
                disposable += githubRepository.getRepoStarredUserList(it, ++perPage)
                    .subscribeOn(Schedulers.io())
                    .map { getRepoStarredUserList ->
                        getRepoStarredUserList.forEach { list ->
                            starredUserListRecyclerModel.addItems(list)
                        }
                    }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        starredUserListRecyclerModel.notifyedItemData()
                        isLoading = false
                    }, {
                        it.printStackTrace()
                    })
            }
        }
    }
}