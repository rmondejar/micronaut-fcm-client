package io.push.data

import com.fasterxml.jackson.annotation.*;
import groovy.transform.CompileStatic

@CompileStatic
class FirebaseMessage implements Message {

    @JsonProperty("to")
    String to

    @JsonProperty("notification")
    Map<String, String> notification = [:]

    @JsonProperty("data")
    Map<String,String> data = [:]

    FirebaseMessage(Message msg) {

        this.to = msg.to
        this.notification.title = msg.title
        this.notification.body = msg.body
        this.data = msg.data
    }

    @Override
    String getTitle() {
        notification.title
    }

    @Override
    String getBody() {
        notification.body
    }
}