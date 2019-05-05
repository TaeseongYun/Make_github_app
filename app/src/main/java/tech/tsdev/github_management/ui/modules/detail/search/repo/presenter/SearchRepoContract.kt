package tech.tsdev.github_management.ui.modules.detail.search.repo.presenter

interface SearchRepoContract {
    interface View {
        fun successLoadView()

        fun failLoadView()
    }

    interface Presenter {
        fun loadRepoBasedSearchSentense(repoName: String?)
    }
}