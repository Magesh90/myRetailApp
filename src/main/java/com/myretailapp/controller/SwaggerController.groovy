package com.myretailapp.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping('/swagger')
class SwaggerController {

    @RequestMapping('/hello')
    void sayHello(){
        println('hello')
    }
}
