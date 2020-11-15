package cn.yoouu.learn.demo.gallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import cn.yoouu.learn.R

class DemoGalleryActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_demo_gallery)
    NavigationUI.setupActionBarWithNavController(this, findNavController(R.id.demoGalleryFragment))
  }

  override fun onSupportNavigateUp(): Boolean {
    return super.onSupportNavigateUp() || findNavController(R.id.demoGalleryFragment).navigateUp()
  }
}