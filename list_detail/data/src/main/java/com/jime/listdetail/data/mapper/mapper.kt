package com.jime.listdetail.data.mapper

import com.jime.listdetail.data.remote.model.OompaLoompaDetailDto
import com.jime.listdetail.data.remote.model.OompaLoompaDto
import com.jime.listdetail.data.remote.model.OompaLoompaPagingDto
import com.jime.listdetail.domain.model.*

fun OompaLoompaPagingDto.toDomain(): OompaLoompaPaging {
    return OompaLoompaPaging(
        currentPage = currentPage,
        totalPages = totalPages,
        oompaLoompaList = results.map { it.toDomain() }
    )
}

fun OompaLoompaDto.toDomain(): OompaLoompa {
    return OompaLoompa(
        id = id,
        name = name,
        lastName = lastName,
        profession = Profession.fromString(profession),
        gender = Gender.fromString(gender),
        image = image
    )
}

fun OompaLoompaDetailDto.toDomain(): OompaLoompaDetail {
    return OompaLoompaDetail(
        id = id,
        name = name,
        lastName = lastName,
        profession = Profession.fromString(profession),
        gender = Gender.fromString(gender),
        image = image,
        age = age,
        height = height,
        email = email,
        description = description,
        country = country,
        quota = quota,
        favoriteColor = favoriteStuff.color,
        favoriteFood = favoriteStuff.food
    )
}