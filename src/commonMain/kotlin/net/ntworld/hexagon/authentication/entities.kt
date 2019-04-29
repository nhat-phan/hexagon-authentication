package net.ntworld.hexagon.authentication

interface User {
    val id: String

    val username: String

    val email: String

    val verified: Boolean

    val lastLoginAt: String?

    val createdAt: String

    val updatedAt: String
}

interface UserRecord : User {
    val tenantId: String

    val salt: String

    val password: String

    val isEnabled: Boolean
}
