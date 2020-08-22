package iv.nakonechnyi.giftenor.services

import androidx.annotation.VisibleForTesting
import iv.nakonechnyi.giftenor.BuildConfig
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request

class NetworkLoader(private val query: String) {

    fun loadImages(responseCallback: Callback) =
        callRequest().enqueue(responseCallback)

    @VisibleForTesting
    private fun buildUrl() =
        "${BuildConfig.TENER_BASE_URL}?key=${BuildConfig.TENER_KEY}&q=$query&pos=${JsonParser.next}"

    private fun callRequest() = Request.Builder()
        .url(buildUrl())
        .get()
        .addHeader("Content-Type", "application/json")
        .addHeader("Accept", "application/json")
        .addHeader("Content-Type", "application/json; charset=UTF-8")
        .build()
        .run { OkHttpClient().newCall(this) }
}