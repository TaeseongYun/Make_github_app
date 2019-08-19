package tech.tsdev.github_management.view.main.activity.presenter.repolist

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.model.repo.UserRepoList
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.util.plusAssign
import tech.tsdev.github_management.view.main.activity.adapter.repolist.model.MyRepoListRecyclerModel

class MyRepoListPresenter(
    private val view: MyRepoListContract.View,
    private val githubRepository: GithubRepository,
    private val myRepoListRecyclerModel: MyRepoListRecyclerModel,
    private val disposable: CompositeDisposable
) : MyRepoListContract.Presenter {

    var isLoading = true

    init {
        myRepoListRecyclerModel.onClick = { position ->
            view.getRepoDetailView(myRepoListRecyclerModel.getItem(position).url)
        }
    }

    override fun getUserRepoListBasedUserName(userName: String?) {
        userName?.let {
            disposable += githubRepository.getUserRepoList(it)
                .subscribeOn(Schedulers.io())
                .map { userRepoList ->
                    userRepoList.forEach { list ->
                        myRepoListRecyclerModel.addItem(list)
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    myRepoListRecyclerModel.notifyItemData()
                    isLoading = false
                }, {
                    it.printStackTrace()
                })
        }
    }
}