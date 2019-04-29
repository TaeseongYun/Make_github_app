package tech.tsdev.github_management.ui.modules.detail.overview.presenter

interface DetailUserOverviewContract {
    interface View {

        fun showFailMessage()

        fun showFailMessage(message: String)

        fun loadUserDetailView(
            userLogin: String,
            userName: String,
            userAvatar: String,
            userEmail: String
        )
    }

    interface Presenter {
        fun loadUserOverView(userName: String)
    }
}