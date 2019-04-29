package net.ntworld.hexagon.authentication

object AuthenticationEvent {
    fun triggerUserCreated(tenantId: String, user: User) {
        TODO("not implemented")
    }

    fun triggerUserUpdated(listener: (tenantId: String, user: User) -> Unit) {
        TODO("not implemented")
    }

    fun triggerUserDeleted(listener: (tenantId: String, user: User) -> Unit) {
        TODO("not implemented")
    }

    fun triggerUserDisabled(listener: (tenantId: String, user: User) -> Unit) {
        TODO("not implemented")
    }

    fun triggerUserEnabled(listener: (tenantId: String, user: User) -> Unit) {
        TODO("not implemented")
    }
}