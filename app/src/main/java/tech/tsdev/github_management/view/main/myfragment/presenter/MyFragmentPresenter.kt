package tech.tsdev.github_management.view.main.myfragment.presenter


import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.util.plusAssign


class MyFragmentPresenter(
    private val view: MyFragmentContract.View,
    private val githubRepository: GithubRepository,
    private val disposable: CompositeDisposable
) : MyFragmentContract.Presneter {
    override fun inputUserNameLoad(userName: String?) {
        userName?.let { name ->
            disposable += githubRepository.getSingleUser(name)
                .observeOn(AndroidSchedulers.mainThread())
                .map { singleUser ->
                    view.loadUserDetailInfo(
                        singleUser.avatarUrl,
                        singleUser.avatarUrl,
                        singleUser.login,
                        singleUser.location,
                        singleUser.joinToGithubDate(singleUser.createdAt)
                    )
                }
                .subscribeOn(Schedulers.io())
                .subscribe({
                    view.dismissProgressBar()
                }, {
                    it.printStackTrace()
                    view.loadViewToastMessage()
                })
        }
    }
}