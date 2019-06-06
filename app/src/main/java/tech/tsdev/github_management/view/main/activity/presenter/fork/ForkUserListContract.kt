package tech.tsdev.github_management.view.main.activity.presenter.fork

interface ForkUserListContract {
    interface View {
        fun forkUserListLoadFailMessage()

        fun forkUserListLoadFailMessage(message: String)

        fun showNothingUserLottieAni()
    }

    interface Presenter {
        fun getLoadForkUserListBasedForkUrl(repoForkUrl: String?)
    }
}