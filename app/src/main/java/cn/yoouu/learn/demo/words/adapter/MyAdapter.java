package cn.yoouu.learn.demo.words.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.yoouu.learn.R;
import cn.yoouu.learn.demo.words.entity.Word;
import cn.yoouu.learn.demo.words.viewmodel.WordViewModel;

/**
 * 将 RecyclerView.Adapter 改为 ListAdapter 可以避免一条数据更改全部视图刷新
 */
public class MyAdapter extends ListAdapter<Word, MyAdapter.MyViewHolder> {
//  List<Word> allWords = new ArrayList<>();
  boolean isUseCardView = false;
  WordViewModel wordViewModel;

  public MyAdapter(boolean isUseCardView, WordViewModel wordViewModel) {
    /**
     * 列表数据的差异化处理是在后台异步进行的
     */
    super(new DiffUtil.ItemCallback<Word>() {
      @Override
      public boolean areItemsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
        return oldItem.getId() == newItem.getId();
      }

      @Override
      public boolean areContentsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
        return (oldItem.getWord().equals(newItem.getWord()) && oldItem.getChineseMeaning().equals(newItem.getChineseMeaning()) && oldItem.isChineseInvisible() == newItem.isChineseInvisible());
      }
    });
    this.isUseCardView = isUseCardView;
    this.wordViewModel = wordViewModel;
  }

//  public void setAllWords(List<Word> allWords) {
//    this.allWords = allWords;
//  }

  /**
   * 绑定监听器应该放在该方法，不能放在 onBindViewHolder
   * 因为 onBindViewHolder 会被经常调用，对性能有影响
   *
   * @param parent
   * @param viewType
   * @return
   */
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
    MyViewHolder holder = new MyViewHolder(itemView);

    holder.itemView.setOnClickListener((View v) -> {
      Uri uri = Uri.parse("https://m.youdao.com/dict?le=eng&q=" + holder.textViewEnglish.getText());
      Intent intent = new Intent(Intent.ACTION_VIEW);
      intent.setData(uri);
      holder.itemView.getContext().startActivity(intent);
    });

    holder.aSwitchChineseInvisible.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Word word = (Word) holder.itemView.getTag(R.id.word_for_view_holder);
        if (isChecked) {
          holder.textViewChinese.setVisibility(View.GONE);
          word.setChineseInvisible(true);
        } else {
          holder.textViewChinese.setVisibility(View.VISIBLE);
          word.setChineseInvisible(false);
        }
        wordViewModel.updateWords(word);
      }
    });
    return holder;
  }

  @Override
  public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    Word word = getItem(position);
    holder.itemView.setTag(R.id.word_for_view_holder, word);
    holder.textViewNumber.setText(String.valueOf(position + 1));
    holder.textViewEnglish.setText(word.getWord());
    holder.textViewChinese.setText(word.getChineseMeaning());
    if (word.isChineseInvisible()) {
      holder.textViewChinese.setVisibility(View.GONE);
      holder.aSwitchChineseInvisible.setChecked(true);
    } else {
      holder.textViewChinese.setVisibility(View.VISIBLE);
      holder.aSwitchChineseInvisible.setChecked(false);
    }
  }

  /**
   * 重写下面这个方法是为了解决 ListAdapter 序号不刷新的问题，因为现在的列表不是全部更新
   * 而是 diff 更新，某些外部的列表 item 进入也会导致序号不正确
   */
  @Override
  public void onViewAttachedToWindow(@NonNull MyViewHolder holder) {
    super.onViewAttachedToWindow(holder);
    holder.textViewNumber.setText(String.valueOf(holder.getAdapterPosition() + 1));
  }

  public static class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView textViewNumber;
    public TextView textViewEnglish;
    public TextView textViewChinese;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    public Switch aSwitchChineseInvisible;

    public MyViewHolder(@NonNull View itemView) {
      super(itemView);
      textViewNumber = itemView.findViewById(R.id.textViewNumber);
      textViewEnglish = itemView.findViewById(R.id.textViewEnglish);
      textViewChinese = itemView.findViewById(R.id.textViewChinese);
      aSwitchChineseInvisible = itemView.findViewById(R.id.switchChineseInvisible);
    }
  }
}
