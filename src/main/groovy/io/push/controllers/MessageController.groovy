package io.push.controllers

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Produces
import io.micronaut.validation.Validated
import io.push.data.MessageCmd
import io.push.data.Result
import io.push.services.PushService
import io.reactivex.Single

import javax.validation.Valid

@Slf4j
@CompileStatic
@Controller('/msg')
@Validated
class MessageController {

    PushService pushService

    MessageController( PushService  pushService) {
        this.pushService = pushService
    }

    @Get('/ping')
    @Produces(MediaType.TEXT_PLAIN)
    String ping() {
        "pong"
    }

    @Post('/push')
    @Produces(MediaType.APPLICATION_JSON)
    Single<Result> send(@Body @Valid MessageCmd cmd) {

        Single<Result> resultSingle = pushService.push(cmd)

        resultSingle
    }
}