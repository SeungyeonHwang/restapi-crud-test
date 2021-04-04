package com.example.mvc.controller.exception

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.util.LinkedMultiValueMap

@WebMvcTest // MVC와 관련된 클래스들만 로딩, MVC (단위)테스트 하기위해
@AutoConfigureMockMvc   // 자동으로 MockMvc 설정
class ExceptionApiControllerTest {
    // 브라우저X, 가상의 요청 -> MockMVC
    @Autowired  // 자동으로 MockMvc관련해서 주입을 시켜줌
    lateinit var mockMvc: MockMvc

    @Test
    fun helloTest() {
        mockMvc.perform(
            get("/api/exception/hello")
        ).andExpect(
            status().`is`(400)
        ).andExpect(
            content().string("hello")
        ).andDo(print())
    }

    @Test
    fun getTest() {
        val queryParams = LinkedMultiValueMap<String,String>()  // Key, Value
        queryParams.add("name","steve")
        queryParams.add("age","20")

        mockMvc.perform(
            get("/api/exception").queryParams(queryParams)
        ).andExpect(
            status().isOk
        ).andExpect(
            content().string("steve 20")
        ).andDo(print())
    }

    @Test
    fun getFailTest(){
        val queryParams = LinkedMultiValueMap<String,String>()  // Key, Value
        queryParams.add("name","steve")
        queryParams.add("age","9")

        mockMvc.perform(
            get("/api/exception").queryParams(queryParams)
        ).andExpect(
            status().isBadRequest
        ).andExpect(
            MockMvcResultMatchers.content().contentType("application/json")
        ).andExpect(
            jsonPath("\$.result_code").value("FAIL")    // root -> 역슬래쉬
        ).andExpect(
            jsonPath("\$.errors[0].field").value("age")
        ).andExpect(
            jsonPath("\$.errors[0].value").value("9")
        ).andDo(print())
    }

    //POST
}