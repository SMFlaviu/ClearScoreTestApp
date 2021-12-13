package com.flaviu.clearscoretestapp.Mock

import com.flaviu.clearscoretestapp.di.NetworkModule
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
class MockModule : NetworkModule() {

    override fun baseUrl(): HttpUrl {
        return "http://localhost:8080/".toHttpUrl()
    }
}