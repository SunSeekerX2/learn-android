package cn.yoouu.learn.demo.words.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.yoouu.learn.R;
import cn.yoouu.learn.demo.words.entity.Word;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
  List<Word> allWords = new ArrayList<>();
  boolean isUseCardView = false;

  public MyAdapter(boolean isUseCardView) {
    this.isUseCardView = isUseCardView;
  }

  public void setAllWords(List<Word> allWords) {
    this.allWords = allWords;
  }

  @NonNull
  @Override
  public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    View itemView;
    if (isUseCardView) {
      itemView = layoutInflater.inflate(R.layout.demo_words_list_cell_card, parent, false);
    } else {
      itemView = layoutInflater.inflate(R.layout.demo_words_list_cell_normal, parent, false);
    }
    return new MyViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    Word word = allWords.get(position);
    holder.textViewNumber.setText(String.valueOf(position + 1));
    holder.textViewEnglish.setText(word.getWord());
    holder.textViewChinese.setText(word.getChineseMeaning());

    holder.itemView.setOnClickListener((View v) -> {
      Uri uri = Uri.parse("https://m.youdao.com/dict?le=eng&q=" + holder.textViewEnglish.getText());
      Intent intent = new Intent(Intent.ACTION_VIEW);
      intent.setData(uri);
      holder.itemView.getContext().startActivity(intent);
    });
  }

  @Override
  public int getItemCount() {
    return allWords.size();
  }

  static class MyViewHolder extends RecyclerView.ViewHolder {
    TextView textViewNumber, textViewEnglish, textViewChinese;

    public MyViewHolder(@NonNull View itemView) {
      super(itemView);
      textViewNumber = itemView.findViewById(R.id.textViewNumber);
      textViewEnglish = itemView.findViewById(R.id.textViewEnglish);
      textViewChinese = itemView.findViewById(R.id.textViewChinese);
    }
  }
}
