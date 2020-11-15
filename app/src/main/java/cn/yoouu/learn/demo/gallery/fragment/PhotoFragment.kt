package cn.yoouu.learn.demo.gallery.fragment

import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.yoouu.learn.R
import cn.yoouu.learn.demo.gallery.entity.PhotoItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.photo_fragment.*

class PhotoFragment : Fragment() {

  companion object {
    fun newInstance() = PhotoFragment()
  }

  private lateinit var viewModel: PhotoViewModel

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.photo_fragment, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = ViewModelProvider(this).get(PhotoViewModel::class.java)
    demoGalleryPhotoShimmer.apply {
      setShimmerColor(0x55FFFFFF)
      setShimmerAngle(0)
      startShimmerAnimation()
    }

    Glide.with(requireActivity())
      .load(arguments?.getParcelable<PhotoItem>("PHOTO")?.fullUrl)
      .placeholder(R.drawable.ic_baseline_photo_gray_24)
      .listener(object : RequestListener<Drawable> {
        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
          return false
        }

        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
          return false.also { demoGalleryPhotoShimmer?.stopShimmerAnimation() }
        }

      })
      .into(demoGalleryPhotoView)
  }

}