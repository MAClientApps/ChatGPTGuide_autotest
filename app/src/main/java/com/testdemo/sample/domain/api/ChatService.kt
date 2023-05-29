package com.testdemo.sample.domain.api

interface ChatService {

    suspend fun sendMsg(msg: String): Result<String>

}