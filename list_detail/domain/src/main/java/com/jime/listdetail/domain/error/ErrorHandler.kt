package com.jime.listdetail.domain.error

interface ErrorHandler {

    companion object {
        const val UNAUTHORIZED_HTTP_ERROR = 401
        const val NOT_FOUND_HTTP_ERROR = 404
        const val SERVER_HTTP_ERROR = 500
    }

    fun getError(t: Throwable): Error

}