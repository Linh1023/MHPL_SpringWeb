package Springweb.controller;

import Springweb.service.ThietBiService;
import Springweb.entity.ThietBi;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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

@Controller
public class ThietBiController {

    @Autowired
    private ThietBiService thietbiService;

    @PostMapping("/admin/thietbi/dels")
    public String funcTBDels(
            Model m,
            @RequestParam(name = "posts", required = false) List<String> posts,
            RedirectAttributes redirectAttributes) {
        List<String> listnot = new ArrayList<>();
        if (posts != null) {
            for (String post : posts) {
                if (thietbiService.deleteThietBiById(Integer.parseInt(post))) {
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
                redirectAttributes.addFlashAttribute("thongBao", "Thiết bị có mã " + mess + " đang được dùng");
            }
        } else {
            redirectAttributes.addFlashAttribute("thongBao", "Bạn chưa chọn thiết bị nào để xóa");
        }
        return "redirect:/admin/thietbi/all";
    }

    @GetMapping("/admin/thietbi/all")
    public String getAll(@RequestParam(name = "category", required = false) String category,
            @RequestParam(name = "s", required = false) String search, Model m) {
        List<ThietBi> list = null;
        if (category != null && search != null) {
            list = thietbiService.searchThietBi(Integer.parseInt(category), search);
            m.addAttribute("category", category);
            m.addAttribute("search", search);

        } else {
            list = thietbiService.findAll();
        }

        m.addAttribute("list", list);
        m.addAttribute("templateName", "admin/thietbi/thietbi_all");
        return "admin/sample";
    }

    // @GetMapping("/")
    // public String user(Model m) {
    // Iterable<ThietBi> list = thietbiService.findAll();
    // m.addAttribute("list", list);
    // m.addAttribute("templateName", "user_datcho");
    // return "admin/sample";
    // }
    @GetMapping(value = { "/admin/thietbi/edit/{id}" })
    public String edit(@PathVariable("id") int id, Model m) {
        ThietBi cus = thietbiService.findById(id);
        m.addAttribute("thietbi", cus);
        m.addAttribute("templateName", "admin/thietbi/thietbi_edit");
        return "admin/sample";
    }

    @GetMapping("/admin/thietbi/add")
    public String register(Model m) {
        ThietBi cus = new ThietBi();
        m.addAttribute("thietbi", cus);
        m.addAttribute("templateName", "admin/thietbi/thietbi_register");
        return "admin/sample";
    }

    @GetMapping(value = "/thietbi/index")
    @ResponseBody
    public Iterable getAllList(Model m) {
        return thietbiService.findAll();
    }

    @PostMapping("/admin/thietbi/update")
    public String update(Model m, @ModelAttribute("thietbi") ThietBi thietbi, RedirectAttributes redirectAttributes) {
        thietbiService.updateThietBi(thietbi);
        redirectAttributes.addFlashAttribute("thongBao", "Thiết bị Cập nhật thành công");

        return "redirect:/admin/thietbi/all";
    }

    @GetMapping("/admin/thietbi/delete/{id}")
    public String delete(Model m,
            @PathVariable("id") int id,
            RedirectAttributes redirectAttributes) {
        if (thietbiService.deleteThietBiById(id)) {
            redirectAttributes.addFlashAttribute("thongBao", "Thiết Bị đã bị xóa");
        } else {
            redirectAttributes.addFlashAttribute("thongBao", "Thiết Bị đang được sử dụng không thể xóa");
        }
        return "redirect:/admin/thietbi/all";
    }

    @PostMapping("/admin/thietbi/save")
    public String save(Model m, @ModelAttribute("thietbi") ThietBi thietbi, RedirectAttributes redirectAttributes) {
        thietbiService.saveThietBi(thietbi);
        redirectAttributes.addFlashAttribute("thongBao", "Thiết bị đã được thêm");
        return "redirect:/admin/thietbi/all";
    }

    @PostMapping("/upload")
    public String uploadExcelFile(
        @RequestParam("file") MultipartFile file,
        @RequestParam("sheet") String chonsheet,
        RedirectAttributes redirectAttributes) {
        if (!file.isEmpty()) {
            try (InputStream inputStream = file.getInputStream();
                    XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
                Sheet sheet = workbook.getSheetAt(Integer.parseInt(chonsheet));
                if (sheet != null) {
                    if (thietbiService.processSheet(sheet)) {
                        redirectAttributes.addFlashAttribute("thongBao", "import thanh cong");
                        return "redirect:/admin/thietbi/all"; // Redirect to success page}
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/404"; // Redirect to error page if file upload fails
    }
}
