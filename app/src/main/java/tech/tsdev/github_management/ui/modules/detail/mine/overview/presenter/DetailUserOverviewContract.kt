package tech.tsdev.github_management.ui.modules.detail.mine.overview.presenter

interface DetailUserOverviewContract {
    interface View {

        fun showFailMessage()

        fun showFailMessage(message: String)

        fun loadUserDetailView(
            userLogin: String?,
            userName: String? = "Empty User Name",
            userAvatar: String?,
            userEmail: String? = "Empty Email",
            userDescription: String?,
            userFollowers: Int,
            userFollowings: Int
        )
    }

    interface Presenter {
        fun loadUserOverView(userName: String)
    }
}