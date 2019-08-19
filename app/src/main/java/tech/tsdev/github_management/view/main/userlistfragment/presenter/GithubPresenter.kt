package tech.tsdev.github_management.view.main.userlistfragment.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.util.plusAssign
import tech.tsdev.github_management.view.main.userlistfragment.adapter.model.UserRecyclerModel

class GithubPresenter(
    private val view: GithubContract.View,
    private val githubRepository: GithubRepository,
    private val userRecyclerModel: UserRecyclerModel,
    private val disposable: CompositeDisposable
) : GithubContract.Presenter {


    var isLoading = true
    var since = 30

    init {
        userRecyclerModel.onClick = { position ->
            view.loadDetailUser(userRecyclerModel.getItem(position).login)
        }
    }

    override fun loadGithubUser() {
        isLoading = true
        view.showProgressbar()

        disposable += githubRepository.loadUserList(since)
            .subscribeOn(Schedulers.io())
            .map { userList ->
                userList.forEach {
                    userRecyclerModel.addItem(it)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                userRecyclerModel.notifyDataItem()

                view.dissmissProgressbar()

                since += 10
                isLoading = false
            }, {
                isLoading = false
                view.dissmissProgressbar()

                view.loadFailMessage()
            })
    }
}