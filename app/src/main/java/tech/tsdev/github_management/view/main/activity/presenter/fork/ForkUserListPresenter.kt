package tech.tsdev.github_management.view.main.activity.presenter.fork

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.model.fork.GetForkUserList
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.util.plusAssign
import tech.tsdev.github_management.view.main.activity.adapter.fork.model.ForkUserListRecyclerModel

class ForkUserListPresenter(
    private val view: ForkUserListContract.View,
    private val githubRepository: GithubRepository,
    private val forkUserRecyclerModel: ForkUserListRecyclerModel,
    private val disposable: CompositeDisposable
) : ForkUserListContract.Presenter {

    var isLoading = false
    var nextPage = true
    var perPage = 0

    override fun getLoadForkUserListBasedForkUrl(repoForkUrl: String?) {
        isLoading = true
        if (nextPage) {
            repoForkUrl?.let {
                disposable += githubRepository.getRepoForkedUserList(it, ++perPage)
                    .subscribeOn(Schedulers.io())
                    .map { getForkUserList ->
                        getForkUserList.forEach { list ->
                            forkUserRecyclerModel.addItems(list)
                        }
                    }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        forkUserRecyclerModel.notifiedDataItems()
                        isLoading = false
                    },{
                        it.printStackTrace()
                    })

//                    .enqueue(object : Callback<List<GetForkUserList>> {
//                        override fun onFailure(call: Call<List<GetForkUserList>>, t: Throwable) {
//                            view.forkUserListLoadFailMessage()
//
//                            isLoading = false
//                        }
//
//                        override fun onResponse(
//                            call: Call<List<GetForkUserList>>,
//                            response: Response<List<GetForkUserList>>
//                        ) {
//                            if (response.isSuccessful) {
//                                response.body()?.takeIf { body -> body.isNullOrEmpty() }?.run {
//                                    nextPage = false
//                                    view.showNothingUserLottieAni()
//                                }
//                                response.body()?.let { forkUserList ->
//                                    forkUserList.forEach { forkUser ->
//                                        forkUserRecyclerModel.addItems(forkUser)
//                                    }
//                                    forkUserRecyclerModel.notifiedDataItems()
//                                }
//                            }
//                            isLoading = false
//                        }
//                    })
            }
        }
    }

}