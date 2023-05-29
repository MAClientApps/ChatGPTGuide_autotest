package com.testdemo.sample.domain.repo

interface ChatRepo {

    suspend fun sendMsg(msg: String): Result<String>

}