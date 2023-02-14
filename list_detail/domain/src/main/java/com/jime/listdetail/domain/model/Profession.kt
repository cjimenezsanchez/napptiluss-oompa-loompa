package com.jime.listdetail.domain.model

sealed class Profession(val name: String) {

    object Developer: Profession("Developer")
    object Metalworker: Profession("Metalworker")
    object Gemcutter: Profession("Gemcutter")
    object Medic: Profession("Medic")
    object Brewer: Profession("Brewer")
    object Unknown: Profession("Other")

    companion object {
        fun fromString(profession: String): Profession {
            return when (profession) {
                Developer.name -> Developer
                Metalworker.name -> Metalworker
                Gemcutter.name -> Gemcutter
                Medic.name -> Medic
                Brewer.name -> Brewer
                else -> Unknown
            }
        }
    }

}



