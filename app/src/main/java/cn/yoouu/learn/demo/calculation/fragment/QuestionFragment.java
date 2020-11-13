package cn.yoouu.learn.demo.calculation.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;

import cn.yoouu.learn.R;
import cn.yoouu.learn.databinding.FragmentQuestionBinding;
import cn.yoouu.learn.demo.calculation.viewmodel.CalcViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {
    public QuestionFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        CalcViewModel calcViewModel;
        calcViewModel = new ViewModelProvider(requireActivity()).get(CalcViewModel.class);
        calcViewModel.generator();
        FragmentQuestionBinding binding;
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false);
        binding.setData(calcViewModel);
        binding.setLifecycleOwner(requireActivity());

        // 可变的字符序列
        StringBuilder builder = new StringBuilder();
        View.OnClickListener listener = new View.OnClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.calc_button0:
                        builder.append("0");
                        break;
                    case R.id.calc_button1:
                        builder.append("1");
                        break;
                    case R.id.calc_button2:
                        builder.append("2");
                        break;
                    case R.id.calc_button3:
                        builder.append("3");
                        break;
                    case R.id.calc_button4:
                        builder.append("4");
                        break;
                    case R.id.calc_button5:
                        builder.append("5");
                        break;
                    case R.id.calc_button6:
                        builder.append("6");
                        break;
                    case R.id.calc_button7:
                        builder.append("7");
                        break;
                    case R.id.calc_button8:
                        builder.append("8");
                        break;
                    case R.id.calc_button9:
                        builder.append("9");
                        break;
                    case R.id.calc_button_clear:
                        builder.setLength(0);
//                        binding.calcTvAns.setText(getString(R.string.calc_input_indicator));
                        break;
                }

                if (builder.length() == 0) {
                    binding.calcTvAns.setText(getString(R.string.calc_input_indicator));
                } else {
                    binding.calcTvAns.setText(builder.toString());
                }
            }
        };

        binding.calcButton0.setOnClickListener(listener);
        binding.calcButton1.setOnClickListener(listener);
        binding.calcButton2.setOnClickListener(listener);
        binding.calcButton3.setOnClickListener(listener);
        binding.calcButton4.setOnClickListener(listener);
        binding.calcButton5.setOnClickListener(listener);
        binding.calcButton6.setOnClickListener(listener);
        binding.calcButton7.setOnClickListener(listener);
        binding.calcButton8.setOnClickListener(listener);
        binding.calcButton9.setOnClickListener(listener);
        binding.calcButtonClear.setOnClickListener(listener);

        binding.calcButtonSubmit.setOnClickListener((View v) -> {
            if (StringUtils.isEmpty(builder.toString())) {
                ToastUtils.showShort("Please type the answer!");
                return;
            }

            if (Integer.valueOf(builder.toString()).intValue() == calcViewModel.getAnswer().getValue()) {
                calcViewModel.answerCorrect();
                builder.setLength(0);
                binding.calcTvAns.setText(getString(R.string.calc_input_indicator));
//                builder.append(getString(R.string.calc_answer_corrent_message));
            } else {
                NavController controller = Navigation.findNavController(v);
                // 清空已经获得的分数
                calcViewModel.getCurrentScore().setValue(0);
                if (calcViewModel.win_flag) {
                    controller.navigate(R.id.action_questionFragment_to_winFragment);
                    calcViewModel.win_flag = false;
                    calcViewModel.save();
                } else {
                    controller.navigate(R.id.action_questionFragment_to_loseFragment);
                }
            }
        });

        return binding.getRoot();
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_question, container, false);
    }
}