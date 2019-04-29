package net.ntworld.hexagon.authentication

import kotlinx.coroutines.Deferred
import net.ntworld.hexagon.authentication.hexagon.userCase.createUser.CreateUserPort
import net.ntworld.hexagon.foundation.Port

class AuthenticationApi(private val spi: AuthenticationServiceProvider) {

    fun createUserPort(): Port<CreateUserArgumentBuilder, User> {
        return CreateUserPort(spi)
    }

    fun inviteUserPort(): Port<InviteUserArgumentBuilder, Boolean> {
        TODO("not implemented")
    }

    fun forgotPasswordPort(): Port<ForgotPasswordArgumentBuilder, Boolean> {
        TODO("not implemented")
    }

    fun changePasswordPort(): Port<ChangePasswordArgumentBuilder, Boolean> {
        TODO("not implemented")
    }

    fun onUserCreated(listener: (tenantId: String, user: User) -> Unit) {
        TODO("not implemented")
    }

    fun onUserUpdated(listener: (tenantId: String, user: User) -> Unit) {
        TODO("not implemented")
    }

    fun onUserDeleted(listener: (tenantId: String, user: User) -> Unit) {
        TODO("not implemented")
    }

    fun onUserDisabled(listener: (tenantId: String, user: User) -> Unit) {
        TODO("not implemented")
    }

    fun onUserEnabled(listener: (tenantId: String, user: User) -> Unit) {
        TODO("not implemented")
    }
}