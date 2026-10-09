package com.mannanhosen.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Contact::class], version = 1)
@TypeConverters(Converters::class)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao

    companion object {
        @Volatile
        private var INSTANCE: ContactDatabase? = null
        fun getDatabase(context: Context): ContactDatabase {
               if (INSTANCE == null) {
                   synchronized(this) {
                       INSTANCE = Room.databaseBuilder(
                           context.applicationContext,
                           ContactDatabase::class.java,
                           "contactDb"
                       ).build()
                   }
               }

                return INSTANCE!!
            }
        }
    }

