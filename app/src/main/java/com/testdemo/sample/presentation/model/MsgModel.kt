package com.testdemo.sample.presentation.model

import com.testdemo.sample.common.base.Model

data class MsgModel(
    override var id: String = System.currentTimeMillis().toString(),
    val text: String = "",
    val msgType: MsgType,
    val isSuccess: Boolean = true,
    val created: Long = System.currentTimeMillis(),
): Model<String>

enum class MsgType {
    Me, Other, Loading
}
