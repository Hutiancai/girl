package com.firstboot.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/hello")
public class hello {

    @Autowired
    private com.firstboot.properties.girlproperties girlproperties;

    @GetMapping(value = "say/{id}")
    public String say(@PathVariable("id") Integer id){

        return "id:"+id;
    }
}
