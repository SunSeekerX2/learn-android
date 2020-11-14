package cn.yoouu.learn.demo.words;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import cn.yoouu.learn.R;
import cn.yoouu.learn.demo.words.adapter.MyAdapter;
import cn.yoouu.learn.demo.words.entity.Word;
import cn.yoouu.learn.demo.words.viewmodel.WordViewModel;

public class DemoWordsActivity extends AppCompatActivity {
    WordViewModel wordViewModel;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    Button mBtnInsert, mBtnUpdate, mBtnClear, mBtnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_words);
        mBtnInsert = findViewById(R.id.btn_demo_words_insert);
        mBtnUpdate = findViewById(R.id.btn_demo_words_update);
        mBtnClear = findViewById(R.id.btn_demo_words_clear);
        mBtnDelete = findViewById(R.id.btn_demo_words_delete);
        recyclerView = findViewById(R.id.demo_wrods_recyclerview);

        myAdapter = new MyAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);

        wordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        wordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                myAdapter.setAllWords(words);
                myAdapter.notifyDataSetChanged();
            }
        });

        mBtnInsert.setOnClickListener((View v) -> {
            String[] english = {"migration", "incredible", "thumb", "separate", "roughly", "relayed", "legacy", "tech", "vocab", "Axis"};
            String[] chinese = {"迁移", "难以置信", "拇指", "分离", "粗糙地", "转述", "遗产", "科技", "词汇", "轴线"};
            for (int i = 0; i < english.length; i++) {
                wordViewModel.insertWords(new Word(english[i], chinese[i]));
            }
        });

        mBtnClear.setOnClickListener((View v) -> {
            wordViewModel.deleteAllWords();
        });
    }
}