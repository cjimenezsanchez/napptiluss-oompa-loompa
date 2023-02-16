package com.jime.listdetail.presentation.list.filter

import com.jime.listdetail.domain.model.Profession

sealed class ProfessionFilter {

    object None: ProfessionFilter()
    data class ByType(val type: Profession): ProfessionFilter()

}
