/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Springweb.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Springweb.entity.ThanhVien;
import Springweb.entity.ThietBi;
import Springweb.entity.ThongTinSD;
import Springweb.repository.ThanhVienRepository;
import Springweb.repository.ThietBiRepository;
import Springweb.repository.ThongTinSDRepository;

/**
 *
 * @author PC
 */
@Service
public class ThongTinSDService {
    @Autowired
    private ThongTinSDRepository thongTinSDRepository;
    @Autowired
    private ThanhVienRepository thanhVienRepository;
    @Autowired
    private ThietBiRepository thietBiRepository;

    @Autowired
    private HttpServletRequest request;

    public Iterable<ThongTinSD> findAllWithTGVaoNotNull() {
        Iterable<ThongTinSD> list = thongTinSDRepository.findAllWithTGVaoNotNull();
        return list;
    }

    public Iterable<ThongTinSD> findAllWithMaTBNotNull(){
        Iterable<ThongTinSD> list = thongTinSDRepository.findAllWithMaTBNotNull();
        return list;
    }

    public String datChoUserSave(int maTB, String tGDatCho, RedirectAttributes redirectAttributes)
            throws ParseException {

        int maTV = (int) request.getSession().getAttribute("maTV");
        if (tGDatCho.equals("")) {
            System.out.println("chưa chọn thời gian");
            redirectAttributes.addFlashAttribute("thongBao", "Bạn phải chọn thời gian");
            redirectAttributes.addFlashAttribute("tGDatCho", "null");
            return "redirect:/datcho/" + maTB;
        } // nếu người dùng đã chọn thời gian
        else {
            // kiểm tra xem thời gian đó có người đặt hoặc đang được mượn hay chưa
            Iterable<ThongTinSD> thongTinSD = thongTinSDRepository.findAllWithTGDatChoTGMuon(maTB);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date tGDatChoData = dateFormat.parse(tGDatCho);

            for (ThongTinSD tt : thongTinSD) {
                if (isSameDate(tGDatChoData, tt.gettGDatCho()) || isSameDate(tGDatChoData, tt.gettGMuon())) {

                    redirectAttributes.addFlashAttribute("tGDatCho", tGDatCho);
                    redirectAttributes.addFlashAttribute("thongBao",
                            "Thời gian này đã có người đặt hoặc đang được mượn !");
                    return "redirect:/datcho/" + maTB;
                }
            }

            // thêm thời gian đặt vào database
            ThongTinSD ttsd = new ThongTinSD();
            ttsd.setMaTV(maTV);
            ttsd.setMaTB(maTB);
            ttsd.settGDatCho(tGDatChoData);
            thongTinSDRepository.save(ttsd);
            redirectAttributes.addFlashAttribute("thongBao", "Đặt chổ thành công !");
            return "redirect:/";
        }

    }

    public boolean isSameDate(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false; // Nếu một trong hai đối tượng Date là null, không thể so sánh được
        }
        // Lấy các giá trị ngày, tháng và năm của hai ngày
        int day1 = date1.getDate();
        int month1 = date1.getMonth();
        int year1 = date1.getYear();

        int day2 = date2.getDate();
        int month2 = date2.getMonth();
        int year2 = date2.getYear();

        // So sánh các giá trị ngày, tháng và năm
        return (day1 == day2 && month1 == month2 && year1 == year2);
    }

public Iterable<ThongTinSD> getDatCho(){
    Iterable<ThongTinSD> list = thongTinSDRepository.findAllWithTGDatChoIsNotNull();
        
    Date currentTime = new Date();
     for (ThongTinSD thongTinSD : list) {
         // nếu time hiện tại vượt quá time đặt chổ 1h 
         if ( thongTinSD.gettGMuon() == null && thongTinSD.gettGTra() == null && (checkTime(thongTinSD.gettGDatCho()) == false)  ) {
             thongTinSD.settGTra(currentTime);
             thongTinSDRepository.save(thongTinSD);
         }
     }
     return list;
}
public Boolean checkTime ( Date tGDatCho ) {
    long oneHourInMillis = 1 * 60 * 60 * 1000;
    Date tGDatChoCong1h = new Date(tGDatCho.getTime() + oneHourInMillis);
    
    // Kiểm tra điều kiện
    Date tGHienTai = new Date();
    boolean isTimePassed = tGHienTai.before(tGDatChoCong1h);
    return isTimePassed;
 }

 public void addThongTinSD (int maTV, int maTB) {
    ThongTinSD ttsd = new ThongTinSD();
        ttsd.setMaTV(maTV);
        ttsd.setMaTB(maTB);
        Date tGMuon = new Date();
        ttsd.settGMuon(tGMuon);
        thongTinSDRepository.save(ttsd);
        
 }

 public void editThongTinSD(int maTT, int maTV, int maTB,String tGMuon, String tGTra, String trangThai) {
            Optional<ThongTinSD> thongTinSD = thongTinSDRepository.findById(maTT);
        ThongTinSD ttsd;
        ttsd = thongTinSD.get();
        ttsd.setMaTV(maTV);
        ttsd.setMaTB(maTB);
        if (trangThai.equals("muon")) {
            ttsd.settGTra(null);
        } else if (trangThai.equals("datra")) {
            if (tGTra.equals("")) {
                Date timeTra = new Date();
                ttsd.settGTra(timeTra);
            }
        }
        thongTinSDRepository.save(ttsd);
 }

 public void deleteThongTinSD(int maTT) {
    Optional<ThongTinSD> thongTinSD = thongTinSDRepository.findById(maTT);
    ThongTinSD ttsd = thongTinSD.get();
    thongTinSDRepository.delete(ttsd);
 }

 public void deleteThongTinSDDatCho(int maTT) {
    Optional<ThongTinSD> thongTinSD = thongTinSDRepository.findById(maTT);
    ThongTinSD ttsd = thongTinSD.get();
    thongTinSDRepository.delete(ttsd);
 }

 public String duyetThongTinSD(int maTT, RedirectAttributes redirectAttributes ){
    Optional<ThongTinSD> thongTinSD = thongTinSDRepository.findById(maTT);
         ThongTinSD ttsd = thongTinSD.get();
         Date tGHienTai  = new Date();
         
         if (tGHienTai.before(ttsd.gettGDatCho())) {
              redirectAttributes.addFlashAttribute("thongBao", "Chưa đến thời gian cho mượn !");
             return "redirect:/admin/thongtinsd/datcho";
         } else {
             Iterable<ThongTinSD> tt = thongTinSDRepository.findAllWithTGDatChoTGMuon(ttsd.getMaTB());
          for (ThongTinSD ttsd1 : tt) {
              if (ttsd1.gettGMuon() == null) {
                  
              }  
              else if (ttsd1.gettGMuon().before(tGHienTai)) {
                   redirectAttributes.addFlashAttribute("thongBao", "Thiết bị này chưa được trả !");
                  return "redirect:/admin/thongtinsd/datcho";
              } 
          }
         
         Date tGMuon = new Date();
         ttsd.settGMuon(tGMuon);
         thongTinSDRepository.save(ttsd);
         return "redirect:/admin/thongtinsd/datcho";
         }
 }

 public void tuchoiThongTinSD(int maTT) {
    Optional<ThongTinSD> thongTinSD = thongTinSDRepository.findById(maTT);
    ThongTinSD ttsd = thongTinSD.get();
    Date tGTra = new Date();
    ttsd.settGTra(tGTra);
    thongTinSDRepository.save(ttsd);
 }


  public Model showThongTinSDForm(Model m) {
           Iterable<ThanhVien> listTV = thanhVienRepository.findAll();
        
        Iterable<ThongTinSD> listTBSD = thongTinSDRepository.findAllWithTGTraIsNull();
        
        ArrayList<Integer> arrMaTB = new ArrayList<>();
        
        Date tGHienTai = new Date();
        for (ThongTinSD thongTinSD : listTBSD) {
            if (isSameDate(tGHienTai, thongTinSD.gettGDatCho()) || isSameDate(tGHienTai, thongTinSD.gettGMuon())) {
                arrMaTB.add(thongTinSD.getMaTB());
            }
        }
        
        Iterable<ThietBi> listTB = thietBiRepository.findAll();
        ArrayList<ThietBi> listTBKSD = new ArrayList<>();
        
        for (ThietBi thietBi : listTB) {
            Integer maTB = thietBi.getMaTB();
            if (arrMaTB.contains(maTB) == false) {
                listTBKSD.add(thietBi);
            }
        }
        
        m.addAttribute("listTV", listTV);
        m.addAttribute("listTB", listTBKSD);
        return m;
  }

   public Model setValue(int maTT, Model m) {
    Iterable<ThanhVien> listTV = thanhVienRepository.findAll();
    m.addAttribute("listTV", listTV);

    Iterable<ThongTinSD> listTBSD = thongTinSDRepository.findAllWithTGTraIsNull();

    ArrayList<Integer> arrMaTB = new ArrayList<>();
    
    
           Date tGHienTai = new Date();
    for (ThongTinSD thongTinSD : listTBSD) {
       if (isSameDate(tGHienTai, thongTinSD.gettGDatCho()) || isSameDate(tGHienTai, thongTinSD.gettGMuon())) {
            arrMaTB.add(thongTinSD.getMaTB());
       }
    }

    Optional<ThongTinSD> listTTSD = thongTinSDRepository.findById(maTT);
    ThongTinSD thongTinSD = listTTSD.get();

    Iterable<ThietBi> listTB = thietBiRepository.findAll();
    ArrayList<ThietBi> listTBKSD = new ArrayList<>();

    for (ThietBi thietBi : listTB) {
        Integer maTB = thietBi.getMaTB();
        if (arrMaTB.contains(maTB) == false || (maTB.equals(thongTinSD.getMaTB()))) {
            listTBKSD.add(thietBi);
        }
    }

    m.addAttribute("listTB", listTBKSD);
    m.addAttribute("thongTinSD", thongTinSD);
    return m;
   }
}