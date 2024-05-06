/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Springweb.controller;

import Springweb.service.EmailService;
import Springweb.service.PasswordResetTokenManager;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping(value = {"/admin/index.html", "/admin/", "/admin"})
    public String home(Model model) {
        model.addAttribute("templateName", "admin/home.html");
        return "admin/sample";
    }

    @GetMapping(value = {"/login", "/login/", "/login.html"})
    public String login() {
        return "login";
    }

    @GetMapping(value = {"/register", "/register/", "/register.html"})
    public String register(Model model) {
        return "register";
    }

    @GetMapping(value = {"/password", "/password/", "password.html"})
    public String paswword(Model model) {
        return "password";
    }

    @PostMapping("/password_reset")
    public String passwordAction(@RequestParam(name = "email", required = true) String email, Model model) {
        if (!email.isEmpty()) {
            EmailService emailService = new EmailService();
            String token;
            try {
                PasswordResetTokenManager prtm = new PasswordResetTokenManager();
                token = prtm.generateToken(email);
                emailService.sendSimpleMessage(email, "Mã Đặt lại mật khẩu", token);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "password_reset";
        }
        return "redirect:/";
    }
//    @GetMapping("/admin/thietbi/add")
//    public String register(Model m) {
//        ThietBi cus = new ThietBi();
//        m.addAttribute("thietbi", cus);
//        m.addAttribute("templateName", "admin/thietbi/thietbi_register");
//        return "admin/sample";
//    }

    @GetMapping(value = {"/404", "/404/", "/404.html"})
    public String error404(Model model) {
        return "404";
    }

    @GetMapping(value = {"/401", "/401/", "/401.html"})
    public String error401(Model model) {
        return "401";
    }

    @GetMapping(value = {"/500", "500/", "500.html"})
    public String error500(Model model) {
        return "500";
    }

}
