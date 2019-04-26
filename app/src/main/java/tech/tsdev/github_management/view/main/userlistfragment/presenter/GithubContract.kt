package tech.tsdev.github_management.view.main.userlistfragment.presenter

interface GithubContract {
    interface View {
        fun showProgressbar()

        fun dissmissProgressbar()

        fun loadFailMessage()

        fun loadFailMessage(message: String)

        fun loadDetailUser(userName: String)
    }
    interface Presenter {
        fun loadGithubUser()
    }
}