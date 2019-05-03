package tech.tsdev.github_management.view.main.searchactiviry.presenter

interface SearchContract {
    interface View{
        fun loadErrorMessage()

        fun loadErrorMessage(message: String)

        fun loadDetailActivity(userName: String)

        fun showFailLayout()

        fun showSuccessLayout()
    }

    interface Presenter {
        fun searchLoadUsers(userName: String)
    }
}