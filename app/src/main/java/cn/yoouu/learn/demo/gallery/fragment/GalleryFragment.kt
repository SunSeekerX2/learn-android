package cn.yoouu.learn.demo.gallery.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import cn.yoouu.learn.R
import cn.yoouu.learn.demo.gallery.adapter.GalleryAdapter
import kotlinx.android.synthetic.main.gallery_fragment.*

class GalleryFragment : Fragment() {
  private lateinit var galleryViewModel: GalleryViewModel

  companion object {
    fun newInstance() = GalleryFragment()
  }

//  private lateinit var viewModel: GalleryViewModel

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {

    return inflater.inflate(R.layout.gallery_fragment, container, false)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.demoGallerySwiperIndicator -> {
        demoGallerySwiperLayout.isRefreshing = true
        /**
         * 让转动久一点
         */
        Handler().postDelayed(Runnable { galleryViewModel.fetchData() }, 1000)
//        galleryViewModel.fetchData()
      }
    }
    return super.onOptionsItemSelected(item)
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    super.onCreateOptionsMenu(menu, inflater)
    inflater.inflate(R.menu.demo_gallery_menu, menu)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    setHasOptionsMenu(true)
//    viewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)
    val galleryAdapter = GalleryAdapter()
    demoGalleryRecycleView.apply {
      adapter = galleryAdapter
      layoutManager = GridLayoutManager(requireContext(), 2)
    }
    galleryViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(requireActivity().application)).get(GalleryViewModel::class.java)
    galleryViewModel.photoListLive.observe(viewLifecycleOwner, Observer {
      galleryAdapter.submitList(it)
      demoGallerySwiperLayout.isRefreshing = false
    })
    galleryViewModel.photoListLive.value ?: galleryViewModel.fetchData()

    demoGallerySwiperLayout.setOnRefreshListener {
      galleryViewModel.fetchData()
    }
  }

}