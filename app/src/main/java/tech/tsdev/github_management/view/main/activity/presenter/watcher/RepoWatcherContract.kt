package tech.tsdev.github_management.view.main.activity.presenter.watcher

interface RepoWatcherContract {
    interface View{
        fun loadWatcherUserErrorMessage()

        fun loadWatcherUserErrorMessage(message: String)

        fun showRepoSubscribeEmptyUser()
    }

    interface Presenter{
        fun loadWatcherUserListBasedRepoUrl(repoUrl: String?)
    }
}