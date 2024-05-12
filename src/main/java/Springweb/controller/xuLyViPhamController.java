package Springweb.controller;

import Springweb.entity.XuLy;
import Springweb.repository.XuLyViPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
  private XuLyViPhamRepository  xulyRepository;

    @GetMapping(value = "/admin/xuly/all")
    public String getAll(Model m) {
    Iterable<XuLy> list = xulyRepository.findAll();
    m.addAttribute("list", list);
    m.addAttribute("templateName", "admin/xuly/xuly_all");
    return "admin/sample";
    
}


}
