package com.example.dcardhomework.network

import java.util.*

/**
 * Created by notyen on 2022/9/26
 */
class TokenObservable private constructor() : Observable() {
    private var tokenStatus: TokenStatus = TokenStatus.NORMAL

    enum class TokenStatus {
        NORMAL, TOKEN_EXPIRE, SUBSCRIBE_EXPIRE
    }

    companion object {
        val instances: TokenObservable = TokenObservable()
    }

    fun setTokenStatus(tokenStatus: TokenStatus) {
        this.tokenStatus = tokenStatus
        setChanged()
        notifyObservers()
    }

    fun getTokenStatus(): TokenStatus {
        return tokenStatus
    }
}