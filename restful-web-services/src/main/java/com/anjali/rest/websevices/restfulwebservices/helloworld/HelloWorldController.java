package com.anjali.rest.websevices.restfulwebservices.helloworld;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//this controller will EXPOSE A REST API
//will give a specific url to the rest api
//task1 : creat simple rest api to return string back
//task 2: create rest api to return json back ( commonly rest api does)
@RestController
public class HelloWorldController {
    //hello-world
    //"Hello -world
//    @RequestMapping(method = RequestMethod.GET, path ="/hello-world" )
    // A better way to do this mapping
    @GetMapping( path ="/hello-world")
    public String helloWorld(){
        return "Hello World";
    }

    //task 2
    @GetMapping( path ="/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World");
    }
}
