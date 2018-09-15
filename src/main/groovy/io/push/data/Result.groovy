package io.push.data

import groovy.transform.CompileStatic

@CompileStatic
interface Result {
    String getMessageId()
    String getError()
}

