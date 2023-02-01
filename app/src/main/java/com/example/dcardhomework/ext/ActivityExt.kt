package com.example.dcardhomework.ext

import android.app.Activity
import com.example.dcardhomework.DcardApplication
import com.example.dcardhomework.factory.ViewModelFactory

fun Activity.getVmFactory(): ViewModelFactory {
    val repository = (applicationContext as DcardApplication).DcardRepository
    return ViewModelFactory(repository)
}
