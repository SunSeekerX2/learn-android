package cn.yoouu.learn.demo.words.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import cn.yoouu.learn.demo.words.WordDatabase;
import cn.yoouu.learn.demo.words.dao.WordDao;
import cn.yoouu.learn.demo.words.entity.Word;

public class WordRepository {
    private final LiveData<List<Word>> allWords;
    private final WordDao wordDao;

    public WordRepository(Context context) {
        WordDatabase wordDatabase = WordDatabase.getDataBase(context.getApplicationContext());
        wordDao = wordDatabase.getWordDao();
        allWords = wordDao.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return allWords;
    }

    public void insertWords(Word... words) {
        new InsertAsyncTask(wordDao).execute(words);
    }

    public void updateWords(Word... words) {
        new UpdatetAsyncTask(wordDao).execute(words);
    }

    public void deleteWords(Word... words) {
        new DeletetAsyncTask(wordDao).execute(words);
    }

    public void deleteAllWords() {
        new DeletetAllAsyncTask(wordDao).execute();
    }

    static class InsertAsyncTask extends AsyncTask<Word, Void, Void> {
        private final WordDao wordDao;

        public InsertAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.insertWords(words);
            return null;
        }
    }

    static class UpdatetAsyncTask extends AsyncTask<Word, Void, Void> {
        private final WordDao wordDao;

        public UpdatetAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.updateWords(words);
            return null;
        }
    }

    static class DeletetAsyncTask extends AsyncTask<Word, Void, Void> {
        private final WordDao wordDao;

        public DeletetAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.deleteWords(words);
            return null;
        }
    }

    static class DeletetAllAsyncTask extends AsyncTask<Word, Void, Void> {
        private final WordDao wordDao;

        public DeletetAllAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.deleteAllWords();
            return null;
        }
    }
}
