package com.github.dsetton

import io.vertx.core.AbstractVerticle
import io.vertx.core.Context
import io.vertx.core.Future
import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler

/**
 * Created by daniel on 05/06/17.
 */

class GraphQLVerticle : AbstractVerticle(){

    override fun init(vertx: Vertx?, context: Context?) {
        super.init(vertx, context)
    }

    override fun start(startFuture: Future<Void>?) {
        super.start(startFuture)

        val router = Router.router(vertx)
        router.route().handler(BodyHandler.create())
        router.get("/graphql").handler({})

        vertx.createHttpServer().requestHandler({ req ->
            req.response()
                    .putHeader("content-type", "text/plain")
                    .end("Hello from Vert.x!")
        }).listen(8080)
    }
}