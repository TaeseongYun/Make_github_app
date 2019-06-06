package tech.tsdev.github_management.view.main.activity.presenter.stargazers

interface RepoStarredUserListContract {
    interface View {
        fun loadFailGithubApi()

        fun loadFailGithubApi(message: String)

        fun emptyStargazerUserList()
    }

    interface Presenter{
        fun loadRepoStarredUserList(repoStarredUserList: String?)
    }
}