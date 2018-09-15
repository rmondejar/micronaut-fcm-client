package io.push.services

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import io.push.data.Message

import javax.inject.Singleton
import javax.validation.ConstraintViolation
import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory

@Singleton
@Slf4j
@CompileStatic
class ValidationService {

    private Validator validator

    ValidationService() {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory()
        validator = factory.getValidator()
    }

    boolean check(Message msg) {

        Set<ConstraintViolation<Message>> violations = validator.validate(msg)

        violations?.isEmpty()
    }
}