package com.example.pass

import androidx.room.*
import com.example.pass.NormalPass
import com.example.pass.bucket.Bucket


@Dao
interface PassDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPasses(passes: List<NormalPass>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPass(passes: NormalPass):Long

    @Update()
    fun updatePasses(passes: List<NormalPass>)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updatePass(passes: NormalPass): Int

    @Delete
    fun deletedPasses(passes: List<NormalPass>): Int

    @Query("SELECT * FROM normal_pass")
    fun getAllPass():List<NormalPass>

}