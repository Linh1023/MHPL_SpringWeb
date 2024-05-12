package Springweb.repository;

import Springweb.entity.ThongTinSD;
import Springweb.entity.XuLy;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface XuLyViPhamRepository extends CrudRepository<XuLy, Integer> {
    public List<XuLy> findByMaTVEquals(int id);

//    @Query("SELECT p FROM xuly p WHERE CONCAT(p.MaXL, p.MaTV, p.HinhThucXL, p.SoTien,p.TrangThaiXL) LIKE %?1%")
//public List<XuLy> search(String keyword);
}
