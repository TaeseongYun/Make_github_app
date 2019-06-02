package tech.tsdev.github_management.view.main.activity.presenter.detailissues

interface DetailIssuesSingleContract {

    interface View {
        fun getSingleIssuesLoadFailMessage()

        fun getSingleIssuesLoadFailMessage(message: String)

        fun getViewLoadToolbar(
            repoIsseusOwnerAvatar: String?,
            repoIssuesNum: String?,
            repoIssuesCommentNum: String?,
            repoIssuesBody: String?
        )
    }

    interface Presenter {
        fun getSingleIssues(repoSingleIssuesUrl: String?)
    }
}