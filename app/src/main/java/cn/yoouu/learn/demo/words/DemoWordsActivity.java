package cn.yoouu.learn.demo.words;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.List;

import cn.yoouu.learn.R;
import cn.yoouu.learn.demo.words.adapter.MyAdapter;
import cn.yoouu.learn.demo.words.entity.Word;
import cn.yoouu.learn.demo.words.viewmodel.WordViewModel;

public class DemoWordsActivity extends AppCompatActivity {
    WordViewModel wordViewModel;
    RecyclerView recyclerView;
    MyAdapter myAdapter1, myAdapter2;
    Button mBtnInsert, mBtnClear;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch mSwitch1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_words);
        mBtnInsert = findViewById(R.id.btn_demo_words_insert);
        mBtnClear = findViewById(R.id.btn_demo_words_clear);
        recyclerView = findViewById(R.id.demo_wrods_recyclerview);
        mSwitch1 = findViewById(R.id.switch1);

        myAdapter1 = new MyAdapter(false);
        myAdapter2 = new MyAdapter(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter1);

        wordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        wordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                myAdapter1.setAllWords(words);
                myAdapter2.setAllWords(words);
                myAdapter1.notifyDataSetChanged();
                myAdapter2.notifyDataSetChanged();
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

        mSwitch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    recyclerView.setAdapter(myAdapter2);
                } else {
                    recyclerView.setAdapter(myAdapter1);
                }
            }
        });
    }
}