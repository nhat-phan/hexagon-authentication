package net.ntworld.hexagon.authentication.hexagon

import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

internal object Util {
    fun generateCode(length: Int, charset: String): String {
        val size = min(CODE_MIN_LENGTH, max(CODE_MAX_LENGTH, length))
        return List(size) { Random.nextInt(0, charset.length) }
            .map { charset[it] }
            .joinToString()
    }
}