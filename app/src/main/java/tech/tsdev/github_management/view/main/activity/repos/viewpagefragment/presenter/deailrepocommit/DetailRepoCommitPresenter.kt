package tech.tsdev.github_management.view.main.activity.repos.viewpagefragment.presenter.deailrepocommit

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.model.GetRepoCommitList
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.view.main.activity.repos.viewpagefragment.adapter.model.DetailRepoCommitRecyclerModel

class DetailRepoCommitPresenter(
    private val view: DetailRepoCommitContract.View,
    private val githubRepository: GithubRepository,
    private val repoCommitRecyclerModel: DetailRepoCommitRecyclerModel
) : DetailRepoCommitContract.Presenter {
    override fun loadRepoCommitListBaseRepoName(repoUrl: String?, commitUrl: String) {
        repoUrl?.let {
            githubRepository.getRepoCommitList(it).enqueue(object : Callback<List<GetRepoCommitList>> {
                override fun onFailure(call: Call<List<GetRepoCommitList>>, t: Throwable) {
                    view.loadFailCommitMessage()
                }

                override fun onResponse(
                    call: Call<List<GetRepoCommitList>>,
                    response: Response<List<GetRepoCommitList>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { repoCommitList ->
                            repoCommitList.forEach { getRepoCommit ->
                                repoCommitRecyclerModel.addItems(getRepoCommit)
                            }
                            repoCommitRecyclerModel.notifyedDateItem()
                        }
                    }
                }

            })
        }
    }

}