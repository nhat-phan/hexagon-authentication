package net.ntworld.hexagon.authentication

import net.ntworld.hexagon.foundation.ArgumentBuilder

interface CreateUserArgumentBuilder : ArgumentBuilder {
    fun setUsername(value: String): CreateUserArgumentBuilder

    fun setEmail(value: String): CreateUserArgumentBuilder

    fun setPassword(value: String): CreateUserArgumentBuilder

    fun setShouldConfirmEmail(value: Boolean): CreateUserArgumentBuilder
}

interface ChangePasswordArgumentBuilder: ArgumentBuilder {
    fun setCurrentPassword(value: String): ChangePasswordArgumentBuilder

    fun setNewPassword(value: String): ChangePasswordArgumentBuilder
}

interface ForgotPasswordArgumentBuilder : ArgumentBuilder {
    fun setEmail(value: String): ForgotPasswordArgumentBuilder
}

interface InviteUserArgumentBuilder : ArgumentBuilder {
    fun setEmail(value: String): InviteUserArgumentBuilder
}
