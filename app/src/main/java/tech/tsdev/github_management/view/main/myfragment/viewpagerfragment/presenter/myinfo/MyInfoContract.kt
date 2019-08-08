package tech.tsdev.github_management.view.main.myfragment.viewpagerfragment.presenter.myinfo



interface MyInfoContract {
    interface View {
        fun showGetSingleUserDetailInfo(
            userName: String?,
            userBio: String?,
            userEmail: String?,
            userWeb: String?
        )

        fun dismissLottieView()

        fun getReposPreview(userName: String?)

        fun getUserManyFollowerFollowing(userFollowers: Int?, userFollowings: Int?)

        fun showLoadFailMessage()

        fun showLoadFailMessage(message: String)
    }

    interface Presenter {
        fun getUserInfoBasedOnUserName(userName: String?)

    }
}