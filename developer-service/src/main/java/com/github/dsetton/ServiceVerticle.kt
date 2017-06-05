package com.github.dsetton

import io.vertx.core.AbstractVerticle
import io.vertx.core.json.JsonArray
import io.vertx.ext.web.Router
import java.util.Objects.nonNull

/**
 * Created by daniel on 04/06/17.
 */
class ServiceVerticle: AbstractVerticle() {

    override fun start() {
        super.start()

        val router = Router.router(vertx)

        router.get("/developers/").handler({
            it.response().putHeader("content-type", "application/json").end(config().getJsonArray("developers", JsonArray()).toString())
        })

        router.get("/developers/:id").handler({
            val id = it.request().getParam("id").toInt()
            val list = config().getJsonArray("developers", JsonArray()).list
            if (nonNull(id) && id < list.size){
                it.response().putHeader("content-type", "application/json").end(list[id].toString())
            } else {
                it.response().setStatusCode(404).setStatusMessage("Not Found").end()
            }
        })

    }
}