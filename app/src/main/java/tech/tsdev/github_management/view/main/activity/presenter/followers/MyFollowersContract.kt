package tech.tsdev.github_management.view.main.activity.presenter.followers

interface MyFollowersContract {
    interface View {
        fun loadFailedMessage()

        fun loadFailedMessage(message: String)

        fun dismissLottieProgressBar()
    }

    interface Presenter {
        fun loadUserFollowersBasedUserName(userName: String)
    }
}