package com.example.todolist.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolist.models.TodoTask
import com.example.todolist.ui.theme.GreyCustom

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(screenNumber: MutableState<Int>, tasks: MutableList<TodoTask>) {

    val title = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        TextField(
            value = title.value,
            onValueChange = { title.value = it },
            label = { Text("Titre") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = description.value,
            onValueChange = { description.value = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            modifier = Modifier
                .padding(16.dp),
        ) {
            Button(onClick = {
                tasks.add(TodoTask(title.value, description.value))
                screenNumber.value = 1
            }, modifier = Modifier.fillMaxWidth()) {
                Text("Ajouter")
            }

            Button(
                onClick = {
                    screenNumber.value = 1
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = GreyCustom, contentColor = Color.White),
            ) {
                Text("Quitter")
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun AddTaskScreenPreview() {
    val myTasks = remember { mutableStateListOf<TodoTask>() }
    val screenNumber = remember { mutableStateOf(2) }
    AddTaskScreen(screenNumber = screenNumber, tasks = myTasks)
}