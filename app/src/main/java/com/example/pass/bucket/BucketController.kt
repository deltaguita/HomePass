package com.example.pass.bucket

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.airbnb.epoxy.EpoxyController
import com.example.pass.NormalPass
import com.example.pass.STATUS_VALIDATED
import com.example.pass.database.DatabaseProvider
import com.example.pass.wallet.PassProvider
import com.example.pass.wallet.Wallet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class BucketController : EpoxyController() {

    interface OnDataChangeCallback{
        fun onDataChanged()
    }

    var onDataChangeCallback:OnDataChangeCallback? = null

    var map: MutableMap<NormalPass, List<Bucket>>? = null
        set(value) {
            field = value
            requestModelBuild()
        }


    init {
        setFilterDuplicates(true)
    }



    override fun buildModels() {

        if (map == null) {
            return
        }

        if (map!!.isEmpty()) {
            //show empty view
        }


        map!!.forEach { (pass, buckets) ->

            titleView {
                id(pass.getName())
                title("Pass By ${pass.getName()}")
            }
            buckets.forEach { bucket ->
                bucketItemView {
                    id("bucket" + bucket.id)
                    count("Pass ${bucket.count} ${pass.getName()}")
                    description(bucket.getStatusDescription())
                    checkoutBtnClick(object : View.OnClickListener {
                        override fun onClick(v: View) {
                            PassProvider.getWallet().pay(bucket, object : Wallet.Callback {
                                override fun success(result: String) {
                                    GlobalScope.launch {
                                        activeBucket(pass, bucket, v.context.applicationContext)

                                    }
                                }

                                override fun fail(result: String) {
                                    //fail message if need
                                }

                            })
                        }

                    })

                    itemClickListener(object : View.OnClickListener {
                        override fun onClick(v: View) {
                            AlertDialog.Builder(v.context)
                                .setNeutralButton("ok") { dialog, _ -> dialog.dismiss() }
                                .setTitle("詳細資訊")
                                .setMessage(bucket.getDetailMessage(pass)).show()
                        }

                    })
                }
            }

        }


    }


    suspend fun activeBucket(pass: NormalPass, bucket: Bucket, context: Context) =
        withContext(Dispatchers.IO) {
            bucket.status = STATUS_VALIDATED
            bucket.activatedTime = Date()
            DatabaseProvider.getBucketDao(context).updateBucket(bucket)
            showMessage("開通 ${bucket.count}${pass.getName()} 成功", context)
            onDataChangeCallback?.onDataChanged()
        }

    suspend fun showMessage(message: String, context: Context) = withContext(Dispatchers.Main) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()

    }

}