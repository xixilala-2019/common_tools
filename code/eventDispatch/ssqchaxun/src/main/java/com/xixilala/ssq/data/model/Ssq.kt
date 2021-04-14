package com.xixilala.ssq.data.model

import com.squareup.moshi.Json


data class SsqQueried(
    @Json(name = "result")
    val result:List<Ssq> = ArrayList()
)

data class Ssq(
    @Json(name = "code")
    val code:String="",
    @Json(name = "red")
    val red:String="",
    @Json(name = "blue")
    val blue:String="",
    @Json(name = "date")
    val date:String=""
) {
    fun getNum() =  red.replace(",","-")
}

data class DltQueried(
    @Json(name = "value")
    val value:DltValue = DltValue()
)

data class DltValue (
    @Json(name = "list")
    val list:List<Dlt> = ArrayList()
)

data class Dlt(
    @Json(name = "lotteryDrawNum")
    val lotteryDrawNum:String="",
    @Json(name = "lotteryDrawResult")
    val lotteryDrawResult:String="",
    @Json(name = "lotteryDrawTime")
    val lotteryDrawTime:String=""
) {
    fun getNum() =  lotteryDrawResult.replace(" ","-")
}