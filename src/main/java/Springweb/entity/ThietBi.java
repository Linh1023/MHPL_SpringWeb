package Springweb.entity;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "thietbi")
public class ThietBi {

    @Id
    @Column(name = "matb")
    private int maTB;

    @Column(name = "tentb")
    private String tenTB;

    @Column(name = "motatb" )
    private String moTaTB;

    public int getMaTB() {
        return maTB;
    }

    public void setMaTB(int maTB) {
        this.maTB = maTB;
    }

    public String getTenTB() {
        return tenTB;
    }

    public void setTenTB(String tenTB) {
        this.tenTB = tenTB;
    }

    public String getMoTaTB() {
        return moTaTB;
    }

    public void setMoTaTB(String moTaTB) {
        this.moTaTB = moTaTB;
    }

    
    
    
}
