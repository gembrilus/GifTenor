package iv.nakonechnyi.giftenor

import android.app.Application
import iv.nakonechnyi.giftenor.database.TenorDatabase

class Tenor: Application() {

    companion object{
        lateinit var dataBase: TenorDatabase
        private set
    }

    override fun onCreate() {
        super.onCreate()
        dataBase = getDatabase()
    }

    private fun getDatabase() = TenorDatabase(this)
}