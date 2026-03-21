package com.kotlin.login

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import com.kotlin.login.components.Contact


@Composable
fun ListItemsExample(){
    val frutas = listOf("Manzana", "Banana", "Pera", "Naranja", "Uva")

    LazyColumn {
        items(frutas){ fruta ->
            Text(
                text = fruta,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListItemsExamplePreview(){

    val contacts = listOf(
        Pair("Juan", "123456789"),
        Pair("Maria", "987654321"),
        Pair("Pedro", "555666777")
    )

    LazyColumn {
        items(contacts) { contact ->
            Contact(
                name = contact.first,
                phone = contact.second
            )
        }
    }
}
