package com.example.matechatting.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.matechatting.bean.AccountBean

@Database(entities = [AccountBean::class],version = 1, exportSchema = false)
abstract class AppDatabase :RoomDatabase(){
    abstract fun loginDao():LoginDao

    companion object{
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "mate_chatting").build()
        }
    }
}