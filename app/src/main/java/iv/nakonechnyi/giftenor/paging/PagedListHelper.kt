package iv.nakonechnyi.giftenor.paging

import androidx.paging.Config
import androidx.paging.DataSource
import androidx.paging.PagedList
import iv.nakonechnyi.giftenor.data.GifInfo
import java.util.concurrent.Executors

class PagedListHelper {
    companion object {
        fun getPagedList(dataSource: DataSource<*, GifInfo>): PagedList<GifInfo> {
            val config = Config(
                pageSize = 10,
                enablePlaceholders = false,
                prefetchDistance = 2,
                initialLoadSizeHint = 10
            )
            return PagedList(
                dataSource = dataSource,
                config = config,
                notifyExecutor = MainThreadExecutor(),
                fetchExecutor = Executors.newSingleThreadExecutor()
            )
        }
    }
}