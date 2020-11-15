package cn.yoouu.learn.demo.bottom_navigation.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.animation.AnimatorSet;
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

public class SecondFragment extends Fragment {

  private SecondViewModel mViewModel;
  private ImageView imageView;

  public static SecondFragment newInstance() {
    return new SecondFragment();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.second_fragment, container, false);
    imageView = view.findViewById(R.id.imageView);
    return view;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mViewModel = new ViewModelProvider(this).get(SecondViewModel.class);
    imageView.setScaleX(mViewModel.scaleFactor);
    imageView.setScaleY(mViewModel.scaleFactor);
    ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(imageView, "scaleX", 0, 0);
    ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(imageView, "scaleY", 0, 0);
//    AnimatorSet animatorSet = new AnimatorSet();
//    animatorSet.playTogether(objectAnimatorX, objectAnimatorY);
//    animatorSet.setDuration(500);
    objectAnimatorX.setDuration(500);
    objectAnimatorY.setDuration(500);
    imageView.setOnClickListener((View v) -> {
      if(!objectAnimatorX.isRunning()){
        objectAnimatorX.setFloatValues(imageView.getScaleX() + 0.5f);
        objectAnimatorY.setFloatValues(imageView.getScaleY() + 0.5f);
        mViewModel.scaleFactor += 0.5;
        objectAnimatorX.start();
        objectAnimatorY.start();
      }
    });
  }

}