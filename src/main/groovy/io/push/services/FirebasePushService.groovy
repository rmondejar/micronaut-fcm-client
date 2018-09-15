package io.push.services

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.Client
import io.micronaut.http.client.RxHttpClient
import io.push.conf.FirebaseConfiguration
import io.push.data.FirebaseMessage
import io.push.data.FirebaseResult
import io.push.data.Message
import io.reactivex.Flowable

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Slf4j
@CompileStatic
class FirebasePushService implements PushService {

    ValidationService validationService

    private final RxHttpClient httpClient
    private final String uri
    private String serverKey

    @Inject
    FirebasePushService(@Client(FirebaseConfiguration.FCM_API_URL) RxHttpClient httpClient,
                          FirebaseConfiguration configuration, ValidationService validationService) {

        this.httpClient = httpClient
        uri = FirebaseConfiguration.FCM_API_URL + "/send"
        serverKey = configuration.serverKey

        this.validationService = validationService
    }


    @Override
    boolean push(Message msg) {

        if (!validationService.check(msg)) return false

        FirebaseMessage firebaseMsg = new FirebaseMessage(msg)

        HttpRequest<?> req = HttpRequest.POST(uri, firebaseMsg)

        req.header("Authorization","key=$serverKey").header("Content-Type", "application/json")

        Flowable<HttpResponse<FirebaseResult>> flowable = httpClient.exchange(req, FirebaseResult.class)

        FirebaseResult result = flowable.blockingFirst().body()

        result.messageId
    }

    void close() {
        httpClient.close()
    }
}