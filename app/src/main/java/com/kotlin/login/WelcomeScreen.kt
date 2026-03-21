package com.kotlin.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WelcomeScreen() {
    val navController = rememberNavController()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF4A43A8)),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.95f)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(40.dp)
                )
        ) {
            NavHost(navController = navController, startDestination = "welcome") {
                composable("welcome") {
                    WelcomeContent(
                        onLoginClick = { navController.navigate("login") },
                        onSignUpClick = { navController.navigate("register") }
                    )
                }
                composable("login") {
                    LoginContent(
                        onBackClick = { navController.popBackStack() },
                        onSignUpClick = { navController.navigate("register") },
                        onLoginSuccess = { navController.navigate("contacts") }
                    )
                }
                composable("register") {
                    RegisterContent(
                        onBackClick = { navController.popBackStack() },
                        onLoginClick = { navController.navigate("login") }
                    )
                }
                composable("contacts") {
                    ContactsScreen()
                }
            }
        }
    }
}

@Composable
fun WelcomeContent(onLoginClick: () -> Unit, onSignUpClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.phone_image),
            contentDescription = "Welcome Image",
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        Text(
            text = "Hello",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Text(
            text = "Welcome To Little Drop, where\nyou manage you daily tasks",
            fontSize = 14.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .height(55.dp)
                .background(
                    color = Color(0xFF4A43A8),
                    shape = RoundedCornerShape(30.dp)
                )
                .clickable { onLoginClick() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Login",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .height(55.dp)
                .background(
                    color = Color.Transparent,
                    shape = RoundedCornerShape(30.dp)
                )
                .border(
                    width = 2.dp,
                    color = Color(0xFF4A43A8),
                    shape = RoundedCornerShape(30.dp)
                )
                .clickable { onSignUpClick() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Sign Up",
                color = Color(0xFF4A43A8),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Text(
            text = "Sign up using",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 20.dp)
        )

        Row(
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            SocialCircle(Color(0xFF3B5998), "f")
            SocialCircle(Color(0xFFDB4437), "G+")
            SocialCircle(Color(0xFF0077B5), "in")
        }
    }
}

@Composable
fun SocialCircle(color: Color, text: String) {
    Box(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .size(45.dp)
            .background(color = color, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}
