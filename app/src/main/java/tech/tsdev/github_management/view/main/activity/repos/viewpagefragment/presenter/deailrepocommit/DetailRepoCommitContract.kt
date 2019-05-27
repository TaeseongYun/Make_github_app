package tech.tsdev.github_management.view.main.activity.repos.viewpagefragment.presenter.deailrepocommit

interface DetailRepoCommitContract {
    interface View {
        fun loadFailCommitMessage()

        fun loadFailCommitMessage(message: String?)
    }
    interface Presenter {
        fun loadRepoCommitListBaseRepoName(repoUrl: String?)
    }
}