package com.xixilala.ssq.repository

import com.xixilala.ssq.data.apiImp.ApisImp
import com.xixilala.ssq.data.model.Dlt
import com.xixilala.ssq.data.model.Ssq

object QueryDltRepository {
    suspend fun queryList(): List<Dlt> {
        return ApisImp.queryDlt()
    }
}