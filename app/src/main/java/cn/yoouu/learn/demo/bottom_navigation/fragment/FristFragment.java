package cn.yoouu.learn.demo.bottom_navigation.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import cn.yoouu.learn.R;

public class FristFragment extends Fragment {

  private FristViewModel mViewModel;
  private ImageView imageView;

  public static FristFragment newInstance() {
    return new FristFragment();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.frist_fragment, container, false);
    imageView = view.findViewById(R.id.imageView);
    return view;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    // 改变这里可以让 viewmodel 生命周期变为 activity
    mViewModel = new ViewModelProvider(this).get(FristViewModel.class);
    imageView.setRotation(mViewModel.rotationPosition);
    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0, 0);
    objectAnimator.setDuration(500);
    imageView.setOnClickListener((View v) -> {
      if(!objectAnimator.isRunning()){
        objectAnimator.setFloatValues(imageView.getRotation(), imageView.getRotation() + 100);
        mViewModel.rotationPosition += 100;
        objectAnimator.start();
      }
    });
  }

}