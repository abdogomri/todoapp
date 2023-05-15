package com.abdo.myapplication.ui.screens.list

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abdo.myapplication.R
import com.abdo.myapplication.ui.viewmodels.SharedViewModel
import com.abdo.myapplication.util.SearchAppBarState

@Composable
fun ListScreen(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    LaunchedEffect(key1 = true) {
        sharedViewModel.getAllTasks()
    }
    val allTasks by sharedViewModel.allTasks.collectAsStateWithLifecycle()

    val searchAppBarState: SearchAppBarState
            by sharedViewModel.searchAppBarState
    val searchQueryState: String
            by sharedViewModel.searchQueryState
    Scaffold(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.primary),
        topBar = {
            ListAppBar(
                sharedViewModel = sharedViewModel,
                searchAppBarState = searchAppBarState,
                searchQueryState = searchQueryState,
            )
        },
        floatingActionButton = {
            ListFab(onFabClicked = navigateToTaskScreen)
        },
        content = {
            ListContent(
                paddingValues = it, toDoTaskList = allTasks, navigateToTaskScreen = navigateToTaskScreen
            )
        }
    )
}

@Composable
fun ListFab(
    onFabClicked: (taskId: Int) -> Unit
) {
    FloatingActionButton(onClick = {
        onFabClicked(-1)
    }) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(
                id = R.string.add_button
            )
        )
    }
}
