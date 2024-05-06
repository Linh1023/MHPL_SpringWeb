/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Springweb.controller;

import Springweb.service.ThietBiService;
import Springweb.entity.ThietBi;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ThietBiService thietbiService;

    @GetMapping("/")
    public String getAllUser(
            @RequestParam(name = "category", required = false) String category,
            @RequestParam(name = "s", required = false) String search,
            Model m,
            RedirectAttributes redirectAttributes) {
        if (request.getSession().getAttribute("maTV") == null){
            redirectAttributes.addFlashAttribute("thongBao", "Sai tài khoản mật khẩu !");
            return "redirect:login";
        }
        int maTV = (int) request.getSession().getAttribute("maTV");
        String hoTen = (String) request.getSession().getAttribute("hoTen");

        List<ThietBi> list = null;
        if (category != null && search != null) {
            list = thietbiService.searchThietBi(Integer.parseInt(category), search);
            m.addAttribute("category", category);
            m.addAttribute("search", search);

        } else {
            // Xử lý khi không có tham số nào được cung cấp
            list = thietbiService.findAll();
        }
        m.addAttribute("tk", maTV);
        m.addAttribute("hoTen", hoTen);
        m.addAttribute("list", list);
        m.addAttribute("templateName", "user_datcho");
        return "sample";
    }

    @GetMapping(value = { "/admin/index.html", "/admin/", "/admin" })
    public String home(Model model) {
        model.addAttribute("templateName", "admin/home.html");
        return "admin/sample";
    }


    @GetMapping(value = { "/404", "/404/", "/404.html" })
    public String error404(Model model) {
        return "404";
    }

    @GetMapping(value = { "/401", "/401/", "/401.html" })
    public String error401(Model model) {
        return "401";
    }

    @GetMapping(value = { "/500", "500/", "500.html" })
    public String error500(Model model) {
        return "500";
    }

}
