package net.mt32.mtstats

import org.http4k.core.Method.*
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.bind
import org.http4k.routing.path
import org.http4k.routing.routes
import org.http4k.server.Netty
import org.http4k.server.asServer
import java.io.File

fun main() {
    println("Starting")

    val file: String = File("drawing.svg").readText()

    routes(
        "/" bind GET to { Response(Status.OK).body("ok") },
        "/gh/{username}" bind GET to { req ->
            Response(Status.OK)
                .body(file.replace(Templates.username, req.path("username") ?: "specify pls"))
                .header("Content-Type", "image/svg+xml; charset=utf-8")
        }
    ).asServer(Netty(9009)).start()
}