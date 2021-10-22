package com.pupplecow.myapplication.ui.worker.home

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.pupplecow.myapplication.R
import com.pupplecow.myapplication.databinding.ActivitySafetyManualQuizWebView1Binding

class SafetyManualQuizWebViewActivity1: AppCompatActivity() {

    private lateinit var binding: ActivitySafetyManualQuizWebView1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySafetyManualQuizWebView1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val webView:WebView=findViewById(R.id.safety_webview)

        webView.settings.javaScriptEnabled=true
        webView.webViewClient= WebViewClient()
        webView.webChromeClient= WebChromeClient()
        webView.loadUrl("file:///android_asset/index1.html")
    }
}
