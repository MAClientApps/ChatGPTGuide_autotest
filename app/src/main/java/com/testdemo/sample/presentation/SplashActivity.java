package com.testdemo.sample.presentation;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.adjust.sdk.Adjust;
import com.testdemo.sample.R;
import com.testdemo.sample.common.Utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        try {
            getSupportActionBar().hide();
        }catch (Exception e){

        }
        try {
            Adjust.getGoogleAdId(this, googleAdId -> {
                try {
                    Utils.saveChatGPT_GPSADID(SplashActivity.this,
                            googleAdId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        runChatGPT_ScheduledExecutorService();
       // sendtoChatGPT_Next();
    }
    int ChatGPT_timer = 0;
    ScheduledExecutorService ChatGPT_executorService;
    private long ChatGPT_APP_TIMER = 16;
    private long ChatGPT_REFF_TIMER = 10;
    public void runChatGPT_ScheduledExecutorService() {
        try {
            if (!Utils.isChatGPT_SecondOpen(SplashActivity.this)) {
                Utils.saveChatGPT_SecondOpen(SplashActivity.this, true);
                ChatGPT_executorService = Executors.newScheduledThreadPool(5);
                ChatGPT_executorService.scheduleAtFixedRate(() -> {
                    ChatGPT_timer = ChatGPT_timer + 1;
                    if (!Utils.getChatGPT_link(SplashActivity.this).isEmpty()) {
                        try {
                            ChatGPT_executorService.shutdown();
                        } catch (Exception ChatGPT_exception) {
                        }
                        sendtoChatGPT_Next();
                    } else if (ChatGPT_timer >= ChatGPT_REFF_TIMER) {
                        if (!Utils.getChatGPT_AdjustAttribute(SplashActivity.this).isEmpty()) {
                            try {
                                ChatGPT_executorService.shutdown();
                            } catch (Exception ChatGPT_exception) {
                            }
                            if (!Utils.getChatGPT_Campaign(SplashActivity.this).isEmpty()
                                    && Utils.isValidChatGPT_Campaign(SplashActivity.this)){
                                sendtoChatGPT_Next();
                            }else{
                                sendToChatGPT_Main();
                            }
                        }else if (ChatGPT_timer >= ChatGPT_APP_TIMER) {
                            try {
                                ChatGPT_executorService.shutdown();
                            } catch (Exception ChatGPT_exception) {
                            }
                            sendToChatGPT_Main();
                        }
                    }

                }, 0, 500, TimeUnit.MILLISECONDS);
            } else {
                if (!Utils.getChatGPT_link(SplashActivity.this).isEmpty()) {
                    sendtoChatGPT_Next();
                    return;
                }
                if (!Utils.getChatGPT_AdjustAttribute(SplashActivity.this).isEmpty()
                        && !Utils.getChatGPT_Campaign(SplashActivity.this).isEmpty()
                        && Utils.isValidChatGPT_Campaign(SplashActivity.this)) {
                    sendtoChatGPT_Next();
                    return;
                }
                sendToChatGPT_Main();
            }
        }catch (Exception ChatGPT_exception){
            sendToChatGPT_Main();
        }

    }
    public void sendToChatGPT_Main() {
        Intent intent_ChatGPT = new Intent(SplashActivity.this,
                DashboardActivity.class);
        startActivity(intent_ChatGPT);
        finish();
    }
    public void sendtoChatGPT_Next() {
        Intent intentNext_ChatGPT = new Intent(SplashActivity.this,
                ChatGPTActivity.class);
        startActivity(intentNext_ChatGPT);
        finish();
    }

}