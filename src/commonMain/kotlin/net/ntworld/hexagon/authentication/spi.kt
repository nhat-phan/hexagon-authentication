package net.ntworld.hexagon.authentication

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import net.ntworld.hexagon.foundation.abac.AuthorizationData

const val ALPHABET_NUMBER = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"

interface Options {
    val saltLength: Int
        get() = 32

    val saltCharset: String
        get() = ALPHABET_NUMBER

    val codeLength: Int
        get() = 16

    val codeCharset: String
        get() = ALPHABET_NUMBER
}

object DefaultOptions : Options {}

interface UserRepository {
    fun findByUsernameAsync(username: String): Deferred<User?>

    fun findByEmailAsync(email: String): Deferred<User?>

    fun createUserAsync(username: String, email: String, salt: String, password: String, verified: Boolean): Deferred<UserRecord>
}

interface EmailService {
    suspend fun sendInvitation(recipient: String, code: String): Job

    suspend fun sendConfirmation(recipient: String, code: String): Job

    suspend fun sendPasswordRecovery(recipient: String, code: String): Job
}

interface CryptoService {
    fun encryptPassword(password: String, salt: String): String
}

interface AuthorizationService {
    suspend fun authorizeAsync(data: AuthorizationData): Deferred<Boolean>
}

interface AuthenticationServiceProvider {
    fun getAuthorizationService(tenantId: String): AuthorizationService

    fun getUserRepository(tenantId: String): UserRepository

    fun getEmailService(tenantId: String): EmailService

    fun getCryptoService(tenantId: String): CryptoService

    fun getOptions(): Options = DefaultOptions
}