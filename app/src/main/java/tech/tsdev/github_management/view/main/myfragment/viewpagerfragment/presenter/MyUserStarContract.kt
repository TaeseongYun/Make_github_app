package tech.tsdev.github_management.view.main.myfragment.viewpagerfragment.presenter

interface MyUserStarContract {
    interface View {
        fun loadFailRepoMessage()
    }

    interface Presenter {
        fun getUserGiveStarBasedOnUserName(userName: String)
    }
}