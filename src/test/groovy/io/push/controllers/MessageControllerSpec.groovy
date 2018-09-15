package io.push.controllers

import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.RxHttpClient
import io.micronaut.runtime.server.EmbeddedServer
import io.push.data.MessageCmd
import io.push.services.FirebasePushService
import io.push.services.MockPushService
import io.push.services.PushService
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class MessageControllerSpec extends Specification {

    @Shared
    @AutoCleanup
    EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer, [
            'spec.name': 'messagecontroller',
    ], 'test')

    @Shared
    @AutoCleanup
    RxHttpClient client = embeddedServer.applicationContext.createBean(RxHttpClient, embeddedServer.getURL())

    def "/msg/push interacts once push service"() {
        given:
        MessageCmd cmd = new MessageCmd(title: 'Test',
                to: '/topics/test',
                body: 'Hello Mobile Apps')
        HttpRequest request = HttpRequest.POST('/msg/push', cmd)

        when:
        Collection pushServices = embeddedServer.applicationContext.getBeansOfType(PushService)

        then:
        !pushServices.any { it == FirebasePushService.class}

        when:
        PushService pushService = embeddedServer.applicationContext.getBean(PushService)

        then:
        pushService instanceof MockPushService

        when:
        HttpResponse rsp = client.toBlocking().exchange(request)

        then:
        rsp.status.code == 200
        ((MockPushService) pushService).messages.size() == old(((MockPushService) pushService).messages.size()) + 1
    }
}