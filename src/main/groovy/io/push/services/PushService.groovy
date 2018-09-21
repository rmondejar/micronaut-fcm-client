package io.push.services

import groovy.transform.CompileStatic
import io.push.data.Result
import io.push.data.Message
import io.reactivex.Single

@CompileStatic
interface PushService {

    Single<Result> push(Message msg)

    void close()
}
