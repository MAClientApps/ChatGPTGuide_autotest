package com.testdemo.sample.presentation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.GeolocationPermissions;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.URLUtil;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import androidx.core.content.FileProvider;
import com.testdemo.sample.BuildConfig;
import com.testdemo.sample.R;
import com.testdemo.sample.presentation.model.CustomWeb;

public class GuideActivity extends AppCompatActivity {

    public static Context mContext;
    public static String ChatGPTHOME_URL = "";
    private final static int FCRChatGPT = 1;
    public ProgressBar progressBarChatGPT;
    private String deepLinkingURLChatGPT;
    public String hostpartChatGPT;
    static  boolean isInBackGroundChatGPT =false;
    private static final String ChatGPTINDEX_FILE = "file:///android_asset/index.html";
    private static final int ChatGPTCODE_AUDIO_CHOOSER = 9857;
    public static final String ChatGPTUSER_AGENT = "";
    public static final boolean ChatGPTOPEN_SPECIAL_URLS_IN_NEW_TAB = true;
    public static final boolean ChatGPTCLEAR_CACHE_ON_STARTUP = false;
    public static final boolean ChatGPTEXIT_APP_BY_BACK_BUTTON_ALWAYS = false;
    public static final boolean ChatGPTEXIT_APP_BY_BACK_BUTTON_HOMEPAGE = true;
    public static final boolean ChatGPTAPPEND_LANG_CODE = false;
    public static final boolean ChatGPTIS_LINKING_ENABLED = true;
    private boolean isNotificationURLChatGPT = false;
    public String uuidChatGPT = "";
    private Handler notificationHandlerChatGPT;
    Timer timerChatGPT = new Timer();
    private CustomWeb webViewChatGPT;
    private View offlineLayoutChatGPT;
    public static final int ChatGPTREQUEST_CODE_QR_SCAN = 7632;
    private static boolean connectedNowChatGPT = false;
    public ValueCallback<Uri[]> uploadMessageChatGPT;
    public static final int REQUEST_SELECT_FILEChatGPT = 971;
    public static final boolean ChatGPTUSE_LOCAL_HTML_FOLDER = false;
    public static final boolean ChatGPTENABLE_SWIPE_NAVIGATE = false;
    public static final boolean ChatGPTINCREMENT_WITH_REDIRECTS = true;
    public static final boolean ChatGPTACTIVATE_PROGRESS_BAR = true;
    public static final boolean ChatGPTOPEN_NOTIFICATION_URLS_IN_SYSTEM_BROWSER = false;
    public static final int ChatGPTEXTERNAL_URL_HANDLING_OPTIONS = 0;
    private String mCMChatGPT, mVMChatGPT;
    private ValueCallback<Uri[]> mUMAChatGPT;
   private boolean isRedirectedChatGPT = false;
    static  long TimeStampChatGPT =0;

    @SuppressLint({"SetJavaScriptEnabled", "MissingInflatedId"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mContext = this;
        uuidChatGPT = Settings.System.getString(super.getContentResolver(), Settings.Secure.ANDROID_ID);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_guide);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        StrictMode.ThreadPolicy threadPolicyChatGPT = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicyChatGPT);

        Intent intentChatGPT = getIntent();
        if (intentChatGPT != null && intentChatGPT.getData() != null && (intentChatGPT.getData().getScheme().equals("http"))) {
            Uri dataChatGPT = intentChatGPT.getData();
            List<String> pathSegmentsChatGPT = dataChatGPT.getPathSegments();
            if (pathSegmentsChatGPT.size() > 0) {
                deepLinkingURLChatGPT = pathSegmentsChatGPT.get(0).substring(5);
                String fulldeeplinkingurlChatGPT = dataChatGPT.getPath().toString();
                fulldeeplinkingurlChatGPT = fulldeeplinkingurlChatGPT.replace("/link=", "");
                deepLinkingURLChatGPT = fulldeeplinkingurlChatGPT;
            }
        } else if (intentChatGPT != null && intentChatGPT.getData() != null && (intentChatGPT.getData().getScheme().equals("https"))) {
            Uri uriChatGPT = intentChatGPT.getData();
            List<String> pathSegmentsListChatGPT = uriChatGPT.getPathSegments();
            if (pathSegmentsListChatGPT.size() > 0) {
                deepLinkingURLChatGPT = pathSegmentsListChatGPT.get(0).substring(5);
                String fulldeeplinkingurl = uriChatGPT.getPath().toString();
                fulldeeplinkingurl = fulldeeplinkingurl.replace("/link=", "");
                deepLinkingURLChatGPT = fulldeeplinkingurl;
            }
        }

        if (intentChatGPT != null) {
            Bundle extrasBundleChatGPT = getIntent().getExtras();
            String URL = null;
            if (extrasBundleChatGPT != null) {
                URL = extrasBundleChatGPT.getString("ONESIGNAL_URL");
            }
            if (URL != null && !URL.equalsIgnoreCase("")) {
                isNotificationURLChatGPT = true;
                deepLinkingURLChatGPT = URL;
            } else isNotificationURLChatGPT = false;
        }

        webViewChatGPT = findViewById(R.id.webView);
        webViewChatGPT.setGestureDetector(new GestureDetector(new CustomeGestureDetectorChatGPT()));


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        offlineLayoutChatGPT = findViewById(R.id.offline_layout);
        this.findViewById(android.R.id.content).setBackgroundColor(getResources().getColor(R.color.black));
        progressBarChatGPT = findViewById(R.id.progressBar);
        progressBarChatGPT.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.purple_200), PorterDuff.Mode.MULTIPLY);
        webViewChatGPT.getSettings().setSupportMultipleWindows(true);
        webViewChatGPT.getSettings().setUseWideViewPort(true);
        webViewChatGPT.getSettings().setLoadWithOverviewMode(true);
        webViewChatGPT.getSettings().setDomStorageEnabled(true);
        webViewChatGPT.getSettings().setPluginState(WebSettings.PluginState.ON);

       final Button tryAgainButtonChatGPT = findViewById(R.id.try_again_button);
        tryAgainButtonChatGPT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Try again!");
                webViewChatGPT.setVisibility(View.GONE);
                loadMainUrlChatGPT();
            }
        });


        webViewChatGPT.setWebViewClient(new MyWebViewClient() {
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String url) {
                webViewChatGPT.setVisibility(View.GONE);
                offlineLayoutChatGPT.setVisibility(View.VISIBLE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
               if (!isRedirectedChatGPT) {

                    if (url.startsWith("mailto:")) {
                        startActivity(new Intent(Intent.ACTION_SENDTO, Uri.parse(url)));
                        return true;
                    }
                    if (url.startsWith("share:") || url.contains("api.whatsapp.com")) {
                        Intent intentWhatsChatGPT = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intentWhatsChatGPT);
                        return true;
                    }
                    if (url.startsWith("whatsapp:")) {
                        Intent intentChatGPT = new Intent();
                        intentChatGPT.setPackage("com.whatsapp");
                        intentChatGPT.setAction(Intent.ACTION_SEND);
                        intentChatGPT.setType("text/plain");
                        startActivity(intentChatGPT);
                        return true;
                    }
                    if (url.startsWith("geo:") || url.contains("maps:")) {
                        Intent intentChatGPT = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intentChatGPT);
                        return true;
                    }
                    if (url.startsWith("intentChatGPT:")) {
                        Intent intentChatGPT = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intentChatGPT);
                        return true;
                    }
                    if (url.startsWith("tel:")) {
                        Intent intentChatGPT = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intentChatGPT);
                        return true;
                    }
                    if (url.startsWith("sms:")) {
                        Intent intentChatGPT = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intentChatGPT);
                        return true;
                    }
                    if (url.startsWith("play.google.com")) {
                        Intent intentChatGPT = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intentChatGPT);
                        return true;
                    }

                    if (url.startsWith("market:")) {
                        Intent intentChatGPT = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intentChatGPT);
                        return true;
                    }
                    if (url.startsWith("maps.app.goo.gl")) {
                        Intent intentChatGPT = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intentChatGPT);
                        return true;
                    }
                    if (url.contains("maps.google.com")) {
                        Intent intentChatGPT = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intentChatGPT);
                        return true;
                    }

                    if (ChatGPTOPEN_SPECIAL_URLS_IN_NEW_TAB) {
                        WebView.HitTestResult resultChatGPT = view.getHitTestResult();
                        String dataExtraChatGPT = resultChatGPT.getExtra();
                        if ((dataExtraChatGPT != null && dataExtraChatGPT.endsWith("#")) || url.startsWith("newtab:")) {
                            CustomTabsIntent.Builder builderChatGPT = new CustomTabsIntent.Builder();
                            builderChatGPT.setToolbarColor(getResources().getColor(R.color.purple_700));
                            CustomTabsIntent customTabsIntent = builderChatGPT.build();
                            String finalUrlChatGPT = url;

                            if (url.startsWith("newtab:")) {
                                finalUrlChatGPT = url.substring(7);
                            }

                            customTabsIntent.launchUrl(GuideActivity.this, Uri.parse(finalUrlChatGPT));
                            webViewChatGPT.stopLoading();
                            return false;
                        }
                    }
                    return super.shouldOverrideUrlLoading(view, url);
                }
                return false;
            }
        });


        webViewChatGPT.setWebChromeClient(new MyWebChromeClientChatGPT() {
            private Handler notificationHandler;

            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
            }

            @Override
            public void onCloseWindow(WebView window) {
                super.onCloseWindow(window);
            }

            @Override
            public boolean onCreateWindow(WebView view, boolean dialog, boolean userGesture, Message resultMsg) {

                Bundle extrasChatGPT = getIntent().getExtras();
                String URLChatGPT = null;
                String launchUrlChatGPT = null;
                if (extrasChatGPT != null )
                {
                    URLChatGPT = extrasChatGPT.getString("ONESIGNAL_URL");
                    launchUrlChatGPT=extrasChatGPT.getString("openURL");
                }
                if (URLChatGPT != null && !URLChatGPT.equalsIgnoreCase("")) {
                    isNotificationURLChatGPT = true;
                    deepLinkingURLChatGPT = URLChatGPT;
                } else isNotificationURLChatGPT = false;

                if(launchUrlChatGPT!=null)
                {
                    openChatGPTInExternalBrowser(launchUrlChatGPT);
                }
                WebView.HitTestResult hitTestResultChatGPT = view.getHitTestResult();
                String dataExtraChatGPT = hitTestResultChatGPT.getExtra();
                if (URLUtil.isValidUrl(dataExtraChatGPT)) {
                    hostpartChatGPT = Uri.parse(dataExtraChatGPT).getHost();
                      if (ChatGPTEXTERNAL_URL_HANDLING_OPTIONS == 1) {
                            CustomTabsIntent.Builder builderChatGPT = new CustomTabsIntent.Builder();
                            builderChatGPT.setToolbarColor(getResources().getColor(R.color.purple_700));
                            CustomTabsIntent customTabsIntent = builderChatGPT.build();
                            customTabsIntent.launchUrl(GuideActivity.this, Uri.parse(dataExtraChatGPT));
                            webViewChatGPT.stopLoading();
                            return true;
                        } else if (ChatGPTEXTERNAL_URL_HANDLING_OPTIONS == 2) {
                            Intent intentChatGPT = new Intent(Intent.ACTION_VIEW, Uri.parse(dataExtraChatGPT));
                            startActivity(intentChatGPT);
                            return true;
                        }
                }

                if (!ChatGPTOPEN_SPECIAL_URLS_IN_NEW_TAB) {
                    if (dataExtraChatGPT == null) {
                        WebView newWebViewChatGPT = new WebView(view.getContext());
                        newWebViewChatGPT.setWebChromeClient(new MyWebChromeClientChatGPT());
                        WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
                        transport.setWebView(newWebViewChatGPT);
                        resultMsg.sendToTarget();
                        return true;
                    } else {
                        if (URLUtil.isValidUrl(dataExtraChatGPT)) {
                            webViewChatGPT.loadUrl(dataExtraChatGPT);
                        }
                    }
                } else {
                    CustomTabsIntent.Builder builderChatGPT = new CustomTabsIntent.Builder();
                    builderChatGPT.setToolbarColor(getResources().getColor(R.color.purple_700));
                    CustomTabsIntent customTabsIntentChatGPT = builderChatGPT.build();
                    WebView newWebView = new WebView(view.getContext());
                    newWebView.setWebChromeClient(new WebChromeClient());
                    WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
                    transport.setWebView(newWebView);
                    resultMsg.sendToTarget();
                }

                return true;
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }

            @SuppressLint("InlinedApi")
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {


                if (mUMAChatGPT != null) {
                    mUMAChatGPT.onReceiveValue(null);
                }
                mUMAChatGPT = filePathCallback;

                if (Arrays.asList(fileChooserParams.getAcceptTypes()).contains("audio/*")) {
                    Intent chooserIntent = fileChooserParams.createIntent();
                    startActivityForResult(chooserIntent, ChatGPTCODE_AUDIO_CHOOSER);
                    return true;
                }

                Intent takePictureIntentChatGPT = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntentChatGPT.resolveActivity(GuideActivity.this.getPackageManager()) != null) {
                    File photoFileChatGPT = null;
                    try {
                        photoFileChatGPT = createImageFileChatGPT();
                        takePictureIntentChatGPT.putExtra("PhotoPath", mCMChatGPT);
                    } catch (Exception ex) {

                    }
                    if (photoFileChatGPT != null) {
                        mCMChatGPT = "file:" + photoFileChatGPT.getAbsolutePath();
                        takePictureIntentChatGPT.putExtra(MediaStore.EXTRA_OUTPUT,
                                FileProvider.getUriForFile(GuideActivity.this, getPackageName() + ".provider", photoFileChatGPT));
                    } else {
                        takePictureIntentChatGPT = null;
                    }
                }
                Intent takeVideoIntentChatGPT = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                if (takeVideoIntentChatGPT.resolveActivity(GuideActivity.this.getPackageManager()) != null) {
                    File videoFile = null;
                    try {
                        videoFile = createVideoFileChatGPT();
                        takeVideoIntentChatGPT.putExtra("PhotoPath", mVMChatGPT);
                    } catch (Exception ex) {

                    }
                    if (videoFile != null) {
                        mVMChatGPT = "file:" + videoFile.getAbsolutePath();
                        takeVideoIntentChatGPT.putExtra(MediaStore.EXTRA_OUTPUT,
                                FileProvider.getUriForFile(GuideActivity.this, getPackageName() + ".provider", videoFile));
                    } else {
                        takeVideoIntentChatGPT = null;
                    }
                }

                Intent contentSelectionIntentChatGPT = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                contentSelectionIntentChatGPT.addCategory(Intent.CATEGORY_OPENABLE);
                contentSelectionIntentChatGPT.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                contentSelectionIntentChatGPT.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/* video/*");

                String[] mimeTypesChatGPT = {"text/csv", "text/comma-separated-values", "application/pdf", "image/*", "video/*", "*/*"};
                contentSelectionIntentChatGPT.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypesChatGPT);

                Intent[] intentArrayChatGPT;
                if (takePictureIntentChatGPT != null && takeVideoIntentChatGPT != null) {
                    intentArrayChatGPT = new Intent[]{takePictureIntentChatGPT, takeVideoIntentChatGPT};
                } else if (takePictureIntentChatGPT != null) {
                    intentArrayChatGPT = new Intent[]{takePictureIntentChatGPT};
                } else if (takeVideoIntentChatGPT != null) {
                    intentArrayChatGPT = new Intent[]{takeVideoIntentChatGPT};
                } else {
                    intentArrayChatGPT = new Intent[0];
                }
                Intent chooserIntentChatGPT = new Intent(Intent.ACTION_CHOOSER);
                chooserIntentChatGPT.putExtra(Intent.EXTRA_INTENT, contentSelectionIntentChatGPT);
                chooserIntentChatGPT.putExtra(Intent.EXTRA_TITLE, "Upload");
                chooserIntentChatGPT.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArrayChatGPT);
                startActivityForResult(chooserIntentChatGPT, FCRChatGPT);
                return true;
            }

        });


        webViewChatGPT.setDownloadListener((url, userAgent, contentDisposition, mimetype, contentLength) -> {
            Intent viewIntentChatGPT = new Intent(Intent.ACTION_VIEW);
            viewIntentChatGPT.setData(Uri.parse(url));
            startActivity(viewIntentChatGPT);
        });

        registerForContextMenu(webViewChatGPT);

        final WebSettings webSettingsChatGPT = webViewChatGPT.getSettings();
        webSettingsChatGPT.setJavaScriptEnabled(true);
        webSettingsChatGPT.setDomStorageEnabled(true);
        webSettingsChatGPT.setAllowFileAccess(true);
        webSettingsChatGPT.setAllowContentAccess(true);
        webSettingsChatGPT.setGeolocationEnabled(true);
        webSettingsChatGPT.setBuiltInZoomControls(false);
        webSettingsChatGPT.setSupportZoom(false);

        webSettingsChatGPT.setJavaScriptCanOpenWindowsAutomatically(true);
        if (ChatGPTCLEAR_CACHE_ON_STARTUP) {
            webSettingsChatGPT.setCacheMode(WebSettings.LOAD_NO_CACHE);
        } else {
            webSettingsChatGPT.setCacheMode(WebSettings.LOAD_DEFAULT);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(webViewChatGPT, true);
        }
        webSettingsChatGPT.setAllowUniversalAccessFromFileURLs(true);
        webSettingsChatGPT.setAllowFileAccessFromFileURLs(true);
        webSettingsChatGPT.setAllowFileAccess(true);
        webSettingsChatGPT.setAllowContentAccess(true);
        webSettingsChatGPT.setAllowUniversalAccessFromFileURLs(true);
        webSettingsChatGPT.setDatabaseEnabled(true);
        webSettingsChatGPT.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        if (!ChatGPTUSER_AGENT.isEmpty()) {
            webSettingsChatGPT.setUserAgentString(ChatGPTUSER_AGENT);
        }

        if (ChatGPTCLEAR_CACHE_ON_STARTUP) {
            webViewChatGPT.clearCache(true);
        }

        if (ChatGPTUSE_LOCAL_HTML_FOLDER) {
            loadLocalChatGPT(ChatGPTINDEX_FILE);
        } else if (hasChatGPTInternetConnected(GuideActivity.this)) {
            if (ChatGPTUSE_LOCAL_HTML_FOLDER) {
                loadLocalChatGPT(ChatGPTINDEX_FILE);
            } else {
                loadMainUrlChatGPT();
            }
        } else {
            loadLocalChatGPT(ChatGPTINDEX_FILE);
        }
        if (!connectedNowChatGPT) {
            checkInternetConnectionChatGPT();
        }
        if(getIntent().getExtras()!=null)
        {
            String openurlChatGPT = getIntent().getExtras().getString("openURL");
            if(openurlChatGPT!=null)
            {
                openChatGPTInExternalBrowser(openurlChatGPT);
            }
        }
    }
    public static boolean hasChatGPTInternetConnected(Context context) {
        ConnectivityManager ChatGPTconnectmanager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return (ChatGPTconnectmanager != null && ChatGPTconnectmanager.getActiveNetworkInfo() != null) && ChatGPTconnectmanager
                .getActiveNetworkInfo().isConnectedOrConnecting();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() ==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void checkInternetConnectionChatGPT() {
        class AutoRec extends TimerTask {
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {

                        if (!hasChatGPTInternetConnected(GuideActivity.this)) {
                            connectedNowChatGPT = false;
                            offlineLayoutChatGPT.setVisibility(View.VISIBLE);
                            webViewChatGPT.setVisibility(View.GONE);
                            loadMainUrlChatGPT();
                        } else {
                            if (!connectedNowChatGPT) {
                                webViewChatGPT.setVisibility(View.GONE);
                                loadMainUrlChatGPT();
                                connectedNowChatGPT = true;
                                if (timerChatGPT != null) timerChatGPT.cancel();
                            }
                        }
                    }
                });

            }
        }
        timerChatGPT.schedule(new AutoRec(), 0, 5000);
    }

    private File createVideoFileChatGPT() throws IOException {
        String timeStampChatGPT = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        String imageFileNameChatGPT = "VID_" + timeStampChatGPT + "";
        File mediaStorageDirChatGPT = getCacheDir();
        if (!mediaStorageDirChatGPT.exists()) {
            if (!mediaStorageDirChatGPT.mkdirs()) {
                return null;
            }
        }
        return File.createTempFile(
                imageFileNameChatGPT,
                ".mp4",
                mediaStorageDirChatGPT
        );
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onResume() {
        super.onResume();
        isInBackGroundChatGPT =false;
        TimeStampChatGPT = Calendar.getInstance().getTimeInMillis();
    }

    private File createImageFileChatGPT() throws IOException {
        String timeStampChatGPT = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        String imageFileNameChatGPT = "JPEG_" + timeStampChatGPT + "";
        File mediaStorageDir = getCacheDir();
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        return File.createTempFile(
                imageFileNameChatGPT,
                ".jpg",
                mediaStorageDir
        );
    }

    private void loadLocalChatGPT(String path) {
        webViewChatGPT.loadUrl(path);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        final WebView.HitTestResult webViewHitTestResultChatGPT = webViewChatGPT.getHitTestResult();

        if (webViewHitTestResultChatGPT.getType() == WebView.HitTestResult.IMAGE_TYPE ||
                webViewHitTestResultChatGPT.getType() == WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE) {
        }
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        webViewChatGPT.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        webViewChatGPT.restoreState(savedInstanceState);
    }

    private void openChatGPTInExternalBrowser(String launchUrl) {
        Intent browserIntentChatGPT = new Intent(Intent.ACTION_VIEW, Uri.parse(launchUrl));
        startActivity(browserIntentChatGPT);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private class MyWebChromeClientChatGPT extends WebChromeClient {

        private View mCustomViewChatGPT;
        private CustomViewCallback mCustomViewChatGPTCallback;
        private int mOriginalOrientationChatGPT;
        private int mOriginalChatGPTSystemUiVisibility;

        MyWebChromeClientChatGPT() {
        }

        public Bitmap getDefaultVideoPoster() {
            if (mCustomViewChatGPT == null) {
                return null;
            }
            return BitmapFactory.decodeResource(getApplicationContext().getResources(), 2130837573);
        }
        public void onHideCustomView() {
            ((FrameLayout) getWindow().getDecorView()).removeView(this.mCustomViewChatGPT);
            this.mCustomViewChatGPT = null;
            getWindow().getDecorView().setSystemUiVisibility(this.mOriginalChatGPTSystemUiVisibility);
            setRequestedOrientation(this.mOriginalOrientationChatGPT);
            this.mCustomViewChatGPTCallback.onCustomViewHidden();
            this.mCustomViewChatGPTCallback = null;
        }

        public void onShowCustomView(View paramView, CustomViewCallback paramCustomViewCallback) {
            if (this.mCustomViewChatGPT != null) {
                onHideCustomView();
                return;
            }
            this.mCustomViewChatGPT = paramView;
            this.mOriginalChatGPTSystemUiVisibility = getWindow().getDecorView().getSystemUiVisibility();
            this.mOriginalOrientationChatGPT = getRequestedOrientation();
            this.mCustomViewChatGPTCallback = paramCustomViewCallback;
            ((FrameLayout) getWindow().getDecorView()).addView(this.mCustomViewChatGPT, new FrameLayout.LayoutParams(-1, -1));
            getWindow().getDecorView().setSystemUiVisibility(3846);
        }

        public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
            callback.invoke(origin, true, false);
        }

        boolean progressBarActiveChatGPT = false;

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (ChatGPTACTIVATE_PROGRESS_BAR && !progressBarActiveChatGPT) {
                progressBarChatGPT.setVisibility(View.VISIBLE);
                progressBarActiveChatGPT = true;
            }

            isRedirectedChatGPT = true;
            if (newProgress >= 80 && ChatGPTACTIVATE_PROGRESS_BAR && progressBarActiveChatGPT) {
                progressBarChatGPT.setVisibility(View.GONE);
                progressBarActiveChatGPT = false;
            }

            if (newProgress == 100) {
                isRedirectedChatGPT = false;
                webViewChatGPT.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPermissionRequest(final PermissionRequest request) {
            request.grant(request.getResources());
        }

    }


    private class MyWebViewClient extends WebViewClient {

        MyWebViewClient() {
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            if (!isRedirectedChatGPT || ChatGPTINCREMENT_WITH_REDIRECTS) {
                super.onPageStarted(view, url, favicon);
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if (!isRedirectedChatGPT) {
                setTitle(view.getTitle());
                customCSSChatGPT();
                super.onPageFinished(view, url);
            }
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (!isRedirectedChatGPT) {
                hostpartChatGPT = Uri.parse(url).getHost();

                if (hasChatGPTInternetConnected(GuideActivity.this)) {
                    if (url.startsWith("sendlocalpushmsg://push.send")) {
                        webViewChatGPT.stopLoading();
                        if (webViewChatGPT.canGoBack()) {
                            webViewChatGPT.goBack();
                        }
                    } else if (url.startsWith("sendlocalpushmsg://push.send.cancel") && notificationHandlerChatGPT != null) {
                        webViewChatGPT.stopLoading();
                        if (webViewChatGPT.canGoBack()) {
                            webViewChatGPT.goBack();
                        }
                        notificationHandlerChatGPT.removeCallbacksAndMessages(null);
                        notificationHandlerChatGPT = null;
                    } else if (url.startsWith("get-uuid://")) {
                        webViewChatGPT.loadUrl("javascript: var uuid = '" + uuidChatGPT + "';");
                        return true;
                    }  else if (url.startsWith("spinneron://")) {
                        progressBarChatGPT.setVisibility(View.VISIBLE);
                        return true;
                    } else if (url.startsWith("spinneroff://")) {
                        progressBarChatGPT.setVisibility(View.GONE);
                        return true;
                    }
                    else if (url.startsWith("getappversion://")) {
                        webViewChatGPT.loadUrl("javascript: var versionNumber = '" + BuildConfig.VERSION_NAME + "';" +
                                "var bundleNumber  = '" + BuildConfig.VERSION_CODE + "';");
                        return true;
                    }else if (url.startsWith("reset://")) {
                        WebSettings webSettingsChatGPT = webViewChatGPT.getSettings();
                       // webSettingsChatGPT.setAppCacheEnabled(false);
                        webSettingsChatGPT.setCacheMode(WebSettings.LOAD_NO_CACHE);
                        webViewChatGPT.clearCache(true);
                        Toast.makeText(GuideActivity.this, "App reset was successful.", Toast.LENGTH_LONG).show();
                        loadMainUrlChatGPT();
                        return true;
                    } else if (url.startsWith("shareapp://")) {
                        String shareMessageChatGPT = "\nLet me recommend you this application\n\n";
                        shareMessageChatGPT = shareMessageChatGPT + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                        if (url.contains("sharetext?=")) {
                            String key_share_text = "sharetext?=";
                            int firstIndex = url.lastIndexOf(key_share_text);
                            shareMessageChatGPT = url.substring(firstIndex + key_share_text.length());
                        }
                        Intent shareIntentChatGPT = new Intent(Intent.ACTION_SEND);
                        shareIntentChatGPT.setType("text/plain");
                        shareIntentChatGPT.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
                        shareIntentChatGPT.putExtra(Intent.EXTRA_TEXT, shareMessageChatGPT);
                        startActivity(Intent.createChooser(shareIntentChatGPT, "Share the app"));
                        return true;
                    }
                } else if (!hasChatGPTInternetConnected(GuideActivity.this)) {
                    offlineLayoutChatGPT.setVisibility(View.VISIBLE);
                    return true;
                }

                if (hostpartChatGPT.contains("whatsapp.com")) {
                    final Intent intentChatGPT = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    final int newDocumentFlagChatGPT = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) ? Intent.FLAG_ACTIVITY_NEW_DOCUMENT : Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET;
                    intentChatGPT.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | newDocumentFlagChatGPT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    startActivity(intentChatGPT);
                }
                if (((ChatGPTEXTERNAL_URL_HANDLING_OPTIONS != 0)
                        && !(url).startsWith("file://") && (!ChatGPTUSE_LOCAL_HTML_FOLDER
                        || !(url).startsWith("file://"))) && URLUtil.isValidUrl(url)) {

                    if (ChatGPTEXTERNAL_URL_HANDLING_OPTIONS == 1) {
                        CustomTabsIntent.Builder builderChatGPT = new CustomTabsIntent.Builder();
                        builderChatGPT.setToolbarColor(getResources().getColor(R.color.purple_700));
                        CustomTabsIntent customTabsIntentChatGPT = builderChatGPT.build();
                        customTabsIntentChatGPT.launchUrl(GuideActivity.this, Uri.parse(url));
                        webViewChatGPT.stopLoading();
                        return true;
                    } else if (ChatGPTEXTERNAL_URL_HANDLING_OPTIONS == 2) {
                        Intent intentChatGPT = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intentChatGPT);
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        if (ChatGPTEXIT_APP_BY_BACK_BUTTON_ALWAYS) {
            super.onBackPressed();
        } else if (webViewChatGPT.canGoBack()) {
            webViewChatGPT.goBack();
        }
        else if (ChatGPTEXIT_APP_BY_BACK_BUTTON_HOMEPAGE) {
            super.onBackPressed();
        }

    }

    private void customCSSChatGPT() {
        try {
            InputStream inputStreamChatGPT = getAssets().open("custom.css");
            byte[] cssbufferChatGPT = new byte[inputStreamChatGPT.available()];
            inputStreamChatGPT.read(cssbufferChatGPT);
            inputStreamChatGPT.close();

            String encodedcssChatGPT = Base64.encodeToString(cssbufferChatGPT, Base64.NO_WRAP);
            if (!TextUtils.isEmpty(encodedcssChatGPT)) {
                webViewChatGPT.loadUrl("javascript:(function() {" +
                        "var parent = document.getElementsByTagName('head').item(0);" +
                        "var style = document.createElement('style');" +
                        "style.type = 'text/css';" +
                        "style.innerHTML = window.atob('" + encodedcssChatGPT + "');" +
                        "parent.appendChild(style)" +
                        "})()");
            }
        } catch (Exception ChatGPT) {

        }
    }
    private void loadMainUrlChatGPT() {

        if (hasChatGPTInternetConnected(GuideActivity.this)) {
            offlineLayoutChatGPT.setVisibility(View.GONE);

            if (ChatGPTIS_LINKING_ENABLED && deepLinkingURLChatGPT != null && !deepLinkingURLChatGPT.isEmpty()) {
                if (isNotificationURLChatGPT && ChatGPTOPEN_NOTIFICATION_URLS_IN_SYSTEM_BROWSER && URLUtil.isValidUrl(deepLinkingURLChatGPT)) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(deepLinkingURLChatGPT)));
                    deepLinkingURLChatGPT = null;
                } else if (URLUtil.isValidUrl(deepLinkingURLChatGPT)) {
                    webViewChatGPT.loadUrl(deepLinkingURLChatGPT);
                    return;
                } else {
                    Toast.makeText(this, "URL is not valid", Toast.LENGTH_SHORT).show();
                }
            }
            String urlExtChatGPT = "";
            String urlExt2ChatGPT = "";
            String languageChatGPT = "";
            if (ChatGPTAPPEND_LANG_CODE) {
                languageChatGPT = Locale.getDefault().getLanguage().toUpperCase();
                languageChatGPT = "?webview_language=" + languageChatGPT;
            } else {
                languageChatGPT = "";
            }
            String urlToLoadChatGPT = ChatGPTHOME_URL + languageChatGPT;
            if (ChatGPTUSE_LOCAL_HTML_FOLDER) {
                loadLocalChatGPT(ChatGPTINDEX_FILE);
            } else {
                webViewChatGPT.loadUrl(urlToLoadChatGPT + urlExtChatGPT + urlExt2ChatGPT);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (requestCode == REQUEST_SELECT_FILEChatGPT) {
                if (uploadMessageChatGPT == null)
                    return;
                uploadMessageChatGPT.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, intent));
                uploadMessageChatGPT = null;
            }
        }
        Uri[] resultsChatGPT = null;
        Uri uriChatGPT = null;
        if (requestCode == FCRChatGPT) {
            if (resultCode == Activity.RESULT_OK) {
                if (mUMAChatGPT == null) {
                    return;
                }
                if (intent == null || intent.getData() == null) {

                    if (intent != null && intent.getClipData() != null) {

                        int countChatGPT = intent.getClipData().getItemCount(); //evaluate the countChatGPT before the for loop --- otherwise, the countChatGPT is evaluated every loop.
                        resultsChatGPT = new Uri[intent.getClipData().getItemCount()];
                        for (int i = 0; i < countChatGPT; i++) {
                            uriChatGPT = intent.getClipData().getItemAt(i).getUri();
                            resultsChatGPT[i] = uriChatGPT;

                        }
                    }

                    if (mCMChatGPT != null) {
                        File fileChatGPT = new File(Uri.parse(mCMChatGPT).getPath());
                        if (fileChatGPT.length() > 0)
                            resultsChatGPT = new Uri[]{Uri.parse(mCMChatGPT)};
                        else
                            fileChatGPT.delete();
                    }
                    if (mVMChatGPT != null) {
                        File fileChatGPT = new File(Uri.parse(mVMChatGPT).getPath());
                        if (fileChatGPT.length() > 0)
                            resultsChatGPT = new Uri[]{Uri.parse(mVMChatGPT)};
                        else
                            fileChatGPT.delete();
                    }

                } else {
                    String dataStringChatGPT = intent.getDataString();
                    if (dataStringChatGPT != null) {
                        resultsChatGPT = new Uri[]{Uri.parse(dataStringChatGPT)};
                    } else {
                        if (intent.getClipData() != null) {
                            final int numSelectedFilesChatGPT = intent.getClipData().getItemCount();
                            resultsChatGPT = new Uri[numSelectedFilesChatGPT];
                            for (int i = 0; i < numSelectedFilesChatGPT; i++) {
                                resultsChatGPT[i] = intent.getClipData().getItemAt(i).getUri();
                            }
                        }
                    }
                }
            } else {
                if (mCMChatGPT != null) {
                    File fileChatGPT = new File(Uri.parse(mCMChatGPT).getPath());
                    if (fileChatGPT != null) fileChatGPT.delete();
                }
                if (mVMChatGPT != null) {
                    File fileChatGPT = new File(Uri.parse(mVMChatGPT).getPath());
                    if (fileChatGPT != null) fileChatGPT.delete();
                }
            }
            mUMAChatGPT.onReceiveValue(resultsChatGPT);
            mUMAChatGPT = null;
        } else if (requestCode == ChatGPTCODE_AUDIO_CHOOSER) {
            if (resultCode == Activity.RESULT_OK) {
                if (intent != null && intent.getData() != null) {
                    resultsChatGPT = new Uri[]{intent.getData()};
                }
            }
            mUMAChatGPT.onReceiveValue(resultsChatGPT);
            mUMAChatGPT = null;
        } else if (requestCode == ChatGPTREQUEST_CODE_QR_SCAN) {
            if (resultCode == Activity.RESULT_OK) {
                if (intent != null) {
                    String resultChatGPT = intent.getStringExtra("com.blikoon.qrcodescanner.got_qr_scan_relult");
                    if (resultChatGPT != null && URLUtil.isValidUrl(resultChatGPT)) {
                        webViewChatGPT.loadUrl(resultChatGPT);
                    }
                }
            }
        }
    }


    @Override
    public void onPause() {
        isInBackGroundChatGPT =true;
        TimeStampChatGPT = Calendar.getInstance().getTimeInMillis();
        super.onPause();
    }

    private class CustomeGestureDetectorChatGPT extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (ChatGPTENABLE_SWIPE_NAVIGATE) {
                if (e1 == null || e2 == null) return false;
                if (e1.getPointerCount() > 1 || e2.getPointerCount() > 1) return false;
                else {
                    try {
                        if (e1.getX() - e2.getX() > 100 && Math.abs(velocityX) > 800) {
                            if (webViewChatGPT.canGoBack()) {
                                webViewChatGPT.goBack();
                            }
                            return true;
                        }
                        else if (e2.getX() - e1.getX() > 100 && Math.abs(velocityX) > 800) {
                            if (webViewChatGPT.canGoForward()) {
                                webViewChatGPT.goForward();
                            }
                            return true;
                        }
                    } catch (Exception e) {
                    }
                    return false;
                }
            }
            return false;
        }
    }

}
