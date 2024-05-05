
package Springweb.repository;

import Springweb.entity.ThietBi;
import Springweb.entity.ThongTinSD;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThongTinSDRepository extends CrudRepository<ThongTinSD, Integer> {
    @Query("FROM ThongTinSD WHERE MaTB IS NOT NULL AND TGMuon IS NOT NULL")
    Iterable<ThongTinSD> findAllWithMaTBNotNull();

    @Query("FROM ThongTinSD WHERE MaTB IS NOT NULL AND TGTra IS NULL")
    Iterable<ThongTinSD> findAllWithTGTraIsNull();

    @Query("FROM ThongTinSD WHERE tgdatcho IS NOT NULL ")
    Iterable<ThongTinSD> findAllWithTGDatChoIsNotNull();

    @Query("FROM ThongTinSD WHERE TGVao IS NOT NULL")
    Iterable<ThongTinSD> findAllWithTGVaoNotNull();

    @Query("FROM ThongTinSD WHERE MaTV = :MaTV AND TGVao IS NOT NULL")
    Iterable<ThongTinSD> findByMaTV(int MaTV);

}
