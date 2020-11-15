package cn.yoouu.learn.demo.paging;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.Observable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.yoouu.learn.R;
import cn.yoouu.learn.demo.paging.adapter.MyPagedAdapter;
import cn.yoouu.learn.demo.paging.dao.StudentDao;
import cn.yoouu.learn.demo.paging.entity.Student;

public class DempPagingActivity extends AppCompatActivity {
  RecyclerView recyclerView;
  Button mBtnPopulate, mBtnClear;
  StudentDao studentDao;
  StudentDataBase studentDataBase;
  MyPagedAdapter pagedAdapter;
  LiveData<PagedList<Student>> allStudentsLivePaged;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_demp_paging);
    recyclerView = findViewById(R.id.demoPagedListCellTextView);
    mBtnPopulate = findViewById(R.id.demoPagedButtonPopulate);
    mBtnClear = findViewById(R.id.demoPagedButtonClear);
    pagedAdapter = new MyPagedAdapter();
    recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    recyclerView.setAdapter(pagedAdapter);

    studentDataBase = StudentDataBase.getInstance(this);
    studentDao = studentDataBase.getStudentDao();
    allStudentsLivePaged = new LivePagedListBuilder<>(studentDao.getAllStudents(),  20).build();

    allStudentsLivePaged.observe(this, new Observer<PagedList<Student>>() {
      @Override
      public void onChanged(PagedList<Student> students) {
        pagedAdapter.submitList(students);
      }
    });

    mBtnPopulate.setOnClickListener((View view) -> {
      Student[] students = new Student[1000];
      for (int i = 0; i < 1000; i++) {
        Student student = new Student();
        student.setStudentNumber(i);
        students[i] = student;
      }
      new InsertAsyncTask(studentDao).execute(students);
    });
    mBtnClear.setOnClickListener((View view) -> {
      new ClearAsyncTask(studentDao).execute();
    });
  }

  static class InsertAsyncTask extends AsyncTask<Student, Void, Void> {
    StudentDao studentDao;

    public InsertAsyncTask(StudentDao studentDao) {
      this.studentDao = studentDao;
    }

    @Override
    protected Void doInBackground(Student... students) {
      studentDao.insertStudent(students);
      return null;
    }
  }

  static class ClearAsyncTask extends AsyncTask<Void, Void, Void> {
    StudentDao studentDao;

    public ClearAsyncTask(StudentDao studentDao) {
      this.studentDao = studentDao;
    }

    @Override
    protected Void doInBackground(Void... voids) {
      studentDao.deleteAllStudent();
      return null;
    }
  }
}