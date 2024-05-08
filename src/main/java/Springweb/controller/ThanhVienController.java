package Springweb.controller;

import Springweb.entity.ThanhVien;
import Springweb.repository.ThanhVienRepository;
import Springweb.service.ThanhVienService;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
@Controller
public class ThanhVienController {

    @Autowired
    private ThanhVienRepository thanhvienRepository;

    @Autowired
    private ThanhVienService thanhvienService;

    @PostMapping("/admin/thanhvien/dels")
    public String funcTVDels(
            Model m,
            @RequestParam(name = "posttv", required = false) List<String> posttv,
            RedirectAttributes redirectAttributes) {
        List<String> listnot = new ArrayList<>();
        if (posttv != null) {
            for (String post : posttv) {
                if (thanhvienService.deleteThanhVienById(Integer.parseInt(post))) {
                } else {
                    listnot.add(post);
                }
            }
            if (listnot != null) {
                String mess = listnot.get(0);
                for (String post : listnot) {
                    if (listnot.get(0) == post) {
                        continue;
                    }
                    mess += (", " + post);

                }
                redirectAttributes.addFlashAttribute("thongBao", "Thành viên có mã " + mess + " đang được dùng");
            }
        } else {
            redirectAttributes.addFlashAttribute("thongBao", "Bạn chưa chọn thành viên nào để xóa");
        }
        return "redirect:/admin/thanhvien/all";
    }

    @GetMapping(value = "/admin/thanhvien/all")
    public String getAll(Model m) {
        Iterable<ThanhVien> list = thanhvienRepository.findAll();
        m.addAttribute("list", list);
        m.addAttribute("templateName", "admin/thanhvien/thanhvien_all");
        return "admin/sample";
    }

    @GetMapping(value = "/admin/thanhvien")
    @ResponseBody
    public Iterable getAlltest(Model m) {
        Iterable<ThanhVien> list = thanhvienRepository.findAll();
        return list;
    }

    @GetMapping(value = { "/admin/thanhvien/edit/{id}" })
    public String edit(@PathVariable("id") int id, Model m) {
        Optional<ThanhVien> cus = thanhvienRepository.findById(id);
        cus.ifPresent(thanhvien -> m.addAttribute("thanhvien", cus));
        m.addAttribute("templateName", "admin/thanhvien/thanhvien_edit");
        return "admin/sample";
    }

    @GetMapping("/admin/thanhvien/add")
    public String register(Model m) {
        ThanhVien cus = new ThanhVien();
        m.addAttribute("thanhvien", cus);
        m.addAttribute("templateName", "admin/thanhvien/thanhvien_register");
        return "admin/sample";
    }

    @PostMapping("/admin/thanhvien/update")
    public String update(Model m, @ModelAttribute("thanhvien") ThanhVien thanhvien) {
        Optional<ThanhVien> tb = thanhvienRepository.findById(thanhvien.getMaTV());
        ThanhVien c;
        c = tb.get();
        c.setMaTV(thanhvien.getMaTV());
        c.setHoTen(thanhvien.getHoTen());
        c.setKhoa(thanhvien.getKhoa());
        c.setNganh(thanhvien.getNganh());
        c.setSdt(thanhvien.getSdt());
        c.setPassword(thanhvien.getPassword());
        c.setEmail(thanhvien.getEmail());
        thanhvienRepository.save(c);
        return "redirect:/admin/thanhvien/all";
    }

    @GetMapping("/admin/thanhvien/delete/{id}")
    public String delete(Model m,
            @PathVariable("id") int id,
            RedirectAttributes redirectAttributes) {
        if (thanhvienService.deleteThanhVienById(id)) {
            redirectAttributes.addFlashAttribute("thongBao", "Thành viên đã bị xóa");
        } else {
            redirectAttributes.addFlashAttribute("thongBao", "Thành viên đang được sử dụng không thể xóa");
        }
        return "redirect:/admin/thanhvien/all";
    }

    @PostMapping("/admin/thanhvien/save")
    public String save(Model m, @ModelAttribute("thanhvien") ThanhVien thanhvien) {

        thanhvienRepository.save(thanhvien);

        return "redirect:/admin/thanhvien/all";

    }

    @PostMapping("/uploadthanhvien")
    public String uploadExcelFile(
        @RequestParam("file") MultipartFile file,
        @RequestParam("sheet") String chonsheet,
        RedirectAttributes redirectAttributes) {
        if (!file.isEmpty()) {
            try (InputStream inputStream = file.getInputStream();
                    XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
                Sheet sheet = workbook.getSheetAt(Integer.parseInt(chonsheet));
                if (sheet != null) {
                    if (thanhvienService.processSheet(sheet)) {
                        redirectAttributes.addFlashAttribute("thongBao", "import thanh cong");
                        return "redirect:/admin/thanhvien/all"; // Redirect to success page}
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/404"; // Redirect to error page if file upload fails
    }
}