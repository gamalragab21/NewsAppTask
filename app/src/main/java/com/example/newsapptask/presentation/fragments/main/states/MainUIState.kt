package com.example.newsapptask.presentation.fragments.main.states

import com.example.newsapptask.domain.models.NewsData

data class MainUIState(
    val loading: Boolean = false,
    val data: List<NewsData> = emptyList(),
    val message: String? = null,
    val errors: MutableMap<String, List<String>?>? = null
)