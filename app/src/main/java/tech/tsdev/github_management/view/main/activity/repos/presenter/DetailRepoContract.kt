package tech.tsdev.github_management.view.main.activity.repos.presenter

interface DetailRepoContract {
    interface View {
        fun loadFailedMessage()

        fun loadFailedMessage(message: String)

        fun updateToolbarImg(
            ownerAvatarImg: String,

        )
    }

    interface Presneter {
        fun getLoadRepoInfo(ownerName: String, repoName: String)
    }
}