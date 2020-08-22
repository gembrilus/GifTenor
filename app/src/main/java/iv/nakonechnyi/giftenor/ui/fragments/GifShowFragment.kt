package iv.nakonechnyi.giftenor.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import iv.nakonechnyi.giftenor.R
import iv.nakonechnyi.giftenor.data.GifInfo
import iv.nakonechnyi.giftenor.services.ImageLoader
import kotlinx.android.synthetic.main.fragment_gif_show.*

class GifShowFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_gif_show, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.getSerializable(KEY)?.let {
            ImageLoader.loadFullSizeGif(this, it as GifInfo, gif_container)
        }
    }

    companion object {
        private const val KEY = "gif_object"
        fun newInstance(gifInfo: GifInfo) = GifShowFragment().apply {
            arguments = Bundle().apply {
                putSerializable(KEY, gifInfo)
            }
        }
    }
}