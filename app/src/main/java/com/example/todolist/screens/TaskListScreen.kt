package com.example.todolist.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolist.models.TodoTask
import com.example.todolist.ui.theme.BluePrimaryDark

@Composable
fun TaskListScreen(screenNumber: MutableState<Int>, selectedTask: MutableState<TodoTask?>, tasks: MutableList<TodoTask>) {
    LazyColumn {
        items(tasks) { task ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(text = task.title)

                Row {
                    IconButton(onClick = {
                        selectedTask.value = task
                        screenNumber.value = 3
                    }) {
                        Icon(Icons.Default.Info, contentDescription = "Details", tint = BluePrimaryDark)
                    }

                    IconButton(onClick = {
                        tasks.remove(task)
                    }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                    }
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskListScreenPreview() {
    val myTasks = remember { mutableStateListOf<TodoTask>() }
    val screenNumber = remember { mutableStateOf(2) }
    val selectedTask = remember { mutableStateOf<TodoTask?>(TodoTask("", "")) }
    TaskListScreen(screenNumber = screenNumber, selectedTask = selectedTask, tasks = myTasks)
}