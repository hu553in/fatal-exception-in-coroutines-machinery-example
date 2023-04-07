package com.example.demo

import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import java.time.Duration
import kotlin.time.toKotlinDuration

class WorkDoneHelper {

    private var workDoneChannel = Channel<Unit>(1)

    suspend fun confirmWorkDone() {
        workDoneChannel.send(Unit)
    }

    suspend fun waitForWorkDone() {
        withContext(Default) {
            withTimeout(timeout = Duration.ofSeconds(10).toKotlinDuration()) {
                workDoneChannel.receive()
            }
        }
    }

}
