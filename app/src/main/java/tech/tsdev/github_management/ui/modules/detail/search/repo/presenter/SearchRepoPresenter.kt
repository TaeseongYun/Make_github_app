package tech.tsdev.github_management.ui.modules.detail.search.repo.presenter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.model.SearchRepoData
import tech.tsdev.github_management.model.github.GithubRepository

class SearchRepoPresenter(
    val view: SearchRepoContract.View,
    private val githubRepository: GithubRepository
) : SearchRepoContract.Presenter {
    override fun loadRepoBasedSearchSentense(repoName: String) {
        // network 패키지에서 githubInterface 안에 레파지토리 부를 함수 만들어서 레파지토리 까지 함수 만들어야함
        githubRepository.getSearchRepo(repoName).enqueue(object : Callback<SearchRepoData>{
            override fun onFailure(call: Call<SearchRepoData>, t: Throwable) {
                view
            }

            override fun onResponse(call: Call<SearchRepoData>, response: Response<SearchRepoData>) {

            }

        })
    }

}