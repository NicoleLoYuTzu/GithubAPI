package com.example.dcardhomework.data.source

import com.example.dcardhomework.Result
import com.example.dcardhomework.data.SearchResponse
import com.example.dcardhomework.network.DcardApi
import com.example.dcardhomework.util.Logger

object RemoteDataSource: DcardRepository {

    override suspend fun getInfo(searchQuery:String): Result<SearchResponse> {
        return try {
            // this will run on a thread managed by Retrofit
            val listResult = DcardApi.retrofitService.searchRepos(searchQuery)
            Logger.i("getInfo => $listResult")

            return Result.Success(listResult)
        } catch (e: Exception) {
            Logger.w("[${this::class.simpleName}] exception=${e.message}")
            Result.Error(e)
        }
    }


}