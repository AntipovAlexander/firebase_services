package com.antipov.firebaseservices.data.repository

import kotlinx.coroutines.channels.Channel

interface ReactiveRepository {
    fun getChannel(): Channel<String>
}