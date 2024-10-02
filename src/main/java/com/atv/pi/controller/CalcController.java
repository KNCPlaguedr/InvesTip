
package com.atv.pi.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class CalcController {
    
    @GetMapping
    public String calc(Model model) {
        return "Home";
    }
    @GetMapping("/como-investir")
    public String how(Model model) {
    return "howto";
    }
    @GetMapping("/onde-investir")
    public String where(Model model){
        return "where";
    }
    @GetMapping("/login")
    public String login (Model model){
        return "register";
    }
    @GetMapping("/cadastrar")
    public String registrar (Model model){
        return "cad";
    }
    
}
