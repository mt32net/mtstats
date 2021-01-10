package net.mt32.mtstats

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import net.mt32.mtstats.githubapi.model.UserResponse
import org.http4k.client.ApacheClient
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.bind
import org.http4k.routing.path
import org.http4k.routing.routes
import org.http4k.server.Netty
import org.http4k.server.asServer
import java.io.File

const val pathName = "username"
val file: String = File("drawing.svg").readText()

val config: Config = Json {  }.decodeFromString(File("config.json").readText())

fun main() {
    println("Starting...")
    routes(
        "/" bind GET to { Response(Status.OK).body("ok") },
        "/gh/{$pathName}" bind GET to { req ->
            Response(Status.OK)
                .body(file(config.hotReload).replaceWithUserInfo(req.userInfo()))
                .header("Content-Type", "image/svg+xml; charset=utf-8")
        }
    ).asServer(Netty(9009)).start()
}

fun file(hot: Boolean = false) : String {
    if (hot) return File("drawing.svg").readText()
    return file
}