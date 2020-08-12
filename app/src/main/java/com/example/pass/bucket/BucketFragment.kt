package com.example.pass.bucket

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pass.NormalPass
import com.example.pass.R
import com.example.pass.database.DatabaseProvider
import kotlinx.android.synthetic.main.fragment_bucket.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BucketFragment : Fragment() {

    private val bucketController = BucketController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bucketController.onDataChangeCallback = object :BucketController.OnDataChangeCallback{
            override fun onDataChanged() {
                GlobalScope.launch {
                    fetchBucket(view!!.context.applicationContext)
                }
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bucket, container, false)
    }


    override fun onResume() {
        super.onResume()

        //一般來說不需要每次OnResume 都重讀資料，大專案我會搭配 RxRelay 使用，但要這要引入一大包Rx，實在沒必要在小專案上使用
        GlobalScope.launch {
            fetchBucket(view!!.context.applicationContext)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bucket_recyclerView.layoutManager = LinearLayoutManager(view.context)
        bucket_recyclerView.adapter = bucketController.adapter

    }

    companion object {
        @JvmStatic
        fun newInstance() = BucketFragment()
    }


    suspend fun fetchBucket(context: Context) = withContext(Dispatchers.IO){
        val passDao = DatabaseProvider.getPassDao(context)
        val bucketDao =  DatabaseProvider.getBucketDao(context)
        val passes = passDao.getAllPass()
        val map = mutableMapOf<NormalPass,List<Bucket>>()
        passes.forEach {
            bucketDao.getBucketByPassId(it.getId()).apply {
                map[it] = this
            }
        }
        setData(map)
    }
    suspend fun setData(map: MutableMap<NormalPass, List<Bucket>>) = withContext(Dispatchers.Main) {
        bucketController.map = map
    }

}