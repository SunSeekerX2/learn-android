package cn.yoouu.learn.demo.score;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import cn.yoouu.learn.R;
import cn.yoouu.learn.databinding.ActivityDemoScoreBinding;

public class DemoScoreActivity extends AppCompatActivity {
    ScoreViewModel scoreViewModel;
    ActivityDemoScoreBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_demo_score);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_demo_score);
        scoreViewModel = new ViewModelProvider(this, new SavedStateViewModelFactory(getApplication(), this)).get(ScoreViewModel.class);
        binding.setData(scoreViewModel);
        binding.setLifecycleOwner(this);
    }
}