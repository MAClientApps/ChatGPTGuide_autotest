package com.testdemo.sample.data.repo

import com.testdemo.sample.domain.repo.ChatRepo
import com.testdemo.sample.domain.api.ChatService
import javax.inject.Inject

class ChatRepoImpl @Inject constructor (
    private val chatService: ChatService
) : ChatRepo {

    override suspend fun sendMsg(msg: String): Result<String> {
        return chatService.sendMsg(msg)
    }

}