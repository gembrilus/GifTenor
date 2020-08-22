package iv.nakonechnyi.giftenor.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import iv.nakonechnyi.giftenor.ui.adapters.TenerAdapter
import iv.nakonechnyi.giftenor.ui.viewmodels.TenerViewModel

abstract class PagedFragment: Fragment() {

    abstract fun getLayout(): Int

    protected lateinit var model: TenerViewModel
    protected var callback: TenerAdapter.OnGifClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as TenerAdapter.OnGifClickListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(getLayout(), container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        model = ViewModelProvider(requireActivity(), TenerViewModel.FACTORY)
            .get(TenerViewModel::class.java)
    }


    override fun onDetach() {
        callback = null
        super.onDetach()
    }

}