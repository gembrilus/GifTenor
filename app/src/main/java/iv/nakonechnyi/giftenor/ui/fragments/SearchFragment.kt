package iv.nakonechnyi.giftenor.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import iv.nakonechnyi.giftenor.R
import iv.nakonechnyi.giftenor.ui.adapters.TenerNetAdapter
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*
import java.util.*

class SearchFragment : PagedFragment() {

    private lateinit var mAdapter: TenerNetAdapter

    override fun getLayout() = R.layout.fragment_search

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            view.search_bar.setQuery(
                it.getString(QUERY),
                false
            )
        }
        setButtonVisibility(false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mAdapter = TenerNetAdapter(this::setButtonVisibility)
            .apply {
                callback?.let { setGifClickListener(it) }
            }

        with(list_of_gif) {
            layoutManager = GridLayoutManager(requireContext(), resources.getInteger(R.integer.columns_in_search), RecyclerView.VERTICAL, false)
            adapter = mAdapter
        }

        with(search_bar) {
            isIconified = false
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?) = query?.let {
                    model.fetchGifs(query)
                    hideSoftKeyboard(this@with)
                    setQuery(null, false)
                    true
                } ?: false

                override fun onQueryTextChange(newText: String?) = false
            })
        }

        btn_to_favorite.setOnClickListener {
            val data = mAdapter.checkedItems.values.toList()
            if (data.isNotEmpty()) {
                setButtonVisibility(false)
                model.saveFavorites(data)
                mAdapter.invalidate()
            }
        }

        model.netDataSource.observe(requireActivity(), Observer(mAdapter::submitList))

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(QUERY, search_bar.query.toString())
        super.onSaveInstanceState(outState)
    }

    private fun setButtonVisibility(checked: Boolean) {
        btn_to_favorite.visibility = if (checked) View.VISIBLE else View.GONE
    }

    private fun hideSoftKeyboard(v: View){
        val inputManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(v.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    companion object {
        private const val QUERY = "query"
        fun newInstance() = SearchFragment()
    }
}