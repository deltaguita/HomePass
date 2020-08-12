package com.example.pass.menu

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.airbnb.epoxy.EpoxyController
import com.example.pass.NormalPass
import com.example.pass.R
import com.example.pass.STATUS_INVALIDATED
import com.example.pass.bucket.Bucket
import com.example.pass.database.DatabaseProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class MenuController : EpoxyController(){

    private var list:List<NormalPass>? = null


    init {
        setFilterDuplicates(true)
    }
    override fun buildModels() {


        if (list == null) {
            return
        }

        if (list!!.isEmpty()) {
            //show empty view
        }
        list!!.forEachIndexed { index, pass ->
            menuPassView {
                id(index)
                name("1${pass.getName()}")
                description(pass.getDescription())
                click(object :View.OnClickListener{
                    override fun onClick(v: View) {
                        // add item do bucket
                        val count = (v.parent as View)
                            .findViewById<EditText>(R.id.menu_item_count_editText)
                            .editableText.toString().toInt()
                        val  bucket = Bucket()
                        bucket.count = count
                        bucket.insertionTime = Date()
                        bucket.status = STATUS_INVALIDATED
                        bucket.passId = pass.getId()
                        GlobalScope.launch {
                            insertBucket(pass,bucket,v.context.applicationContext)
                        }
                    }
                })
            }

        }


    }

    fun setPassList(list: List<NormalPass>) {
        this.list = list
        requestModelBuild()
    }

    suspend fun insertBucket(pass: NormalPass,bucket: Bucket,context:Context) = withContext(Dispatchers.IO) {
        DatabaseProvider.getPassDao(context).insertPass(pass)
        DatabaseProvider.getBucketDao(context).insertBucket(bucket)
        showMessage("成功將${bucket.count}${pass.getName()}加入Bucket",context)
    }

    suspend fun showMessage(message:String,context:Context) = withContext(Dispatchers.Main) {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show()

    }

}