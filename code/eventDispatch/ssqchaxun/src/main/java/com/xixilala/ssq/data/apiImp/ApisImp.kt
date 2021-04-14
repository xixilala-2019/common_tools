package com.xixilala.ssq.data.apiImp

import com.xixilala.ssq.data.api.DLTApis
import com.xixilala.ssq.data.api.SSQApis
import com.xixilala.ssq.data.model.Dlt
import com.xixilala.ssq.data.model.Ssq
import com.xixilala.ssq.net.ApiService.dltApiService
import com.xixilala.ssq.net.ApiService.ssqApiService

object ApisImp {

    suspend fun querySsq(apis:SSQApis = ssqApiService, name:String="ssq", itemCount:Int = 30): List<Ssq> {
        val querySsq = apis.querySsq(
            name = name,
            issueCount = itemCount
        )
        return querySsq.result
    }

    suspend fun queryDlt(apis:DLTApis = dltApiService): List<Dlt> {
        val querySsq = apis.queryDlt()
        return querySsq.value.list
    }
}