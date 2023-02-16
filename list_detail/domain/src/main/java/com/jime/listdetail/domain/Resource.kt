package com.jime.listdetail.domain

import com.jime.listdetail.domain.error.Error

sealed class Resource<T> {

    data class Success<T>(val data: T): Resource<T>()

    data class Failure<T>(val error: Error, val data: T? = null): Resource<T>()

}
