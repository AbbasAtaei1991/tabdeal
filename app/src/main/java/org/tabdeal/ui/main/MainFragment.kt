package org.tabdeal.ui.main

import android.annotation.SuppressLint
import android.net.http.SslError
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.SslErrorHandler
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import org.tabdeal.R
import org.tabdeal.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var webView: WebView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        webView = binding.webView
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWebView()
    }
    
    @SuppressLint("JavascriptInterface")
    private fun initWebView() {
        webView.webViewClient = object : WebViewClient() {
            override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                handler!!.proceed()
            }
        }
        webView.settings.javaScriptEnabled = true
        webView.settings.allowContentAccess
        webView.settings.allowFileAccess
        webView.settings.databaseEnabled = true
        webView.settings.domStorageEnabled = true
        webView.addJavascriptInterface(this, "android")
        webView.loadUrl("https://tabdeal.org/")
    }
}