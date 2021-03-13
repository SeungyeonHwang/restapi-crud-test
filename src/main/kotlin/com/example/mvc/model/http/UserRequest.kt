package com.example.mvc.model.http

import com.example.mvc.annotation.StringFormatDateTime
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.springframework.format.annotation.DateTimeFormat
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.constraints.*

//해당 Bean 검증 (UserRequest)
data class UserRequest(

    @field:NotEmpty
    @field:Size(min = 2, max = 8)
    var name: String? = null,

    @field:PositiveOrZero   // 0 < 숫자를 검증 0도 포함(양수)
    var age: Int? = null,

    @field:Email    // email양식
    var email: String? = null,

    @field:NotBlank // 공백을 검증
    var address: String? = null,

    @field:Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}\$")  // 정규식 검증
    var phoneNumber: String? = null, // phoneNumber 양식

    //Valid 까다로울 때, 원하는 조건의 어노테이션 없을 경우 -> annotation 커스터마이징
    @field:StringFormatDateTime(pattern = "yyyy-MM-dd HH:mm:ss", message = "패턴이 올바르지 않습니다.")
    var createdAt: String? = null   // yyyy-MM-dd HH:mm:ss  ex) 2020-10-02 13:00:00
)
//{
    //True면 정상 False면 비정상 -> 매번 이렇게 만들긴 힘들어서 커스텀 어노테이션 만든다 보통 : annotation package, StringFormatDateTime 참고
//    @AssertTrue(message = "생성일자의 패턴은 yyyy-MM-dd HH:mm:ss 여야 합니다") //method는 field X
//    private fun isValidCreatedAt(): Boolean {   // 정상 -> true , 비정상 -> false
//        return try {
//            LocalDateTime.parse(this.createdAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
//            true
//        } catch (e: Exception) {
//            false
//        }
//    }

//}
