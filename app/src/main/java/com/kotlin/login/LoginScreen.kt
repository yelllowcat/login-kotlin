package com.kotlin.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.kotlin.login.components.CustomButton
import com.kotlin.login.components.CustomTextField

@Composable
fun LoginContent(
    onBackClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onLoginSuccess: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val architectsDaughter = FontFamily(
        Font(R.font.architects_daughter, FontWeight.Normal)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        // Title
        Text(
            text = "Inicia Sesión",
            fontFamily = architectsDaughter,
            fontSize = 38.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Plane Illustration
        Image(
            painter = painterResource(id = R.drawable.transportation_plane),
            contentDescription = "Plane Illustration",
            modifier = Modifier
                .width(260.dp)
                .height(130.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Input Fields (width 85%)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CustomTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = "Correo electrónico",
                modifier = Modifier.fillMaxWidth()
            )

            CustomTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = "Contraseña",
                isPasswordField = true,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(36.dp))

        // Login Button
        CustomButton(
            text = "Iniciar sesión",
            onClick = { onLoginSuccess() },
            modifier = Modifier
                .width(200.dp)
                .height(52.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Navigation Link
        Row(
            modifier = Modifier
                .clickable { onSignUpClick() }
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "¿No tienes cuenta? ",
                color = Color.Gray,
                fontSize = 14.sp
            )
            Text(
                text = "Regístrate",
                color = Color(0xFF5A44EC),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.weight(1f, fill = false))

        // Bottom Illustration
        Image(
            painter = painterResource(id = R.drawable.digital_nomad),
            contentDescription = "Traveler Illustration",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            alignment = Alignment.BottomCenter
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginContentPreview() {
    LoginContent(
        onBackClick = {},
        onSignUpClick = {},
        onLoginSuccess = {}
    )
}
