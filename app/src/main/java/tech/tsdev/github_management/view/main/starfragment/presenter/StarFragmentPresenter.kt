package tech.tsdev.github_management.view.main.starfragment.presenter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.model.ReceivedEvents
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.view.main.starfragment.adapter.model.StarRecyclerModel

class StarFragmentPresenter(
    val view: StarFragmentContract.View,
    private val githubRepository: GithubRepository,
    private val starRecyclerModel: StarRecyclerModel
) : StarFragmentContract.Presenter {

    init {
        starRecyclerModel.onClick = { position ->
            view.getDetailRepository(starRecyclerModel.getItem(position).repo.name)
        }
    }

    override fun getResultReceivedBasedOnUserName(userName: String) {
        githubRepository.getUserReceivedResult(userName).enqueue(object : Callback<List<ReceivedEvents>> {
            override fun onFailure(call: Call<List<ReceivedEvents>>, t: Throwable) {
                view.loadFailedMessage()
            }

            override fun onResponse(call: Call<List<ReceivedEvents>>, response: Response<List<ReceivedEvents>>) {
                if (response.isSuccessful) {
                    response.body()?.let { listReceivedEvent ->
                        starRecyclerModel.clearItemData()
                        listReceivedEvent.forEach { receivedEvent ->
                            starRecyclerModel.addItem(receivedEvent)
                        }
                        starRecyclerModel.nofityedItemsData()
                    } ?: let {
                        view.loadFailMessage(response.errorBody().toString())
                    }
                }
            }

        })
    }


}