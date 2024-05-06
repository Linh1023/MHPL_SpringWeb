package Springweb.repository;

import Springweb.entity.ThietBi;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThietBiRepository extends CrudRepository<ThietBi, Integer> {
     List<ThietBi> findByMaTBEquals(int maTB);

    List<ThietBi> findByMoTaTBContaining(String moTaTB);

    List<ThietBi> findByTenTBContaining(String tenTB);

    
}