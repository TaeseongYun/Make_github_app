package tech.tsdev.github_management.view.main.myfragment.viewpagerfragment.presenter.myinfo

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.model.UserRepoList
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.util.plusAssign

class MyInfoPresenter(
    private val view: MyInfoContract.View,
    private val githubRepository: GithubRepository,
    private val disposable: CompositeDisposable
) : MyInfoContract.Presenter {

    private var userRepos = 0


    override fun getUserInfoBasedOnUserName(userName: String?) {
        userName?.let { disposable +=
            githubRepository.getSingleUser(it)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( { singleUser ->
                    view.showGetSingleUserDetailInfo(
                        singleUser.name,
                        singleUser.bio,
                        singleUser.email,
                        singleUser.blog
                    )
                    view.getUserManyFollowerFollowing(
                        singleUser.followers,
                        singleUser.following
                    )
                }, {
                    it.printStackTrace()
                })
        }
        userName?.let {
            githubRepository.getUserRepoList(userName).enqueue(object : Callback<List<UserRepoList>> {
                override fun onFailure(call: Call<List<UserRepoList>>, t: Throwable) {
                    view.showLoadFailMessage()
                }

                override fun onResponse(call: Call<List<UserRepoList>>, response: Response<List<UserRepoList>>) {
                    if(response.isSuccessful) {
                        response.body()?.let { repoList ->
                            repoList.forEach {
                                userRepos++
                            }
                            view.getReposPreview(
                                "$userRepos Repositories"
                            )
                        }
                    }
                }

            })
        }
        view.dismissLottieView()
    }
}