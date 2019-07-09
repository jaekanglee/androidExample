package com.ppizil.setvisiblehint

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class SubTabAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {
    val list:ArrayList<Fragment> ? = ArrayList()


    fun getFragment(pos : Int) : Fragment? {
        return list?.get(pos)
    }

    override fun getCount(): Int {
        return  3
    }

    override fun getItem(position: Int): Fragment? {
        list?.add(ContentFragment.newInstance(position))
        return list?.get(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> return "첫번째"
            1 -> return "두번쨰"
            2 -> return  "세번쨰"
        }
        return  ""
    }
}