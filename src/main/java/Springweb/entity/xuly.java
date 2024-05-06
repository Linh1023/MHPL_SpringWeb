package Springweb.entity;

import java.sql.Date;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "xuly")
public class xuly {

    @Column(name = "MaTV")
    private int maTV;

    @Id
    private int maXL;

    @Column(name = "HinhThucXL")
    private String hinhThucSX;

    @Column(name = "SoTien")
    public Integer soTien;

    @Column(name = "NgayXL")
    private Date NgayXL;

    @Column(name = "TrangThaiXL")
    private int TrangThaiXL;

    public void XuLy() {
    }

    public void XuLy(
            int maTV,
            int maXL,
            String hinhThucSX,
            int soTien,
            Date NgayXL,
            int TrangThaiXL) {
        this.maTV = maTV;
        this.maXL = maXL;
        this.hinhThucSX = hinhThucSX;
        this.soTien = soTien;
        this.NgayXL = NgayXL;
        this.TrangThaiXL = TrangThaiXL;
    }
}