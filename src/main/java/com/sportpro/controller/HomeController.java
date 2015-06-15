package com.sportpro.controller;

import ejb.MySessionRemote;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    
    MySessionRemote mySession = lookupMySessionRemote();

    @RequestMapping({"/", "/home"})
    public String showHomePage(Model model) {
        model.addAttribute("hello", mySession.getResult());
        return "home";
    }

    private ejb.MySessionRemote lookupMySessionRemote() {
        try {
            Context c = new InitialContext();
            return (ejb.MySessionRemote) c.lookup("java:global/EntAppEjb-ejb/MySession!ejb.MySessionRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
