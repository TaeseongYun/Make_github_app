package tech.tsdev.github_management.view.main.activity.presenter.stargazers

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.model.GetRepoStarredUserList
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.view.main.activity.adapter.stargazers.model.StarredUserListRecyclerModel

class RepoStarredUserListPresenter(
    private val view: RepoStarredUserListContract.View,
    private val githubRepository: GithubRepository,
    private val starredUserListRecyclerModel: StarredUserListRecyclerModel
) : RepoStarredUserListContract.Presenter {

    var isLoading = false

    var nextPageSearch = true
    private var perPage = 0

    override fun loadRepoStarredUserList(repoStarredUserList: String?) {
        isLoading = true

        if(nextPageSearch) {
            repoStarredUserList?.let {
                githubRepository.getRepoStarredUserList(it, ++perPage).enqueue(object :
                    Callback<List<GetRepoStarredUserList>> {
                    override fun onFailure(call: Call<List<GetRepoStarredUserList>>, t: Throwable) {
                        view.loadFailGithubApi()

                        isLoading = false
                    }

                    override fun onResponse(
                        call: Call<List<GetRepoStarredUserList>>,
                        response: Response<List<GetRepoStarredUserList>>
                    ) {
                        if (response.isSuccessful) {
                            if (perPage == 1) {
                                response.body().takeIf { body -> body.isNullOrEmpty() }?.run {
                                    view.emptyStargazerUserList()
                                }
                            }
                            response.body().takeIf { body -> body.isNullOrEmpty() }?.run {
                                nextPageSearch = false
                            }
                            response.body()?.let { starredUserList ->
                                starredUserList.forEach { starredUser ->
                                    starredUserListRecyclerModel.addItems(starredUser)
                                }
                                starredUserListRecyclerModel.notifyedItemData()
                            } ?: let {
                                view.loadFailGithubApi(response.errorBody().toString())
                            }
                        }
                        isLoading = false
                    }

                })
            }
        }
    }
}