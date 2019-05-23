package tech.tsdev.github_management.view.main.activity.presenter.repolist

interface MyRepoListContract {
    interface View {
        fun showLoadFailToastMessage()

        fun showLoadFailToastMessage(message: String)
    }

    interface Presenter {
        fun getUserRepoListBasedUserName(userName: String?)
    }
}