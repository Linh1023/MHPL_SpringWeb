/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Springweb.repository;

import Springweb.entity.XuLy;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author dothetung
 */
public interface XuLyRepo extends JpaRepository<XuLy, Long>{
    
//  @Query("SELECT p FROM XuLy p WHERE (p.MaXL LIKE :keyword) OR (p.MaTV LIKE :keyword) OR (p.HinhThucXL LIKE :keyword) OR (p.SoTien LIKE :keyword) OR (p.TrangThaiXL LIKE :keyword)")
//public List<XuLy> search(@Param("keyword") String keyword);

    
}
