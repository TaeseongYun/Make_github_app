package tech.tsdev.github_management.view.main.myfragment.viewpagerfragment.presenter

interface MyUserStarContract {
    interface View {
        fun loadFailRepoMessage()

        fun getDetailRepoBasedUserGivedStar(repoUrl: String)
    }

    interface Presenter {
        fun getUserGiveStarBasedOnUserName(userName: String)


    }
}