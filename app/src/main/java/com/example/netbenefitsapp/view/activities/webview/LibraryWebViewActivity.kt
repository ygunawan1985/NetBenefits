package com.example.netbenefitsapp.view.activities.webview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.example.netbenefitsapp.R

class LibraryWebViewActivity : AppCompatActivity() {

    private lateinit var webView : WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library_web_view)

        val intent = intent
        val libraryUrl = intent.getStringExtra("libraryUrl")

        webView = findViewById(R.id.wvLearning)
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(libraryUrl)
    }
}
