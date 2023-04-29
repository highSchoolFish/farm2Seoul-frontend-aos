package com.farm2seoul_frontend_aos.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.farm2seoul_frontend_aos.data.repository.PagingSource
import com.farm2seoul_frontend_aos.data.repository.OpenApiRetrofitBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    private val retrofitInterface = OpenApiRetrofitBuilder.create()

    private val _date = MutableLiveData<String>()
    val date: LiveData<String>
        get() = _date

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error

    private val _search = MutableLiveData<String>()

    /*
    private val _auctionResult = MutableLiveData<PagingData<RowItems>>() //페이징 데이터 옵저빙 (화면 회전 시 데이터 손실 방지)
    val auctionResult: LiveData<PagingData<RowItems>>
        get() = _auctionResult
    */

    init {
        _date.value = "0000.00.00"
        _error.value = false
        _search.value = ""

    }

    /** 네트워크 에러 핸들러 **/
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        _error.value = true
    }

    /** 검색 쿼리 전달 **/
    fun search(search_query: String) {
        _search.value = search_query
    }

    /** 검색 쿼리 전달 **/
    fun getSearch(): MutableLiveData<String> = _search

    /** 페이징 데이터 스트림 설정 **/
    val getData = Pager(PagingConfig(pageSize = 10)) {
        PagingSource(retrofitInterface, application, "")
    }.flow

    /** 검색 페이징 데이터 스트림 설정 **/
    val getSearchData = Pager(PagingConfig(pageSize = 10)) {
        PagingSource(retrofitInterface, application, _search.value.toString())
    }.flow

    /** 경매 날짜 통신 **/
    fun date() {
        viewModelScope.launch(exceptionHandler) {
            _error.value = false
            val response = retrofitInterface.getGarakGradePrice("1", "2", "")
            val date = response.garakGradePrice.row[0].INVEST_DT
            val year = date.substring(0, 4)
            val month = date.substring(4, 6)
            val day = date.substring(6, 8)
            val string = "$year.$month.$day 일별 경매"
            _date.value = string
        }
    }
}