package iv.nakonechnyi.giftenor.ui.fragments

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import iv.nakonechnyi.giftenor.R
import iv.nakonechnyi.giftenor.RemoveBySwipeAdapter
import iv.nakonechnyi.giftenor.RemoveBySwipeCallback
import iv.nakonechnyi.giftenor.ui.adapters.TenerAdapter
import iv.nakonechnyi.giftenor.ui.adapters.TenerDbAdapter
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : PagedFragment(), RemoveBySwipeAdapter {

    private lateinit var mAdapter: TenerAdapter

    override fun getLayout() = R.layout.fragment_favorite

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val swiper = RemoveBySwipeCallback(this)
        val itemTouchHelper = ItemTouchHelper(swiper)

        mAdapter = TenerDbAdapter().apply {
            callback?.let { setGifClickListener(it) }
        }

        with(favorite_list) {
            layoutManager = GridLayoutManager(requireContext(), resources.getInteger(R.integer.columns_in_favorite), RecyclerView.VERTICAL, false)
            adapter = mAdapter
            itemTouchHelper.attachToRecyclerView(this)
        }

        model.favoriteGifs.observe(requireActivity(), Observer(mAdapter::submitList))

    }

    override fun removeOnSwipe(pos: Int) {
        val gifInfo = mAdapter.currentList?.get(pos)
        gifInfo?.let { model.removeFavorite(it) }
        mAdapter.notifyItemRemoved(pos)
    }

    companion object {
        fun newInstance() = FavoriteFragment()
    }
}