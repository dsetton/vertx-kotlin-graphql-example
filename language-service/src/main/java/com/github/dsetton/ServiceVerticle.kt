package com.github.dsetton

import io.vertx.core.AbstractVerticle
import io.vertx.core.json.JsonArray
import io.vertx.ext.web.Router
import java.util.*
import java.util.Objects.nonNull

/**
 * Created by daniel on 04/06/17.
 */
class ServiceVerticle: AbstractVerticle() {

    override fun start() {
        super.start()

        val router = Router.router(vertx)

        router.get("/languages/").handler({
            it.response().putHeader("content-type", "application/json").end(config().getJsonArray("languages", JsonArray()).toString())
        })

        router.get("/languages/:id").handler({
            val id = it.request().getParam("id").toInt()
            val list = config().getJsonArray("languages", JsonArray()).list
            if (nonNull(id) && id < list.size){
                it.response().putHeader("content-type", "application/json").end(list[id].toString())
            } else {
                it.response().setStatusCode(404).setStatusMessage("Not Found").end()
            }
        })

    }
}