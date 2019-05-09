package tech.tsdev.github_management.view.main.starfragment.presenter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.model.ReceivedEvents
import tech.tsdev.github_management.model.github.GithubRepository

class StarFragmentPresenter(
    val view: StarFragmentContract.View,
    private val githubRepository: GithubRepository
) : StarFragmentContract.Presenter {
    override fun getResultReceivedBasedOnUserName(userName: String) {
        githubRepository.getUserReceivedResult(userName).enqueue(object : Callback<List<ReceivedEvents>> {
            override fun onFailure(call: Call<List<ReceivedEvents>>, t: Throwable) {
                view.loadFailedMessage()
            }

            override fun onResponse(call: Call<List<ReceivedEvents>>, response: Response<List<ReceivedEvents>>) {
                if (response.isSuccessful) {
                    response.body()?.let { listReceivedEvent ->
                        listReceivedEvent.forEach { receivedEvent ->
                            receivedEvent.let {
                                view.loadNewsBaseOnUserName(
                                    it.actor.avatarUrl,
                                    it.actor.login,
                                    it.whichStarRepoORCreateRepo(it.type),
                                    it.createdAt
                                )
                            }
                        }
                    } ?: let {
                        view.loadFailMessage(response.errorBody().toString())
                    }
                }
            }

        })
    }


}