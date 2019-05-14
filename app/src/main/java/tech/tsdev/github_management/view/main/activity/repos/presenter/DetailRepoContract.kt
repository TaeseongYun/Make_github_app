package tech.tsdev.github_management.view.main.activity.repos.presenter

interface DetailRepoContract {
    interface View {
        fun loadFailedMessage()

        fun loadFailedMessage(message: String)

        fun updateToolbarImg(
            ownerAvatarImg: String,
            repoName: String,
            repoDescription: String?,
            repoToolbarTitle: String
        )

        fun dismissProgressBar()
    }

    interface Presenter {
        fun getLoadRepoInfo(repoUrl: String)
    }
}