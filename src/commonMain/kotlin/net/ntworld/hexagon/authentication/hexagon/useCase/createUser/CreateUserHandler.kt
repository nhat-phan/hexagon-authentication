package net.ntworld.hexagon.authentication.hexagon.useCase.createUser

import net.ntworld.hexagon.authentication.*
import net.ntworld.hexagon.authentication.hexagon.Util
import net.ntworld.hexagon.authentication.hexagon.entity.UserImpl
import net.ntworld.hexagon.foundation.Handler

internal class CreateUserHandler(
    private val userRepository: UserRepository,
    private val emailService: EmailService,
    private val cryptoService: CryptoService,
    private val options: Options
) : Handler<CreateUserArgument, User> {

    override fun handle(argument: CreateUserArgument): UserImpl {
        this.assertUserIsNotExists(argument.username, argument.email)

        val salt = Util.generateCode(options.saltLength, options.saltCharset)
        val encryptedPassword = this.cryptoService.encryptPassword(argument.password, salt)

        val record = this.userRepository.createUser(
            argument.username,
            argument.email,
            salt,
            encryptedPassword,
            !argument.shouldConfirmEmail
        )

        if (argument.shouldConfirmEmail) {
            this.emailService.sendConfirmation(
                argument.email,
                Util.generateCode(options.codeLength, options.codeCharset)
            )
        }

        return UserImpl(
            id = record.id,
            username = record.username,
            email = record.email,
            verified = record.verified,
            lastLoginAt = record.lastLoginAt,
            createdAt = record.createdAt,
            updatedAt = record.updatedAt
        )
    }

    private fun assertUserIsNotExists(username: String, email: String) {
        if (this.userRepository.findByUsername(username) !== null ||
            this.userRepository.findByEmail(email) !== null
        ) {
            throw Exception("Duplicated account.")
        }
    }
}