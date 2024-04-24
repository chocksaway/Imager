package com.chocksaway.imager.controller;

import com.chocksaway.imager.service.AuthenticationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {
    private final AuthenticationService authenticationService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String loginWithCredentials(@RequestParam String name, @RequestParam String password, ModelMap modelMap) {
        if (authenticationService.authenticate(name, password)) {
            modelMap.addAttribute("name", name);
            logger.info("Login successful");
            return "welcome";
        }

        logger.info("Login failed");
        modelMap.put("errorMessage", "Invalid username or password");
        return "login";
    }

}
