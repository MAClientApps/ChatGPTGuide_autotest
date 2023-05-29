package com.testdemo.sample.presentation;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.testdemo.sample.R;
import com.testdemo.sample.common.Utils;

public class ChatGPTActivity extends AppCompatActivity {

    Button ChatGPT_button_retry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        try {
            getSupportActionBar().hide();
        }catch (Exception e){

        }
        initChatGPT_webconfig();
    }

    private WebView webview_ChatGPT;
    LinearLayout errorLayout_ChatGPT;

    @SuppressLint("SetJavaScriptEnabled")
    public void initChatGPT_webconfig() {
        webview_ChatGPT = findViewById(R.id.webview_ChatGPTWeb);
        errorLayout_ChatGPT = findViewById(R.id.layout_error_ChatGPT);
        webview_ChatGPT.getSettings().setJavaScriptEnabled(true);
        webview_ChatGPT.getSettings().setUseWideViewPort(true);
        webview_ChatGPT.getSettings().setLoadWithOverviewMode(true);
        webview_ChatGPT.getSettings().setDomStorageEnabled(true);
        webview_ChatGPT.getSettings().setPluginState(WebSettings.PluginState.ON);
        CookieManager.getInstance().setAcceptCookie(true);
        webview_ChatGPT.setWebChromeClient(new WebChromeClient());
        webview_ChatGPT.setVisibility(View.VISIBLE);
        webview_ChatGPT.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request,
                                        WebResourceError error) {
                super.onReceivedError(view, request, error);
                String ChatGPT_url = request.getUrl().toString();
                if (!ChatGPT_url.startsWith("http")) {
                    Intent mainIntent_ChatGPT = new Intent(ChatGPTActivity.this, DashboardActivity.class);
                    startActivity(mainIntent_ChatGPT);
                    try {
                        Intent ChatGPT_Intent = new Intent(Intent.ACTION_VIEW);
                        ChatGPT_Intent.setData(Uri.parse(ChatGPT_url));
                        startActivity(ChatGPT_Intent);
                        finish();
                        return;
                    } catch (Exception ChatGPT_exception) {
                        finish();
                        return;
                    }
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }

        });

        lets_load_ChatGPT();
    }
    protected void lets_load_ChatGPT() {
        if (Utils.hasChatGPT_InternetConnected(this)) {
            webview_ChatGPT.loadUrl(Utils.myChatGPT_Link (ChatGPTActivity.this));
        } else {
            hadChatGPT_ErrorView();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        webview_ChatGPT.onPause();
    }
    @Override
    public void onResume() {
        super.onResume();
        webview_ChatGPT.onResume();
    }


    @Override
    public void onBackPressed() {

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        webview_ChatGPT.loadUrl("about:blank");
    }


    public void hadChatGPT_ErrorView() {
        errorLayout_ChatGPT.setVisibility(View.VISIBLE);
        ChatGPT_button_retry = findViewById(R.id.button_retry_ChatGPT);
        ChatGPT_button_retry.setOnClickListener(view -> {
            errorLayout_ChatGPT.setVisibility(View.GONE);
            lets_load_ChatGPT();
        });
    }

}
