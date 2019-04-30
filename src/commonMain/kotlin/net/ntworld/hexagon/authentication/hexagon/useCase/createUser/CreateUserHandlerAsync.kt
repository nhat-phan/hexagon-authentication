package net.ntworld.hexagon.authentication.hexagon.useCase.createUser

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import net.ntworld.hexagon.authentication.*
import net.ntworld.hexagon.authentication.hexagon.Util
import net.ntworld.hexagon.authentication.hexagon.entity.UserImpl
import net.ntworld.hexagon.foundation.HandlerAsync

internal class CreateUserHandlerAsync(
    private val userRepository: UserRepository,
    private val emailService: EmailService,
    private val cryptoService: CryptoService,
    private val options: Options
) : HandlerAsync<CreateUserArgument, User> {

    override suspend fun handleAsync(argument: CreateUserArgument) = GlobalScope.async {
        assertUserIsNotExists(argument.username, argument.email)

        val salt = Util.generateCode(options.saltLength, options.saltCharset)
        val encryptedPassword = cryptoService.encryptPassword(argument.password, salt)

        val record = userRepository.createUserAsync(
            argument.username,
            argument.email,
            salt,
            encryptedPassword,
            !argument.shouldConfirmEmail
        ).await()

        if (argument.shouldConfirmEmail) {
            launch {
                emailService.sendConfirmation(
                    argument.email,
                    Util.generateCode(options.codeLength, options.codeCharset)
                )
            }
        }

        UserImpl(
            id = record.id,
            username = record.username,
            email = record.email,
            verified = record.verified,
            lastLoginAt = record.lastLoginAt,
            createdAt = record.createdAt,
            updatedAt = record.updatedAt
        )
    }

    private suspend fun assertUserIsNotExists(username: String, email: String) {
        if (userRepository.findByUsernameAsync(username).await() !== null ||
            userRepository.findByEmailAsync(email).await() !== null
        ) {
            throw Exception("Duplicated account.")
        }
    }
}