package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolist.ui.theme.TodolistTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodolistTheme {
                TodoApp()
            }
        }
    }
}

@Composable
fun TodoApp() {
    var taskText by remember { mutableStateOf("") }
    var tasks by remember { mutableStateOf(listOf<Pair<String, Boolean>>()) }

    Column(modifier = Modifier.padding(16.dp)) {
        Row {
            TextField(
                value = taskText,
                onValueChange = { taskText = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Enter task...") },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        if (taskText.isNotBlank()) {
                            tasks = tasks + (taskText to false)
                            taskText = ""
                        }
                    }
                )
            )
            Button(
                onClick = {
                    if (taskText.isNotBlank()) {
                        tasks = tasks + (taskText to false)
                        taskText = ""
                    }
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text("Add")
            }
        }
        LazyColumn {
            items(tasks) { task ->
                TaskItem(task) { updatedTask ->
                    tasks = tasks.map {
                        if (it.first == task.first) updatedTask else it
                    }
                }
            }
        }
    }
}

@Composable
fun TaskItem(task: Pair<String, Boolean>, onCheckedChange: (Pair<String, Boolean>) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = task.first, modifier = Modifier.weight(1f))
        Checkbox(
            checked = task.second,
            onCheckedChange = { onCheckedChange(task.first to it) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TodoAppPreview() {
    TodolistTheme {
        TodoApp()
    }
}
