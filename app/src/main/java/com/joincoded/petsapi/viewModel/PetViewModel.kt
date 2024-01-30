package com.joincoded.petsapi.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.joincoded.petsapi.Model.Pet
import com.joincoded.petsapi.Singleton.RetrofitHelper
import com.joincoded.petsapi.interfacePets.PetApiServices
import com.joincoded.petsapi.repo.PetRepository
import kotlinx.coroutines.launch

class PetViewModel : ViewModel() {
    private val petApiService = RetrofitHelper.getInstance().create(PetApiServices::class.java)
    private val repository = PetRepository(petApiService)

    var pets by mutableStateOf(listOf<Pet>())

    init {
        fetchPets()
    }

    fun fetchPets() {
        viewModelScope.launch {
            try {
                pets = repository.getAllPets()
            } catch (e: Exception) {

                //Error message
            }


        }
    }


    fun addPet(pet: Pet) {
        viewModelScope.launch {
            try {
                var response = petApiService.addPet(pet)

                if (response.isSuccessful && response.body() != null) {
                    //Showing Successful message
                } else {
                    //Showing error message
                }
            } catch (e: Exception) {
                //Network issue
            }
        }

    }

    fun deletePet(petID: Int) {
        viewModelScope.launch {
            try {
                val response = petApiService.deletetPet(petID)
                if (response.isSuccessful) {

                } else {

                }
            } catch (e: Exception) {

            }
        }

    }
}


