package cn.yoouu.learn.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import cn.yoouu.learn.R;
import cn.yoouu.learn.demo.bottom_navigation.DemoBottomNavigationActivity;
import cn.yoouu.learn.demo.calculation.CalculationActivity;
import cn.yoouu.learn.demo.gallery.DemoGalleryActivity;
import cn.yoouu.learn.demo.paging.DempPagingActivity;
import cn.yoouu.learn.demo.score.DemoScoreActivity;
import cn.yoouu.learn.demo.serializable.DemoSerializableActivity;
import cn.yoouu.learn.demo.words.DemoWordsActivity;

public class MainActivity extends AppCompatActivity {
  Button mBtnLifeCycle;
  Button mBtnViewModel;
  Button mBtnViewModelWithLiveData;
  Button mBtnViewModelWithDataBinding;
  Button mBtnDemoScore;
  Button mBtnSharedPreferences;
  Button mBtnDemoCalc;
  Button mBtnDemoWords;
  Button mBtnDemoButtonNavigation;
  Button mBtnDemoPaging;
  Button mBtnDemoSerializable;
  Button mBtnDemoGallery;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mBtnLifeCycle = findViewById(R.id.btn_lifecycle);
    mBtnViewModel = findViewById(R.id.btn_viewmodel);
    mBtnViewModelWithLiveData = findViewById(R.id.btn_viewmodel_with_livedata);
    mBtnViewModelWithDataBinding = findViewById(R.id.btn_viewmodel_with_databinding);
    mBtnDemoScore = findViewById(R.id.btn_demo_score);
    mBtnSharedPreferences = findViewById(R.id.btn_sp);
    mBtnDemoCalc = findViewById(R.id.btn_demo_calc);
    mBtnDemoWords = findViewById(R.id.btn_demo_words);
    mBtnDemoButtonNavigation = findViewById(R.id.btnDemoButtonNavigation);
    mBtnDemoPaging = findViewById(R.id.btnDemoPaging);
    mBtnDemoSerializable = findViewById(R.id.btnDemoSerializable);
    mBtnDemoGallery = findViewById(R.id.btnDemoGallery);

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
    mBtnDemoScore.setOnClickListener((View v) -> {
      Intent intent = new Intent(MainActivity.this, DemoScoreActivity.class);
      startActivity(intent);
    });
    mBtnSharedPreferences.setOnClickListener((View v) -> {
      Intent intent = new Intent(MainActivity.this, SharedPreferencesActivity.class);
      startActivity(intent);
    });
    mBtnDemoCalc.setOnClickListener((View v) -> {
      Intent intent = new Intent(MainActivity.this, CalculationActivity.class);
      startActivity(intent);
    });
    mBtnDemoWords.setOnClickListener((View v) -> {
      Intent intent = new Intent(MainActivity.this, DemoWordsActivity.class);
      startActivity(intent);
    });
    mBtnDemoButtonNavigation.setOnClickListener((View v) -> {
      Intent intent = new Intent(MainActivity.this, DemoBottomNavigationActivity.class);
      startActivity(intent);
    });
    mBtnDemoPaging.setOnClickListener((View v) -> {
      Intent intent = new Intent(MainActivity.this, DempPagingActivity.class);
      startActivity(intent);
    });
    mBtnDemoSerializable.setOnClickListener((View v) -> {
      Intent intent = new Intent(MainActivity.this, DemoSerializableActivity.class);
      startActivity(intent);
    });
    mBtnDemoGallery.setOnClickListener((View v) -> {
      Intent intent = new Intent(MainActivity.this, DemoGalleryActivity.class);
      startActivity(intent);
    });
  }
}