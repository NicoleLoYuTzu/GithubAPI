package com.example.dcardhomework.util

import android.content.Context
import androidx.annotation.VisibleForTesting
import com.example.dcardhomework.data.source.DcardRepository
import com.example.dcardhomework.data.source.RemoteDataSource

object ServiceLocator {

    @Volatile
    var dcardRepository: DcardRepository? = null
        @VisibleForTesting set

    fun provideTasksRepository(context: Context): DcardRepository {
        synchronized(this) {
            return dcardRepository
                ?: dcardRepository
                ?: createDcardRepository(context)
        }
    }

        private fun createDcardRepository(context: Context): DcardRepository {
        return RemoteDataSource
    }

}
