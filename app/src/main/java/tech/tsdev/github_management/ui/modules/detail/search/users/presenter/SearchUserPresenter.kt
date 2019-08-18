package tech.tsdev.github_management.ui.modules.detail.search.users.presenter


import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import tech.tsdev.github_management.base.recycler.model.BaseRecyclerModel
import tech.tsdev.github_management.model.Item
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.util.plusAssign


class SearchUserPresenter(
    private val view: SearchUserContract.View,
    private val githubRepository: GithubRepository,
    private val searchRecyclerAdapter: BaseRecyclerModel<Item>,
    private val disposable: CompositeDisposable
) : SearchUserContract.Presenter {

    // 리사이클러 onClick 고차함수만들어서 람다로  전해줌
    init {
        searchRecyclerAdapter.onClick = { position ->
            view.loadSearchUserDetail(searchRecyclerAdapter.getItemData(position).login)
        }
    }

    override fun getSearchUserForQuery(userName: String?) {
        userName?.let {
            disposable += githubRepository.searchUserList(it)
                .observeOn(AndroidSchedulers.mainThread())
                .map { searchUser ->
                    searchUser.items.forEach { item ->
                        searchRecyclerAdapter.addItem(item)
                    }
                }
                .subscribeOn(Schedulers.io())
                .subscribe({
                    searchRecyclerAdapter.notifyDataItems()
                }, {
                    it.printStackTrace()
                })
        }
    }

}