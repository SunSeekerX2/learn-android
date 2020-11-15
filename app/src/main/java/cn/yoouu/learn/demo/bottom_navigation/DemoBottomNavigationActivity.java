package cn.yoouu.learn.demo.bottom_navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import cn.yoouu.learn.R;

public class DemoBottomNavigationActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_demo_bottom_navigation);
    BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
    NavController navController = Navigation.findNavController(this, R.id.bottomNavigationViewFragment);
    AppBarConfiguration configuration = new AppBarConfiguration.Builder(bottomNavigationView.getMenu()).build();
    NavigationUI.setupActionBarWithNavController(this, navController, configuration);
    NavigationUI.setupWithNavController(bottomNavigationView, navController);
  }
}