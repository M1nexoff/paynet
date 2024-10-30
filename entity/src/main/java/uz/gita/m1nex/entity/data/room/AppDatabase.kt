package uz.gita.m1nex.entity.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.gita.m1nex.entity.data.model.room.LastTransfersTable
import uz.gita.m1nex.entity.data.model.room.TemplateTable
import uz.gita.m1nex.entity.data.room.dao.LastTransfersDao
import uz.gita.m1nex.entity.data.room.dao.TemplateDao


@Database(entities = [TemplateTable::class, LastTransfersTable::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cardDao(): TemplateDao
    abstract fun lastTransferDao(): LastTransfersDao
    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun init(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "card.db"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE!!
        }

        fun getInstance() = INSTANCE!!
    }
}