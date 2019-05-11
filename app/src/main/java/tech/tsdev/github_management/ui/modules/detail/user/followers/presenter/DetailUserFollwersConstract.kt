package tech.tsdev.github_management.ui.modules.detail.user.followers.presenter

interface DetailUserFollwersConstract {
    interface View{
        fun loadFailUserFollowersMessage()

        fun loadFailUserFollowersMessage(message: String)
    }

    interface Presenter {
        fun getFollowersBasedOnUserName(userName: String)
    }
}