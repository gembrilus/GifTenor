package iv.nakonechnyi.giftenor.services

import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.signature.ObjectKey
import iv.nakonechnyi.giftenor.data.GifInfo

object ImageLoader {

    fun loadFullSizeGif(context: Fragment, gifInfo: GifInfo, to: ImageView) {
        Glide.with(context)
            .load(gifInfo.url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .signature(ObjectKey(gifInfo.created))
            .into(to)
    }

    fun loadGifPreview(context: View, gifInfo: GifInfo, to: ImageView) {
        Glide.with(context)
            .load(gifInfo.preview)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .signature(ObjectKey(gifInfo.created))
            .into(to)
    }

}