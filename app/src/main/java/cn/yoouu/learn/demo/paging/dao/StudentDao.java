package cn.yoouu.learn.demo.paging.dao;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import cn.yoouu.learn.demo.paging.entity.Student;

@Dao
public interface StudentDao {
  @Insert
  void insertStudent(Student... students);

  @Query("DELETE FROM student_table")
  void deleteAllStudent();

  @Query("SELECT * FROM student_table ORDER BY ID DESC")
  DataSource.Factory<Integer, Student> getAllStudents();
}
