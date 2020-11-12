package cn.yoouu.learn.demo.calculation.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.yoouu.learn.R;
import cn.yoouu.learn.databinding.FragmentTitleBinding;
import cn.yoouu.learn.demo.calculation.viewmodel.CalcViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class TitleFragment extends Fragment {
  public TitleFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    CalcViewModel calcViewModel;
    calcViewModel = new ViewModelProvider(requireActivity()).get(CalcViewModel.class);
    FragmentTitleBinding binding;
    binding = DataBindingUtil.inflate(inflater, R.layout.fragment_title,container,false);
    binding.setData(calcViewModel);
    binding.setLifecycleOwner(requireActivity());
    binding.calcBtnEnter.setOnClickListener((View v) -> {
      NavController controller = Navigation.findNavController(v);
      controller.navigate(R.id.action_titleFragment_to_questionFragment);
    });
    return binding.getRoot();
//    return inflater.inflate(R.layout.fragment_title, container, false);
  }
}