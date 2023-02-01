package com.example.dcardhomework.ext

import androidx.fragment.app.Fragment
import com.example.dcardhomework.DcardApplication
import com.example.dcardhomework.factory.ViewModelFactory
import com.example.dcardhomework.data.source.DcardRepository


fun Fragment.getVmFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as DcardApplication).DcardRepository
    return ViewModelFactory(repository)
}

