package com.testdemo.sample.domain.useCase

import com.testdemo.sample.domain.repo.ChatRepo
import javax.inject.Inject

class SendMsgUseCaseImpl @Inject constructor(
    private val chatRepo: ChatRepo
) : SendMsgUseCase {

    override suspend fun execute(msg: String): Result<String> {
        return chatRepo.sendMsg(msg)
    }

}