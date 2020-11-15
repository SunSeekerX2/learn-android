package cn.yoouu.learn.demo.gallery.fragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.yoouu.learn.demo.gallery.entity.PhotoItem
import cn.yoouu.learn.demo.gallery.entity.Pixabay
import cn.yoouu.learn.demo.gallery.util.VolleySingleton
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson

class GalleryViewModel(application: Application) : AndroidViewModel(application) {
  // TODO: Implement the ViewModel
  private val _photoListLive = MutableLiveData<List<PhotoItem>>()
  val photoListLive: LiveData<List<PhotoItem>>
    get() = _photoListLive

  fun fetchData() {
    val stringRequest = StringRequest(
      Request.Method.GET,
      getUrl(),
      {
        _photoListLive.value = Gson().fromJson(it, Pixabay::class.java).hits.toList()
      },
      {
        Log.d("getPhotosError>>>", it.toString())
      }
    )

    VolleySingleton.getInstance(getApplication()).requestQueue.add(stringRequest)
  }

  private fun getUrl(): String {
    return "https://pixabay.com/api/?key=19121154-d6490a5b905d0b6f31f5fa298&q=${keywords.random()}&per_page=60"
  }

  private val keywords = arrayOf("cat", "dog", "car", "beauty", "phone", "computer", "flower", "animal")
}