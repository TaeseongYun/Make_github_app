package tech.tsdev.github_management.ui.modules.detail.mine.followers.presenter

interface DetailUserFollowersContract {
    interface View{
        fun loadFailUserFollowersMessage()

        fun loadFailUserFollowersMessage(message: String)

        fun justShowToast()
    }

    interface Presenter {
        fun getFollowersBasedOnUserName(userName: String)
    }
}