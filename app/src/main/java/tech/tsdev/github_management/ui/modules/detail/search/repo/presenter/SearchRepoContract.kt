package tech.tsdev.github_management.ui.modules.detail.search.repo.presenter

interface SearchRepoContract {
    interface View {
        fun successLoadView()

        fun failLoadView()

        fun justLoadToast()
    }

    interface Presenter {
        fun loadRepoBasedSearchSentense(repoName: String?)
    }
}