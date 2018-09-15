package io.push.conf

import groovy.transform.CompileStatic
import io.micronaut.context.annotation.ConfigurationProperties
import io.micronaut.context.annotation.Requires

@CompileStatic
@ConfigurationProperties(FirebaseConfiguration.PREFIX)
@Requires(property = FirebaseConfiguration.PREFIX)
class FirebaseConfiguration {

    public static final String PREFIX = "fcm"
    public static final String FCM_API_URL = "https://fcm.googleapis.com/fcm"

    String serverKey

    Map<String, Object> toMap() {
        [
                serverKey: serverKey

        ] as Map<String, Object>
    }
}