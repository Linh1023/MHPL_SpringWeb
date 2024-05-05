package Springweb.controller;

import Springweb.service.ThietBiService;
import Springweb.entity.ThietBi;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ThietBiController {

    @Autowired
    private ThietBiService thietbiService;

    @GetMapping("/admin/thietbi/all")
    public String getAll(@RequestParam(name = "category", required = false) String category,
            @RequestParam(name = "s", required = false) String search, Model m) {
        List<ThietBi> list = null;
        if (category != null && search != null) {
            list = thietbiService.searchThietBi(Integer.parseInt(category), search);
            m.addAttribute("category", category);
            m.addAttribute("search", search);
            
        } else {
            // Xử lý khi không có tham số nào được cung cấp
            list = thietbiService.findAll();
        }

        m.addAttribute("list", list);
        m.addAttribute("templateName", "admin/thietbi/thietbi_all");
        return "admin/sample";
    }
    
    @GetMapping("/")
    public String user(Model m) {
        Iterable<ThietBi> list = thietbiRepository.findAll();
        m.addAttribute("list", list);
        m.addAttribute("templateName", "user_datcho");
        return "admin/sample";
    }

    @GetMapping(value = {"/admin/thietbi/edit/{id}"})
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
    public String update(Model m, @ModelAttribute("thietbi") ThietBi thietbi) {
        thietbiService.updateThietBi(thietbi);
        return "redirect:/admin/thietbi/all";
    }

    @GetMapping("/admin/thietbi/delete/{id}")
    public String delete(Model m, @PathVariable("id") int id) {
        thietbiService.deleteThietBiById(id);
        return "redirect:/admin/thietbi/all";
    }

    @PostMapping("/admin/thietbi/save")
    public String save(Model m, @ModelAttribute("thietbi") ThietBi thietbi) {
        thietbiService.saveThietBi(thietbi);
        return "redirect:/admin/thietbi/all";
    }
}
