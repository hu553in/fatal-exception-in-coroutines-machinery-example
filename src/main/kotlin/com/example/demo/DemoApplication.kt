package com.example.demo

import io.mockk.coEvery
import io.mockk.spyk
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest

private val workDoneHelper = WorkDoneHelper()
private val someService: SomeService = spyk()
private val testDispatcher = StandardTestDispatcher()

private class SomeService {
    suspend fun someMethod() {
        delay(5000)
        println("someMethod was finished!")
    }
}

class DemoApplication

fun main() = runTest(testDispatcher) {
    coEvery { someService.someMethod() } coAnswers {
        callOriginal()
        workDoneHelper.confirmWorkDone()
    }
    someService.someMethod()
    workDoneHelper.waitForWorkDone()
}
