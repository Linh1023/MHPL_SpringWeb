/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Springweb.controller;

import Springweb.entity.ThanhVien;
import Springweb.repository.ThanhVienRepository;
import Springweb.service.EmailService;
import Springweb.service.PasswordResetTokenManager;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author PC
 */
@Controller
public class AccountController {

    @Autowired
    private ThanhVienRepository thanhVienRepository;

//     /login/dangnhap
    @PostMapping(value = "/login/dangnhap")
     public String dangnhap( @RequestParam("maTV") int maTV,
            @RequestParam("passWord") String passWord,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes) {

    if ( maTV == 123123 && passWord.equals("123123")) {
         return "redirect:/admin/";
    }     
         
    Iterable<ThanhVien> list = thanhVienRepository.inspectAccount(maTV, passWord);
   
    for (ThanhVien tv : list) {
          request.getSession().setAttribute("maTV", tv.getMaTV());
          request.getSession().setAttribute("hoTen", tv.getHoTen());
         
          System.out.println("Đã có 1 thành viên");
          return "redirect:/";
    }

        redirectAttributes.addFlashAttribute("thongBao", "Sai tài khoản mật khẩu !");
        redirectAttributes.addFlashAttribute("tk", maTV);
        redirectAttributes.addFlashAttribute("mk", passWord);

        return "redirect:/login";
    }

    @GetMapping(value = "/hoso")
    public String hoSo(Model m,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes) {

        int maTV = (int) request.getSession().getAttribute("maTV");
        String hoTen = (String) request.getSession().getAttribute("hoTen");

        Optional<ThanhVien> thanhVien = thanhVienRepository.findById(maTV);
        ThanhVien tv = thanhVien.get();

        m.addAttribute("tk", maTV);
        m.addAttribute("hoTen", hoTen);
        m.addAttribute("thanhVien", tv);
        m.addAttribute("templateName", "hoso");
        return "sample";

    }

    // @GetMapping("/admin/thietbi/add")
    // public String register(Model m) {
    //     ThietBi cus = new ThietBi();
    //     m.addAttribute("thietbi", cus);
    //     m.addAttribute("templateName", "admin/thietbi/thietbi_register");
    //     return "admin/sample";
    // }
    @PostMapping(value = "/register_submit")
    public String RegisterSubmit(
            @RequestParam("passwordconfirm") String passwordconfirm,
            Model m,
            @ModelAttribute("thanhvien") ThanhVien tv,
            RedirectAttributes redirectAttributes) {
        if (!thanhVienRepository.existsById(tv.getMaTV())) {
            if (passwordconfirm != tv.getPassword()) {
                thanhVienRepository.save(tv);
                redirectAttributes.addFlashAttribute("thongBao", "Đăng ký thành công");
                return "redirect:/login";
            } else {
                m.addAttribute("thongBao", "Xác nhận mật khẩu không chính xác");
            }

        } else {
            m.addAttribute("thongBao", "Mã Thành Viên đã tồn tại");
        }
        m.addAttribute("thanhvien", tv);
        return "register";
    }

    @GetMapping(value = {"/login", "/login/", "/login.html"})
    public String login() {
        return "login";
    }

    @GetMapping(value = {"/register", "/register/", "/register.html"})
    public String register(Model model) {
        ThanhVien tv = new ThanhVien();
        model.addAttribute("thanhvien", tv);
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

}
