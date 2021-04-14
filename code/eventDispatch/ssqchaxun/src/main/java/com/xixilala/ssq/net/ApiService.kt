package com.xixilala.ssq.net

import com.xixilala.ssq.data.api.DLTApis
import com.xixilala.ssq.data.api.SSQApis
import com.xixilala.ssq.data.model.Dlt
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiService {

    private const val SSQ_BASE_URL = "http://www.cwl.gov.cn/cwl_admin/kjxx/"
    private const val DLT_BASE_URL = "https://webapi.sporttery.cn/"

    private fun getSSQService(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(SSQ_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
    private fun getDLTService(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(DLT_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
    val ssqApiService = getSSQService().create(SSQApis::class.java)

    val dltApiService = getDLTService().create(DLTApis::class.java)
}