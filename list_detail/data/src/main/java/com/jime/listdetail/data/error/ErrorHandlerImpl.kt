package com.jime.listdetail.data.error


import com.jime.listdetail.domain.error.Error
import com.jime.listdetail.domain.error.ErrorHandler
import okio.IOException
import retrofit2.HttpException

class ErrorHandlerImpl(): ErrorHandler {

    override fun getError(t: Throwable): Error {
        return when(t) {
            is IOException -> Error.Network
            is HttpException -> {
                when (t.code()) {
                    ErrorHandler.UNAUTHORIZED_HTTP_ERROR -> Error.UnAuthorized
                    ErrorHandler.NOT_FOUND_HTTP_ERROR -> Error.NotFound
                    ErrorHandler.SERVER_HTTP_ERROR -> Error.ServiceUnavailable
                    else -> Error.Unknown
                }
            }
            else -> Error.Unknown
        }
    }
}