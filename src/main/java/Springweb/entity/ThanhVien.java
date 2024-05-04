package Springweb.entity;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "thanhvien")
public class ThanhVien {

    @Id
    @Column(name = "matv")
    private int MaTV;

    @Column(name = "hoten")
    private String HoTen;

    @Column(name = "khoa" )
    private String Khoa;

    @Column(name = "nganh" )
    private String Nganh;
    
    @Column(name = "sdt" )
    private String Sdt;
    
    @Column(name = "password" )
    private String Password;
    
    @Column(name = "email" )
    private String Email;

    public int getMaTV() {
        return MaTV;
    }

    public void setMaTV(int MaTV) {
        this.MaTV = MaTV;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getKhoa() {
        return Khoa;
    }

    public void setKhoa(String Khoa) {
        this.Khoa = Khoa;
    }

    public String getNganh() {
        return Nganh;
    }

    public void setNganh(String Nganh) {
        this.Nganh = Nganh;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String Sdt) {
        this.Sdt = Sdt;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    
    
    
}
