package net.ntworld.hexagon.authentication.hexagon

import net.ntworld.hexagon.authentication.CreateUserArgumentBuilder
import net.ntworld.hexagon.foundation.ArgumentBuilderBase

internal class UserArgumentBuilder :
    ArgumentBuilderBase(), CreateUserArgumentBuilder {

    override fun setUsername(value: String): CreateUserArgumentBuilder {
        this.set(BUILDER_FIELD_USERNAME, value.trim())

        return this
    }

    override fun setEmail(value: String): CreateUserArgumentBuilder {
        this.set(BUILDER_FIELD_EMAIL, value.trim().toLowerCase())

        return this
    }

    override fun setPassword(value: String): CreateUserArgumentBuilder {
        this.set(BUILDER_FIELD_PASSWORD, value)

        return this
    }

    override fun setShouldConfirmEmail(value: Boolean): CreateUserArgumentBuilder {
        this.set(BUILDER_FIELD_SHOULD_CONFIRM_EMAIL, value)

        return this
    }

}