package net.ntworld.hexagon.authentication

import net.ntworld.hexagon.foundation.MultiTenancyArgument
import net.ntworld.hexagon.foundation.MultiTenancyArgumentFactoryBase
import net.ntworld.hexagon.foundation.MultiTenancyUserArgument

typealias GuestActionArgument = MultiTenancyArgument

typealias GuestActionArgumentFactory<A> = MultiTenancyArgumentFactoryBase<A>

typealias MemberActionArgument = MultiTenancyUserArgument

