package com.rapid.compose.core.webview.client

import android.graphics.Bitmap
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.rapid.compose.core.webview.callback.WebViewCallback
import java.lang.ref.WeakReference

class CustomWebViewClient(
    callback: WebViewCallback? = null
) : WebViewClient() {

    private val weakCallback = WeakReference(callback)

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        weakCallback.get()?.onPageStarted(url)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        weakCallback.get()?.onPageFinished(url)
    }

    override fun onReceivedError(
        view: WebView, request: WebResourceRequest, error: WebResourceError
    ) {
        weakCallback.get()?.onReceivedError(error.errorCode, error.description.toString(), request.url.toString())
    }

    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
        return weakCallback.get()?.shouldOverrideUrlLoading(request.url.toString()) ?: false
    }
}