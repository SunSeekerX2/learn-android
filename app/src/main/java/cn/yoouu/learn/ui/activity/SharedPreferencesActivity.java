package cn.yoouu.learn.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import cn.yoouu.learn.R;

public class SharedPreferencesActivity extends AppCompatActivity {

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        // 默认生成的文件名和 Activity 名字一样
//        SharedPreferences shp = getPreferences(Context.MODE_PRIVATE);
        // 文件名自己设置
        SharedPreferences shp = getSharedPreferences("my_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shp.edit();

        // 存储
        editor.putInt("INT", 123128);
        // 同步
//        editor.commit();
        // 非同步，有可能产生碰撞
        editor.apply();


        // 读取
        int x  = shp.getInt("INT", 0);
        Log.d("SharedPreferencesActivity：读取>>>", String.valueOf(x));

    }
}