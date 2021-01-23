package com.sample.accountkit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.huawei.hms.support.hwid.ui.HuaweiIdAuthButton;

public class MainActivity2 extends AppCompatActivity {
HuaweiIdAuthButton btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
}