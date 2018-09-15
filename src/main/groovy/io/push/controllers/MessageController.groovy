package io.push.controllers

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import io.push.data.MessageCmd
import io.push.services.PushService
import io.reactivex.Flowable

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

    @Post('/push')
    HttpResponse send(@Body @Valid MessageCmd cmd) {

        boolean succ = pushService.push(cmd)

        succ ? HttpResponse.ok() : HttpResponse.serverError()
    }
}