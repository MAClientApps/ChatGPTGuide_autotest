package com.testdemo.sample.data.api

import com.testdemo.sample.data.model.ChatRequestBody
import com.testdemo.sample.data.model.ChatResponse
import com.testdemo.sample.domain.api.ChatService
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

class ChatServiceImpl @Inject constructor(
    private val httpClient: HttpClient
) : ChatService {

    override suspend fun sendMsg(msg: String): Result<String> {
       try {
           val response = httpClient.post("completions") {
               contentType(ContentType.Application.Json)
               setBody(ChatRequestBody(prompt = msg))
           }
           if(response.status == HttpStatusCode.OK) {
               val chatResponse: ChatResponse = response.body()
               if(chatResponse.choices.isNotEmpty()) {
                   return Result.success(chatResponse.choices[0].text.replace("\n",""))
               }
           }
       } catch (_: Exception) { }
        return Result.failure(Exception())
    }

}