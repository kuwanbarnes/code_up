package com.codeup.codeup_demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MathController {

    @GetMapping(path = "/add/{number}/and/{num2}")
    @ResponseBody
    public int add(@PathVariable int number,@PathVariable int num2) {
        return number +num2;
    }
    @RequestMapping(path = "/subtract/{number}/from/{num2}", method = RequestMethod.GET)
    @ResponseBody
    public int subtract(@PathVariable int number, @PathVariable int num2) {
        return num2 - number;
    }
    @RequestMapping(path = "/multiply/{number}/and/{}num2", method = RequestMethod.GET)
    @ResponseBody
    public int multiply(@PathVariable int number,@PathVariable int num2) {
        return num2 * number;
    }
    @RequestMapping(path = "/divide/{number}/by/{num2}", method = RequestMethod.GET)
    @ResponseBody
    public int divide(@PathVariable int number,@PathVariable int num2) {
        return number / num2;
    }
}

