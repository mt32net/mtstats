package net.mt32.mtstats

import kotlinx.serialization.Serializable

@Serializable
data class Config(
    val accessToken: String = "XXX",
    val hotReload: Boolean = false,
)