package com.testdemo.sample.domain.useCase

interface SendMsgUseCase {

    suspend fun execute(msg: String): Result<String>

}