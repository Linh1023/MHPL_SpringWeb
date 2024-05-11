package Springweb.repository;

import Springweb.entity.ThietBi;
import Springweb.entity.ThongTinSD;
import java.util.Date;

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

    @Query("SELECT ttsd FROM ThongTinSD ttsd JOIN ttsd.thanhVien tv WHERE tv.khoa = :Khoa AND ttsd.tGVao IS NOT NULL")
    Iterable<ThongTinSD> findByKhoa(String Khoa);

    @Query("SELECT ttsd FROM ThongTinSD ttsd JOIN ttsd.thanhVien tv WHERE tv.hoTen = :Name AND ttsd.tGVao IS NOT NULL")
    Iterable<ThongTinSD> findByName(String Name);

    @Query("FROM ThongTinSD WHERE MaTB IS NOT NULL AND TGTra IS NULL AND MaTB =?1")
    Iterable<ThongTinSD> findAllWithTGDatChoTGMuon(int maTB);

    public List<ThongTinSD> findByMaTBEquals(int id);

    @Query("SELECT COUNT(DISTINCT e.maTV), DATE(e.tGVao) FROM ThongTinSD e WHERE e.tGVao IS NOT NULL GROUP BY DATE(e.tGVao)")
    List<Object[]> countThanhVienTheoThoiGian();

    @Query("SELECT COUNT(DISTINCT e.maTV), DATE(e.tGVao) FROM ThongTinSD e WHERE e.tGVao BETWEEN :start AND :end GROUP BY DATE(e.tGVao)")
    List<Object[]> countThanhVienTheoThoiGianLoc(Date start, Date end);

    @Query("SELECT COUNT(DISTINCT e.maTV), t.khoa  FROM ThongTinSD e JOIN ThanhVien t ON e.maTV = t.maTV WHERE e.tGVao IS NOT NULL GROUP BY t.khoa")
    List<Object[]> countThanhVienTheoKhoa();
    
    @Query("SELECT COUNT(DISTINCT e.maTV), t.nganh  FROM ThongTinSD e JOIN ThanhVien t ON e.maTV = t.maTV WHERE e.tGVao IS NOT NULL GROUP BY t.nganh")
    List<Object[]> countThanhVienTheoNganh();
}
