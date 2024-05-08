/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Springweb.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author dothetung 
 */

@Entity
@Data
@Table(name = "xuly")
public class XuLy {
    @Column(name = "matv")
  private int maTV;

  @Id
  @Column(name = "maxl")
  private int maXL;

    @Column(name = "hinhthucxl")
    private String HinhThucXL; 

  @Column(name = "sotien")
  public Integer soTien;

  @Column(name = "ngayxl")
  private Date NgayXL;

  @Column(name = "trangthaixl")
  private int TrangThaiXL;

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public int getMaXL() {
        return maXL;
    }

    public void setMaXL(int maXL) {
        this.maXL = maXL;
    }

    public String getHinhThucXL() {
        return HinhThucXL;
    }

    public void setHinhThucXL(String HinhThucXL) {
        this.HinhThucXL = HinhThucXL;
    }

   
    public Integer getSoTien() {
        return soTien;
    }

    public void setSoTien(Integer soTien) {
        this.soTien = soTien;
    }

    public Date getNgayXL() {
        return NgayXL;
    }

    public void setNgayXL(Date NgayXL) {
        this.NgayXL = NgayXL;
    }

    public int getTrangThaiXL() {
        return TrangThaiXL;
    }

    public void setTrangThaiXL(int TrangThaiXL) {
        this.TrangThaiXL = TrangThaiXL;
    }
}
