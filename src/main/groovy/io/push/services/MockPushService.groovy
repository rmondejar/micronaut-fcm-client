package io.push.services

import io.micronaut.context.annotation.Primary
import io.micronaut.context.annotation.Requires
import io.push.data.Message

import javax.inject.Singleton

@Primary
@Requires(property = 'spec.name', value = 'messagecontroller')
@Singleton
class MockPushService implements PushService {

    List<Message> messages = []

    @Override
    boolean push(Message msg) {
        messages << msg
        true
    }

    @Override
    void close() {}
}