package com.example.newsapptask.presentation.fragments.main.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapptask.common.helpers.Resource
import com.example.newsapptask.domain.useCases.GetNewsUseCase
import com.example.newsapptask.presentation.fragments.main.MainUiEvent
import com.example.newsapptask.presentation.fragments.main.states.MainUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
     @ApplicationContext private val context: Context,
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {


    private val _newsUiState = MutableLiveData<MainUIState>()
    val newsUiState get() = _newsUiState

    val mainUiActions = Channel<MainUiEvent>(Channel.UNLIMITED)

    init {
        triggerAction()
    }

    private fun triggerAction() {
        viewModelScope.launch {
            mainUiActions.consumeAsFlow().collect {
                when (it) {
                    is MainUiEvent.LoadNews -> {
                        getAllNews()
                    }

                }
            }
        }
    }

    private fun getAllNews() {
        getNewsUseCase().onEach {
            when (it) {
                is Resource.Success -> {
                    _newsUiState.postValue(
                        MainUIState(
                            data = it.data!!
                        )
                    )
                }
                is Resource.Error -> {
                    _newsUiState.postValue(
                        MainUIState(
                            message = it.message ?: "",
                            errors = it.errors
                        )
                    )
                }
                is Resource.Loading -> {
                    _newsUiState.postValue(MainUIState(loading = true))
                }
            }
        }.launchIn(viewModelScope)

    }


}