package com.pisces.android.wuha.function.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.pisces.android.wuha.R
import com.pisces.android.wuha.base.LBaseActivity
import kotlinx.android.synthetic.main.search_header.*

/**
 * Created by Chris Li on 2017/9/17.
 */
class SearchShowActivity : LBaseActivity() {
    companion object {
        fun start(context: Context) {
            val stater = Intent(context, SearchShowActivity::class.java)
            context.startActivity(stater)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_show_a)
        edSearchView.run {
        }
        edSearchView.setOnClickListener {
            SearchForActivity.start(this)
            finish()
        }
    }
}