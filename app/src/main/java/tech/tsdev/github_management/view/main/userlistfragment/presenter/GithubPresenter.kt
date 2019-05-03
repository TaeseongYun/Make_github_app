package tech.tsdev.github_management.view.main.userlistfragment.presenter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tech.tsdev.github_management.BuildConfig
import tech.tsdev.github_management.model.UserListData
import tech.tsdev.github_management.model.github.GithubRepository
import tech.tsdev.github_management.view.main.userlistfragment.adapter.model.UserRecyclerModel

class GithubPresenter(
    val view: GithubContract.View,
    private val githubRepository: GithubRepository,
    val userRecyclerModel: UserRecyclerModel
) : GithubContract.Presenter {


    var isLoading = true
    var since = 30

    init{
        userRecyclerModel.onClick = { position ->
            view.loadDetailUser(userRecyclerModel.getItem(position).login)
        }
    }

    override fun loadGithubUser() {
        isLoading = true
//        view.dissmissProgressbar()
        githubRepository.loadUserList(since).enqueue(object : Callback<List<UserListData>> {
            override fun onFailure(call: Call<List<UserListData>>, t: Throwable) {
                isLoading = false
                view.dissmissProgressbar()

                view.loadFailMessage()
            }

            override fun onResponse(call: Call<List<UserListData>>, response: Response<List<UserListData>>) {
                if (response.isSuccessful) {

                    response.body()?.let { userList ->
                        userList.forEach {
                            userRecyclerModel.addItem(it)
                        }
                        userRecyclerModel.notifyDataItem()
                    } ?: let {
                        view.loadFailMessage(response.errorBody().toString())
                    }
                    since += 10
                } else {
                    view.loadFailMessage()

                }
                view.dissmissProgressbar()
                isLoading = false
            }

        })
    }
}