package tech.tsdev.github_management.view.main.starfragment.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.model.ReceivedEvents
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.util.plusAssign
import tech.tsdev.github_management.view.main.starfragment.adapter.model.StarRecyclerModel

class StarFragmentPresenter(
    val view: StarFragmentContract.View,
    private val githubRepository: GithubRepository,
    private val starRecyclerModel: StarRecyclerModel,
    private val disposable: CompositeDisposable
) : StarFragmentContract.Presenter {

    init {
        starRecyclerModel.onClick = { position ->
            view.getDetailRepository(
                starRecyclerModel.getItem(position).repo.url
            )
        }
    }

    override fun getResultReceivedBasedOnUserName(userName: String) {
        disposable += githubRepository.getUserReceivedResult(userName).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).subscribe({ listReceivedEvent ->
                starRecyclerModel.clearItemData()
                listReceivedEvent.forEach {
                    starRecyclerModel.addItem(it)
                }
                starRecyclerModel.nofityedItemsData()
                view.dismissLottieProgressbar()
            }, {
                view.dismissLottieProgressbar()

                view.loadFailedMessage()
            })
    }
}