package tech.tsdev.github_management.view.main.starfragment.presenter

interface StarFragmentContract {
    interface View {

        fun loadFailedMessage()

        fun loadFailMessage(message: String)

        fun loadNewsBaseOnUserName(
            userAvatar: String,
            userName: String,
            userInfo: String,
            userDate: String
        )
    }

    interface Presenter {
        fun getResultReceivedBasedOnUserName(userName: String)
    }
}