package com.farm2seoul_frontend_aos.data.repository

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.farm2seoul_frontend_aos.data.model.RowItems
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class PagingSource(
    private val retrofitInterface: RetrofitInterface,
    private val context: Context
) : PagingSource<Int, RowItems>() {

    override fun getRefreshKey(state: PagingState<Int, RowItems>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RowItems> = withContext(Dispatchers.IO){
        val start = params.key ?: 1
        return@withContext try {
            val response = retrofitInterface.getGarakGradePrice(start.toString(), (start + 10).toString())
            val items = response.garakGradePrice.row
            val prevKey = if (start == 1) null else start - 10
            val nextKey = if (items.isEmpty()) null else start + 10
            LoadResult.Page(
                data = items,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: IOException) {      //네트워크 에러 처리
            Log.e(ContentValues.TAG, "IOException: $e")
            LoadResult.Error(e)
        } catch (e: HttpException) {    //서버 에러 처리
            Log.e(
                ContentValues.TAG, "HttpException: ${e.response()?.code()}\n" +
                        "HttpExceptionBody: ${e.response()?.errorBody()?.string()}"
            )
            LoadResult.Error(e)
        } catch (e: Exception) {        //나머지 예외 처리
            Log.e(ContentValues.TAG, "Exception: $e")
            LoadResult.Error(e)
        }.also {//에러 메시지 출력
            if (it is LoadResult.Error) {
                val message = when (it.throwable) {
                    is IOException -> "네트워크 오류가 발생했습니다."
                    is HttpException -> "서버 오류가 발생했습니다."
                    else -> "알 수 없는 오류가 발생했습니다."
                }
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

}