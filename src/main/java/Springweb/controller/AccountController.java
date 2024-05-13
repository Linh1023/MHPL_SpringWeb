/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Springweb.controller;

import Springweb.entity.ThanhVien;
import Springweb.entity.ThongTinSD;
import Springweb.entity.XuLy;
import Springweb.repository.ThanhVienRepository;
import Springweb.service.Mail;
import Springweb.service.MailService;
import Springweb.service.PasswordResetTokenManager;
import Springweb.service.ThanhVienService;
import java.security.NoSuchAlgorithmException;
import Springweb.repository.ThongTinSDRepository;
import Springweb.repository.XuLyViPhamRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
    @Autowired
    private ThanhVienService thanhVienService;
    
    @Autowired
    private XuLyViPhamRepository xuLyViPhamRepository;

    @Autowired
    private MailService mailService;

    private String tokenstatic;
    private Integer id;

    
   
     
         @Autowired
    private ThongTinSDRepository thongTinSDRepository;
     
//     /login/dangnhap
    @PostMapping(value = "/login/dangnhap")
    public String dangnhap(@RequestParam("maTV") int maTV,
            @RequestParam("passWord") String passWord,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes) {

        if (maTV == 123123 && passWord.equals("123123")) {
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
     public String hoSo(    Model m,
                            HttpServletRequest request,
                            RedirectAttributes redirectAttributes
 
     ) {
         
       int maTV = (int) request.getSession().getAttribute("maTV");
       String hoTen = (String) request.getSession().getAttribute("hoTen");
       
      
       Optional<ThanhVien> thanhVien = thanhVienRepository.findById(maTV);
       ThanhVien tv = thanhVien.get();
       
       
       
       
        Iterable<ThongTinSD> list = thongTinSDRepository.findAllWithTGDatChoIsNotNullFollowMaTV(maTV);
        Iterable<ThongTinSD> list1 = thongTinSDRepository.findAllWithMaTBNotNullFollowMaTV(maTV);
        List<XuLy> list2 = xuLyViPhamRepository.findByMaTVEquals(maTV);
        
        m.addAttribute("list", list);
      m.addAttribute("list1", list1);
        m.addAttribute("list2", list2);
         
       m.addAttribute("tk", maTV);
       m.addAttribute("hoTen", hoTen);   
       m.addAttribute("thanhVien", tv);   
       m.addAttribute("templateName", "hoso");
      return "sample";
 
    }
     
        @PostMapping(value = "/hoso/save")
        public String savePass(    Model m,
                            HttpServletRequest request,
                            RedirectAttributes redirectAttributes,
                            @RequestParam("password") String password,
                            @RequestParam("newpassword")  String newpassword
                            ) {
      
       int maTV = (int) request.getSession().getAttribute("maTV");
       Iterable<ThanhVien> list = thanhVienRepository.inspectAccount(maTV, password);
       
       Boolean flag = false;
       for (ThanhVien tv : list) {   
         flag = true;
        }
            System.out.println(flag);
       if (flag == true) {
            Optional<ThanhVien> thanhVien = thanhVienRepository.findById(maTV);
            ThanhVien tvien = thanhVien.get();
            tvien.setPassword(newpassword);
            thanhVienRepository.save(tvien);
       } else if (flag == false) {
//           sai mk
  
         redirectAttributes.addFlashAttribute("thongBao", "Sai mật khẩu !");
         redirectAttributes.addFlashAttribute("password", password);
         redirectAttributes.addFlashAttribute("newpassword", newpassword);
       }
       
       return "redirect:/hoso";
 
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
    public String passwordreset(@RequestParam(name = "email", required = true) String email,
            Model model,
            RedirectAttributes redirectAttributes) throws NoSuchAlgorithmException {
        if (!email.isEmpty()) {
            try {
                int idn = thanhVienService.findIdByEmail(email); // Lấy ID từ repository
            System.out.println("id của bạn là : " + idn);
            if (idn != 0) {
                id = idn; // Gán id từ repository vào id
            } 
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("thongBao1", "Email không tồn tại !");
                return "redirect:/password";  
            }
            
            PasswordResetTokenManager prtm = new PasswordResetTokenManager();
            String token = prtm.generateToken();
            tokenstatic = token;
            Mail mail = new Mail();
            mail.setMailFrom("sender@gmail.com");
            mail.setMailTo(email);
            mail.setMailSubject("Mã Đặt lại mật khẩu");
            mail.setMailContent(token);
            mailService.sendEmail(mail);
            return "password_reset";
        }
        redirectAttributes.addFlashAttribute("thongBao", "Đặt lại mật khẩu thành công");
        return "redirect:/password";
    }

    @PostMapping("/password/check")
    public String passwordcheck(
            @RequestParam(name = "token", required = true) String token,
            @RequestParam(name = "passwordnew", required = true) String passwordnew,
            @RequestParam(name = "confirmpasswordnew", required = true) String confirmpasswordnew,
            Model model,
            RedirectAttributes redirectAttributes
    ) throws NoSuchAlgorithmException {
        if (token.equals(tokenstatic)) {
            if (passwordnew.equals(confirmpasswordnew)) {
                thanhVienService.setPass(id, confirmpasswordnew);
                redirectAttributes.addFlashAttribute("thongBao", "Đặt lại mật khẩu thành công");
                return "redirect:/login";
            } else {
                redirectAttributes.addFlashAttribute("thongBao", tokenstatic + " tokenstatic " + token);
                return "redirect:/login";
            }
        } else {
            redirectAttributes.addFlashAttribute("thongBao", "Không thành công");
            return "redirect:/login";
        }
    }

}
