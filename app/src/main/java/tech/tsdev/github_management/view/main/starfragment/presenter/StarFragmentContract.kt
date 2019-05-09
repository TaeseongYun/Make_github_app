package tech.tsdev.github_management.view.main.starfragment.presenter

interface StarFragmentContract {
    interface View {

    }

    interface Presenter {
        fun loadNewsBaseOnUserName(
            userAvatar: String,
            userName: String,
            userInfo: String,
            userDate: String
        )
    }
}