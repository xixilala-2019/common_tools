package com.xixilala.ssq.data.api

import com.xixilala.ssq.data.model.SsqQueried
import retrofit2.http.*

private val REFERER = "http://www.cwl.gov.cn/kjxx/ssq/"
private val ACCEPT_ENCODING = "http://www.cwl.gov.cn/kjxx/ssq/"
interface SSQApis {

    @GET("findDrawNotice")
    suspend fun querySsq(
        @Header("Accept-Encoding") acceptEncoding: String = ACCEPT_ENCODING,
        @Header("Referer") referer: String = REFERER,
        @Query("name") name: String,
        @Query("issueCount") issueCount: Int
    ): SsqQueried

}