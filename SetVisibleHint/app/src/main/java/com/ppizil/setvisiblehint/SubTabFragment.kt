package com.ppizil.setvisiblehint

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_content.view.*

class SubTabFragment : Fragment() {

    companion object {

        fun newInstance(pos: Int): SubTabFragment {
            var subTabFragment: SubTabFragment = SubTabFragment()
            var bundle: Bundle = Bundle()
            bundle.putInt("pos", pos)
            subTabFragment.arguments = bundle
            return subTabFragment
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            if (isAdded) {
                var pos = viewpager.currentItem
                var fragment: Fragment? = subTabAdapter.getFragment(pos)
                if (fragment is ContentFragment) {
                    fragment.userVisibleHint = isVisibleToUser
                }
            }
        }
        else{
            if(isAdded)
            Toast.makeText(context,"애드안됨",Toast.LENGTH_LONG).show()
        }
    }

    lateinit var tabLayout: TabLayout
    lateinit var viewpager: ViewPager
    lateinit var subTabAdapter: SubTabAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = layoutInflater.inflate(R.layout.fragment_sub_tab, null)
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabLayout = view.findViewById(R.id.tablayout)

        viewpager = view.findViewById(R.id.viewpager)

        subTabAdapter = SubTabAdapter(childFragmentManager)

        viewpager.adapter = subTabAdapter

        tabLayout.setupWithViewPager(viewpager)

        isAdded
        isAdded

    }
}
