package com.testdemo.sample.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.testdemo.sample.R;

public class DashboardActivity extends AppCompatActivity {

    private ImageView buttonGuide,buttonGShiftLabs,buttonHelpTest,buttonDev,buttonOpenAI;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        getSupportActionBar().hide();

        buttonGuide = findViewById(R.id.button_chatgpt);
        buttonGShiftLabs = findViewById(R.id.button_g_shift_labs);
        buttonHelpTest = findViewById(R.id.button_help_test);
        buttonDev = findViewById(R.id.button_dev);
        buttonOpenAI = findViewById(R.id.button_open_ai);

        buttonGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(DashboardActivity.this,MainActivity.class));
            }
        });

        buttonGShiftLabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GuideActivity.ChatGPTHOME_URL ="https://gshiftlabs.com/chat-gpt-a-beginners-guide/";
                startActivity(new Intent(DashboardActivity.this,GuideActivity.class));
            }
        });

        buttonHelpTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GuideActivity.ChatGPTHOME_URL ="https://helpfirst.in/what-is-chat-gpt/";
                startActivity(new Intent(DashboardActivity.this,GuideActivity.class));
            }
        });
        buttonDev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GuideActivity.ChatGPTHOME_URL ="https://dev.to/adriantwarog/ultimate-chatgpt-resource-guide-chatgpt-tutorial-dfe";
                startActivity(new Intent(DashboardActivity.this,GuideActivity.class));
            }
        });
        buttonOpenAI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GuideActivity.ChatGPTHOME_URL ="https://openai.com/blog/chatgpt/";
                startActivity(new Intent(DashboardActivity.this,GuideActivity.class));
            }
        });

    }

}