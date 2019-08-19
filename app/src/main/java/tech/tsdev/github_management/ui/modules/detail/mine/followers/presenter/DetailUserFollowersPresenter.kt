package tech.tsdev.github_management.ui.modules.detail.mine.followers.presenter

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

class DetailUserFollowersPresenter(
    private val view: DetailUserFollowersContract.View,
    private val githubRepository: GithubRepository,
    private val followersRecyclerModel: BaseRecyclerModel<UserFollowersFollowingList>,
    private val disposable: CompositeDisposable
) : DetailUserFollowersContract.Presenter {

    init {
        followersRecyclerModel.onClick = { position ->
            view.justShowToast()
        }
    }

    override fun getFollowersBasedOnUserName(userName: String) {
        disposable += githubRepository.getUserFollowers(userName)
            .subscribeOn(Schedulers.io())
            .map { userFollowersFollowingList ->
                followersRecyclerModel.clearItem()
                userFollowersFollowingList.forEach { list ->
                    followersRecyclerModel.addItem(list)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                followersRecyclerModel.notifyDataItems()
            }, {
                it.printStackTrace()
                view.loadFailUserFollowersMessage()
            })
    }
}