package com.abdo.myapplication.ui.screens.list

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.abdo.myapplication.data.models.Priority
import com.abdo.myapplication.data.models.ToDoTask
import com.abdo.myapplication.ui.theme.LARGE_PADDING
import com.abdo.myapplication.ui.theme.PRIORITY_INDICATOR_SIZE
import com.abdo.myapplication.util.RequestState

@Composable
fun ListContent(
    toDoTaskList: RequestState<List<ToDoTask>>,
    navigateToTaskScreen: (taskId: Int) -> Unit,
    paddingValues: PaddingValues
) {
    if (toDoTaskList is RequestState.Success) {
        if (toDoTaskList.data.isNotEmpty()) {
            DisplayTasks(
                toDoTaskList = toDoTaskList.data,
                navigateToTaskScreen = navigateToTaskScreen,
                paddingValues = paddingValues
            )
        } else {
            EmptyContent(
                paddingValues = paddingValues
            )
        }
    }
}

@Composable
fun DisplayTasks(
    toDoTaskList: List<ToDoTask>,
    navigateToTaskScreen: (taskId: Int) -> Unit,
    paddingValues: PaddingValues
) {
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .background(color = MaterialTheme.colorScheme.onSurface)
    ) {
        items(
            items = toDoTaskList,
            key = { task -> task.id }
        ) { task ->
            TaskItem(
                toDoTask = task,
                navigateToTaskScreen = navigateToTaskScreen
            )
        }
    }
}

@Composable
fun TaskItem(
    toDoTask: ToDoTask,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.onSurface),
        onClick = {
            navigateToTaskScreen(toDoTask.id)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(all = LARGE_PADDING)
                .fillMaxWidth()
        ) {
            Row {
                Text(
                    modifier = Modifier.weight(8f),
                    text = toDoTask.title,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Canvas(
                        modifier = Modifier
                            .size(PRIORITY_INDICATOR_SIZE)
                    ) {
                        drawCircle(
                            color = toDoTask.priority.color
                        )
                    }
                }
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = toDoTask.description,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
fun TaskItemPreview() {
    TaskItem(
        toDoTask = ToDoTask(
            title = "Task number 1",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras lobortis consectetur rutrum. Aenean dignissim orci tortor, quis ultricies quam tincidunt ac. Duis porta laoreet blandit. Donec vitae condimentum ligula, eget pellentesque quam. Nam ornare sem et lectus tincidunt malesuada. Sed leo augue, mattis congue magna quis, pellentesque hendrerit nulla. Vestibulum nec risus tempor, laoreet nibh ac, condimentum eros. Quisque auctor placerat sem, id lobortis dui molestie vitae. Cras nec fermentum erat. Vestibulum dapibus nisi ut nisl faucibus fermentum. Nullam venenatis vel massa ac cursus. Nam urna felis, sodales eu est et, hendrerit feugiat eros. Morbi faucibus vulputate risus sed aliquam. Suspendisse nibh erat, mollis at eros vitae, iaculis malesuada leo. Pellentesque tortor nisl, sagittis a orci quis, placerat volutpat elit. In tincidunt leo sit amet arcu convallis rutrum.",
            priority = Priority.HIGH
        ),
        navigateToTaskScreen = {}
    )
}