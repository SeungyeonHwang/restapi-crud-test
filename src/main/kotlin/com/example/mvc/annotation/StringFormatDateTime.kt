package com.example.mvc.annotation

import com.example.mvc.validator.StringFormatDateTimeValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

//Annotation으로써 작동하게 하는 Setting
//Validator필요 -> StringFormatDateTimeValidator
@Constraint(validatedBy = [StringFormatDateTimeValidator::class])
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME) //Runtime 에만 활용 할 수 있도록
@MustBeDocumented //Kotlin에서 붙여주기
annotation class StringFormatDateTime(
    //pattern을 받고 message를 출력할 수 있다.
    val pattern: String = "yyyy-MM-dd HH:mm:ss",
    val message: String = "시간형식이 유효하지 않습니다",

    //default 값
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)