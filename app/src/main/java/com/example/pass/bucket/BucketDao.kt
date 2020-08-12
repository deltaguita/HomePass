package com.example.pass.bucket

import androidx.room.*


@Dao
interface BucketDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBucket(passes: Bucket): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateBucket(passes: Bucket)

    @Delete
    fun deletedBucket(passes: List<Bucket>): Int

    @Query("SELECT * FROM bucket WHERE pass_id = :id")
    fun getBucketByPassId(id:Int):List<Bucket>

}