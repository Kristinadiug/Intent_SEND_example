package com.example.sms_intent_example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button smsButton;
    private Button mmsButton;

    public void SendSMS() {
        Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:15555215554"));
        smsIntent.putExtra("sms_body", "Hello world!");
        startActivity(smsIntent);
    }

    public void SendMMS() {
        Uri attached_Uri = Uri.parse("https://ibb.co/1Rp74s4");
        Intent mmsIntent = new Intent(Intent.ACTION_SEND);
        mmsIntent.putExtra("sms_body", "Please see the attached image");
        mmsIntent.putExtra("address", "15555215554");
        mmsIntent.putExtra(Intent.EXTRA_STREAM, attached_Uri);
        mmsIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        mmsIntent.setType("image/jpg");
        startActivity(mmsIntent);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.button_send:
                SendSMS();
            case R.id.button_mms:
                SendMMS();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smsButton = findViewById(R.id.button_send);
        smsButton.setOnClickListener(this);

        mmsButton = findViewById(R.id.button_mms);
        mmsButton.setOnClickListener(this);
    }
}