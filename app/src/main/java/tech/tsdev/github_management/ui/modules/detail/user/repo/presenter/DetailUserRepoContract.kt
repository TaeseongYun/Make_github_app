package tech.tsdev.github_management.ui.modules.detail.user.repo.presenter

interface DetailUserRepoContract {
    interface View {
        fun loadFailMessage()

        fun loadFailMessage(message: String)

        fun getLoadDetailMyRepository(repoUrl: String)
    }

    interface Presenter {
        fun getUserRepoBaseUserName(userName: String)
    }
}