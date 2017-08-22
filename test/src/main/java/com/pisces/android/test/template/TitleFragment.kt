package com.pisces.android.test.template

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pisces.android.framworkerlibrary.core.JBaseFragment
import com.pisces.android.test.R
import kotlinx.android.synthetic.main.content_frag.*

/**
 * Created by Jam on 2017/8/22.
 */
class TitleFragment:JBaseFragment() {

    companion object {
        fun newInstance(contentText:String):Fragment{
            val args = Bundle().apply {  putString("CONTENT",contentText)}
            return TitleFragment().apply {
                arguments = args
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.content_frag,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_content.text = arguments.getString("CONTENT")
    }


}