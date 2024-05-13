package Springweb.repository;

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

    
    @Query("SELECT COUNT(e.TrangThaiXL), e.TrangThaiXL FROM XuLy e GROUP BY e.TrangThaiXL")
    List<Object[]> countByGroupByTrangThaiXL();
    
    @Query("SELECT x FROM XuLy x WHERE x.TrangThaiXL = 1")
    public List<XuLy> findByTrangThaiXL();
    

    @Query("FROM XuLy  WHERE trangthaixl  = 0 AND matv = ?1")
    public  Iterable<XuLy> checkViPham(int maTV);
}
