package com.jime.listdetail.domain.model

sealed class Profession(val name: String) {

    object Developer: Profession("developer")
    object Unknown: Profession("")

    companion object {
        fun fromString(profession: String): Profession {
            return when (profession) {
                Developer.name -> Developer
                else -> Unknown
            }
        }
    }

}



