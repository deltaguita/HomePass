package com.example.pass.database

import android.content.Context
import androidx.room.Room
import com.example.pass.PassDao
import com.example.pass.bucket.BucketDao

class DatabaseProvider {

    companion object {
        private var db:AppDatabase? = null

        fun initDB(context: Context){
            if (db == null) {
                db = Room.databaseBuilder(context,AppDatabase::class.java,"prod").build()
            }
        }

        fun getPassDao(context: Context): PassDao {
            initDB(context)
            return db!!.getNormalPassDao()
        }

        fun getBucketDao(context: Context): BucketDao {
            initDB(context)
            return db!!.getBucketDao()
        }
    }




}