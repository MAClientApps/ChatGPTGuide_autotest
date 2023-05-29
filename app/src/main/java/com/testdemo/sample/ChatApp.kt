package com.testdemo.sample

import android.app.Activity
import android.app.Application
import android.content.Context
import android.net.Uri
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.util.Log
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.adjust.sdk.*
import com.google.firebase.analytics.FirebaseAnalytics
import com.testdemo.sample.common.Utils
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ChatApp : Application(), ImageLoaderFactory {

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .components {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()
    }
    var context_ChatGPT: Context? = null

    override fun onCreate() {
        super.onCreate()

        Log.e("Tag", "onCreate")
        try {
            context_ChatGPT = this
        } catch (ChatGPT_exception: Exception) {
        }

        val configAdjust_ChatGPT = AdjustConfig(
            this, Utils.ChatGPT_ADJUST_TOKEN,
            AdjustConfig.ENVIRONMENT_PRODUCTION
        )

        Adjust.addSessionCallbackParameter(
            Utils.ChatGPT_User_UUID,
            Utils.createChatGPT_ClickID(applicationContext)
        )

        configAdjust_ChatGPT.setOnAttributionChangedListener { attribution: AdjustAttribution ->
            Log.e("Tag", "setOnAttributionChangedListener")
            Utils.saveChatGPT_AdjustAttribute(
                applicationContext,
                attribution.toString()
            )
            Utils.saveChatGPT_Campaign(context_ChatGPT, attribution.campaign)
        }

        configAdjust_ChatGPT.setOnDeeplinkResponseListener { deeplink: Uri ->
            Log.e("Tag", "setOnDeeplinkResponseListener")
            Utils.saveChatGPT_link(applicationContext, deeplink.toString())
            false
        }


        try {
            context_ChatGPT?.let {
                FirebaseAnalytics.getInstance(it)
                    .getAppInstanceId()
                    .addOnCompleteListener { task ->
                        val adjustEvent_ChatGPT = AdjustEvent(Utils.ChatGPT_EVENT_TOKEN)
                        adjustEvent_ChatGPT.addCallbackParameter(
                            Utils.ChatGPT_EVENT_PARAM_KEY,
                            task.getResult()
                        )
                        adjustEvent_ChatGPT.addCallbackParameter(
                            Utils.ChatGPT_User_UUID,
                            Utils.createChatGPT_ClickID(applicationContext)
                        )
                        Adjust.addSessionCallbackParameter(
                            Utils.ChatGPT_PARAM_FIREBASE_INSTANCE_ID,
                            task.getResult()
                        )
                        Adjust.trackEvent(adjustEvent_ChatGPT)
                        Adjust.sendFirstPackages()
                    }
            }
        } catch (e: Exception) {
        }
        configAdjust_ChatGPT.setDelayStart(1.5)
        Adjust.onCreate(configAdjust_ChatGPT)

        registerActivityLifecycleCallbacks(AdjustLifecycleCallbacks())
    }
    private class AdjustLifecycleCallbacks : ActivityLifecycleCallbacks {
        override fun onActivityCreated(
            activity: Activity,
            savedInstanceState: Bundle?
        ) {
        }

        override fun onActivityStarted(activity: Activity) {}
        override fun onActivityResumed(activity: Activity) {
            Adjust.onResume()
        }

        override fun onActivityPaused(activity: Activity) {
            Adjust.onPause()
        }

        override fun onActivityStopped(activity: Activity) {}
        override fun onActivitySaveInstanceState(
            activity: Activity,
            outState: Bundle
        ) {
        }

        override fun onActivityDestroyed(activity: Activity) {}
    }

}