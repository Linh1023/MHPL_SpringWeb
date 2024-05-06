package Springweb.entity;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "thanhvien")
public class ThanhVien {

    @Id
    @Column(name = "matv")
    private int maTV;

    @Column(name = "hoten")
    private String hoTen;

    @Column(name = "khoa" )
    private String khoa;

    @Column(name = "nganh" )
    private String nganh;
    
    @Column(name = "sdt" )
    private String sdt;
    
    @Column(name = "password" )
    private String password;
    
    @Column(name = "email" )
    private String email;

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getKhoa() {
        return khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    public String getNganh() {
        return nganh;
    }

    public void setNganh(String nganh) {
        this.nganh = nganh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    } 
    
    @ManyToOne
    @JoinColumn(name = "xuly", insertable = false, updatable = false)
    private xuly xuly;

    public xuly getxuly() {
        return xuly;
    }

    public void setxuly(xuly xuly) {
        this.xuly = xuly;
    }
}
