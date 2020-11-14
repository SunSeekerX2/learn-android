package cn.yoouu.learn.demo.words.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import cn.yoouu.learn.demo.words.WordDatabase;
import cn.yoouu.learn.demo.words.dao.WordDao;
import cn.yoouu.learn.demo.words.entity.Word;
import cn.yoouu.learn.demo.words.repository.WordRepository;

public class WordViewModel extends AndroidViewModel {
    private WordDao wordDao;
    private final WordRepository repository;

    public WordViewModel(@NonNull Application application) {
        super(application);
        repository = new WordRepository(application);
    }

    public LiveData<List<Word>> getAllWords() {
        return repository.getAllWords();
    }

    public void insertWords(Word... words) {
        repository.insertWords(words);
    }

    public void updateWords(Word... words) {
        repository.updateWords(words);
    }

    public void deleteWords(Word... words) {
        repository.deleteWords(words);
    }

    public void deleteAllWords() {
        repository.deleteAllWords();
    }

    public LiveData<List<Word>> findWordsWithPattern(String pattern) {
        return repository.findWordsWithPattern(pattern);
    }
}
