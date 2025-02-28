package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
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
    var tasks by remember { mutableStateOf(listOf<String>()) }
    var text by remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier.padding(16.dp)) {
        BasicTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (text.text.isNotBlank()) {
                        tasks = tasks + text.text
                        text = TextFieldValue("")
                    }
                }
            )
        )
        Button(
            onClick = {
                if (text.text.isNotBlank()) {
                    tasks = tasks + text.text
                    text = TextFieldValue("")
                }
            },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text("Add Task")
        }
        Column {
            tasks.forEach { task ->
                Text(text = task, modifier = Modifier.padding(4.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoAppPreview() {
    TodolistTheme {
        TodoApp()
    }
}
