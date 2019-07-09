package com.ppizil.setvisiblehint

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ParentAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {

    val list:ArrayList<Fragment> ? = ArrayList()

    override fun getItem(position: Int): Fragment? {
        list?.add( SubTabFragment.newInstance(position))
        return list?.get(position)
    }

    fun getFragment(pos : Int) : Fragment? {
        return list?.get(pos)
    }

    override fun getCount(): Int {
        return 2
    }



    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "첫번째"
            1 -> return "두번쨰"
            2 -> return "세번쨰"
        }
        return ""
    }

}