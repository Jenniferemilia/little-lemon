package com.example.littlelemon

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardScreen(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)


    val coroutineScope = rememberCoroutineScope()
    var successMessage by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var emailId by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current

    fun handleClick(
        navController: NavController,
        firstName: String,
        lastName: String,
        emailId: String
    ) {
        val enabled = firstName.isNotBlank() && lastName.isNotBlank() && emailId.isNotBlank()

        if (enabled) {
            val data = sharedPref.edit()
            data.putString("firstName",firstName)
            data.putString("lastName",lastName)
            data.putString("emailId", emailId)
            data.apply()
            navController.navigate("home") {
                popUpTo("onboard") { inclusive = true }
            }
        }
    }

    Box(modifier = Modifier.fillMaxWidth()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus() // 👈 dismiss keyboard
                    })
                }
                .padding(top = 10.dp)) {

            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "app_logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(10.dp),
                contentScale = ContentScale.Fit
            )

            Box(
                modifier = Modifier.fillMaxWidth()
                    .background(Color(0xFF495E57))
                    .height(90.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "Let's get to know you",
                    modifier = Modifier.padding(start = 60.dp, end = 30.dp),
                    fontSize = 25.sp
                )
            }
            Text(
                text = "Personal Information",
                fontSize = 24.sp,
                fontWeight = Bold,
                modifier = Modifier.padding(20.dp)
            )

            TextField(
                modifier = Modifier.padding(start = 40.dp, bottom = 20.dp, top = 20.dp)
                    .border(
                        width = 3.dp,                         // border thickness
                        color = Color(0xFF495E57),            // border color
                        shape = RoundedCornerShape(16.dp)     // rounded corners
                    ),
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text(text = "First Name") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                ),

                )
            TextField(
                modifier = Modifier.padding(start = 40.dp, bottom = 20.dp, top = 20.dp)
                    .border(
                        width = 3.dp,                         // border thickness
                        color = Color(0xFF495E57),            // border color
                        shape = RoundedCornerShape(16.dp)     // rounded corners
                    ),
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text(text = "last Name") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )
            )

            TextField(
                modifier = Modifier.padding(start = 40.dp, bottom = 20.dp, top = 20.dp)
                    .border(
                        width = 3.dp,                         // border thickness
                        color = Color(0xFF495E57),            // border color
                        shape = RoundedCornerShape(16.dp)     // rounded corners
                    ),
                value = emailId,
                onValueChange = { emailId = it },
                label = { Text(text = "Mail Id") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )

            Button(
                onClick = { handleClick(navController, firstName, lastName, emailId) },
                modifier = Modifier
                    .padding(top = 50.dp, start = 50.dp, end = 30.dp)
                    .height(48.dp)
                    .width(280.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF495E57),
                    contentColor = Color.White
                ),
                border = BorderStroke(2.dp, Color.White),
                enabled = firstName.isNotBlank() && lastName.isNotBlank() && emailId.isNotBlank()
            ) {
                Text(text = "Register")
            }
        }
    }
}







