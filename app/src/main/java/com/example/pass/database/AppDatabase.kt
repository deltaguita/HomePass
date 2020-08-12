package com.example.pass.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pass.NormalPass
import com.example.pass.PassDao
import com.example.pass.bucket.Bucket
import com.example.pass.bucket.BucketDao

@Database(entities = [Bucket::class, NormalPass::class],version = 1,exportSchema = true)
abstract class AppDatabase : RoomDatabase(){

    abstract fun getBucketDao():BucketDao

    abstract fun getNormalPassDao(): PassDao

}