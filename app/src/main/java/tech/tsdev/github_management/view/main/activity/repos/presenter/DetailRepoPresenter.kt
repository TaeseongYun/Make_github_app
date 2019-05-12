package tech.tsdev.github_management.view.main.activity.repos.presenter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.model.GetSingleRepo
import tech.tsdev.github_management.model.github.GithubRepository

class DetailRepoPresenter(
    val view: DetailRepoContract.View,
    private val githubRepository: GithubRepository
) : DetailRepoContract.Presneter {
    override fun getLoadRepoInfo(ownerName: String, repoName: String) {
        githubRepository.getRepoInfoBasedOnOwnerNameRepoName(ownerName, repoName)
            .enqueue(object : Callback<GetSingleRepo>{
                override fun onFailure(call: Call<GetSingleRepo>, t: Throwable) {
                    view.loadFailedMessage()
                }

                override fun onResponse(call: Call<GetSingleRepo>, response: Response<GetSingleRepo>) {
                    if(response.isSuccessful) {
                        response.body()?.let {

                        } ?: let {
                            view.loadFailedMessage(response.errorBody().toString())
                        }
                    }
                }

            })
    }

}