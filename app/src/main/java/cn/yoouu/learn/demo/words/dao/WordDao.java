package cn.yoouu.learn.demo.words.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import cn.yoouu.learn.demo.words.entity.Word;

@Dao
public interface WordDao {
    @Insert
    void insertWords(Word...words);

    @Update
    void updateWords(Word...words);

    @Delete
    void deleteWords(Word...words);

    @Query("DELETE FROM WORD")
    void deleteAllWords();

    /**
     * 返回liveData 系统自动放在子线程执行
     * @return
     */
    @Query("SELECT * FROM WORD ORDER BY id DESC")
    LiveData<List<Word>> getAllWords();
}
