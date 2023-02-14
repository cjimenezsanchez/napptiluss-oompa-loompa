package com.jime.listdetail.domain.model

sealed class Gender(val name: String) {

    object Male: Gender("M")
    object Female: Gender("F")

    companion object {
        fun fromString(gender: String): Gender {
            return when (gender) {
                Male.name -> Male
                else -> Female
            }
        }
    }

}

