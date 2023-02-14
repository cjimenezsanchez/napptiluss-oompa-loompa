package com.jime.listdetail.presentation.list

data class Event<out T>(private val content: T) {

    private var hasBeenHandled = false

    fun getContentIfHasNotBeenHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }
}