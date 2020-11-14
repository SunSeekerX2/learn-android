package cn.yoouu.learn.demo.words;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.List;

import cn.yoouu.learn.R;
import cn.yoouu.learn.demo.words.adapter.MyAdapter;
import cn.yoouu.learn.demo.words.entity.Word;
import cn.yoouu.learn.demo.words.viewmodel.WordViewModel;

public class DemoWordsActivity extends AppCompatActivity {
  private NavController navController;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_demo_words);
    navController = Navigation.findNavController(findViewById(R.id.demo_words_fragment));
    NavigationUI.setupActionBarWithNavController(this, navController);
  }

  @Override
  public boolean onSupportNavigateUp() {
    InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.hideSoftInputFromWindow(findViewById(R.id.demo_words_fragment).getWindowToken(), 0);
    navController.navigateUp();
    return super.onSupportNavigateUp();
  }
}