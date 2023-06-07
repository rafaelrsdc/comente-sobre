package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/comente-sobre")
    public String index() {
        return "index";
    }

    @PostMapping("/comente-sobre/buscar")
    public String search(@RequestParam("assunto") String assunto) {
        return "redirect:/comente-sobre/" + assunto;
    }

    @GetMapping("/comente-sobre/{assunto}")
    public String topic(@PathVariable("assunto") String assunto, Model model) {
        model.addAttribute("assunto", assunto);
        return "topic";
    }
}
