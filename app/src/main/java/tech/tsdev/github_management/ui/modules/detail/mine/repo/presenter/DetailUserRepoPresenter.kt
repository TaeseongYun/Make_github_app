package tech.tsdev.github_management.ui.modules.detail.mine.repo.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.base.recycler.model.BaseRecyclerModel
import tech.tsdev.github_management.model.repo.UserRepoList
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.util.plusAssign


class DetailUserRepoPresenter(
    private val view: DetailUserRepoContract.View,
    private val githubRepository: GithubRepository,
    private val detailRepoRecyclerAdapter: BaseRecyclerModel<UserRepoList>,
    private val disposable: CompositeDisposable
) : DetailUserRepoContract.Presenter {

    init {
        detailRepoRecyclerAdapter.onClick = { position ->
            view.getLoadDetailMyRepository(detailRepoRecyclerAdapter.getItemData(position).url)
        }
    }

    override fun getUserRepoBaseUserName(userName: String) {
        disposable += githubRepository.getUserRepoList(userName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ userRepoList ->
                if(userRepoList.isEmpty())
                    view.showEmptyRepositoryLottie()
                else {
                    userRepoList.forEach { list ->
                        detailRepoRecyclerAdapter.addItem(list)
                    }
                }
                detailRepoRecyclerAdapter.notifyDataItems()
            }, {
                it.printStackTrace()
            })
    }

}