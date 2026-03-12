package com.kotlin.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterContent(onBackClick: () -> Unit, onLoginClick: () -> Unit) {

    fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@(.+)\$")
        return emailRegex.matches(email)

    }

    fun isEqual(password: String, cfmPassword: String): Boolean {
        return password == cfmPassword
    }

    fun isFilled(name: String, phone: String, email: String, password: String, cfmPassword: String): Boolean {
        return name.isNotEmpty() && phone.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && cfmPassword.isNotEmpty()
    }

    fun isValidNumber(phone: String): Boolean {
        val phoneRegex = Regex("^[0-9]{10}\$")
        return phoneRegex.matches(phone)
    }
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var cfmPassword by remember { mutableStateOf("") }

    var mailError by remember { mutableStateOf(false) }

    var passwordError by remember { mutableStateOf(false) }
    var eqlPasswordError by remember { mutableStateOf(false) }
    var phoneError by remember { mutableStateOf(false) }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "←",
                fontSize = 24.sp,
                modifier = Modifier.clickable { onBackClick() }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Create Account",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(32.dp))

        TextField(
            value = name,
            onValueChange = { name = it },
            placeholder = { Text("Full Name") },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color(0xFF4A43A8),
                unfocusedIndicatorColor = Color.LightGray
            )
        )

        Spacer(modifier = Modifier.height(16.dp))



        TextField(
            value = email,
            onValueChange = {
                email = it
                mailError = !isValidEmail(it)
                            },
            placeholder = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            isError = mailError,

            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color(0xFF4A43A8),
                unfocusedIndicatorColor = Color.LightGray
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = phone,
            onValueChange = {
                phone = it
                phoneError = !isValidNumber(it)
                            },
            placeholder = { Text("Phone") },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            isError = phoneError,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color(0xFF4A43A8),
                unfocusedIndicatorColor = Color.LightGray
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color(0xFF4A43A8),
                unfocusedIndicatorColor = Color.LightGray
            )
        )

        Spacer(modifier = Modifier.height(32.dp))
        TextField(
            value = cfmPassword,
            onValueChange = {
                cfmPassword = it
                eqlPasswordError = !isEqual(password, it)
                            },
            placeholder = { Text("Confirm Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            isError = eqlPasswordError,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color(0xFF4A43A8),
                unfocusedIndicatorColor = Color.LightGray
            )
        )

        Spacer(modifier = Modifier.height(32.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .background(
                    color = if (isFilled(name, phone, email, password, cfmPassword)) Color(0xFF4A43A8) else Color(0xFF4A43A8).copy(alpha = 0.5f),
                    shape = RoundedCornerShape(30.dp)
                )
                .clickable { /* Handle Register */ },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Sign Up",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row {
            Text(text = "Already have an account? ", color = Color.Gray)
            Text(
                text = "Login",
                color = Color(0xFF4A43A8),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onLoginClick() }
            )
        }
    }
}
