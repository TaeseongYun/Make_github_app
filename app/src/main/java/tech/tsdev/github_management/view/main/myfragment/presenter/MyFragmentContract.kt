package tech.tsdev.github_management.view.main.myfragment.presenter

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth



interface MyFragmentContract {
    interface View{
        fun loadViewToastMessage()

        fun loadFailToastMessage()
    }

    interface Presneter{
        fun githubLoginWithCridential(auth: FirebaseAuth, credential: AuthCredential)
    }
}