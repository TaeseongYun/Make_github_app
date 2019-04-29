package tech.tsdev.github_management.ui.modules.detail.overview.presenter

interface DetailUserOverviewContract {
    interface View {
        fun addTablayoutItem()

        fun showFailMessage()

        fun showFailMessage(message: String)

        fun loadUserDetailView()
    }

    interface Presenter {
        fun loadUserOverView(userName: String)
    }
}