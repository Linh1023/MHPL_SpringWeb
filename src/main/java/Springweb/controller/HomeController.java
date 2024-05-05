/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Springweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = {"/admin/index.html", "/admin/","/admin"})
    public String home(Model model) {
        model.addAttribute("templateName", "admin/home.html");
        return "admin/sample";
    }
    @GetMapping(value = {"/login","/login/","/login.html"})
    public String login() {
        return "login";
    }
    @GetMapping(value = {"/register","/register/","/register.html"})
    public String register(Model model) {
        return "register";
    }
    @GetMapping(value = {"/password","/password/","password.html"})
    public String paswword(Model model) {
        return "password";
    }
    @GetMapping(value = {"/404","/404/","/404.html"})
    public String error404(Model model) {
        return "404";
    }
    @GetMapping(value = {"/401","/401/","/401.html"})
    public String error401(Model model) {
        return "401";
    }
    @GetMapping(value = {"/500","500/","500.html"})
    public String error500(Model model) {
        return "500";
    }
    
    
}
