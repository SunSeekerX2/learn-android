package cn.yoouu.learn.demo.gallery.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class Pixabay(val totalHits: Int,
                   val hits: Array<PhotoItem>,
                   val total: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Pixabay

        if (totalHits != other.totalHits) return false
        if (!hits.contentEquals(other.hits)) return false
        if (total != other.total) return false

        return true
    }

    override fun hashCode(): Int {
        var result = totalHits
        result = 31 * result + hits.contentHashCode()
        result = 31 * result + total
        return result
    }

    override fun toString(): String {
        return "Pixabay(totalHits=$totalHits, hits=${hits.contentToString()}, total=$total)"
    }
}

@Parcelize
data class PhotoItem(@SerializedName("webformatURL") val previewUrl: String,
                     @SerializedName("id") val photoId: Int,
                     @SerializedName("webformatHeight") val photoHeight: Int,
                     @SerializedName("largeImageURL") val fullUrl: String,
                     @SerializedName("user") val photoUser: String,
                     @SerializedName("likes") val photoLikes: Int,
                     @SerializedName("favorites") val photoFavorites: Int) : Parcelable {
    override fun toString(): String {
        return "PhotoItem(previewUrl='$previewUrl', photoId=$photoId, fullUrl='$fullUrl')"
    }
}