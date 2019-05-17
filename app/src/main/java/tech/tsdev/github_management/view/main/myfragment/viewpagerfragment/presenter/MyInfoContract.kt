package tech.tsdev.github_management.view.main.myfragment.viewpagerfragment.presenter

interface MyInfoContract {
    interface View {
        fun showGetSingleUserDetailInfo(
            userName: String?,
            userBio: String?,
            userEmail: String?,
            userWeb: String?
        )

        fun showLoadFailMessage()

        fun showLoadFailMessage(message: String)
    }

    interface Presenter {
        fun getUserInfoBasedOnUserName(userName: String?)
    }
}