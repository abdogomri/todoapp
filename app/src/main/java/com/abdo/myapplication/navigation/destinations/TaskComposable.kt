package com.abdo.myapplication.navigation.destinations

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.abdo.myapplication.ui.screens.task.TaskScreen
import com.abdo.myapplication.ui.viewmodels.SharedViewModel
import com.abdo.myapplication.util.Action
import com.abdo.myapplication.util.Constants
import com.abdo.myapplication.util.Constants.TASK_ARGUMENT_KEY
import com.abdo.myapplication.util.Constants.TASK_SCREEN

fun NavGraphBuilder.taskComposable(
    navigateToListScreen: (Action) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(Constants.TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        }),
    ) { navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)
        sharedViewModel.getSelectedTask(taskId = taskId)
        val selectedTask by sharedViewModel.selectedTask.collectAsStateWithLifecycle()
        TaskScreen(selectedTask = selectedTask, navigateToListScreen = navigateToListScreen)
    }
}