package Springweb.controller;

import Springweb.entity.ThietBi;
import Springweb.repository.ThietBiRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ThietBiController {

    @Autowired
    private ThietBiRepository thietbiRepository;

    @GetMapping(value = "/admin/thietbi/all")
    public String getAll(Model m) {
        Iterable<ThietBi> list = thietbiRepository.findAll();
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
        Optional<ThietBi> cus = thietbiRepository.findById(id);
        cus.ifPresent(thietbi -> m.addAttribute("thietbi", cus));
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

//    @GetMapping(value = "/thietbi/index")
//    public @ResponseBody
//    Iterable getAllList(Model m) {
//        return thietbiRepository.findAll();
//    }
    @PostMapping("/admin/thietbi/update")
    public String update(Model m, @ModelAttribute("thietbi") ThietBi thietbi) {
        Optional<ThietBi> tb = thietbiRepository.findById(thietbi.getMaTB());
        ThietBi c;
        c = tb.get();
        c.setMaTB(thietbi.getMaTB());
        c.setTenTB(thietbi.getTenTB());
        c.setMoTaTB(thietbi.getMoTaTB());
        thietbiRepository.save(c);
        return "redirect:/admin/thietbi/all";
    }

    @GetMapping(value = {"/admin/thietbi/delete/{id}"})
    public String delete(Model m, @PathVariable("id") int id) {
        Optional<ThietBi> thietbiOptional = thietbiRepository.findById(id);

        if (thietbiOptional.isPresent()) {
            ThietBi thietbi = thietbiOptional.get();
            thietbiRepository.delete(thietbi);
            return "redirect:/admin/thietbi/all";
        } else {
            // Handle the case where the thietbi with the given id doesn't exist
            return "redirect:/admin/thietbi/all";
        }
    }

    @PostMapping("/admin/thietbi/save")
    public String save(Model m, @ModelAttribute("thietbi") ThietBi thietbi) {

        thietbiRepository.save(thietbi);

        return "redirect:/admin/thietbi/all";

    }
}
