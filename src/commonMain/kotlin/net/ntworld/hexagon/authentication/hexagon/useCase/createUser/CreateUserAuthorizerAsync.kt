package net.ntworld.hexagon.authentication.hexagon.useCase.createUser

import net.ntworld.hexagon.authentication.AuthorizationService
import net.ntworld.hexagon.authentication.hexagon.RESOURCE_TYPE_USER
import net.ntworld.hexagon.foundation.Argument
import net.ntworld.hexagon.foundation.abac.*

class CreateUserAuthorizerAsync(
    private val authorizationService: AuthorizationService
) : AuthorizerAsync, AuthorizationDataDirectorAsync {

    override suspend fun constructAuthorizationDataAsync(builder: AuthorizationDataBuilder, argument: Argument) {
        builder.copyFrom(argument)
            .withCreateAction()
            .withResource(makeResource(RESOURCE_TYPE_USER))
    }

    override suspend fun authorizeAsync(data: AuthorizationData) = authorizationService.authorizeAsync(data)
}