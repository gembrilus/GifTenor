package iv.nakonechnyi.giftenor.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "gif_info")
data class GifInfo(
    @PrimaryKey val id: String,
    val created: Double,
    val title: String,
    val preview: String,
    val url: String
) : Serializable {

    @Transient
    @Ignore
    var checked: Boolean = false

}