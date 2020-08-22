package iv.nakonechnyi.giftenor.ui.adapters

import android.view.View
import iv.nakonechnyi.giftenor.R
import iv.nakonechnyi.giftenor.data.GifInfo
import iv.nakonechnyi.giftenor.services.ImageLoader
import kotlinx.android.synthetic.main.view_holder_favorite.view.*

class TenerDbAdapter : TenerAdapter() {
    override fun getLayout() = R.layout.view_holder_favorite
    override fun bindTo(v: View, gifInfo: GifInfo?) {
        gifInfo?.let { item ->
            ImageLoader.loadGifPreview(v, item, v.favorite_gif_container)
            v.setOnClickListener {
                onGifClickListener?.onGifClick(item)
            }
        }
    }
}