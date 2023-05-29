package com.testdemo.sample.di

import com.testdemo.sample.data.repo.ChatRepoImpl
import com.testdemo.sample.data.api.ChatServiceImpl
import com.testdemo.sample.domain.repo.ChatRepo
import com.testdemo.sample.domain.api.ChatService
import com.testdemo.sample.domain.useCase.SendMsgUseCase
import com.testdemo.sample.domain.useCase.SendMsgUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppBindsModule {

    @Binds
    abstract fun bindChatService(chatServiceImpl: ChatServiceImpl): ChatService

    @Binds
    abstract fun bindChatRepo(chatRepoImpl: ChatRepoImpl) : ChatRepo

    @Binds
    abstract fun bindSendMsgUseCase(sendMsgUseCaseImpl: SendMsgUseCaseImpl): SendMsgUseCase

}