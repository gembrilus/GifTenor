package iv.nakonechnyi.giftenor.cache

import android.content.Context
import android.os.Environment
import com.bumptech.glide.load.engine.cache.DiskCache
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory.CacheDirectoryGetter
import java.io.File

class ExternalDiskCacheGifFactory(
    context: Context,
    diskCacheName: String = DiskCache.Factory.DEFAULT_DISK_CACHE_DIR,
    diskCacheSize: Int = DiskCache.Factory.DEFAULT_DISK_CACHE_SIZE
) : DiskLruCacheFactory(
    CacheDirectoryGetter {
        if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
            context.externalCacheDir
                ?.let { File(it, diskCacheName) }
        } else {
            context.cacheDir
                ?.let { File(it, diskCacheName) }
        }
    },
    diskCacheSize.toLong()
)