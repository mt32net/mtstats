package net.mt32.mtstats

import kotlinx.serialization.Serializable

@Serializable
data class GraphQLResponse<T>(
    val data: T,
    val errors: List<String> = listOf()
)