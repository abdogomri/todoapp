package com.abdo.myapplication.ui.screens.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abdo.myapplication.R
import com.abdo.myapplication.components.PriorityDropDown
import com.abdo.myapplication.data.models.Priority
import com.abdo.myapplication.ui.theme.LARGE_PADDING
import com.abdo.myapplication.ui.theme.MEDIUM_PADDING

@Composable
fun TaskContent(
    title: String,
    onTitleChanged: (String) -> Unit,
    description: String,
    onDescriptionChanged: (String) -> Unit,
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit,
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues = paddingValues)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = onTitleChanged,
            label = {
                Text(text = stringResource(R.string.title))
            },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(MEDIUM_PADDING))
        PriorityDropDown(
            priority = priority,
            onPrioritySelected = onPrioritySelected
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxSize(),
            value = description,
            onValueChange = onDescriptionChanged,
            label = {
                Text(text = stringResource(R.string.description))
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TaskContentPreview() {
    TaskContent(
        title = "",
        onTitleChanged = {},
        description = "",
        onDescriptionChanged = {},
        priority = Priority.HIGH,
        onPrioritySelected = {},
        paddingValues = PaddingValues(16.dp)
    )
}