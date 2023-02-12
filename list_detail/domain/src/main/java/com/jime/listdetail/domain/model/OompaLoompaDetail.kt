package com.jime.listdetail.domain.model

class OompaLoompaDetail(
    id: String,
    name: String,
    lastName: String,
    profession: Profession,
    gender: Gender,
    image: String,
    val age: Int,
    val height: Int,
    val email: String,
    val description: String,
    val country: String,
    val quota: String,
    val favoriteColor: String,
    val favoriteFood: String,
) : OompaLoompa(
    id = id,
    name = name,
    lastName = lastName,
    profession = profession,
    gender = gender,
    image = image
)
