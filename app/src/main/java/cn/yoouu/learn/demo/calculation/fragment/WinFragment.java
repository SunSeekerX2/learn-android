package cn.yoouu.learn.demo.calculation.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.yoouu.learn.R;
import cn.yoouu.learn.databinding.FragmentLoseBinding;
import cn.yoouu.learn.databinding.FragmentWinBinding;
import cn.yoouu.learn.demo.calculation.viewmodel.CalcViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class WinFragment extends Fragment {

    public WinFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        CalcViewModel calcViewModel;
        calcViewModel = new ViewModelProvider(getActivity()).get(CalcViewModel.class);
        FragmentWinBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_win, container, false);
        binding.setData(calcViewModel);
        binding.setLifecycleOwner(requireActivity());
        binding.calcBtnWinBack.setOnClickListener((View v) -> {
            NavController controller = Navigation.findNavController(v);
            controller.navigate(R.id.action_winFragment_to_titleFragment);
        });
        // Inflate the layout for this fragment
        return binding.getRoot();
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_win, container, false);
    }
}