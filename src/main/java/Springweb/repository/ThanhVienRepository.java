
package Springweb.repository;

import Springweb.entity.ThanhVien;
import Springweb.entity.ThietBi;
import Springweb.entity.ThongTinSD;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThanhVienRepository extends CrudRepository<ThanhVien, Integer>{

    List<ThanhVien> findByMaTVEquals(int maTV);
    
    @Query("FROM ThanhVien WHERE MaTV = ?1 AND password =?2")
    Iterable<ThanhVien> inspectAccount(int maTV, String pass);
}
