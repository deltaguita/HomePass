package com.example.pass

class PassProvider {


    fun getPassList(): List<NormalPass> {

        return arrayListOf(
            NormalPass(1,"小時", 1, "暢遊 1 小時", HOUR, 1)
            , NormalPass(2,"天", 1, "暢遊 1 天", DAY, 1)
        )
    }
}