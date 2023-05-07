package com.abdo.myapplication.data.models

import androidx.compose.ui.graphics.Color
import com.abdo.myapplication.ui.theme.HighPriorityColor
import com.abdo.myapplication.ui.theme.LowPriorityColor
import com.abdo.myapplication.ui.theme.MediumPriorityColor
import com.abdo.myapplication.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)

}