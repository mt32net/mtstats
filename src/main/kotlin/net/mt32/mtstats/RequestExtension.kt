package net.mt32.mtstats

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import net.mt32.mtstats.githubapi.model.GhUser
import net.mt32.mtstats.githubapi.model.UserResponse
import org.http4k.client.ApacheClient
import org.http4k.core.Request
import org.http4k.routing.path
import java.io.File

val client = ApacheClient()
val formatter = Json {
    ignoreUnknownKeys = true
    isLenient = true
}

fun Request.userInfo(): GhUser {
    val username: String = path(pathName) ?: error("Where is the username?")
    val bodyString = client(GraphQLRequest.fromFile(File("queries/User.graphql"), mapOf("username" to username))).bodyString()
    return formatter.decodeFromString<GraphQLResponse<UserResponse>>(bodyString).data.user
}