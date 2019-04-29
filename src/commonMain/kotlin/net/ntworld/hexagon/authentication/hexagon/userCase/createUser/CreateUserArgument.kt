package net.ntworld.hexagon.authentication.hexagon.userCase.createUser

import net.ntworld.hexagon.authentication.GuestActionArgument
import net.ntworld.hexagon.foundation.ArgumentContext

internal data class CreateUserArgument(
    override val uniqueId: String,
    override val context: ArgumentContext,
    override val currentTenantId: String,
    override val currentUserId: String?,
    val username: String,
    val email: String,
    val password: String,
    val shouldConfirmEmail: Boolean
) : GuestActionArgument