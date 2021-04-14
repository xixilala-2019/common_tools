package com.xixilala.ssq.mvi.ssq

sealed class QueryIntent {
    object querySsqList :QueryIntent()
    object queryDltList:QueryIntent()
}