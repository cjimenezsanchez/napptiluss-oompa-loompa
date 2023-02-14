package com.jime.listdetail.presentation.util

import com.jime.listdetail.domain.model.Gender
import com.jime.listdetail.domain.model.Profession
import com.jime.listdetail.presentation.R

fun Gender.getStringId(): Int {
    return when (this) {
        is Gender.Male -> R.string.male
        else -> R.string.female
    }
}

fun Profession.getStringId(): Int {
    return when (this) {
        is Profession.Developer -> R.string.developer
        is Profession.Brewer -> R.string.brewer
        is Profession.Medic -> R.string.medic
        is Profession.Gemcutter -> R.string.gemcutter
        is Profession.Metalworker -> R.string.metalworker
        else -> R.string.unknown
    }
}