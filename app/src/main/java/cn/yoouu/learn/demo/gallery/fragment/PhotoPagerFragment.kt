package cn.yoouu.learn.demo.gallery.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import cn.yoouu.learn.R
import cn.yoouu.learn.demo.gallery.adapter.PagerPhotoListAdapter
import cn.yoouu.learn.demo.gallery.entity.PhotoItem
import kotlinx.android.synthetic.main.photo_pager_fragment.*

const val REQUEST_WRITE_EXTERNAL_STORAGE = 1

class PhotoPagerFragment : Fragment() {

    companion object {
        fun newInstance() = PhotoPagerFragment()
    }

    private lateinit var viewModel: PhotoPagerViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.photo_pager_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PhotoPagerViewModel::class.java)
        val photoList: ArrayList<PhotoItem>? = arguments?.getParcelableArrayList<PhotoItem>("PHOTO_LIST")
        PagerPhotoListAdapter().apply {
            demoGalleryViewPager2.adapter = this
            submitList(photoList)
        }
        demoGalleryViewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                demoGalleryPhotoTag.text = "${position + 1} / ${photoList?.size}"
            }
        })
        demoGalleryViewPager2.setCurrentItem(arguments?.getInt("PHOTO_POSITION") ?: 0, false)

        demoGalleryDownloadImageview.setOnClickListener {
            if (Build.VERSION.SDK_INT < 29 && ContextCompat.checkSelfPermission(requireContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_WRITE_EXTERNAL_STORAGE)
            }
        }
    }

}