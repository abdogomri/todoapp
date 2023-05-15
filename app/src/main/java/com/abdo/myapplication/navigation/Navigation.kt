package com.abdo.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.abdo.myapplication.navigation.destinations.listComposable
import com.abdo.myapplication.navigation.destinations.taskComposable
import com.abdo.myapplication.ui.viewmodels.SharedViewModel
import com.abdo.myapplication.util.Constants.LIST_SCREEN

@Composable
fun SetupNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    val screen = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(
        navController = navController,
        startDestination = LIST_SCREEN
    ) {
        listComposable(
            navigateToTaskScreen = screen.task,
            sharedViewModel = sharedViewModel
        )

        taskComposable(
            sharedViewModel = sharedViewModel,
            navigateToListScreen = screen.list
        )
    }

}