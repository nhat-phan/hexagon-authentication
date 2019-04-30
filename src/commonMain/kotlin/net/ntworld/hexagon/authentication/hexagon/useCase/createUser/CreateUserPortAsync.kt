package net.ntworld.hexagon.authentication.hexagon.useCase.createUser

import net.ntworld.hexagon.authentication.User
import net.ntworld.hexagon.authentication.AuthenticationServiceProvider
import net.ntworld.hexagon.authentication.CreateUserArgumentBuilder
import net.ntworld.hexagon.authentication.hexagon.UserArgumentBuilder
import net.ntworld.hexagon.foundation.PortAsync
import net.ntworld.hexagon.foundation.abac.authorizeBy
import net.ntworld.hexagon.foundation.makePortAsync

val CreateUserPortAsync = fun(spi: AuthenticationServiceProvider): PortAsync<CreateUserArgumentBuilder, User> {
    val builder = UserArgumentBuilder()
    val factory = CreateUserArgumentFactory()

    return makePortAsync(builder, factory) {
        val handler = CreateUserHandlerAsync(
            spi.getUserRepository(it.currentTenantId),
            spi.getEmailService(it.currentTenantId),
            spi.getCryptoService(it.currentTenantId),
            spi.getOptions()
        )

        handler.authorizeBy(CreateUserAuthorizerAsync(
            spi.getAuthorizationService(it.currentTenantId)
        ))
    }
}