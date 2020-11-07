package cn.yoouu.learn.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.yoouu.learn.R;
import cn.yoouu.learn.viewmodel.MyViewModel;

public class ViewModelActivity extends AppCompatActivity {
    MyViewModel myViewModel;
    TextView textView;
    Button mbtnPlusOne;
    Button mbtnPlusTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_model);

        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        textView = findViewById(R.id.textView2);
        textView.setText(String.valueOf(myViewModel.num));
        mbtnPlusOne = findViewById(R.id.btn_plus_one);
        mbtnPlusTwo = findViewById(R.id.btn_plus_two);

        mbtnPlusOne.setOnClickListener((View v) -> {
            myViewModel.num++;
            textView.setText(String.valueOf(myViewModel.num));
        });
        mbtnPlusTwo.setOnClickListener((View v) -> {
            myViewModel.num+=2;
            textView.setText(String.valueOf(myViewModel.num));
        });
    }
}