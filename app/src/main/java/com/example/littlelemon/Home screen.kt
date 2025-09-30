package com.example.littlelemon

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    val firstName = sharedPref.getString("firstName", "") ?: ""

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        // Header Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f)) // push logo to center

            // Logo in center
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(250.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            // Profile button on right
            IconButton(
                onClick = { navController.navigate("profile") }
            ) {
                Image(
                    painter = painterResource(R.drawable.profile),
                    contentDescription = "Profile",
                    modifier = Modifier.size(48.dp)
                )
            }
        }

        // 🏠 Home content goes here (below header)
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column {
                Text("Welcome to Home!", fontSize = 24.sp)

                Spacer(modifier = Modifier.weight(0.50f))
                Button(onClick = {
                    navController.navigate("profile")
                }) {
                    Text(
                        text = "Set Your Profile",
                        fontSize = 20.sp,   // use sp, not dp
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
