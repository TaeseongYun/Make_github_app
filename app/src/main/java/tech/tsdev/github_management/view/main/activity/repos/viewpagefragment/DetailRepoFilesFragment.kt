package tech.tsdev.github_management.view.main.activity.repos.viewpagefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import tech.tsdev.github_management.R

class DetailRepoFilesFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.detail_repo_file_layout, container, false)
}