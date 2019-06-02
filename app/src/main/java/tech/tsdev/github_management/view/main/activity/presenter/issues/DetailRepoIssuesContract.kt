package tech.tsdev.github_management.view.main.activity.presenter.issues

interface DetailRepoIssuesContract {
    interface View{
        fun issuesLoadFailMessage()

        fun issuesLoadFailMessage(message: String)

        fun showEmptyIssuesAnimation()

        fun dismissEmptyIssuesAnimation()

        fun detailIssuesActivityIncludeComments(detailIssuesUrl: String?, detailCommentsUrl: String?)
    }

    interface Presenter {
        fun loadRepoIssuesDetailBasedIssuesUrl(repoIssuesUrl: String?)
    }
}