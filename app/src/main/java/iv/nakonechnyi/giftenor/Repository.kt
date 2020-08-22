package iv.nakonechnyi.giftenor

import iv.nakonechnyi.giftenor.data.GifInfo
import iv.nakonechnyi.giftenor.database.TenorDatabase
import iv.nakonechnyi.giftenor.paging.NetDataSource
import iv.nakonechnyi.giftenor.services.NetworkLoader

class Repository(private val db: TenorDatabase) {

    fun getFromDb() = db.tenorDao().getAll()

    suspend fun addToFavorite(list: List<GifInfo>) = db.tenorDao().insertAll(list)

    suspend fun removeFavorite(gifInfo: GifInfo) = db.tenorDao().remove(gifInfo)

    fun getFromNetwork(loader: NetworkLoader) = NetDataSource(loader)

}