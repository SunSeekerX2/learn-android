package cn.yoouu.learn.demo.words.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import cn.yoouu.learn.R;
import cn.yoouu.learn.demo.words.adapter.MyAdapter;
import cn.yoouu.learn.demo.words.entity.Word;
import cn.yoouu.learn.demo.words.viewmodel.WordViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class WordsFragment extends Fragment {
  private WordViewModel wordViewModel;
  private RecyclerView recyclerView;
  private MyAdapter myAdapter1, myAdapter2;
  private FloatingActionButton floatingActionButton;
  private LiveData<List<Word>> filteredWords;
  private List<Word> allWords;
  private final String VIEW_TYPE_SHP = "view_type_shp";
  private final String IS_USING_CARD_VIEW = "is_using_card_view";
  private DividerItemDecoration dividerItemDecoration;

  public WordsFragment() {
    // 默认为false 不显示菜单
    setHasOptionsMenu(true);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_words, container, false);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    wordViewModel = new ViewModelProvider(requireActivity()).get(WordViewModel.class);
    recyclerView = requireActivity().findViewById(R.id.demo_words_recyclerview);
    recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
    myAdapter1 = new MyAdapter(false, wordViewModel);
    myAdapter2 = new MyAdapter(true, wordViewModel);

    // 滑动删除 允许左滑或者右滑
    new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.START | ItemTouchHelper.END) {
      @Override
      public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        // 拖动排序有 bug
//        Word wordFrom = allWords.get(viewHolder.getAdapterPosition());
//        Word wordTo = allWords.get(target.getAdapterPosition());
//        int idTemp = wordFrom.getId();
//        wordFrom.setId(wordTo.getId());
//        wordTo.setId(idTemp);
//        wordViewModel.updateWords(wordFrom, wordTo);
//        myAdapter1.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());myAdapter2.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return false;
      }

      @Override
      public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        Word wordToDelete = allWords.get(viewHolder.getAdapterPosition());
        wordViewModel.deleteWords(wordToDelete);
        Snackbar.make(requireActivity().findViewById(R.id.demo_words_fragment), "删除了一个单词", Snackbar.LENGTH_SHORT).setAction("撤销", new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            wordViewModel.insertWords(wordToDelete);
          }
        }).show();
      }

      //在滑动的时候，画出浅灰色背景和垃圾桶图标，增强删除的视觉效果

      Drawable icon = ContextCompat.getDrawable(requireActivity(),R.drawable.ic_baseline_delete_forever_24);
      Drawable background = new ColorDrawable(Color.LTGRAY);
      @Override
      public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        View itemView = viewHolder.itemView;
        int iconMargin = (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;

        int iconLeft,iconRight,iconTop,iconBottom;
        int backTop,backBottom,backLeft,backRight;
        backTop = itemView.getTop();
        backBottom = itemView.getBottom();
        iconTop = itemView.getTop() + (itemView.getHeight() - icon.getIntrinsicHeight()) /2;
        iconBottom = iconTop + icon.getIntrinsicHeight();
        if (dX > 0) {
          backLeft = itemView.getLeft();
          backRight = itemView.getLeft() + (int)dX;
          background.setBounds(backLeft,backTop,backRight,backBottom);
          iconLeft = itemView.getLeft() + iconMargin ;
          iconRight = iconLeft + icon.getIntrinsicWidth();
          icon.setBounds(iconLeft,iconTop,iconRight,iconBottom);
        } else if (dX < 0){
          backRight = itemView.getRight();
          backLeft = itemView.getRight() + (int)dX;
          background.setBounds(backLeft,backTop,backRight,backBottom);
          iconRight = itemView.getRight()  - iconMargin;
          iconLeft = iconRight - icon.getIntrinsicWidth();
          icon.setBounds(iconLeft,iconTop,iconRight,iconBottom);
        } else {
          background.setBounds(0,0,0,0);
          icon.setBounds(0,0,0,0);
        }
        background.draw(c);
        icon.draw(c);
      }
    }).attachToRecyclerView(recyclerView);


    /**
     * shp 只会读取一次，之后的读取都是读取的内存变量
     */
    SharedPreferences shp = requireActivity().getSharedPreferences(VIEW_TYPE_SHP, Context.MODE_PRIVATE);
    boolean viewType = shp.getBoolean(IS_USING_CARD_VIEW, false);
    dividerItemDecoration = new DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL);
    if (viewType) {
      recyclerView.setAdapter(myAdapter2);
    } else {
      recyclerView.setAdapter(myAdapter1);
      recyclerView.addItemDecoration(dividerItemDecoration);
    }
    /**
     * 重写下面这个方法是为了解决 ListAdapter 序号不刷新的问题，因为现在的列表不是全部更新
     * 而是 diff 更新
     */
    recyclerView.setItemAnimator(new DefaultItemAnimator() {
      @Override
      public void onAnimationFinished(@NonNull RecyclerView.ViewHolder viewHolder) {
        super.onAnimationFinished(viewHolder);
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        if (linearLayoutManager != null) {
          int fristPosition = linearLayoutManager.findFirstVisibleItemPosition();
          int lastPosition = linearLayoutManager.findLastVisibleItemPosition();
          for (int i = fristPosition; i < lastPosition; i++) {
            MyAdapter.MyViewHolder holder = (MyAdapter.MyViewHolder) recyclerView.findViewHolderForAdapterPosition(i);
            if (holder != null) {
              holder.textViewNumber.setText(String.valueOf(i + 1));
            }
          }
        }
      }
    });

    filteredWords = wordViewModel.getAllWords();
    filteredWords.observe(requireActivity(), new Observer<List<Word>>() {
      @Override
      public void onChanged(List<Word> words) {
        int temp = myAdapter1.getItemCount();
        allWords = words;
        if (temp != words.size()) {
//          recyclerView.smoothScrollBy(0, -200);
          /**
           * 提交数据列表，会在后台进行差异化比较
           * 根据对比结果刷新页面
           */
          myAdapter1.submitList(words);
          myAdapter2.submitList(words);
//          myAdapter1.notifyDataSetChanged();
//          myAdapter2.notifyDataSetChanged();
        }
      }
    });

    floatingActionButton = requireActivity().findViewById(R.id.floatingActionButton);
    floatingActionButton.setOnClickListener((View v) -> {
      NavController controller = Navigation.findNavController(v);
      controller.navigate(R.id.action_wordsFragment_to_addFragment);
    });
  }

  @Override
  public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.demo_words_menu, menu);
    SearchView searchView = (SearchView) menu.findItem(R.id.demoWordsSearchBar).getActionView();
    searchView.setMaxWidth(700);

    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        return false;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
        String pattern = newText.trim();
        filteredWords.removeObservers(requireActivity());
        filteredWords = wordViewModel.findWordsWithPattern(pattern);
        filteredWords.observe(requireActivity(), new Observer<List<Word>>() {
          @Override
          public void onChanged(List<Word> words) {
            int temp = myAdapter1.getItemCount();
            allWords = words;
//            myAdapter1.setAllWords(words);
//            myAdapter2.setAllWords(words);
            if (temp != words.size()) {
              myAdapter1.submitList(words);
              myAdapter2.submitList(words);
            }
          }
        });
        /**
         * 如果你认为你需要的功能业务都处理完了，就可以 return true 阻止事件向下传递了。
         */
        return true;
      }
    });
  }

  @SuppressLint("NonConstantResourceId")
  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
      case R.id.demoWordsMenuClearData:
        new AlertDialog.Builder(requireActivity()).setTitle("清空数据").setPositiveButton("确定", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            wordViewModel.deleteAllWords();
          }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {

          }
        }).create().show();
        break;
      case R.id.demoWordsMenuSwtichViewType:
        SharedPreferences shp = requireActivity().getSharedPreferences(VIEW_TYPE_SHP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shp.edit();
        boolean viewType = shp.getBoolean(IS_USING_CARD_VIEW, false);
        if (viewType) {
          recyclerView.setAdapter(myAdapter1);
          recyclerView.addItemDecoration(dividerItemDecoration);
          editor.putBoolean(IS_USING_CARD_VIEW, false);
        } else {
          recyclerView.setAdapter(myAdapter2);
          recyclerView.removeItemDecoration(dividerItemDecoration);
          editor.putBoolean(IS_USING_CARD_VIEW, true);
        }
        editor.apply();
        break;
    }
    return super.onOptionsItemSelected(item);
  }
}