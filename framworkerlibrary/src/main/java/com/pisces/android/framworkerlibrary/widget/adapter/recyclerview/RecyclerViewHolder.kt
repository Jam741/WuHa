package com.yingwumeijia.commonlibrary.utils.adapter.recyclerview

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.text.util.Linkify
import android.util.SparseArray
import android.view.*
import android.view.animation.AlphaAnimation
import android.widget.*
import com.pisces.adnroid.ltaskpicture.LImg
import com.yingwumeijia.commonlibrary.utils.ScreenUtils

/**
 * Created by jamisonline on 2017/5/31.
 */
class RecyclerViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    //    var activity: Activity? = null
//    var fragment: Fragment? = null
    var mViews = SparseArray<View>()
    var mContext: Context? = null
    var position: Int? = null


    constructor(fragment: Fragment, itemView: View?) : this(itemView) {
//        this.fragment = fragment
        this.mContext = fragment.context
    }

    constructor(activity: Activity, itemView: View?) : this(itemView) {
//        this.activity = activity
        this.mContext = activity
    }


    companion object {
        fun get(activity: Activity, parent: ViewGroup, layoutId: Int, viewType: Int): RecyclerViewHolder {
            return RecyclerViewHolder(activity, LayoutInflater.from(activity).inflate(layoutId, parent, false))
        }

        fun get(fragment: Fragment, parent: ViewGroup, layoutId: Int, viewType: Int): RecyclerViewHolder {
            return RecyclerViewHolder(fragment, LayoutInflater.from(fragment.context).inflate(layoutId, parent, false))
        }
    }

    fun getViewWith(@IdRes viewId: Int): View {

        var view: View?
        view = mViews[viewId]
        if (view == null) {
            view = itemView.findViewById(viewId)
            mViews.put(viewId, view)
        }
        return view
    }

    fun setTextWith(@IdRes viewId: Int, text: String?) {
        var tv = getViewWith(viewId) as TextView
        if (TextUtils.isEmpty(text)) return Unit
        tv.text = text
    }

    fun setTextWith(@IdRes viewId: Int, @IdRes resId: Int) {
        var tv = getViewWith(viewId) as TextView
        tv.setText(resId)
    }


    /**
     * ImageView
     */
    fun setImageResource(viewId: Int, resId: Int): RecyclerViewHolder {
        val view = getViewWith(viewId) as ImageView
        view.setImageResource(resId)
        return this
    }

    fun setImageBitmap(viewId: Int, bitmap: Bitmap): RecyclerViewHolder {
        val view = getViewWith(viewId) as ImageView
        view.setImageBitmap(bitmap)
        return this
    }

    fun setImageDrawable(viewId: Int, drawable: Drawable): RecyclerViewHolder {
        val view = getViewWith(viewId) as ImageView
        view.setImageDrawable(drawable)
        return this
    }


    fun setImageUrl(viewId: Int, url: String): RecyclerViewHolder {
        val view = getViewWith(viewId) as ImageView
        LImg.with(mContext).load(url).into(view)
        return this
    }


    fun setBackgroundColor(viewId: Int, color: Int): RecyclerViewHolder {
        val view = getViewWith(viewId)
        view.setBackgroundColor(color)
        return this
    }


    fun setBackgroundRes(viewId: Int, backgroundRes: Int): RecyclerViewHolder {
        val view = getViewWith(viewId)
        view.setBackgroundResource(backgroundRes)
        return this
    }

    fun setTextColor(viewId: Int, textColor: Int): RecyclerViewHolder {
        val view = getViewWith(viewId) as TextView
        view.setTextColor(textColor)
        return this
    }

    fun setTextColorRes(viewId: Int, textColorRes: Int): RecyclerViewHolder {
        val view = getViewWith(viewId) as TextView
        view.setTextColor(mContext!!.getResources().getColor(textColorRes))
        return this
    }

    fun setAlpha(viewId: Int, value: Float): RecyclerViewHolder {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getViewWith(viewId).setAlpha(value)
        } else {
            // Pre-honeycomb hack to set Alpha value
            val alpha = AlphaAnimation(value, value)
            alpha.duration = 0
            alpha.fillAfter = true
            getViewWith(viewId).startAnimation(alpha)
        }
        return this
    }

    fun setVisible(viewId: Int, visible: Boolean): RecyclerViewHolder {
        val view = getViewWith(viewId)
        view.setVisibility(if (visible) View.VISIBLE else View.GONE)
        return this
    }

    fun linkify(viewId: Int): RecyclerViewHolder {
        val view = getViewWith(viewId) as TextView
        Linkify.addLinks(view, Linkify.ALL)
        return this
    }

    fun setTypeface(typeface: Typeface, vararg viewIds: Int): RecyclerViewHolder {
        for (viewId in viewIds) {
            val view = getViewWith(viewId) as TextView
            view.setTypeface(typeface)
            view.setPaintFlags(view.getPaintFlags() or Paint.SUBPIXEL_TEXT_FLAG)
        }
        return this
    }

    fun setProgress(viewId: Int, progress: Int): RecyclerViewHolder {
        val view = getViewWith(viewId) as ProgressBar
        view.setProgress(progress)
        return this
    }

    fun setProgress(viewId: Int, progress: Int, max: Int): RecyclerViewHolder {
        val view = getViewWith(viewId) as ProgressBar
        view.setMax(max)
        view.setProgress(progress)
        return this
    }

    fun setMax(viewId: Int, max: Int): RecyclerViewHolder {
        val view = getViewWith(viewId) as ProgressBar
        view.setMax(max)
        return this
    }

    fun setRating(viewId: Int, rating: Float): RecyclerViewHolder {
        val view = getViewWith(viewId) as RatingBar
        view.setRating(rating)
        return this
    }

    fun setRating(viewId: Int, rating: Float, max: Int): RecyclerViewHolder {
        val view = getViewWith(viewId) as RatingBar
        view.setMax(max)
        view.setRating(rating)
        return this
    }

    fun setTag(viewId: Int, tag: Any): RecyclerViewHolder {
        val view = getViewWith(viewId)
        view.setTag(tag)
        return this
    }

    fun setTag(viewId: Int, key: Int, tag: Any): RecyclerViewHolder {
        val view = getViewWith(viewId)
        view.setTag(key, tag)
        return this
    }

    fun setChecked(viewId: Int, checked: Boolean): RecyclerViewHolder {
        val view = getViewWith(viewId) as Checkable
        view.isChecked = checked
        return this
    }

    fun setWidthHeightBaseWidth(viewId: Int, width: Int, height: Int): RecyclerViewHolder {
        val view = getViewWith(viewId)
        val widthNum = view.getWidth()
        val heightNum: Int
        val lp = view.layoutParams
        lp.width = ScreenUtils.screenWidth / 2
        lp.height = widthNum * height / width
        view.setLayoutParams(lp)
        return this
    }

    fun setSize(viewId: Int, width: Int, height: Int): RecyclerViewHolder {
        val view = getViewWith(viewId)
        val lp = view.layoutParams
        lp.width = width
        lp.height = height
        view.layoutParams = lp
        return this
    }

    fun setOnClickListener(viewId: Int, listener: View.OnClickListener): RecyclerViewHolder {
        val view = getViewWith(viewId)
        view.setOnClickListener(listener)
        return this
    }

    fun setOnTouchListener(viewId: Int,
                           listener: View.OnTouchListener): RecyclerViewHolder {
        val view = getViewWith(viewId)
        view.setOnTouchListener(listener)
        return this
    }

    fun setOnLongClickListener(viewId: Int,
                               listener: View.OnLongClickListener): RecyclerViewHolder {
        val view = getViewWith(viewId)
        view.setOnLongClickListener(listener)
        return this
    }

    fun setOnItemClickListener(listener: OnItemCliceListener): RecyclerViewHolder {
        itemView.setOnClickListener(View.OnClickListener { listener.itemClick(itemView, position!!) })
        return this
    }

    fun setOnItemLongClickListener(listener: OnItemLongCliceListener): RecyclerViewHolder {
        itemView.setOnLongClickListener(View.OnLongClickListener { listener.itemLongClick(itemView, position!!) })
        return this
    }

    interface OnItemCliceListener {
        fun itemClick(itemView: View, position: Int)
    }

    interface OnItemLongCliceListener {
        fun itemLongClick(itemView: View, position: Int): Boolean
    }
}