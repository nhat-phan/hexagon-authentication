package net.ntworld.hexagon.authentication.hexagon.useCase.createUser

import net.ntworld.hexagon.authentication.AuthorizationService
import net.ntworld.hexagon.authentication.hexagon.RESOURCE_TYPE_USER
import net.ntworld.hexagon.foundation.Argument
import net.ntworld.hexagon.foundation.abac.*

class CreateUserAuthorizer(
    private val authorizationService: AuthorizationService
) : Authorizer, AuthorizationDataDirector {

    override fun constructAuthorizationData(builder: AuthorizationDataBuilder, argument: Argument) {
        builder.copyFrom(argument)
            .withCreateAction()
            .withResource(makeResource(RESOURCE_TYPE_USER))
    }

    override fun authorize(data: AuthorizationData): Boolean {
        return authorizationService.authorize(data)
    }

}