package io.push.data

import groovy.transform.CompileStatic
import groovy.transform.ToString

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@ToString
@CompileStatic
class MessageCmd implements Message {

    @NotNull
    @NotBlank
    String to

    @NotNull
    @NotBlank
    String title

    @NotNull
    @NotBlank
    String body

    Map<String,String> data = [:]

}