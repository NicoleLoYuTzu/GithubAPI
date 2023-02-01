package com.example.dcardhomework.data.source

import com.example.dcardhomework.data.SearchResponse

/**
 * Created by Wayne Chen in Jul. 2019.
 *
 * Interface to the Stylish layers.
 */
interface DcardRepository {

    suspend fun getInfo(searchQuery:String): com.example.dcardhomework.Result<SearchResponse>

}
