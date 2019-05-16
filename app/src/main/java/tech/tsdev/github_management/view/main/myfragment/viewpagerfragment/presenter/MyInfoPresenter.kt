package tech.tsdev.github_management.view.main.myfragment.viewpagerfragment.presenter

import tech.tsdev.github_management.model.github.GithubRepository

class MyInfoPresenter(val view: MyInfoContract.View,
                      val githubRepository: GithubRepository) : MyInfoContract.Presenter {

}