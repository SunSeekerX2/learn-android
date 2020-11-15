package cn.yoouu.learn.demo.serializable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import cn.yoouu.learn.R;
import cn.yoouu.learn.databinding.ActivityDemoParcelableBinding;

public class DemoParcelableActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityDemoParcelableBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_demo_parcelable);
    Intent intent = getIntent();
    StudentUsingPrcelable student = intent.getBundleExtra("student_bundle").getParcelable("student");
//    System.out.println(student.toString());
    binding.demoParcelableTextViewName.setText(student.getName());
    binding.demoParcelableTextViewAge.setText(String.valueOf(student.getAge()));
    /**
     * 这里拿不到 score 嵌套对象，但是序列化之前是存在的，不清楚原因
     */
//    binding.demoParcelableTextViewMath.setText(String.valueOf(student.getScore().getMath()));
//    binding.demoParcelableTextViewEnglish.setText(String.valueOf(student.getScore().getEnglish()));
//    binding.demoParcelableTextViewChinese.setText(String.valueOf(student.getScore().getChinese()));
//    binding.demoParcelableTextViewGrade.setText(String.valueOf(student.getScore().getGrade()));
  }
}