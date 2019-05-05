package tech.tsdev.github_management.ui.modules.detail.search.users.presenter

interface SearchUserContract {
    interface View {

    }

    interface Presenter {
        fun getSearchUserForQuery(userName: String)
    }
}