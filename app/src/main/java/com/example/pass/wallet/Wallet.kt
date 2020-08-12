package com.example.pass.wallet

import com.example.pass.bucket.Bucket

interface Wallet {

    fun pay(bucket: Bucket, callback: Callback)

    interface Callback {
        fun success(result: String)

        fun fail(result: String)
    }
}