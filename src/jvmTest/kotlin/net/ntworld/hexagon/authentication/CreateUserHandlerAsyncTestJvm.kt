package net.ntworld.hexagon.authentication

import kotlinx.coroutines.runBlocking
import net.ntworld.hexagon.authentication.CreateUserHandlerAsyncTest
import kotlin.test.Test

class CreateUserHandlerAsyncTestJvm {
    @Test
    fun testHandlerAsync() = runBlocking {
        CreateUserHandlerAsyncTest().testHandlerAsync(
            "any", "any", "any", false, true
        )
    }
}