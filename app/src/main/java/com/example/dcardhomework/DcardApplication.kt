package com.example.dcardhomework

import android.app.Application
import android.content.res.Configuration
import com.example.dcardhomework.data.source.DcardRepository
import com.example.dcardhomework.util.ServiceLocator
import java.util.*
import kotlin.properties.Delegates

class DcardApplication : Application() {

//     Depends on the flavor,
    val DcardRepository: DcardRepository
        get() = ServiceLocator.provideTasksRepository(this)

    companion object {
        var instance: DcardApplication by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
