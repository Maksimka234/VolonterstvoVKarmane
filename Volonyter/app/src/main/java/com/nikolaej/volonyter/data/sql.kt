package com.nikolaej.volonyter.data

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow


@Entity(tableName = "maksik")
data class maksik(

    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "vid")
    val vid: String,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "time")
    val time: String,

    @ColumnInfo(name = "mesto")
    val mesto: String
)


@Dao
interface maksikDao{
    @Query("""
        SELECT  * FROM maksik;
    """)
    fun getAll(): Flow<List<maksik>>

    @Query(
        """
            SELECT * FROM maksik
            where vid = :vid
            ORDER BY id ASC;
        """
    )
    fun getFullVid(vid: String): Flow<List<maksik>>

    @Query(
        """
            SELECT * FROM maksik
            where name = :name
        """
    )
    fun getSob(name: String): Flow<List<maksik>>
}

@Database(entities = arrayOf(maksik::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun MaksikDao(): maksikDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "maksik"
                )
                    .createFromAsset("database/maksik.db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        INSTANCE = it
                    }
            }
        }
    }
}

class MaksickViewModel(private val lessonDao: maksikDao): ViewModel() {

    fun getAll(): Flow<List<maksik>> = lessonDao.getAll()

    fun getAllvi(vid: String): Flow<List<maksik>> = lessonDao.getFullVid(vid)

    fun getNav(name: String): Flow<List<maksik>> = lessonDao.getSob(name)

    companion object {
        val factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as LessonApp)
                MaksickViewModel(application.database.MaksikDao())
            }
        }
    }
}


class LessonApp: Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}
