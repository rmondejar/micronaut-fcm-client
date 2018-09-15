package io.push.services

import groovy.transform.CompileStatic
import io.push.data.Message

@CompileStatic
interface PushService {

    boolean push(Message msg)

    void close()
}
