package com.example.pass.bucket

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.pass.*
import com.example.pass.database.DateConverter
import org.jetbrains.annotations.Nullable
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "bucket")
@TypeConverters(DateConverter::class)
class Bucket : BuckDescrip {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "pass_id")
    var passId: Int = 0

    @ColumnInfo(name = "insertion_time")
    var insertionTime: Date? = null

    @Nullable
    @ColumnInfo(name = "activated_time")
    var activatedTime: Date? = null

    @ColumnInfo(name = "status")
    var status: String = ""

    @ColumnInfo(name = "count")
    var count: Int = 0


    override fun getStatusDescription(): String {
        return when (status) {
            STATUS_INVALIDATED -> "尚未開通"
            STATUS_VALIDATED -> "已開通"
            STATUS_EXPIRED -> "已過期"
            else -> ""
        }
    }

    override fun getDetailMessage(pass: Pass): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("Status:${getStatusDescription()}\n")
        val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
        if (insertionTime != null) {
            stringBuilder.append("InsertionDate: ${simpleDateFormat.format(insertionTime!!)}\n")
            if (activatedTime != null) {
                "ActivatedTime:${simpleDateFormat.format(activatedTime!!)}"
                val calendar = Calendar.getInstance(Locale.getDefault())
                calendar.time = insertionTime!!
                if (pass.getDurationType() == HOUR) {
                    calendar.add(Calendar.HOUR, count)
                    stringBuilder.append("ExpireTime:${simpleDateFormat.format(calendar.time)}\n")
                } else if (pass.getDurationType() == DAY) {
                    calendar.add(Calendar.DAY_OF_YEAR, count)
                    stringBuilder.append("ExpireTime:End of ${SimpleDateFormat("yyyy/MM/dd"
                        , Locale.getDefault()).format(calendar.time)}\n")
                }
            }
        }

        return stringBuilder.toString()

    }


}