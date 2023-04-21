package com.farm2seoul_frontend_aos.presentation.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.farm2seoul_frontend_aos.data.model.RowItems
import com.farm2seoul_frontend_aos.data.repository.PagingSource
import com.farm2seoul_frontend_aos.data.repository.RetrofitBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import javax.inject.Inject

@HiltViewModel
class Fragment1ViewModel @Inject constructor(
    application: Application
) : BaseViewModel(application) {

    private val retrofitInterface = RetrofitBuilder.create()
    private val _date = MutableLiveData<String>()
    val date: LiveData<String>
        get() = _date
/*    private val _auctionResult = MutableLiveData<PagingData<RowItems>>() //페이징 데이터 옵저빙 (화면 회전 시 데이터 손실 방지)
    val auctionResult: LiveData<PagingData<RowItems>>
        get() = _auctionResult*/

    init {
        _date.value = "0000.00.00"
    }


    //페이징 데이터 스트림 설정
    val getData = Pager(PagingConfig(pageSize = 10)) {
        PagingSource(retrofitInterface, context)
    }.flow.cachedIn(viewModelScope)/*.collect {
        _auctionResult.value = it
    }*/

    fun date() {
        viewModelScope.launch {
            val response = retrofitInterface.getGarakGradePrice("1", "2")
            val date = response.garakGradePrice.row[0].INVEST_DT
            val year = date.substring(0, 4)
            val month = date.substring(4, 6)
            val day = date.substring(6, 8)
            val string = "$year.$month.$day 일별 경매"
            _date.value = string
        }

    }

}