@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.abdo.myapplication.ui.screens.task

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.abdo.myapplication.R
import com.abdo.myapplication.data.models.Priority
import com.abdo.myapplication.data.models.ToDoTask
import com.abdo.myapplication.util.Action

@Composable
fun TaskAppBar(
    selectedTask: ToDoTask?,
    navigateToListScreen: (Action) -> Unit
) {
    if (selectedTask == null) {
        NewTaskAppBar(navigateToListScreen = navigateToListScreen)

    } else {
        ExistingTaskAppBar(
            selectedTask = selectedTask,
            navigateToListScreen = navigateToListScreen
        )
    }
}

@Composable
fun NewTaskAppBar(
    navigateToListScreen: (Action) -> Unit
) {
    TopAppBar(
        navigationIcon = {
            BackAction(onBackClicked = navigateToListScreen)
        },
        title = {
            Text(text = stringResource(R.string.add_task))
        },
        actions = {
            AddAction(onAddClicked = navigateToListScreen)
        }
    )
}

@Composable
fun ExistingTaskAppBar(
    selectedTask: ToDoTask,
    navigateToListScreen: (Action) -> Unit
) {
    TopAppBar(
        navigationIcon = {
            CloseAction(onCloseClicked = navigateToListScreen)
        },
        title = {
            Text(
                text = selectedTask.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        actions = {
            DeleteAction(onDeleteClicked = navigateToListScreen)
            UpdateAction(onUpdateClicked = navigateToListScreen)
        }
    )
}

@Composable
fun BackAction(
    onBackClicked: (Action) -> Unit
) {
    IconButton(onClick = { onBackClicked(Action.NO_ACTION) }) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = stringResource(R.string.back_arrow)
        )
    }
}

@Composable
fun CloseAction(
    onCloseClicked: (Action) -> Unit
) {
    IconButton(onClick = { onCloseClicked(Action.NO_ACTION) }) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = stringResource(R.string.close_icon)
        )
    }
}

@Composable
fun DeleteAction(
    onDeleteClicked: (Action) -> Unit
) {
    IconButton(onClick = { onDeleteClicked(Action.DELETE) }) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = stringResource(R.string.delete_icon)
        )
    }
}

@Composable
fun UpdateAction(
    onUpdateClicked: (Action) -> Unit
) {
    IconButton(onClick = { onUpdateClicked(Action.UPDATE) }) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = stringResource(R.string.update_icon)
        )
    }
}

@Composable
fun AddAction(
    onAddClicked: (Action) -> Unit
) {
    IconButton(onClick = { onAddClicked(Action.ADD) }) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = stringResource(R.string.add_task)
        )
    }
}

@Preview
@Composable
fun NewTaskAppBarPreview() {
    NewTaskAppBar(navigateToListScreen = {})
}

@Preview
@Composable
fun ExistingTaskAppBarPreview() {
    ExistingTaskAppBar(
        selectedTask = ToDoTask(
            title = "Task number 1",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras lobortis consectetur rutrum. Aenean dignissim orci tortor, quis ultricies quam tincidunt ac. Duis porta laoreet blandit. Donec vitae condimentum ligula, eget pellentesque quam. Nam ornare sem et lectus tincidunt malesuada. Sed leo augue, mattis congue magna quis, pellentesque hendrerit nulla. Vestibulum nec risus tempor, laoreet nibh ac, condimentum eros. Quisque auctor placerat sem, id lobortis dui molestie vitae. Cras nec fermentum erat. Vestibulum dapibus nisi ut nisl faucibus fermentum. Nullam venenatis vel massa ac cursus. Nam urna felis, sodales eu est et, hendrerit feugiat eros. Morbi faucibus vulputate risus sed aliquam. Suspendisse nibh erat, mollis at eros vitae, iaculis malesuada leo. Pellentesque tortor nisl, sagittis a orci quis, placerat volutpat elit. In tincidunt leo sit amet arcu convallis rutrum.",
            priority = Priority.HIGH
        ),
        navigateToListScreen = {}
    )
}

