package com.codeup.codeup_demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class diceRollController {


    @GetMapping("/roll-dice")
    public String sayHello() {
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String computerRoll(@PathVariable int n, Model model) {
         int randomNumber = (int) Math.floor(Math.random() * 5) + 1;
         String message;
        if(n==randomNumber){
            message="You guessed right!";
        }else{
            message="You guessed wrong!";
        }

model.addAttribute("randomNumber",randomNumber);
        model.addAttribute("n",n);
        model.addAttribute("message",message);


        return "dice-roll";
    }
}
