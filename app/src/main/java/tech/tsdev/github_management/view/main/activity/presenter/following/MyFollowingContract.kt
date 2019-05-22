package tech.tsdev.github_management.view.main.activity.presenter.following

interface MyFollowingContract {
    interface View {
        fun loadFailedAnkoMessage()

        fun loadFailedAnkoMessage(message: String)

        fun dismissLottieProgressbar()
    }

    interface Presenter {
        fun getFollowingUserListBasedUserName(userName: String?)
    }
}