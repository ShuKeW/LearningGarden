package com.learninggarden;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * @author weishukai
 * @date 2019-06-19   10:45
 * @describe
 */
public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("aaa", "getInterfaces length:" + InterfaceB.class.getInterfaces().length);
    }
}
