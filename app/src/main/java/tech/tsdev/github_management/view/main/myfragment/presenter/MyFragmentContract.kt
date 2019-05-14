package tech.tsdev.github_management.view.main.myfragment.presenter

interface MyFragmentContract {
    interface View{
        fun loadViewToastMessage()

        fun loadFailToastMessage(message: String)

        fun loadUserDetailInfo(userAvatar: String,
                               userBackground: String,
                               userLogin: String,
                               userLocation: Any?,
                               userJoinTime: String)

        fun showProgressBar()

        fun dismissProgressBar()

    }

    interface Presneter{
        fun inputUserNameLoad(userName: String?)
    }
}