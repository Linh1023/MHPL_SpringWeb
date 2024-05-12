/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Springweb.service;

import Springweb.entity.XuLy;
import Springweb.repository.ThongTinSDRepository;
import Springweb.repository.XuLyViPhamRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author quang
 */
@Service
public class ThongKeService {

    @Autowired
    private ThongTinSDRepository thongTinSDRepository;

    @Autowired
    private XuLyViPhamRepository xuLyViPhamRepository;
    public List<Object[]> countThanhVienTheoTG() {
        return thongTinSDRepository.countThanhVienTheoThoiGian();
    }

    public List<Object[]> countThanhVienTheoKhoa() {
        return thongTinSDRepository.countThanhVienTheoKhoa();
    }

    public List<Object[]> countThanhVienTheoNganh() {
        return thongTinSDRepository.countThanhVienTheoNganh();
    }

    public List<Object[]> countThanhVienTheoThoiGianLoc(Date start, Date end) {
        return thongTinSDRepository.countThanhVienTheoThoiGianLoc(start, end);
    }

    public long tinhTongTien(List<XuLy> list) {
           long sotien = 0;

        for (XuLy x : list) {
            Integer num = x.getSoTien();
            sotien += num;
        }
        return sotien;
    }
    
    public List<Object[]> countByGroupByTrangThaiXL() {
        return xuLyViPhamRepository.countByGroupByTrangThaiXL();
    }
    
    public List<XuLy> findByTrangThaiXL(){
        return xuLyViPhamRepository.findByTrangThaiXL();
    }
}
