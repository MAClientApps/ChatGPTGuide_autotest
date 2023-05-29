package com.testdemo.sample.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ChatResponse(
    val choices: List<ChatResponseItem> = listOf(),
)

@Serializable
data class ChatResponseItem(
    val text: String = ""
)