package Springweb.controller;

import Springweb.entity.ThanhVien;
import Springweb.entity.XuLy;
import Springweb.repository.ThanhVienRepository;
import Springweb.repository.XuLyViPhamRepository;
import Springweb.service.xulyviphamServcice;
import java.util.List;
import java.util.Optional;
// import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author dothetung
 */
@Controller
public class xuLyViPhamController {

  //
  @Autowired
  private XuLyViPhamRepository xulyRepository;

  private ThanhVienRepository thanhvienRepository;

  @Autowired
  private xulyviphamServcice xulyService;

  @Autowired
  public void YourController(ThanhVienRepository thanhvienRepository) {
    this.thanhvienRepository = thanhvienRepository;
  }

  @GetMapping(value = "/admin/xuly/all")
  public String getAll(Model m) {
    // Iterable<XuLy> list = xulyRepository.findAll();
    Iterable<XuLy> list = this.xulyService.findAll();
    m.addAttribute("list", list);
    m.addAttribute("templateName", "admin/xuly/xuly_all");
    return "admin/sample";
  }

  @GetMapping("/admin/xuly/add")
  public String register(Model m) {
    XuLy xl = new XuLy();
    m.addAttribute("xuly", xl);
    m.addAttribute("templateName", "admin/xuly/xuly_register");
    return "admin/sample";
  }

  @GetMapping("/admin/xuly/add2/{id}")
  public String LIST_PICK(Model m, @PathVariable("id") int id) {
    Optional<ThanhVien> cus = thanhvienRepository.findById(id);
    ThanhVien tv = cus.get();
    XuLy xl = new XuLy();
    xl.setMaTV(tv.getMaTV());
    m.addAttribute("xuly", xl);
    m.addAttribute("templateName", "admin/xuly/xuly_register");
    return "admin/sample";
  }

  @GetMapping("/admin/xuly/listthanhvien")
  public String list_thanh_vien(Model m) {
    // ThanhVien tv = new ThanhVien();
    Iterable<ThanhVien> tv = thanhvienRepository.findAll();
    m.addAttribute("list", tv);
    return "admin/xuly/luaThanhVien";
  }

  @GetMapping(value = { "/admin/xuly/edit/{id}" })
  public String edit(@PathVariable("id") int id, Model m) {
    Optional<XuLy> cus = xulyRepository.findById(id);
    cus.ifPresent(xuly -> m.addAttribute("xuly", cus));
    m.addAttribute("templateName", "admin/xuly/xuly_edit");
    return "admin/sample";
  }

  @PostMapping("/admin/xuly/update")
  public String update(
    Model m,
    @ModelAttribute("xuly") XuLy xu_ly,
    RedirectAttributes redirectAttributes
  ) {
    if (this.xulyService.update(xu_ly)) {
      redirectAttributes.addFlashAttribute("thongBao", "Update Done");
    } else {
      redirectAttributes.addFlashAttribute("thongBao", "Update Not Done");
    }
    return "redirect:/admin/xuly/all";
  }

  @PostMapping("/admin/xuly/save")
  public String save(
    Model m,
    @ModelAttribute("xuly") XuLy xu_ly,
    RedirectAttributes redirectAttributes
  ) {
    Optional<XuLy> xl = xulyRepository.findById(xu_ly.getMaXL());

    if (!xl.isPresent()) {
      this.xulyService.save(xu_ly);
      redirectAttributes.addFlashAttribute("thongBao", "Save Successfully");
    } else {
      redirectAttributes.addFlashAttribute("thongBao", "Save Not Successfully");
    }

    return "redirect:/admin/xuly/all";
  }

  @GetMapping(value = { "/admin/xuly/delete/{id}" })
  public String delete(Model m, @PathVariable("id") int id) {
    this.xulyService.delete(id);
    return "redirect:/admin/xuly/all";
  }

  //   @GetMapping(value = { "/admin/xuly/timkiem/{id}" })
  //   public String findMaXL(Model model, @PathVariable("id") String id) {
  //     System.err.println("----" + id);
  //     Optional<XuLy> list = xulyRepository.findById(Integer.parseInt(id));
  //     model.addAttribute("list", list);
  //     model.addAttribute("templateName", "admin/xuly/xuly_all");
  //     return "admin/xuly/xuly_all";
  //   }

//  @RequestMapping("/admin/xuly/find")
//  public String viewHomePage(Model model, @Param("keyword") String keyword) {
//    List<XuLy> listProducts = xulyService.listAll(keyword);
//    model.addAttribute("list", listProducts);
//    model.addAttribute("templateName", "admin/xuly/xuly_all");
//    return "admin/sample";
//  }
}
