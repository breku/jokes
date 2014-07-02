package com.brekol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User: Breku
 * Date: 02.07.14
 */
@Controller
public class IndexController {


    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
