package com.bootcamp.kotlin.common

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

interface Scope: CoroutineScope {

    class Impl : Scope {
        override lateinit var job: Job
    }

    var job: Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    fun initScope() {
        job = SupervisorJob()
    }

    fun destroyScope() {
        job.cancel()
    }
}