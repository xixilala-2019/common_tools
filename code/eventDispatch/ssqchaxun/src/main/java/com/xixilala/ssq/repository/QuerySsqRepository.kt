package com.xixilala.ssq.repository

import com.xixilala.ssq.data.apiImp.ApisImp
import com.xixilala.ssq.data.model.Ssq

object QuerySsqRepository {
    suspend fun queryList(): List<Ssq> {
        return ApisImp.querySsq()
    }
}