package com.xixilala.ssq.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.xixilala.ssq.mvi.ssq.QueryIntent
import com.xixilala.ssq.mvi.ssq.QueryState
import com.xixilala.ssq.repository.QueryDltRepository
import com.xixilala.ssq.repository.QuerySsqRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import java.lang.Exception

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class MainViewModel(application: Application):AndroidViewModel(application) {

    val userIntent = Channel<QueryIntent>(Channel.UNLIMITED)

    private val _state = MutableStateFlow<QueryState>(QueryState.Idle)
    val state : StateFlow<QueryState>
        get() = _state

    init {
        handleIntent()
    }

    @InternalCoroutinesApi
    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect(object : FlowCollector<QueryIntent>{
                override suspend fun emit(value: QueryIntent) {
                    when(value) {
                        is QueryIntent.querySsqList -> {querySsqList()}
                        is QueryIntent.queryDltList -> {queryDltList()}
                    }
                }
            })
        }
    }

    private fun querySsqList() {
        viewModelScope.launch {
            _state.value =  QueryState.Loading
            try {
                _state.value = QueryState.SsqSuccess(QuerySsqRepository.queryList())
            } catch (e:Exception) {
                e.printStackTrace()
                _state.value = QueryState.Error
            }
        }
    }

    private fun queryDltList() {
        viewModelScope.launch {
            _state.value =  QueryState.Loading
            try {
                _state.value = QueryState.DltSuccess(QueryDltRepository.queryList())
            } catch (e:Exception) {
                e.printStackTrace()
                _state.value = QueryState.Error
            }
        }
    }
}