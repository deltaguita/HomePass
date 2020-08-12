package com.example.pass.bucket

import com.example.pass.Pass

interface BuckDescrip {

    fun getStatusDescription():String

    fun getDetailMessage(pass: Pass):String

}