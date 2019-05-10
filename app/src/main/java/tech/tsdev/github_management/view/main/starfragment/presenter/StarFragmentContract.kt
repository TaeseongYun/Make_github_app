package tech.tsdev.github_management.view.main.starfragment.presenter

interface StarFragmentContract {
    interface View {

        fun loadFailedMessage()

        fun loadFailMessage(message: String)
    }

    interface Presenter {
        fun getResultReceivedBasedOnUserName(userName: String)
    }
}