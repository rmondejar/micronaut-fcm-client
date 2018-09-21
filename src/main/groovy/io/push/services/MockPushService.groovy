package io.push.services

import io.micronaut.context.annotation.Primary
import io.micronaut.context.annotation.Requires
import io.push.data.FirebaseResult
import io.push.data.Result
import io.push.data.Message
import io.reactivex.Single

import javax.inject.Singleton

@Primary
@Requires(property = 'spec.name', value = 'messagecontroller')
@Singleton
class MockPushService implements PushService {

    List<Message> messages = []

    @Override
    Single<Result> push(Message msg) {
        messages << msg
        Single.just(new FirebaseResult(messageId: "000000000000")) as Single<Result>
    }

    @Override
    void close() {
        messages.clear()
    }
}