package cz.ich.notinoapp.main.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import cz.ich.notinoapp.feature.product.data.local.ProductDao
import cz.ich.notinoapp.feature.product.data.local.ProductEntity

@Database(entities = [ProductEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "notino-db"
    }

    abstract fun productDao(): ProductDao
}