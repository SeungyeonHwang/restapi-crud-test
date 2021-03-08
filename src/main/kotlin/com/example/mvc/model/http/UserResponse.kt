package com.example.mvc.model.http

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

data class UserResponse(
    var result: Result? = null, //default = null
    var description: String? = null,

    @JsonProperty("user") //userRequest, user(response랑 request 이름 다를 경우)
    var user: MutableList<UserRequest>? = null
)

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class) //Camel Case -> Snake Case 변환(이름 맞추기)
data class Result(
    var resultCode: String? = null,
    var resultMessage: String? = null
)