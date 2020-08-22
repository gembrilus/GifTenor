package iv.nakonechnyi.giftenor.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import iv.nakonechnyi.giftenor.data.GifInfo

@Database(entities = [GifInfo::class], version = 1)
abstract class TenorDatabase : RoomDatabase() {

    abstract fun tenorDao(): TenorDao

    companion object {

        @Volatile
        private var INSTANCE: TenorDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE
            ?: synchronized(LOCK) {
                INSTANCE
                    ?: Room.databaseBuilder(
                        context.applicationContext,
                        TenorDatabase::class.java,
                        "tenor_database"
                    )
                        .build()
                        .also { INSTANCE = it }
            }

    }

}