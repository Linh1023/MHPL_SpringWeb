/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Springweb.service;

import Springweb.entity.ThietBi;
import Springweb.entity.ThongTinSD;
import Springweb.entity.XuLy;
import Springweb.repository.ThongTinSDRepository;
import Springweb.repository.XuLyRepo;
import Springweb.repository.XuLyViPhamRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dothetung
 */
@Service
public class xulyviphamServcice {

  @Autowired
  private XuLyViPhamRepository xulyRepo;
@Autowired
  private XuLyRepo xulyRepo1;

  @Autowired
  public void xulyviphamService(XuLyViPhamRepository xulyRepo) {
    this.xulyRepo = xulyRepo;
  }

  public Iterable<XuLy> findAll() {
    return (Iterable<XuLy>) xulyRepo.findAll();
  }

  public boolean update(XuLy xu_ly) {
    Optional<XuLy> xl = xulyRepo.findById(xu_ly.getMaXL());
    XuLy c;
    c = xl.get();

    c.setMaXL(xu_ly.getMaXL());
    c.setMaTV(xu_ly.getMaTV());
    c.setHinhThucXL(xu_ly.getHinhThucXL());
    c.setSoTien(xu_ly.getSoTien());
    c.setNgayXL(xu_ly.getNgayXL());
    c.setTrangThaiXL(xu_ly.getTrangThaiXL());

    xulyRepo.save(c);
    return true;
  }

  public void delete(int id) {
    Optional<XuLy> XuLyOptional = xulyRepo.findById(id);

    if (XuLyOptional.isPresent()) {
      XuLy xuly = XuLyOptional.get();
      xulyRepo.delete(xuly);
    }
  }

  public void save(XuLy xuly) {
    xulyRepo.save(xuly);
  }
  
  
  
//     public List<XuLy> listAll(String keyword) {
//         if (keyword != null) {
//             return xulyRepo1.search(keyword);
//         }
//         return (List<XuLy>) xulyRepo1.findAll();
//     }

  
}
