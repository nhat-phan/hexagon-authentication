package net.ntworld.hexagon.authentication.hexagon.useCase.createUser

import net.ntworld.hexagon.authentication.User
import net.ntworld.hexagon.authentication.AuthenticationServiceProvider
import net.ntworld.hexagon.authentication.CreateUserArgumentBuilder
import net.ntworld.hexagon.authentication.hexagon.UserArgumentBuilder
import net.ntworld.hexagon.foundation.Port
import net.ntworld.hexagon.foundation.abac.authorizeBy
import net.ntworld.hexagon.foundation.makePort

val CreateUserPort =
    fun(spi: AuthenticationServiceProvider): Port<CreateUserArgumentBuilder, User> {
        val builder = UserArgumentBuilder()
        val factory = CreateUserArgumentFactory()

        return makePort(builder, factory) {
            val handler = CreateUserHandler(
                spi.getUserRepository(it.currentTenantId),
                spi.getEmailService(it.currentTenantId),
                spi.getCryptoService(it.currentTenantId),
                spi.getOptions()
            )

            handler.authorizeBy(CreateUserAuthorizer(
                spi.getAuthorizationService(it.currentTenantId)
            ))
        }
    }