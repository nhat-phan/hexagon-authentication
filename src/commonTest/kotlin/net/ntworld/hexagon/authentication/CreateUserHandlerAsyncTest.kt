package net.ntworld.hexagon.authentication


import io.mockk.MockKDsl
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import net.ntworld.hexagon.authentication.hexagon.UserArgumentBuilder
import net.ntworld.hexagon.authentication.hexagon.useCase.createUser.CreateUserArgumentFactory
import net.ntworld.hexagon.authentication.hexagon.useCase.createUser.CreateUserHandlerAsync

data class UserRecordImpl(
    override val id: String,
    override val username: String,
    override val email: String,
    override val verified: Boolean,
    override val lastLoginAt: String?,
    override val createdAt: String,
    override val updatedAt: String,
    override val tenantId: String,
    override val salt: String,
    override val password: String,
    override val isEnabled: Boolean
) : UserRecord

class CreateUserHandlerAsyncTest {
    private val userRepositoryMock: UserRepository by lazy {
        mock(UserRepository::class)
    }

    private val emailServiceMock: EmailService by lazy {
        MockKDsl.internalMockkClass(UserRepository::class)
        // mockkClass(EmailService::class)
    }

    private val cryptoServiceMock: CryptoService by lazy {
        MockKDsl.internalMockkClass(UserRepository::class)
        // mockkClass(CryptoService::class)
    }

    private val handler: CreateUserHandlerAsync
        get() = CreateUserHandlerAsync(
            userRepositoryMock,
            emailServiceMock,
            cryptoServiceMock,
            DefaultOptions
        )

    suspend fun testHandlerAsync(
        username: String,
        email: String,
        password: String,
        shouldConfirmEmail: Boolean,
        sendEmail: Boolean
    ) {
        val builder = UserArgumentBuilder()
        builder
            .setUniqueId("unique-id")
            .setCurrentTenantId("test")
            .setContextEnvironment("test", "test")
            .setContextDatetime("does-not-matter")
            .setContextIpAddress("127.0.0.1")

        builder
            .setUsername(username)
            .setEmail(email)
            .setPassword(password)
            .setShouldConfirmEmail(shouldConfirmEmail)

        val argument = CreateUserArgumentFactory().makeArgument(builder)

        val encryptedPassword = "encrypted-password"
        every { cryptoServiceMock.encryptPassword(any(), any()) } returns encryptedPassword

        every { userRepositoryMock.findByUsernameAsync(any()) } returns GlobalScope.async { null }
        every { userRepositoryMock.findByEmailAsync(any()) } returns GlobalScope.async { null }
        every {
            userRepositoryMock.createUserAsync(any(), any(), any(), any(), any())
        } returns GlobalScope.async {
            UserRecordImpl(
                id = "1",
                tenantId = "",
                username = username,
                email = email,
                salt = "",
                password = encryptedPassword,
                verified = !shouldConfirmEmail,
                createdAt = "",
                updatedAt = "",
                lastLoginAt = null,
                isEnabled = true
            )
        }

        println(handler.handleAsync(argument).await())

        verify {
            userRepositoryMock.createUserAsync(username, email, any(), encryptedPassword, !shouldConfirmEmail)
        }
    }
}