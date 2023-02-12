package com.jime.listdetail.domain.model

sealed class Gender(val name: String) {

    object Male: Gender("male")
    object Female: Gender("female")

    companion object {
        fun fromString(gender: String): Gender {
            return when (gender) {
                Male.name -> Male
                else -> Female
            }
        }
    }

}
