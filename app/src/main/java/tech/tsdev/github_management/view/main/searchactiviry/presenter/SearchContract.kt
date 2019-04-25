package tech.tsdev.github_management.view.main.searchactiviry.presenter

interface SearchContract {
    interface View{
        fun loadErrorMessage()

        fun loadErrorMessage(message: String)
    }

    interface Presenter {
        fun searchLoadUsers(userName: String)
    }
}