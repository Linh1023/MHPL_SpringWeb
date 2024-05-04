package Springweb.entity;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "thanhvien")
public class ThanhVien {

    @Id
    @Column(name = "MaTV")
    private int MaTV;

    @Column(name = "HoTen")
    private String HoTen;

    @Column(name = "Khoa" )
    private String Khoa;

    @Column(name = "Nganh" )
    private String Nganh;
    
    @Column(name = "SDT" )
    private String Sdt;
    
    @Column(name = "PassWord" )
    private String Password;
    
    @Column(name = "Email" )
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
