package com.abdo.myapplication.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdo.myapplication.data.models.ToDoTask
import com.abdo.myapplication.data.repositories.ToDoRepository
import com.abdo.myapplication.util.RequestState
import com.abdo.myapplication.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: ToDoRepository
) : ViewModel() {

    val searchAppBarState: MutableState<SearchAppBarState> =
        mutableStateOf(SearchAppBarState.CLOSED)
    val searchQueryState: MutableState<String> =
        mutableStateOf("")

    private val _allTasks = MutableStateFlow<RequestState<List<ToDoTask>>>(RequestState.Idle)
    val allTasks: StateFlow<RequestState<List<ToDoTask>>> = _allTasks.asStateFlow()

    fun getAllTasks() {
        _allTasks.value = RequestState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getAllTasks.collect {
                    _allTasks.value = RequestState.Success(it)
                }
            } catch (e: Exception) {
                _allTasks.value = RequestState.Error(e)
            }

        }
    }

    private val _selectedTask: MutableStateFlow<ToDoTask?> = MutableStateFlow(null)
    val selectedTask: StateFlow<ToDoTask?> = _selectedTask.asStateFlow()

    fun getSelectedTask(taskId: Int) {
        viewModelScope.launch {
            repository.getSelectedTask(taskId = taskId).collect { task ->
                _selectedTask.value = task
            }
        }
    }


}