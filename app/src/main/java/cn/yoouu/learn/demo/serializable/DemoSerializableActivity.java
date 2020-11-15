package cn.yoouu.learn.demo.serializable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import cn.yoouu.learn.R;

public class DemoSerializableActivity extends AppCompatActivity {
  EditText editTextName, editTextAge, editTextMath, editTextEnglish, editTextChinese;
  Button mBtnSave, mBtnLoad, mBtnParcelable;
  TextView textViewGrade;
  private final String FILE_NAME = "my_data";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_demo_serializable);
    editTextName = findViewById(R.id.demoSerializableEditTextName);
    editTextAge = findViewById(R.id.demoSerializableEditTextAge);
    editTextMath = findViewById(R.id.demoSerializableEditTextMath);
    editTextEnglish = findViewById(R.id.demoSerializableEditTextEnglish);
    editTextChinese = findViewById(R.id.demoSerializableEditTextChinese);
    mBtnSave = findViewById(R.id.demoSerializableButtonSave);
    mBtnLoad = findViewById(R.id.demoSerializableButtonLoad);
    mBtnParcelable = findViewById(R.id.demoSerializableButtonParcelable);
    textViewGrade = findViewById(R.id.demoSerializableTextViewGrade);

    mBtnSave.setOnClickListener((View v) -> {
      int math = Integer.parseInt(editTextMath.getText().toString().trim());
      int english = Integer.parseInt(editTextEnglish.getText().toString().trim());
      int chinese = Integer.parseInt(editTextChinese.getText().toString().trim());

      ScoreUsingJava score = new ScoreUsingJava(math, english, chinese);

      String name = editTextName.getText().toString().trim();
      int age = Integer.parseInt(editTextAge.getText().toString().trim());
      StudentUsingJava student = new StudentUsingJava(name, age, score);
      try {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(openFileOutput(FILE_NAME, MODE_PRIVATE));
        objectOutputStream.writeObject(student);
        objectOutputStream.flush();
        objectOutputStream.close();
        ToastUtils.showShort("保存成功");
        editTextName.setText("");
        editTextAge.setText("");
        editTextMath.setText("");
        editTextEnglish.setText("");
        editTextChinese.setText("");
        textViewGrade.setText("-");
      } catch (IOException e) {
        e.printStackTrace();
      }
    });

    mBtnLoad.setOnClickListener((View view) -> {
      try {
        ObjectInputStream objectInputStream = new ObjectInputStream(openFileInput(FILE_NAME));
        StudentUsingJava student = (StudentUsingJava) objectInputStream.readObject();
        editTextName.setText(student.getName());
        editTextAge.setText(String.valueOf(student.getAge()));
        editTextMath.setText(String.valueOf(student.getScore().getMath()));
        editTextEnglish.setText(String.valueOf(student.getScore().getEnglish()));
        editTextChinese.setText(String.valueOf(student.getScore().getChinese()));
        textViewGrade.setText(student.getScore().getGrade());
      } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
      }
    });

    /**
     * 使用 parcelable
     */
    mBtnParcelable.setOnClickListener((View view) -> {
      String name = editTextName.getText().toString().trim();
      int age = Integer.parseInt(editTextAge.getText().toString().trim());

      int math = Integer.parseInt(editTextMath.getText().toString().trim());
      int english = Integer.parseInt(editTextEnglish.getText().toString().trim());
      int chinese = Integer.parseInt(editTextChinese.getText().toString().trim());
      StudentUsingPrcelable student = new StudentUsingPrcelable(name, age, new ScoreUsingParcelable(math, english, chinese));

      /**
       * parcelable 支持进程间通信
       * DemoParcelableActivity 已经设置为新的进程 可以到清单文件查看
       */
      Intent intent = new Intent(DemoSerializableActivity.this, DemoParcelableActivity.class);
      Bundle bundle = new Bundle();
//      System.out.println(">>>>>>>>>>>"+ student.toString());
      bundle.putParcelable("student", student);
      intent.putExtra("student_bundle", bundle);
      startActivity(intent);
    });
  }
}