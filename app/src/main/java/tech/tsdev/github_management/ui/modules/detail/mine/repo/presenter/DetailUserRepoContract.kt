package tech.tsdev.github_management.ui.modules.detail.mine.repo.presenter

interface DetailUserRepoContract {
    interface View {
        fun loadFailMessage()

        fun loadFailMessage(message: String)

        fun getLoadDetailMyRepository(repoUrl: String)

        fun justViewToast()
    }

    interface Presenter {
        fun getUserRepoBaseUserName(userName: String)
    }
}