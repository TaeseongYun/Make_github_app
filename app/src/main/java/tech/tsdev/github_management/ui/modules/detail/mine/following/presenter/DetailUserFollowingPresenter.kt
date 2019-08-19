package tech.tsdev.github_management.ui.modules.detail.mine.following.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.base.recycler.model.BaseRecyclerModel
import tech.tsdev.github_management.model.user.UserFollowersFollowingList
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.util.plusAssign


class DetailUserFollowingPresenter(
    private val view: DetailUserFollowingContract.View,
    private val githubRepository: GithubRepository,
    private val userFollowingModel: BaseRecyclerModel<UserFollowersFollowingList>,
    private val disposable: CompositeDisposable
) : DetailUserFollowingContract.Presenter {

    init {
        userFollowingModel.onClick = {
            view.justViewToast()
        }
    }

    override fun loadUserFollowingBasedUserName(userName: String) {
        disposable += githubRepository.getUserFollowing(userName)
            .subscribeOn(Schedulers.io())
            .map { userFollowingList ->
                userFollowingModel.clearItem()
                userFollowingList.forEach { list ->
                    userFollowingModel.addItem(list)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                userFollowingModel.notifyDataItems()
            }, {
                it.printStackTrace()
                view.loadFailMessage()
            })
    }
}