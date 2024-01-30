package com.joincoded.petsapi

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.joincoded.petsapi.Model.Pet
import com.joincoded.petsapi.ui.theme.PetsAPITheme
import com.joincoded.petsapi.viewModel.PetViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetsAPITheme {

                AddContent()

            }
        }
    }


}


@Composable
fun PetListScreen(viewModel: PetViewModel, padding: PaddingValues) {
    val pets = viewModel.pets
    LazyColumn(modifier = Modifier.padding(padding)) {
        items(pets) { pet ->
            PetItem(pet)
        }
    }

}

@Composable
fun PetItem(pet: Pet,petViewModel: PetViewModel = viewModel()) {

    val petViewModel : PetViewModel = viewModel()

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),

        ) {

        // TODO: Alignment
        Row(
            Modifier
                .padding(10.dp)
                .height(150.dp),
            verticalAlignment = Alignment.CenterVertically
        )
        {

            AsyncImage(
                model = pet.image,
                contentDescription = null,
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)

            )



            Column {

                Text(text = pet.name)
                Text(text = pet.gender)

            }
        }

    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContent() {
    var petViewModel: PetViewModel = viewModel()
    var navController = rememberNavController()



    Scaffold(
        topBar = { TopAppBar(title = { "My Pets List" }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Text(text = "+")

            }
        }




    ) { padding ->

        NavHost(navController = navController, startDestination = "petList") {
            composable("petList") {
                PetListScreen(petViewModel, padding)

            }
            composable("addPet") {
                AddPetScreen()
            }

        }


    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddPetScreen() {

    val petViewModel: PetViewModel = viewModel()


    var name by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var adopted by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    val image by remember { mutableStateOf("") }


    Scaffold {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Add a New Pet",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            InputField(
                value = name,
                onValueChange = { name = it.toString() },
                label = "Name"
            )
            InputField(
                value = age,
                onValueChange = { age = it.toString() },
                label = "Age"
            )
            InputField(
                value = gender,
                onValueChange = { gender = it.toString() },
                label = "gender",
                keyboardType = KeyboardType.Number
            )

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val newPet = Pet(
                        id = 0,  // Assuming ID is generated by the server
                        name = name,
                        gender = gender,
                        age = age.toInt(),
                        image = image,
                        adopted = adopted.toBoolean()
                    )
                    petViewModel.addPet(newPet)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Book")
            }
        }
    }
}

@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}