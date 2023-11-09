package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.todolist.models.TodoTask
import com.example.todolist.screens.AddTaskScreen
import com.example.todolist.screens.TaskDetails
import com.example.todolist.screens.TaskListScreen
import com.example.todolist.ui.theme.BluePrimary
import com.example.todolist.ui.theme.ToDoListTheme
import com.example.todolist.ui.theme.YellowAccent

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoListTheme {
                MyAppContent()
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppContent() {
    val myTasks = remember { mutableStateListOf<TodoTask>() }
    val screenNumber = remember { mutableStateOf(1) }
    val selectedTask = remember { mutableStateOf<TodoTask?>(null) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                screenNumber.value = 2
            }, containerColor = YellowAccent, contentColor = Color.White) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
        topBar = {
            TopAppBar(
                title = { Text("Todo List") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = BluePrimary,
                    titleContentColor = Color.White
                )
            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            AnimatedVisibility(
                visible = screenNumber.value == 1,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                TaskListScreen(screenNumber = screenNumber, selectedTask = selectedTask, tasks = myTasks)
            }

            AnimatedVisibility(
                visible = screenNumber.value == 2,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                AddTaskScreen(screenNumber = screenNumber, tasks = myTasks)
            }

            AnimatedVisibility(
                visible = screenNumber.value == 3,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                TaskDetails(screenNumber = screenNumber, task = selectedTask.value)
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun MyAppContentPreview() {
    MyAppContent()
}