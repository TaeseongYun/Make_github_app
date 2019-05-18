package tech.tsdev.github_management.view.main.myfragment.viewpagerfragment.presenter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.model.GetUserStarred
import tech.tsdev.github_management.model.github.GithubRepository

class MyUserStarPresenter(private val view: MyUserStarContract.View,
                          private val githubRepository: GithubRepository): MyUserStarContract.Presenter {
    override fun getUserGiveStarBasedOnUserName(userName: String) {
        githubRepository.getStarBasedonUserName(userName).enqueue(object : Callback<List<GetUserStarred>>{
            override fun onFailure(call: Call<List<GetUserStarred>>, t: Throwable) {
                view.loadFailRepoMessage()
            }

            override fun onResponse(call: Call<List<GetUserStarred>>, response: Response<List<GetUserStarred>>) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

}