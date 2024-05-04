
package Springweb.repository;

import Springweb.entity.ThongTinSD;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThongTinSDRepository extends CrudRepository<ThongTinSD, Integer>{
    @Query("FROM ThongTinSD WHERE MaTB IS NOT NULL AND TGMuon IS NOT NULL")
    Iterable<ThongTinSD> findAllWithMaTBNotNull();
    
    @Query("FROM ThongTinSD WHERE MaTB IS NOT NULL AND TGTra IS NULL")
    Iterable<ThongTinSD> findAllWithTGTraIsNull();
    
    @Query("FROM ThongTinSD WHERE tgdatcho IS NOT NULL ")
    Iterable<ThongTinSD> findAllWithTGDatChoIsNotNull();
    
}
