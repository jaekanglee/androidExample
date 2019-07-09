package com.ppizil.setvisiblehint

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_content.view.*

class ContentFragment : Fragment() {

    companion object {
        fun newInstance(pos: Int): ContentFragment {
            var contentFragment: ContentFragment = ContentFragment()
            var bundle: Bundle = Bundle()
            bundle.putInt("pos", pos)
            contentFragment.arguments = bundle
            return contentFragment
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            if (isAdded) {
                var pos =  arguments?.getInt("pos")
                Log.e("자식 페이지호출","$pos")
                 textView.setText("$pos 페이지")
            }
        }

    }

    fun get (){

    }
    lateinit var textView: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_content, null)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        textView = view.text_content

    }
}