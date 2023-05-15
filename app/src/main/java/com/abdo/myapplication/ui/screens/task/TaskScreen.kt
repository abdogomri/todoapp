package com.abdo.myapplication.ui.screens.task

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.abdo.myapplication.util.Action

@Composable
fun TaskScreen(
    navigateToListScreen: (Action) -> Unit
) {

    Scaffold(
        topBar = { TaskAppBar(navigateToListScreen = navigateToListScreen) },
        content = {}
    )
}