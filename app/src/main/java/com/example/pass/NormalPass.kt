package com.example.pass

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "normal_pass")
data class NormalPass(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") private val id:Int,
    @ColumnInfo(name = "name") private val name: String,
    @ColumnInfo(name = "price")private val price:Int,
    @ColumnInfo(name = "description")private val description: String,
    @ColumnInfo(name = "duration_type")private val durationType: String,
    @ColumnInfo(name = "duration")private val duration: Int) :Pass{

    constructor(pass: Pass) : this(
        pass.getId(),
        pass.getName(),
        pass.getPrice(),
        pass.getDescription(),
        pass.getDurationType(),
        pass.getDuration()) {
    }


    override fun getId(): Int {
        return id
    }

    override fun getName(): String {
        return name
    }

    override fun getPrice(): Int {
        return price
    }

    override fun getDescription(): String {
        return description
    }

    override fun getDurationType(): String {
        return durationType
    }

    override fun getDuration(): Int {
        return duration

    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NormalPass

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }


}