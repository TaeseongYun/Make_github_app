package tech.tsdev.github_management.view.main.activity.presenter.issues

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.model.GetRepoIssuesList
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.view.main.activity.adapter.issues.model.DetailRepoIssuesListRecyclerModel

class DetailRepoIssuesPresenter(
    private val view: DetailRepoIssuesContract.View,
    private val githubRepository: GithubRepository,
    private val detailIRepoIssuesListRecyclerModel: DetailRepoIssuesListRecyclerModel
) : DetailRepoIssuesContract.Presenter {
    override fun loadRepoIssuesDetailBasedIssuesUrl(repoIssuesUrl: String?) {
        repoIssuesUrl?.let {
            githubRepository.getRepoIssuesList(it).enqueue(object : Callback<List<GetRepoIssuesList>> {
                override fun onFailure(call: Call<List<GetRepoIssuesList>>, t: Throwable) {
                    view.issuesLoadFailMessage()
                }

                override fun onResponse(call: Call<List<GetRepoIssuesList>>, response: Response<List<GetRepoIssuesList>>) {
                    if (response.isSuccessful) {
                        if(response.body().isNullOrEmpty())
                            view.showEmptyIssuesAnimation()
                        response.body()?.let { getRepoIssuesList ->
                            getRepoIssuesList.forEach { getRepoIssues ->
                                detailIRepoIssuesListRecyclerModel.addItems(getRepoIssues)
                            }
                            detailIRepoIssuesListRecyclerModel.notifiedItemData()
                        }
                    }
                }

            })
        }
    }
}