
package Springweb.repository;

import Springweb.entity.ThanhVien;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThanhVienRepository extends CrudRepository<ThanhVien, Integer>{
    
}
