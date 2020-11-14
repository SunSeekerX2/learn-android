package cn.yoouu.learn.demo.words.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import cn.yoouu.learn.R;
import cn.yoouu.learn.demo.words.entity.Word;
import cn.yoouu.learn.demo.words.viewmodel.WordViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {
  private Button mBtnSubmit;
  private EditText editTextEnglish, editTextChinese;
  private WordViewModel wordViewModel;

  public AddFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_add, container, false);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    FragmentActivity activity = requireActivity();
    mBtnSubmit = activity.findViewById(R.id.demoWordsButtonSubmit);
    editTextEnglish = activity.findViewById(R.id.demoWordsEditTextEnglish);
    editTextChinese = activity.findViewById(R.id.demoWordsEditTextChinese);
    wordViewModel = new ViewModelProvider(this).get(WordViewModel.class);

    mBtnSubmit.setEnabled(false);
    editTextEnglish.requestFocus();
    InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.showSoftInput(editTextEnglish, 0);

    TextWatcher textWatcher = new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        String english = editTextEnglish.getText().toString().trim();
        String chinese = editTextChinese.getText().toString().trim();
        mBtnSubmit.setEnabled( !english.isEmpty() && !chinese.isEmpty());
      }

      @Override
      public void afterTextChanged(Editable s) {

      }
    };
    editTextEnglish.addTextChangedListener(textWatcher);
    editTextChinese.addTextChangedListener(textWatcher);
    mBtnSubmit.setOnClickListener((View v) -> {
      String english = editTextEnglish.getText().toString().trim();
      String chinese = editTextChinese.getText().toString().trim();
      Word word = new Word(english, chinese);
      wordViewModel.insertWords(word);
      NavController controller = Navigation.findNavController(v);
      controller.navigateUp();
      imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    });
  }
}