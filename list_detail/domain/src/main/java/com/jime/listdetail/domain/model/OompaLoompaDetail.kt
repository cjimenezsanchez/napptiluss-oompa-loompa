package com.jime.listdetail.domain.model

class OompaLoompaDetail(
    id: Int,
    name: String,
    lastName: String,
    email: String,
    profession: Profession,
    gender: Gender,
    image: String,
    val age: Int,
    val height: Int,
    val description: String,
    val country: String,
    val quota: String,
    val favoriteSong: String,
    val favoriteColor: String,
    val favoriteFood: String,
) : OompaLoompa(
    id = id,
    name = name,
    lastName = lastName,
    email = email,
    profession = profession,
    gender = gender,
    image = image
)
