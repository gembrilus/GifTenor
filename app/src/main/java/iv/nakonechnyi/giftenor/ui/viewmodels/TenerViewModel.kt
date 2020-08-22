package iv.nakonechnyi.giftenor.ui.viewmodels

import android.util.Log
import android.widget.CompoundButton
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.paging.toLiveData
import iv.nakonechnyi.giftenor.Repository
import iv.nakonechnyi.giftenor.Tenor
import iv.nakonechnyi.giftenor.data.GifInfo
import iv.nakonechnyi.giftenor.paging.PagedListHelper
import iv.nakonechnyi.giftenor.services.JsonParser
import iv.nakonechnyi.giftenor.services.NetworkLoader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext

class TenerViewModel(private val repo: Repository)
    : ViewModel(), CoroutineScope {

    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    val favoriteGifs = repo.getFromDb().toLiveData(
        pageSize = 10,
        initialLoadKey = 10,
        fetchExecutor = Executors.newSingleThreadExecutor()
    )

    private var _netDataSource = MutableLiveData<PagedList<GifInfo>>()
    val netDataSource: LiveData<PagedList<GifInfo>> get() = _netDataSource

    fun fetchGifs(query: String) {
        JsonParser.reset()
        _netDataSource.value = PagedListHelper.getPagedList(
            repo.getFromNetwork(NetworkLoader(query))
        )
    }

    fun saveFavorites(data: List<GifInfo>) = launch {
            repo.addToFavorite(data)
        }

    fun removeFavorite(gifInfo: GifInfo) = launch { repo.removeFavorite(gifInfo) }

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }

    companion object {

        val FACTORY = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return TenerViewModel(Repository(Tenor.dataBase)) as T
            }

        }

    }

}