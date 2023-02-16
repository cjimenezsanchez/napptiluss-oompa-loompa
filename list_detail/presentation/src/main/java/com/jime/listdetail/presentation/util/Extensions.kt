package com.jime.listdetail.presentation.util

import com.jime.listdetail.domain.model.Gender
import com.jime.listdetail.domain.model.Profession
import com.jime.listdetail.presentation.R
import com.jime.listdetail.presentation.list.filter.ProfessionFilter

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

fun Profession.getDrawableId(): Int {
    return when (this) {
        is Profession.Developer -> R.drawable.ic_developer
        is Profession.Brewer -> R.drawable.ic_brewer
        is Profession.Medic -> R.drawable.ic_medic
        is Profession.Gemcutter -> R.drawable.ic_gemcutter
        is Profession.Metalworker -> R.drawable.ic_metal_worker
        else -> R.drawable.ic_unknown
    }
}

infix fun ProfessionFilter.sameAs(filter: ProfessionFilter): Boolean {
    return when (this) {
        is ProfessionFilter.None -> {
           filter is ProfessionFilter.None
        }
        is ProfessionFilter.ByType -> {
            when (filter) {
                is ProfessionFilter.None -> false
                is ProfessionFilter.ByType -> {
                    return this.type == filter.type
                }
            }
        }
    }
}