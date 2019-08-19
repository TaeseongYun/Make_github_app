package tech.tsdev.github_management.view.main.activity.presenter.following

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.util.plusAssign
import tech.tsdev.github_management.view.main.activity.adapter.following.model.MyFollowingModel

class MyFollowingPresenter(
    private val view: MyFollowingContract.View,
    private val githubRepository: GithubRepository,
    private val myFollowingModel: MyFollowingModel,
    private val disposable: CompositeDisposable
) : MyFollowingContract.Presenter {
    override fun getFollowingUserListBasedUserName(userName: String?) {
        userName?.let {
            disposable += githubRepository.getUserFollowing(it)
                .subscribeOn(Schedulers.io())
                .map { userFollowersFollowingList ->
                    userFollowersFollowingList.forEach { list ->
                        myFollowingModel.addItems(list)
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    myFollowingModel.notifyItemsData()
                    view.dismissLottieProgressbar()
                }, {
                    it.printStackTrace()
                    view.loadFailedAnkoMessage()
                })
        }
    }
}