package tech.tsdev.github_management.view.main.activity.presenter.detailissues

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.model.GetIssuesComments
import tech.tsdev.github_management.model.GetRepoCommitList
import tech.tsdev.github_management.model.GetRepoIssuesList
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.view.main.activity.adapter.comments.model.DetailIssuesCommentsRecyclerModel

class DetailIssuesSinglePresenter(
    private val view: DetailIssuesSingleContract.View,
    private val githubRepository: GithubRepository,
    private val detailIssuesCommentsModel: DetailIssuesCommentsRecyclerModel
) : DetailIssuesSingleContract.Presenter {

    override fun getSingleIssues(repoSingleIssuesUrl: String?) {
        repoSingleIssuesUrl?.let {
            githubRepository.getSingleRepoIssues(it).enqueue(object : Callback<GetRepoIssuesList> {
                override fun onFailure(call: Call<GetRepoIssuesList>, t: Throwable) {
                    view.getSingleIssuesLoadFailMessage()
                }

                override fun onResponse(call: Call<GetRepoIssuesList>, response: Response<GetRepoIssuesList>) {
                    if (response.isSuccessful) {
                        response.body()?.let { repoSingleIssues ->
                            view.getViewLoadToolbar(
                                repoSingleIssues.user.avatarUrl,
                                repoSingleIssues.number.toString(),
                                repoSingleIssues.comments.toString(),
                                repoSingleIssues.body
                            )
                        } ?: let { view.getSingleIssuesLoadFailMessage(response.errorBody().toString()) }
                    }
                }

            })
        }
    }

    override fun getIssuesCommentsList(repoIssuesCommentsList: String?) {
        repoIssuesCommentsList?.let {
            githubRepository.getIssuesCommentsList(it).enqueue(object : Callback<List<GetIssuesComments>> {
                override fun onFailure(call: Call<List<GetIssuesComments>>, t: Throwable) {
                    view.getSingleIssuesLoadFailMessage()
                }

                override fun onResponse(
                    call: Call<List<GetIssuesComments>>,
                    response: Response<List<GetIssuesComments>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { repoCommentsList ->
                            repoCommentsList.forEach { repoComments ->
                                detailIssuesCommentsModel.addItems(repoComments)
                            }
                            detailIssuesCommentsModel.nofityedItemData()
                        }
                    }
                }

            })
        }
    }
}