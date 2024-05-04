/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Springweb.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Admin
 */
@Data
@Entity
@Table(name = "thongtinsd")
public class ThongTinSD {

    @Id
    @Column(name = "MaTT")
    private int maTT;

    @Column(name = "MaTV")
    private int maTV;

//    dùng integer tại vì có thể null
    @Column(name = "MaTB")
    private Integer maTB;

    @Column(name = "TGVao")
    private Date tGVao;

    @Column(name = "TGMuon")
    private Date tGMuon;

    @Column(name = "TGTra")
    private Date tGTra;

    @Column(name = "TGDatCho")
    private Date tGDatCho;

    
    
    @ManyToOne
    @JoinColumn(name = "MaTV", insertable = false, updatable = false)
    private ThanhVien thanhVien;

    public int getMaTT() {
        return maTT;
    }

    public void setMaTT(int maTT) {
        this.maTT = maTT;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public Integer getMaTB() {
        return maTB;
    }

    public void setMaTB(Integer maTB) {
        this.maTB = maTB;
    }

    public Date gettGVao() {
        return tGVao;
    }

    public void settGVao(Date tGVao) {
        this.tGVao = tGVao;
    }

    public Date gettGMuon() {
        return tGMuon;
    }

    public void settGMuon(Date tGMuon) {
        this.tGMuon = tGMuon;
    }

    public Date gettGTra() {
        return tGTra;
    }

    public void settGTra(Date tGTra) {
        this.tGTra = tGTra;
    }

    public ThanhVien getThanhVien() {
        return thanhVien;
    }

    public void setThanhVien(ThanhVien thanhVien) {
        this.thanhVien = thanhVien;
    }

    public ThietBi getThietBi() {
        return thietBi;
    }

    public void setThietBi(ThietBi thietBi) {
        this.thietBi = thietBi;
    }
    
    public Date gettGDatCho() {
        return tGDatCho;
    }

    public void settGDatCho(Date tGDatCho) {
        this.tGDatCho = tGDatCho;
    }

    @ManyToOne
    @JoinColumn(name = "MaTB", insertable = false, updatable = false)
    private ThietBi thietBi;

   

  
}

