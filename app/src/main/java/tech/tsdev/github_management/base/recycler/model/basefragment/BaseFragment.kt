package tech.tsdev.github_management.base.recycler.model.basefragment

import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment : Fragment() {
    protected val disposable = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()

        disposable.clear()
    }
}