package cn.yoouu.learn.demo.words;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import cn.yoouu.learn.demo.words.dao.WordDao;
import cn.yoouu.learn.demo.words.entity.Word;

@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordDatabase extends RoomDatabase {
    private static WordDatabase INSTANCE;

    public static synchronized WordDatabase getDataBase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WordDatabase.class, "word_database")
                    .allowMainThreadQueries()
                    .build();

        }
        return INSTANCE;
    }

    //    private WordDatabase getDataBase
    public abstract WordDao getWordDao();
}
