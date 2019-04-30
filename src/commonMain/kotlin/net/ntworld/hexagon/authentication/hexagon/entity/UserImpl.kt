package net.ntworld.hexagon.authentication.hexagon.entity

import net.ntworld.hexagon.authentication.User

internal data class UserImpl(
    override val id: String,
    override val username: String,
    override val email: String,
    override val verified: Boolean,
    override val lastLoginAt: String?,
    override val createdAt: String,
    override val updatedAt: String
) : User