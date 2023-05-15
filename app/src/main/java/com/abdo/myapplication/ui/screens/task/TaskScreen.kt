package com.abdo.myapplication.ui.screens.task

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.abdo.myapplication.data.models.Priority
import com.abdo.myapplication.data.models.ToDoTask
import com.abdo.myapplication.util.Action

@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    navigateToListScreen: (Action) -> Unit
) {

    Scaffold(
        topBar = {
            TaskAppBar(
                selectedTask = selectedTask,
                navigateToListScreen = navigateToListScreen
            )
        },
        content = { paddingValues ->
            TaskContent(
                title = "",
                onTitleChanged = {},
                description = "",
                onDescriptionChanged = {},
                priority = Priority.HIGH,
                onPrioritySelected = {},
                paddingValues = paddingValues
            )
        }
    )
}