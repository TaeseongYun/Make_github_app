package tech.tsdev.github_management.ui.modules.detail.mine.following.presenter

interface DetailUserFollowingContract {
    interface View{
        fun loadFailMessage()

        fun loadFailMessage(message: String)

        fun justViewToast()
    }

    interface Presenter{
        fun  loadUserFollowingBasedUserName(userName: String)
    }
}