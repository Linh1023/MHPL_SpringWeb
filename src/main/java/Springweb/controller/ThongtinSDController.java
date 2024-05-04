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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author PC
 */
@Controller
public class ThongtinSDController {
    
     @Autowired
    private ThongTinSDRepository thongTinSDRepository;
     @Autowired
    private ThanhVienRepository thanhVienRepository; 
     @Autowired
    private ThietBiRepository thietBiRepository; 
    
    
    
    @GetMapping(value = "/admin/thongtinsd/all")
     public String getThongTinSD(Model m) {
       Iterable<ThongTinSD> list = thongTinSDRepository.findAllWithMaTBNotNull();
       
       for (ThongTinSD thongTinSD : list) {
            thongTinSD.getThanhVien().getHoTen();
           thongTinSD.getThanhVien().getHoTen();
        }
       
        m.addAttribute("list", list);
        m.addAttribute("templateName", "admin/thongtinsd/thongtinsd_all");
        return "admin/sample";
    }
     

     
     
     @PostMapping(value = "/admin/thongtinsd/add/save")
     public String addThongTinSD( @RequestParam("maTV") int maTV,
                                  @RequestParam("maTB") int maTB) {
        System.out.println("Mã thành viên: " + maTV);
        System.out.println("Mã thiết bị: " + maTB);
        
        ThongTinSD ttsd = new ThongTinSD();
        ttsd.setMaTV(maTV);
        ttsd.setMaTB(maTB);
        Date tGMuon = new Date();
        ttsd.settGMuon(tGMuon);
        thongTinSDRepository.save(ttsd);
        
        

        return "redirect:/admin/thongtinsd/all";
    }
     
     @PostMapping(value = "/admin/thongtinsd/edit/save")
     public String editThongTinSD(@RequestParam("maTT") int maTT,
                                  @RequestParam("maTV") int maTV,
                                  @RequestParam("maTB") int maTB,
                                  @RequestParam("tGMuon") String tGMuon,
                                  @RequestParam("tGTra") String tGTra,
                                  @RequestParam("inlineRadioOptions") String trangThai ) {
        System.out.println("Mã thông tin: " + maTT);
        System.out.println("Mã thành viên: " + maTV);
        System.out.println("Mã thiết bị: " + maTB);
        
        
        System.out.println("Thời gian mượn: " + tGMuon);
        System.out.println("Thời gian trả: " + tGTra);
        System.out.println("Trạng thái: " + trangThai);
        
        Optional<ThongTinSD> thongTinSD = thongTinSDRepository.findById(maTT);
        ThongTinSD ttsd;
        ttsd = thongTinSD.get();
        ttsd.setMaTV(maTV);
        ttsd.setMaTB(maTB);
        if (trangThai.equals("muon")) {
            ttsd.settGTra(null);
        } else if (trangThai.equals("datra")){
            if (tGTra.equals("")) {
                Date timeTra = new Date();
                ttsd.settGTra(timeTra);
            } 
        }
        thongTinSDRepository.save(ttsd);
        
        

        return "redirect:/admin/thongtinsd/all";
    }
     
      @GetMapping(value = {"/admin/thongtinsd/delete/{maTT}"})
     public String deleteThongTinSD(@PathVariable("maTT") int maTT) {
         Optional<ThongTinSD> thongTinSD = thongTinSDRepository.findById(maTT);
         ThongTinSD ttsd = thongTinSD.get();
          thongTinSDRepository.delete(ttsd);

        return "redirect:/admin/thongtinsd/all";
     }
     
     @GetMapping("/admin/thongtinsd/add")
     public String showThongTinSDForm(Model m) {
        Iterable<ThanhVien> listTV = thanhVienRepository.findAll();
        m.addAttribute("listTV", listTV);
        
        Iterable<ThongTinSD> listTBSD = thongTinSDRepository.findAllWithTGTraIsNull();
        
        ArrayList<Integer> arrMaTB = new ArrayList<>();
        for (ThongTinSD thongTinSD : listTBSD) {
           arrMaTB.add(thongTinSD.getMaTB());
        }
        
        Iterable<ThietBi> listTB = thietBiRepository.findAll();
        ArrayList<ThietBi> listTBKSD = new ArrayList<>();
        
        for (ThietBi thietBi : listTB)  {
            Integer maTB = thietBi.getMaTB();
            if (arrMaTB.contains(maTB) == false) {
              listTBKSD.add(thietBi);
            }
        }

        m.addAttribute("listTB", listTBKSD);
        m.addAttribute("templateName", "admin/thongtinsd/thongtinsd_add");
        return "admin/sample";
    }
     
  
     @GetMapping(value = {"/admin/thongtinsd/edit/{maTT}"})
     public String setValue(@PathVariable("maTT") int maTT ,Model m) {
                 Iterable<ThanhVien> listTV = thanhVienRepository.findAll();
        m.addAttribute("listTV", listTV);
        
        Iterable<ThongTinSD> listTBSD = thongTinSDRepository.findAllWithTGTraIsNull();
        
        ArrayList<Integer> arrMaTB = new ArrayList<>();
        for (ThongTinSD thongTinSD : listTBSD) {
           arrMaTB.add(thongTinSD.getMaTB());
        }
        
        Optional<ThongTinSD> listTTSD = thongTinSDRepository.findById(maTT);
        ThongTinSD thongTinSD = listTTSD.get();
        
        Iterable<ThietBi> listTB = thietBiRepository.findAll();
        ArrayList<ThietBi> listTBKSD = new ArrayList<>();
        
        for (ThietBi thietBi : listTB)  {
            Integer maTB = thietBi.getMaTB();
            if (arrMaTB.contains(maTB) == false || (maTB.equals(thongTinSD.getMaTB()))) {
              listTBKSD.add(thietBi);
            }
        }

        m.addAttribute("listTB", listTBKSD);

       
     
        
        m.addAttribute("thongTinSD", thongTinSD);
        m.addAttribute("templateName", "admin/thongtinsd/thongtinsd_edit");
        return "admin/sample";
    }
     
     
}
