package com.fady.wordwiz.data.datasource.local.worddictionarydb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@TypeConverters(TypeConverter::class)
@Database(entities = [WordDictionary::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getWordDictionaryDao(): WordDictionaryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, AppDataBase::class.java, DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }

        private const val DATABASE_NAME = "mediminder_database"
    }

}
