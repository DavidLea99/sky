package com.sky.sample.springboot.controller;

import com.github.sky.spring.boot.remoting.BaseRemoting;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by viruser on 2018/7/13.
 */
@RestController
public class DemoController extends BaseRemoting {

    @RequestMapping("/test")
    public String testRemoting(){
        String url = "https://www.taobao.com";
        Map<String, String> map = new HashMap<>();
        map.put("username", "xxxx");
        String result = post(url, "", map);
        return result;
    }
}
