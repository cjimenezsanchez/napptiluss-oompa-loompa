package com.jime.listdetail.domain.error

sealed class Error {

    object Network: Error()
    object UnAuthorized: Error()
    object NotFound: Error()
    object ServiceUnavailable: Error()
    object Unknown: Error()

}
