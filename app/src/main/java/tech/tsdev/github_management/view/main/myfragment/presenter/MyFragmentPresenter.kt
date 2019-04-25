package tech.tsdev.github_management.view.main.myfragment.presenter


import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GithubAuthCredential


class MyFragmentPresenter(
    val view: MyFragmentContract.View
) : MyFragmentContract.Presneter {


    override fun githubLoginWithCridential(auth: FirebaseAuth, credential: AuthCredential) {
        auth.signInWithCredential(credential).addOnCompleteListener {

            view.loadViewToastMessage()

        }.addOnFailureListener {

            view.loadFailToastMessage()
        }
    }


}