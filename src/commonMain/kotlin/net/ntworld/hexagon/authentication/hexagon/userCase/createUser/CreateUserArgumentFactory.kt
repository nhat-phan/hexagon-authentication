package net.ntworld.hexagon.authentication.hexagon.userCase.createUser

import net.ntworld.hexagon.authentication.hexagon.*
import net.ntworld.hexagon.authentication.GuestActionArgumentFactory
import net.ntworld.hexagon.foundation.MessageBag
import net.ntworld.hexagon.foundation.MultiTenancyArgumentData

internal class CreateUserArgumentFactory : GuestActionArgumentFactory<CreateUserArgument>() {
    override fun make(data: MultiTenancyArgumentData): CreateUserArgument {
        return CreateUserArgument(
            uniqueId = data.uniqueId,
            currentTenantId = data.currentTenantId,
            currentUserId = data.currentUserId,
            context = data.context,
            username = data.getValue(BUILDER_FIELD_USERNAME),
            email = data.getValue(BUILDER_FIELD_EMAIL),
            password = data.getValue(BUILDER_FIELD_PASSWORD),
            shouldConfirmEmail = data.getValue(BUILDER_FIELD_SHOULD_CONFIRM_EMAIL, true)
        )
    }

    override fun validate(data: MultiTenancyArgumentData, errors: MessageBag): Boolean {
        data.isNotStringOrBlank(BUILDER_FIELD_USERNAME) {
            errors.add(BUILDER_FIELD_USERNAME, "required")
        }

        data.isNotStringOrBlank(BUILDER_FIELD_EMAIL) {
            errors.add(BUILDER_FIELD_EMAIL, "required")
        }

        data.isNotStringOrBlank(BUILDER_FIELD_PASSWORD) {
            errors.add(BUILDER_FIELD_PASSWORD, "required")
        }

        return errors.isEmpty()
    }

}