package com.vnerds.xapokotlin

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_webview.*

class WebViewActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)


        val mActionBar = supportActionBar
        mActionBar!!.setTitle(intent.getStringExtra("title"))
        mActionBar.setDisplayHomeAsUpEnabled(true)
        mActionBar.setDisplayShowTitleEnabled(true)

        webview.settings.javaScriptEnabled = true
        webview.loadUrl(intent.getStringExtra("url"))
        webview.webViewClient = MyWebViewClient()
    }

    private inner class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(webView: WebView, url: String): Boolean {
            return false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == android.R.id.home) {
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
