package tech.tsdev.github_management.ui.modules.detail.user.following.presenter

import tech.tsdev.github_management.model.github.GithubRepository

class DetailUserFollowingPresenter(val view: DetailUserFollowingContract.View,
                                   val githubRepository: GithubRepository,
                                   ) : DetailUserFollowingContract.Presenter {
    override fun loadUserFollowingBasedUserName(userName: String) {

    }

}