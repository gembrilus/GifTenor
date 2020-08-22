package iv.nakonechnyi.giftenor.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import iv.nakonechnyi.giftenor.R
import iv.nakonechnyi.giftenor.data.GifInfo
import iv.nakonechnyi.giftenor.ui.adapters.TenerAdapter
import iv.nakonechnyi.giftenor.ui.fragments.GifShowFragment
import iv.nakonechnyi.giftenor.ui.fragments.MainFragment

class MainActivity : AppCompatActivity(), TenerAdapter.OnGifClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.findFragmentById(R.id.fragment_container)
            ?: supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, MainFragment.newInstance())
                .commit()

    }

    override fun onGifClick(gifInfo: GifInfo) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, GifShowFragment.newInstance(gifInfo))
            .addToBackStack(null)
            .commit()
    }
}