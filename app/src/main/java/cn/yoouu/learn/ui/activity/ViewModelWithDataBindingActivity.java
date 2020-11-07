package cn.yoouu.learn.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import cn.yoouu.learn.R;
import cn.yoouu.learn.databinding.ActivityViewModelWithDataBindingBinding;
import cn.yoouu.learn.viewmodel.DataBindingViewModel;

public class ViewModelWithDataBindingActivity extends AppCompatActivity {
    DataBindingViewModel dataBindingViewModel;
//    TextView textView;
//    ImageButton imageButton;
//    ImageButton imageButton2;
    ActivityViewModelWithDataBindingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_view_model_with_data_binding);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_model_with_data_binding);
//        binding.textviewDatabindingviewmodel
//        textView = findViewById(R.id.textview_databindingviewmodel);
//        imageButton = findViewById(R.id.imageButton);
//        imageButton2 = findViewById(R.id.imageButton2);

        dataBindingViewModel = new ViewModelProvider(this).get(DataBindingViewModel.class);
        binding.setData(dataBindingViewModel);
        // 不可少
        binding.setLifecycleOwner(this);

//        dataBindingViewModel.getLikedNumber().observe(this, new Observer<Integer>() {
//            @Override
//            public void onChanged(Integer integer) {
//                binding.textView.setText(String.valueOf(integer));
//            }
//        });

//        binding.imageButton.setOnClickListener((View v) -> {
//            dataBindingViewModel.addNum(1);
//        });
//        binding.imageButton2.setOnClickListener((View v) -> {
//            dataBindingViewModel.addNum(-1);
//        });
    }
}