package com.joincoded.petsapi.interfacePets

import com.joincoded.petsapi.Model.Pet
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PetApiServices {
    @GET("pets")
    suspend fun getAllPets(): List<Pet>

    @POST("pets")
    suspend fun addPet(@Body pet: Pet): Response<Pet>

    @DELETE("pets/{petsID}")
    suspend fun deletetPet(@Path("petID") petsID : Int): Response<Unit>


}










