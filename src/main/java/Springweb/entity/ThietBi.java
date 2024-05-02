package Springweb.entity;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "thietbi")
public class ThietBi {

    @Id
    @Column(name = "MaTB")
    private int MaTB;

    @Column(name = "TenTB")
    private String TenTB;

    @Column(name = "MotaTB" )
    private String MoTaTB;

    public int getMaTB() {
        return MaTB;
    }

    public void setMaTB(int MaTB) {
        this.MaTB = MaTB;
    }

    public String getTenTB() {
        return TenTB;
    }

    public void setTenTB(String TenTB) {
        this.TenTB = TenTB;
    }

    public String getMoTaTB() {
        return MoTaTB;
    }

    public void setMoTaTB(String MoTaTB) {
        this.MoTaTB = MoTaTB;
    }
    
    
}
