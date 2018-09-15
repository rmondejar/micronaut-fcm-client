package io.push.data


import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.CompileStatic

@CompileStatic
class FirebaseResult implements Result {

    @JsonProperty("message_id")
    String messageId

    @JsonProperty("error")
    String error
}