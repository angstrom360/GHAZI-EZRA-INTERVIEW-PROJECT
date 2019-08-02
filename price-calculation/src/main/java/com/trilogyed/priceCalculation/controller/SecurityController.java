package com.trilogyed.priceCalculation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class SecurityController {

    @RequestMapping(value = "/loggedin",method= RequestMethod.GET)
    public String loggedIn(Principal principal){
        return "You are now logged in as " + principal.getName() + ".";
    }

    @RequestMapping(value = "/allDone", method = RequestMethod.GET)
    public String allDone(){
        return "Thank you, and see you again :-) !";
    }



}
