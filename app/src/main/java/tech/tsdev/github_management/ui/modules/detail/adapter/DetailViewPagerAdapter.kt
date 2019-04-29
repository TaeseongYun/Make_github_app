package tech.tsdev.github_management.ui.modules.detail.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.ViewGroup
import tech.tsdev.github_management.ui.modules.detail.followers.DetailUserFollwersFragment
import tech.tsdev.github_management.ui.modules.detail.following.DetailUserFollwingFragment
import tech.tsdev.github_management.ui.modules.detail.overview.DetailUserOverviewFragment
import tech.tsdev.github_management.ui.modules.detail.repo.DetailUserRepoFragment

class DetailViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {



    override fun getItem(position: Int): Fragment? =
        when (position) {
            0 -> DetailUserOverviewFragment()
            1 -> DetailUserRepoFragment()
            2 -> DetailUserFollwersFragment()
            3 -> DetailUserFollwingFragment()
            else -> null
        }




    override fun getCount(): Int = 4

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }

}