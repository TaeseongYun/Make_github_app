package tech.tsdev.github_management.view.main.starfragment.presenter

interface StarFragmentContract {
    interface View {
        fun getDetailRepository(repoUrl: String)

        fun loadFailedMessage()

        fun loadFailMessage(message: String)

        fun dismissLottieProgressbar()
    }

    interface Presenter {
        fun getResultReceivedBasedOnUserName(userName: String)
    }
}