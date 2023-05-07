package com.abdo.myapplication.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.abdo.myapplication.R
import com.abdo.myapplication.data.models.Priority
import com.abdo.myapplication.ui.theme.PRIORITY_INDICATOR_SIZE

@Composable
fun PriorityItem(
    priority: Priority,
    onItemClicked: () -> Unit,
    isSelected: Boolean
) {

    DropdownMenuItem(
        text = {
            Text(
                text = priority.name,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        onClick = onItemClicked,
        leadingIcon = {
            Canvas(modifier = Modifier.size(PRIORITY_INDICATOR_SIZE)) {
                drawCircle(color = priority.color)
            }
        },
        trailingIcon = {
            if (isSelected)
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = stringResource(R.string.selected_filter)
                )
        }

    )
}

@Preview
@Composable
fun PriorityItemPreview() {
    PriorityItem(priority = Priority.MEDIUM, {}, true)
}