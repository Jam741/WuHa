package com.rx.android.jamspeedlibrary.utils.view

import android.content.Context
import android.util.AttributeSet
import android.widget.GridView

/**
 * Created by jam on 2015/10/19.
 * 兼容ScollerView的GridView
 */
class MyGridView : GridView {

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {}


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE shr 2, MeasureSpec.AT_MOST)
        super.onMeasure(widthMeasureSpec, expandSpec)

    }


}
