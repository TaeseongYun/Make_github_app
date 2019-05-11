package tech.tsdev.github_management.ui.modules.detail.user.following.presenter

interface DetailUserFollowingContract {
    interface View{
        fun loadFailMessage()

        fun loadFailMessage(message: String)
    }

    interface Presenter{
        fun  loadUserFollowingBasedUserName(userName: String)
    }
}