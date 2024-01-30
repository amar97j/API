package com.joincoded.petsapi.repo

import com.joincoded.petsapi.interfacePets.PetApiServices

class PetRepository (private val api:PetApiServices){
    suspend fun getAllPets()= api.getAllPets()
}