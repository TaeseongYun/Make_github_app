package tech.tsdev.github_management.ui.modules.detail.search.users

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tech.tsdev.github_management.R
import tech.tsdev.github_management.ui.modules.detail.search.users.presenter.SearchUserContract

class SearchUserFragment : Fragment(), SearchUserContract.View {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.pg_search_user_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}