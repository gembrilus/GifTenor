package iv.nakonechnyi.giftenor.ui.adapters

import android.util.Log
import android.view.View
import android.widget.CompoundButton
import iv.nakonechnyi.giftenor.R
import iv.nakonechnyi.giftenor.data.GifInfo
import iv.nakonechnyi.giftenor.services.ImageLoader
import kotlinx.android.synthetic.main.view_holder_search.view.*

class TenerNetAdapter(private val afterAction: (Boolean) -> Unit) : TenerAdapter() {

    private val _checkedItems = mutableMapOf<CompoundButton, GifInfo>()
    val checkedItems: Map<CompoundButton, GifInfo> get() = _checkedItems

    override fun getLayout() = R.layout.view_holder_search
    override fun bindTo(v: View, gifInfo: GifInfo?) {
        gifInfo?.let { item ->

            ImageLoader.loadGifPreview(v, item, v.searched_gif)

            with(v.title) {
                text =
                    if (item.title.isNotBlank()) item.title else v.context.getString(R.string.no_title)
            }

            with(v.checkbox) {
                isChecked = item.checked
                setOnClickListener {
                    item.checked = !item.checked
                    if (item.checked) {
                        _checkedItems[it as CompoundButton] = item
                    } else {
                        _checkedItems.remove(it)
                    }
                    afterAction(_checkedItems.isNotEmpty())
                }
            }

            with(v.searched_gif) {
                setOnClickListener {
                    onGifClickListener?.onGifClick(item)
                }
            }
        }
    }

    fun invalidate() {
        for ((k, v) in _checkedItems) {
            k.isChecked = false
            v.checked = false
        }
        _checkedItems.clear()

    }
}