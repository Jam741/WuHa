package com.pisces.android.wuha

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pisces.android.framworkerlibrary.core.JBaseFragment
import kotlinx.android.synthetic.main.placeholder_frag.*

/**
 * Created by Jam on 2017/8/24.
 */
class PlaceHolderFragment:JBaseFragment() {

    companion object {
        fun newInstance(text:String):PlaceHolderFragment{
            return PlaceHolderFragment().apply { arguments =  Bundle().apply { putString("TEXT",text) }  }
        }
    }

    private val  placeholderText by lazy { arguments.getString("TEXT") }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.placeholder_frag,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_placeholder.text = placeholderText
    }
}