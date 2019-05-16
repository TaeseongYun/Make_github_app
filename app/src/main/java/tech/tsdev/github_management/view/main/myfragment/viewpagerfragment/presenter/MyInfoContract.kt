package tech.tsdev.github_management.view.main.myfragment.viewpagerfragment.presenter

interface MyInfoContract {
    interface View {

    }

    interface Presenter {
        fun getUserInfoBasedOnUserName(userName: String?)
    }
}