package tech.tsdev.github_management.ui.modules.detail.mine.overview.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.ui.modules.detail.mine.overview.adapter.RepoRecyclerViewAdapter
import tech.tsdev.github_management.util.plusAssign

class DetailUserOverviewPresenter(
    private val view: DetailUserOverviewContract.View,
    private val repoRecyclerViewAdapter: RepoRecyclerViewAdapter,
    private val githubRepository: GithubRepository,
    private val disposable: CompositeDisposable
) : DetailUserOverviewContract.Presenter {

    init {
        repoRecyclerViewAdapter.onClick = { positon ->
            view.showFailMessage()
        }
    }

    override fun loadUserOverView(userName: String) {
        disposable += githubRepository.getSingleUser(userName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ singleUser ->
                if (singleUser.location.isNullOrEmpty())
                    view.showUserEmptyInfo()
                view.loadUserDetailView(
                    singleUser.login,
                    singleUser.name,
                    singleUser.avatarUrl,
                    singleUser.email,
                    singleUser.bio,
                    singleUser.followers,
                    singleUser.following,
                    singleUser.public_repos,
                    singleUser.location
                )
            }, {
                it.printStackTrace()
            })


        disposable += githubRepository.getUserRepoList(userName)
            .take(2)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { repoList ->
                repoList.forEach { repo ->
                    repoRecyclerViewAdapter.addItem(repo)
                }
            }
            .subscribe({
                repoRecyclerViewAdapter.notifyDataSetChanged()
            }, {
                it.printStackTrace()
            })
    }

}