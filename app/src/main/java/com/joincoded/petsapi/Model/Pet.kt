package com.joincoded.petsapi.Model

data class Pet(
    val id: Int,
    val name: String,
    val adopted: Boolean = false,
    val image: String,
    val age: Int,
    val gender: String
)
