package io.push.data

import groovy.transform.CompileStatic

@CompileStatic
interface Message {
    String getTo()
    String getTitle()
    String getBody()
    Map<String,String> getData()

}

