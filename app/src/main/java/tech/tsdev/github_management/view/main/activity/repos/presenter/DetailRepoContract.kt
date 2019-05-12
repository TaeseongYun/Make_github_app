package tech.tsdev.github_management.view.main.activity.repos.presenter

interface DetailRepoContract {
    interface View {
        fun loadFailedMessage()

        fun loadFailedMessage(message: String)

        fun updateToolbarImg(
            ownerAvatarImg: String,
            repoName: String,
            repoDescription: String?
        )
    }

    interface Presneter {
        fun getLoadRepoInfo(repoUrl: String)
    }
}