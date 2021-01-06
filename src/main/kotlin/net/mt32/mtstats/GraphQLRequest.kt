package net.mt32.mtstats

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.http4k.core.Method
import org.http4k.core.Request
import java.io.File

object GraphQLRequest {

    val formatter = Json

    operator fun invoke(query: String, variables: Map<String, String>): Request {
        val variableString = formatter.encodeToString(variables)
        return Request(Method.POST, "https://api.github.com/graphql")
            .header("Authorization", "Bearer ${config.accessToken}")
            .header("Content-Type", "application/json")
            .body("{\"query\":\"$query\", \"variables\": $variableString\"")
    }

    fun fromFile(file: File, variables: Map<String, String>): Request =
        invoke(file.readText().replace("\n", ""), variables)
}