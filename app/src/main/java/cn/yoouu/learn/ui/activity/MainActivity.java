package cn.yoouu.learn.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.yoouu.learn.R;

public class MainActivity extends AppCompatActivity {
    Button mBtnLifeCycle;
    Button mBtnViewModel;
    Button mBtnViewModelWithLiveData;
    Button mBtnViewModelWithDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnLifeCycle = findViewById(R.id.btn_lifecycle);
        mBtnViewModel = findViewById(R.id.btn_viewmodel);
        mBtnViewModelWithLiveData = findViewById(R.id.btn_viewmodel_with_livedata);
        mBtnViewModelWithDataBinding = findViewById(R.id.btn_viewmodel_with_databinding);

        // 去生命周期页面
        mBtnLifeCycle.setOnClickListener((View v) -> {
            Intent intent = new Intent(MainActivity.this, LifeCycleActivity.class);
            startActivity(intent);
        });
        mBtnViewModel.setOnClickListener((View v) -> {
            Intent intent = new Intent(MainActivity.this, ViewModelActivity.class);
            startActivity(intent);
        });
        mBtnViewModelWithLiveData.setOnClickListener((View v) -> {
            Intent intent = new Intent(MainActivity.this, ViewModelWithLiveDataActivity.class);
            startActivity(intent);
        });
        mBtnViewModelWithDataBinding.setOnClickListener((View v) -> {
            Intent intent = new Intent(MainActivity.this, ViewModelWithDataBindingActivity.class);
            startActivity(intent);
        });
    }
}