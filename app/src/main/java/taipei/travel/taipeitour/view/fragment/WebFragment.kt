package taipei.travel.taipeitour.view.fragment

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import taipei.travel.taipeitour.R
import taipei.travel.taipeitour.base.BaseFragment
import taipei.travel.taipeitour.databinding.FragWebViewBinding

class WebFragment : BaseFragment<FragWebViewBinding>(FragWebViewBinding::inflate) {
    companion object {
        fun newInstance(strTitle: String, strURL: String) = WebFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_WEB_TITLE, strTitle)
                putString(ARG_URL, strURL)
            }
        }
    }

    private val strTitle by lazy {
        arguments?.getString(ARG_WEB_TITLE) ?: getString(R.string.text_untitled)
    }

    private val strURL by lazy { arguments?.getString(ARG_URL) ?: "" }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            printLog("onBackPressedCallback, handleOnBackPressed()")

            vb.webView.apply {
                if (canGoBack()) goBack()
                else popFragment()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (strURL.isEmpty()) popFragmentWithAlert(getString(R.string.text_invalid_link))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vb.webView.apply {
            if (savedInstanceState == null) loadUrl(strURL)
            else restoreState(savedInstanceState)
        }
    }

    override fun onResume() {
        super.onResume()

        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)

        vb.webView.onResume()
    }

    override fun onPause() {
        onBackPressedCallback.remove()

        vb.webView.onPause()

        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        vb.webView.saveState(outState)

        super.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        vb.webView.apply {
            stopLoading()
            destroy()
        }

        super.onDestroyView()
    }

    override fun initUI() {
        vb.tvTitle.text = strTitle

        vb.tvUrl.apply {
            text = strURL

            setOnLongClickListener {
                copyToClipboard(text, getString(R.string.text_link_copied))

                true
            }
        }

        vb.ibRefresh.apply {
            setTooltipTextCompat(getString(R.string.text_refresh))

            setOnClickListener {
                vb.webView.reload()
            }
        }

        vb.ibCross.apply {
            setTooltipTextCompat(getString(R.string.text_close))

            setOnClickListener {
                popFragment()
            }
        }

        vb.guidelineToolbar.setPercentForOrientation()

        vb.webView.apply {
            @Suppress("setJavaScriptEnabled")
            settings.javaScriptEnabled = true

            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest) = false
            }

            webChromeClient = object : WebChromeClient() {
                private var receivedTitle = getString(R.string.text_untitled)

                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    vb.progressbar.apply {
                        progress = newProgress

                        if (progress != max) {
                            visibility = View.VISIBLE
                            vb.tvTitle.text = getString(R.string.text_loading)
                        } else {
                            visibility = View.INVISIBLE
                            vb.tvTitle.text = receivedTitle
                        }
                    }
                }

                override fun onReceivedTitle(view: WebView?, title: String?) {
                    receivedTitle = title ?: getString(R.string.text_untitled)

                    vb.tvTitle.text = receivedTitle
                    vb.tvUrl.text = view?.url
                }
            }
        }
    }
}
