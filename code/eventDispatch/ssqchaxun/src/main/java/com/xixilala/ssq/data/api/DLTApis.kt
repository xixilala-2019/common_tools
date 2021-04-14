package com.xixilala.ssq.data.api

import com.xixilala.ssq.data.model.DltQueried
import com.xixilala.ssq.data.model.SsqQueried
import retrofit2.http.*

interface DLTApis {

    @GET("gateway/lottery/getHistoryPageListV1.qry")
    //gameNo=85&provinceId=0&pageSize=30&isVerify=1&pageNo=1
    suspend fun queryDlt(
        @Query("gameNo") gameNo: Int=85,
        @Query("provinceId") provinceId: Int=0,
        @Query("pageSize") pageSize: Int=30,
        @Query("isVerify") isVerify: Int=1,
        @Query("pageNo") pageNo: Int=1
    ): DltQueried

}