package io.push.services

import io.micronaut.context.ApplicationContext
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.runtime.server.EmbeddedServer
import io.push.data.MessageCmd
import io.push.data.Result
import io.reactivex.Single
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class FirebasePushServiceSpec extends Specification {

    @Shared
    @AutoCleanup
    EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer, 'test')

    @Shared
    @AutoCleanup
    FirebasePushService service = embeddedServer.applicationContext.getBean(FirebasePushService)

    def "/msg/push successful invokation"() {
        given:
        MessageCmd cmd = new MessageCmd(
                to: '/topics/test',
                title: 'Test',
                body: 'Hello mobile apps')

        when:
        boolean succ = service.push(cmd)

        then:
        succ
    }

    def "/msg/push cannot be invoked without title"() {
        given:
        MessageCmd cmd = new MessageCmd(
                to: '/topics/test',
                body: 'Hello mobile apps')

        when:
        Single<Result> single = service.push(cmd)
        boolean error = single.blockingGet().error

        then:
        error
    }

    def "/msg/push cannot be invoked without to"() {
        given:
        MessageCmd cmd = new MessageCmd(
                title: 'Test',
                body: 'Hello mobile apps')

        when:
        Single<Result> single = service.push(cmd)
        boolean error = single.blockingGet().error

        then:
        error
    }

    def "/msg/push cannot be invoked without body"() {
        given:
        MessageCmd cmd = new MessageCmd(
                title: 'TEST',
                to: '/topics/test')

        when:
        Single<Result> single = service.push(cmd)
        boolean error = single.blockingGet().error

        then:
        error
    }

}