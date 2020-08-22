package iv.nakonechnyi.giftenor.services

import android.util.Log
import androidx.annotation.VisibleForTesting
import iv.nakonechnyi.giftenor.data.GifInfo
import org.json.JSONException
import org.json.JSONObject

object JsonParser {
    var next: String = ""
        private set

    fun parse(response: String?): List<GifInfo> {
        val list = mutableListOf<GifInfo>()

        if (response == null) return list
        try {
            val jsonObject = JSONObject(response)
            next = jsonObject.getString(NEXT)
            val results = jsonObject.getJSONArray(RESULTS)
            var i = 0
            while (i < results.length()) {
                val gifObject = results.getJSONObject(i++)
                val id = gifObject.getString(ID)
                val title = gifObject.getString(TITLE)
                val created = gifObject.getDouble(TIME_STAMP)
                val media = gifObject.getJSONArray(MEDIA).getJSONObject(0)
                for (key in media.keys()) {
                    if (key == GIF) {
                        val value = media.get(key)
                        if (value is JSONObject) {
                            val preview = value.getString(PREVIEW)
                            val url = value.getString(URL)

                            list.add(
                                GifInfo(id, created, title, preview, url)
                            )
                            break
                        }
                    }
                }
            }
        } catch (e: JSONException) {
            Log.e(TAG, e.message.toString())
        }
        return list
    }

    fun reset(){
        next = ""
    }

    private const val NEXT = "next"
    private const val RESULTS = "results"
    private const val ID = "id"
    private const val MEDIA = "media"
    private const val TITLE = "title"
    private const val TIME_STAMP = "created"
    private const val GIF = "gif"
    private const val PREVIEW = "preview"
    private const val URL = "url"
    private const val TAG = "TENOR_PARSER"
}