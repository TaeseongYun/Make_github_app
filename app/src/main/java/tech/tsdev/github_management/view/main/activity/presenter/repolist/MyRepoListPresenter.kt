package tech.tsdev.github_management.view.main.activity.presenter.repolist

import tech.tsdev.github_management.model.github.GithubRepository

class MyRepoListPresenter(private val view: MyRepoListContract.View,
                          private val githubRepository: GithubRepository) : MyRepoListContract.Presenter {

}