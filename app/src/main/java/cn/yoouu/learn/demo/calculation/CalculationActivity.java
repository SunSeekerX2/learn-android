package cn.yoouu.learn.demo.calculation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.DialogInterface;
import android.os.Bundle;

import com.blankj.utilcode.util.ToastUtils;

import cn.yoouu.learn.R;

public class CalculationActivity extends AppCompatActivity {
    NavController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);

        controller = Navigation.findNavController(this, R.id.calc_fragment);
        NavigationUI.setupActionBarWithNavController(this, controller);
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (controller.getCurrentDestination().getId() == R.id.questionFragment) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getString(R.string.calc_dialog_quit_title));
            builder.setPositiveButton(getString(R.string.calc_dialog_positive_msg), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    controller.navigateUp();
                }
            });
            builder.setNegativeButton(getString(R.string.calc_dialog_negative_msg), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ToastUtils.showShort("Cancel");
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            controller.navigate(R.id.titleFragment);
        }
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (controller.getCurrentDestination().getId() == R.id.titleFragment) {
            finish();
        } else {
            onSupportNavigateUp();
        }
    }
}