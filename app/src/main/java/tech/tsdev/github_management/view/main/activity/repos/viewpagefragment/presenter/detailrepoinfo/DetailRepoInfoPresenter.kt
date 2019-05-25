package tech.tsdev.github_management.view.main.activity.repos.viewpagefragment.presenter.detailrepoinfo

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.model.GetSingleRepo
import tech.tsdev.github_management.model.github.GithubRepository

class DetailRepoInfoPresenter(private val view: DetailRepoInfoContract.View,
                              private val githubRepository: GithubRepository) : DetailRepoInfoContract.Presenter {
    override fun getLoadRepoInfoBasedRepoUrl(repoUrl: String) {
        githubRepository.getRepoInfoBasedOnOwnerNameRepoName(repoUrl).enqueue(object : Callback<GetSingleRepo>{
            override fun onFailure(call: Call<GetSingleRepo>, t: Throwable) {
                view.loadFailMessage()
            }

            override fun onResponse(call: Call<GetSingleRepo>, response: Response<GetSingleRepo>) {
                if(response.isSuccessful) {
                    response.body()?.let { singleRepo ->
                        view.showDetailRepoInfo(
                            singleRepo.fullName,
                            singleRepo.simpleDateCreateAt(singleRepo.createdAt),
                            singleRepo.openIssuesCount.toString(),
                            singleRepo.stargazersCount.toString(),
                            singleRepo.forksCount.toString(),
                            singleRepo.watchersCount.toString()
                        )
                    }
                }
            }

        })
    }

}