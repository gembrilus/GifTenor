package iv.nakonechnyi.giftenor.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import iv.nakonechnyi.giftenor.data.GifInfo
import iv.nakonechnyi.giftenor.services.JsonParser
import iv.nakonechnyi.giftenor.services.NetworkLoader
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class NetDataSource(private val loader: NetworkLoader) : PageKeyedDataSource<String, GifInfo>() {

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, GifInfo>
    ) {
        loader.loadImages(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e(TAG, "On the request to ${call.request().url} occurs an error\n ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val list = JsonParser.parse(response.body?.string())
                    callback.onResult(list, null, JsonParser.next)
                } else {
                    Log.e(TAG, "An error occurs. HTTP status - ${response.code}")
                }
            }
        })
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, GifInfo>) {
        loader.loadImages(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e(TAG, "On the request to ${call.request().url} occurs an error\n ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val list = JsonParser.parse(response.body?.string())
                    callback.onResult(list, JsonParser.next)
                } else {
                    Log.e(TAG, "An error occurs. HTTP status - ${response.code}")
                }
            }
        })
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, GifInfo>) {}

    companion object {
        private const val TAG = "GifTenor.NetDataSource"
    }
}