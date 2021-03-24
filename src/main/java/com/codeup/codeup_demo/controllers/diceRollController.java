package com.codeup.codeup_demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class diceRollController {
    @GetMapping("/roll-dice")
    public String sayHello(@PathVariable String name, Model model) {
        model.addAttribute("name", name);
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String computerRoll(@PathVariable int n, Model model) {
        model.addAttribute("n", n);
        return "roll-dice";
    }
}
