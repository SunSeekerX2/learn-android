package cn.yoouu.learn.demo.gallery.adapter

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cn.yoouu.learn.R
import cn.yoouu.learn.demo.gallery.entity.PhotoItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.demo_gallery_cell.view.*

class GalleryAdapter : ListAdapter<PhotoItem, MyViewHolder>(DIFFCALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val holder = MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.demo_gallery_cell, parent, false))
        holder.itemView.setOnClickListener {
            Bundle().apply {
//        putParcelable("PHOTO", getItem(holder.adapterPosition))
//        holder.itemView.findNavController().navigate(R.id.action_galleryFragment_to_photoFragment, this)

                putParcelableArrayList("PHOTO_LIST", ArrayList(currentList))
                putInt("PHOTO_POSITION", holder.adapterPosition)
                holder.itemView.findNavController().navigate(R.id.action_galleryFragment_to_photoPagerFragment, this)
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val photoItem: PhotoItem = getItem(position)
        with(holder.itemView) {
            demoGalleryShimmerCell.apply {
                setShimmerColor(0x55FFFFFF)
                setShimmerAngle(0)
                startShimmerAnimation()
            }
            demoGalleryPhotoTextViewUser.text = photoItem.photoUser
            demoGalleryPhotoTextViewLikes.text = photoItem.photoLikes.toString()
            demoGalleryPhotoTextViewFavorites.text = photoItem.photoFavorites.toString()
            demoGalleryPhotoImageView.layoutParams.height = photoItem.photoHeight
//            itemView.layoutParams.height = getItem(position).photoHeight
        }
        holder.itemView.demoGalleryShimmerCell.apply {
            setShimmerColor(0x55FFFFFF)
            setShimmerAngle(0)
            startShimmerAnimation()
        }
//        holder.itemView.layoutParams.height = getItem(position).photoHeight
        Glide.with(holder.itemView)
            .load(getItem(position).previewUrl).placeholder(R.drawable.ic_baseline_photo_gray_24)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    return false.also { holder.itemView.demoGalleryShimmerCell?.stopShimmerAnimation() }
                }

            })
            .into(holder.itemView.demoGalleryPhotoImageView)
    }

    object DIFFCALLBACK : DiffUtil.ItemCallback<PhotoItem>() {
        override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
            /**
             * === 表示判断是否是同一个对象
             */
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
            return oldItem.photoId == newItem.photoId
        }
    }
}

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)