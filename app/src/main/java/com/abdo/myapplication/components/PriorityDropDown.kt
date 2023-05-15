@file:OptIn(ExperimentalMaterial3Api::class)

package com.abdo.myapplication.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.abdo.myapplication.R
import com.abdo.myapplication.data.models.Priority
import com.abdo.myapplication.ui.theme.PRIORITY_INDICATOR_SIZE

@Composable
fun PriorityDropDown(
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }
    var selectedPriority by remember { mutableStateOf(priority) }

    ExposedDropdownMenuBox(
        modifier = Modifier.fillMaxWidth(),
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        OutlinedTextField(
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            readOnly = true,
            value = selectedPriority.name,
            onValueChange = {},
            label = {
                Text(text = stringResource(R.string.priority))
            },
            leadingIcon = {
                Canvas(modifier = Modifier.size(PRIORITY_INDICATOR_SIZE)) {
                    drawCircle(color = selectedPriority.color)
                }
            },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = OutlinedTextFieldDefaults.colors()
        )
        ExposedDropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            Priority.values().slice(0..2).forEach { priority ->
                PriorityItem(
                    priority = priority,
                    onItemClicked = {
                        expanded = false
                        onPrioritySelected(priority)
                        selectedPriority = priority
                    },
                    isSelected = selectedPriority.name == priority.name
                )
            }
        }
    }
}

@Preview
@Composable
fun PriorityDropDownPreview() {
    PriorityDropDown(priority = Priority.HIGH, onPrioritySelected = {})
}