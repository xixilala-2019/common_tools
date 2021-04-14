package com.xixilala.ssq.mvi.ssq

import com.xixilala.ssq.data.model.Dlt
import com.xixilala.ssq.data.model.Ssq

sealed class QueryState {

    object Idle:QueryState()
    object Loading:QueryState()
    object Error:QueryState()
    data class SsqSuccess(val list:List<Ssq>):QueryState()
    data class DltSuccess(val list:List<Dlt>):QueryState()
}