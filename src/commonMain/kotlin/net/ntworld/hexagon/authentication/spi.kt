package net.ntworld.hexagon.authentication

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

interface UserRepository {
    fun findByUsername(username: String): User?

    fun findByEmail(email: String): User?

    fun createUser(username: String, email: String, salt: String, password: String, verified: Boolean): UserRecord
}

interface EmailService {
    fun sendInvitation(recipient: String, code: String)

    fun sendConfirmation(recipient: String, code: String)

    fun sendPasswordRecovery(recipient: String, code: String)
}

interface CryptoService {
    fun encryptPassword(password: String, salt: String): String
}

interface AuthorizationService {
    fun authorize(data: AuthorizationData): Boolean
}

interface AuthenticationServiceProvider {
    fun getAuthorizationService(tenantId: String): AuthorizationService

    fun getUserRepository(tenantId: String): UserRepository

    fun getEmailService(tenantId: String): EmailService

    fun getCryptoService(tenantId: String): CryptoService

    fun getOptions(): Options
}