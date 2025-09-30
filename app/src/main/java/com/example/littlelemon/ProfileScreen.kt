package com.example.littlelemon

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    // ✅ Wrap values in state to make Compose recompose
    val firstName = remember { mutableStateOf(sharedPref.getString("firstName", "") ?: "") }
    val lastName = remember { mutableStateOf(sharedPref.getString("lastName", "") ?: "") }
    val emailId = remember { mutableStateOf(sharedPref.getString("emailId", "") ?: "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Profile",
            modifier = Modifier.fillMaxWidth()
                .height(200.dp)
                .padding(10.dp),

        )
        Text("Profile Details", fontSize = 30.sp, fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 6.dp))

        Spacer(modifier = Modifier.height(16.dp))
        Text("First Name:",fontSize = 23.sp, fontWeight = FontWeight.W500,
            modifier = Modifier.padding(10.dp))

        OutlinedCard(
            modifier = Modifier
                .padding(10.dp)
                .height(40.dp)
                .width(300.dp),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(2.dp, Color.Black)
        ) {
            Text(
                text = firstName.value,
                fontSize = 22.sp, fontWeight = FontWeight.W400,
                modifier = Modifier.padding(8.dp)
            )
        }

        Text("Last Name: ",fontSize = 22.sp, fontWeight = FontWeight.W500,
            modifier = Modifier.padding(10.dp))
        OutlinedCard(
            modifier = Modifier
                .padding(10.dp)
                .height(40.dp)
                .width(300.dp),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(2.dp, Color.Black)
        ) {
            Text(
                text = lastName.value,
                fontSize = 22.sp, fontWeight = FontWeight.W400,
                modifier = Modifier.padding(8.dp)
            )
        }
        Text("Email: ${emailId.value}",fontSize = 22.sp, fontWeight = FontWeight.W500,
            modifier = Modifier.padding(10.dp))
        OutlinedCard(
            modifier = Modifier
                .padding(10.dp)
                .height(40.dp)
                .width(300.dp),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(2.dp, Color.Black)
        ) {
            Text(
                text = emailId.value,
                fontSize = 22.sp, fontWeight = FontWeight.W400,
                modifier = Modifier.padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Button(onClick = {
            val editor = sharedPref.edit()
            editor.clear()
            editor.apply()
            navController.navigate("onboard") },
            modifier = Modifier.fillMaxWidth()
                .height(50.dp)
                .width(200.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF4CE14), // background
                contentColor = Color.Black )) {
            Text("Logout")
        }
    }
}
