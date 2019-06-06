package tech.tsdev.github_management.view.main.activity.repos.viewpagefragment.presenter.detailrepoinfo

interface DetailRepoInfoContract {
    interface View {
        fun loadFailMessage()

        fun loadFailMessage(message: String)

        fun showDetailRepoInfo(repoName: String?,
                               repoCreateAt: String?,
                               repoIssues: String?,
                               repoStargazers: String?,
                               repoForks: String?,
                               repoWatchers: String?)

        fun getOwnerRepoReadme(
            repoReadMeUrl: String?
        )

        //repoName  을 넘겨주기 위해 만듦
        fun getSendRepoNameRepoUrl(
            repoName: String?,
            repoUrl: String?
        )
    }

    interface Presenter {
        fun getLoadRepoInfoBasedRepoUrl(repoUrl: String)

        fun getLoadRepoReadmeBasedRepoUrl(repoUrl: String)
    }
}