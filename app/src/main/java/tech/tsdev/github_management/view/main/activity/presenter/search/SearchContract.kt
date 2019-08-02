package tech.tsdev.github_management.view.main.activity.presenter.search

interface SearchContract {
    interface View{
        fun loadErrorMessage()

        fun loadErrorMessage(message: String)

        fun loadDetailActivity(userName: String)

        fun showSuccessLayout()
    }

    interface Presenter {
        fun searchLoadUsers(userName: String)
    }
}