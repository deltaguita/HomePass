package com.example.pass.wallet

import com.example.pass.bucket.Bucket

class TestWallet :Wallet{
    override fun pay(bucket: Bucket, callback: Wallet.Callback) {

        callback.success("ok")

    }


}