package com.abdo.myapplication.navigation.destinations

import androidx.compose.material3.Scaffold
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.abdo.myapplication.ui.screens.list.ListScreen
import com.abdo.myapplication.util.Constants.LIST_ARGUMENT_KEY
import com.abdo.myapplication.util.Constants.LIST_SCREEN

fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (Int) -> Unit
) {
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY) {
            type = NavType.StringType
        }),
    ) {
        ListScreen(navigateToTaskScreen = navigateToTaskScreen)
    }
}