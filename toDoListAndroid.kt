@Composable
fun TodoListApp() {
    var tasks by remember { mutableStateOf(listOf<String>()) }
    var newTask by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = newTask,
            onValueChange = { newTask = it },
            label = { Text("Enter a task") }
        )

        Button(onClick = {
            if (newTask.isNotEmpty()) {
                tasks = tasks + newTask
                newTask = ""
            }
        }) {
            Text("Add Task")
        }

        LazyColumn {
            items(tasks) { task ->
                Text(task, modifier = Modifier.padding(8.dp))
            }
        }
    }
}
