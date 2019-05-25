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
    }

    interface Presenter {
        fun getLoadRepoInfoBasedRepoUrl(repoUrl: String)
    }
}