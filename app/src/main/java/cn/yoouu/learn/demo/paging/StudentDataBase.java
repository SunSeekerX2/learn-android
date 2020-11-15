package cn.yoouu.learn.demo.paging;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import cn.yoouu.learn.demo.paging.dao.StudentDao;
import cn.yoouu.learn.demo.paging.entity.Student;

@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class StudentDataBase extends RoomDatabase {
  static StudentDataBase instance;

  public static synchronized StudentDataBase getInstance(Context context) {
    if (instance == null) {
      instance = Room.databaseBuilder(context, StudentDataBase.class, "student_database").build();
    }
    return instance;
  }

  abstract StudentDao getStudentDao();
}
