package iv.nakonechnyi.giftenor.database

import androidx.paging.DataSource
import androidx.room.*
import iv.nakonechnyi.giftenor.data.GifInfo

@Dao
interface TenorDao {

    @Query("SELECT * FROM gif_info")
    fun getAll(): DataSource.Factory<Int, GifInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(gif: List<GifInfo>)

    @Delete
    suspend fun remove(gif: GifInfo)

}