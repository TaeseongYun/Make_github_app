package tech.tsdev.github_management.view.main.activity.presenter.followers

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.model.user.UserFollowersFollowingList
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.util.plusAssign
import tech.tsdev.github_management.view.main.activity.adapter.followers.model.MyFollowersRecyclerModel

class MyFollowersPresenter(
    private val view: MyFollowersContract.View,
    private val githubRepository: GithubRepository,
    private val myFollowersRecyclerModel: MyFollowersRecyclerModel,
    private val disposable: CompositeDisposable
) : MyFollowersContract.Presenter {

    init {
        view.dismissLottieProgressBar()
    }

    override fun loadUserFollowersBasedUserName(userName: String) {
        disposable += githubRepository.getUserFollowers(userName)
            .subscribeOn(Schedulers.io())
            .map { userFollowersList ->
                userFollowersList.forEach { userFollower ->
                    myFollowersRecyclerModel.addItems(userFollower)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                myFollowersRecyclerModel.notifyItemsData()
            }, {
                view.loadFailedMessage()
                it.printStackTrace()
            })
    }

}