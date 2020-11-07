package cn.yoouu.learn.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import cn.yoouu.learn.R;
import cn.yoouu.learn.viewmodel.DataBindingViewModel;

public class ViewModelWithLiveDataActivity extends AppCompatActivity {
    DataBindingViewModel dataBindingViewModel;
    TextView textView;
    ImageButton imageButton;
    ImageButton imageButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_model_with_live_data_);
        textView = findViewById(R.id.textview_databindingviewmodel);
        imageButton = findViewById(R.id.imageButton);
        imageButton2 = findViewById(R.id.imageButton2);

        dataBindingViewModel = new ViewModelProvider(this).get(DataBindingViewModel.class);

        dataBindingViewModel.getLikedNumber().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                textView.setText(String.valueOf(integer));
            }
        });

        imageButton.setOnClickListener((View v) -> {
            dataBindingViewModel.addNum(1);
        });
        imageButton2.setOnClickListener((View v) -> {
            dataBindingViewModel.addNum(-1);
        });
    }
}