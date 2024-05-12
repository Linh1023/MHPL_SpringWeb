/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Springweb.controller;

import Springweb.entity.ThanhVien;
import Springweb.entity.ThietBi;
import Springweb.entity.ThongTinSD;
import Springweb.repository.ThanhVienRepository;
import Springweb.repository.ThietBiRepository;
import Springweb.repository.ThongTinSDRepository;
import Springweb.service.KhuVucHocTapService;
import Springweb.service.ThietBiService;
import Springweb.service.ThongTinSDService;
import Springweb.service.ThietBiService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author PC
 */
@Controller
public class ThongtinSDController {




    @Autowired
    private ThongTinSDService thongTinSDService;

    @Autowired
    private KhuVucHocTapService khuVucHocTapService;

    @Autowired
    private ThanhVienRepository thanhVienRepository;

    // duong

    @Autowired
    private ThietBiService thietbiService;
    
    @Autowired
    private HttpServletRequest request;

    // cuong
    @GetMapping(value = "/admin/thanhvien/khuvuchoctap")
    public String getAllThongTinSD(Model m) {
        Iterable<ThongTinSD> list = thongTinSDService.findAllWithTGVaoNotNull();
        m.addAttribute("list", list);
        m.addAttribute("templateName", "admin/thanhvien/khuvuchoctap_all");
        return "admin/sample";
    }

    @GetMapping("/admin/thanhvien/khuvuchoctap_add")
    public String register(Model m) {
        ThongTinSD ttsd = new ThongTinSD();
        List<Integer> options = new ArrayList<>();
        List<ThanhVien> listthanhvien= (List<ThanhVien>) thanhVienRepository.findAll();
        for(ThanhVien tv:listthanhvien){
            options.add(tv.getMaTV());
        }

        // Chuyển danh sách dữ liệu sang view
        m.addAttribute("options", options);
        m.addAttribute("thongtinsd", ttsd);
        m.addAttribute("templateName", "admin/thanhvien/khuVucHocTap_add");
        return "admin/sample";
    }

    @PostMapping("/admin/thanhvien/khuvuchoctap_save")
    public String save(Model m, @ModelAttribute("thongtinsd") ThongTinSD ttsd) {
        khuVucHocTapService.saveThietBi(ttsd);
        return "redirect:/admin/thanhvien/khuvuchoctap";
    }

    @GetMapping("/admin/thanhvien/khuvuchoctapsearch")
    public String getAll(@RequestParam(name = "option", required = false) String option,
            @RequestParam(name = "hihi", required = false) String search, Model m) {
        Iterable<ThongTinSD> list = null;
        if (option != null && search != null) {
            list = khuVucHocTapService.searchTTSD(Integer.parseInt(option), search);
            m.addAttribute("option", option);
            m.addAttribute("search", search);

        } else {
            // Xử lý khi không có tham số nào được cung cấp
            list = khuVucHocTapService.findAll();
        }

        m.addAttribute("list", list);
        m.addAttribute("templateName", "admin/thanhvien/khuvuchoctap_all");
        return "admin/sample";
    }


    
     @GetMapping(value = {"/datcho/{maTB}"})
     public String datChoUser(@PathVariable("maTB") int maTB, Model m) {
         int maTV = (int) request.getSession().getAttribute("maTV");
         String hoTen = (String) request.getSession().getAttribute("hoTen");
         
         ThietBi thietBi = thietbiService.findById(maTB);
         
          m.addAttribute("tk", maTV);
        m.addAttribute("hoTen", hoTen);
         m.addAttribute("thietBi", thietBi);
         m.addAttribute("templateName", "user_xacnhandatcho");
         return "sample";
     }
     
      @PostMapping(value = "/datcho/save")
     public String datChoUserSave( @RequestParam("maTB") int maTB,
                                  @RequestParam("tGDatCho") String tGDatCho, Model m,
                                  RedirectAttributes redirectAttributes) throws ParseException{
     
 
        return thongTinSDService.datChoUserSave(maTB, tGDatCho, redirectAttributes);
          
    }
     
    
    @GetMapping(value = "/admin/thongtinsd/all")
    public String getThongTinSD(Model m) {
        Iterable<ThongTinSD> list = thongTinSDService.findAllWithMaTBNotNull();

        m.addAttribute("list", list);
        m.addAttribute("templateName", "admin/thongtinsd/thongtinsd_all");
        return "admin/sample";
    }

    @GetMapping(value = "/admin/thongtinsd/datcho")
    public String getDatCho(Model m) {
        Iterable<ThongTinSD> list = thongTinSDService.getDatCho();
        
       
        
        
        m.addAttribute("list", list);
        m.addAttribute("templateName", "admin/thongtinsd/thongtinsd_datcho");
        return "admin/sample";
    }
     

     

    @PostMapping(value = "/admin/thongtinsd/add/save")
    public String addThongTinSD(@RequestParam("maTV") int maTV,
            @RequestParam("maTB") int maTB) {
       
        
      thongTinSDService.addThongTinSD(maTV, maTB);
   

        return "redirect:/admin/thongtinsd/all";
    }

    @PostMapping(value = "/admin/thongtinsd/edit/save")
    public String editThongTinSD(@RequestParam("maTT") int maTT,
            @RequestParam("maTV") int maTV,
            @RequestParam("maTB") int maTB,
            @RequestParam("tGMuon") String tGMuon,
            @RequestParam("tGTra") String tGTra,
            @RequestParam("inlineRadioOptions") String trangThai) {

        thongTinSDService.editThongTinSD(maTT, maTV, maTB, tGMuon, tGTra, trangThai);


        return "redirect:/admin/thongtinsd/all";
    }

    @GetMapping(value = { "/admin/thongtinsd/delete/{maTT}" })
    public String deleteThongTinSD(@PathVariable("maTT") int maTT) {
 
        thongTinSDService.deleteThongTinSD(maTT);

        return "redirect:/admin/thongtinsd/all";
    }

    @GetMapping(value = { "/admin/thongtinsd/xoa/{maTT}" })
    public String deleteThongTinSDDatCho(@PathVariable("maTT") int maTT) {
      
        thongTinSDService.deleteThongTinSDDatCho(maTT);
        return "redirect:/admin/thongtinsd/datcho";
     }

      @GetMapping(value = {"/admin/thongtinsd/duyet/{maTT}"})
     public String duyetThongTinSD(@PathVariable("maTT") int maTT,  RedirectAttributes redirectAttributes) {

        return thongTinSDService.duyetThongTinSD(maTT, redirectAttributes);
         
     }
     
      @GetMapping(value = {"/admin/thongtinsd/tuchoi/{maTT}"})
     public String tuchoiThongTinSD(@PathVariable("maTT") int maTT) {
  
        thongTinSDService.tuchoiThongTinSD(maTT);
        return "redirect:/admin/thongtinsd/datcho";
    }

    @GetMapping("/admin/thongtinsd/add")
    public String showThongTinSDForm(Model m) {
      
        m = thongTinSDService.showThongTinSDForm(m);
        m.addAttribute("templateName", "admin/thongtinsd/thongtinsd_add");
        return "admin/sample";
    }

    @GetMapping(value = { "/admin/thongtinsd/edit/{maTT}" })
    public String setValue(@PathVariable("maTT") int maTT, Model m) {
      
        m = thongTinSDService.setValue(maTT, m);
        m.addAttribute("templateName", "admin/thongtinsd/thongtinsd_edit");
        return "admin/sample";
    }

}
