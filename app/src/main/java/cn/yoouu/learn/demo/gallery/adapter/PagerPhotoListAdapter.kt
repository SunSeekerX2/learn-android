package cn.yoouu.learn.demo.gallery.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cn.yoouu.learn.R
import cn.yoouu.learn.demo.gallery.entity.PhotoItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.demo_gallery_pager_photo_view.view.*
import kotlinx.android.synthetic.main.photo_fragment.view.*
import kotlinx.android.synthetic.main.photo_fragment.view.demoGalleryPhotoView

class PagerPhotoListAdapter : ListAdapter<PhotoItem, PagerPhotoViewHolder>(DiffCallback) {
  object DiffCallback : DiffUtil.ItemCallback<PhotoItem>() {
    override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
      return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
      return oldItem.photoId == newItem.photoId
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerPhotoViewHolder {
    LayoutInflater.from(parent.context).inflate(R.layout.demo_gallery_pager_photo_view, parent, false).apply {
      return PagerPhotoViewHolder(this)
    }
  }

  override fun onBindViewHolder(holder: PagerPhotoViewHolder, position: Int) {
    Glide.with(holder.itemView)
      .load(getItem(position).previewUrl)
      .placeholder(R.drawable.ic_baseline_photo_gray_24)
      .into(holder.itemView.demoGalleryPagePhoto)
  }
}

class PagerPhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)