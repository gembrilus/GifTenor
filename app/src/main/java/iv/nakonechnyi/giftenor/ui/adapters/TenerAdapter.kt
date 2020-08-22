package iv.nakonechnyi.giftenor.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import iv.nakonechnyi.giftenor.data.GifInfo

abstract class TenerAdapter
    : PagedListAdapter<GifInfo,
        TenerAdapter.TenerViewHolder>(DIFF_UTIL) {

    abstract fun getLayout(): Int
    abstract fun bindTo(v: View, gifInfo: GifInfo?)

    protected var onGifClickListener: OnGifClickListener? = null

    fun setGifClickListener(listener: OnGifClickListener) {
        onGifClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LayoutInflater.from(parent.context)
            .inflate(getLayout(), parent, false)
            .run(::TenerViewHolder)

    override fun onBindViewHolder(holder: TenerViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class TenerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(gifInfo: GifInfo?) = bindTo(itemView, gifInfo)
    }

    interface OnGifClickListener {
        fun onGifClick(gifInfo: GifInfo)
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<GifInfo>() {
            override fun areItemsTheSame(oldItem: GifInfo, newItem: GifInfo) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: GifInfo, newItem: GifInfo) = oldItem == newItem
        }
    }
}