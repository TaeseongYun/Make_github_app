package tech.tsdev.github_management.ui.modules.detail.search.repo.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import tech.tsdev.github_management.base.recycler.model.BaseRecyclerModel
import tech.tsdev.github_management.model.search.RepoItem
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.util.plusAssign


class SearchRepoPresenter(
    private val view: SearchRepoContract.View,
    private val githubRepository: GithubRepository,
    private val githubRecyclerView: BaseRecyclerModel<RepoItem>,
    private val disposable: CompositeDisposable
) : SearchRepoContract.Presenter {
    init {
        githubRecyclerView.onClick = { position ->
            view.justLoadToast()
        }
    }

    override fun loadRepoBasedSearchSentense(repoName: String?) {
        // network 패키지에서 githubInterface 안에 레파지토리 부를 함수 만들어서 레파지토리 까지 함수 만들어야함
        view.successLoadView()
        repoName?.let {
            disposable += githubRepository.getSearchRepo(it)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { searchRepoData ->
                    if(searchRepoData.items.isEmpty())
                        view.failLoadView()
                    searchRepoData.items.forEach { repoItem ->
                        githubRecyclerView.addItem(repoItem)
                    }
                }
                .subscribe({
                    githubRecyclerView.notifyDataItems()
                }, {
                    view.failLoadView()
                })
        }
    }
}