package com.chocksaway.imager.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {
    @RequestMapping("say-hello")
    @ResponseBody
    public String sayHello() {
        return "Hello! What are you learning today.........";
    }

    @RequestMapping("say-hello-html")
    @ResponseBody
    public String sayHelloHtml() {
        return """
                <html>
                <body>say html hello</body
                </html>""";
    }

    @RequestMapping("say-hello-jsp")
    public String sayHelloJsp(Model model) {
        model.addAttribute("name", "milesd");
        return "sayHello";
    }
}
