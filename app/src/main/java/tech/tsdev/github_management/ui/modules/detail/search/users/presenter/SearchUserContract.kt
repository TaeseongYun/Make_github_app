package tech.tsdev.github_management.ui.modules.detail.search.users.presenter

interface SearchUserContract {
    interface View {
        fun loadFailShowMessage()

        fun loadFailShowMessage(message: String)
    }

    interface Presenter {
        fun getSearchUserForQuery(userName: String?)
    }
}