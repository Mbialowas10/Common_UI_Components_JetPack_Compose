package com.mbialowas.common_ui_components_jetpack_compose



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.camera.core.Camera
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone

import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UIControlsDemo()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UIControlsDemo() {
    var text by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }
    var gender by remember { mutableStateOf("Male") }
    var isSwitchOn by remember { mutableStateOf(false) }
    var isToggled by remember { mutableStateOf(false) }
    var sliderValue by remember { mutableStateOf(30f) }
    var dropdownExpanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Option 1") }

    val dropdownItems = listOf("Option 1", "Option 2", "Option 3")
    val listItems = listOf("Item A", "Item B", "Item C", "Item D")

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("UI Controls Demo") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
                .wrapContentSize(Alignment.TopStart),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Welcome to UI Controls Demo", fontSize = 18.sp)

            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Enter text here") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(onClick = {
                println("Button clicked with input: $text")
            }) {
                Text("Submit")
            }

            Icon(
                imageVector = Icons.Default.Phone,
                contentDescription = "Camera",
                modifier = Modifier.size(64.dp)
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = isChecked, onCheckedChange = { isChecked = it })
                Text("I agree")
            }

            Text("Gender:")
            Row {
                RadioButton(
                    selected = gender == "Male",
                    onClick = { gender = "Male" }
                )
                Text("Male")
                Spacer(Modifier.width(8.dp))
                RadioButton(
                    selected = gender == "Female",
                    onClick = { gender = "Female" }
                )
                Text("Female")
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Switch(checked = isSwitchOn, onCheckedChange = { isSwitchOn = it })
                Text("Enable feature")
            }

            Button(onClick = { isToggled = !isToggled }) {
                Text(if (isToggled) "Toggle ON" else "Toggle OFF")
            }

            Text("Slider: ${sliderValue.toInt()}")
            Slider(
                value = sliderValue,
                onValueChange = { sliderValue = it },
                valueRange = 0f..100f
            )

            LinearProgressIndicator(
                progress = sliderValue / 100f,
                modifier = Modifier.fillMaxWidth()
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.TopStart)
            ) {
                Button(onClick = { dropdownExpanded = true }) {
                    Text(selectedOption)
                }

                DropdownMenu(
                    expanded = dropdownExpanded,
                    onDismissRequest = { dropdownExpanded = false },
                    modifier = Modifier.fillMaxWidth() // Optional for full width
                ) {
                    dropdownItems.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                selectedOption = option
                                dropdownExpanded = false
                            }
                        )
                    }
                }
            }





            Text("List View:")
            LazyColumn {
                items(listItems) { item ->
                    Text(item)
                }
            }
        }
    }
}




