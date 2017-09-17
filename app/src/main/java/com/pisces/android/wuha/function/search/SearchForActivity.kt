package com.pisces.android.wuha.function.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseActivity
import com.pisces.android.wuha.function.search.show.SearchShowActivity
import com.yingwumeijia.commonlibrary.utils.ListUtil
import kotlinx.android.synthetic.main.search_frag.*

/**
 * Created by Chris Li on 2017/9/2.
 * 搜索界面
 */
class SearchForActivity : LBaseActivity(), SearchController.OnLoadHistoryAndHotKeyWordsListener {

    override fun didLoadHistoryKeyWords(data: List<String>?) {
        if (ListUtil.isEmpty(data))
            history_layout.visibility = View.GONE
        else {
            history_layout.visibility = View.VISIBLE
            controller.historyKeyWordsAdapter.addRange(data)
        }

    }

    override fun didLoadHotKeyWords(data: List<String>?) {
        if (ListUtil.isEmpty(data))
            hot_layout.visibility = View.GONE
        else {
            hot_layout.visibility = View.VISIBLE
            controller.hotKeyWordsAdapter.addRange(data)
        }
    }


    private var searchKeyWord: String = ""

    private val controller by lazy { SearchController(this, this) }

    companion object {
        fun start(context: Context) {
            val stater = Intent(context, SearchForActivity::class.java)
            context.startActivity(stater)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_frag)


        flow_history.run {
            adapter = controller.historyKeyWordsAdapter
            setOnTagClickListener { view, position, parent ->
                searchKeyWord = controller.historyKeyWordsAdapter.getItem(position)
                didSearch()
                return@setOnTagClickListener true
            }
        }


        flow_hot.run {
            adapter = controller.hotKeyWordsAdapter
            setOnTagClickListener { view, position, parent ->
                searchKeyWord = controller.hotKeyWordsAdapter.getItem(position)
                didSearch()
                return@setOnTagClickListener true
            }
        }

        btnSearch.setOnClickListener {
            didSearch()
        }



        edSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                searchKeyWord = if (query == null) "" else query!!
                didSearch()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })


        controller.loadHistoryKeyWords()
        controller.loadHotKeyWords()
    }


    fun didSearch() {
        Log.d("SEARCH", searchKeyWord)
        if (TextUtils.isEmpty(searchKeyWord)) {
            toastWith("搜索不能为空")
            return
        }
        SearchShowActivity.start(this, searchKeyWord)
    }
}