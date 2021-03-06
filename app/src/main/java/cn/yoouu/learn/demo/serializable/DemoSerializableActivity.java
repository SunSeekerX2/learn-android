package cn.yoouu.learn.demo.serializable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import cn.yoouu.learn.R;

public class DemoSerializableActivity extends AppCompatActivity {
  EditText editTextName, editTextAge, editTextMath, editTextEnglish, editTextChinese;
  Button mBtnSave, mBtnLoad, mBtnParcelable,mBtnGson;
  TextView textViewGrade, textViewGson;
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
    mBtnGson = findViewById(R.id.demoSerializableButtonGson);
    textViewGrade = findViewById(R.id.demoSerializableTextViewGrade);
    textViewGson = findViewById(R.id.demoSerializableTextViewGson);

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
        ToastUtils.showShort("????????????");
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
     * ?????? parcelable
     */
    mBtnParcelable.setOnClickListener((View view) -> {
      String name = editTextName.getText().toString().trim();
      int age = Integer.parseInt(editTextAge.getText().toString().trim());

      int math = Integer.parseInt(editTextMath.getText().toString().trim());
      int english = Integer.parseInt(editTextEnglish.getText().toString().trim());
      int chinese = Integer.parseInt(editTextChinese.getText().toString().trim());
      StudentUsingPrcelable student = new StudentUsingPrcelable(name, age, new ScoreUsingParcelable(math, english, chinese));

      /**
       * parcelable ?????????????????????
       * DemoParcelableActivity ??????????????????????????? ???????????????????????????
       */
      Intent intent = new Intent(DemoSerializableActivity.this, DemoParcelableActivity.class);
      Bundle bundle = new Bundle();
//      System.out.println(">>>>>>>>>>>"+ student.toString());
      bundle.putParcelable("student", student);
      intent.putExtra("student_bundle", bundle);
      startActivity(intent);
    });

    /**
     * ?????? Gson
     */
    mBtnGson.setOnClickListener((View view) -> {
      String name = editTextName.getText().toString().trim();
      int age = Integer.parseInt(editTextAge.getText().toString().trim());

      int math = Integer.parseInt(editTextMath.getText().toString().trim());
      int english = Integer.parseInt(editTextEnglish.getText().toString().trim());
      int chinese = Integer.parseInt(editTextChinese.getText().toString().trim());
      StudentUsingPrcelable student = new StudentUsingPrcelable(name, age, new ScoreUsingParcelable(math, english, chinese));
      Gson gson = new Gson();
      String jsonStudent = gson.toJson(student);
      textViewGson.setText(jsonStudent);
    });
  }
}